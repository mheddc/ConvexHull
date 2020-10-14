package de.feu.propra18.q2689600.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.feu.propra18.q2689600.Datamodel.Datamodel;
import de.feu.propra18.q2689600.Datamodel.GuiPoint;

/**
 * Diese Klasse stellt ein Textfeld zur Verfuegung, ueber das eine Anzahl von Punkten eingegeben (1-9999) werden kann. 
 * Diese werden dann mit zufälligen Koordinaten erstellt und auf der Zeichenflaeche dargestellt.
 *  
 * @author Manuel Heder
 * 
 */
public class AddPointWindow extends JPanel implements ActionListener{

	private JTextField textField;
	private JButton PunkteHinzu;
	
	private Datamodel dm;
	private DrawingWindow dw;
	
	
	/**
	 * Konstuktor; Definiert Aussehen und Groeße des gesamtem JPanels, des Textfelds und der Knoepfe.
     *
	 * @param dm2  Datamodel stellt das Datenmodel dar.
	 */
	public AddPointWindow(Datamodel dm2) {
		
		//speichert Referenz des Datenmodells
		this.dm=dm2;
		
		//Aussehen des JPAnels
		this.setAlignmentY(CENTER_ALIGNMENT);;
		setBorder(BorderFactory.createLineBorder(Color.black));
    	setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	
    	//Beschreibung erstellen und hinzufuegen zum JPanel
		JLabel erklaerung1=new JLabel("Geben Sie bitte die Anzahl");
		JLabel erklaerung2=new JLabel("der hinzuzufuegenden Punkte");
		JLabel erklaerung3=new JLabel("ein (1-9999):");
		erklaerung1.setAlignmentX(CENTER_ALIGNMENT);
		erklaerung2.setAlignmentX(CENTER_ALIGNMENT);
		erklaerung3.setAlignmentX(CENTER_ALIGNMENT);
		this.add(erklaerung1);
		this.add(erklaerung2);
		this.add(erklaerung3);
		
		
		//Erzeugung und Definition des Textfelds
		textField = new JTextField(10);
		textField.setAlignmentX(CENTER_ALIGNMENT);
		textField.setMaximumSize(new Dimension(150,20));
		textField.addActionListener(this);//ActionListener fuer Returneingabe
		this.add(textField);	
		
		
		//Knopf zum Bestaetigen der Eingabe
		PunkteHinzu = new JButton("Punkte hinzufuegen");
		PunkteHinzu.setAlignmentX(CENTER_ALIGNMENT);
		this.add(PunkteHinzu);
		PunkteHinzu.addActionListener(this);


	}
	
	/**
	 *Methode steuert die Funktionalitaet des Knopfes.
     *Geforderte Anzahl von Punkten mit zufälligen Koordinaten wird erzeugt und der Punktmenge hinzugefuegt.
     *
	 * @param e  Actionevent um Textfeld einzulesen
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Textfeld wird ausgelesen
		String test= textField.getText();

		//Falls es nicht leer ist und es sich um Zahlen handelt
		if(!test.equals("") && test.chars().allMatch(Character::isDigit)) {
			int input=Integer.parseInt(test);
			if (input<10000 && input>0) {
				ArrayList<GuiPoint> gpl=new ArrayList<GuiPoint>();
				//Zufaellige Punkte werden erzeugt
				for (int ii=1; ii<=input;ii++) {
						int x=(int)((dw.getDim()-dw.getBounderydiff())*Math.random()+dw.getLowerBoundaryx()+dw.getBounderydiff()/2);
						int y=(int)((dw.getDim()-dw.getBounderydiff())*Math.random()+dw.getLowerBoundaryy()+dw.getBounderydiff()/2);
						GuiPoint dumyPoint=new GuiPoint(x,y);
						gpl.add(dumyPoint);
				}
				dm.addPointsFromGuiPointArrayList(gpl);
				//Datenmodell wird aktualisiert und Zeichnflaeche neu gezeichnet
				dm.updateDatamodel();
				dw.repaint();
			}

		};
		textField.setText("");

		
	}

	/**
	 *Setter fuer die Referenz auf das Zeichenfenster.
     *
	 * @param dw  DrawingWindow wird gesetzt.
	 */
	public void setDataGui(DrawingWindow dw) {
		// TODO Auto-generated method stub
		this.dw=dw;
	}
	

}
