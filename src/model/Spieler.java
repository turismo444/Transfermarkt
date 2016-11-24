package model;
import java.util.Date;

public class Spieler extends ObjectBase
{
	static int lastNewNumber = 0;
	
	private int spielerID;
	private int vereinsID;
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private int leistungswert;
	
	
	public Spieler(int spielerID, int vereinsID, String vorname, String nachname, Date geburtsdatum,
			int leistungswert) {
		super();
		this.spielerID = spielerID;
		this.vereinsID = vereinsID;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.leistungswert = leistungswert;
	}
	
	public Spieler() {
		super();
		this.spielerID = lastNewNumber--;
		this.vereinsID = 0;
		this.vorname = "";
		this.nachname = "";
		this.geburtsdatum = null;
		this.leistungswert = 0;
		this.isNew = true;
	}

	public int getSpielerID() {
		return spielerID;
	}

	public void setSpielerID(int spielerID) {
		if(spielerID != this.spielerID){
			this.spielerID = spielerID;
			isMod = true;
		}
	}

	public int getVereinsID() {
		return vereinsID;
	}

	public void setVereinsID(int vereinsID) {
		if (this.vereinsID != vereinsID) {
			this.vereinsID = vereinsID;
			isMod = true;
		}
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		if (this.vorname != vorname) {
			this.vorname = vorname;
			isMod = true;
		}
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		if (this.nachname != nachname) {
			this.nachname = nachname;
			isMod = true;
		}
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		if (this.geburtsdatum != geburtsdatum) {
			this.geburtsdatum = geburtsdatum;
			isMod = true;
		}
	}

	public int getLeistungswert() {
		return leistungswert;
	}

	public void setLeistungswert(int leistungswert) {
		if (this.leistungswert != leistungswert) {
			this.leistungswert = leistungswert;
			isMod = true;
		}
	}
	
}