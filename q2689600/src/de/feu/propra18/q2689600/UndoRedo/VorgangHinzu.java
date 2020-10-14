package de.feu.propra18.q2689600.UndoRedo;

import de.feu.propra18.q2689600.Datamodel.GuiPoint;
/**
 * Diese Klasse stellt einen Vorgang für das Hinzufügen eines Punktes dar.
 *  
 * @author Manuel Heder
 * 
 */
public class VorgangHinzu extends Vorgang{

	//Punkt der hinzugefügt wurde.
	private GuiPoint hinzu;
	
	/**
	 * Konstruktor; Initialisiert hinzu mit einem Punkt.
     *
     *@param gp GuiPoint als hinzugefügter Punkt.
    */
	public VorgangHinzu(GuiPoint gp) {
		super();
		setHinzu(gp);
	}

	/**
	 * Getter für den hinzugefügten Punkt. (Getter)
     *
	 * @return GuiPoint als hinzugefügtes Element.
	 */
	public GuiPoint getHinzu() {
		return hinzu;
	}

	/**
	 * Weist den GuiPoint hinzu zu. (Setter)
     *
	 * @param  hinzu GuiPoint wird zum hinzugefügten Punkt.
	 */
	public void setHinzu(GuiPoint hinzu) {
		this.hinzu = hinzu;
	}

}
