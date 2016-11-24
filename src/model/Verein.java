package model;

public class Verein extends ObjectBase 
{
	static int lastNewNumber = 0; //für neu erzeugte VereinsIds
	
	
	private int vereinsID;
	private String vereinsname;
	private String vereinsort;
	
	/** Konstruktor zum Lesen aus der DB
	 * @param vereinsId
	 */
	Verein(int vereinsId, String vereinsname, String vereinsort) {
		super();
		this.vereinsID = vereinsId;
		this.vereinsname = vereinsname;
		this.vereinsort = vereinsort;
	}
	
    Verein() {
		super();
		this.vereinsID = --lastNewNumber;
		this.vereinsname = "";
		this.vereinsort = "";
		this.isNew = true;
	}


	public int getVereinsID() {
		return vereinsID;
	}

	public void setVereinsID(int vereinsId) {
		if (this.vereinsID != vereinsId) {
			this.vereinsID = vereinsId;
			isMod = true;
		}
	}

	public String getVereinsname() {
		return vereinsname;
	}

	public void setVereinsname(String vereinsname) {
		if (this.vereinsname != vereinsname) {
			this.vereinsname = vereinsname;
			isMod = true;
		}
	}

	public String getVereinsort() {
		return vereinsort;
	}

	public void setVereinsort(String vereinsort) {
		if (this.vereinsort != vereinsort) {
			this.vereinsort = vereinsort;
			isMod = true;
		}
	}
	
}
