package model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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
			int gebot = rs.getInt(5);
			boolean istAngenommen = rs.getBoolean(6);
			boolean istAbgeschlossen = rs.getBoolean(7);
			
			Angebot a = new Angebot(angebotsID, vereinVon, vereinAn, spielerID, gebot, istAngenommen, istAbgeschlossen);
			angebot.put(a.getAngebotsID(), a);
		}
	}
	
	// store speziell für vereine
	void vStore(Verein v) throws SQLException 
	{
		if (v.isNew) 
		{	
			String sql = "INSERT INTO Vereine VALUES(?, ?, ?)";
			java.sql.PreparedStatement stmt = db.prepareStatement(sql);
			stmt.setInt(1, v.getVereinsID());
			stmt.setString(2, v.getVereinsname());
			stmt.setString(3, v.getVereinsort());
			stmt.executeUpdate(sql);
			stmt.close();
						
			v.isNew = false;
		} 
		else if (v.isToDelete) 
		{
			verein.remove(v.getVereinsID());
			
			String sql = "DELETE FROM Vereine WHERE VereinsID = " + v.getVereinsID();
			Statement stmt = db.prepareStatement(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} 
		else if (v.isMod) 
		{
			v.isMod = false;
		}
	}

	void sStore(Spieler s) throws SQLException
	{
		if(s.isNew)
		{
			String sql = "INSERT INTO Spieler VALUES(?, ?, ?, ?, ?, ?)";
			java.sql.PreparedStatement stmt = db.prepareStatement(sql);
			stmt.setInt(1, s.getSpielerID());
			stmt.setInt(2, s.getVereinsID());
			stmt.setString(3, s.getVorname());
			stmt.setString(4, s.getNachname());
			stmt.setDate(5, s.getGeburtsdatum());
			stmt.setInt(6, s.getLeistungswert());
			stmt.executeUpdate(sql);
			stmt.close();
			
			s.isNew = false;
		}
		else if (s.isToDelete) 
		{
			spieler.remove(s.getSpielerID());
			
			String sql = "DELETE FROM Spieler WHERE SpielerID = " + s.getSpielerID();
			Statement stmt = db.prepareStatement(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} 
		else if (s.isMod) 
		{
			s.isMod = false;
		}
	}
	
	void aStore(Angebot a) throws SQLException
	{
		if(a.isNew)
		{
			String sql = "INSERT INTO Angebote VALUES(?, ?, ?, ?, ?, ?, ?)";
			java.sql.PreparedStatement stmt = db.prepareStatement(sql);
			stmt.setInt(1, a.getAngebotsID());
			stmt.setInt(2, a.getVereinVon());
			stmt.setInt(3, a.getVereinAn());
			stmt.setInt(4, a.getSpielerID());
			stmt.setInt(5, a.getGebot());
			stmt.setBoolean(6, a.isIstAngenommen());
			stmt.setBoolean(7, a.isIstAbgeschlossen());
			stmt.executeUpdate(sql);
			stmt.close();
			
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
	void vStore() throws SQLException
	{
		for(Verein v: verein.values()) 
		{
			vStore(v);
		}
	}
	
	void sStore() throws SQLException
	{
		for(Spieler s: spieler.values()) 
		{
			sStore(s);
		}
	}
	
	void aStore() throws SQLException
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