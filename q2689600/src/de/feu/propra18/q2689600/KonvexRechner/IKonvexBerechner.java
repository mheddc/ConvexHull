package de.feu.propra18.q2689600.KonvexRechner;

import java.util.ArrayList;

import de.feu.propra18.q2689600.Datamodel.GuiPoint;

/**
 * Interface für alle Klassen, die die konvexe Hülle berechnen.
 *  
 * @author Manuel Heder
 * 
 */

public interface IKonvexBerechner {

	
	/**
	 * Berechnung der konvexen Hülle und gibt sie als ArrayList von Guipoints zurück.
     *
     *@return ArrayList aus GuiPoints, die die konvexe Hülle repräsentiert.
    */
	ArrayList<GuiPoint> calcConvexpolynom();

	/**
	 * Getter fuer R-Punkt.
     *
     *@return Gibt GuiPoint zurueck.
	 */
	GuiPoint getRPoint();

	/**
	 * Getter fuer T-Punkt.
     *
     *@return Gibt GuiPoint zurueck.
	 */
	GuiPoint getTPoint();
	
	/**
	 * Getter fuer L-Punkt.
     *
     *@return Gibt GuiPoint zurueck.
	 */
	GuiPoint getLPoint();

	/**
	 * Getter fuer B-Punkt.
     *
     *@return Gibt GuiPoint zurueck.
	 */
	GuiPoint getBPoint();

}
