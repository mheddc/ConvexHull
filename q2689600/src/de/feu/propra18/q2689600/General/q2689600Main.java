package de.feu.propra18.q2689600.General;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import de.feu.propra18.interfaces.IHullCalculator;
import de.feu.propra18.q2689600.Datamodel.Datamodel;
import de.feu.propra18.q2689600.Datamodel.GuiPoint;
import de.feu.propra18.q2689600.Gui.MainWindow;
import de.feu.propra18.tester.Tester;


/**
 * Diese Klasse implementiert das Interface IHullCalcultor und 
 * stellt daher die Schnittstelle fuer die Tester-Klasse dar.
 * Weiterhin beinhaltet diese Klasse die Main-Methode.
 * 
 * @author Manuel Heder
 * 
 */

public class q2689600Main implements IHullCalculator{

	Datamodel datamodel; //Referenz des Datamodels
	
	
	/**
	 * Konstruktor; Erstellt das Datamodel, welches sowohl fuer den Tester als auch die Gui notwendig ist.
     *
	 */
	public q2689600Main() {
		datamodel=new Datamodel();
	};
	
	
	
	/**
	 * Stellt den Eingangspunkt in das Programm dar. Entweder wird die Testklasse erzeugt, 
	 * der die verschiedenen Tests durchfuehrt oder die Gui wird gestartet.
     *
	 * @param args Stringarray der die Eingabepamater an das Programm enthaelt.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length > 0 && "-t".equals(args[0])) {
			IHullCalculator calculator = new q2689600Main();
			Tester tester = new Tester(args, calculator);
			System.out.println(tester.test());
			}
		else // Benutzeroberflaeche
			{
				q2689600Main test = new q2689600Main();

				MainWindow dg = new MainWindow(test.datamodel);

		        EventQueue.invokeLater(() -> {   
		            dg.setVisible(true);
		        });    

			}
	}

	
	
	/**
	 * Fuegt der Punktmenge einen Punkt hinzu. Ruft dazu die addPoint Methode des Datamodels auf.
     * 
	 * @param x  Integer der die x-Koordinate repraesentiert.
	 * @param y  Integer der die y-Koordinate repraesentiert.
	 */
	@Override
	public void addPoint(int x, int y) {
		datamodel.addPoint(x, y);
	}

	
	
	/**
	 * Fuegt der Punktmenge Punkte aus einem Array hinzu. Ruft dazu mehrfach die addPoint Methode des Datamodels auf.
     *
	 * @param pointArray   Zweidimensionaler Integer-Array der x- und y-Koordinaten speichert. 
	 * Die erste Dimension ist der Index des Punktes und die zweite Dimension speichert unter [0] 
	 * den x-Wert und unter [1] den y-Wert. 
	 */
	@Override
	public void addPointsFromArray(int[][] pointArray) {
		datamodel.addPointsFromArray(pointArray);		
	}

	
	/**
	 * Fuegt der Punktmenge Punkte aus einer Datei hinzu. Ruft dazu mehrfach die addPoint Methode des 
	 * Datamodels auf.
     *
	 * @param fileName   String der den Namen der einzulesenden Datei beinhaltet. 
	 * @throws IOException  Falls die Datei nicht gefunden wird oder ein Fehler beim Einleseprozess 
	 * des BufferedReader geschieht.
	 */
	@Override
	public void addPointsFromFile(String fileName) throws IOException  {

			FileInputStream fs = new FileInputStream(fileName);
        	InputStreamReader ir=new InputStreamReader(fs);
        	BufferedReader br=new BufferedReader(ir);
        	
        	String line=null; //Initialisierung, wird zum Einlesen der Datei genutzt.
        	ArrayList<GuiPoint> gpl=new ArrayList<GuiPoint>();
        	//while-Schleife liest zeilenweise durch die Datei.
        	while(br.ready()){

					line=br.readLine();
					String[] Koordinaten=line.split("\\s");
					if (Koordinaten.length==2) {
						try {
							int x=Integer.parseInt(Koordinaten[0]);
							int y=Integer.parseInt(Koordinaten[1]);
							
							//Es wird geprueft, ob die Koordinaten innerhalb der Integer-Grenzen sind.
							if(x<=datamodel.MaxX && y<=datamodel.MaxY && x>=datamodel.MinX && y>=datamodel.MinX) {
								GuiPoint dummyPoint=new GuiPoint(x, y);
								gpl.add(dummyPoint);
								
							}
						}catch(NumberFormatException e){};	
						
					}

        	};
        	datamodel.addPointsFromGuiPointArrayList(gpl);
            
            fs.close();
            ir.close();
            br.close();
            //Nach dem Einlesen der Punkte in die Punktmenge wird das Datamodel upgedated. 
            //D.h. das Konturpolynom und die konvexe Huelle wird berechnet.
            datamodel.updateDatamodel();
            
		
	}
	
	
	
	/**
	 * Loescht alle Punkte. Nutzt dazu die Methode clear des Datamodels.
	 * 
	 */
	@Override
	public void clear() {
		datamodel.clear();
		
	}

	
	
	/**
	 * Berechnet die konvexe Huelle. Ruft dazu die Methode getConvecHull des Datamodels auf.
     * 
	 * @return int[][]   Gibt die konvexe Huelle zurueck. Die erste Dimension ist ein Laufindex 
	 * fuer den Punkt. Die zweite Dimension beschreibt den x-Wert [0] und den y-Wert [1].
	 */
	@Override
	public int[][] getConvexHull() {
		return datamodel.getConvexHull();
	}

	
	/**
	 * Gibt die E-Mail-Adresse als String zurueck.
     *
	 * @return String   Gibt die E-Mail-Adresse als String zurueck.
	 */
	@Override
	public String getEmail() {
		return "Manuel.Heder@gmx.de";
	}

	
	
	/**
	 * Gibt die Matrikelnummer als String zurueck.
  	 * @return String   Gibt die Matrikelnummer als String zurueck.
	 */
	@Override
	public String getMatrNr() {
		return "2689600";
	}

	
	
	/**
	 * Gibt den Namen als String zurueck.
     *
	 * @return String    Gibt den Namen als String zurueck.
	 */
	@Override
	public String getName() {
		return "Manuel Heder";
	}


	/**
	 * Gibt den die x-Koordinate des GEK zurück.
     *
	 * @return double    Gibt die x-Koordinate des GEK zurück
	 */
	@Override
	public double getGEKCenterX() {
		return datamodel.getGEKCenterX();
		//return 0.0;
	}


	/**
	 * Gibt den die y-Koordinate des GEK zurück.
     *
	 * @return double    Gibt die y-Koordinate des GEK zurück
	 */
	@Override
	public double getGEKCenterY() {
		return datamodel.getGEKCenterY();
		//return 0.0;
	}


	/**
	 * Gibt den Radius des GEK zurück.
     *
	 * @return double    Gibt den Radius des GEK zurück
	 */
	@Override
	public double getGEKRadius() {
		return datamodel.getGEKRadius();
		//return 0.0;
	}

}
