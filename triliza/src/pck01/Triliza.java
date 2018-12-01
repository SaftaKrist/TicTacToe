package pck01; // ΒΛΕΠΕ ΓΡΑΜΜΗ 36!!!

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Random;
import javax.swing.JMenuBar;
import java.awt.TextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;

/* ΥΠΟΛΟΓΙΣΤΗΣ ΣΕ ΑΜΥΝΑ:
 * 
 * Intermediate: 1) Αν απελείται από τρίλιζα οπότε την "κόβει"2) Αν μπορεί να κάνει τρίλιζα
 * την κάνει 3) Αν η πρώτη κίνηση του παίχτη είναι στο κέντρο τότε απαντά με γωνία 4) Αν η
 * πρώτη κίνηση του παίχτη δεν είναι γωνία απαντά με γωνία 5) Αν τίποτα από τα παραπάνω παίζει τυχαία.
 * 
 * Expert: 1) Αν απελείται από τρίλιζα οπότε την "κόβει" 2) Αν μπορεί να κάνει τρίλιζα
 * την κάνει 3) Αν η πρώτη κίνηση του παίχτη δεν είναι στο κέντρο τότε απαντά με κέντρο διαφορετικά 
 * απαντα με γωνία 4) Αν ο παίχτης έχει παίξει δύο γωνίες τότε πρέπει να παίξει σταυρό (μιλάμε
 * μόνο για την ΤΕΤΑΡΤΗ ΚΙΝΗΣΗ) 5) Αν οι δύο πρώτες κινήσεις του παίχτη είναι γωνια-σταυρός ή 
 * σταυρός-γωνία πρέπει να παίξει στο σταυρό διαλέγοντας τη στήλη του σταυρού αν είναι πιο άδεια από 
 * τη γραμμή ή τη γραμμή του σταυρού αν είναι πιο άδεια από τη στήλη. (μιλάμε πάλι για ΤΕΤΑΡΤΗ ΚΙΝΗΣΗ) 
 * 6) Αν οι δύο πρώτες κινήσεις του παίχτη είναι ΜΗ ΑΝΤΙΔΙΑΜΜΕΤΡΙΚΟΙ σταυροι (όχι και οι δύο
 * στη γραμμή όχι και οι δύο στη στήλη) τότε ο υπολογιστής πρέπει να κλείσει τη ΓΩΝΙΑ
 * που εναι κενή αναμεσα στα δύο αυτά σύμβολα.  7) Αν οι δύο πρώτες κινήσεις του παίχτη είναι κέντρο
 * και γωνία και επιπλέον ΣΧΗΜΑΤΙΖΟΥΝ ΔΙΑΓΩΝΙΟ με την κίνηση του υπολογιστή τότε ο υπολογιστής ΣΤΗΝ
 * ΤΕΤΑΡΤΗ κίνηση πρέπει να παίξει γωνία!  
 * 
 * ΥΠΟΛΟΓΙΣΤΗΣ ΣΕ ΕΠΙΘΕΣΗ:
 * 
 * Intermediate: 1) Σε πρώτη κίνηση παίζει κατά 50% κέντρο και κατά 50% γωνία.  2) Αν έχει παίξει στην
 * πρώτη κίνησ κέντρο,  ο, τι κι αν παίξει ο χρήστης, η δεύτερη κίνηση έιναι γωνία (εκτός εάν πρέπει 
 * να κόψει απειλούμενη τρίλιζα). 3) Αν παίξει γωνία και ο χρήστης δεν έχει πάρει το κέντρο, τότε παίρνει
 * το κέντρο. 4) Αν ο χρήστης έχει πάρει το κέντρο, τότε παίζει κατά 50% αντιδιαμετρική γωνία και κατά
 * 50% άλλο. (PERFECT!)
 * 
 * ΠΡΟΧΕΙΡΟ: Παγίδα όταν η πρώτη κίνηση είναι κέντρο και η απάντηση του παίχτη είναι γωνία, μπορεί να
 * γίνει ΜΟΝΟ με αντιδιαμετρική γωνία. Η παγίδα πετυχαίνει μόνο αν η δεύτερη απάντηση του παίχτη είναι
 * σταυρός. Τότε αν ο παίχτης απειλεί με τρίλιζα, η παγίδα γίνεται "αυτόματα" (δηλαδή ο υπολογιστής
 * "αμυνόμενος" - αποτρέποντας την τρίλιζα του παίχτη κάνει "διπλή")ενώ αν δεν απειλεί με τρίλιζα τότε:
 * ΑΝ Η ΓΡΑΜΜΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%)
 * ενώ ΑΝ Η ΣΤΗΛΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%).  
 * 
 * 
 * Expert. (Πρέπει να προσπαθεί πάντα να σκαρώσει "διπλή" (παγίδα)).
 * 
 * 1) Παγίδα "του κέντρου"
 * Όταν η πρώτη κίνηση είναι κέντρο και η απάντηση του παίχτη είναι γωνία, παγίδα μπορεί να
 * γίνει ΜΟΝΟ με αντιδιαμετρική γωνία. Η παγίδα πετυχαίνει μόνο αν η δεύτερη απάντηση του παίχτη είναι
 * σταυρός. Τότε αν ο παίχτης απειλεί με τρίλιζα, η παγίδα γίνεται "αυτόματα" (δηλαδή ο υπολογιστής
 * "αμυνόμενος" - αποτρέποντας την τρίλιζα του παίχτη κάνει "διπλή")ενώ αν δεν απειλεί με τρίλιζα τότε:
 * ΑΝ Η ΓΡΑΜΜΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%)
 * ενώ ΑΝ Η ΣΤΗΛΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%).
 * 
 * Όταν η πρώτη κίνηση είναι κέντρο και η απάντηση του παίχτη είναι σταυρός, τότε έχουμε δύο υποπεριπτώσεις
 * παγίδας: α) Να παίξουμε οποιαδήποτε γωνία. Τότε αν αμυνόμενος ο παίχτης απειλεί με τρίλιζα, η παγίδα γίνεται 
 * "αυτόματα" (μέσω της άμυνάς μας). Αν αμυνόμενος ο παίχτης δεν απειλεί με τρίλιζα, τότε και πάλι
 * ΑΝ Η ΓΡΑΜΜΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%)
 * ενώ ΑΝ Η ΣΤΗΛΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%).
 * β)Να παίξουμε σταυρό ΑΛΛΑ ΟΧΙ αντιδιαμετρικά του σταυρού του παίχτη. (Δηλαδή αν ο παίχτης έπαιξε σταυρό μεσαίας
 * γραμμής θα παίξουμε σταυρό μεσαίας στήλης, ενώ αν ο παίχτης έπαιξε σταυρό μεσαίας στήλης θα παίξουμε σταυρό 
 * μεσαίας γραμμής).
 * Αν παίξαμε σταυρό μεσαίας στήλης και ο παίχτης μας κόψει την απειλούμενη τρίλιζα, παίζουμε στη ΓΡΑΜΜΗ της προηγούμενης
 * κίνησής μας (με 50% μία από τις δύο γωνίες). Αν παίξαμε σταυρό μεσαίας γραμμής και ο παίχτης μας κόψει την απειλούμενη
 * τρίλιζα, παίζουμε στη ΣΤΗΛΗ της προηγούμενης κίνησής μας (με 50% μία από τις δύο γωνίες).     
 * 
 * 
 * 2) Παγίδα "της γωνίας"
 *
 * Όταν η πρώτη κίνηση είναι γωνία και ο παίχτης απαντήσει με κέντρο, παίζουμε αντιδιαμετρική γωνία. ΑΝ ο 
 * παίχτης παίξει γωνία τότε ΧΑΝΕΙ διότι κόβοντας την τρίλιζά του του κανουμε "αυτόματα" διπλή.
 * 
 * Όταν η πρώτη κίνηση είναι γωνία και ο παίχτης απαντήσει με γωνία αλλα όχι αντιδιαμετρική γωνία, τότε πάλι
 * παίζουμε αντιδιαμετρική γωνία. Εκει ο παίχτης χάνει σε κάθε περίπτωση διότι κόβοντας την τρίλιζά του, πάλι 
 * του κανουμε "αυτόματα" διπλή. 
 * 
 * Όταν η πρώτη κίνηση είναι γωνία και ο παίχτης απαντήσει με σταυρό, τότε παίζουμε κέντρο.
 * Αν απειλούμαστε με τρίλιζα κερδίσαμε, διότι κόβοντάς τη κάνουμε διπλή. Αν δεν απειλούμαστε με τρίλιζα
 * ΑΝ Η ΓΡΑΜΜΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%)
 * ενώ ΑΝ Η ΣΤΗΛΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%).
 *  
 * Όταν η πρώτη κίνηση είναι γωνία και ο παίχτης απαντήσει αντιδιαμετρική γωνία, τότε παίζουμε κέντρο.
 * (Σημ. Αν μετά απειληθούμε με τρίλιζα κατά 50% ο παίχτης έχει πέσει σε διπλή παγίδα. Συγκεκριμένα
 * αν απειλήσει παίζοντας σταυρό). Αν δεν απειλούμαστε και πάλι 
 * ΑΝ Η ΓΡΑΜΜΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%)
 * ενώ ΑΝ Η ΣΤΗΛΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%).
 * 
 * 
 * 3) Παγίδα "του σταυρού"
 * 
 * Όταν η πρώτη κίνηση είναι σταυρός και ο παίχτης απαντήσει με γωνία που ΔΕΝ ΜΕ ΑΚΟΥΜΠΑ (ΔΕΝ ΕΙΝΑΙ ΓΕΙΤΟΝΙΚΗ
 * ΜΟΥ) τότε ΠΡΕΠΕΙ ΝΑ ΠΑΙΞΩ ΕΤΣΙ ΩΣΤΕ ΝΑ Α Π Ε Ι Λ Η Σ Ω  την αντιδιαμετρική γωνία!!! Αν ο χρήστης κόψει
 * την τρίλιζα, απαντάω με κέντροι και κέρδισα!
 * 
 * Όταν η πρώτη κίνηση είναι σταυρός και ο παίχτης απαντήσει με γωνία που ΜΕ ΑΚΟΥΜΠΑ (ΔΕΝ ΕΙΝΑΙ ΓΕΙΤΟΝΙΚΗ
 * ΜΟΥ) τότε ΠΡΕΠΕΙ ΝΑ ΠΑΙΞΩ ΜΗ ΓΕΙΤΟΝΙΚΗ ΜΟΥ γωνία!!! Αν μετά ο χρήστης ΔΕΝ παίξει κέντρο και ΔΕΝ απειλούμαι
 * τότε παίζω στον αντιδιαμετρικό σταυρό (και κάνω διπλή).
 * 
 * Όταν η πρώτη κίνηση είναι σταυρός και ο παίχτης απαντήσει με αντιδιαμετρικό σταυρό, απαντάω με ΓΕΙΤΟΝΙΚΗ ΤΟΥ
 * ΓΩΝΙΑ και μετά αν δεν απειλούμαι, με γειτονική γωνία και μη αντιδιαμετρικη. (Πιθανόν να κάτσει διπλή)
 * 
 * Όταν η πρώτη κίνηση είναι σταυρός και ο παίχτης απαντήσει με μη αντιδιαμετρικό σταυρό παίζω κέντρο
 * και αν ο παίχτης κλείσει την απειλούμενη τρίλιζα παίζω γειτονική γωνία. 
 * 
 * Όταν η πρώτη κίνηση είναι σταυρός και ο παίχτης απαντήσει με κέντρο παίζω με μή γειτονική γωνία.
 * Αν δεν απειλούμαι με τρίλιζα, τότε αν έχω παίξει σταυρό μεσαίας στήλης παίζω στην γειτονική και 
 * μη αντιδιαμετρικη γωνία 
 * 
 * 
 *  
 * */


