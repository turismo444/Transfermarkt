/**
 * 
 * @author Michael Krapf / Orcun D�ger
 * 
 */

package model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ObjectManager 
{
	Connection db;
	
	// Singleton
	static ObjectManager instance = null;
	
	/**
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private ObjectManager() throws SQLException, ClassNotFoundException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		db = DriverManager.getConnection("jdbc:mysql://localhost/transfermarkt", "root", "");
		
		readVereine();
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	static ObjectManager getInstance() throws SQLException, ClassNotFoundException 
	{
		if (instance == null) instance = new ObjectManager();
		return instance;
	}
	
	/**
	 * 
	 */
	HashMap<Integer, Verein> verein = new HashMap<Integer, Verein>();
	HashMap<Integer, Angebot> angebot = new HashMap<Integer, Angebot>();
	HashMap<Integer, Spieler> spieler = new HashMap<Integer, Spieler>();
	
	/**
	 * 
	 * @return
	 */
	public Verein newVerein() 
	{
		Verein v = new Verein();
		verein.put(v.getVereinsID(), v);
		return v;
	}

	/**
	 * 
	 * @param vereinsId
	 * @return
	 */
	public Verein getVerein(int vereinsId)
	{
		return verein.get(vereinsId);
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection<Verein> getAllVereine()
	{
		return verein.values();
	}
	
	/**
	 * 
	 * @return
	 */
	public Spieler newSpieler()
	{
		Spieler s = new Spieler();
		spieler.put(s.getSpielerID(), s);
		return s;
	}
	
	/**
	 * 
	 * @param spielerID
	 * @return
	 */
	public Spieler getSpieler(int spielerID)
	{
		return spieler.get(spielerID);
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection<Spieler> getAllSpieler()
	{
		return spieler.values();
	}
	
	/**
	 * 
	 * @return
	 */
	public Angebot newAngebot()
	{
		Angebot a = new Angebot();
		angebot.put(a.getAngebotsID(), a);
		return a;
	}
	
	/**
	 * 
	 * @param angebotsID
	 * @return
	 */
	public Angebot getAngebot(int angebotsID)
	{
		return angebot.get(angebotsID);
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection<Angebot> getAllAngebote()
	{
		return angebot.values();
	}
	
	// Einlesen aller Vereine in die HashMap
	/**
	 * 
	 * @throws SQLException
	 */
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
	
	/**
	 * 
	 * @throws SQLException
	 */
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
	
	/**
	 * 
	 * @throws SQLException
	 */
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
	
	// store speziell f�r vereine
	/**
	 * 
	 * @param v
	 * @throws SQLException
	 */
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
			String sql = "DELETE FROM Vereine WHERE VereinsID = "+ v.getVereinsID();
			Statement stmt = db.prepareStatement(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			
			v.isToDelete = false;
			verein.remove(v.getVereinsID());
		} 
		else if (v.isMod) 
		{
			String sql = "UPDATE Vereine SET Vereinsname = ?, Vereinsort = ? WHERE VereinsID = ?";
			java.sql.PreparedStatement stmt = db.prepareStatement(sql);
			stmt.setString(1, v.getVereinsname());
			stmt.setString(2, v.getVereinsort());
			stmt.setInt(3, v.getVereinsID());
			stmt.executeUpdate(sql);
			stmt.close();
						
			v.isMod = false;
		}
	}

	/**
	 * 
	 * @param s
	 * @throws SQLException
	 */
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
			String sql = "DELETE FROM Spieler WHERE SpielerID = " + s.getSpielerID();
			Statement stmt = db.prepareStatement(sql);
			stmt.executeUpdate(sql);
			stmt.close();

			spieler.remove(s.getSpielerID());
		} 
		else if (s.isMod) 
		{
			String sql = "UPDATE Spieler SET VereinsID = ?, Vorname = ?, Nachname = ?, Geburtsdatum = ?, Leistungswert = ?  WHERE SpielerID = ?";
			java.sql.PreparedStatement stmt = db.prepareStatement(sql);
			stmt.setInt(1, s.getVereinsID());
			stmt.setString(2, s.getVorname());
			stmt.setString(3, s.getNachname());
			stmt.setDate(4, s.getGeburtsdatum());
			stmt.setInt(5, s.getLeistungswert());
			stmt.setInt(6, s.getSpielerID());
			stmt.executeUpdate(sql);
			stmt.close();
			
			s.isMod = false;
		}
	}
	
	/**
	 * 
	 * @param a
	 * @throws SQLException
	 */
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
			String sql = "DELETE FROM Angebote WHERE AngebotsID = " + a.getAngebotsID();
			Statement stmt = db.prepareStatement(sql);
			stmt.executeUpdate(sql);
			stmt.close();

			angebot.remove(a.getAngebotsID());
		} 
		else if (a.isMod) 
		{
			String sql = "UPDATE Angebote SET VereinVon = ?, VereinAn = ?, SpielerID = ?, Gebot = ?, istAngenommen = ?, istAbgeschlossen = ? WHERE AngebotsID = ?";
			java.sql.PreparedStatement stmt = db.prepareStatement(sql);
			stmt.setInt(1, a.getVereinVon());
			stmt.setInt(2, a.getVereinAn());
			stmt.setInt(3, a.getSpielerID());
			stmt.setInt(4, a.getGebot());
			stmt.setBoolean(5, a.isIstAngenommen());
			stmt.setBoolean(6, a.isIstAbgeschlossen());
			stmt.setInt(7, a.getAngebotsID());
			stmt.executeUpdate(sql);
			stmt.close();
			
			a.isMod = false;
		}
	}
	
	// geht alle vereine durch und updatet jene, bei denen �nderungen vorliegen
	/**
	 * 
	 * @throws SQLException
	 */
	void vStore() throws SQLException
	{
		for(Verein v: verein.values()) 
		{
			vStore(v);
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	void sStore() throws SQLException
	{
		for(Spieler s: spieler.values()) 
		{
			sStore(s);
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	void aStore() throws SQLException
	{
		for(Angebot a : angebot.values()) 
		{
			aStore(a);
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException
	{
		db.close();
	}
}