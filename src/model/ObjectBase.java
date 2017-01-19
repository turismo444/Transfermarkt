/**
 * 
 * @author Michael Krapf / Orcun D�ger
 * 
 */

package model;


/**
 * Diese Abstrakte Klasse wird von Angebot, Verein und Spieler extendet.
 * Das hei�t jedes Objekt dieser Klassen hat auch diese Eigenschaften.
 * Diese Eigenschaften werden als Flags benutzt, die beschreiben,
 * ob dieses Objekt im Programm ver�ndert oder neu erstellt wurde.
 * Wenn es gel�scht werden soll, wird isToDelete auf true gesetzt.
 */
public abstract class ObjectBase 
{
	protected boolean isNew = false;
	protected boolean isMod = false;
	protected boolean isToDelete = false;
}