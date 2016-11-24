package model;

import java.sql.Connection;

/**
 * @author ilker
 *
 */
public class Verein extends DBObjectBase {
	static int lastNewNumber = 0; //für neu erzeugte VereinsIds
	

	private int vereinsId;
	String vereinsname;
	
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
