package model;

public class Angebot extends ObjectBase
{
	static int lastNewNumber = 0;
	
	private int angebotsID;
	private int vereinVon;
	private int vereinAn;
	private int spielerID;
	private double gebot;
	private boolean istAngenommen;
	private boolean istAbgeschlossen;
	
	
	Angebot(int angebotsID, int vereinVon, int vereinAn, int spielerID, double gebot, boolean istAngenommen,
			boolean istAbgeschlossen) {
		super();
		this.angebotsID = angebotsID;
		this.vereinVon = vereinVon;
		this.vereinAn = vereinAn;
		this.spielerID = spielerID;
		this.gebot = gebot;
		this.istAngenommen = istAngenommen;
		this.istAbgeschlossen = istAbgeschlossen;
	}
	
	Angebot(int angebotsID, int vereinVon, int vereinAn, int spielerID, double gebot) {
		super();
		this.angebotsID = --lastNewNumber;
		this.vereinVon = vereinVon;
		this.vereinAn = vereinAn;
		this.spielerID = spielerID;
		this.gebot = gebot;
		this.istAngenommen = false;
		this.istAbgeschlossen = false;
		isNew = true;
	}

	public Angebot() {
		// TODO Auto-generated constructor stub
	}

	public int getAngebotsID() {
		return angebotsID;
	}

	public void setAngebotsID(int angebotsID) {
		if(angebotsID != this.angebotsID){
			this.angebotsID = angebotsID;
			isMod = true;
		}
	}

	public int getVereinVon() {
		return vereinVon;
	}

	public void setVereinVon(int vereinVon) {
		if(vereinVon != this.vereinVon){
			this.vereinVon = vereinVon;
			isMod = true;
		}
	}

	public int getVereinAn() {
		return vereinAn;
	}

	public void setVereinAn(int vereinAn) {
		if(vereinAn != this.vereinAn){
			this.vereinAn = vereinAn;
			isMod = true;
		}
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

	public double getGebot() {
		return gebot;
	}

	public void setGebot(double gebot) {
		if(gebot != this.gebot){
			this.gebot = gebot;
			isMod = true;
		}
	}

	public boolean isIstAngenommen() {
		return istAngenommen;
	}

	public void setIstAngenommen(boolean istAngenommen) {
		if(istAngenommen != this.istAngenommen){
			this.istAngenommen = istAngenommen;
			isMod = true;
		}
	}

	public boolean isIstAbgeschlossen() {
		return istAbgeschlossen;
	}

	public void setIstAbgeschlossen(boolean istAbgeschlossen) {
		if(istAbgeschlossen != this.istAbgeschlossen){
			this.istAbgeschlossen = istAbgeschlossen;
			isMod = true;
		}
	}
	
}