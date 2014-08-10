/*
 * Created on 24.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.net.*;
import java.io.*;
import kurse.Kurs;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
class HttpTest extends Thread {

    private String Symbol;

    HttpTest(String sym) {
        Symbol = sym;
    }

    public void run() {

    	Kurs TestKurs;
        URL URLVerbindung;
        String line;
        int y = 0;
        int l = 0;
        int lines = 0;
        do {
            try {
                //URLVerbindung = new URL("http://de.table.finance.yahoo.com/table.csv?a=7&b=26&c=1900&d=10&e=26&f=2004&s=cbk.de&y="+y+"&g=d&ignore=.csv");	
                URLVerbindung = new URL("http://ichart.yahoo.com/table.csv?a=7&b=26&c=1900&d=1&e=26&f=2013&s=" + Symbol + "&y=" + y + "&g=d&ignore=.csv");
                BufferedReader instream = new BufferedReader(new InputStreamReader(URLVerbindung.openStream()));
                l = 0;
                do {
                    line = instream.readLine();
                    if (line != null) {
                        l = l + 1;
                        lines = lines + 1;
                        if (line.startsWith("Date") || line.startsWith("<")) {
                            l = l - 1;
                        } else {
                            System.out.println(Symbol + ": " + line);
                            TestKurs = new Kurs(line, "Yahoo", Symbol);
                        }
                        ;

                    }
                    ;
                } while (line != null);
                lines -= 2;
            } catch (IOException ioex) {
                System.out.println("Fehler! " + ioex.toString());
            }

            y += l;
        } while (l > 0);

        System.out.println("\nRead " + lines + " lines of data for " + Symbol + " .");

    }
}

class ThreadBasics {

    public static void main(String[] args) {

        /* Erzeugung zweier BeispielThreads */

        HttpTest t1 = new HttpTest("cbk.de");
        HttpTest t2 = new HttpTest("alv.de");

        System.out.println();

        /* Start der Threads, 
        die dann jeweils ihre run()-Methode ausfuehren */

        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
        }


        System.out.println("Fertig!");

    }
}