public class Triliza {
	
	private JFrame frame;
	
	private JButton cells[]; 
	private Random rn; /* Είναι μία "κληρωτίδα" ακεραίων (από 0 έως 8) */
	private String level="intermediate";
	private String playerSymbol="O";
	private String computerSymbol="X";
	private Color defaultColor = Color.BLACK;
	private Color winningColor = Color.RED;
	private boolean computerAttackMode=false; /* Όταν είναι true επιτίθεται ο υπολογιστής
	δηλαδή ο υπολογιστής παίζει πρώτος, αλλιώς όταν είναι false επιτίθεται ο παίχτης πρώτος. */
	private boolean playerTurn=false;
	/* Aυτή η μεταβλητή θα αλλάζει σε κάθε κίνηση.  Όταν είναι true, παίζει ο παίχτης. 
	 * Όταν παίξει, θα γίνεται false. Αφού παίξει ο υπολογιστής θα την ξαναθέτει true για να
	 * μπορεί να ξαναπαίζει ο παίχτης. 
	 * Αυτή η μεταβλητή στο player-attack-mode θα αρχικοποιείται στο true.
	 * Στο computer-attack-mode θα αρχικοποιείται στο false. */	
	int trap_category;
	/* Mε βάση αυτήν τη μεταβλητή ο expert διαλέγει την κατηγορία παγίδας που θα καταστρώσει 
	 * (Τιμές: 0, 1 ή 2) */
	
	JRadioButtonMenuItem beginnerRadioSelection; 
	JRadioButtonMenuItem intermediateRadioSelection;
	JRadioButtonMenuItem expertRadioSelection;
	JRadioButtonMenuItem playerSymbolSelectionO;
	JRadioButtonMenuItem playerSymbolSelectionX;
	
	/* Οι παρακα΄τω μεταβλητές πρέπει να επαναρχικοποιούνται σε κάθε νέο παιχνίδι. */
	int counter=0; /* Μετράει τα κατηλειμένα τετράγωνα */
	boolean winFlag=false; /* Σηκώνεται όταν βρεθεί νικητής */
	int cross_trap_flag;//βοηθητικη μεταβλητη για την παγιδα του σταυρου στην επιθεση του υπολογιστη
	
	
	
	
	/* Επιστρέφει true όταν το περίεχόμενο των τριων κελιών είναι ίσα! */
	private boolean trinityMatch(int cell_1,int cell_2,int cell_3){
		
		System.out.print("cells["+cell_1+"]="+cells[cell_1].getText());
		System.out.print(" cells["+cell_2+"]="+cells[cell_2].getText());
		System.out.println(" cells["+cell_3+"]="+cells[cell_3].getText());
		
		/* Αν υπάρχει έστω και ένα κενό επιστρέφει false */
		if(cells[cell_1].getText().equals(""))
			return false;
		if(cells[cell_2].getText().equals(""))
			return false;
		if(cells[cell_3].getText().equals(""))
			return false;
		
		/* Διαφορετικά ελέγχεται αν σχηματίζεται τριάδα */
		if(!cells[cell_1].getText().equals(cells[cell_2].getText()))
			return false;
		if(!cells[cell_2].getText().equals(cells[cell_3].getText()))
			return false;
		return true;	
		
	}
	
	
	/* Επιστρέφει ένα τετράγωνο το οποίο αν συμπληρωθεί σχηματίζεται τρίλιζα
	 * (είτε από τη μεριά του παίχτη είτε απο τη μεριά του υπολογιστή).
	 * Ο υπολογιστής μπορεί να την καλεί είτε για να εμποδίζει τριλιζες του
	 * παίχτη είτε για να βρίσκει δικές του (αναλογα με το διοχετευόμενο searchingSymbol).
	 * Αν δεν βρεθεί σχηματιζόμενη τρίλιζα τότε επιστρέφεται -1. */
	private int ticTacToeThreat(String searchingSymbol){
		
		int firstLinePlayerSymbols=0;
		int firstLineComputerSymbols=0;
		int secondLinePlayerSymbols=0;
		int secondLineComputerSymbols=0;
		int thirdLinePlayerSymbols=0;
		int thirdLineComputerSymbols=0; 
		
		int firstColumnPlayerSymbols=0;
		int firstColumnComputerSymbols=0;
		int secondColumnPlayerSymbols=0;
		int secondColumnComputerSymbols=0;
		int thirdColumnPlayerSymbols=0;
		int thirdColumnComputerSymbols=0;
		
		int diagonalPlayerSymbols=0;
		int diagonalComputerSymbols=0;
		
		int antiDiagonalPlayerSymbols=0;
		int antiDiagonalComputerSymbols=0;
		
		if(cells[0].getText().equals(playerSymbol)){
			firstLinePlayerSymbols++;
			firstColumnPlayerSymbols++;
			diagonalPlayerSymbols++;
		}
		if(cells[0].getText().equals(computerSymbol)){
			firstLineComputerSymbols++;
			firstColumnComputerSymbols++;
			diagonalComputerSymbols++;
		}
		if(cells[1].getText().equals(playerSymbol)){
			firstLinePlayerSymbols++;
			secondColumnPlayerSymbols++;
		}
		if(cells[1].getText().equals(computerSymbol)){
			firstLineComputerSymbols++;
			secondColumnComputerSymbols++;
		}
		if(cells[2].getText().equals(playerSymbol)){
			firstLinePlayerSymbols++;
			thirdColumnPlayerSymbols++;
			antiDiagonalPlayerSymbols++;
		}
		if(cells[2].getText().equals(computerSymbol)){
			firstLineComputerSymbols++;
			thirdColumnComputerSymbols++;
			antiDiagonalComputerSymbols++;
		}
		if(cells[3].getText().equals(playerSymbol)){
			secondLinePlayerSymbols++;
			firstColumnPlayerSymbols++;
		}
		if(cells[3].getText().equals(computerSymbol)){
			secondLineComputerSymbols++;
			firstColumnComputerSymbols++;
		}
		if(cells[4].getText().equals(playerSymbol)){
			secondLinePlayerSymbols++;
			secondColumnPlayerSymbols++;
			diagonalPlayerSymbols++;
			antiDiagonalPlayerSymbols++;
		}
		if(cells[4].getText().equals(computerSymbol)){
			secondLineComputerSymbols++;
			secondColumnComputerSymbols++;
			diagonalComputerSymbols++;
			antiDiagonalComputerSymbols++;
		}
		if(cells[5].getText().equals(playerSymbol)){
			secondLinePlayerSymbols++;
			thirdColumnPlayerSymbols++;
		}
		if(cells[5].getText().equals(computerSymbol)){
			secondLineComputerSymbols++;
			thirdColumnComputerSymbols++;
		} 
		if(cells[6].getText().equals(playerSymbol)){
			thirdLinePlayerSymbols++;
			firstColumnPlayerSymbols++;
			antiDiagonalPlayerSymbols++;
		}
		if(cells[6].getText().equals(computerSymbol)){
			thirdLineComputerSymbols++;
			firstColumnComputerSymbols++;
			antiDiagonalComputerSymbols++;
		}
		if(cells[7].getText().equals(playerSymbol)){
			thirdLinePlayerSymbols++;
			secondColumnPlayerSymbols++;
		}
		if(cells[7].getText().equals(computerSymbol)){
			thirdLineComputerSymbols++;
			secondColumnComputerSymbols++;
		}
		if(cells[8].getText().equals(playerSymbol)){
			thirdLinePlayerSymbols++;
			thirdColumnPlayerSymbols++;
			diagonalPlayerSymbols++;
		}
		if(cells[8].getText().equals(computerSymbol)){
			thirdLineComputerSymbols++;
			thirdColumnComputerSymbols++;
			diagonalComputerSymbols++;
		}
		

		if(searchingSymbol.equals(playerSymbol)){
		/* Κώδικας στον οποίο ο υπολογιστης ψαχνει να κόψει τις τρίλιζες του παίχτη. */
			
			if(firstLinePlayerSymbols-firstLineComputerSymbols==2){
				
				if(cells[0].getText().equals(""))
					return 0;
				if(cells[1].getText().equals(""))
					return 1;
				return 2;
			}
			
			if(secondLinePlayerSymbols-secondLineComputerSymbols==2){
				
				if(cells[3].getText().equals(""))
					return 3;
				if(cells[4].getText().equals(""))
					return 4;
				return 5;
			}
			
			if(thirdLinePlayerSymbols-thirdLineComputerSymbols==2){
				
				if(cells[6].getText().equals(""))
					return 6;
				if(cells[7].getText().equals(""))
					return 7;
				return 8;
			}
			
			 if(firstColumnPlayerSymbols-firstColumnComputerSymbols==2){
				
				if(cells[0].getText().equals(""))
					return 0;
				if(cells[3].getText().equals(""))
					return 3;
				return 6;
			}
			 
			 if(secondColumnPlayerSymbols-secondColumnComputerSymbols==2){
					
					if(cells[1].getText().equals(""))
						return 1;
					if(cells[4].getText().equals(""))
						return 4;
					return 7;
			}			 
			 if(thirdColumnPlayerSymbols-thirdColumnComputerSymbols==2){
					
					if(cells[2].getText().equals(""))
						return 2;
					if(cells[5].getText().equals(""))
						return 5;
					return 8;
				}
			 if(diagonalPlayerSymbols-diagonalComputerSymbols==2){
					
					if(cells[0].getText().equals(""))
						return 0;
					if(cells[4].getText().equals(""))
						return 4;
					return 8;
				}
			 if(antiDiagonalPlayerSymbols-antiDiagonalComputerSymbols==2){
					
					if(cells[2].getText().equals(""))
						return 2;
					if(cells[4].getText().equals(""))
						return 4;
					return 6;
				}
			
			
			return -1;
		}
		else{
			/* Κώδικας στον οποίο ο υπολογιστης ψαχνει να κάνει τρίλιζα. */	
			// Σε οποια γραμμή στήλη διαγώνιο το "σκορ" είναι 2-0 πρέπει να βάλει στο κενό
			
			
			
			if(firstLineComputerSymbols-firstLinePlayerSymbols==2){
				
				if(cells[0].getText().equals(""))
					return 0;
				if(cells[1].getText().equals(""))
					return 1;
				return 2;
			}
			
			if(secondLineComputerSymbols-secondLinePlayerSymbols==2){
				
				if(cells[3].getText().equals(""))
					return 3;
				if(cells[4].getText().equals(""))
					return 4;
				return 5;
			}
			if(thirdLineComputerSymbols-thirdLinePlayerSymbols==2){
				
				if(cells[6].getText().equals(""))
					return 6;
				if(cells[7].getText().equals(""))
					return 7;
				return 8;
			}
			 if(firstColumnComputerSymbols-firstColumnPlayerSymbols==2){
				
				if(cells[0].getText().equals(""))
					return 0;
				if(cells[3].getText().equals(""))
					return 3;
				return 6;
			}
			 if(secondColumnComputerSymbols-secondColumnPlayerSymbols==2){
					
					if(cells[1].getText().equals(""))
						return 1;
					if(cells[4].getText().equals(""))
						return 4;
					return 7;
				}
			 if(thirdColumnComputerSymbols-thirdColumnPlayerSymbols==2){
					
					if(cells[2].getText().equals(""))
						return 2;
					if(cells[5].getText().equals(""))
						return 5;
					return 8;
				}
			 if(diagonalComputerSymbols-diagonalPlayerSymbols==2){
					
					if(cells[0].getText().equals(""))
						return 0;
					if(cells[4].getText().equals(""))
						return 4;
					return 8;
				}
			 if(antiDiagonalComputerSymbols-antiDiagonalPlayerSymbols==2){
					
					if(cells[2].getText().equals(""))
						return 2;
					if(cells[4].getText().equals(""))
						return 4;
					return 6;
				}
			
			
			
			
			
			return -1;
		}
		
		
	}
	
