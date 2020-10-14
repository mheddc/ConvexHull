package de.feu.propra18.q2689600.GEKRechner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import de.feu.propra18.q2689600.Datamodel.GuiEdge;
import de.feu.propra18.q2689600.Datamodel.GuiKreis;
import de.feu.propra18.q2689600.Datamodel.GuiPoint;

/**
 * Diese Klasse berechnet den GEK und implementiert dazu die GEKBerechner-Schnittstelle..
 *  
 * @author Manuel Heder
 * 
 */

public class GEKBerechner implements IGEKBerechner{

	//Speichert die Kantenstruktur in einer HashMap. Key ist hier der erste GuiPoint P1. 
	private HashMap<GuiPoint, GuiEdge> edgeList;
	//Speichert die Kanten in einer Radiusstruktur. Ermöglicht einen schnellen Zugriff auf die Kante mit dem
	//kleinsten Radius.
	private TreeSet<GuiEdge> radiusTree;
	
	/**
	 * Konstruktor; Initialisiert Variablen.
     *
    */
	public GEKBerechner() {
		//Initialisiert Kantenstrukur
		edgeList =new HashMap<GuiPoint,GuiEdge>();
		radiusTree= new TreeSet<GuiEdge>();
	}

	/**
	 * Berechnet den GEK mit dem Algorithmus der Kantenelmination.
     *
     *@param convexpolynom ArrayList als Ausgangpunkt für die Berechnung des GEK.
     *@return GuiKreis als GEK.
    */
	public GuiKreis calcMaxKreis(ArrayList<GuiPoint> convexpolynom) {
		initRechner1();
		//Erzeugt Kantenstruktur
		createEdgeStructure(convexpolynom);
		//berechnet GEK durch Kantenelimination
		if (radiusTree.size()>=3) {
			while(radiusTree.size()>3) {
				//Kante mit kleinstem Radius
				GuiEdge dummyEdge=radiusTree.first();
				
				//Holt aus der HashMap die angrenzenden Kanten unter Verwendung der ersten Kantenpunkte.
				GuiEdge Vor=edgeList.get(dummyEdge.getVor());
				GuiEdge VorVor=edgeList.get(Vor.getVor());
				GuiEdge Nach=edgeList.get(dummyEdge.getNach());
				GuiEdge NachNach=edgeList.get(Nach.getNach());
				
				//Eliminiert die Kante mit den kleinsten Radius aus der HashMap
				edgeList.remove(dummyEdge.getP1());
				//Eliminiert drei Kanten aus der Radiusstruktur
				radiusTree.remove(dummyEdge);
				radiusTree.remove(Vor);
				radiusTree.remove(Nach);
				
				//Nach der Kantenelimination müssen die Kanten aktualisiert werden.
				Vor.setNach(Nach.getP1());
				Nach.setVor(Vor.getP1());
				
				//Berechnet Kreise
				GuiKreis dummyKreis1=calcKreis(VorVor,Vor,Nach);
				GuiKreis dummyKreis2=calcKreis(Vor,Nach,NachNach);
				
				//Aktualisiert Kreise
				Vor.setKreis(dummyKreis1);
				Nach.setKreis(dummyKreis2);
				
				//Fügt die aktualisierten Kanten der Edge und Radiusstruktur zu
				//Die Kanten sind in der Edgestruktur noch vorhanden und werden überschrieben.
				edgeList.put(Vor.getP1(), Vor);
				edgeList.put(Nach.getP1(), Nach);
				radiusTree.add(Vor);
				radiusTree.add(Nach);
				
				
			}
			GuiEdge dummyEdge=radiusTree.first();
			return dummyEdge.getKreis();
		} else if (convexpolynom.size()!=0){
			//erzeugt einen Kreis mit Radius null
			return new GuiKreis(convexpolynom.get(0).getX(),convexpolynom.get(0).getY(),0);	
		}else {
			return null;	
		}
	}
	
	/**
	 * Initialisiert HashMap und TreeSet.
     *
    */
	private void initRechner1() {
		edgeList.clear();
		radiusTree.clear();
		
	}

