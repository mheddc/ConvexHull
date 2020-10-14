package de.feu.propra18.q2689600.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import de.feu.propra18.q2689600.Datamodel.Datamodel;
import de.feu.propra18.q2689600.Datamodel.GuiPoint;


/**
 * Diese Klasse stellt die Zeichenflaeche dar. Alle Punkte, das Konturpolynom und die konvexe Huelle werden hier 
 * dargestellt.
 * 
 * 
 * @author Manuel Heder
 * 
 */
public class DrawingWindow extends JPanel{

	
	//Referenz fuer Punktliste des Datamodels
	private ArrayList<GuiPoint> pointList;
	
	//Referenzen fuer Fenster
	private Datamodel dm;
	private AddPointWindow apw;
	private ButtonWindow bw;
	private MainWindow mw;
	
	//Groeße des Fensters
	private int MaxXSize=600;
	private int MaxYSize=600;
	
	//Werte die fuer die Skalierung verwendet werden.
	private long lowerBoundaryx;
	private long lowerBoundaryy;
	private Double scalef;
	private long dim;
	private double activationRadius;
	private long bounderydiff;
	
	
	
	/**
	 * Konstruktor; Speichert Referenzen auf das Datenmodell und Komponenten durch einen weiteren Methodenaufruf auf InitUI.
     *
	 * @param dm2  Datamodel Stellt das Datenmodel dar.
	 * @param apw  AddPointWindow stellt das Fenster zum hinzufuegen von Punkten dar.
	 * @param bw   ButtonWindow stellt das Fenster zum Hinzufuegen, Loeschen und Verschieben von Punkten dar.
     * @param mw   MainWindow stellt das Hauptfenster dar.
	 */
    public DrawingWindow(Datamodel dm2, AddPointWindow apw, ButtonWindow bw, MainWindow mw) {

        initUI(dm2, apw, bw, mw);
    }

    
    
	/**
	 * Initialisiert die Zeichenflaeche und teilt Referenzen zu.
     *
	 * @param dm2  Datamodel Stellt das Datenmodel dar.
	 * @param apw  AddPointWindow stellt das Fenster zum hinzufuegen von Punkten dar.
	 * @param bw   ButtonWindow stellt das Fenster zum Hinzufuegen, Loeschen und Verschieben von Punkten dar.
	 * @param mw   MainWindow stellt das Hauptfenster dar.
	 */
    private void initUI(Datamodel dm2, AddPointWindow apw, ButtonWindow bw, MainWindow mw) {
    
    	//Fuegt die Referenzen hinzu.
    	dm=dm2;
    	pointList=dm.getPointList();
    	this.bw=bw;
    	this.apw=apw;
    	this.mw=mw;

    	//Initialisiert die Werte zur Berechnung der Skalierung etc.
    	calcMapValues();
    	
    	//Setzt Groeße und Rand des Zeichnungsfensters fest.
    	setMinimumSize(new Dimension(MaxXSize, MaxYSize));
    	setPreferredSize(new Dimension(MaxXSize, MaxYSize));
    	Border border=BorderFactory.createLineBorder(Color.black);
    	Border eborder=BorderFactory.createEmptyBorder(3, 3, 3, 3);
    	setBorder(BorderFactory.createCompoundBorder(eborder,border));
    	
    	//Fuegt Listener fuer Maus-Events hinzu.
        addMouseListener(new MouseAdapterext());
        addMouseMotionListener(new MouseAdapterext());
    }
    
    
    
	/**
	 * Zeichnet die Punkte, Konturpolynome und konvexe Huelle auf die Zeichenflaeche. Die Zeichenflaeche passt sich der Groeße des Hauptfensters an.
     *
	 * @param g  Graphics-Obejct.
	 */
    public void paintComponent(Graphics g) {
    	
    	//Stellt die aktuelle Groeße des Hauptfensters fest. Die Zeichenflaeche passt sich an.
    	MaxXSize=(int) (mw.getContentPane().getWidth()-340);
    	MaxYSize=mw.getContentPane().getHeight();
    	
    	//Der kleinere Wert, jedoch hoechstens 600 Pixel bestimmen die Groeße der Zeichenflaeche.
    	int min=Math.min(MaxXSize, MaxYSize);
    	MaxXSize=Math.min(min, 600);
    	MaxYSize=MaxXSize;
    	this.setSize(MaxXSize, MaxYSize);
    	
    	//Hier werden die Skalierungswerte berechnet
    	calcMapValues();
    	
    	//Punkte, Konturpolynom und konvexe Huelle werden gezeichnet.
    	super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGrid(g2);
        drawPoints(g2);
        //drawKonturpolynom(g2);
        drawConvexpolynom(g2);
        drawGEK(g2);

    }
    
