
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * This class is responsible for extracting the event -b code from the text file
 * At the moment I have separated the variables and events, now I need to further
 * refine it to get each event.  
 * This class decides how the text file is split up, eg, it knows where the guards are (After "WHERE"
 * and before "END") 
 * @author Joshi
 */
public class EventB {
	private BufferedReader br = null;
	private StringTokenizer st;
	private ArrayList<Variable> varArr;
	public EventB(){
		
	}
	/**
	 * ReadInfo should read the Event-B File and tokenize the values.
	 * the approach here is to just read the events section.
	 * Create a Hashmap that maps a String (variable) to the Value (Integer)
	 * Then I will track the events, create a Data Structure to handle events, which basically
	 * uses the Hashmap to change the variables around based on the event.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	 * @throws FileNotFoundException
	 */
	public void readInfo() throws FileNotFoundException{
		StringBuilder fileText = new StringBuilder();
		String currLine;
		String vars;
		String events;
		int firsteventIndex = 0;
		int firstvarIndex = 0;
		int firstInitIndex = 0;
		int initIndex = 0;
		int varIndex = 0;
		int eventIndex;
		int valIndex = 0;
		String varName = "";
		String varValue = "";
		String[] varList = null;
		try {
			//put the event-b txt into source project
			//Use the stringbuilder to remove the unneccesary strings and keep the
			//salient ones, then assign to variables,guards etc
			br = new BufferedReader(new FileReader("C:\\Users\\shushruth\\Downloads\\eventb.txt"));

			while ((currLine = br.readLine()) != null) {
				fileText.append(currLine);
				firstvarIndex = fileText.indexOf("VARIABLES");
				varIndex = firstvarIndex + 9;
				firsteventIndex = fileText.indexOf("EVENTS");
				firstInitIndex = fileText.indexOf("INITIALISATION");
				initIndex = firstInitIndex + 13;
			}

			//System.out.println(events);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		EventB eb = new EventB();
		eb.readInfo();
	}
}