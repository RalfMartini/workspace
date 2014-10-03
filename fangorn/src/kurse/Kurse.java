/**
 * 
 */
package kurse;

import java.util.Calendar;

/**
 * @author Ralf
 *
 */
public class Kurs {

	private String name;
	private Calendar datum = Calendar.getInstance();
	private float eroeffnung;
	private float max;
	private float tief;
	private float schluss;
	private double volumen;
	private String org;
	
	private String szName;
	private String szDatum;
	private String szEroeffnung;
	private String szMax;
	private String szTief;
	private String szSchluss;
	private String szVolumen;
	private String szOrg;
	
	public Kurs(String line, String org, String name){
		szDatum 		= line.split(";")[0];
		szEroeffnung 	= line.split(";")[1];
		szMax			= line.split(";")[2];
		szTief			= line.split(";")[3];
		szSchluss		= line.split(";")[4];
		szVolumen		= line.split(";")[5];
		szOrg			= org;
		szName			= name;
		this.org   		= org;
		this.name		= name;
		
		System.out.println(szDatum);
		System.out.println(szEroeffnung);
		System.out.println(szMax);
		System.out.println(szTief);
		System.out.println(szSchluss);
		System.out.println(szVolumen);
		System.out.println(szOrg);
		System.out.println(szName);
		
		datum.set(Integer.valueOf(szDatum.split("\\.")[2]), Integer.valueOf(szDatum.split("\\.")[1]), Integer.valueOf(szDatum.split("\\.")[0]));
		eroeffnung = Float.valueOf(szEroeffnung.replace(",", "."));
		max = Float.valueOf(szMax.replace(",", "."));
		tief = Float.valueOf(szTief.replace(",", "."));
		schluss = Float.valueOf(szSchluss.replace(",", "."));
		volumen = Double.valueOf(szVolumen.split(",")[0].replace(".", ""));
	}
}
