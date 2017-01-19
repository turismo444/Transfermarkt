/**
 * 
 * @author Michael Krapf / Orcun Döger
 * 
 */

package model;


/**
 * Diese Abstrakte Klasse wird von Angebot, Verein und Spieler extendet.
 * Das heißt jedes Objekt dieser Klassen hat auch diese Eigenschaften.
 * Diese Eigenschaften werden als Flags benutzt, die beschreiben,
 * ob dieses Objekt im Programm verändert oder neu erstellt wurde.
 * Wenn es gelöscht werden soll, wird isToDelete auf true gesetzt.
 */
public abstract class ObjectBase 
{
	protected boolean isNew = false;
	protected boolean isMod = false;
	protected boolean isToDelete = false;
}