    /**
	 * Zeichnet die Punkte auf die Zeichenflaeche.
     *
	 * @param g2  Graphics2D-Obejct.
	 */
    private void drawGEK(Graphics2D g2) {
    	
    	if (!pointList.isEmpty()) {
	    	g2.setColor(Color.RED);
	    	//Koordinatentransformation vor dem Zeichnen auf die Zeichenflaeche.
	    	GuiPoint p=mapPointToPane(dm.getGEKCenterPoint());
	    	int radius=(int) Math.round(dm.getGEKRadius()/scalef);
	    	g2.drawOval(p.getX()-radius, p.getY()-radius, radius*2, radius*2);
	    		
	    	
	    	
	    	 //Zeichnet Ursprung und Extrempunkte
	    	GuiPoint pDummy;

	    	g2.setColor(Color.MAGENTA);
	    	pDummy=mapPointToPane(dm.getGEKCenterPoint());
	    	g2.fillOval(pDummy.getX()-4, pDummy.getY()-4, 8, 8);

    	}
    }
    
	/**
	 * Zeichnet die Punkte auf die Zeichenflaeche.
     *
	 * @param g2  Graphics2D-Obejct.
	 */
    private void drawGrid(Graphics2D g2) {

	    	g2.setColor(Color.black);
	    	if (lowerBoundaryx<0) {
	    	
	    		for (int ii=0; ii<50;ii++) {
	    			g2.drawLine(300,ii*12+3, 300, ii*12+8);
	    		}
	    	}
	    	if (lowerBoundaryy<0) {
		    	
	    		for (int ii=0; ii<50;ii++) {
	    			g2.drawLine(ii*12+3,300,ii*12+8, 300);
	    		}
	    	}
	    	
    }
    
	/**
	 * Zeichnet die Punkte auf die Zeichenflaeche.
     *
	 * @param g2  Graphics2D-Obejct.
	 */
    private void drawPoints(Graphics2D g2) {
    	
    	if (!pointList.isEmpty()) {
	    	g2.setColor(Color.RED);
	    	for (int ii=0; ii<pointList.size(); ii++) {
	    		//Koordinatentransformation vor dem Zeichnen auf die Zeichenflaeche.
	    		GuiPoint p=mapPointToPane(pointList.get(ii));
	    		g2.drawOval(p.getX()-2, p.getY()-2, 4, 4);
	    		
	    	}
	    	
	    	
	    	 //Zeichnet Ursprung und Extrempunkte
	    	GuiPoint pDummy;
	    	
	    	g2.setColor(Color.MAGENTA);
	    	GuiPoint origin=new GuiPoint(0,0);
	    	GuiPoint p=mapPointToPane(origin);
	    	g2.fillOval(p.getX()-2, p.getY()-2, 4, 4);
	    	
	    	g2.setColor(Color.MAGENTA);
	    	pDummy=mapPointToPane(dm.getTPoint());
	    	g2.fillOval(pDummy.getX()-4, pDummy.getY()-4, 8, 8);
	    	
	    	g2.setColor(Color.MAGENTA);
	    	pDummy=mapPointToPane(dm.getBPoint());
	    	g2.fillOval(pDummy.getX()-4, pDummy.getY()-4, 8, 8);
	    	
	    	g2.setColor(Color.MAGENTA);
	    	pDummy=mapPointToPane(dm.getLPoint());
	    	g2.fillOval(pDummy.getX()-4, pDummy.getY()-4, 8, 8);
	    	
	    	g2.setColor(Color.MAGENTA);
	    	pDummy=mapPointToPane(dm.getRPoint());
	    	g2.fillOval(pDummy.getX()-4, pDummy.getY()-4, 8, 8);
    	}
    }
    
    
    
