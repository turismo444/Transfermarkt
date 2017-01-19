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
	 * Von diesem Konstruktor werden die Vereine erstellt, die von der Datenbank kommen.
	 * Von ihnen sind alle Daten klar.
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
	 * Von diesem Konstruktur werden neue Vereine erstellt, die später in die Datenbank gespeißt werden
	 * Da die AngebotsID nicht vom Benutzer kommt muss eine neue erstellt werden, 
	 * so wird die static variable lastNewNumber angewendet
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
	 * AngebotsID getter
	 * Die getter und setter Methoden sind dafür da, dass nicht direkt auf die Variable zugegriffen werden muss,
	 * was die Sicherheit beeinträchtigen würde.
	 * @return
	 */
	public int getAngebotsID() {
		return angebotsID;
	}

	/**
	 * AngebotsID setter
	 * Wenn die gesetzte Variable nicht die Selbe ist, wird sie gesetzt.
	 * Außerdem wird das Modifikationsflag gesetzt, wodurch in der Datenbank Upgedatet wird.
	 * @param angebotsID
	 */
	public void setAngebotsID(int angebotsID) {
		if(angebotsID != this.angebotsID){
			this.angebotsID = angebotsID;
			isMod = true;
		}
	}

	/**
	 * Gibt den Verein, von dem das Angebot kommt
	 * @return
	 */
	public int getVereinVon() {
		return vereinVon;
	}

	/**
	 * Setzt den Verein, von dem das Angebot kommt
	 * @param vereinVon
	 */
	public void setVereinVon(int vereinVon) {
		if(vereinVon != this.vereinVon){
			this.vereinVon = vereinVon;
			isMod = true;
		}
	}

	/**
	 * Gibt den Verein, an dem das Angebot geht
	 * @return
	 */
	public int getVereinAn() {
		return vereinAn;
	}

	/**
	 * Setzt den Verein, an dem das Angebot geht
	 * @param vereinAn
	 */
	public void setVereinAn(int vereinAn) {
		if(vereinAn != this.vereinAn){
			this.vereinAn = vereinAn;
			isMod = true;
		}
	}

	/**
	 * Gibt die SpielerID des betreffenden Spielers
	 * @return
	 */
	public int getSpielerID() {
		return spielerID;
	}

	/**
	 * Setzt die SpielerID des betreffenden Spielers
	 * @param spielerID
	 */
	public void setSpielerID(int spielerID) {
		if(spielerID != this.spielerID){
			this.spielerID = spielerID;
			isMod = true;
		}
	}

	/**
	 * Gibt den Betrag des Gebots
	 * @return
	 */
	public int getGebot() {
		return gebot;
	}

	/**
	 * Setzt den Betrag des Gebots
	 * @param gebot
	 */
	public void setGebot(int gebot) {
		if(gebot != this.gebot){
			this.gebot = gebot;
			isMod = true;
		}
	}

	/**
	 * Gibt, ob das Angebot angenommen wurde
	 * @return
	 */
	public boolean isIstAngenommen() {
		return istAngenommen;
	}

	/**
	 * Setzt, ob das Angebot angenommen wurde
	 * @param istAngenommen
	 */
	public void setIstAngenommen(boolean istAngenommen) {
		if(istAngenommen != this.istAngenommen){
			this.istAngenommen = istAngenommen;
			isMod = true;
		}
	}

	/**
	 * Gibt, ob das Angebot abgeschlossen wurde
	 * @return
	 */
	public boolean isIstAbgeschlossen() {
		return istAbgeschlossen;
	}

	/**
	 * Setzt, ob das Angebot abgeschlossen wurde
	 * @param istAbgeschlossen
	 */
	public void setIstAbgeschlossen(boolean istAbgeschlossen) {
		if(istAbgeschlossen != this.istAbgeschlossen){
			this.istAbgeschlossen = istAbgeschlossen;
			isMod = true;
		}
	}	
}