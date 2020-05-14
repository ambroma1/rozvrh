/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skola;

/**
 *
 * @author ugla
 */
public class Ucebna
{
    private String nazev;
    private int kapacita;
    private boolean sPocitaci;
    
    private Rozvrh rozvrh;
    

    public Ucebna(String nazev, int kapacita, boolean sPocitaci)
    {
        this.nazev = nazev;
        this.kapacita = kapacita;
        this.sPocitaci = sPocitaci;
        
        this.rozvrh = new Rozvrh();
    }

    public String getNazev() {
        return nazev;
    }

    public int getKapacita() {
        return kapacita;
    }

    public boolean sPocitaci()
    {
        return sPocitaci;
    }
    
    public Rozvrh getRozvrh() {
        return rozvrh;
    }
    
    public void addAkce(Akce akce)
    {
        rozvrh.addAkce(akce);
    }

    public boolean lzePridat(Akce akce)
    {
        return rozvrh.lzePridat(akce);
    }
    
    public String info()
    {
        return this.info1() + ":\n" + rozvrh.tabulka();
    }

    public String info1()
    {
        String s = this.toString() + " (kapacita: " + kapacita;
        if (sPocitaci)
            s += ", počítačová)";
        else
            s += ")";
        
        return s;
    }

    public String line(String sep)
    {
        return nazev + sep + kapacita + sep + sPocitaci + "\n";
    }

    @Override
    public String toString()
    {
        return this.nazev;
    }
}
