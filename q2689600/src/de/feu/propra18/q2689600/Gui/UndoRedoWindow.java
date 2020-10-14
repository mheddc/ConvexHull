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
 * Diese Klasse stellt Knoepfe zur Verfuegung mit der die Undo- und Redooperation durchgeführt werden können.
 *  
 * @author Manuel Heder
 * 
 */


public class UndoRedoWindow extends JPanel implements ActionListener{


	//Knoepfe für Undo- und Redooperationen
	private JButton Undo;
	private JButton Redo;
	
	//Referenzen fuer Datenmodell und Zeichenflaeche
	private Datamodel dm;
	private DrawingWindow dw;
	
	/**
	 * Konstuktor; Definiert Aussehen und Groeße des gesamtem JPanels und der Knoepfe und erzeugt diese.
     * Die Action Listener für die Knoepfe werden hier ebenfalls hinzugefuegt.
     *
	 * @param dm2  Datamodel stellt das Datenmodel dar.
	 */
	public UndoRedoWindow(Datamodel dm2) {
		//Datenmodellreferenz wird abgespeichert
		this.dm=dm2;
		//JPanel Aussehen
		this.setAlignmentY(CENTER_ALIGNMENT);;
		setBorder(BorderFactory.createLineBorder(Color.black));
    	setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	
    	
		JLabel erklaerung1=new JLabel("Undo<>Redo");
		erklaerung1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(erklaerung1);
		
		JPanel jp=new JPanel();
		jp.setLayout(new BoxLayout(jp,BoxLayout.X_AXIS));
		
		
		this.add(jp);
		this.setSize(300,80);
		
		//Knoepfe fuer die Undo- und Redooperation wird hinzugefügt
		Undo = new JButton(" < ");
		jp.add(Undo);
		Undo.addActionListener(this);

		Redo = new JButton(" > ");
		jp.add(Redo);
		Redo.addActionListener(this);
		
	}
	
	/**
	 *Methode steuert Funktionalitaet der Knoepfe nach deren Auswahl.
     *Führt Redo- oder Undooperation durch und löst eine Neuberechnung und Neuziechnung aus.
     *
	 * @param e  Actionevent um herauszufinden, welcher Knopf geklickt wurde.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Quelle wird bestimmt und die gewünschte Opertaion druchgeführt.
		if (e.getSource().equals(Undo)){
				dm.undo();
			}else if (e.getSource().equals(Redo)){
				dm.redo();
		};
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
