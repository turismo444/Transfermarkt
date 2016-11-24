package model;

import java.sql.Connection;

public abstract class DBObjectBase {
	protected boolean isNew = false;
	protected boolean isMod = false;
	protected boolean isToDelete = false;
	
}
