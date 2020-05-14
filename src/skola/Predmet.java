/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skola;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bauerpe1
 */
public class Predmet
{
    private String nazev;
    private String zkratka;
    private String semestr;
    private int hodinPrednasek;
    private int hodinCviceni;
    private int kredity;
    
    private Ucitel garant;
    private List<Ucitel> cvicici;
    private List<Ucitel> prednasejici;
    private List<Student> studenti;
    
    private List<Akce> seznamAkci;


    public Predmet(String nazev, String zkratka, String semestr, int prednasky, int cviceni, int kredity, Ucitel garant)
    {
        this.nazev = nazev;
        this.zkratka = zkratka;
        this.semestr = semestr;
        this.hodinPrednasek = prednasky;
        this.hodinCviceni = cviceni;
        this.kredity = kredity;
        
        this.garant = garant;
        this.cvicici = new ArrayList<>();
        this.prednasejici = new ArrayList<>();
        this.studenti = new ArrayList<>();
    
        this.seznamAkci = new ArrayList<>();
    }

    public void addAkce(Akce akce)
    {
        if (akce.getUcebna().lzePridat(akce) && akce.getUcitel().lzePridat(akce))
        {
            if ("přednáška".equals(akce.getTyp()))
                if (!prednasejici.contains(akce.getUcitel()))
                    prednasejici.add(akce.getUcitel());
            if ("cvičení".equals(akce.getTyp()))
                if (!cvicici.contains(akce.getUcitel()))
                    cvicici.add(akce.getUcitel());
        
            int id = seznamAkci.size();
            akce.setId(id);
            seznamAkci.add(akce);
            akce.getUcebna().addAkce(akce);
            akce.getUcitel().addAkce(akce);
        }
        else
        {    
            System.out.println("CHYBA: nelze přidat akci " + akce.info1());
            if (!akce.getUcebna().lzePridat(akce))
                System.out.println("Rozvrh ucebny:\n" + akce.getUcebna().getRozvrh());
            if (!akce.getUcitel().lzePridat(akce))
                System.out.println("Rozvrh ucitele:\n" + akce.getUcitel().getRozvrh());
        }
    }
    
    public String getNazev() {
        return nazev;
    }

    public String getZkratka() {
        return zkratka;
    }

    public String getSemestr() {
        return semestr;
    }

    public int getKredity() {
        return kredity;
    }

    public int prihlaseno() {
        return studenti.size();
    }
    
    public Akce getAkce(int k)
    {
        return seznamAkci.get(k);
    }

    public List<Akce> getSeznamAkci() {
        return seznamAkci;
    }
    
    public void setGarant(Ucitel garant) {
        this.garant = garant;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public void addStudent(Student student)
    {
        if (!studenti.contains(student))
        {
            studenti.add(student);
            student.addPredmet(this);
        }
    }

    public String info()
    {
        String s = this.info1();
        
        s += "\nPřednášející:\n";
        for (Ucitel t : prednasejici)
            s += t.info1() + "\n";
   
        s += "\nCvičící:\n";
        for (Ucitel t : cvicici)
            s += t.info1() + "\n";
        
        s += "\nStudenti:\n";
        for (int i = 0; i < this.studenti.size(); i++)
            s += this.studenti.get(i) + "\n";
        
        s += "\nRozvrhové akce:\n";
        for (Akce a : seznamAkci)
            s += a.info1() + "\n";
        s += "\n";
        
        return s;
    }
    
    public String info1()
    {
        String s = this.nazev + " (" + this.zkratka + "), " 
                 + this.semestr + ", " + this.hodinPrednasek + "+" + this.hodinCviceni + ", " + this.kredity + "kr.";
        
        return s;
    }            

    public String line(String sep)
    {
        return nazev + sep + zkratka + sep + semestr + sep + hodinPrednasek + sep + hodinCviceni + sep + kredity + sep + garant + "\n";
    }

    public String studenti(String sep)
    {
        StringBuilder b = new StringBuilder(zkratka);

        studenti.forEach((s) -> {
            b.append(sep);
            b.append(s.username);
        });
        b.append("\n");
        
        return b.toString();
    }
    
    @Override
    public String toString()
    {
        return this.zkratka;
    }
}
