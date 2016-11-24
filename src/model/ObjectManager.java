package model;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;

public class ObjectManager 
{

	Connection db;
	
	// Singleton
	static ObjectManager instance = null;
	
	private ObjectManager() throws SQLException, ClassNotFoundException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		db = DriverManager.getConnection("jdbc:mysql://localhost/transfermarkt", "root", "");
		
		readVereine();
	}
	
	static ObjectManager getInstance() throws SQLException, ClassNotFoundException 
	{
		if (instance == null) instance = new ObjectManager();
		return instance;
	}
	
	HashMap<Integer, Verein> verein = new HashMap<Integer, Verein>();
	HashMap<Integer, Angebot> angebot = new HashMap<Integer, Angebot>();
	HashMap<Integer, Spieler> spieler = new HashMap<Integer, Spieler>();
	
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
	
	public Spieler newSpieler()
	{
		Spieler s = new Spieler();
		spieler.put(s.getSpielerID(), s);
		return s;
	}
	
	public Spieler getSpieler(int spielerID)
	{
		return spieler.get(spielerID);
	}
	
	public Collection<Spieler> getAllSpieler()
	{
		return spieler.values();
	}
	
	public Angebot newAngebot()
	{
		Angebot a = new Angebot();
		angebot.put(a.getAngebotsID, a);
		return a;
	}
	
	public Angebot getAngebot(int angebotsID)
	{
		return angebot.get(angebotsID);
	}
	
	public Collection<Angebot> getAllAngebote()
	{
		return angebot.values();
	}
	
	// Einlesen aller vereine in die HashMap
	public void readVereine() throws SQLException
	{
		Statement s = db.createStatement();
		ResultSet rs = s.executeQuery("select * from vereine");
		while (rs.next()) 
		{
			int vereinsid= rs.getInt(1);
			String vereinsname = rs.getString(2);
			
			Verein v = new Verein(vereinsid, vereinsname);
			verein.put(v.getVereinsId(), v);
		}
	}
	
	public void readSpieler() throws SQLException 
	{
		Statement s = db.createStatement();
		ResultSet rs = s.executeQuery("select * from spieler");
		while (rs.next()) 
		{
			int spielerid = rs.getInt(1);
			int vereinsid= rs.getInt(2);
			String vorname = rs.getString(3);
			String nachname = rs.getString(4);
			Date geburtsdatum = rs.getDate(5);
			int leistungswert = rs.getInt(6);
			
			Spieler s = new Spieler(spielerid, vereinsid, vorname, nachname, geburtsdatum, leistungswert);
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
	
	public void close() throws SQLException
	{
		db.close();
	}
}