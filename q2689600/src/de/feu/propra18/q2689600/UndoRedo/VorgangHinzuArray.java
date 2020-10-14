package de.feu.propra18.q2689600.UndoRedo;

import java.util.ArrayList;

import de.feu.propra18.q2689600.Datamodel.GuiPoint;
/**
 * Diese Klasse stellt einen Vorgang für das Hinzufügen eines Array von Punkten dar.
 *  
 * @author Manuel Heder
 * 
 */
public class VorgangHinzuArray extends Vorgang{


	//pointList enthaelt die Punkte.
	private ArrayList<GuiPoint> pointList;
	
	/**
	 * Konstruktor;
     *
    */
	public VorgangHinzuArray() {
		super();
	}

	/**
	 * Fügt der pointList einen neuen Punkt zu.
     *
     *@param hinzu GuiPoint stellt das hinzuzufügende Element dar.
    */
	public void addPoint(GuiPoint hinzu) {
		pointList.add(hinzu);
	}

	/**
	 * Getter für die Punktliste.
     *
	 * @return ArrayList als Liste der Punkte.
	 */
	public ArrayList<GuiPoint> getPointList() {
		return pointList;
	}

	/**
	 * Setter für die Punktliste. 
     *
	 * @param pointList ArrayList als Liste der Punkte.
	 */
	public void setPointList(ArrayList<GuiPoint> pointList) {
		this.pointList = pointList;
	}
	
	
}
