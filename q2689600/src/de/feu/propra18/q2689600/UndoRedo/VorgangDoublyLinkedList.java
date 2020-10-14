package de.feu.propra18.q2689600.UndoRedo;

/**
 * Diese Klasse stellt eine doppelt verkettete Liste dar, die Vorgangselemente speichert. 
 * Diese dienen als Basis der Undo- und Redofunktion dar.
 *  
 * @author Manuel Heder
 * 
 */
public class VorgangDoublyLinkedList {

	//Referenz auf das erste Element
	private Vorgang start;
	//Referenz auf das aktuelle Element
	private Vorgang current;
	
	/**
	 * Konstruktor; Initialisiert Variablen.
     *
     *@param start Vorgang als erstes Element.
    */
	public VorgangDoublyLinkedList(Vorgang start) {
		this.start=start;
		this.setCurrent(start);
	}

	/**
	 * Konstruktor; Initialisiert Variablen.
     *
    */
	public VorgangDoublyLinkedList() {
		this.start=null;
		this.setCurrent(start);
	}
	
	/**
	 * Löscht alle Elemente in der Liste.
     *
    */
	public void clear() {
		this.start=null;
		this.setCurrent(start);
	}
	
	/**
	 * Weist den Vorgang current zu. (Setter)
     *
	 * @param  v Vorgang wird zum aktuellen Element (current).
	 */
	private void setCurrent(Vorgang v) {
		current=v;
		
	}
	
	/**
	 * Gibt den Vorgang current zurueck. (Getter)
     *
	 * @return  Vorgang als aktuelles Element der Liste.
	 */
	public Vorgang getCurrent() {
		return current;
	}
	
	/**
	 * Gibt den Vorgang start zurueck. (Getter)
     *
	 * @return  Vorgang als erstes Element der Liste.
	 */	
	public Vorgang getStart() {
		return start;
	}

	/**
	 * Weist den Vorgang start zu. (Setter)
     *
	 * @param start Vorgang wird zum ersten Element der Liste.
	 */
	public void setStart(Vorgang start) {
		this.start = start;
	}
	
	/**
	 * Fügt der Liste einen neuen Vorgang zu
     *
     *@param v Vorgang stellt das hinzuzufügende Element dar.
    */
	public void add(Vorgang v) {
		//Prüft ob die Liste leer ist.
		if (start==null ||current==null) {
			start=v;
			current=v;
		} else {	
			current.setNach(v);
			v.setVor(current);
			current=v;
			
		}
	}

	/**
	 * Geht in der Liste ein Element zurück
	 * 
    */
	public void previous() {
		//Prüft ob der Listenanfang bereits erreicht ist.
		if (current!=null) {
			current=current.getVor();
		}
	}
	
	/**
	 * Geht in der Liste ein Element vor.
	 * 
    */
	public void next() {

		//Prüft, ob sich current am Listenanfang befindet und ob es Nachfolgeelement gibt.
		if (current==null) {
			current=start;
		}else  if (current.getNach()!=null) {
			current=current.getNach();
		}
	}


}
