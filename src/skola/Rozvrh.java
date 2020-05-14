/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skola;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.ArrayList;

/**
 *
 * @author ugla
 */
public class Rozvrh
{
    static final int DELKA_LEKCE = 50;
    static ArrayList<LocalTime> casy;
    
    private Akce[][] rozpis;

    
    public Rozvrh()
    {
        casy = new ArrayList<>();
        
        casy.add(LocalTime.of(7,25));
        for (int i = 1 ; i < 15; i++)
            casy.add(casy.get(i-1).plusMinutes(DELKA_LEKCE));
        
        rozpis = new Akce[7][casy.size()];
    }

    int pozice(LocalTime cas)
    {
        return (int) (casy.get(0).until(cas,MINUTES)/DELKA_LEKCE);
    }
    
    boolean volno(int den, int zac, int kon)
    {
        for (int i = zac; i <= kon; i++)
            if (rozpis[den][i] != null)
                return false;
        
        return true;
    }        

    public boolean lzePridat(Akce akce)
    {
        return volno(akce.getDen(), pozice(akce.getCasOd()), pozice(akce.getCasOd()));
    }
    
    void addAkce(Akce akce)
    {
        int d = akce.getDen();
        int zac = pozice(akce.getCasOd());
        int kon = pozice(akce.getCasDo());
        
        if (volno(d,zac,kon))
        {
            akce.setZacatek(zac);
            akce.setKonec(kon);
            
            for (int i = zac; i <= kon; i++)
                rozpis[d][i] = akce;
        }
    }
    
    public String seznam()
    {
        StringBuilder b = new StringBuilder();
        for (int i = 0 ; i < 7; i++)
            for (int j = 0; j < 15; j++)
            {
                Akce a = rozpis[i][j];
                if (a != null && a.getZacatek() == j)
                    b.append(a.info1()).append("\n");
            }

        return b.toString();
    }

    public String zahlaviTabulky(int sirkaSloupce)
    {
        return zahlaviTabulky(sirkaSloupce,"|");
    }
    
    public String zahlaviTabulky(int sirkaSloupce, String sep)
    {
        StringBuilder b = new StringBuilder(StrUtil.paddRight("Den",' ',sirkaSloupce)).append(sep);
        for (int k = 0; k < 15; k++)
            b.append(StrUtil.paddRight(casy.get(k).toString(),' ',sirkaSloupce)).append(sep);
        
        b.append("\n");

        return b.toString();
    }

    public String oddelovaciRadek(int sirkaSloupce)
    {
        return oddelovaciRadek(sirkaSloupce,"|");
    }
    
    public String oddelovaciRadek(int sirkaSloupce, String sep)
    {
        StringBuilder b = new StringBuilder(StrUtil.paddRight("",'_',sirkaSloupce)).append(sep);
        for (int k = 0; k < 15; k++)
           b.append(StrUtil.paddRight("",'_',sirkaSloupce)).append(sep);
        
        b.append("\n");
        
        return b.toString();
    }

    public String radekTabulky(int i, int sirkaSloupce)
    {
        return radekTabulky(i,sirkaSloupce,"|");
    }

    public String radekTabulky(int i, int sirkaSloupce, String sep)
    {
        StringBuilder [] b = new StringBuilder[3];

        b[0] = new StringBuilder(StrUtil.paddRight(Dny.Jmeno(i),' ',sirkaSloupce)).append(sep);
        b[1] = new StringBuilder(StrUtil.paddRight("",' ',sirkaSloupce)).append(sep);
        b[2] = new StringBuilder(StrUtil.paddRight("",'_',sirkaSloupce)).append(sep);
        
        for (int k = 0; k < 15; k++)
        {
            Akce akce = rozpis[i][k];

            if (akce == null)
            {
                b[0].append(StrUtil.paddRight("",' ',sirkaSloupce)).append(sep);
                b[1].append(StrUtil.paddRight("",' ',sirkaSloupce)).append(sep);
                b[2].append(StrUtil.paddRight("",'_',sirkaSloupce)).append(sep);
            }
            else
                if (k == akce.getZacatek())
                {
                    int len = akce.delka();
                    b[0].append(StrUtil.paddBetween(akce.getPredmet().toString(), akce.getTyp().substring(0,2), ' ', len*sirkaSloupce + len-1)).append(sep);
                    b[1].append(StrUtil.paddBetween(akce.getUcebna().toString(), akce.getUcitel().toString(), ' ', len*sirkaSloupce + len-1)).append(sep);
                    b[2].append(StrUtil.paddRight("",'_',len*sirkaSloupce + len-1)).append(sep);
                }
        }
        
        b[0].append("\n");
        b[1].append("\n");
        b[2].append("\n");

        return b[0].toString() + b[1].toString() + b[2].toString();
    }
    
    public String tabulka()
    {
        StringBuilder b = new StringBuilder(zahlaviTabulky(8));
        
        b.append(oddelovaciRadek(8));
        for (int i = 0; i < 7; i++)
        {
            b.append(radekTabulky(i,8));
        }
        
        return b.toString();
    }
    
    @Override
    public String toString()
    {
        return seznam();
    }
}
