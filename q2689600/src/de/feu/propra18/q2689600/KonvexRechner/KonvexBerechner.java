package de.feu.propra18.q2689600.KonvexRechner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import de.feu.propra18.q2689600.Datamodel.GuiPoint;

/**
 * Diese Klasse berechnet die konvexe Hülle.
 *  
 * @author Manuel Heder
 * 
 */
public class KonvexBerechner implements IKonvexBerechner{

	//Sweep Lines werden im Zug der Berechnung benoetigt und werden auch auf der Zeichenflaeche dargestellt
	private ArrayList<GuiPoint> upperSweepLineRight;
	private ArrayList<GuiPoint> upperSweepLineLeft;
	private ArrayList<GuiPoint> lowerSweepLineRight;
	private ArrayList<GuiPoint> lowerSweepLineLeft;	
	
	//Konvexe Linien. Zusammen bilden sie die konvexe Huellkurve.
	private ArrayList<GuiPoint> upperConvexSweepLineRight;
	private ArrayList<GuiPoint> upperConvexSweepLineLeft;
	private ArrayList<GuiPoint> lowerConvexSweepLineRight;
	private ArrayList<GuiPoint> lowerConvexSweepLineLeft;

	//Extrempunkte: Top, Bottom, Left, Right
	private GuiPoint T;
	private GuiPoint B;
	private GuiPoint L;
	private GuiPoint R;
	
	//pointList enthaelt die Punkte.
	private ArrayList<GuiPoint> pointList;
	private ArrayList<GuiPoint> convexpolynom;
	
	/**
	 * Konstruktor; Initialisiert Variablen.
     *
     *@param pointList ArrayListaus GuiPoints als Ausgangspunkt der Berechnung.
    */
	public KonvexBerechner(ArrayList<GuiPoint> pointList) {
		this.pointList=new ArrayList<GuiPoint>();
		this.convexpolynom=new ArrayList<GuiPoint>();
		this.pointList=pointList;
	}
	

	/**
	 * Steuert die Berechnung der konvexen Hülle und gibt sie als ArrayList von Guipoints zurück.
     *
     *@return ArrayList aus GuiPoints, die die konvexe Hülle repräsentiert.
    */
	@Override
	public ArrayList<GuiPoint> calcConvexpolynom() {
		calcKonturpolynom();
		calcConvexSweepLines();
		return getConvexHull();
	}
	
	
	/**
	 * Steuert die Berechnung des Konturpolynoms. Nach Abschluss der Methode sind die vier Sweep lines und Extrempunkte bestimmt.
     *
	 */
	public void calcKonturpolynom() {
		if (!pointList.isEmpty()) {
			//Sortiert die pointList
			Collections.sort(pointList);
			calcSweepLineLeft(pointList);
			calcSweepLineRight(pointList);
		} else {
			//falls keine Punkte in der Liste sind werden die Sweeplines initialisiert.
			getUpperSweepLineLeft().clear();
			getLowerSweepLineLeft().clear();
			getUpperSweepLineRight().clear();
			getLowerSweepLineRight().clear();
			
			
		}
	}
	

	
	/**
	 * Steuert die Berechnung der konvexen Huelle. Ergbenis ist die konvexe Huelle.
     *
	 */
	public void calcConvexSweepLines() {
		if (!pointList.isEmpty()) {
			upperConvexSweepLineLeft=getConvexSweepLine(upperSweepLineLeft,1);
			lowerConvexSweepLineLeft=getConvexSweepLine(lowerSweepLineLeft,-1);
			upperConvexSweepLineRight=getConvexSweepLine(upperSweepLineRight,-1);
			lowerConvexSweepLineRight=getConvexSweepLine(lowerSweepLineRight,1);
		}else {
			getUpperConvexSweepLineLeft().clear();
			getLowerConvexSweepLineLeft().clear();
			getUpperConvexSweepLineRight().clear();
			getLowerConvexSweepLineRight().clear();
		}
	}
	