	/**
	 * Berechnet Skalierungsfaktoren, so dass alle Punkte auf der Zeichenflaeche dargestellt werden koennen.
	 * Ein Rand von 5% der Maximaldistanz zwischen Extrempunkten wird dabei um die Punktmenge erzeugt.
     *
	 */
    public void calcMapValues() {
    	
    	//Initialisierung .
    	Double scalefx=1.;
    	Double scalefy=1.;
    	scalef=1.;
    	long dimx=600;
    	long dimy=600;
    	dim=600;
    	lowerBoundaryx=0;
    	lowerBoundaryy=0;
    	activationRadius=Math.round(scalef)*4;
    	
    	//Falls es mehr als zwei Punkte in der Punkteliste gibt werden die Skalierungswerte neu berechnet.
    	if (pointList.size()>1) {
 
    		//Maximale Distanzen zwischen den Extrmpunkten.
    		dimx=(long)dm.getRPoint().getX()-(long)dm.getLPoint().getX();
    		dimy=(long)dm.getTPoint().getY()-(long)dm.getBPoint().getY();
    		
    		//Dient zur Berechnung eines Randes um die Punktmenge von 5%.
    		bounderydiff=(long)Math.max(dimx*0.05, dimy*0.05);
    		dimx=dimx+bounderydiff;
    		dimy=dimy+bounderydiff;
    		if(dimx<1)dimx=1;
    		if(dimy<1)dimy=1;
    		dim=Math.max(dimx, dimy);	//Groeße der Zeichenflaeche
    		
    		//Skalierungsfaktor von berechneter Groeße der Zeichenflaeche auf tatsaechliche Pixel der Zeichenflaeche.
    		scalefx= ((double)(dimx)/(double)(MaxXSize));
    		scalefy= ((double)(dimy)/(double)(MaxYSize));
    		scalef=Math.max(scalefx, scalefy);  //Einheiten pro Pixel

    		//Hier wird der Aktivierungsradius festgelegt, innerhalb dessen Punkte verschoben oder geloescht werden koennen.
    		activationRadius=scalef*6;
    		
    		//Untere Grenze der Punktwerte in x- und y-Richtung abzueglich des Randes.
    		lowerBoundaryx=dm.getLPoint().getX()-bounderydiff/2;
    		lowerBoundaryy=dm.getBPoint().getY()-bounderydiff/2;
    	}	
    }
    
    
    
    
	/**
	 * Transformiert die "echten" Koordinaten in Koordinaten der Zeichenflaeche.
     *
	 * @param gp  GuiPoint mit (echten) Koordinaten.
	 * @return ngp  GuiPoint mit (Zeichen)Koordinaten.
	 */
    private GuiPoint mapPointToPane(GuiPoint gp) {
    	
    	GuiPoint ngp=new GuiPoint();
    	
    	long xFromBoundary=gp.getX()-lowerBoundaryx;	
    	long yFromBoundary=gp.getY()-lowerBoundaryy;	
    		
    	ngp.setX((int) (xFromBoundary/scalef));
    	ngp.setY((int) (MaxYSize-(yFromBoundary/scalef)));
    	return ngp;
    		
    }
    
    
    
    
	/**
	 * Transformiert die "Zeichen"-Koordinaten eines Klicks in "echte" Koordinaten.
	 * 
     *
	 * @param gp  GuiPoint mit (Zeichen) Koordinaten.
	 * @return ngp  GuiPoint mit (Echten)Koordinaten.
	 */
    public GuiPoint mapClickToPointCoordinates(GuiPoint gp) {

    	//Abstaende von den Zeichenflaechengrenzen
    	long leftDistance=gp.getX();
    	long lowerDistance=MaxYSize-gp.getY();
    	
    	//Skalierung plus untere Grenzwerte
    	long xValue=(long) (leftDistance*scalef+lowerBoundaryx);
    	long yValue=(long) (lowerDistance*scalef+lowerBoundaryy);
    	
    	GuiPoint ngp=new GuiPoint();
	
    	//Falls die Werte die akzeptierten Maximalwerte des Datenmodells ueberschreiten.
   		if(xValue>dm.MaxX)xValue=dm.MaxX;
   		if(xValue<dm.MinX)xValue=dm.MinX;
   		if(yValue>dm.MaxY)yValue=dm.MaxY;
   		if(yValue<dm.MinY)yValue=dm.MinY;
   		
   		//Rueckgabepunkt wird erzeugt und zurueckgegeben.
   		ngp.setX((int)xValue);
    	ngp.setY((int)yValue);
    	return ngp;

    }
    
   
    
    
/*	/**
	 * Zeichnet das Konturpolynom auf die Zeichenflaeche.
     *
	 * @param g2  Graphics2D-Obejct.
	 *//*
    public void drawKonturpolynom(Graphics2D g2){
    	
    	//Dummypunkte zum Zeichnen
    	GuiPoint p1, p2;    	
    	
    	
    	if (!pointList.isEmpty()) {
        	
        	//Schleife fuer erste Sweep line in rot
    		g2.setColor(Color.RED);
	        p2=mapPointToPane(dm.getUpperSweepLineLeft().get(0));
	    	for (int ii=1; ii<dm.getUpperSweepLineLeft().size(); ii++) {
	    		p1=p2;
	    		p2=mapPointToPane(dm.getUpperSweepLineLeft().get(ii));
	    		g2.drawLine(p1.getX(), p1.getY(),p2.getX(),p2.getY());
	    		
	    	}
	    	
	    	//Schleife fuer zwete Sweep line in gruen
	    	g2.setColor(Color.GREEN);
	        p2=mapPointToPane(dm.getLowerSweepLineLeft().get(0));
	    	for (int ii=1; ii<dm.getLowerSweepLineLeft().size(); ii++) {
	    		p1=p2;
	    		p2=mapPointToPane(dm.getLowerSweepLineLeft().get(ii));
	    		g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
	    	}
	    	
	    	//Schleife fuer dritte Sweep line in blau
	    	g2.setColor(Color.BLUE);
	        p2=mapPointToPane(dm.getUpperSweepLineRight().get(0));
	    	for (int ii=1; ii<dm.getUpperSweepLineRight().size(); ii++) {
	    		p1=p2;
	    		p2=mapPointToPane(dm.getUpperSweepLineRight().get(ii));
	    		g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
	    	}
	    	
	    	//Schleife fuer vierte Sweep line in gelb
	    	g2.setColor(Color.YELLOW);
	        p2=mapPointToPane(dm.getLowerSweepLineRight().get(0));
	    	for (int ii=1; ii<dm.getLowerSweepLineRight().size(); ii++) {
	    		p1=p2;
	    		p2=mapPointToPane(dm.getLowerSweepLineRight().get(ii));
	    		g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
	    	}

    	}
    }*/
    

    
    
    
	/**
	 * Zeichnet die konvexe Huelle auf die Zeichenflaeche.
     *
	 * @param g2  Graphics2D-Obejct.
	 */
    public void drawConvexpolynom(Graphics2D g2){
    	
    	//Dummypunkte zum Zeichnen
    	GuiPoint p1, p2;    	
    	
    	
    	
    	if (!pointList.isEmpty()) {
    		
    		//Schleife fuer erste konvexe Sweep line in schwarz
    		g2.setColor(Color.BLACK);
    		p2=mapPointToPane(dm.getConvexpolynom().get(0));
    		for (int ii=1; ii<dm.getConvexpolynom().size(); ii++) {
    			p1=p2;
    			p2=mapPointToPane(dm.getConvexpolynom().get(ii));
    			g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    		}
    		p1=p2;
			p2=mapPointToPane(dm.getConvexpolynom().get(0));
			g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    		
/*    		//Schleife fuer erste konvexe Sweep line in schwarz
    		g2.setColor(Color.BLACK);
    		p2=mapPointToPane(dm.getUpperConvexSweepLineLeft().get(0));
    		for (int ii=1; ii<dm.getUpperConvexSweepLineLeft().size(); ii++) {
    			p1=p2;
    			p2=mapPointToPane(dm.getUpperConvexSweepLineLeft().get(ii));
    			g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    		}
    	
    		//Schleife fuer zweite konvexe Sweep line in schwarz
    		p2=mapPointToPane(dm.getLowerConvexSweepLineLeft().get(0));
    		for (int ii=1; ii<dm.getLowerConvexSweepLineLeft().size(); ii++) {
    			p1=p2;
    			p2=mapPointToPane(dm.getLowerConvexSweepLineLeft().get(ii));
    			g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    		}
    	
    		//Schleife fuer dritte konvexe Sweep line in schwarz
    		p2=mapPointToPane(dm.getUpperConvexSweepLineRight().get(0));
    		for (int ii=1; ii<dm.getUpperConvexSweepLineRight().size(); ii++) {
    			p1=p2;
    			p2=mapPointToPane(dm.getUpperConvexSweepLineRight().get(ii));
    			g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    		}
    	
    		//Schleife fuer vierte konvexe Sweep line in schwarz
    		p2=mapPointToPane(dm.getLowerConvexSweepLineRight().get(0));
    		for (int ii=1; ii<dm.getLowerConvexSweepLineRight().size(); ii++) {
    			p1=p2;
    			p2=mapPointToPane(dm.getLowerConvexSweepLineRight().get(ii));
    			g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    		}*/
    	}
    }
    
    
    
    
    /**
     * Diese innere Klasse haendelt die Mausklicks auf der Zeichenflaeche.
     * 
     * 
     * @author Manuel Heder
     * 
     */
    class MouseAdapterext extends MouseAdapter { 	
    	
