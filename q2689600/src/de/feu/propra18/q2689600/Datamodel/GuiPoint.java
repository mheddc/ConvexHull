package de.feu.propra18.q2689600.Datamodel;

/**
 * Diese Klasse stellt einen Punkt auf der Zeichenebene dar.
 * 
 * @author Manuel Heder
 * 
 */

public class GuiPoint implements Comparable<GuiPoint>{

	//koordinaten x und y.
	private int x;
	private int y;
	
	
	
	/**
	 * Konstruktor; Initialisiert den Punkt mit den Koordinaten (0,0).
	 *
	 */
	public GuiPoint(){
		this.setX(0);
		this.setY(0);
	}
	
	/**
	 * Konstruktor; Erstellt einen Punkten mir den uebergebenen Koordinaten.
     *
	 * @param x  Integer der die x-Koordinate repraesentiert.
	 * @param y  Integer der die y-Koordinate repraesentiert.
	 */
	public GuiPoint(int x, int y){
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Implementiert das Comparable-Interface. Das uebergebene Objekt wird mit dem aktuellen Objekt verglichen.
     *
	 * @param o  GuiPoint der verglichen werden soll.
	 * @return  int  Gibt einen Wert [-1,0,+1] zurueck, um anzuzeigen, ob das uebergebene Objekt kleiner, gleich oder groeßer ist.
	 */
	@Override
	public int compareTo(GuiPoint o) {
		// TODO Auto-generated method stub
		long a=this.getX();
		long b=o.getX();
		long c=this.getY();
		if (c==0) {
			c=0;
		}
		long d=o.getY();
		if (a>b) {return 1;}
		else if (a<b) {return -1;}
		else if (c>d) {return 1;}
		else if (c<d) {return -1;}
		else return 0;
	}

	/**
	 * Gibt die x-Koordinate zurueck. (Getter)
     *
	 * @return  int der die x-Koordinate repraesentiert.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setzt die x-Koordinate. (Setter)
     *
     * @param x int der die x-Koordinate repraesentiert.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gibt die y-Koordinate zurueck. (Getter)
     *
	 * @return  int Integer der die y-Koordinate repraesentiert.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setzt die y-Koordinate. (Setter)
     *
     * @param y int der die y-Koordinate repraesentiert.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Gibt die x-Koordinate zurueck. (Getter)
     *
	 * @return  int der die x-Koordinate repraesentiert.
	 */
	public double getXdouble() {
		return (double)x;
	}
	
	/**
	 * Gibt die y-Koordinate zurueck. (Getter)
     *
	 * @return  int Integer der die y-Koordinate repraesentiert.
	 */
	public double getYdouble() {
		return (double)y;
	}
}