	private void setDefaultColor(){
		for(int i=0;i<9;i++)
			cells[i].setForeground(defaultColor);
	}
	
	private void setWinningColorInWinningTrinity(int cell_1,int cell_2,int cell_3){
		
		String winningSymbol=cells[cell_1].getText();
		
		cells[cell_1].setForeground(winningColor);
		cells[cell_2].setForeground(winningColor);
		cells[cell_3].setForeground(winningColor);
		
		cells[cell_1].setText(winningSymbol);
		cells[cell_2].setText(winningSymbol);
		cells[cell_3].setText(winningSymbol);
		
	}
	
	
	private boolean checkWin(){
		
		String winningSymbol;
		
		boolean win=false;
		
		if (counter<5)
			return false;
		
		if(trinityMatch(0,1,2)){
				
			System.out.println("Match 0,1,2");
			setWinningColorInWinningTrinity(0,1,2);
			
			win = true;
			
		}
		else if(trinityMatch(3,4,5)){
			
			System.out.println("Match 3,4,5");
			setWinningColorInWinningTrinity(3,4,5);
			
			win = true;
			
		}	
		else if(trinityMatch(6,7,8)){
			
			System.out.println("Match 6,7,8");
			setWinningColorInWinningTrinity(6,7,8);
			
			win = true;
		}	
		else if(trinityMatch(0,3,6)){
			
			System.out.println("Match 0,3,6");
			setWinningColorInWinningTrinity(0,3,6);
			
			win = true;
		}	
		else if(trinityMatch(1,4,7)){
			
			System.out.println("Match 1,4,7");
			setWinningColorInWinningTrinity(1,4,7);
			
			win = true;
		}	
		else if(trinityMatch(2,5,8)){
			
			System.out.println("Match 2,5,8");
			setWinningColorInWinningTrinity(2,5,8);
				
			win = true;
		}	
		else if(trinityMatch(0,4,8)){
			
			System.out.println("Match 0,4,8");
			setWinningColorInWinningTrinity(0,4,8);

			win = true;
			
		}	
		else if(trinityMatch(2,4,6)){
			
			System.out.println("Match 2,4,6");
			setWinningColorInWinningTrinity(2,4,6);
			
			win = true;
		}
		
		if(win == true){
			intermediateRadioSelection.setEnabled(true);
			beginnerRadioSelection.setEnabled(true);
			expertRadioSelection.setEnabled(true);
			playerSymbolSelectionO.setEnabled(true);
			playerSymbolSelectionX.setEnabled(true);
		}
		
		return win;
	}
	
    private void computerMove(){
    	
    	System.out.println("Eίμαι ένας compiuterίδης...");
    	
    	if(counter==8){
    		
    		intermediateRadioSelection.setEnabled(true);
			beginnerRadioSelection.setEnabled(true);
			expertRadioSelection.setEnabled(true);
			playerSymbolSelectionO.setEnabled(true);
			playerSymbolSelectionX.setEnabled(true);
    		
    	}
    	
    	if(counter<9){
    		
	    	if(computerAttackMode==true){
	    		System.out.println("...και επιτίθεμαι!");
	    		if(level.equals("beginner"))
	    			beginnerLevelComputerMove();
	    		else if(level.equals("intermediate"))
    				intermediateLevelComputerAttackMove();	
	    		else {
	    	
	    			
	    			
	    			
	    			if(trap_category==0)
	    				expertLevelComputerAttackMove();
	    			else if(trap_category==1)
	    				expertLevelComputerAttackMove_2();
	    			else 
	    				expertLevelComputerAttackMove_3();
	    		
	    		}
	    	}
	    	
	    	else{
	    		System.out.println("...και αμύνομαι!");
		    	if(level.equals("beginner"))
		    		beginnerLevelComputerMove();
		    	else if(level.equals("intermediate"))
		    		intermediateLevelComputerDefenceMove();
		    	else
		    		expertLevelComputerDefenceMove();
		    	
	    	    
	    	
	    	}
	    	counter++;
    	}
    	System.out.println("counter = "+counter);
    	
    	if(checkWin())
    		winFlag=true;
    }
    
    


	private void beginnerLevelComputerMove(){
    	
    	
    	if(ticTacToeThreat(computerSymbol)!=-1){
    		cells[ticTacToeThreat(computerSymbol)].setText(computerSymbol);
    		return;
    	}
    	if(ticTacToeThreat(playerSymbol)!=-1){
    		cells[ticTacToeThreat(playerSymbol)].setText(computerSymbol);
    		return;
    		
    	}
    	
    	
    	
    	    	
    	int randomCell = Math.abs(rn.nextInt())%9;

    	/* Οσο δεν βρίσκει "κενό" κουμπί, προχωρά σε νέα κλήρωση! */
    	while(!cells[randomCell].getText().equals(""))
    		randomCell = Math.abs(rn.nextInt())%9;
    	
    	cells[randomCell].setText(computerSymbol);
    	
    }
    
    private void intermediateLevelComputerDefenceMove(){
    	
    	
    	if(ticTacToeThreat(computerSymbol)!=-1){
    		cells[ticTacToeThreat(computerSymbol)].setText(computerSymbol);
    		return;
    	}
    	if(ticTacToeThreat(playerSymbol)!=-1){
    		cells[ticTacToeThreat(playerSymbol)].setText(computerSymbol);
    		return;
    		
    	}
    	
    	if(counter==1){
    		int realCell=0;
    		int randomCell = Math.abs(rn.nextInt())%4;
    		if (randomCell==0)
    			realCell=0;
    		if (randomCell==1)
    			realCell=2;
    		if (randomCell==2)
    			realCell=6;
    		if (randomCell==3)
    			realCell=8;
    		

        	/* Οσο δεν βρίσκει "κενό" κουμπί, προχωρά σε νέα κλήρωση! */
        	while(!cells[realCell].getText().equals("")){
        		randomCell = Math.abs(rn.nextInt())%4;
        		if (randomCell==0)
        			realCell=0;
        		if (randomCell==1)
        			realCell=2;
        		if (randomCell==2)
        			realCell=6;
        		if (randomCell==3)
        			realCell=8;        	
        		}
        	
        	cells[realCell].setText(computerSymbol);
        	return;
    		
    	}
    	
    	
    	    	
    	int randomCell = Math.abs(rn.nextInt())%9;

    	/* Οσο δεν βρίσκει "κενό" κουμπί, προχωρά σε νέα κλήρωση! */
    	while(!cells[randomCell].getText().equals(""))
    		randomCell = Math.abs(rn.nextInt())%9;
    	
    	cells[randomCell].setText(computerSymbol);
    	
    }
    
    
    
    
    

