import java.util.ArrayList;
import java.util.HashMap;
public class EventB {
	private ArrayList<Variable> var;
	private ArrayList<Guard> guard;
	private ArrayList<Assignment> assign;
	private HashMap<ArrayList<Guard>, ArrayList<Assignment>> gToAMap;
	
	public EventB(ArrayList<Variable> varArr,ArrayList<Guard> guardArr,ArrayList<Assignment> assignArr){
		var = varArr;
		guard = guardArr;
		assign = assignArr;
	}
	
	public ArrayList<Variable> getVars(){
		return var;
	}
	public ArrayList<Guard> getGuards(){
		return guard;
	}
	public ArrayList<Assignment> getAssignments(){
		return assign;
	}
	public void provideLink(ArrayList<Guard> gArr, ArrayList<Assignment> assignArr ){
		gToAMap.put(gArr, assignArr);
	}
	public HashMap<ArrayList<Guard>, ArrayList<Assignment>> getGToAMap(){
		return gToAMap;
	}
}