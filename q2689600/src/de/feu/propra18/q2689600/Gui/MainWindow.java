package de.feu.propra18.q2689600.Gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.*;

import de.feu.propra18.q2689600.Datamodel.Datamodel;
import de.feu.propra18.q2689600.Datamodel.GuiPoint;


/**
 * Diese Klasse stellt das Hauptfenster dar. Alle anderen Fenster und Menues sind hier anhaengig.
 * Die Klasse enthaelt auch die fuer die Menues notwendigen Action Listener.
 * 
 * @author Manuel Heder
 * 
 */
public class MainWindow extends JFrame{

	//Referenzen fuer Fenster innerhalb des Hauptfensters
	private Datamodel dm;
	private DrawingWindow dw;
	private AddPointWindow apw;
	private AddPointOverButtonWindow apobw;
	private ButtonWindow bw;
	private UndoRedoWindow urw;
	
	//Referenz zum Schreiben der Datei 
	private File FileToWrite=null;
	
	/**
	 * Konstruktor; Es wird initUI aufgerufen.
     *
	 * @param datamodel  Datamodel stellt das Datenmodel dar.
	 */
    public MainWindow(Datamodel datamodel) {
    	initUI(datamodel);
	}

	/**
	 * Erstellt die Subfenster und Menues und fuegt diese dem Hauptfenster zu. Fuer die einzelnen Menuepunkte werden Listener hinzugefuegt.
     *
	 * @param datamodel  Datamodel stellt das Datenmodel dar.
	 */
	private void initUI(Datamodel datamodel) {
    
    	//Erstellt Referenz zum Datenmodell
		dm=datamodel;
 
		//Hier werden die Unterfester erstellt
    	apw=new AddPointWindow(dm);
    	apobw=new AddPointOverButtonWindow(dm);
    	bw=new ButtonWindow(dm);
    	dw= new DrawingWindow(dm, apw, bw, this);
    	urw=new UndoRedoWindow(dm);

    	//Eine Referenz des Zeichenfensters wird an die Addpoint-Fenster uebergeben.
    	apw.setDataGui(dw);
    	apobw.setDataGui(dw);
    	urw.setDataGui(dw);
		
    	
    	this.setLayout(new BorderLayout(5,5));
    	
    	//Menues werden erstellt.
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("Datei");
        JMenu help = new JMenu("Hilfe");
        
        JMenuItem eMenuItem1 = new JMenuItem("Neu");
        JMenuItem eMenuItem2 = new JMenuItem("Öffnen");
        JMenuItem eMenuItem3 = new JMenuItem("Speichern");
        JMenuItem eMenuItem4 = new JMenuItem("Speichern unter");
        JMenuItem eMenuItem5 = new JMenuItem("Exit");
        
        file.add(eMenuItem1);
        file.add(eMenuItem2);
        file.add(eMenuItem3);
        file.add(eMenuItem4);
        file.add(eMenuItem5);
        
        JMenuItem eMenuItem6 = new JMenuItem("Bedienungsanleitung");
        
        help.add(eMenuItem6);
        
        //Hils- und Dateimenue werden dem Hauptfenster hinzugefuegt.
        menu.add(file);
        menu.add(help);
        
        //Punkte werden geloescht und Zeichenflaeche neu gezeichnet.
        eMenuItem1.addActionListener((ActionEvent event) -> {
            dm.clear();
    		dm.updateDatamodel();
    		dw.calcMapValues();
    		repaint();
        });
              
        //Eine Datei wird geoeffnet und eingelesen. Alte Punkte werden geloescht.
        eMenuItem2.addActionListener((ActionEvent event) -> {
        	
        	JFileChooser chooser = new JFileChooser();
        	File dir=new File("../Tester/data");
			chooser.setCurrentDirectory(dir);
        	int oo=chooser.showOpenDialog(null);

        	//Falls eine Datei ausgewaehlt wurde...
            if(oo == JFileChooser.APPROVE_OPTION)
            {
            	//Alle Punkte werden geloescht. Datenmodel bereinigt.
                dm.clear();
            	FileToWrite =chooser.getSelectedFile();
				try {
					Reader ir = new InputStreamReader(new FileInputStream(FileToWrite), StandardCharsets.UTF_8);	
	            	BufferedReader br=new BufferedReader(ir);
	            	String line=null;
	            	ArrayList<GuiPoint> gpl=new ArrayList<GuiPoint>();
	            	//Zeilenweise wird ausgelesen.
	            	while(br.ready()){
	            		try {
							line=br.readLine();
							String[] Koordinaten=line.split("\\s");
							if (Koordinaten.length==2) {
								int x=Integer.parseInt(Koordinaten[0]);
								int y=Integer.parseInt(Koordinaten[1]);
								if(x<=dm.MaxX && y<=dm.MaxY && x>=dm.MinX && y>=dm.MinX) {
									//Falls maximal akzeptierte Koordinatenwerte nicht ueberschritten werden, wird der Punkt hinzugefuegt.
									GuiPoint dummyPoint=new GuiPoint(x, y);
									gpl.add(dummyPoint);
								}
							}
						} catch (IOException|NumberFormatException e) {

						}
	            	};

	            	datamodel.addPointsFromGuiPointArrayList(gpl);
	                ir.close();
	                br.close();
	                
	                //Punkte, Sweep lines und konvexe Huelle werden berechnet und dargestellt.
	                dm.updateDatamodel();
	                dw.calcMapValues(); //Skalierungsfaktoren werden berechnet.
	                dw.repaint();
				} catch (IOException e1) {
					
				}   

                
                      
            }
        
        });
        
      
        //Punkte werden gespeichert. Datei ist hier bereits ausgewaehlt.
        eMenuItem3.addActionListener((ActionEvent event) -> {
        	try {
        		if (FileToWrite!=null) {
        			Writer fw = new OutputStreamWriter(new FileOutputStream(FileToWrite), StandardCharsets.UTF_8);	
        			
        			//Alle Punkte werden in Datei geschrieben.
        			for (int ii=0; ii<dm.getPointList().size();ii++) {
        				fw.write(Integer.toString(dm.getPointList().get(ii).getX()));
        				fw.write(" ");
        				fw.write(Integer.toString(dm.getPointList().get(ii).getY()));
        				fw.write(System.getProperty("line.separator"));
        			}
        			fw.flush();
        			fw.close();
        		} else {
        			JOptionPane.showMessageDialog(null, "Keine Datei ausgewaehlt.", "Fehler", JOptionPane.INFORMATION_MESSAGE);
        		}
			} catch (IOException e) {

			}
        	
        });
        
      //Punkte werden gespeichert. Benutzer waehlt dazu vorher eine Datei aus.
        eMenuItem4.addActionListener((ActionEvent event) -> {
        	JFileChooser chooser = new JFileChooser();
        	String filename="../Tester/data";
        	File dir=new File(filename);
			chooser.setCurrentDirectory(dir);
        	chooser.showSaveDialog(null);
        	FileToWrite =chooser.getSelectedFile();
        	try {
        		if (FileToWrite!=null) {
        			Writer fw = new OutputStreamWriter(new FileOutputStream(FileToWrite), StandardCharsets.UTF_8);	
        			

        			//Alle Punkte werden in Datei geschrieben.
        			for (int ii=0; ii<dm.getPointList().size();ii++) {
        				fw.write(Integer.toString(dm.getPointList().get(ii).getX()));
        				fw.write(" ");
        				fw.write(Integer.toString(dm.getPointList().get(ii).getY()));
        				fw.write(System.getProperty("line.separator"));
        			}
        			fw.flush();
        			fw.close();
        		} else {
        			JOptionPane.showMessageDialog(null, "Keine Datei ausgewaehlt.", "Fehler", JOptionPane.INFORMATION_MESSAGE);
        		}
			} catch (IOException e) {

			}     
        });
        
        //Programm wird verlassen und die VM beendet.
        eMenuItem5.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        //Die Bedienungsanleitung wird angezeigt.
        eMenuItem6.addActionListener((ActionEvent event) -> {
            InstructionsWindow iw=new InstructionsWindow();

        });

 

        //Hauptfenster wird definiert
        setJMenuBar(menu);
        setTitle("KH-Berechner - Manuel Heder - 2689600");
        setSize(920, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       

        //Zeichenebene wird dem Hauptfenster hinzugefuegt.
    	this.add(dw, BorderLayout.CENTER);
    	dw.setVisible(true);
    	
    	//Panle jp wird erzeugt, um dort alle anderen Komponenten und Fenster zusammenzufassen und gemeinsam dem Hauptfesnter hinzuzufuegen.
    	JPanel jp=new JPanel();
    	jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
    	
    	//Placeholder
    	JPanel jp1=new JPanel();
    	jp1.setSize(40,50);
    	JPanel jp2=new JPanel();
    	jp2.setSize(40,50);
    	JPanel jp3=new JPanel();
    	jp3.setSize(40,50);

    	//Subfenster werden dem jp Panel hinzugefuegt
    	apw.setAlignmentY(TOP_ALIGNMENT);
    	jp.add(urw);
    	jp.add(jp1);
    	jp.add(apw);
    		
       	jp.add(jp2);

       	jp.add(apobw);
    	
       	jp.add(jp3);
       	
    	jp.add(bw);
    	
    	jp.setSize(370,600);
        jp.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    	this.add(jp, BorderLayout.EAST);
    	
    	pack();
    }
   
}

