package de.feu.propra18.q2689600.UndoRedo;

import de.feu.propra18.q2689600.Datamodel.GuiPoint;
/**
 * Diese Klasse stellt einen Vorgang für das Loeschen eines Punktes dar.
 *  
 * @author Manuel Heder
 * 
 */
public class VorgangLoeschen extends Vorgang{

		//Geloeschter Punkt
		private GuiPoint geloescht;
		
		/**
		 * Konstruktor;
	     *
	     *@param geloescht GuiPoint des geloeschten Punktes.
	    */
		public VorgangLoeschen(GuiPoint geloescht) {
			super();
			setGeloescht(geloescht);
		}

		/**
		 * Getter für geloeschten Punkt.
	     *
		 * @return GuiPoint stellt den geloeschten Punkt dar.
		 */
		public GuiPoint getGeloescht() {
			return geloescht;
		}

		/**
		 * Setter für geloeschten Punkt.
	     *
		 * @param geloescht GuiPoint stellt den geloeschten Punkt dar.
		 */
		public void setGeloescht(GuiPoint geloescht) {
			this.geloescht = geloescht;
		}


}