    private void intermediateLevelComputerAttackMove(){
    	int randomCell=0;
    	int realCell=0;
    	if(ticTacToeThreat(computerSymbol)!=-1){
    		cells[ticTacToeThreat(computerSymbol)].setText(computerSymbol);
    		return;
    	}
    	if(ticTacToeThreat(playerSymbol)!=-1){
    		cells[ticTacToeThreat(playerSymbol)].setText(computerSymbol);
    		return;
    		
    	}
    	
    	if(counter==0){
    		randomCell = Math.abs(rn.nextInt())%2;
    		if (randomCell==0)
    			cells[4].setText(computerSymbol);
    		else{
    			randomCell = Math.abs(rn.nextInt())%4;
    			if (randomCell==0)
    				realCell=0;
    			else if (randomCell==1)
    				realCell=2;
    			else if (randomCell==2)
    				realCell=6;
    			else
    				realCell=8;
    			cells[realCell].setText(computerSymbol);
    			
    		}
    		return;
    	}
    	
    	if(counter==2){
    		if(cells[4].getText().equals(computerSymbol)){
    			if (randomCell==0)
        			realCell=0;
        		else if (randomCell==1)
        			realCell=2;
        		else if (randomCell==2)
        			realCell=6;
        		else
        			realCell=8;

            	/* Οσο δεν βρίσκει "κενό" κουμπί, προχωρά σε νέα κλήρωση! */
        		while(!cells[realCell].getText().equals("")){
        			
            		randomCell = Math.abs(rn.nextInt())%4;
            		
            		if (randomCell==0)
            			realCell=0;
            		if (randomCell==1)
            			realCell=2;
            		if (randomCell==2)
            			realCell=6;
            		if (randomCell==3)
            			realCell=8;        	
            	}
            	
            	cells[realCell].setText(computerSymbol);
            	return;
    		
    		}else{
    			if(!cells[4].getText().equals(playerSymbol)){
    				cells[4].setText(computerSymbol);
    			}else{
    				
    				randomCell = Math.abs(rn.nextInt())%2;
    				if (randomCell==0){
    					if(cells[0].getText().equals(computerSymbol))
    						cells[8].setText(computerSymbol);
    					if(cells[2].getText().equals(computerSymbol))
    						cells[6].setText(computerSymbol);
    					if(cells[6].getText().equals(computerSymbol))
    						cells[2].setText(computerSymbol);
    					if(cells[8].getText().equals(computerSymbol))
    						cells[0].setText(computerSymbol);
    				}else{
    					while(!cells[randomCell].getText().equals(""))
    			    		randomCell = Math.abs(rn.nextInt())%9;
    			    	
    			    	cells[randomCell].setText(computerSymbol);
    			    	
    			    }
    	    			
    	    	}
    				
    				
    			return;
    		}
    			
    	}
    	
    	
		randomCell = Math.abs(rn.nextInt())%9;

    	/* Οσο δεν βρίσκει "κενό" κουμπί, προχωρά σε νέα κλήρωση! */
    	while(!cells[randomCell].getText().equals(""))
    		randomCell = Math.abs(rn.nextInt())%9;
    	
    	cells[randomCell].setText(computerSymbol);
		return;
			
    			
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    	private void expertLevelComputerAttackMove() {
    		
    		int randomNumber=0;
    		int randomCell=0;
    		int randomCrossCell=0;
        	int realCell=0;
        	int chosenCell=0;
        	
        	/* Αν ο υπολογιστής μπορεί να κάνει τρίλιζα, την κάνει. */
        	if(ticTacToeThreat(computerSymbol)!=-1){
        		cells[ticTacToeThreat(computerSymbol)].setText(computerSymbol);
        		return;
        	}
        	
        	/* Αν ο υπολογιστής απειλείται, αποτρέπει την τρίλιζα του παίχτη. */
        	if(ticTacToeThreat(playerSymbol)!=-1){
        		cells[ticTacToeThreat(playerSymbol)].setText(computerSymbol);
        		return;
        		
        	}
        	        	
        	/* ΠΡΟΣΟΧΗ!!! ΠΑΡΑΚΑΤΩ ΥΛΟΠΟΙΕΙΤΑΙ ΜΟΝΑΧΑ Η ΠΑΓΙΔΑ ΤΟΥ ΚΕΝΤΡΟΥ!!!! */
        	 
        	/* Αν η τρέχουσα κίνηση είναι η πρώτη,τότε ο υπολογιστής παίζει κέντρο. */
        	if(counter==0){
        		cells[4].setText(computerSymbol);
        		return;
        	}
        	if(counter==2){
        		
        		/* Εδώ έχουμε τη ΔΕΥΤΕΡΗ ΚΙΝΗΣΗ ΤΟΥ ΥΠΟΛΟΓΙΣΤΗ (ο παίχτης έχει "απαντήσει" 
        		 * στην πρώτη κίνηση του υπολογιστή η οποία ήταν κέντρο!) */
        		
        		/* Αν η "πρώτη απάντηση" του παίχτη ήταν γωνία, τότε ο υπολογιστής
        		 * παίζει στην αντιδιαμετρική γωνία. */
        		if(cells[0].getText().equals(playerSymbol)){
        			cells[8].setText(computerSymbol);
        		}
        		else if(cells[2].getText().equals(playerSymbol)){
        			cells[6].setText(computerSymbol);
        		}
        		else if(cells[6].getText().equals(playerSymbol)){
        			cells[2].setText(computerSymbol);
        		}
        		else if(cells[8].getText().equals(playerSymbol)){
        			cells[0].setText(computerSymbol);
        		}
        		else{

/* Όταν η πρώτη κίνηση είναι κέντρο και η απάντηση του παίχτη είναι σταυρός, τότε έχουμε δύο υποπεριπτώσεις
 * παγίδας: α) Να παίξουμε οποιαδήποτε γωνία. Τότε αν αμυνόμενος ο παίχτης απειλεί με τρίλιζα, η παγίδα γίνεται 
 * "αυτόματα" (μέσω της άμυνάς μας). Αν αμυνόμενος ο παίχτης δεν απειλεί με τρίλιζα, τότε και πάλι
 * ΑΝ Η ΓΡΑΜΜΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%)
 * ενώ ΑΝ Η ΣΤΗΛΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%).
 * β)Να παίξουμε σταυρό ΑΛΛΑ ΟΧΙ αντιδιαμετρικά του σταυρού του παίχτη. (Δηλαδή αν ο παίχτης έπαιξε σταυρό μεσαίας
 * γραμμής θα παίξουμε σταυρό μεσαίας στήλης, ενώ αν ο παίχτης έπαιξε σταυρό μεσαίας στήλης θα παίξουμε σταυρό 
 * μεσαίας γραμμής).
 * Αν παίξαμε σταυρό μεσαίας στήλης και ο παίχτης μας κόψει την απειλούμενη τρίλιζα, παίζουμε στη ΓΡΑΜΜΗ της προηγούμενης
 * κίνησής μας (με 50% μία από τις δύο γωνίες). Αν παίξαμε σταυρό μεσαίας γραμμής και ο παίχτης μας κόψει την απειλούμενη
 * τρίλιζα, παίζουμε στη ΣΤΗΛΗ της προηγούμενης κίνησής μας (με 50% μία από τις δύο γωνίες).     
 * 
 * 
 * */
        			/* Αν η "πρώτη απάντηση" του παίχτη ήταν σταυρός τότε με 50% πιθανότητα θα παίξει μία τυχαία γωνία,
        			 * ενώ με 50% πιθανμότητα θα παίξει σταυρό, αλλά όχια αντιδιαμετρικά του σταυρού του παίχτη*/
        			
        			if(rn.nextInt()%2==0){
        				
        				/* Υλοποίηση περίπτωσης επιλογής τυχαίας γωνίας. */
        				
        				/* Επιλέγεται τυχαία μία από τις τέσσερις γωνίες. */
        				randomCell =2*(Math.abs(rn.nextInt())%4);
        				if(randomCell==4)
        					randomCell=8;
            			/* H παραπάνω εντολή είναι ένα μαθηματικό κολπάκι:
            			 * Οι γωνίες είναι στις θέσεις 0,2,6 και 8
            			 * Σε πρώτη φάση επιλέγεται ένας αριθμός εκ' των 0,2,4,6.
            			 * Μετά απλά αν επιλέχθηκε το 4, αυτό γίνεται 8 */
        				System.out.println("randomCell "+randomCell);
	            		
	        			cells[randomCell].setText(computerSymbol);
        			}
        			else{
        				
        				/* Υλοποίηση περίπτωσης επιλογής σταυρού αλλά ΟΧΙ αντιδιαμετρικού με τον παίχτη! */
        				int symmetrical_cross;
        				
        				/* Αρχικά υπολογίζεται η θέση του αντιδιαμετρικού σταυρού. */
        				if(cells[1].getText().equals(playerSymbol))
        					symmetrical_cross=7;
        				else if(cells[3].getText().equals(playerSymbol))
        					symmetrical_cross=5;
        				else 	if(cells[5].getText().equals(playerSymbol))
        					symmetrical_cross=3;
        				else 	
        					symmetrical_cross=1;
        				
        				
        				randomCrossCell= 2*(Math.abs(rn.nextInt())%4)+1; 
        				/* Mαθηματικό κολπάκι: 2*{0,1,2,3}+1 = {1,3,5,7}
        				 * όπου 1,3,5,7 οι θέσεις του σταυρού! */
        				
        				while(!cells[randomCrossCell].getText().equals("") || randomCrossCell==symmetrical_cross)
        					randomCrossCell= 2*(Math.abs(rn.nextInt())%4)+1; 
        			        		    	
        				cells[randomCrossCell].setText(computerSymbol);
        				
        			}
        		        			
        		}   		
        		
        		return;
        	}
    		if(counter==4){
    			
    			/* Εδώ είναι η υλοποίηση της τρίτης κίνησης του υπολογιστή
    			 * στην περίπτωση που προσπαθεί να εφαρμόσει την παγίδα του κέντρου.
    			 * Θα πρέπει αρχικά να εντοπίσουμε αν η δεύτερή του κίνηση ήταν γωνία
    			 * ή αν ήταν σταυρός! */
    			
    			boolean crossFlag=false;
    			
    			for(int i=1;i<=7;i+=2){
    				if(cells[i].getText().equals(computerSymbol)){
    					crossFlag=true;
    					break;
    				}	
    			}
    			/* Aν μετά τις παραπάνω γραμμες το crossFlag είνμαι true σημαίνει
    			 * ότι η δεύτερη κίνηση του υπολογιστή ήταν σταυρός, ανώ αν είναι false σημαίνει
    			 * ότι η δεύτερη κίνηση του υπολογιστή ήταν γωνία.*/
    			
    			if(crossFlag==false){
    				
    				/* Περίπτωση στην οποία η πρώτη κίνηση του υπολογιστή ήταν κέντρο και η
    				 * δεύτερη γωνία */
	    			boolean row_1=true,row_2=true,row_3=true,row_4=true;
	    			
	    			if(cells[1].getText().equals(playerSymbol))
	    				row_1=false;
	    			if(cells[3].getText().equals(playerSymbol))
	    				row_4=false;
	    			if(cells[5].getText().equals(playerSymbol))
	    				row_2=false;
	    			if(cells[7].getText().equals(playerSymbol))
	    				row_3=false;
	    			if(cells[0].getText().equals(computerSymbol)){
	    				randomCell = Math.abs(rn.nextInt())%2;
	    				if(row_1==true){
	    					if(randomCell==0)
	    						cells[1].setText(computerSymbol);
	    					else
	    						cells[2].setText(computerSymbol);
	    				}
	    				if(row_4==true){
	    					if(randomCell==0)
	    						cells[3].setText(computerSymbol);
	    					else
	    						cells[6].setText(computerSymbol);
	    				}
	    			}else if(cells[2].getText().equals(computerSymbol)){
	    				randomCell = Math.abs(rn.nextInt())%2;
	    				if(row_1==true){
	    					if(randomCell==0)
	    						cells[0].setText(computerSymbol);
	    					else
	    						cells[1].setText(computerSymbol);
	    				}
	    				if(row_2==true){
	    					if(randomCell==0)
	    						cells[5].setText(computerSymbol);
	    					else
	    						cells[8].setText(computerSymbol);
	    				}
	    			}else if(cells[6].getText().equals(computerSymbol)){
	    				
	    				randomCell = Math.abs(rn.nextInt())%2;
	    				if(row_4==true){
	    					if(randomCell==0)
	    						cells[0].setText(computerSymbol);
	    					else
	    						cells[3].setText(computerSymbol);
	    				}
	    				if(row_3==true){
	    					if(randomCell==0)
	    						cells[7].setText(computerSymbol);
	    					else
	    						cells[8].setText(computerSymbol);
	    				}
	    			}else if(cells[8].getText().equals(computerSymbol)){
	    				randomCell = Math.abs(rn.nextInt())%2;
	    				if(row_2==true){
	    					if(randomCell==0)
	    						cells[2].setText(computerSymbol);
	    					else
	    						cells[5].setText(computerSymbol);
	    				}
	    				if(row_3==true){
	    					if(randomCell==0)
	    						cells[6].setText(computerSymbol);
	    					else
	    						cells[7].setText(computerSymbol);
	    				}
	    			}
    			}
    			else{

    				/* Περίπτωση στην οποία η πρώτη κίνηση του υπολογιστή ήταν κέντρο και η
    				 * δεύτερη σταυρός */
    				/*Ο υπολογιστης παιζει σε μια απο τις δυο κενές γωνίες της στηλης η της γραμης της προηγουμενης
    				 * κίνησης του
    				 */
    				if(cells[1].getText().equals(computerSymbol)){
    					randomCell = Math.abs(rn.nextInt())%2;
    					if (randomCell==0)
    						realCell=0;
    					else
    						realCell=2;
    					cells[realCell].setText(computerSymbol);
    					
    					
    					
    				}
    				if(cells[3].getText().equals(computerSymbol)){
    					randomCell = Math.abs(rn.nextInt())%2;
    					if (randomCell==0)
    						realCell=0;
    					else
    						realCell=6;
    					cells[realCell].setText(computerSymbol);
    				}
    				if(cells[5].getText().equals(computerSymbol)){
    					randomCell = Math.abs(rn.nextInt())%2;
    					if (randomCell==0)
    						realCell=2;
    					else
    						realCell=8;
    					cells[realCell].setText(computerSymbol);
    				}
    				if(cells[7].getText().equals(computerSymbol)){
    					randomCell = Math.abs(rn.nextInt())%2;
    					if (randomCell==0)
    						realCell=6;
    					else
    						realCell=8;
    					cells[realCell].setText(computerSymbol);
    				}
    				
    				
    				
    				
    				
    				
    				
    				
    			}
    			return;	
    		}
    		
    			
        	randomCell = Math.abs(rn.nextInt())%9;

        	/* Οσο δεν βρίσκει "κενό" κουμπί, προχωρά σε νέα κλήρωση! */
        	while(!cells[randomCell].getText().equals(""))
        		randomCell = Math.abs(rn.nextInt())%9;
        	
        	cells[randomCell].setText(computerSymbol);
    		return;
    	
	}
    	private void expertLevelComputerAttackMove_2() {
    		
    	/*	* 2) Παγίδα "της γωνίας"
    	 *
    	 * Όταν η πρώτη κίνηση είναι γωνία και ο παίχτης απαντήσει με κέντρο, παίζουμε αντιδιαμετρική γωνία. ΑΝ ο 
    	 * παίχτης παίξει γωνία τότε ΧΑΝΕΙ διότι κόβοντας την τρίλιζά του του κανουμε "αυτόματα" διπλή.
    	 * 
    	 * Όταν η πρώτη κίνηση είναι γωνία και ο παίχτης απαντήσει με γωνία αλλα όχι αντιδιαμετρική γωνία, τότε πάλι
    	 * παίζουμε αντιδιαμετρική γωνία. Εκει ο παίχτης χάνει σε κάθε περίπτωση διότι κόβοντας την τρίλιζά του, πάλι 
    	 * του κανουμε "αυτόματα" διπλή. 
    	 * 
    	 * Όταν η πρώτη κίνηση είναι γωνία και ο παίχτης απαντήσει με σταυρό, τότε παίζουμε κέντρο.
    	 * Αν απειλούμαστε με τρίλιζα κερδίσαμε, διότι κόβοντάς τη κάνουμε διπλή. Αν δεν απειλούμαστε με τρίλιζα
    	 * ΑΝ Η ΓΡΑΜΜΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%)
    	 * ενώ ΑΝ Η ΣΤΗΛΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%).
    	 *  
    	 * Όταν η πρώτη κίνηση είναι γωνία και ο παίχτης απαντήσει αντιδιαμετρική γωνία, τότε παίζουμε κέντρο.
    	 * (Σημ. Αν μετά απειληθούμε με τρίλιζα κατά 50% ο παίχτης έχει πέσει σε διπλή παγίδα. Συγκεκριμένα
    	 * αν απειλήσει παίζοντας σταυρό). Αν δεν απειλούμαστε και πάλι 
    	 * ΑΝ Η ΓΡΑΜΜΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%)
    	 * ενώ ΑΝ Η ΣΤΗΛΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά τότε παίζει σε ένα από τα 2 κενά (με 50%).
    	 * 
    	 * Τελικά τα παραπάνω υλοποιούνται ως εξής:
    	 * 
    	 * Στην πρώτη κίνηση ο υπολογιστής παίζει γωνία.
    	 * 
    	 * Αν ο παίχτης απαντήσει με κέντρο ή μη αντιδιαμετρική γωνία, ο υπολογιστής παίζει στη 2η του κίνηση αντιδιαμετρική γωνία.
    	 * Σε κάθε άλλη περίπτωση ο υπολογιστής απαντά στη 2η του κίνηση με κέντρο.
    	 * 
    	 * Στην τρίτη του κίνηση αν ο υπολογιστής δεν κάνει τρίλιζα και δεν απειλείται, τότε σίγουρα το παιχνίδι
    	 * έχει έρθει σε μία κατάσταση στην οποία ο υπολογιστής έχει κλείσει δύο τετραγωνα κάποιας διαγωνίου
    	 * ενώ ο παίκτης έχει "κόψει" την τρίλιζα της διαγωνίου και έχσει παίξει και σε κάποιο σταυρό
    	 * ΧΩΡΙΣ ΝΑ ΑΠΕΙΛΕΙ. Στην περίπτωση αυτή ΑΝ Η ΓΡΑΜΜΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά 
    	 * τότε παίζει σε ένα από τα 2 κενά (με 50%) ενώ ΑΝ Η ΣΤΗΛΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά 
    	 * τότε παίζει σε ένα από τα 2 κενά (με 50%).
    	 * 
    	 */
    		int randomCell=0;
    		int realCell=0;
    		
    		
    		
    		/* Αν ο υπολογιστής μπορεί να κάνει τρίλιζα, την κάνει. */
        	if(ticTacToeThreat(computerSymbol)!=-1){
        		cells[ticTacToeThreat(computerSymbol)].setText(computerSymbol);
        		return;
        	}
        	
        	/* Αν ο υπολογιστής απειλείται, αποτρέπει την τρίλιζα του παίχτη. */
        	if(ticTacToeThreat(playerSymbol)!=-1){
        		cells[ticTacToeThreat(playerSymbol)].setText(computerSymbol);
        		return;
        		
        	}
    		
    		if(counter==0){
    			randomCell = Math.abs(rn.nextInt())%4;
        		if (randomCell==0)
        			realCell=0;
        		if (randomCell==1)
        			realCell=2;
        		if (randomCell==2)
        			realCell=8;
        		if (randomCell==3)
        			realCell=6;     
        		cells[realCell].setText(computerSymbol);
        		return;
    		}
    		
    		if(counter==2){
    			int symmetricCorner=0;
    			boolean playerCornerPlay=false;
    			if(cells[0].getText().equals(computerSymbol))
    				symmetricCorner=8;
    			if(cells[2].getText().equals(computerSymbol))
    				symmetricCorner=6;
    			if(cells[6].getText().equals(computerSymbol))
    				symmetricCorner=2;
    			if(cells[8].getText().equals(computerSymbol))
    				symmetricCorner=0;
    			// Βλεπουμε αν ο παιχτης εχει παιξει γωνία
    			if(cells[0].getText().equals(playerSymbol))
    				playerCornerPlay=true;
    			else if(cells[2].getText().equals(playerSymbol))
    				playerCornerPlay=true;
    			else if(cells[6].getText().equals(playerSymbol))
    				playerCornerPlay=true;
    			else if(cells[8].getText().equals(playerSymbol))
    				playerCornerPlay=true;
    			    			
    			
    			if(cells[4].getText().equals(playerSymbol)){
    				cells[symmetricCorner].setText(computerSymbol);
    				return;
    			}
    			
    			/* Βρίσκουμε ποια γωνία έχει παίξει ο υπολογιστής ως πρώτη κίνηση */
    			
    			if(!cells[symmetricCorner].getText().equals(playerSymbol)&&playerCornerPlay==true ){
    				cells[symmetricCorner].setText(computerSymbol);
    				return;
    			}
    			
    			cells[4].setText(computerSymbol);
    			
    			return;
    			
    		}
    		
    		
    		/*Εδώ βρισκόμαστε στην περίπτωση που ο υπολογιστής έχει κλείσει δύο τετραγωνα κάποιας διαγωνίου
	    	 * ενώ ο παίκτης έχει "κόψει" την τρίλιζα της διαγωνίου και έχσει παίξει και σε κάποιο σταυρό
	    	 * ΧΩΡΙΣ ΝΑ ΑΠΕΙΛΕΙ. Στην περίπτωση αυτή ΑΝ Η ΓΡΑΜΜΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά 
	    	 * τότε παίζει σε ένα από τα 2 κενά (με 50%) ενώ ΑΝ Η ΣΤΗΛΗ ΤΗΣ ΠΑΙΓΜΕΝΗΣ ΓΩΝΙΑΣ του υπολογιστή έχει 2 κενά 
	    	 * τότε παίζει σε ένα από τα 2 κενά (με 50%).*/
    		if(counter==4){
    			
    			boolean row_1=true,row_2=true,row_3=true,row_4=true;
    			
    			if(cells[1].getText().equals(playerSymbol))
    				row_1=false;
    			if(cells[3].getText().equals(playerSymbol))
    				row_4=false;
    			if(cells[5].getText().equals(playerSymbol))
    				row_2=false;
    			if(cells[7].getText().equals(playerSymbol))
    				row_3=false;
    			if(cells[0].getText().equals(computerSymbol)){
    				randomCell = Math.abs(rn.nextInt())%2;
    				if(row_1==true){
    					if(randomCell==0)
    						cells[1].setText(computerSymbol);
    					else
    						cells[2].setText(computerSymbol);
    				}
    				if(row_4==true){
    					if(randomCell==0)
    						cells[3].setText(computerSymbol);
    					else
    						cells[6].setText(computerSymbol);
    				}
    			}else if(cells[2].getText().equals(computerSymbol)){
    				randomCell = Math.abs(rn.nextInt())%2;
    				if(row_1==true){
    					if(randomCell==0)
    						cells[0].setText(computerSymbol);
    					else
    						cells[1].setText(computerSymbol);
    				}
    				if(row_2==true){
    					if(randomCell==0)
    						cells[5].setText(computerSymbol);
    					else
    						cells[8].setText(computerSymbol);
    				}
    			}else if(cells[6].getText().equals(computerSymbol)){
    				
    				randomCell = Math.abs(rn.nextInt())%2;
    				if(row_4==true){
    					if(randomCell==0)
    						cells[0].setText(computerSymbol);
    					else
    						cells[3].setText(computerSymbol);
    				}
    				if(row_3==true){
    					if(randomCell==0)
    						cells[7].setText(computerSymbol);
    					else
    						cells[8].setText(computerSymbol);
    				}
    			}else if(cells[8].getText().equals(computerSymbol)){
    				randomCell = Math.abs(rn.nextInt())%2;
    				if(row_2==true){
    					if(randomCell==0)
    						cells[2].setText(computerSymbol);
    					else
    						cells[5].setText(computerSymbol);
    				}
    				if(row_3==true){
    					if(randomCell==0)
    						cells[6].setText(computerSymbol);
    					else
    						cells[7].setText(computerSymbol);
    				}
    			}
    			return;
    		}
    		/* Σε κάθε άλλη περίπτωση ο υπολογιστής επιλέγει τυχαίο τετράγωνο! */
    		
    		
    		randomCell = Math.abs(rn.nextInt())%9;

        	/* Οσο δεν βρίσκει "κενό" κουμπί, προχωρά σε νέα κλήρωση! */
        	while(!cells[randomCell].getText().equals(""))
        		randomCell = Math.abs(rn.nextInt())%9;
        	
        	cells[randomCell].setText(computerSymbol);
    		return;
    		
    		
    		
    		
    		
    		
    	}
    	
    	
    	private void expertLevelComputerAttackMove_3(){
    	/*	Παγίδα "του σταυρού"
		 * 
		 * Όταν η πρώτη κίνηση είναι σταυρός και ο παίχτης απαντήσει με γωνία που ΔΕΝ ΜΕ ΑΚΟΥΜΠΑ (ΔΕΝ ΕΙΝΑΙ ΓΕΙΤΟΝΙΚΗ
		 * ΜΟΥ) τότε ΠΡΕΠΕΙ ΝΑ ΠΑΙΞΩ ΕΤΣΙ ΩΣΤΕ ΝΑ Α Π Ε Ι Λ Η Σ Ω  την αντιδιαμετρική γωνία!!! Αν ο χρήστης κόψει
		 * την τρίλιζα, απαντάω με κέντροι και κέρδισα!
		 * 
		 * Όταν η πρώτη κίνηση είναι σταυρός και ο παίχτης απαντήσει με γωνία που ΜΕ ΑΚΟΥΜΠΑ (ΕΙΝΑΙ ΓΕΙΤΟΝΙΚΗ
		 * ΜΟΥ) τότε ΠΡΕΠΕΙ ΝΑ ΠΑΙΞΩ TΗ ΜΗ ΓΕΙΤΟΝΙΚΗ ΜΟΥ γωνία που ΒΡΙΣΚΕΤΑΙ ΣΤΗΝ ΙΔΙΑ ΓΡΑΜΜΗ ή ΣΤΗΝ ΙΔΙΑ
		 * ΣΤΗΛΗ με τη γειτονική γωνία που έπαιξε ο χρήστης!!! Αν μετά ο χρήστης ΔΕΝ παίξει κέντρο και ΔΕΝ απειλούμαι
		 * τότε παίζω στον αντιδιαμετρικό σταυρό (και κάνω διπλή).flag=2
		 * 
		 * Όταν η πρώτη κίνηση είναι σταυρός και ο παίχτης απαντήσει με αντιδιαμετρικό σταυρό, απαντάω με ΓΕΙΤΟΝΙΚΗ ΤΟΥ
		 * ΓΩΝΙΑ και μετά αν δεν απειλούμαι, με γειτονική γωνία και μη αντιδιαμετρικη. (Πιθανόν να κάτσει διπλή) flag=0
		 * 
		 * Όταν η πρώτη κίνηση είναι σταυρός και ο παίχτης απαντήσει με μη αντιδιαμετρικό σταυρό παίζω κέντρο 
		 * και αν ο παίχτης κλείσει την απειλούμενη τρίλιζα παίζω γειτονική γωνία (ως προς την πρώτη μου κίνηση!!). 
		 * flag==3
		 * Όταν η πρώτη κίνηση είναι σταυρός και ο παίχτης απαντήσει με κέντρο παίζω με μή γειτονική γωνία.
		 * Αν δεν απειλούμαι με τρίλιζα, τότε αν έχω παίξει σταυρό μεσαίας στήλης παίζω στην γειτονική και 
		 * μη αντιδιαμετρικη γωνία flag=0
    	 * 
    	 * 
    	 *  
    	 * */
    		
    		
    		int realCell; 
    		int randomCell=0;
    		/* Bοηθητικές μεταβλητές */
    		
    		/* Αν ο υπολογιστής μπορεί να κάνει τρίλιζα, την κάνει */
    	 	if(ticTacToeThreat(computerSymbol)!=-1){
        		cells[ticTacToeThreat(computerSymbol)].setText(computerSymbol);
        		return;
        	}
    	 	/* Αν ο παίχτης απηλεί να κάνει τρίλιζα, τον κόβει */
        	if(ticTacToeThreat(playerSymbol)!=-1){
        		cells[ticTacToeThreat(playerSymbol)].setText(computerSymbol);
        		return;
        		
        	}
    		
    		if(counter==0){
    			randomCell= 2*(Math.abs(rn.nextInt())%4)+1; 
		    	
				cells[randomCell].setText(computerSymbol);
				return;
    		}
    		
    		if(counter==2){
    			boolean flag1=false;
    			if(cells[1].getText().equals(computerSymbol)){
    				if(cells[6].getText().equals(playerSymbol)){
    					cells[0].setText(computerSymbol);
    					return;
    				}
    				if(cells[8].getText().equals(playerSymbol)){
    					cells[2].setText(computerSymbol);
    					return;
    				}
    				if(cells[0].getText().equals(playerSymbol)){
    					cells[6].setText(computerSymbol);
    					cross_trap_flag=2;
    					return;
    				}
    				if(cells[2].getText().equals(playerSymbol)){
    					cells[8].setText(computerSymbol);
    					cross_trap_flag=2;
    					return;
    				}
    				if(cells[7].getText().equals(playerSymbol)||cells[4].getText().equals(playerSymbol)){
    					cross_trap_flag=0;
    					randomCell= (Math.abs(rn.nextInt())%2);
    					if(randomCell==0){
    						cells[6].setText(computerSymbol);
    						return;
    					}
    					if(randomCell==1){
    						cells[8].setText(computerSymbol);
    						return;
    					}
    				}
    				cells[4].setText(computerSymbol);
    				cross_trap_flag=3;
    				return;
    			}
    			if(cells[3].getText().equals(computerSymbol)){
    				if(cells[2].getText().equals(playerSymbol)){
    					cells[0].setText(computerSymbol);
    					return;
    				}
    				if(cells[8].getText().equals(playerSymbol)){
    					cells[6].setText(computerSymbol);
    					return;
    				}
    				if(cells[0].getText().equals(playerSymbol)){
    					cells[2].setText(computerSymbol);
    					cross_trap_flag=2;
    					return;
    				}
    				if(cells[6].getText().equals(playerSymbol)){
    					cells[8].setText(computerSymbol);
    					cross_trap_flag=2;
    					return;
    				}
    				if(cells[5].getText().equals(playerSymbol)||cells[4].getText().equals(playerSymbol)){
    					cross_trap_flag=0;
    					randomCell= (Math.abs(rn.nextInt())%2);
    					if(randomCell==0){
    						cells[2].setText(computerSymbol);
    						return;
    					}
    					if(randomCell==1){
    						cells[8].setText(computerSymbol);
    						return;
    					}
    				}
    				cells[4].setText(computerSymbol);
    				cross_trap_flag=3;
    				return;
    			}
    			if(cells[5].getText().equals(computerSymbol)){
    				if(cells[0].getText().equals(playerSymbol)){
    					cells[2].setText(computerSymbol);
    					return;
    				}
    				if(cells[6].getText().equals(playerSymbol)){
    					cells[8].setText(computerSymbol);
    					return;
    				}
    				if(cells[2].getText().equals(playerSymbol)){
    					cells[0].setText(computerSymbol);
    					cross_trap_flag=2;
    					return;
    				}
    				if(cells[8].getText().equals(playerSymbol)){
    					cells[6].setText(computerSymbol);
    					cross_trap_flag=2;
    					return;
    				}
    				if(cells[3].getText().equals(playerSymbol)||cells[4].getText().equals(playerSymbol)){
    					cross_trap_flag=0;
    					randomCell= (Math.abs(rn.nextInt())%2);
    					if(randomCell==0){
    						cells[0].setText(computerSymbol);
    						return;
    					}
    					if(randomCell==1){
    						cells[6].setText(computerSymbol);
    						return;
    					}
    				}
    				cells[4].setText(computerSymbol);
    				cross_trap_flag=3;
    				return;
    			}
    			if(cells[7].getText().equals(computerSymbol)){
    				if(cells[0].getText().equals(playerSymbol)){
    					cells[6].setText(computerSymbol);
    					return;
    				}
    				if(cells[2].getText().equals(playerSymbol)){
    					cells[8].setText(computerSymbol);
    					return;
    				}
    				if(cells[6].getText().equals(playerSymbol)){
    					cells[0].setText(computerSymbol);
    					cross_trap_flag=2;
    					return;
    				}
    				if(cells[8].getText().equals(playerSymbol)){
    					cells[2].setText(computerSymbol);
    					cross_trap_flag=2;
    					return;
    				}
    				if(cells[1].getText().equals(playerSymbol)||cells[4].getText().equals(playerSymbol)){
    					cross_trap_flag=0;
    					randomCell= (Math.abs(rn.nextInt())%2);
    					if(randomCell==0){
    						cells[0].setText(computerSymbol);
    						return;
    					}
    					if(randomCell==1){
    						cells[2].setText(computerSymbol);
    						return;
    					}
    				}
    				cells[4].setText(computerSymbol);
    				cross_trap_flag=3;
    				return;
    			}
    			
    				
    				
    				
    				
    				
    				
    				
    				
    				
    				
    			}
    		
    		
    		
    			if(counter==4){
    				 if(cross_trap_flag==0) {
    					 if(cells[1].getText().equals(computerSymbol)) {
    						
    	    					if(cells[6].getText().equals(computerSymbol)){
    	    						if(cells[0].getText().equals("")) {
    	    							cells[0].setText(computerSymbol);
    	    							return;
    	    						}	
    	    					}
    	    					if(cells[8].getText().equals(computerSymbol)){
    	    						if(cells[2].getText().equals("")) {
    	    							cells[2].setText(computerSymbol);
    	    							return;
    	    						}	
    	    					}
    	    					
    					 }
    					 if(cells[3].getText().equals(computerSymbol)) {
    						 if(cells[2].getText().equals(computerSymbol)){
 	    						if(cells[0].getText().equals("")) {
 	    							cells[0].setText(computerSymbol);
 	    							return;
 	    						}	
 	    					}
 	    					if(cells[8].getText().equals(computerSymbol)){
 	    						if(cells[6].getText().equals("")) {
 	    							cells[6].setText(computerSymbol);
 	    							return;
 	    						}	
 	    					}
    					 }
    					 
    					 if(cells[5].getText().equals(computerSymbol)) {
    						 if(cells[0].getText().equals(computerSymbol)){
 	    						if(cells[2].getText().equals("")) {
 	    							cells[2].setText(computerSymbol);
 	    							return;
 	    						}	
 	    					}
 	    					if(cells[6].getText().equals(computerSymbol)){
 	    						if(cells[8].getText().equals("")) {
 	    							cells[8].setText(computerSymbol);
 	    							return;
 	    						}	
 	    					}
    					 }
    					 
    					 if(cells[7].getText().equals(computerSymbol)) {
    						 if(cells[0].getText().equals(computerSymbol)){
 	    						if(cells[6].getText().equals("")) {
 	    							cells[6].setText(computerSymbol);
 	    							return;
 	    						}	
 	    					}
 	    					if(cells[2].getText().equals(computerSymbol)){
 	    						if(cells[8].getText().equals("")) {
 	    							cells[8].setText(computerSymbol);
 	    							return;
 	    						}	
 	    					}
    					 }
    					 
    					 
    					 
    				 }
    				 if(cross_trap_flag==1) {
    					 if(cells[1].getText().equals(computerSymbol)) {
    						 if(cells[6].getText().equals(computerSymbol)) {
    							 if(cells[0].getText().equals("")) {
    								 cells[0].setText(computerSymbol);
    								 return;
    							 }
    							 
    						 }
    						 if(cells[8].getText().equals(computerSymbol)) {
    							 if(cells[2].getText().equals("")) {
    								 cells[2].setText(computerSymbol);
    								 return;
    							 }
    						 }
    					 }
    					 
    					 
    					 if(cells[3].getText().equals(computerSymbol)) {
    						 if(cells[2].getText().equals(computerSymbol)) {
    							 if(cells[0].getText().equals("")) {
    								 cells[0].setText(computerSymbol);
    								 return;
    							 }
    							 
    						 }
    						 if(cells[8].getText().equals(computerSymbol)) {
    							 if(cells[6].getText().equals("")) {
    								 cells[6].setText(computerSymbol);
    								 return;
    							 }
    						 }
    					 }
    					 
    					 
    					 if(cells[5].getText().equals(computerSymbol)) {
    						 if(cells[0].getText().equals(computerSymbol)) {
    							 if(cells[2].getText().equals("")) {
    								 cells[2].setText(computerSymbol);
    								 return;
    							 }
    							 
    						 }
    						 if(cells[6].getText().equals(computerSymbol)) {
    							 if(cells[8].getText().equals("")) {
    								 cells[8].setText(computerSymbol);
    								 return;
    							 }
    						 }
    					 }
    					 
    					 
    					 
    					 
    					 if(cells[7].getText().equals(computerSymbol)) {
    						 if(cells[0].getText().equals(computerSymbol)) {
    							 if(cells[6].getText().equals("")) {
    								 cells[6].setText(computerSymbol);
    								 return;
    							 }
    							 
    						 }
    						 if(cells[2].getText().equals(computerSymbol)) {
    							 if(cells[8].getText().equals("")) {
    								 cells[8].setText(computerSymbol);
    								 return;
    							 }
    						 }
    					 } 
    				 }
    				 if(cross_trap_flag==2) {
    					 if(cells[1].getText().equals(computerSymbol)) {
    						 if(cells[7].getText().equals("")) {
    							 cells[7].setText(computerSymbol);
    							 return;
    						 }
    					 } 
    					 
    					 if(cells[3].getText().equals(computerSymbol)) {
    						 if(cells[5].getText().equals("")) {
    							 cells[5].setText(computerSymbol);
    							 return;
    						 }
    					 }
    					 
    					 if(cells[5].getText().equals(computerSymbol)) {
    						 if(cells[3].getText().equals("")) {
    							 cells[3].setText(computerSymbol);
    							 return;
    						 }
    					 }
    					 
    					 
    					 
    					 
    					 
    					 if(cells[7].getText().equals(computerSymbol)) {
    						 if(cells[1].getText().equals("")) {
    							 cells[1].setText(computerSymbol);
    							 return;
    						 }
    					 }
    					 
    					 
    					 
    					 
    					 
    					 
    				 }
    				 if(cross_trap_flag==3) {
    					 if(cells[1].getText().equals(computerSymbol)) {
    						 randomCell= (Math.abs(rn.nextInt())%2);
    	    					if(randomCell==0){
    	    						cells[0].setText(computerSymbol);
    	    						return;
    	    					}
    	    					if(randomCell==1){
    	    						cells[2].setText(computerSymbol);
    	    						return;
    	    					}
    					 }
    					 if(cells[3].getText().equals(computerSymbol)) {
    						 randomCell= (Math.abs(rn.nextInt())%2);
    	    					if(randomCell==0){
    	    						cells[0].setText(computerSymbol);
    	    						return;
    	    					}
    	    					if(randomCell==1){
    	    						cells[6].setText(computerSymbol);
    	    						return;
    	    					}
    					 }
    					 if(cells[5].getText().equals(computerSymbol)) {
    						 randomCell= (Math.abs(rn.nextInt())%2);
    	    					if(randomCell==0){
    	    						cells[2].setText(computerSymbol);
    	    						return;
    	    					}
    	    					if(randomCell==1){
    	    						cells[8].setText(computerSymbol);
    	    						return;
    	    					}
    					 }
    					 if(cells[7].getText().equals(computerSymbol)) {
    						 randomCell= (Math.abs(rn.nextInt())%2);
    	    					if(randomCell==0){
    	    						cells[6].setText(computerSymbol);
    	    						return;
    	    					}
    	    					if(randomCell==1){
    	    						cells[8].setText(computerSymbol);
    	    						return;
    	    					}
    					 }
    					 
    					 
    					 
    					 
    				 }
    				 
    				 
    				 
    				 
    					
    					
    			}
    				
    			
    			
    			
    			
    			
    			
    			
    		
    		
    		
    		/* Οσο δεν βρίσκει "κενό" κουμπί, προχωρά σε νέα κλήρωση! */
        	while(!cells[randomCell].getText().equals(""))
        		randomCell = Math.abs(rn.nextInt())%9;
        	
        	cells[randomCell].setText(computerSymbol);
    		
    		
    		
    		
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    
    
    
    private void expertLevelComputerDefenceMove(){
    	 
		int realCell; 
		int randomCell;
		/* Bοηθητικές μεταβλητές */
		
		/* Αν ο υπολογιστής μπορεί να κάνει τρίλιζα, την κάνει */
	 	if(ticTacToeThreat(computerSymbol)!=-1){
    		cells[ticTacToeThreat(computerSymbol)].setText(computerSymbol);
    		return;
    	}
	 	/* Αν ο παίχτης απηλεί να κάνει τρίλιζα, τον κόβει */
    	if(ticTacToeThreat(playerSymbol)!=-1){
    		cells[ticTacToeThreat(playerSymbol)].setText(computerSymbol);
    		return;
    		
    	}
    	
    	/* Αν η πρώτη κίνηση του παίχτη δεν είναι στο κέντρο τότε απαντά με κέντρο διαφορετικά 
		απαντα με γωνία */
    	if(counter==1){
    		
    	
        	if(cells[4].getText()==""){
        		
        		cells[4].setText(computerSymbol);
        		return;
        		
        	}else{
        		
        		randomCell = rn.nextInt();
        		System.out.println("randomCell "+randomCell);

        		randomCell = Math.abs(randomCell)%4;
        		
        		System.out.println("randomCell "+randomCell);
        		if (randomCell==0)
        			realCell=0;
        		else if (randomCell==1)
        			realCell=2;
        		else if (randomCell==2)
        			realCell=6;
        		else
        			realCell=8;

            	/* Οσο δεν βρίσκει "κενό" κουμπί, προχωρά σε νέα κλήρωση! */
        		while(!cells[realCell].getText().equals("")){
        			
            		randomCell = Math.abs(rn.nextInt())%4;
            		
            		if (randomCell==0)
            			realCell=0;
            		if (randomCell==1)
            			realCell=2;
            		if (randomCell==2)
            			realCell=6;
            		if (randomCell==3)
            			realCell=8;        	
            	}
            	
            	cells[realCell].setText(computerSymbol);
            	return;
        		
        	}
    	
    	}
    	
    	/* Αν ο παίχτης έχει παίξει δύο γωνίες τότε πρέπει να παίξει σταυρό (μιλάμε μόνο 
    	 * για την ΤΕΤΑΡΤΗ ΚΙΝΗΣΗ) */
    	
    	/* Συνεπώς ο,τι γίνει θα γίνει αν counter == 3 και ο παιχτης δεν έχει παίξει κέντρο */
    	
    	if(!cells[4].getText().equals(playerSymbol) && counter==3){
        	
    		/* Oπότε αρχικά μετράμε τις γωνίες και πόσους σταυρούς που έχει πιάσει ο παίχτης */
        	int playerCornerMoves=0;
        	int playerCrossMoves=0;
        	
        	/* Μετράμε τις γωνίες */
        	if(cells[0].getText().equals(playerSymbol))
        		playerCornerMoves++;
        	if(cells[2].getText().equals(playerSymbol))
        		playerCornerMoves++;
        	if(cells[6].getText().equals(playerSymbol))
        		playerCornerMoves++;
        	if(cells[8].getText().equals(playerSymbol))
        		playerCornerMoves++;
        	
        	/* Μετράμε τους σταυρούς */
        	if(cells[1].getText()==playerSymbol)
        		playerCrossMoves++;
        	if(cells[3].getText()==playerSymbol)
        		playerCrossMoves++;
        	if(cells[5].getText()==playerSymbol)
        		playerCrossMoves++;
        	if(cells[7].getText()==playerSymbol)
        		playerCrossMoves++;
        	
        	
        	/* Aν ο παίχτης έχει παίξει δύο γωνίες  τότε ο υπολογιστής
        	 * παίζει σταυρό. */
        	if(playerCornerMoves==2){
        		
        		
        		
        		realCell=0;
        		randomCell = Math.abs(rn.nextInt())%4;
        		if (randomCell==0)
        			realCell=1;
        		if (randomCell==1)
        			realCell=3;
        		if (randomCell==2)
        			realCell=5;
        		if (randomCell==3)
        			realCell=7;     
        		
        		
        		while(!cells[realCell].getText().equals("")){
            		randomCell = Math.abs(rn.nextInt())%4;
            		if (randomCell==0)
            			realCell=1;
            		if (randomCell==1)
            			realCell=3;
            		if (randomCell==2)
            			realCell=5;
            		if (randomCell==3)
            			realCell=9;        	
            		}
            	
            	cells[realCell].setText(computerSymbol);
            	return;
        		
        	}
        	/* Αν οι δύο πρώτες κινήσεις του παίχτη είναι γωνια-σταυρός ή σταυρός-γωνία πρέπει να παίξει 
        	 * στο σταυρό διαλέγοντας τη στήλη του σταυρού αν είναι πιο άδεια από τη γραμμή ή τη γραμμή 
        	 * του σταυρού αν είναι πιο άδεια από τη στήλη. */
        	if(playerCornerMoves==1 && playerCrossMoves==1){
        		
        		/* Πρακτικά: Αν είναι κατηλλειμένo ένα από τα cell[3] και cell[5] πρέπει
        		 * να κληρωθεί ένα από τα cell[1] ή cell[7]. Αν είναι κατηλλειμένo ένα από 
        		 * τα cell[1] και cell[7] πρέπει να κληρωθεί ένα από τα cell[3] ή cell[5].*/
        		randomCell = Math.abs(rn.nextInt())%2;
        		
        		if(cells[3].getText().equals(playerSymbol) || cells[5].getText().equals(playerSymbol)){
	        		if(randomCell==0)
	        			cells[1].setText(computerSymbol);
	        		else
	        			cells[7].setText(computerSymbol);
        		}
        		else{
        			
        			if(randomCell==0)
            			cells[5].setText(computerSymbol);
            		else
            			cells[3].setText(computerSymbol);        			
        		}
        		return;
        	}
        	
        	
        	if(playerCrossMoves==2){
        		int linePlayerMoves = 0,columnPlayerMoves = 0;
        		if(cells[1].getText().equals(playerSymbol))
        			columnPlayerMoves++;
        		if(cells[7].getText().equals(playerSymbol))
        			columnPlayerMoves++;
        		if(cells[3].getText().equals(playerSymbol))
        			linePlayerMoves++;
        		if(cells[5].getText().equals(playerSymbol))
        			linePlayerMoves++;
        		if(linePlayerMoves==1 && columnPlayerMoves==1){
        			if(cells[1].getText().equals(playerSymbol) && cells[3].getText().equals(playerSymbol))
        				cells[0].setText(computerSymbol);
        			if(cells[1].getText().equals(playerSymbol) && cells[5].getText().equals(playerSymbol))
        				cells[2].setText(computerSymbol);
        			if(cells[3].getText().equals(playerSymbol) && cells[7].getText().equals(playerSymbol))
        				cells[6].setText(computerSymbol);
        			if(cells[5].getText().equals(playerSymbol) && cells[7].getText().equals(playerSymbol))
        				cells[8].setText(computerSymbol);
        			return;
        		}
        	}


        	
        	
    	}
    	if(counter==3){
    		int PlayerCornerMoves=0;
    		if(cells[0].getText().equals(playerSymbol))
        		PlayerCornerMoves++;
        	if(cells[2].getText().equals(playerSymbol))
        		PlayerCornerMoves++;
        	if(cells[6].getText().equals(playerSymbol))
        		PlayerCornerMoves++;
        	if(cells[8].getText().equals(playerSymbol))
        		PlayerCornerMoves++;
    		if(PlayerCornerMoves==1){
    		
    		
        	System.out.println("85677856");
        	realCell=0;
    		randomCell = Math.abs(rn.nextInt())%4;
    		if (randomCell==0)
    			realCell=0;
    		if (randomCell==1)
    			realCell=2;
    		if (randomCell==2)
    			realCell=6;
    		if (randomCell==3)
    			realCell=8;     
    		
    		
    		while(!cells[realCell].getText().equals("")){
        		randomCell = Math.abs(rn.nextInt())%4;
        		if (randomCell==0)
        			realCell=0;
        		if (randomCell==1)
        			realCell=2;
        		if (randomCell==2)
        			realCell=6;
        		if (randomCell==3)
        			realCell=8;        	
        		}
        	
        	cells[realCell].setText(computerSymbol);
        	return;
    		
    		}
    		
    	}
    	
    	randomCell = Math.abs(rn.nextInt())%9;

    	/* Οσο δεν βρίσκει "κενό" κουμπί, προχωρά σε νέα κλήρωση! */
    	while(!cells[randomCell].getText().equals(""))
    		randomCell = Math.abs(rn.nextInt())%9;
    	
    	cells[randomCell].setText(computerSymbol);
    	
    }
    	 
    	 
    	 
    	 
    	 
    	 
    	 
     
    private void playerMove(int cell){
    	
    	/* Για αρχή θα απενεργοποιήσουμε την αλλαγή επιπέδου του χρήστη
    	 * εν μέσω παρτίδας. */
    	if(winFlag==false && counter<9){
    	
    	intermediateRadioSelection.setEnabled(false);
		beginnerRadioSelection.setEnabled(false);
		expertRadioSelection.setEnabled(false);
		playerSymbolSelectionO.setEnabled(false);
		playerSymbolSelectionX.setEnabled(false);
    	}
    	System.out.println("Player move counter = "+counter);
    	System.out.println("Player tries to play in cell "+cell);
    	/* Aν υπάρχει νικητής ο παίκτης δεν μπορεί να παίξει. */
    	if(winFlag)
    		return;
    	
    	/* Aν το κελί είναι διαθέσιμο... */
    	if (cells[cell].getText().equals("")){
    		System.out.println("PlayING in cell "+cell);
    		/* ...ο παίκτης συμπληρώνει το συμβολό του στο κελί...*/
			cells[cell].setText(playerSymbol);
			/* ...αυξάνεται ο μετρητής...*/
			counter++;
			System.out.println("Set text OK!");
			/* ...και ελέγχεται αν ο παίκτης κέρδισε. (Θα παίξουμε με if)*/
			if(checkWin())
	    		winFlag=true;
			System.out.println("checkWin OK!");
			/* Aν υπάρχει νικητής το computer δεν μπορεί να παίξει. */
			if(winFlag)
	    		return;
			
			System.out.println("winFlag OK!");
			//try {
				//Thread.sleep(2000);
			//} catch (InterruptedException e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
			/* Εάν ο παίκτης δεν κέρδισε θα παίζει ο υπολογιστής */
			computerMove();
			
		}
    	
    }
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Triliza window = new Triliza();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Triliza() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		
		cells = new JButton[9];
		/* Δημιουργούμε πίνακα κουμπιών για ευκολότερη διαχείρισή τους */
		
		rn = new Random();
		/* Φτιάχνουμε την "κληρωτίδα" μας */
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/** 
		 *  Η γενική δομή του κώδικα που θα ακολουθήσει έχει ως εξής:
		 *  
		 *  Αρχικά έχουμε τον κώδικα που κατασκευάζει τα κελιά.
		 *  Αμέσως μετά έχουμε τον κώδικα που κατασκευάζει το μενού.
		 *  Αμέσως μετά έχουμε τον κώδικα που κατασκευάζει τα υπόλοιπα αντικείμενα της παλέτας.
		 *  
		 *  Ακολουθούν οι action listeners ως εξής:
		 *  Πρώτα έχουμε τους action listeners των κελιών.
		 *  Ακολουθούν οι action listeners του μενού.
		 *  Τελευταίους έχουμε τους action listeners των υπόλοιπων αντικειμένων της παλέτας.
		 *  
		 *  Ακολουθεί κώδικας ο οποίος φτιάχνει τα κελια! 
		 * 
		 * **/
		
		/* Κατασκευή πρώτου κελιού */
		JButton btnNewButton = new JButton("");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 35));
		cells[0] = btnNewButton;
		btnNewButton.setBounds(75, 67, 72, 72);
		frame.getContentPane().add(btnNewButton);
		
