/**
 * 
 * @author Michael Krapf / Orcun Döger
 * 
 */

package model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ObjectManager 
{
	Connection db;
	
	/**
	 * Dies ist nötig, um aus dieser Klasse einen Singelton machen zu können.
	 */
	static ObjectManager instance = null;
	
	/**
	 * Im Konstruktor wird die JDBC Verbindung zur Datenbank aufgebaut
	 * und direkt alles aus der Datenbank eingelesen ins Programm.
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
	 * Dies ist die Methode des Singleton-Patterns, um das einzige Objekt aufrufen zu können.
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
	 * Die Hashmaps dienen dazu, um effizienter durch die ID auf das Objekt zugreifen zu können.
	 */
	HashMap<Integer, Verein> verein = new HashMap<Integer, Verein>();
	HashMap<Integer, Angebot> angebot = new HashMap<Integer, Angebot>();
	HashMap<Integer, Spieler> spieler = new HashMap<Integer, Spieler>();
	
	/**
	 * Mit dieser Methode erstellt man einen neuen Verien.
	 * Beim erstellen eines neues Vereins wird sie direkt in die Hashmap eingetragen.
	 * @return
	 */
	public Verein newVerein() 
	{
		Verein v = new Verein();
		verein.put(v.getVereinsID(), v);
		return v;
	}

	/**
	 * Gibt den gewünschten Verein.
	 * @param vereinsId
	 * @return
	 */
	public Verein getVerein(int vereinsId)
	{
		return verein.get(vereinsId);
	}
	
	/**
	 * Gibt eine Collection mit alles Vereinen.
	 * @return
	 */
	public Collection<Verein> getAllVereine()
	{
		return verein.values();
	}
	
	/**
	 * Erstellt einen neuen Spieler
	 * @return
	 */
	public Spieler newSpieler()
	{
		Spieler s = new Spieler();
		spieler.put(s.getSpielerID(), s);
		return s;
	}
	
	/**
	 * Gibt einen bestimmten Spieler.
	 * @param spielerID
	 * @return
	 */
	public Spieler getSpieler(int spielerID)
	{
		return spieler.get(spielerID);
	}
	
	/**
	 * Gibt alle Spieler als Collection
	 * @return
	 */
	public Collection<Spieler> getAllSpieler()
	{
		return spieler.values();
	}
	
	/**
	 * Erstellt neues eines Angebot
	 * @return
	 */
	public Angebot newAngebot()
	{
		Angebot a = new Angebot();
		angebot.put(a.getAngebotsID(), a);
		return a;
	}
	
	/**
	 * Gibt ein bestimmtes Angebot
	 * @param angebotsID
	 * @return
	 */
	public Angebot getAngebot(int angebotsID)
	{
		return angebot.get(angebotsID);
	}
	
	/**
	 * Gibt alle Angebote als Collection
	 * @return
	 */
	public Collection<Angebot> getAllAngebote()
	{
		return angebot.values();
	}
	
	/**
	 * Liest alle Vereine von der Datenbank in das Programm ein.
	 * Diese Methode wird auch im Konstruktor ausgeführt
	 * @throws SQLException
	 */
	public void readVereine()
	{
		Statement s = null;
		ResultSet rs = null;
		
		try{
			s = db.createStatement();
			rs = s.executeQuery("select * from vereine");
			while (rs.next()) 
			{
				int vereinsid = rs.getInt(1);
				String vereinsname = rs.getString(2);
				String vereinsort = rs.getString(3);
				
				Verein v = new Verein(vereinsid, vereinsname, vereinsort);
				verein.put(v.getVereinsID(), v);
			}
		} 
		catch (SQLException e) {}
		finally{
			saveCloseRS(rs);
			saveCloseST(s);
		}
		
		
	}
	
	/**
	 * Liest alle Spieler von der Datenbank in das Programm ein.
	 * @throws SQLException
	 */
	public void readSpieler()
	{
		Statement s = null;
		ResultSet rs = null;
		
		try{
			s = db.createStatement();
			rs = s.executeQuery("select * from spieler");
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
		catch (SQLException e) {}
		finally{
			saveCloseRS(rs);
			saveCloseST(s);
		}
	}
	
	/**
	 * Liest alle Angebote von der Datenbank in das Programm ein.
	 * @throws SQLException
	 */
	public void readAngebote()
	{
		Statement s = null;
		ResultSet rs = null;
		
		try{
			s = db.createStatement();
			rs = s.executeQuery("select * from angebote");
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
		catch (SQLException e) {}
		finally{
			saveCloseRS(rs);
			saveCloseST(s);
		}
	}
	
	/**
	 * Speichert bei Bedarf Veränderungen eines Vereins oder neue Vereine in der Datenbank ab.
	 * Je Nach Neu, Modifiziert oder zu Löschen verhält sich diese Methode verschieden.
	 * @param v
	 * @throws SQLException
	 */
	void vStore(Verein v)
	{
		PreparedStatement stmt = null;

		try{
			if (v.isNew) 
			{	
				String sql = "INSERT INTO Vereine VALUES(?, ?, ?)";
				stmt = db.prepareStatement(sql);
				stmt.setInt(1, v.getVereinsID());
				stmt.setString(2, v.getVereinsname());
				stmt.setString(3, v.getVereinsort());
				stmt.executeUpdate(sql);
				stmt.close();
							
				v.isNew = false;
			} 
			else if (v.isToDelete) 
			{
				String sql = "DELETE FROM Vereine WHERE VereinsID = ?";
				stmt = db.prepareStatement(sql);
				stmt.setInt(1, v.getVereinsID());
				stmt.executeUpdate(sql);
				stmt.close();
				
				v.isToDelete = false;
				verein.remove(v.getVereinsID());
			} 
			else if (v.isMod) 
			{
				String sql = "UPDATE Vereine SET Vereinsname = ?, Vereinsort = ? WHERE VereinsID = ?";
				stmt = db.prepareStatement(sql);
				stmt.setString(1, v.getVereinsname());
				stmt.setString(2, v.getVereinsort());
				stmt.setInt(3, v.getVereinsID());
				stmt.executeUpdate(sql);
				stmt.close();
							
				v.isMod = false;
			}
		} 
		catch (SQLException e) {}
		finally{
			saveClosePS(stmt);
		}
		
	}

	/**
	 * Speichert bei Bedarf Veränderungen eines Spielers oder neue Spieler in der Datenbank ab.
	 * Je Nach Neu, Modifiziert oder zu Löschen verhält sich diese Methode verschieden.
	 * @param s
	 * @throws SQLException
	 */
	void sStore(Spieler s)
	{
		PreparedStatement stmt = null;
		
		
		try{
			if(s.isNew)
			{
				String sql = "INSERT INTO Spieler VALUES(?, ?, ?, ?, ?, ?)";
				stmt = db.prepareStatement(sql);
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
				String sql = "DELETE FROM Spieler WHERE SpielerID = ?";
				stmt = db.prepareStatement(sql);
				stmt.setInt(1, s.getSpielerID());
				stmt.executeUpdate(sql);
				stmt.close();
	
				spieler.remove(s.getSpielerID());
			} 
			else if (s.isMod) 
			{
				String sql = "UPDATE Spieler SET VereinsID = ?, Vorname = ?, Nachname = ?, Geburtsdatum = ?, Leistungswert = ?  WHERE SpielerID = ?";
				stmt = db.prepareStatement(sql);
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
		catch (SQLException e) {}
		finally{
			saveClosePS(stmt);
		}
	}
	
	/**
	 * Speichert bei Bedarf Veränderungen eines Angebots oder neue Angebote in der Datenbank ab.
	 * Je Nach Neu, Modifiziert oder zu Löschen verhält sich diese Methode verschieden.
	 * @param a
	 * @throws SQLException
	 */
	void aStore(Angebot a)
	{
		PreparedStatement stmt = null;
		
		try{
			if(a.isNew)
			{
				String sql = "INSERT INTO Angebote VALUES(?, ?, ?, ?, ?, ?, ?)";
				stmt = db.prepareStatement(sql);
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
				String sql = "DELETE FROM Angebote WHERE AngebotsID = ?";
				stmt = db.prepareStatement(sql);
				stmt.setInt(1, a.getAngebotsID());
				stmt.executeUpdate(sql);
				stmt.close();
	
				angebot.remove(a.getAngebotsID());
			} 
			else if (a.isMod) 
			{
				String sql = "UPDATE Angebote SET VereinVon = ?, VereinAn = ?, SpielerID = ?, Gebot = ?, istAngenommen = ?, istAbgeschlossen = ? WHERE AngebotsID = ?";
				stmt = db.prepareStatement(sql);
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
		catch (SQLException e) {}
		finally{
			saveClosePS(stmt);
		}
	}
	
	/**
	 * Dies führt die Methode für die Speicherung des Vereins für alle Vereine aus.
	 * Geht alle Vereine durch und updatet jene, bei denen Änderungen vorliegen
	 * @throws SQLException
	 */
	void vStore()
	{
		for(Verein v: verein.values()) 
		{
			vStore(v);
		}
	}
	
	/**
	 * Dies führt die Methode für die Speicherung des Spieler für alle Vereine aus.
	 * @throws SQLException
	 */
	void sStore()
	{
		for(Spieler s: spieler.values()) 
		{
			sStore(s);
		}
	}
	
	/**
	 * Dies führt die Methode für die Speicherung des Angebote für alle Vereine aus.
	 * @throws SQLException
	 */
	void aStore()
	{
		for(Angebot a : angebot.values()) 
		{
			aStore(a);
		}
	}
	
	public void saveCloseRS(ResultSet rs)
	{
		try
		{
			if(rs == null)
			{
				rs.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveCloseST(Statement stmt)
	{
		try
		{
			if(stmt == null)
			{
				stmt.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveClosePS(PreparedStatement stmt)
	{
		try
		{
			if(stmt == null)
			{
				stmt.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Schließt die Verbundung zur Datenbank
	 * @throws SQLException
	 */
	public void close() throws SQLException
	{
		db.close();
	}
}