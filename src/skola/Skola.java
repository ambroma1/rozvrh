/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;

/**
 *
 * @author bauerpe1
 */
public class Skola
{
    static HashMap<String,Ucitel> ucitele = new HashMap<>();
    static HashMap<String,Student> studenti = new HashMap<>();
    static HashMap<String,Predmet> predmety = new HashMap<>();
    static HashMap<String,Ucebna> ucebny = new HashMap<>();
    
    public double ahoj()
    {
    return 2;}
    
    public static void clear()
    {
        ucitele = new HashMap<>();
        studenti = new HashMap<>();
        predmety = new HashMap<>();
        ucebny = new HashMap<>();
    }

    static void addStudent(String line, String sep)
    {
        String [] tokens = line.split(sep);
        
        studenti.put(tokens[0], new Student(tokens[0], tokens[1],tokens[2],tokens[3]));
    }
     
    static void addUcitel(String line, String sep)
    {
        String [] tokens = line.split(sep);

        ucitele.put(tokens[3], new Ucitel(tokens[0], tokens[1],tokens[2],tokens[4], tokens[3]));
    }

    static void addPredmet(String line, String sep)
    {
        String [] tokens = line.split(sep);

        predmety.put(tokens[1], new Predmet(tokens[0],tokens[1],tokens[2],Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]),Integer.parseInt(tokens[5]),ucitele.get(tokens[6])));
    }
     
    static void addUcebna(String line, String sep)
    {
        String [] tokens = line.split(sep);

        ucebny.put(tokens[0], new Ucebna(tokens[0],Integer.parseInt(tokens[1]),Boolean.parseBoolean(tokens[2])));
    }

    static void addAkce(String line, String sep)
    {
        String [] tokens = line.split(sep);
        
        Skola.addAkce(new Akce(predmety.get(tokens[0]),tokens[1], ucebny.get(tokens[2]), tokens[3], LocalTime.parse(tokens[4]), LocalTime.parse(tokens[5]), ucitele.get(tokens[6]), Integer.parseInt(tokens[7])));
        
    }
    
    static void addStudent(Student student)
    {
        studenti.put(student.getUsername(),student);
    }

    static void addUcitel(Ucitel ucitel)
    {
        ucitele.put(ucitel.getZkratka(),ucitel);
    }

    static void addPredmet(Predmet predmet)
    {
        predmety.put(predmet.getZkratka(),predmet);
    }

    static void addUcebna(Ucebna ucebna)
    {
        ucebny.put(ucebna.getNazev(),ucebna);
    }

    static void addAkce(Akce akce)
    {
        akce.getPredmet().addAkce(akce);
    }

    static void zapisStudentyNaPredmet(String line, String sep)
    {
        String [] tokens = line.split(sep);
        
        Predmet p = predmety.get(tokens[0]);
        for (int i = 1; i < tokens.length; i++)
            p.addStudent(studenti.get(tokens[i]));
    }

    static void zapisStudentyNaAkci(String line, String sep)
    {
        String [] tokens = line.split(sep);
        
        Predmet p = predmety.get(tokens[0]);
        Akce a = p.getAkce(Integer.parseInt(tokens[1]));
        
        for (int i = 2; i < tokens.length; i++)
            a.addStudent(studenti.get(tokens[i]));
    }
    
    public static void fill()
    {
        Skola.addUcitel(new Ucitel("malyfi1","Filip", "Malý", "KIKM", "MAF"));
        Skola.addUcitel(new Ucitel("bauerpe1","Petr", "Bauer", "KIKM", "BAU"));
        Skola.addUcitel(new Ucitel("havigji1","Jiří", "Haviger", "KIKM", "HAG"));
        Skola.addUcitel(new Ucitel("froncka1","Kateřina", "Frončková", "KIKM", "XFK"));
        Skola.addUcitel(new Ucitel("prazapa1","Pavel", "Pražák", "KIKM", "PRA"));

        Skola.addStudent(new Student("brandlu2","Lukáš", "Brandejs", "IM"));
        Skola.addStudent(new Student("cernoan1","Anežka", "Černohousová", "IM"));
        Skola.addStudent(new Student("dadakvo1","Vojtěch","Dadák", "IM" ));
        Skola.addStudent(new Student("figerja1","Jakub", "Figer", "IM"));
        Skola.addStudent(new Student("machaka6","Karolína", "Macháčková", "IM"));
        Skola.addStudent(new Student("sedami1","Michael", "Šeda", "IM"));
        
        Skola.addPredmet(new Predmet("Programování", "PROM", "LS", 2, 2, 5, ucitele.get("MAF")));
        Skola.addPredmet(new Predmet("Základy matematiky 2", "ZMAT2", "LS", 2, 2, 7, ucitele.get("PRA")));

        Skola.addUcebna(new Ucebna("J1",200, false));
        Skola.addUcebna(new Ucebna("J15",25, false));
        Skola.addUcebna(new Ucebna("J23",20, true));
        Skola.addUcebna(new Ucebna("A5",108, false));
        Skola.addUcebna(new Ucebna("J10",20, true));
        Skola.addUcebna(new Ucebna("J22",20, true));
        Skola.addUcebna(new Ucebna("A3",63, false));
        Skola.addUcebna(new Ucebna("A4",63, false));
        Skola.addUcebna(new Ucebna("J13",60, false));
        Skola.addUcebna(new Ucebna("J2",25, false));
    
       
        Skola.addAkce(new Akce(predmety.get("PROM"),"cvičení", ucebny.get("J23"), "po", LocalTime.of(12,25),LocalTime.of(14,00), ucitele.get("BAU")));
        Skola.addAkce(new Akce(predmety.get("PROM"),"cvičení", ucebny.get("J23"), "po", LocalTime.of(14,05),LocalTime.of(15,40), ucitele.get("BAU")));
        Skola.addAkce(new Akce(predmety.get("PROM"),"cvičení", ucebny.get("J23"), "út", LocalTime.of(10,45),LocalTime.of(12,20), ucitele.get("XFK")));
        Skola.addAkce(new Akce(predmety.get("PROM"),"přednáška", ucebny.get("A5"), "út", LocalTime.of(14,05),LocalTime.of(15,40), ucitele.get("MAF")));
        Skola.addAkce(new Akce(predmety.get("PROM"),"cvičení", ucebny.get("J10"), "út", LocalTime.of(15,45),LocalTime.of(17,20), ucitele.get("MAF")));
        Skola.addAkce(new Akce(predmety.get("PROM"),"cvičení", ucebny.get("J22"), "st", LocalTime.of(14,55),LocalTime.of(16,30), ucitele.get("MAF")));
            
        Skola.addAkce(new Akce(predmety.get("ZMAT2"),"přednáška", ucebny.get("J1"), "po", LocalTime.of(10,45),LocalTime.of(12,20), ucitele.get("HAG")));
        Skola.addAkce(new Akce(predmety.get("ZMAT2"),"cvičení", ucebny.get("J15"), "po", LocalTime.of(12,25),LocalTime.of(14,00), ucitele.get("HAG")));
        Skola.addAkce(new Akce(predmety.get("ZMAT2"),"cvičení", ucebny.get("J15"), "po", LocalTime.of(15,45),LocalTime.of(17,20), ucitele.get("BAU")));
        Skola.addAkce(new Akce(predmety.get("ZMAT2"),"cvičení", ucebny.get("A4"), "út", LocalTime.of(8,15),LocalTime.of(9,50), ucitele.get("HAG")));
        Skola.addAkce(new Akce(predmety.get("ZMAT2"),"cvičení", ucebny.get("A3"), "út", LocalTime.of(10,45),LocalTime.of(12,20), ucitele.get("HAG")));
        Skola.addAkce(new Akce(predmety.get("ZMAT2"),"cvičení", ucebny.get("J13"), "čt", LocalTime.of(10,45),LocalTime.of(12,20), ucitele.get("BAU")));
        Skola.addAkce(new Akce(predmety.get("ZMAT2"),"cvičení", ucebny.get("J2"), "čt", LocalTime.of(12,25),LocalTime.of(14,00), ucitele.get("BAU")));
        Skola.addAkce(new Akce(predmety.get("ZMAT2"),"cvičení", ucebny.get("J2"), "čt", LocalTime.of(14,05),LocalTime.of(15,40), ucitele.get("BAU")));
       
        studenti.values()
            .forEach(student -> {
                predmety.get("PROM").addStudent(student);
                predmety.get("ZMAT2").addStudent(student);
            });
        
        studenti.values()
            .forEach(student -> {
                predmety.get("PROM").getAkce(3).addStudent(student);
                predmety.get("ZMAT2").getAkce(0).addStudent(student);

                if (student.prijmeni.matches("Brandejs|Černohousová|Figer")) {
                    student.addAkce(predmety.get("PROM").getAkce(1));
                } else {
                    student.addAkce(predmety.get("PROM").getAkce(0));
                }
            });
    }

    static void uloz(String outfile)
    {
        uloz(outfile,";");
    }
    
    static void uloz(String outfile, String sep)
    {
        try
        {
           // Assume default encoding.
            FileWriter fileWriter = new FileWriter(outfile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Studenti:\n");
            for (Student s : studenti.values())
                bufferedWriter.write(s.line(sep));
            
            bufferedWriter.write("\nUčitelé:\n");
            for (Ucitel u : ucitele.values())
                bufferedWriter.write(u.line(sep));
            
            bufferedWriter.write("\nPředměty:\n");
            for (Predmet p : predmety.values())
                bufferedWriter.write(p.line(sep));

            bufferedWriter.write("\nUčebny:\n");
            for (Ucebna u : ucebny.values())
                bufferedWriter.write(u.line(sep));
            
            bufferedWriter.write("\nAkce:\n");
            for (Predmet p : predmety.values())
                for (Akce a : p.getSeznamAkci())
                    bufferedWriter.write(a.line(sep));

            bufferedWriter.write("\nStudenti na předmětech:\n");
                for (Predmet p : predmety.values())
                    if (p.prihlaseno() > 0)
                        bufferedWriter.write(p.studenti(sep));

            bufferedWriter.write("\nStudenti na akcich:\n");
            for (Predmet p : predmety.values())
                for (Akce a : p.getSeznamAkci())
                    if (a.prihlaseno() > 0)
                        bufferedWriter.write(a.studenti(sep));

            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println("Error writing to file '" + outfile + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

    }

    static void nacti(String infile)
    {
        nacti(infile,";");
    }
    
    static void nacti(String infile, String sep)
    {
        try
        {
           // Assume default encoding.
            FileReader fileReader = new FileReader(infile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            bufferedReader.readLine();
            
            while (!(line = bufferedReader.readLine()).isBlank())
                addStudent(line,sep);
            bufferedReader.readLine();
            while (!(line = bufferedReader.readLine()).isBlank())
                addUcitel(line,sep);
            bufferedReader.readLine();
            while (!(line = bufferedReader.readLine()).isBlank())
                addPredmet(line,sep);
            bufferedReader.readLine();
            while (!(line = bufferedReader.readLine()).isBlank())
                addUcebna(line,sep);
            bufferedReader.readLine();
            while (!(line = bufferedReader.readLine()).isBlank())
                addAkce(line,sep);
            bufferedReader.readLine();
            while (!(line = bufferedReader.readLine()).isBlank())
                 zapisStudentyNaPredmet(line,sep);
           
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null)
                zapisStudentyNaAkci(line,sep);
            
            bufferedReader.close();
        }
        catch(IOException ex)
        {
            System.out.println("Error reading from file '" + infile + "'");
        }
    }

    static void vypis()
    {
        System.out.println("----------------------------------------");
        predmety.values().forEach((p) -> {
            System.out.print(p.info());
            System.out.println("----------------------------------------");
        });

        studenti.values().forEach((s) -> {
            System.out.println(s.info());
        });
        System.out.println();
        
        ucitele.values().forEach((t) -> {
            System.out.println(t.info());
        });
        System.out.println();
        
        ucebny.values().forEach((u) -> {
            System.out.println(u.info());
        });

        System.out.println("----------------------------------------");
        predmety.values().forEach((p) -> {
            p.getSeznamAkci().forEach((a) -> {
                if (a.prihlaseno() > 0)
                {
                    System.out.println(a.info());
                    System.out.println("----------------------------------------");
                }
            });
        });

        System.out.println();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        fill();
        uloz("skola.txt");
        clear();
        nacti("skola.txt");
        vypis();
    }
    
}
