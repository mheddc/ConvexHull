package de.feu.propra18.q2689600.UndoRedo;



/**
 * Diese abstrakte Klasse stellt einen Vorgang dar als 
 * Basiselement zur Implementierung der Undo und Redofunktion.
 *  
 * @author Manuel Heder
 * 
 */
public abstract class Vorgang {

	//Referenzen auf Vorgänger- und Nachfolgelemente
	private Vorgang vor;
	private Vorgang nach;

	/**
	 * Konstruktor; Initialisiert Variablen.
     *
    */
	public Vorgang () {
		setVor(null);
		setNach(null);
	}

	/**
	 * Gibt den Vorgang vor zurueck. (Getter)
     *
	 * @return  Vorgang als voriges Element.
	 */
	public Vorgang getVor() {
		return vor;
	}

	/**
	 * Weist den Vorgang nach zu. (Setter)
     *
	 * @param  vor Vorgang als vorhergehendes Element .
	 */
	public void setVor(Vorgang vor) {
		this.vor = vor;
	}

	/**
	 * Gibt den Vorgang nach zurueck. (Getter)
     *
	 * @return  Vorgang als nachfolgendes Element.
	 */
	public Vorgang getNach() {
		return nach;
	}

	/**
	 * Weist den Vorgang nach zu. (Setter)
     *
	 * @param  nach Vorgang als nachfolgendes Element .
	 */
	public void setNach(Vorgang nach) {
		this.nach = nach;
	}

}
