package de.feu.propra18.q2689600.Gui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.feu.propra18.q2689600.Datamodel.Datamodel;
import de.feu.propra18.q2689600.Datamodel.GuiPoint;

/**
 * Diese Klasse stellt Knoepfe zur Verfuegung, mit der wahlweise 10, 50, 100, 500 oder 1000 zufaellige Punkte
 * auf der Zeichenflaeche erstellt werden koennen.
 *  
 * @author Manuel Heder
 * 
 */


public class AddPointOverButtonWindow extends JPanel implements ActionListener{


	//Knoepfe ueber die zufaellige Punte der Zeichenflaeche hinzugefuegt werden koennen.
	private JButton PunkteHinzu10;
	private JButton PunkteHinzu50;
	private JButton PunkteHinzu100;
	private JButton PunkteHinzu500;
	private JButton PunkteHinzu1000;
	
	//Referenzen fuer Datenmodell und Zeichenflaeche
	private Datamodel dm;
	private DrawingWindow dw;
	
	/**
	 * Konstuktor; Definiert Aussehen und Groeße des gesamtem JPanels und der Knoepfe und erzeugt diese.
     * Die Action Listener für die Knoepfe werden hier ebenfalls hinzugefuegt.
     *
	 * @param dm2  Datamodel stellt das Datenmodel dar.
	 */
	public AddPointOverButtonWindow(Datamodel dm2) {
		//Datenmodellreferenz wird abgespeichert
		this.dm=dm2;
		//JPanel Aussehen
		this.setAlignmentY(CENTER_ALIGNMENT);;
		setBorder(BorderFactory.createLineBorder(Color.black));
    	setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	
    	
		JLabel erklaerung1=new JLabel("Zufaellige Punkte hinzufuegen");
		erklaerung1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(erklaerung1);
		
		JPanel jp=new JPanel();
		jp.setLayout(new BoxLayout(jp,BoxLayout.X_AXIS));
		
		
		this.add(jp);
		this.setSize(300,80);
		
		//Knoepfe fuer Punkte werden erstellt.
		PunkteHinzu10 = new JButton(" 10 ");
		jp.add(PunkteHinzu10);
		PunkteHinzu10.addActionListener(this);

		PunkteHinzu50 = new JButton(" 50 ");
		jp.add(PunkteHinzu50);
		PunkteHinzu50.addActionListener(this);
		
		PunkteHinzu100 = new JButton(" 100");
		jp.add(PunkteHinzu100);
		PunkteHinzu100.addActionListener(this);
		
		PunkteHinzu500 = new JButton(" 500");
		jp.add(PunkteHinzu500);
		PunkteHinzu500.addActionListener(this);
		
		PunkteHinzu1000 = new JButton("1000");
		jp.add(PunkteHinzu1000);
		PunkteHinzu1000.addActionListener(this);

	}
	
	/**
	 *Methode steuert Funktionalitaet der Knoepfe nach deren Auswahl.
     *Die geforderte Anzahl von Punkten wird erzeugt und der Punktmenge hinzugefuegt.
     *
	 * @param e  Actionevent um herauszufinden, welcher Knopf geklickt wurde.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int input=0;
		
		//Anzahl der zuzufuegenden Punkte wird bestimmt
		if (e.getSource().equals(PunkteHinzu10)){
				input=10;
			}else if (e.getSource().equals(PunkteHinzu50)){
				input=50;
			}else if (e.getSource().equals(PunkteHinzu100)){
				input=100;
			}else if (e.getSource().equals(PunkteHinzu500)){
				input=500;	
			}else if (e.getSource().equals(PunkteHinzu1000)){
				input=1000;
		};
		
		ArrayList<GuiPoint> gpl=new ArrayList<GuiPoint>();
		//Zufaellige Punkte werden erzeugt und der Punktmenge hinzugefuegt
		
		for (int ii=1; ii<=input;ii++) {
			int x=(int)((dw.getDim()-dw.getBounderydiff())*Math.random()+dw.getLowerBoundaryx()+dw.getBounderydiff()/2);
			int y=(int)((dw.getDim()-dw.getBounderydiff())*Math.random()+dw.getLowerBoundaryy()+dw.getBounderydiff()/2);
			gpl.add(new GuiPoint(x,y));
		}
		
		dm.addPointsFromGuiPointArrayList(gpl);
		//Neuberechnung und Darstellung der Punktmenge
		dm.updateDatamodel();
		dw.repaint();
	
	}

	/**
	 *Setter fuer die Referenz auf das Zeichenfenster.
     *
	 * @param dw  DrawingWindow wird gesetzt.
	 */
	public void setDataGui(DrawingWindow dw) {
		this.dw=dw;
	}
	

}