	/**
	 * Berechnet die obere und untere linke Sweepline aus der Punktmenge.
     *
	 * @param pointList2   GuiPoint array der die Punkte der Punktmenge enthaelt. 
	 */
	private void calcSweepLineLeft(ArrayList<GuiPoint> pointList2) {
		
		//Initialisiert die Sweeplines
		upperSweepLineLeft=new ArrayList<GuiPoint>();
		lowerSweepLineLeft=new ArrayList<GuiPoint>();
		
		//Der erste Punkt der sortierten Punkliste ist der L-Punkt.
		L=pointList2.get(0);
		
		//Der L-Punkt ist der erste Punkt sowohl der unteren als auch der oberen Sweepline.
		upperSweepLineLeft.add(L);
		lowerSweepLineLeft.add(L);
		
		//Max und minimum Y-Wert werden mit dem L-Punkt initialisiert.
		Integer maxYSoFar=new Integer(L.getY());
		Integer minYSoFar=new Integer(L.getY());
		
		//Geht durch die gesamte Punktliste und fuellt die Sweeplines auf.
		for (int ii=1; ii<pointList2.size();ii++ ) {
			if (pointList2.get(ii).getY()<=minYSoFar) {
				minYSoFar=pointList2.get(ii).getY();
				lowerSweepLineLeft.add(pointList2.get(ii));
			}
			if (pointList2.get(ii).getY()>=maxYSoFar) {
				maxYSoFar=pointList2.get(ii).getY();
				upperSweepLineLeft.add(pointList2.get(ii));
			}
		}
		
		
		//Setzt den T und B-Point.
		T=upperSweepLineLeft.get(getUpperSweepLineLeft().size()-1);
		B=lowerSweepLineLeft.get(getLowerSweepLineLeft().size()-1);
		
	}

	
	/**
	 * Berechnet die obere und untere rechte Sweepline aus der Punktmenge.
     *
	 * @param pointList2   GuiPoint array der die Punkte der Punktmenge enthaelt. 
	 */
	private void calcSweepLineRight(ArrayList<GuiPoint> pointList2) {
		
		//Initialisiert die Sweeplines
		upperSweepLineRight=new ArrayList<GuiPoint>();
		lowerSweepLineRight=new ArrayList<GuiPoint>();

		//Der letzte Punkt der sortierten Punkliste ist der R-Punkt.
		R=pointList2.get(pointList2.size()-1);
		
		//Der R-Punkt ist der erste Punkt sowohl der unteren als auch der oberen Sweepline.
		upperSweepLineRight.add(R);
		lowerSweepLineRight.add(R);
		
		//Max und minimum Y-Wert werden mit dem R-Punkt initialisiert.
		Integer maxYSoFar=new Integer(R.getY());
		Integer minYSoFar=new Integer(R.getY());
		
		Boolean BPointGefunden=false;
		Boolean TPointGefunden=false;
		 
		//Falls R gleich T oder B ist ist diese Sweepline vollstaendig. Verwendung in der Schleife unten.
		if (R==T) {
			TPointGefunden=true;
		}
		if (R==B) {
			BPointGefunden=true;
		}
		//Geht durch die gesamte Punktliste und fuellt die Sweeplines auf.Falls B, oder T getroffen wird, wird abgebrochen.
		for (int ii=pointList2.size()-2; ii>=0;ii--) {
			if (pointList2.get(ii).getY()<=minYSoFar && BPointGefunden==false) {
				if (pointList2.get(ii)==B) {
					//Ende der Sweep line erreicht
					BPointGefunden=true;
				} 
				minYSoFar=pointList2.get(ii).getY();
				getLowerSweepLineRight().add(pointList2.get(ii));
			}
			if (pointList2.get(ii).getY()>=maxYSoFar && TPointGefunden==false) {
				if (pointList2.get(ii)==T) {
					//Ende der Sweep line erreicht
					TPointGefunden=true;
				}
				maxYSoFar=pointList2.get(ii).getY();
				getUpperSweepLineRight().add(pointList2.get(ii));
			}
		}
		
	}
	
