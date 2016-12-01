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
		verein.put(v.getVereinsID(), v);
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
		angebot.put(a.getAngebotsID(), a);
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
	
	// Einlesen aller Vereine in die HashMap
	public void readVereine() throws SQLException
	{
		Statement s = db.createStatement();
		ResultSet rs = s.executeQuery("select * from vereine");
		while (rs.next()) 
		{
			int vereinsid = rs.getInt(1);
			String vereinsname = rs.getString(2);
			String vereinsort = rs.getString(3);
			
			Verein v = new Verein(vereinsid, vereinsname, vereinsort);
			verein.put(v.getVereinsID(), v);
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
			
			Spieler sp = new Spieler(spielerid, vereinsid, vorname, nachname, geburtsdatum, leistungswert);
			spieler.put(sp.getSpielerID(), sp);
		}
	}
	
	public void readAngebote() throws SQLException 
	{
		Statement s = db.createStatement();
		ResultSet rs = s.executeQuery("select * from angebote");
		while (rs.next()) 
		{
			int angebotsID = rs.getInt(1);
			int vereinVon = rs.getInt(2);
			int vereinAn = rs.getInt(3);
			int spielerID = rs.getInt(4);
			double gebot = rs.getDouble(5);
			boolean istAngenommen = rs.getBoolean(6);
			boolean istAbgeschlossen = rs.getBoolean(7);
			
			Angebot a = new Angebot(angebotsID, vereinVon, vereinAn, spielerID, gebot, istAngenommen, istAbgeschlossen);
			angebot.put(a.getAngebotsID(), a);
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
			verein.remove(v.getVereinsID());
			// JDBC delete from db
		} 
		else if (v.isMod) 
		{
			v.isMod = false;
		}
	}

	void sStore(Spieler s)
	{
		if(s.isNew)
		{
			s.isNew = false;
		}
		else if (s.isToDelete) 
		{
			spieler.remove(s.getSpielerID());
			// JDBC delete from db
		} 
		else if (s.isMod) 
		{
			s.isMod = false;
		}
	}
	
	void aStore(Angebot a)
	{
		if(a.isNew)
		{
			a.isNew = false;
		}
		else if (a.isToDelete) 
		{
			angebot.remove(a.getAngebotsID());
			// JDBC delete from db
		} 
		else if (a.isMod) 
		{
			a.isMod = false;
		}
	}
	
	// geht alle vereine durch und updatet jene, bei denen Änderungen vorliegen
	void vStore()
	{
		for(Verein v: verein.values()) 
		{
			vStore(v);
		}
	}
	
	void sStore()
	{
		for(Spieler s: spieler.values()) 
		{
			sStore(s);
		}
	}
	
	void aStore()
	{
		for(Angebot a : angebot.values()) 
		{
			aStore(a);
		}
	}
	
	public void close() throws SQLException
	{
		db.close();
	}
}