    	/**
    	 * Haendelt das Verschieben eines Punktes auf der Zeichenflaeche. 
         *
    	 * @param e  MouseEvent.
    	 */
    	public void mouseDragged(MouseEvent e){
        	if (bw.isVerschiebeSchalter()) {
        		//Falls es einen aktivierten Punkt gibt und di Grenzen der Zeichenflaeche nicht ueberschritten werden..
	    		if(dm.getActivatedPoint()!=null && e.getX()<MaxXSize+1 && e.getY()<MaxYSize-1 && e.getX()>0 && e.getY()>0) {
	    			//erzeugt Punkt mit Zeichnekoordinaten
	    			GuiPoint dp= new GuiPoint(e.getX(), e.getY());
	    			//transformiert Punkt in echte Koordinaten
	    			dp=mapClickToPointCoordinates(dp);
	    			//Aktualisiert die Koordinaten des Punktes.
	    			dm.getActivatedPoint().setX(dp.getX());
	    			dm.getActivatedPoint().setY(dp.getY());
	    			dm.updateDatamodel();
	    		}
	    		repaint();
        	}
    	}
    	
    	
    	
    	
    	/**
    	 *Falls ein Punkt verschoben, hinzugefuegt oder geloescht werden soll, wird hier der entsprechnede Punkt bestimmt.
         *Dazu werden die Koordinaten des Mausklicks in echte Koordinaten umgerechnet und mit der Punktmenge verglichen.
         *
    	 * @param e MouseEvent
    	 */
        public void mousePressed(MouseEvent e) {
        	if (bw.isVerschiebeSchalter()) {
        		GuiPoint dp= new GuiPoint(e.getX(), e.getY());
        		//Echte Korrdinaten werden bestimmt
        		dp=mapClickToPointCoordinates(dp);
        		//Punkt wird herausgesucht und aktiviert
        		boolean activated =dm.activate(dp.getX(), dp.getY(), activationRadius);
        		if(activated) {
        			dm.startVerschiebeVorgang();
        		}
        	}else if (bw.isHinzuSchalter()) {
        		GuiPoint dp= new GuiPoint(e.getX(), e.getY());
        		//Transformation der Mauskoordinaten in echte Koordinaten.
        		dp=mapClickToPointCoordinates(dp);
        		//Fuegt den Punkt der Punkteliste hinzu und loest Neuberechnung und Neuzeichnung aus.
        		dm.addPoint(dp.getX(),dp.getY());
        		dm.updateDatamodel();
        		repaint();
        	} else if (bw.isLoeschenSchalter()) {
        		GuiPoint dp= new GuiPoint(e.getX(), e.getY());
        		//Transformation der Mauskoordinaten in echte Koordinaten.
        		dp=mapClickToPointCoordinates(dp);
        		boolean activated=dm.activate(dp.getX(), dp.getY(),activationRadius);
        		//Loescht den Punkt aus der Punkteliste und loest Neuberechnung und Neuzeichnung aus.
        		if(activated) {
        			dm.removePoint(dm.getActivatedPoint());
        			dm.updateDatamodel();
        			repaint(); 
        			dm.setActivatedPoint(null);
        		}
        	}
        }
        
        
        
