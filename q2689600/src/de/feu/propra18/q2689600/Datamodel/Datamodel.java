package de.feu.propra18.q2689600.Datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import de.feu.propra18.q2689600.GEKRechner.IGEKBerechner;
import de.feu.propra18.q2689600.GEKRechner.GEKBerechner;
import de.feu.propra18.q2689600.KonvexRechner.IKonvexBerechner;
import de.feu.propra18.q2689600.KonvexRechner.KonvexBerechner;
import de.feu.propra18.q2689600.UndoRedo.Vorgang;
import de.feu.propra18.q2689600.UndoRedo.VorgangDoublyLinkedList;
import de.feu.propra18.q2689600.UndoRedo.VorgangHinzu;
import de.feu.propra18.q2689600.UndoRedo.VorgangHinzuArray;
import de.feu.propra18.q2689600.UndoRedo.VorgangLoeschen;
import de.feu.propra18.q2689600.UndoRedo.VorgangVerschiebung;

/**
 * Diese Klasse stellt das Datenmodell dar und beinhaltet daher die Punkte und die konvexe Huelle.
 * Außerdem enthält es Referenzen auf die Interfaces zur Berechnung der konvexen Hülle und des GEK.
 * Eine Referenz auf die Vorgangsliste zur Durchführung der Undo- und Redooperation ist auch enthalten.
 * 
 * @author Manuel Heder
 * 
 */
public class Datamodel {

	//Maximum- und Minimumwerte, die vom Datenmodell akzeptiert werden.
	public final int MaxX=2147483647;
	public final int MaxY=2147483647;
	public final int MinX=-2147483648;
	public final int MinY=-2147483648;
	
	//pointList enthaelt die Punkte.
	private ArrayList<GuiPoint> pointList;
	private ArrayList<GuiPoint> convexpolynom;


	//Interface Berechner
	IKonvexBerechner br;
	IGEKBerechner brGEK1;
	GuiKreis kreis;
	VorgangDoublyLinkedList vl; //Zum speichern aller Vorgänge für die Undo und Redooperation.

	
	//activated ist ein Punkt, der im Zuge der Verschiebung oder Loeschung aus der Punktmenge 
	//herausgesucht wird.
	private GuiPoint activated;
	

	/**
	 * Konstruktor; Initialisiert die Punktliste und die konvexe Huelle.
     *
	 */
	public Datamodel(){
		pointList=new ArrayList<GuiPoint>();
		convexpolynom=new ArrayList<GuiPoint>();
		br= new KonvexBerechner(pointList);
		brGEK1=new GEKBerechner();
		vl=new VorgangDoublyLinkedList();
	}
		
	
	/**
	 * Führt die Undo-Operation durch.
     *
	 */
	public void undo() {
		//aktueller Stand in der Vorgangsliste
		Vorgang v=vl.getCurrent();
		//Um zu prüfen, ob der Anfang der Liste schon erreicht ist.
		if (v!=null) {
			//Stand wird einen Vorgang zurückgesetzt
			vl.previous();
			//Undo wird durchgeführt.
			if (v instanceof VorgangHinzu) {
				pointList.remove(((VorgangHinzu) v).getHinzu());
			}else if (v instanceof VorgangHinzuArray) {
				VorgangHinzuArray vha=(VorgangHinzuArray)v;
				for (int ii=0; ii<vha.getPointList().size();ii++) {
					pointList.remove(vha.getPointList().get(ii));
				}
			}else if (v instanceof VorgangLoeschen) {
				pointList.add(((VorgangLoeschen) v).getGeloescht());
			}else if (v instanceof VorgangVerschiebung ) {
				//holt Referenz auf verschobenen Punkt und weist die alten Koordinaten zu.
				GuiPoint moving=((VorgangVerschiebung) v).getMovingPoint();
				moving.setX(((VorgangVerschiebung) v).getAlt().getX());
				moving.setY(((VorgangVerschiebung) v).getAlt().getY());
			}
		}
	}
	
	/**
	 * Führt die Redo-Operation durch.
     *
	 */
	public void redo() {
		Vorgang v=vl.getCurrent();
		//prüft ob Ende der Liste noch nicht erreicht ist.
		if (v==null||v.getNach()!=null) {
			//geht zum nächsten Vorgangselement
			vl.next();
			v=vl.getCurrent();
			//Führt redo-Operation durch
			if (v instanceof VorgangHinzu) {
				pointList.add(((VorgangHinzu) v).getHinzu());
			}else if (v instanceof VorgangHinzuArray) {
				VorgangHinzuArray vha=(VorgangHinzuArray)v;
				for (int ii=0; ii<vha.getPointList().size();ii++) {
					pointList.add(vha.getPointList().get(ii));
				}
			}else if (v instanceof VorgangLoeschen) {
				pointList.remove(((VorgangLoeschen) v).getGeloescht());
			}else if (v instanceof VorgangVerschiebung ) {
				//holt Referenz auf verschobenen Punkt und weist die neuen Koordinaten zu.
				GuiPoint moving=((VorgangVerschiebung) v).getMovingPoint();
				moving.setX(((VorgangVerschiebung) v).getNeu().getX());
				moving.setY(((VorgangVerschiebung) v).getNeu().getY());
			}
		}	
	}
	
