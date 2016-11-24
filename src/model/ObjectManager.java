package model;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;

public class ObjectManager 
{
	// Singleton
	static ObjectManager instance = null;
	private ObjectManager() throws SQLException 
	{
		readVereine();
	}
	
	static ObjectManager getInstance() throws SQLException 
	{
		if (instance == null) instance = new ObjectManager();
		return instance;
	}
	
	HashMap<Integer, Verein> verein = new HashMap<Integer, Verein>();
	Connection db; // Muss noch ausgschiebn werdn
	
	public Verein newVerein() 
	{
		Verein v = new Verein();
		verein.put(v.getVereinsId(), v);
		return v;
	}

	public Verein getVerein(int vereinsId)
	{
		return verein.get(vereinsId);
	}
	
	public Collection<Verein> getAllVereine()
	{
		return verein.values();
	}
	
	// Einlesen aller vereine in die HashMap
	public void readVereine() throws SQLException
	{
		Statement s = db.createStatement();
		ResultSet rs = s.executeQuery("select vereinsid, vereinsname from vereine");
		while (rs.next()) 
		{
			int vereinsid= rs.getInt(1);
			String vereinsname = rs.getString(2);
			
			Verein v = new Verein(vereinsid, vereinsname);
			verein.put(v.getVereinsId(), v);
		}
	}
	
	// store speziell für vereine
	void vStore(Verein v) 
	{
		if (v.isNew) 
		{	
			// JDBC
			v.isNew = false;
		} 
		else if (v.isToDelete) 
		{
			verein.remove(v.getVereinsId());
			// JDBC delete from db
		} 
		else if (v.isMod) 
		{
			v.isMod = false;
		}
	}
	
	//geht alle vereine durch und updatet jene, bei denen Änderungen vorliegen
	void store()
	{
		for(Verein v: verein.values()) 
		{
			vStore(v);
		}
	}
}