	/**
	 * Erzeugt aus dem convexpolynom die Kantenstruktur.
     *
     *@param convexpolynom ArrayList als Ausgangpunkt für die Berechnung des GEK.
    */
	private void createEdgeStructure(ArrayList<GuiPoint> convexpolynom) {
		
		//Muss größer als 2 sein. Ansonsten kann kein GEK berechnet werden.
		if (convexpolynom.size()>2) {
			
			GuiEdge dummyEdge1=new GuiEdge();
			GuiEdge dummyEdge2=new GuiEdge();
			 
			int jj=2;
			//Geht durch das ganze convexpolynom und erzeugt Kanten
			dummyEdge1.setVor(convexpolynom.get(jj-2));
			dummyEdge1.setP1(convexpolynom.get(jj-1));
			dummyEdge1.setP2(convexpolynom.get(jj));
			dummyEdge1.setNach(convexpolynom.get(jj));
			
			//Geht durch das ganze convexpolynom und erzeugt Kanten
			dummyEdge2.setVor(convexpolynom.get(jj-1));
			dummyEdge2.setP1(convexpolynom.get(jj));
			dummyEdge2.setP2(convexpolynom.get((jj+1)%convexpolynom.size()));
			dummyEdge2.setNach(convexpolynom.get((jj+1)%convexpolynom.size()));
			
			//Geht durch das ganze convexpolynom und erzeugt Kanten. Dazu werden drei Kanten benötigt.
			//Es wird pro Schleifendurchlauf eine neue Kante erzeugt.
			for (int ii=3; ii<=convexpolynom.size()+2;ii=ii+1) {
				
				GuiEdge dummyEdge3=new GuiEdge();
				
				dummyEdge3.setVor(convexpolynom.get((ii-1)%convexpolynom.size()));
				dummyEdge3.setP1(convexpolynom.get((ii)%convexpolynom.size()));
				dummyEdge3.setP2(convexpolynom.get((ii+1)%convexpolynom.size()));
				dummyEdge3.setNach(convexpolynom.get((ii+1)%convexpolynom.size()));
				
				//berechnet Kreis mit Nachbarkanten als Ausgangspunkt für die Kantenelimination.
				GuiKreis kreis=calcKreis(dummyEdge1,dummyEdge2,dummyEdge3);
				dummyEdge2.setKreis(kreis);
				radiusTree.add(dummyEdge2);//Fügt voll berechnete Kante dem RadiusTree zu
				edgeList.put(dummyEdge2.getP1(), dummyEdge2);//Fügt voll berechnete Kante der HashMap zu.
															//Als Key dient hier der erste Kantenpunkt
				//Refernzen werden weitergereicht für den nächsten Schleifendurchlauf
				dummyEdge1=dummyEdge2;
				dummyEdge2=dummyEdge3;
			}
			
			
		} 
		
	}

	/**
	 * Berechnet aus drei Kanten den dazugehörigen Kreis.
     *
     *@param dummyEdgeVor GuiEdge ist erste Kante.
     *@param dummyEdgeAkt GuiEdge ist zweite Kante.
     *@param dummyEdgeNach GuiEdge ist dritte Kante.
     *@return GuiKreis als GEK.
    */
	private GuiKreis calcKreis(GuiEdge dummyEdgeVor, GuiEdge dummyEdgeAkt, GuiEdge dummyEdgeNach) {
		
		GuiPoint A=dummyEdgeVor.getP1();
		GuiPoint B=dummyEdgeVor.getP2();
		GuiPoint C=dummyEdgeAkt.getP1();
		GuiPoint D=dummyEdgeAkt.getP2();
		GuiPoint E=dummyEdgeNach.getP1();
		GuiPoint F=dummyEdgeNach.getP2();

		Double dAB = calcNorm(A,B);
		Double dCD = calcNorm(C,D);
		Double dEF = calcNorm(E,F);
		Double alpha1= dAB * (D.getYdouble()-C.getYdouble() )-dCD * (B.getYdouble()-A.getYdouble() );
		Double alpha2= dCD * (F.getYdouble()-E.getYdouble() )-dEF * (D.getYdouble()-C.getYdouble() );
		Double beta1=  dCD * (B.getXdouble()-A.getXdouble() )-dAB * (D.getXdouble()-C.getXdouble());
		Double beta2= dEF  * (D.getXdouble()-C.getXdouble() )-dCD * (F.getXdouble() -E.getXdouble());
		Double gamma1= dAB * (C.getXdouble()*(D.getYdouble()-C.getYdouble())-C.getYdouble()*(D.getXdouble()-C.getXdouble()))+ dCD*(A.getYdouble()*(B.getXdouble()-A.getXdouble())-A.getXdouble()*(B.getYdouble()-A.getYdouble()));
		Double gamma2= dCD * (E.getXdouble()*(F.getYdouble()-E.getYdouble())-E.getYdouble()*(F.getXdouble()-E.getXdouble()))+ dEF*(C.getYdouble()*(D.getXdouble()-C.getXdouble())-C.getXdouble()*(D.getYdouble()-C.getYdouble()));
		
		Double dH = alpha1*beta2-alpha2*beta1;
		Double dU = gamma1*beta2-gamma2*beta1;
		Double dV = alpha1*gamma2-alpha2*gamma1;
				 
		Double Mx=dU/dH;
		Double My=dV/dH;
			
		Double radius1=A.getXdouble()-Mx;
		Double radius2=(double) (B.getYdouble()-A.getYdouble());
		Double radius3=A.getYdouble()-My;
		Double radius4=(double) (A.getXdouble()-B.getXdouble());
		Double radius5=radius1*radius2;
		Double radius6=radius3*radius4;
		Double radius7=radius5+radius6;
		Double radius8=radius7/dAB;
		
		
		
		GuiKreis kreis=new GuiKreis();
		kreis.setMittelpunktx(Mx);
		kreis.setMittelpunkty(My);
		kreis.setRadius(radius8);
		return kreis;
	}

	/**
	 * Berechnet den Abstand zwischen zwei Punkten.
     *
     *@param A GuiPoint des ersten Punktes
     *@param B GuiPoint des zweiten Punktes
     *@return Double bezeichnet den Abstand.
    */
	private Double calcNorm(GuiPoint A, GuiPoint B) {
		
		double a = A.getXdouble()-B.getXdouble();
		double b = A.getYdouble()-B.getYdouble();
		Double c = Math.sqrt(a*a+b*b);
		return c;
	}
	
	
	
}
