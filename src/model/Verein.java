package model;

public class Verein extends ObjectBase 
{
	static int lastNewNumber = 0; //f�r neu erzeugte VereinsIds
	private int vereinsId;
	String vereinsname;
	private String vereinsort;
	
	/** Konstruktor zum Lesen aus der DB
	 * @param vereinsId
	 */
	 Verein(int vereinsId, String vereinsname) {
		super();
		this.vereinsId = vereinsId;
		this.vereinsname = vereinsname;
	}
	
	
	/** Konstruktor zum Lesen aus der DB
	 * @param vereinsId
	 */
     Verein() {
		super();
		this.vereinsId = --lastNewNumber;
		this.vereinsname= "";
		this.isNew = true;
	}


	public int getVereinsId() {
		return vereinsId;
	}


	public void setVereinsId(int vereinsId) {
		if (this.vereinsId != vereinsId) {
			this.vereinsId = vereinsId;
			isMod = true;
		}
	}


	
}