		/* Κατασκευή δεύτερου κελιού */
		JButton button = new JButton("");
		button.setFont(new Font("Tahoma", Font.BOLD, 35));
		button.setBounds(159, 67, 72, 72);
		frame.getContentPane().add(button);
		cells[1] = button;
		
		/* Κατασκευή τρίτου κελιού */
		JButton button_1 = new JButton("");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 35));
		button_1.setBounds(243, 67, 72, 72);
		frame.getContentPane().add(button_1);
		cells[2] = button_1;
		
		/* Κατασκευή τεταρτου κελιού */
		JButton button_2 = new JButton("");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 35));
		button_2.setBounds(75, 152, 72, 72);
		frame.getContentPane().add(button_2);
		cells[3] = button_2;
		
		/* Κατασκευή πέμπτου κελιού */
		JButton button_3 = new JButton("");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 35));
		button_3.setBounds(159, 152, 72, 72);
		frame.getContentPane().add(button_3);
		cells[4] = button_3;
		
		/* Κατασκευή εκτου κελιού */
		JButton button_4 = new JButton("");
		button_4.setFont(new Font("Tahoma", Font.BOLD, 35));
		button_4.setBounds(243, 152, 72, 72);
		frame.getContentPane().add(button_4);
		cells[5] = button_4;		
		
		/* Κατασκευή εβδόμου κελιού */
		JButton button_5 = new JButton("");
		button_5.setFont(new Font("Tahoma", Font.BOLD, 35));
		button_5.setBounds(75, 238, 72, 72);
		frame.getContentPane().add(button_5);
		cells[6] = button_5;
		
		/* Κατασκευή ογδοου κελιού */
		JButton button_6 = new JButton("");
		button_6.setFont(new Font("Tahoma", Font.BOLD, 35));
		button_6.setBounds(159, 237, 72, 72);
		frame.getContentPane().add(button_6);
		cells[7] = button_6;
		
		/* Κατασκευή ενάτου κελιού */
		JButton button_7 = new JButton("");
		button_7.setFont(new Font("Tahoma", Font.BOLD, 35));
		button_7.setBounds(243, 237, 72, 72);
		frame.getContentPane().add(button_7);
		cells[8] = button_7;
		
		/** 
		 * 
		 *  Τέλος κώδικα ο οποίος φτιάχνει τα κελια! 
		 *  Ακολουθεί κώδικας ο οποίος φτιάχνει το μενού! 
		 * 
		 * **/
		setDefaultColor();
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 737, 26);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNew = new JMenu("Game");
		menuBar.add(mnNew);
		
		JMenuItem menuItemNewGame = new JMenuItem("New game");
		mnNew.add(menuItemNewGame);
		
		JMenu mnLevel = new JMenu("Level");
		mnNew.add(mnLevel);
		
		ButtonGroup group = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();
		ButtonGroup group3 = new ButtonGroup();
		
		beginnerRadioSelection = new JRadioButtonMenuItem("beginner");
		mnLevel.add(beginnerRadioSelection);
		
		intermediateRadioSelection = new JRadioButtonMenuItem("intermediate");
		mnLevel.add(intermediateRadioSelection);
		
		expertRadioSelection = new JRadioButtonMenuItem("expert");
		mnLevel.add(expertRadioSelection);
		
		group.add(beginnerRadioSelection);
		group.add(intermediateRadioSelection);
		group.add(expertRadioSelection);
		intermediateRadioSelection.setSelected(true);
		
		
		
		JMenu mnPlayerSymbol = new JMenu("Player symbol");
		mnNew.add(mnPlayerSymbol);
		
		playerSymbolSelectionO = new JRadioButtonMenuItem("O");
		mnPlayerSymbol.add(playerSymbolSelectionO);
		
		playerSymbolSelectionX = new JRadioButtonMenuItem("X");
		mnPlayerSymbol.add(playerSymbolSelectionX);
		group2.add(playerSymbolSelectionX);
		group2.add(playerSymbolSelectionO);
		playerSymbolSelectionO.setSelected(true);
		
		JMenu mnMode = new JMenu("Mode");
		mnNew.add(mnMode);
		
		JRadioButtonMenuItem rdbtnmntmPlayerFirst = new JRadioButtonMenuItem("Player first");
		mnMode.add(rdbtnmntmPlayerFirst);
		
		JRadioButtonMenuItem rdbtnmntmComputerFirst = new JRadioButtonMenuItem("Computer first");
		mnMode.add(rdbtnmntmComputerFirst);
		group3.add(rdbtnmntmPlayerFirst);
		group3.add(rdbtnmntmComputerFirst);
		rdbtnmntmPlayerFirst.setSelected(true);

		/* Αction listener της πρώτης επιλογής */
		beginnerRadioSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){

					level="beginner";
					System.out.println("beginner selected!");
				
			}
		});
		
		
		rdbtnmntmPlayerFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){

					computerAttackMode=false;
					
				
			}
		});
		rdbtnmntmComputerFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){

					computerAttackMode=true;
					
				
			}
		});
		
		
		
		
		
		/* Αction listener της δεύτερης επιλογής */
		intermediateRadioSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){

					level="intermediate";
					System.out.println("intermediate selected!");
				
			}
		});
		
		
		/* Αction listener της τρίτης επιλογής */
		expertRadioSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){

					level="expert";
					System.out.println("expert selected!");	
				
			}
		});
		
		playerSymbolSelectionO.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){

					playerSymbol="O";
					computerSymbol="X";
				
			}
		});
		playerSymbolSelectionX.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){

					playerSymbol="X";
					computerSymbol="O";
				
			}
		});
		
		
		/** 
		 * 
		 *  Τέλος κώδικα ο οποίος φτιάχνει συμπληρωματικά αντικείμενα στην παλέτα! 
		 *  Ακολουθεί κώδικας ο οποίος προσθέτει τους action listeners των κελιών! 
		 * 
		 * **/
		
		/* Αction listener του πρώτου κελιού */
		cells[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(!(computerAttackMode==true && counter==0))
					playerMove(0);
			}
		});
		
		/* Αction listener του δευτερου κελιού */
		cells[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				if(!(computerAttackMode==true && counter==0))
					playerMove(1);
			}
		});
		
		/* Αction listener του τριτου κελιού */
		cells[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(computerAttackMode==true && counter==0))
					playerMove(2);
			}
		});
		
		/* Αction listener του τεταρτου κελιού */
		cells[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(computerAttackMode==true && counter==0))
					playerMove(3);
			}
		});
		
		/* Αction listener του πεμπτου κελιού */
		cells[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(computerAttackMode==true && counter==0))
					playerMove(4);
			}
		});
		
		/* Αction listener του εκτου κελιού */
		cells[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(computerAttackMode==true && counter==0))
					playerMove(5);
			}
		});
		
		/* Αction listener του εβδομου κελιού */
		cells[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(computerAttackMode==true && counter==0))
					playerMove(6);
			}
		});
		
		/* Αction listener του ογδοου κελιού */
		cells[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(computerAttackMode==true && counter==0))
					playerMove(7);
			}
		});
		
		/* Αction listener του ενάτου κελιού */
		cells[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(computerAttackMode==true && counter==0))
					playerMove(8);
			}
		});
		
		/* Aκολουθεί ο action listener  */
		menuItemNewGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setDefaultColor();
				counter=0;
				winFlag=false;
				intermediateRadioSelection.setEnabled(true);
				beginnerRadioSelection.setEnabled(true);
				expertRadioSelection.setEnabled(true);
				playerSymbolSelectionO.setEnabled(true);
				playerSymbolSelectionX.setEnabled(true);
				for(int i=0;i<9;i++)
					cells[i].setText("");
				
				if(computerAttackMode==true) {
					trap_category = Math.abs(rn.nextInt())%3;
					computerMove();
				}
			}
		});
		
		//if(computerAttackMode==true){
			
		//	System.out.println("Ο υπολογιστής παίζειο πρώτος!!!\n");
		//	computerMove();
		//}
						
	}
}
