package de.feu.propra18.q2689600.Gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;


/**
 * Diese Klasse stellt das Fenster fuer die Bedienungsanleitung dar.
 * Der Text ist scrollbar.
 *  
 * @author Manuel Heder
 * 
 */

public class InstructionsWindow extends JFrame{

	/**
	 * Konstuktor; Das Fenster wird mit Namen, Groeße etc definiert und der Text der Bedienungsanleitung über einen weiteren Methodenaufruf hinzugefügt.
     *
	 */
   public InstructionsWindow() {
       setTitle("Bedienungsanleitung");
       setSize(400, 600);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       setVisible(true);
	   initText();

   }

	/**
	 * Diese Methode enthaelt den anzuzeigenden Text der Bedienungsanleitung.
     * Sie wird vom Konstruktor direkt aufgerufen. Der Text wird dem JFrame hinzugefuegt.
	 */
   private void initText() {
	  //Text der Bedienungsanleitung. 
	   JLabel text= new JLabel("<html>\n" + 
			"<body lang=\"en-US\" dir=\"ltr\">\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><u><b>Programmbeschreibung</b></u></p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Das Programm zeigt Punkte auf einer Zeichenfläche</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"an und berechnet dazu das Konturpolynom und die</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"konvexe Hülle.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Bestimmte Extrempunkte (äußerster Punkt links,</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"rechts, oben, unten) werden als magentafarbener  \n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"ausgefüllter Punkt angezeigt. Alle anderen Punkte</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"werden als roter nicht ausgefüllter Kreis angezeigt. \n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Das Konturpolynom zwischen den Extrempunkten hat</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"jeweils eine andere Farbe. Die konvexe Hülle hat die</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Farbe schwarz.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><u><b>Zeichenfläche</b></u></p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Die Zeichenfläche hat die Abmaße von 600x600</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Pixel. Der Ursprung befindet sich in der unteren</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"linken Ecke.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Werden Punkte über eine Datei eingelesen, so passt</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"sich die Zeichenfläche der eingelesen Punktmenge an.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Der Ursprung kann sich dann auch außerhalb der</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"angezeigten Zeichenfläche befinden.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Werden Punkte zufällig hinzugefügt, so geschieht das</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"immer innerhalb der angezeigten Zeichenfläche.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Maximal können Punkte im folgenden Werteberereich</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"angezeigt werden:</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<font color=\"#000000\"><font face=\"Liberation Serif, serif\"><font size=\"3\" style=\"font-size: 12pt\">	x-Koordinaten\n" + 
			"im Bereich von -2147483648 	bis +2147483647 </font></font></font>\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<font color=\"#000000\"><font face=\"Liberation Serif, serif\"><font size=\"3\" style=\"font-size: 12pt\">	y-Koordinaten\n" + 
			"im Bereich von -2147483648 	bis +2147483647 </font></font></font>\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><u><b>Zufällige\n" + 
			"Punkte hinzufügen</b></u></p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Im Kasten in der oberen rechten Ecke können bis zu</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"9999 Punkte mit zufälligen Koordinaten hinzugefügt</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"werden. Alle zufälligen Punkte befinden sich</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"innerhalb der angezeigten Zeichenfläche. Es können</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"mehrfach Punkte hinzugefügt werden.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><u><b>Punkte\n" + 
			"hinzufügen, löschen und verschieben</b></u></p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Im rechten unteren Kasten werden verschiedene</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Möglichkeiten der Manipulation der Punktemenge per</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Maus angeboten.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Nach Aktivierung des Schalters “Punkte hinzufügen”</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"können durch klicken in die Zeichenfläche Punkte</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"hinzugefügt werden.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Nach Aktivierung des Schalters “Punkte verschieben”</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"kann ein Punkt durch anklicken und einer</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"anschließenden Mausbewegung bei gedrückter linker</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Maustaste verschoben werden. Der Punkt kann dabei</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"nur innerhalb der angezeigten Zeichenfläche</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"verschoben werden.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Nach Aktivierung der Schalters “Punkte löschen”</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"kann durch klicken auf einen bestimmten Punkt dieser</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"gelöscht werden.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><u><b>Dateimenü</b></u></p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"In der linken oberen Ecke wird in der Menüleiste das</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Dateimenü mit verschiedenen Optionen angeboten:</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Neu:</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Alle Punkte werden gelöscht.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Öffnen:</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Es können Punkte per Texdatei eingelesen werden.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Die Punkte müssen dabei folgendes Format haben:</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"x-Koordinate+Leerzeichen+y-Koordinate+Zeilenumbruch</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Zeilen, die nicht dieses Format implementieren,</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"werden ignoriert. Punkte können nur innerhalb des</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"folgenden Wertebereichs hinzugefügt werden:</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<font color=\"#000000\"><font face=\"Liberation Serif, serif\"><font size=\"3\" style=\"font-size: 12pt\">	x-Koordinaten\n" + 
			"im Bereich von -2147483648 	bis +2147483647 </font></font></font>\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<font color=\"#000000\"><font face=\"Liberation Serif, serif\"><font size=\"3\" style=\"font-size: 12pt\">	y-Koordinaten\n" + 
			"im Bereich von -2147483648 	bis +2147483647 </font></font></font>\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Speichern:</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Die vorhandene Punktmenge wird in einer Datei</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"abgespeichert. Dazu muss zuvor bereits eine Datei</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"durch die Option “Speichern unter” oder “Öffen”</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"ausgewählt worden sein. Die Datei wird durch das</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"speichern überschrieben.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Punkte werden in dem Format, wie unter der  Option</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"“Öffnen” beschrieben, gespeichert. \n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Speichern unter:</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Es kann eine Datei ausgewählt oder neu erzeugt</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"werden, in der die vorhandenen Punkte abgespeichert</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"werden.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Die Punkte werden dabei in einem Format, wie unter</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"der Option “Öffnen” beschrieben, gespeichert.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Exit:</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Das Programm wird beendet.</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"<br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><u><b>Hilfemenü</b></u></p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; line-height: 100%\"><br/>\n" + 
			"\n" + 
			"</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"In der linken oberen Ecke wird in der Menüleiste das</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"Hilfemenü angeboten. Dort kann über die Option</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"“Bedienungsanleitung” die Bedienungsanleitung</p>\n" + 
			"<p align=\"justify\" style=\"margin-bottom: 0in; font-weight: normal; line-height: 100%; text-decoration: none\">\n" + 
			"angezeigt werden.</p>\n" + 
			"</body>\n" + 
			"</html>");
	   JScrollPane textScrollPane= new JScrollPane(text);
	   this.add(textScrollPane);
   }
   
   
   
}
