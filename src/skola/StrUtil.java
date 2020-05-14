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
public class StrUtil
{
    public static String paddBetween(String levy, String pravy, char c, int len)
    {
        StringBuilder b = new StringBuilder(levy);
        
        for (int k = levy.length() + pravy.length(); k < len; k++)
            b.append(c);
        
        b.append(pravy);
        
        return b.toString();
    }

    public static String paddLeft(String s, char c, int len)
    {
        StringBuilder b = new StringBuilder();
        
        for (int k = s.length(); k < len; k++)
            b.append(c);
            
        b.append(s);
        
        return b.toString();
    }

    public static String paddRight(String s, char c, int len)
    {
        StringBuilder b = new StringBuilder(s);
        
        for (int k = s.length(); k < len; k++)
            b.append(c);
            
        return b.toString();
    }
}