	/**
	 * Fuegt der Punktmenge einen Punkt hinzu und erzeugt einen Vorgang.
     *
	 * @param x  Integer der die x-Koordinate repraesentiert.
	 * @param y  Integer der die y-Koordinate repraesentiert.
	 */
	public void addPoint(int x, int y){
		GuiPoint dummyPoint=new GuiPoint(x, y);
		pointList.add(dummyPoint);
		VorgangHinzu vh=new VorgangHinzu(dummyPoint);
		vl.add(vh);
	}

	/**
	 * Löscht einen Punkt und erzeugt einen Vorgang dazu.
     *
	 * @param gp Zu löschender Punkt.
	 */
	public void removePoint(GuiPoint gp){
		pointList.remove(gp);
		VorgangLoeschen vh=new VorgangLoeschen(gp);
		vl.add(vh);	
	}

	/**
	 * Fuegt der Punktmenge einen Punkt hinzu.
     *
	 * @param x  Integer der die x-Koordinate repraesentiert.
	 * @param y  Integer der die y-Koordinate repraesentiert.
	 */
	public void removePoint(int x, int y){
		GuiPoint dummyPoint=new GuiPoint(x, y);
		pointList.remove(dummyPoint);
		VorgangLoeschen vh=new VorgangLoeschen(dummyPoint);
		vl.add(vh);	
	}
	
	/**
	 * Fuegt der Punktmenge Punkte aus einem Array hinzu. Erzeugt einen Vorgang dazu.
	 * 
	 * @param pointArray   Zweidimensionaler Integer-Array der x- und y-Koordinaten speichert. 
	 * Die erste Dimension ist der Index des Punktes und die zweite Dimension speichert unter [0] 
	 * den x-Wert und unter [1] den y-Wert. 
	 */
	public void addPointsFromArray(int[][] pointArray) {
		VorgangHinzuArray vza=new VorgangHinzuArray();
		for (int ii=0;ii<=pointArray.length;ii++){
			GuiPoint dummyPoint=new GuiPoint(pointArray[ii][0],pointArray[ii][1]);
			pointList.add(dummyPoint);
			vza.addPoint(dummyPoint);
		}
		vl.add(vza);
		
	}
	
	/**
	 * Fuegt der Punktmenge Punkte aus einer ArrayList hinzu. 
	 * 
	 * @param pl ArrayList   Liste von GuiPoints zum Hinzufügen.
	 */
	public void addPointsFromGuiPointArrayList(ArrayList<GuiPoint> pl) {
		VorgangHinzuArray vza=new VorgangHinzuArray();
		for (int ii=0;ii<pl.size();ii++){
			pointList.add(pl.get(ii));
		}
		vza.setPointList(pl);
		vl.add(vza);
		
	}

	
	/**
	 * Aktualisiert das Datenmodell durch Neuberechnung der konvexen Huelle und des GEK.
     *
	 */
	public void updateDatamodel() {
		convexpolynom = br.calcConvexpolynom();
		kreis=brGEK1.calcMaxKreis(convexpolynom);
	}


	
	/**
	 * Die konvexe Huelle wird in ein Integer-Array umgeschrieben, um den Anforderungen des Test-Interfaces
	 * zu entsprechen.
     *
     *@return int[][] stellt die konvexe Hülle als Array dar.
	 */
	public int[][] getConvexHull (){
		
		//Die konvexe Huelle wird in ein Integer-Array umgeschrieben, um den Anforderungen des Test-Interfaces
		//zu entsprechen.
		if (convexpolynom.size()!=0) {
			int [][] ConvexHull=new int[convexpolynom.size()][2];
	
		
			for (int ii=0; ii<convexpolynom.size();ii++) {
				ConvexHull[ii][0]=convexpolynom.get(ii).getX();
				ConvexHull[ii][1]=convexpolynom.get(ii).getY();
			}
			return ConvexHull;
		} else {
			return null;
		}
	
	}
	
