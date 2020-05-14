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
 * @author ugla
 */
public class Dny
{
    static ArrayList<String> dny = new ArrayList<>(List.of("Pondělí","Úterý","Středa","Čtvrtek","Pátek","Sobota","Neděle"));
    static ArrayList<String> zkratky = new ArrayList<>(List.of("po","út","st","čt","pá","so","ne")); 
    
    static String Jmeno(int k)
    {
        return dny.get(k);
    }

    static String jmeno(int k)
    {
        return dny.get(k).toLowerCase();
    }

    static String Zkratka(int k)
    {
        return dny.get(k).substring(0,2);
    }
    
    static String zkratka(int k)
    {
        return dny.get(k).substring(0,2).toLowerCase();
    }

    static int index(String den)
    {
        return zkratky.indexOf(den);
    }
}
