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
public class Osoba
{
    protected String jmeno;
    protected String prijmeni;
    protected String username;

    
    public Osoba(String username, String jmeno, String prijmeni)
    {
        this.username = username;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString()
    {
        return this.jmeno + " " + this.prijmeni;
    }    
}
