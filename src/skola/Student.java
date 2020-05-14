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
public class Student extends Osoba
{
    private String obor;
    private int rocnik;
    
    private List<Predmet> predmety;
    private Rozvrh rozvrh;
    
    
    public Student(String username, String jmeno, String prijmeni, String obor)
    {
        super(username,jmeno,prijmeni);
        
        this.obor = obor;
        this.rocnik = 1;
        this.predmety = new ArrayList<>();
        this.rozvrh = new Rozvrh();
    }

    public String getObor() {
        return obor;
    }

    public int getRocnik() {
        return rocnik;
    }

    public Rozvrh getRozvrh() {
        return rozvrh;
    }

    public boolean maZapsan(Predmet predmet)
    {
        return predmety.contains(predmet);
    }
            
    public void addPredmet(Predmet predmet)
    {
        if (!maZapsan(predmet))
        {
            predmety.add(predmet);
            predmet.addStudent(this);
        }
    }

    public void addAkce(Akce akce)
    {
        if (maZapsan(akce.getPredmet()) && akce.volnychMist() > 0 && rozvrh.lzePridat(akce))
        {
            rozvrh.addAkce(akce);
            akce.getStudenti().add(this);
        }
    }

    public String info()
    {
        String s = this.toString();
        s += "\nZapsané předměty:\n";
        
        for (Predmet p : predmety)
            s += p.info1() + "\n";
        
        s += "\nRozvrh:\n" + rozvrh.tabulka();

        return s;
    }
    
    public String info1()
    {
        String s = this.toString() + " " + predmety.toString();
 
        return s;
    }

    public String line(String sep)
    {
        return username + sep + jmeno + sep + prijmeni + sep + obor + sep + rocnik + "\n";
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " (" + this.obor + ", r." + this.rocnik + ")";
    }
}
