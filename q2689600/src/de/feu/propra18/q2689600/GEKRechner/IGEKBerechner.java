package de.feu.propra18.q2689600.GEKRechner;

import java.util.ArrayList;

import de.feu.propra18.q2689600.Datamodel.GuiKreis;
import de.feu.propra18.q2689600.Datamodel.GuiPoint;

/**
 * Interface für alle Klassen, die das GEK berechnen.
 *  
 * @author Manuel Heder
 * 
 */

public interface IGEKBerechner {

	/**
	 * Berechnet den GEK.
     *
     *@param convexpolynom ArrayList als Ausgangpunkt für die Berechnung des GEK.
     *@return GuiKreis als GEK.
    */
	public GuiKreis calcMaxKreis(ArrayList<GuiPoint> convexpolynom);
}