    	/**
    	 * Deaktiviert jeglichen aktivierten Punkt beim loslassen der Maustaste. 
    	 * Wird im Rahmen der Verschiebung von Punkten verwendet.
         *
    	 * @param e MouseEvent
    	 */
        public void mouseReleased(MouseEvent e) {
        	if (bw.isVerschiebeSchalter()&&dm.getActivatedPoint()!=null) {
        		dm.endVerschiebeVorgang();
        		dm.setActivatedPoint(null);
        	}
        }
    }



	/**
	 * Getter fuer lowerBoundaryx.
     *
     * @return long lowerBoundaryx wird zurueckgegeben.
	 */
	public long getLowerBoundaryx() {
		return lowerBoundaryx;
	}


	/**
	 * Getter fuer lowerBoundaryy.
     *
     * @return long lowerBoundaryy wird zurueckgegeben.
	 */
	public long getLowerBoundaryy() {
		return lowerBoundaryy;
	}


	/**
	 * Getter fuer dim.
     *
     * @return long dim wird zurueckgegeben.
	 */
	public long getDim() {
		return dim;
	}


	/**
	 * Getter fuer activationRadius.
     *
     * @return long activationRadius wird zurueckgegeben.
	 */
	public double getActivationRadius() {
		return activationRadius;
	}


	/**
	 * Getter fuer bounderydiff.
     *
     * @return long bounderydiff wird zurueckgegeben.
	 */
	public long getBounderydiff() {
		return bounderydiff;
	}
}
