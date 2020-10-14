package de.feu.propra18.q2689600.Datamodel;



/**
 * Diese Klasse stellt eine Kante dar und wird zur Berechnung des GEK verwendet.
 * Außerdem besitzt die Klasse Referenzen auf die vorhergehende und nachfolgende Kante.
 * Die vohergehende und nachfolgende Kante werden durch den Alg. der Kantenelemination zugewiesen.
 * Außerdem speichert die Klasse den berechnetn Kreis mit der nachfolgenden und vorhergehenden Kante.
 * 
 * @author Manuel Heder
 * 
 */

public class GuiEdge implements Comparable {

	private GuiPoint Vor; //Referenz auf den Punkt p1 der vorigen Kante. Die vorige Kante wid durch den Alg. zur Kantenelemination zugewiesen.
	private GuiPoint p1;  //Erster Punkt der Kante
	private GuiPoint p2;  //Zweiter Punkt der Kante
	private GuiPoint Nach;//Referenz auf den Punkt p1 der nachfolgenden Kante. Die nachfolgende Kante wid durch den Alg. zur Kantenelemination zugewiesen.
	private GuiKreis kreis;//Stellt einen Kreis mit Radius und Mittelpunkt dar. 
						   //Wird berechnet mit einer nachfolgenden und einer vorhergehenden Kante. 

	/**
	 * Konstruktor; Initialisiert Variablen.
     *
	 */
	public GuiEdge() {
		//Initialisierung
		Vor=new GuiPoint();
		p1=new GuiPoint();
		p2=new GuiPoint();
		Nach=new GuiPoint();
		kreis=new GuiKreis();
	}
	
	/**
	 * Gibt den Kreis zurueck. (Getter)
     *
	 * @return  GuiKreis der den berechneten Kreis mit einer vorhergehenden und nachfolgenden Kante repraesentiert.
	 */
	public GuiKreis getKreis() {
		return kreis;
	}

	/**
	 * Weist der Variablen Kreis einen Kreis zu. (Setter)
     *
     * @param kreis GuiKreis der den berechneten Kreis mit einer vorhergehenden und nachfolgenden Kante repraesentiert.
	 */
	public void setKreis(GuiKreis kreis) {
		this.kreis = kreis;
	}

	/**
	 * Gibt den Punkt p1 der vorhergenenden Kante zurueck. (Getter)
     *
	 * @return  GuiPoint der den Punkt p1 der vorhergehenden Kante repraesentiert.
	 */
	public GuiPoint getVor() {
		return Vor;
	}

	/**
	 * Weist der Variablen vor den Punkt p1 der vorhergehenden Kante zu. (Setter)
     *
     * @param vor GuiPoint p1 vorhergehenden Kante.
	 */
	public void setVor(GuiPoint vor) {
		Vor = vor;
	}
	
	/**
	 * Gibt den Punkt p1 der Kante zurueck. (Getter)
     *
	 * @return  GuiPoint p1.
	 */
	public GuiPoint getP1() {
		return p1;
	}
	
	/**
	 * Setzt den Punkt p1. (Setter)
     *
	 * @param  p1 GuiPoint p1 der aktuellen Kante.
	 */
	public void setP1(GuiPoint p1) {
		this.p1 = p1;
	}

	/**
	 * Gibt den Radius des Kreises der Kante zurueck. (Getter)
     *
	 * @return  Double der den Radius des Kreises der Kante repraesentiert.
	 */
	public Double getRadius() {
		return kreis.getRadius();
	}
	
	/**
	 * Setzt den Radius des Kreises. (Setter)
     *
	 * @param  radius Double der den Radius des Kreises repraesentiert.
	 */
	public void setRadius(Double radius) {
		this.kreis.setRadius(radius);
	}

	/**
	 * Gibt den Punkt p1 der nachfolgenden Kante zurueck. (Getter)
     *
	 * @return  GuiPoint der den Punkt p1 der nachfolgenden Kante repraesentiert.
	 */
	public GuiPoint getNach() {
		return Nach;
	}

	/**
	 * Weist der Variablen nach den Punkt p1 der nachfolgenden Kante zu. (Setter)
     *
     * @param nach GuiPoint p1 nachfolgenden Kante.
	 */
	public void setNach(GuiPoint nach) {
		Nach = nach;
	}

	/**
	 * Gibt den Punkt p2 der Kante zurueck. (Getter)
     *
	 * @return  GuiPoint p2.
	 */
	public GuiPoint getP2() {
		return p2;
	}

	/**
	 * Setzt den Punkt p2. (Setter)
     *
	 * @param  p2 GuiPoint p2 der aktuellen Kante.
	 */
	public void setP2(GuiPoint p2) {
		this.p2 = p2;
	}
	
	/**
	 * Implementiert das Comparable-Interface. Das uebergebene Objekt wird mit dem aktuellen Objekt verglichen.
     * Wird benötigt, um die Kanten in einem TreeSet speichern zu können.
     * Um eine Ordnung zu definieren, wird dabei der Radius genutzt. Sollte der Radius gleich sein, so wird die Koordinate des Punktes p1 der Kante genutzt.
     * 
	 * @param o  GuiEdge die verglichen werden soll.
	 * @return  int  Gibt einen Wert [-1,0,+1] zurueck, um anzuzeigen, ob das uebergebene Objekt kleiner, gleich oder groeßer ist.
	 */
	@Override
	public int compareTo(Object o) {
		GuiEdge dummyEdge=(GuiEdge)o;
		if (this.getRadius()<dummyEdge.getRadius()) {
			return -1;
		} else if (this.getRadius()>dummyEdge.getRadius()){
			return +1;	
		} else {
			if(this.getP1().getX()<dummyEdge.getP1().getX()) {
				return -1;
			}else if (this.getP1().getX()>dummyEdge.getP1().getX()) {
				return +1;
			} else {
				if(this.getP1().getY()<dummyEdge.getP1().getY()) {
					return -1;
				}else if (this.getP1().getY()>dummyEdge.getP1().getY()) {
					return +1;
				} else {
					return 0;
				}
			}
		}
	}




}