	/**
	 * Berechnet die konvexe Huelle aus der Sweepline. uebergeben werden kann jede Art von Sweepline.
     * Durch einen zusaetzlichen Parameter wird gesteuert, ob bei links oder rechtsheraustehenden Punkt
     * der vorige Punkt geloescht wird.
     *
	 * @param sweepLine   Array von Gui-Points der eine Sweepline beinhaltet. 
	 * @param LRvalue   Integer der steuert, ob bei links oder rechtsheraustehenden Punkt
     * der vorige Punkt geloescht wird.
	 * @return  Array von Gui-Points, der die konvexe Huelle enthaelt.
	 */
	private ArrayList<GuiPoint> getConvexSweepLine(ArrayList<GuiPoint> sweepLine, int LRvalue){
		
		ArrayList<GuiPoint> convexSweepLine=new ArrayList<GuiPoint>();
		
		
		//Falls die sweepline nur einen Punkt enthaelt wird dieser sofort zurueck gegeben.
		if (sweepLine.size()>1) {
			
			//Die ersten zwei Punkte werden sofort der konvexen Hoelle hinzugefuegt.
			convexSweepLine.add(sweepLine.get(0));
			convexSweepLine.add(sweepLine.get(1));
			
			//Die restliche Sweepline wird durchgegangen
			for (int ii=2; ii<sweepLine.size(); ii++) {
				
				convexSweepLine.add(sweepLine.get(ii));
				int lastElement=convexSweepLine.size()-1;
				
				//Zun testen. Determinante wird berechnet.
				Double lrResult=getLeftOrRight(convexSweepLine.get(lastElement-2),convexSweepLine.get(lastElement-1),convexSweepLine.get(lastElement));
				
				//Geht in der konvexen Huelle zurueck bis nur noch zwei Elemente uebrig sind oder der vorige Punkt nicht mehr geloescht werden muss.
				while(lastElement>1 && getLeftOrRight(convexSweepLine.get(lastElement-2),convexSweepLine.get(lastElement-1),convexSweepLine.get(lastElement))*LRvalue>=0) {
					convexSweepLine.remove(lastElement-1);
					lastElement=lastElement-1;
				}
				
			
			}
			return convexSweepLine;
			
		}else {
			return sweepLine;
		}
		
	}
	
	
	/**
	 * Fuegt die vier einzelnen Arrays, die die konvexen Huelle bilden (links unten, links oben...), zu einem Array zusammen.
	 * Dieser Gui-Point Array wird dann in einen Integer-Array umgeschrieben. Dient hauptsaechlich zur uebergabe an den Tester.
     * Zur Darstellung wird dieses Array nicht benoetigt, da dazu die einzelnen vier Arrays verwendet werden.
     * 
     * @return int[][] Gibt einen Integer Array zurueck, der die Punkte der konvexen Huelle enthaelt zurueck.
	 */
	public ArrayList<GuiPoint> getConvexHull() {
		
		//Initialisierung des Konvexpolynoms
		convexpolynom.clear();
		
		//Fuegt die komplette obere linke konvexe Huelle hinzu.
		convexpolynom.addAll(upperConvexSweepLineLeft);

		//Fuegt die komplette obere rechte konvexe Huelle hinzu. Da die rechte konvexe Huelle bei R beginnt
		//wird hier vom letzten Punkt aus gestartet.
		for (int zz=upperConvexSweepLineRight.size()-1;zz>=0;zz--) {
			convexpolynom.add(upperConvexSweepLineRight.get(zz));
		}

		//Fuegt die komplette untere rechte konvexe Huelle hinzu.
		convexpolynom.addAll(lowerConvexSweepLineRight);
		
		//Fuegt die komplette untere linke konvexe Huelle hinzu. Da die linke konvexe Huelle bei L beginnt
		//wird hier vom letzten Punkt aus gestartet.	
		for (int zz=lowerConvexSweepLineLeft.size()-1;zz>=0;zz--) {
			convexpolynom.add(lowerConvexSweepLineLeft.get(zz));
		}
		
		
		if (convexpolynom.size()>1){

			
			GuiPoint p1=convexpolynom.get(0);
			GuiPoint p2;
			ListIterator<GuiPoint> lit= convexpolynom.listIterator(1);
			
			//Geht durch die konvexe Huelle. Doppelte Punkte werden rausgeloescht.
			//Doppelte Punkte entstehen, da die vier konvexen Huellen einfach aneinandergereiht wurden.
			while(lit.hasNext()) {
				p2=lit.next();
				if (p1.getX()==p2.getX() && p1.getY()==p2.getY()) {
					lit.remove();
				}else {
					p1=p2;
				}
			}
			
			//Falls der erste und letzte Punkt identisch sind, wird der erste geloescht.
			if (convexpolynom.size()>1){
				p1=convexpolynom.get(0);
				p2=convexpolynom.get(convexpolynom.size()-1);
				if (p1.getX()==p2.getX() && p1.getY()==p2.getY()){
					convexpolynom.remove(0);
				}
			}
		}
		
		Collections.reverse(convexpolynom);
		return convexpolynom;
		
	}
	
	
	/**
	 * Berechnet die Determinate von drei Punkten. Dient zur Berechnung der konvexen Huelle verwendet.
     *
	 * @param A  GuiPoint der den ersten Punkt in der Reihenfolge von drei Punkten darstellt.
	 * @param B  GuiPoint der den zweiten Punkt in der Reihenfolge von drei Punkten darstellt.
	 * @param C  GuiPoint der den dritten Punkt in der Reihenfolge von drei Punkten darstellt.
	 * @return long gibt die Determinante zurueck
	 */
	private Double getLeftOrRight(GuiPoint A, GuiPoint B, GuiPoint C) {

		Double a= ((double)A.getX()*((double)B.getY()-(double)C.getY()));
		Double b=(double) ((double)B.getX()*((double)C.getY()-(double)A.getY()));
		Double c=(double) ((double)C.getX()*((double)A.getY()-(double)B.getY()));
		return a+b+c;
	}

