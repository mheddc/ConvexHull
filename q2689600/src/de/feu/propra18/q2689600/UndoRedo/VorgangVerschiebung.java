package de.feu.propra18.q2689600.UndoRedo;

import de.feu.propra18.q2689600.Datamodel.GuiPoint;
/**
 * Diese Klasse stellt einen Vorgang für das Veschieben eines Punktes dar.
 *  
 * @author Manuel Heder
 * 
 */
public class VorgangVerschiebung extends Vorgang{

	
		private GuiPoint alt;//Koordinaten des Punktes vor dem Verschieben
		private GuiPoint neu;//Koordinaten des Punktes nach dem Verschieben
		private GuiPoint movingPoint;//Referenz auf den verschobenen Punkt
		
		
		/**
		 * Konstruktor;
	     *
	     *@param alt GuiPoint mit Koordinaten vor dem Verschieben.
	     *@param neu GuiPoint mit Koordinaten nach dem Verschieben.
	    */
		public VorgangVerschiebung(GuiPoint alt,GuiPoint neu) {
			super();
			setAlt(alt);
			setNeu(neu);
		}

		/**
		 * Konstruktor;Initialisierung von Variablen
	     *
	    */
		public VorgangVerschiebung() {
			super();
			setAlt(null);
			setNeu(null);
		}

		/**
		 * Getter für Koordinaten des Punktes vor der Verschiebung in Form eines GuiPoints.
	     *
		 * @return GuiPoint mit den Koordinaten des Punktes vor der Verschiebung.
		 */
		public GuiPoint getAlt() {
			return alt;
		}

		/**
		 * Setter für die alten Koordinaten des Punktes vor der Verschiebung in Form eines GuiPoints.
	     *
		 * @param alt GuiPoint mit den Koordinaten des Punktes vor der Verschiebung.
		 */
		public void setAlt(GuiPoint alt) {
			this.alt = alt;
		}

		/**
		 * Getter für Koordinaten des Punktes nach der Verschiebung in Form eines GuiPoints.
	     *
		 * @return GuiPoint mit den Koordinaten des Punktes nach der Verschiebung.
		 */
		public GuiPoint getNeu() {
			return neu;
		}

		/**
		 * Setter für die Koordinaten des Punktes nach der Verschiebung in Form eines GuiPoints.
	     *
		 * @param neu GuiPoint mit den Koordinaten des Punktes nach der Verschiebung.
		 */
		public void setNeu(GuiPoint neu) {
			this.neu = neu;
		}
	
		/**
		 * Getter für den verschobenen Punkt.
	     *
		 * @return GuiPoint als verschobene Punkt.
		 */
		public GuiPoint getMovingPoint() {
			return movingPoint;
		}

		/**
		 * Setter für den verschobenen Punkt.
	     *
		 * @param movingPoint GuiPoint als Referenz des verschobenen Punktes.
		 */
		public void setMovingPoint(GuiPoint movingPoint) {
			this.movingPoint = movingPoint;
		}

		
		
}
