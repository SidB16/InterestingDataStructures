import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author sid16
 *
 */
public class commandLineInterface {
	/**
	 * This interface will read input from a text file called commands.txt.
	 * 
	 * ****Notes****
	 * public Scanner(Readable source) ==>  constructs a new scanner that produces values scanned from specified source.
	 * source is a CHARACTER source implementing the Readable interface.
	 * 
	 * Classes implementing the readable interface:
	 * 1)Buffered Reader ==> Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines. 
	 * 2)**FileReader ==> Reads text from character files using a default buffer size. Decoding from bytes to characters uses either a specified charset or the platform's default charset. 
	 * 3)InputStreamReader==> is super class of file reader
	 * 4)Reader ==> Abstract class for reading character streams. The only methods that a subclass must implement are read(char[], int, int) and close().
	 * @param args
	 */
	public static void main(String[] args) {
		try {
								//for windows use format: "C:\\Users\\pankaj\\Desktop\\test.txt"
			FileReader reader = new FileReader("/home/user/commands.txt"); //encapsulation here since we have to create a reader object that implements the Readable interface so that we can use utilizie a scanner for it!
			Scanner input = new Scanner(reader);
			String decision = " ";
			ArrayList<String> line;
			/**
			 * Plan:
			 * 1) Figure out how to handle +, - and ? conditions ==> check 
			 * 2) How to handle the LINE string like the length, and diffrent substrings. Does space count as a part of the string? 
			 * 
			 */
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
