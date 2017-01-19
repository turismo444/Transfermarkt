/**
 * 
 * @author Michael Krapf / Orcun Döger
 * 
 */

package model;

public class Verein extends ObjectBase 
{
	/**
	 * lastNewNumber -> für neu erzeugte VereinsIDs
	 */
	static int lastNewNumber = 0;
	
	private int vereinsID;
	private String vereinsname;
	private String vereinsort;
	
	/** 
	 * Default-Konstruktor zum Lesen aus der DB
	 * @param vereinsId
	 * @param vereinsname
	 * @param vereinsort 
	 */
	Verein(int vereinsId, String vereinsname, String vereinsort) 
	{
		super();
		this.vereinsID = vereinsId;
		this.vereinsname = vereinsname;
		this.vereinsort = vereinsort;
	}
	
	/**
	 * Default-Konstruktor ohne Parameter
	 */
    Verein() 
    {
		super();
		this.vereinsID = --lastNewNumber;
		this.vereinsname = "";
		this.vereinsort = "";
		this.isNew = true;
	}

    /**
     * Gibt die VereinsID zurück
     * @return
     */
	public int getVereinsID() {
		return vereinsID;
	}

	/**
	 * Setzt die VereinsID
	 * @param vereinsId
	 */
	public void setVereinsID(int vereinsId) {
		if (this.vereinsID != vereinsId) {
			this.vereinsID = vereinsId;
			isMod = true;
		}
	}

	/**
	 * Gibt den Vereinsnamen zurück
	 * @return
	 */
	public String getVereinsname() {
		return vereinsname;
	}

	/**
	 * Setzt den Vereinsnamen
	 * @param vereinsname
	 */
	public void setVereinsname(String vereinsname) {
		if (this.vereinsname != vereinsname) {
			this.vereinsname = vereinsname;
			isMod = true;
		}
	}

	/**
	 * Gibt den Vereinsort zurück
	 * @return
	 */
	public String getVereinsort() {
		return vereinsort;
	}

	/**
	 * Setzt den Vereinsort
	 * @param vereinsort
	 */
	public void setVereinsort(String vereinsort) {
		if (this.vereinsort != vereinsort) {
			this.vereinsort = vereinsort;
			isMod = true;
		}
	}
}