	/**
	 * Sucht einen Punkt der innerhalb des activationRadius um die uebergebenen Koordinaten x und y liegt.
	 * Wird beim Loeschen oder Verschieben eines Punktes verwendet.
     *
	 * @param x  Integer der die x-Koordinate repraesentiert.
	 * @param y  Integer der die y-Koordinate repraesentiert.
	 * @param activationRadius  Long der den Radius um die Koordinaten darstellt.
	 * @return boolean um anzuzeigen, ob ein Punkt gefunden wurde
	 */
	public boolean activate(int x, int y, double activationRadius) {
		boolean activated=false;
		// Geht durch die Punktliste.
		for (int ii=0; ii<pointList.size();ii++) {
			if((pointList.get(ii).getX()<x+activationRadius) && (pointList.get(ii).getX()>x-activationRadius)) {
				if((pointList.get(ii).getY()<y+activationRadius) && (pointList.get(ii).getY()>y-activationRadius)) {
					//Hat einen Punkt gefunden und ordnet ihn Activated zu.
					setActivatedPoint(pointList.get(ii));
					activated=true;
				}
			}
		}
		if(activated==false) {
			setActivatedPoint(null);
		};
		return activated;
	}

	
	/**
	 * Loescht alle Punkte in der Punktliste und setzt die Vorgangsliste zurück.
     *
	 */
	public void clear() {
		// TODO Auto-generated method stub
		pointList.clear();
		vl.clear();
	}
	
	/**
	 * Getter fuer die Punktliste.
     *
     *@return Gibt die Punkteliste zurueck.
	 */
	public ArrayList<GuiPoint> getPointList() {
		return pointList;
	}

	/**
	 * Getter fuer activated.
     *
     *@return Gibt GuiPoint zurueck.
	 */
	public GuiPoint getActivatedPoint() {
		return activated;
	}
	
	/**
	 * Setter fuer activated.
     *
     *@param activated Setter
	 */
	public void setActivatedPoint(GuiPoint activated) {
		this.activated = activated;
	}
	
	/**
	 * Getter fuer R point.
     *
     *@return Gibt GuiPoint zurueck.
	 */
	public GuiPoint getRPoint() {
		return br.getRPoint();
	}

	/**
	 * Getter fuer T point.
     *
     * @return Gibt GuiPoint zurueck.
	 */
	public GuiPoint getTPoint() {
		return br.getTPoint();
	}
	
	/**
	 * Getter fuer L-Punkt.
     *
     *@return Gibt GuiPoint zurueck.
	 */
	public GuiPoint getLPoint() {
		return br.getLPoint();
	}
	
	/**
	 * Getter fuer B-Punkt.
     *
     * @return Gibt GuiPoint zurueck.
	 */
	public GuiPoint getBPoint() {
		return br.getBPoint();
	}
	
	
	public ArrayList<GuiPoint> getConvexpolynom() {
		return convexpolynom;
	}
	
	/**
	 * Gibt den die x-Koordinate des GEK zurück.
     *
	 * @return double    Gibt die x-Koordinate des GEK zurück
	 */
	public double getGEKCenterX() {
		return kreis.getMittelpunktx();
	}


	/**
	 * Gibt den die y-Koordinate des GEK zurück.
     *
	 * @return double    Gibt die y-Koordinate des GEK zurück
	 */
	public double getGEKCenterY() {
		return kreis.getMittelpunkty();
	}

	/**
	 * Gibt den Radius des GEK zurück.
     *
	 * @return double    Gibt den Radius des GEK zurück
	 */
	public GuiPoint getGEKCenterPoint() {
		int a= (int) Math.round(kreis.getMittelpunktx());
		int b= (int) Math.round(kreis.getMittelpunkty());
		return new GuiPoint(a,b);
	}
	
	/**
	 * Gibt den Radius des GEK zurück.
     *
	 * @return double    Gibt den Radius des GEK zurück
	 */
	public double getGEKRadius() {
		return kreis.getRadius();
	}

	/**
	 * Erzeugt am Anfang eines Verschiebevorgangs einen Vorgang.
     *Speichert die Koordinaten vor der Verschiebung in einem GuiPoint.
	 */
	public void endVerschiebeVorgang() {
		VorgangVerschiebung vv=(VorgangVerschiebung) vl.getCurrent();
		vv.setNeu(new GuiPoint(activated.getX(),activated.getY()));
		
	}

	/**
	 * Erzeugt am Anfang eines Verschiebevorgangs einen Vorgang.
     * Speichert die Koordinaten nach der Verschiebung in einem GuiPoint.
     * Außerdem speichert er einen Punkt auf den verschobenen Punkt.
	 */
	public void startVerschiebeVorgang() {
		VorgangVerschiebung vv=new VorgangVerschiebung();
		GuiPoint gp=new GuiPoint(activated.getX(),activated.getY());
		vv.setAlt(gp);
		vv.setMovingPoint(activated);
		vl.add(vv);
	}



}
