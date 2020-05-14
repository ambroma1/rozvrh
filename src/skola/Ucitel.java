/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skola;

/**
 *
 * @author bauerpe1
 */
public class Ucitel extends Osoba
{
    private final String katedra;
    private final String zkratka;

    private Rozvrh rozvrh;

    
    public String getKatedra() {
        return katedra;
    }

    public String getZkratka() {
        return zkratka;
    }

    public Rozvrh getRozvrh() {
        return rozvrh;
    }

    public Ucitel(String username, String jmeno, String prijmeni, String katedra, String zkratka)
    {
         super(username,jmeno,prijmeni);
         
         this.katedra = katedra;
         this.zkratka = zkratka;
         
         this.rozvrh = new Rozvrh();
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
        return super.toString() + " (" + this.katedra + ")"; 
    }

    public String line(String sep)
    {
        return username + sep + jmeno + sep + prijmeni + sep + zkratka + sep + katedra + "\n";
    }
    
    @Override
    public String toString()
    {
        return zkratka;
    }
}
