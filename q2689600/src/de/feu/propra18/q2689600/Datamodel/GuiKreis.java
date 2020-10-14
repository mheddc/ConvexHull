package de.feu.propra18.q2689600.Datamodel;


/**
 * Diese Klasse stellt ein Modell für einen Kreis mit Mittelpunkt und Radius dar.
 * 
 * @author Manuel Heder
 * 
 */

public class GuiKreis {
	
	//Variablen für Mittelpunkt und Radius des Kreises
	Double Mittelpunktx;
	Double Mittelpunkty;
	Double Radius;
	
	/**
	 * Konstruktor; Initialisiert Variablen.
     *
    */
	public GuiKreis() {
		Mittelpunktx=0.;
		Mittelpunkty=0.;
		Radius=0.;
	}
	
	/**
	 * Konstruktor; Initialisiert Variablen mit Werten für Mittelpunkt und Radius.
     *
     * @param Mittelpunktx int der die x-Koordinate des Mittelpunktes repraesentiert.
     * @param Mittelpunkty int der die y-Koordinate des Mittelpunktes repraesentiert.
     * @param Radius int der den Radius des Mittelpunktes repraesentiert.
	 */
	public GuiKreis(int Mittelpunktx,int Mittelpunkty,int Radius) {
		this.Mittelpunktx=(double)Mittelpunktx;
		this.Mittelpunkty=(double)Mittelpunkty;
		this.Radius=(double)Radius;
	}
	
	/**
	 * Gibt die x-Koordinate des Mittelpunktes zurueck. (Getter)
     *
	 * @return  Double der die x-Koordinate des Mittelpunktes repraesentiert.
	 */
	public Double getMittelpunktx() {
		return Mittelpunktx;
	}

	/**
	 *Weist die x-Koordinate des Mittelpunktes des Keises zu. (Setter)
     *
	 * @param  mittelpunktx Double der die x-Koordinate des Mittelpunktes repraesentiert.
	 */
	public void setMittelpunktx(Double mittelpunktx) {
		Mittelpunktx = mittelpunktx;
	}

	/**
	 * Gibt die y-Koordinate des Mittelpunktes zurueck. (Getter)
     *
	 * @return  Double der die y-Koordinate des Mittelpunktes repraesentiert.
	 */
	public Double getMittelpunkty() {
		return Mittelpunkty;
	}


	/**
	 *Weist die y-Koordinate des Mittelpunktes des Keises zu. (Setter)
     *
	 * @param  mittelpunkty Double der die y-Koordinate des Mittelpunktes repraesentiert.
	 */
	public void setMittelpunkty(Double mittelpunkty) {
		Mittelpunkty = mittelpunkty;
	}

	
	/**
	 * Gibt den Radiu des Keises zurueck. (Getter)
     *
	 * @return  Double der Radius des Kreises repraesentiert.
	 */
	public Double getRadius() {
		return Radius;
	}

	/**
	 *Weist den Radius des Keises zu. (Setter)
     *
	 * @param  radius Double der den Radius des Kreises repraesentiert.
	 */
	public void setRadius(Double radius) {
		Radius = radius;
	}
	
}
