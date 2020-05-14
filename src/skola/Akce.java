/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skola;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ugla
 */
public class Akce
{
    private int id;
    
    private int den;
    private LocalTime casOd;
    private LocalTime casDo;
    
    private int zacatek;
    private int konec;
    
    private Predmet predmet;
    private String typ;
    private int kapacita;
    
    private Ucebna ucebna;
    private Ucitel ucitel;
    private List<Student> studenti;
    
    public Akce(Predmet predmet, String typ, Ucebna ucebna, String den, LocalTime casOd, LocalTime casDo, Ucitel ucitel)
    {
        this.id = -1;
        
        this.den = Dny.index(den);
        this.casOd = casOd;
        this.casDo = casDo;
        this.predmet = predmet;
        this.typ = typ;
        this.ucebna = ucebna;
        
        this.zacatek = 0;
        this.konec = 0;
        
        this.ucitel = ucitel;
        this.studenti = new ArrayList<>();
        this.kapacita = ucebna.getKapacita();
    }

    public Akce(Predmet predmet, String typ, Ucebna ucebna, String den, LocalTime casOd, LocalTime casDo, Ucitel ucitel, int max)
    {
        this(predmet,typ,ucebna,den,casOd,casDo,ucitel);
        setMaxKapacita(max);
    }
    
    public int delka()
    {
        return konec - zacatek + 1;
    }

    public int getId() {
        return id;
    }
    
    public LocalTime getCasDo() {
        return casDo;
    }

    public LocalTime getCasOd() {
        return casOd;
    }

    public int prihlaseno() {
        return studenti.size();
    }

    public int getKapacita() {
        return kapacita;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public String getTyp() {
        return typ;
    }

    public Ucebna getUcebna() {
        return ucebna;
    }

    public Ucitel getUcitel() {
        return ucitel;
    }

    public int getDen() {
        return den;
    }

    public int getKonec() {
        return konec;
    }

    public int getZacatek() {
        return zacatek;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public int volnychMist() {
        return kapacita - prihlaseno();
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public final void setMaxKapacita(int max) {
        if (prihlaseno() <= max && max <= ucebna.getKapacita())
            this.kapacita = max;
    }

    public void setKonec(int konec) {
        this.konec = konec;
    }

    public void setZacatek(int zacatek) {
        this.zacatek = zacatek;
    }

    public void addStudent(Student student)
    {
        student.addAkce(this);
    }
    
    @Override
    public String toString()
    {
        String s = predmet + " " + typ + ", " + ucebna + ", " + Dny.Zkratka(den) + " " + this.casOd + "-" + this.casDo;
        
        return s;
    }
    
    public String info1()
    {
        return this.toString() + ", " + ucitel + " (" + prihlaseno() + "/" + kapacita + ")";
    }
    
    public String info()
    {
        String s = this.toString();
        
        s += "\nUčitel: " + ucitel.info1() + "\n";
        s += "\nStudenti:\n";
        for (Student t : studenti)
            s += t + "\n";
        
        return s;
    }

    public String line(String sep)
    {
        return predmet + sep + typ + sep + ucebna + sep + Dny.zkratka(den) + sep + casOd + sep + casDo + sep + ucitel + sep + kapacita + "\n";
    }

    public String studenti(String sep)
    {
        StringBuilder b = new StringBuilder(predmet.getZkratka());
        b.append(sep);
        b.append(id);
        
        studenti.forEach((s) -> {
            b.append(sep);
            b.append(s.username);
        });
        b.append("\n");
        
        return b.toString();
    }
}