	/**
	 * Getter fuer L-Punkt.
     *
     *@return Gibt GuiPoint zurueck.
	 */
	public GuiPoint getLPoint() {
		return L;
	}
	
	/**
	 * Getter fuer B-Punkt.
     *
     * @return Gibt GuiPoint zurueck.
	 */
	public GuiPoint getBPoint() {
		return B;
	}
	
	/**
	 * Getter fuer obere linke sweep line.
     *
     *@return Gibt Array von GuiPoints zurueck
	 */
	public ArrayList<GuiPoint> getUpperSweepLineLeft() {
		return upperSweepLineLeft;
	}
	
	/**
	 * Getter fuer untere linke sweep line.
     *
     *@return Gibt Array von GuiPoints zurueck
	 */
	public ArrayList<GuiPoint> getLowerSweepLineLeft() {
		return lowerSweepLineLeft;
	}

	/**
	 * Getter fuer obere rechte sweep line.
     *
     *@return Gibt Array von GuiPoints zurueck
	 */
	public ArrayList<GuiPoint> getUpperSweepLineRight() {
		return upperSweepLineRight;
	}
	
	/**
	 * Getter fuer untere rechte sweep line.
     *
     *@return Gibt Array von GuiPoints zurueck
	 */
	public ArrayList<GuiPoint> getLowerSweepLineRight() {
		return lowerSweepLineRight;
	}

	/**
	 * Getter fuer LowerConvexSweepLineRight.
     *
     *@return Gibt Array von GuiPoints zurueck
	 */
	public ArrayList<GuiPoint> getLowerConvexSweepLineRight() {
		return lowerConvexSweepLineRight;
	}
		
	/**
	 * Getter fuer UpperConvexSweepLineRight.
     *
     *@return Gibt Array von GuiPoints zurueck
	 */
	public ArrayList<GuiPoint> getUpperConvexSweepLineRight() {
		return upperConvexSweepLineRight;
	}
	
	/**
	 * Getter fuer UpperConvexSweepLineRight.
     *
     *@return Gibt Array von GuiPoints zurueck
	 */
	public ArrayList<GuiPoint> getLowerConvexSweepLineLeft() {
		return lowerConvexSweepLineLeft;
	}
	
	/**
	 * Getter fuer LowerConvexSweepLineLeft.
     *
     *@return Gibt Array von GuiPoints zurueck.
	 */
	public ArrayList<GuiPoint> getUpperConvexSweepLineLeft() {
		return upperConvexSweepLineLeft;
	}

	/**
	 * Getter fuer R point.
     *
     *@return Gibt GuiPoint zurueck.
	 */
	public GuiPoint getRPoint() {
		return R;
	}

	/**
	 * Getter fuer T point.
     *
     * @return Gibt GuiPoint zurueck.
	 */
	public GuiPoint getTPoint() {
		return T;
	}



}
