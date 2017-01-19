/**
 * 
 * @author Michael Krapf / Orcun Döger
 * 
 */

package model;
import java.sql.Date;

public class Spieler extends ObjectBase
{
	static int lastNewNumber = 0;
	
	private int spielerID;
	private int vereinsID;
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private int leistungswert;
	
	/**
	 * 
	 * @param spielerID
	 * @param vereinsID
	 * @param vorname
	 * @param nachname
	 * @param geburtsdatum
	 * @param leistungswert
	 */
	Spieler(int spielerID, int vereinsID, String vorname, String nachname, Date geburtsdatum, int leistungswert) 
	{
		super();
		this.spielerID = spielerID;
		this.vereinsID = vereinsID;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.leistungswert = leistungswert;
	}
	
	/**
	 * 
	 */
	Spieler() 
	{
		super();
		this.spielerID = --lastNewNumber;
		this.vereinsID = 0;
		this.vorname = "";
		this.nachname = "";
		this.geburtsdatum = null;
		this.leistungswert = 0;
		this.isNew = true;
	}

	/**
	 * 
	 * @return
	 */
	public int getSpielerID() {
		return spielerID;
	}

	/**
	 * 
	 * @param spielerID
	 */
	public void setSpielerID(int spielerID) {
		if(spielerID != this.spielerID){
			this.spielerID = spielerID;
			isMod = true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getVereinsID() {
		return vereinsID;
	}
	
	/**
	 * 
	 * @param vereinsID
	 */
	public void setVereinsID(int vereinsID) {
		if (this.vereinsID != vereinsID) {
			this.vereinsID = vereinsID;
			isMod = true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * 
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		if (this.vorname != vorname) {
			this.vorname = vorname;
			isMod = true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * 
	 * @param nachname
	 */
	public void setNachname(String nachname) {
		if (this.nachname != nachname) {
			this.nachname = nachname;
			isMod = true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	/**
	 * 
	 * @param geburtsdatum
	 */
	public void setGeburtsdatum(Date geburtsdatum) {
		if (this.geburtsdatum != geburtsdatum) {
			this.geburtsdatum = geburtsdatum;
			isMod = true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getLeistungswert() {
		return leistungswert;
	}

	/**
	 * 
	 * @param leistungswert
	 */
	public void setLeistungswert(int leistungswert) {
		if (this.leistungswert != leistungswert) {
			this.leistungswert = leistungswert;
			isMod = true;
		}
	}
}