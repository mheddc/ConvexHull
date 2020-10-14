package de.feu.propra18.q2689600.Gui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import de.feu.propra18.q2689600.Datamodel.Datamodel;

/**
 * Diese Klasse stellt Knoepfe zum hinzufuegen, 
 * loeschen und verschieben von Punkten zur Verfügung. Nach Aktivierung 
 * eines dieser Knoepfe koennen Punkte auf der Zeichenflaeche manipuliert bzw. hinzugefügt werden.
 * 
 * @author Manuel Heder
 * 
 */

public class ButtonWindow extends JPanel implements ActionListener{

	//Buttons
	private JToggleButton Hinzu;
	private JToggleButton Loeschen;
	private JToggleButton Verschieben;
	
	//Boolean um Aktivierung der Knoepfe abzufragen
	private boolean HinzuSchalter;
	private boolean VerschiebeSchalter;
	private boolean LoeschenSchalter;
	
	//Referenz zum Datenmodell
	Datamodel dm;
	
	/**
	 * Konstuktor; Definiert Aussehen und Groeße des gesamtem JPanels und der Knoepfe.
     *
	 * @param dm2  Datamodel stellt das Datenmodel dar.
	 */
	public ButtonWindow(Datamodel dm2) {
		//Referenz fuer das Datenmodell wird gespeichert
		dm=dm2;
		this.setAlignmentY(CENTER_ALIGNMENT);;
		setBorder(BorderFactory.createLineBorder(Color.black));
    	setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	
    	//ERstellt Beschreibung und fuegt sie dem JPAnel zu.
		JLabel erklaerung1=new JLabel("Zum Hinzufuegen, Verschieben oder Loeschen");
		JLabel erklaerung2=new JLabel("Schalter aktivieren");
		erklaerung1.setAlignmentX(CENTER_ALIGNMENT);
		erklaerung2.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(erklaerung1);
		this.add(erklaerung2);
		
		
		//Erstellen des "hinzufuegen"-Buttons
		Hinzu = new JToggleButton("Punkte hinzufuegen");
		Hinzu.setAlignmentX(CENTER_ALIGNMENT);
		this.add(Hinzu);
		Hinzu.addActionListener(this);

		//Erstellen des "Verschieben"-Buttons
		Verschieben = new JToggleButton("Punkte verschieben");
		Verschieben.setAlignmentX(CENTER_ALIGNMENT);
		this.add(Verschieben);
		Verschieben.addActionListener(this);

		//Erstellen des "Loeschen"-Buttons
		Loeschen = new JToggleButton("Punkte loeschen");
		Loeschen.setAlignmentX(CENTER_ALIGNMENT);
		this.add(Loeschen);
		Loeschen.addActionListener(this);


	}
	


	/**
	 *Methode steuert die Funktionalitaet der Knoepfe. Der Status der Auswahl wird über Boolean-Variablen gespeichert.
     *Wird ein Knopf ausgewaehlt, so werden die anderen deaktiviert.
     *
	 * @param e  Actionevent um herauszufinden, welcher Knopf geklickt wurde.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// "Hinzu-Schalter" wird aktiviert. Alle anderen werden deaktiviert.
		if (e.getSource()==Hinzu) {
			HinzuSchalter=Hinzu.isSelected();
			Verschieben.setSelected(false);
			VerschiebeSchalter=false;
			Loeschen.setSelected(false);
			LoeschenSchalter=false;
		}
		//"Verschiebeschalter" wird aktiviert. Alle anderen werden deaktiviert.
		if (e.getSource()==Verschieben) {
			VerschiebeSchalter=Verschieben.isSelected();
			Hinzu.setSelected(false);
			HinzuSchalter=false;
			Loeschen.setSelected(false);
			LoeschenSchalter=false;
		}
		//"Loeschschalter" wird aktiviert. Alle anderen werden deaktiviert.
		if (e.getSource()==Loeschen) {
			LoeschenSchalter=Loeschen.isSelected();
			Hinzu.setSelected(false);
			HinzuSchalter=false;
			Verschieben.setSelected(false);
			VerschiebeSchalter=false;
		}
	}


	/**
	 * Getter fuer den Hinzu-Schalter.
     *
     *@return HinzuSchalter Boolean der anzeigt, ob der Knopf angeklickt wurde.
	 */
	public boolean isHinzuSchalter() {
		return HinzuSchalter;
	}



	/**
	 * Getter fuer den Verschiebe-Schalter.
     *
     *@return VerschiebeSchalter Boolean der anzeigt, ob der Knopf angeklickt wurde.
	 */
	public boolean isVerschiebeSchalter() {
		return VerschiebeSchalter;
	}



	/**
	 * Getter fuer LoeschenSchalter.
     *
     *@return LoeschenSchalter Boolean der anzeigt, ob der Knopf angeklickt wurde.
	 */
	public boolean isLoeschenSchalter() {
		return LoeschenSchalter;
	}

	

}
