/**
 * 
 * @author Michael Krapf / Orcun Döger
 * 
 */

package model;

public class Angebot extends ObjectBase
{
	static int lastNewNumber = 0;
	
	private int angebotsID;
	private int vereinVon;
	private int vereinAn;
	private int spielerID;
	private int gebot;
	private boolean istAngenommen;
	private boolean istAbgeschlossen;
	
	/**
	 * 
	 * @param angebotsID
	 * @param vereinVon
	 * @param vereinAn
	 * @param spielerID
	 * @param gebot
	 * @param istAngenommen
	 * @param istAbgeschlossen
	 */
	Angebot(int angebotsID, int vereinVon, int vereinAn, int spielerID, int gebot, boolean istAngenommen, boolean istAbgeschlossen) 
	{
		super();
		this.angebotsID = angebotsID;
		this.vereinVon = vereinVon;
		this.vereinAn = vereinAn;
		this.spielerID = spielerID;
		this.gebot = gebot;
		this.istAngenommen = istAngenommen;
		this.istAbgeschlossen = istAbgeschlossen;
	}
	
	/**
	 * 
	 */
	Angebot() 
	{
		super();
		this.angebotsID = --lastNewNumber;
		this.vereinVon = 0;
		this.vereinAn = 0;
		this.spielerID = 0;
		this.gebot = 0;
		this.istAngenommen = false;
		this.istAbgeschlossen = false;
		isNew = true;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getAngebotsID() {
		return angebotsID;
	}

	/**
	 * 
	 * @param angebotsID
	 */
	public void setAngebotsID(int angebotsID) {
		if(angebotsID != this.angebotsID){
			this.angebotsID = angebotsID;
			isMod = true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getVereinVon() {
		return vereinVon;
	}

	/**
	 * 
	 * @param vereinVon
	 */
	public void setVereinVon(int vereinVon) {
		if(vereinVon != this.vereinVon){
			this.vereinVon = vereinVon;
			isMod = true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getVereinAn() {
		return vereinAn;
	}

	/**
	 * 
	 * @param vereinAn
	 */
	public void setVereinAn(int vereinAn) {
		if(vereinAn != this.vereinAn){
			this.vereinAn = vereinAn;
			isMod = true;
		}
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
	public int getGebot() {
		return gebot;
	}

	/**
	 * 
	 * @param gebot
	 */
	public void setGebot(int gebot) {
		if(gebot != this.gebot){
			this.gebot = gebot;
			isMod = true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isIstAngenommen() {
		return istAngenommen;
	}

	/**
	 * 
	 * @param istAngenommen
	 */
	public void setIstAngenommen(boolean istAngenommen) {
		if(istAngenommen != this.istAngenommen){
			this.istAngenommen = istAngenommen;
			isMod = true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isIstAbgeschlossen() {
		return istAbgeschlossen;
	}

	/**
	 * 
	 * @param istAbgeschlossen
	 */
	public void setIstAbgeschlossen(boolean istAbgeschlossen) {
		if(istAbgeschlossen != this.istAbgeschlossen){
			this.istAbgeschlossen = istAbgeschlossen;
			isMod = true;
		}
	}	
}