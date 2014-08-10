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
 * @author Ralf
 * 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
class TestComdirect extends Thread {

    private String Symbol;

    TestComdirect(String sym) {
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

        	l = 0;

        	try {
            
        		URLVerbindung = new URL("http://www.comdirect.de/inf/kursdaten/historic.csv?DATETIME_TZ_START_RANGE_FORMATED=01.01.1900&ID_NOTATION=180039&OFFSET=" + y + "&INTERVALL=16&SHOW_CORPORATE_ACTION=0&DATETIME_TZ_END_RANGE_FORMATED=28.05.2013&WITH_EARNINGS=false");
                BufferedReader instream = new BufferedReader(new InputStreamReader(URLVerbindung.openStream()));
                
                do {
                                    	
                	line = instream.readLine();
            
                	//System.out.println(line);
                	
                    if (line != null && line.matches("([0-9.,].*);([0-9.,].*)")) {
                        
                    	l = l + 1;
                        lines = lines + 1;
                        
                        System.out.println(Symbol + ": " + line);
                        TestKurs = new Kurs(line, "Comdirect", Symbol);
                    
                    };
                
                } while (line != null);
            
            } catch (IOException ioex) {
                System.out.println("Fehler! " + ioex.toString());
            }

            y += 1;
            
        } while (l > 0);

        System.out.println("\nRead " + lines + " lines of data for " + Symbol + " .");

    }
}

class ThreadBasicsComdirect {

    public static void main(String[] args) {

        /* Erzeugung zweier BeispielThreads */

        TestComdirect t1 = new TestComdirect("cbk.de");
        TestComdirect t2 = new TestComdirect("alv.de");

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
