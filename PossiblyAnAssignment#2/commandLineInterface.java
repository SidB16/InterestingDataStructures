package A3;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */

/**
 * This is the Front-End of part2 i.e. the Command Line.
 * Uses File reader and Scanner to read and scan input commands from text file.
 * NOTE: As it is, this class does not support integration with Backend i.e. the AdjacenyListGraph.
 * However, the Backend is fully defined and implemented!
 *   
 * @author sid16
 *
 */
public class commandLineInterface {
	/**
	 * This interface will read input from a text file called commands.txt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
								//for windows use format: "C:\\Users\\pankaj\\Desktop\\test.txt"
			FileReader reader = new FileReader("/home/user/commands.txt"); //encapsulation here since we have to create a reader object that implements the Readable interface so that we can use utilizie a scanner for it!
			Scanner input = new Scanner(reader);
			String decision = " ";
			List<Vertex<String>> airports = new ArrayList<Vertex<String>>();
	
			
			while(input.hasNext() && !(decision.equals("QUIT")) ) {
				decision = input.nextLine();
				if(!(decision.equals("QUIT")) && !(decision.substring(0, 1).equals("+")) && !(decision.substring(0, 1).equals("-")) && !(decision.substring(0, 1).equals("?"))){
					System.out.println("Error invalid command entered");
				}//end of first control flow ==> invlaid comamnd
				if(!(decision.equals("QUIT")) && (decision.substring(0, 1).equals("+"))) {
					System.out.println("performing addition function");
					commandLineInterface.linePrinter(decision);
				}//end of second control flow ==> addition function 
				if(!(decision.equals("QUIT")) && (decision.substring(0, 1).equals("-"))){
					System.out.println("performing subtraction function");
				}//end of third control flow ==> subtraction function
				if(!(decision.equals("QUIT")) && (decision.substring(0, 1).equals("?"))){
					System.out.println("performing query function");
				}//end of fourth control flow ==> query function
			}
			System.out.println("End of execution......closing inputstream. Good Bye!");
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("commands.txt file not found!");
		}
		
	}
	//Private utilities
	private static void linePrinter(String decision) { // this method currently only works for addition function. Otherwise since  - and ? have different string lenghts, this function will throw a index out of bounds exception!
		System.out.println("String is: " + decision);
		System.out.println("Lenght of String is: " + decision.length());
		System.out.println("First command is: " + decision.substring(0, 1));
		System.out.println("Second command is: " + decision.substring(2, 5));
		System.out.println("Third command is: " + decision.substring(6, 9));
		System.out.println("Fourth command is: " + decision.substring(10, 13));
		System.out.println("Fifth command is: " + decision.substring(14, 19));
		//System.out.println(decision.substring(beginIndex, endIndex));
	}

}
