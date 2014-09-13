import java.util.HashMap;


/**
*Change the class so that it only takes in integers, do not worry about other types.
*/
public class Variable {
	private String varName;
	private Integer varValue;
	private HashMap<String,Integer> varInfo; 
	public Variable(String name){
		varName = name;
	}
	public Variable(String name, Integer val){
		varName = name;
		varValue = val;
		varInfo = new HashMap<String,Integer>();
		varInfo.put(name, val);
	}
	public HashMap<String,Integer> getMap(){
		return varInfo;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public Integer getVarValue() {
		return varValue;
	}
	public void setVarValue(Integer varValue) {
		this.varValue = varValue;
	}
	public String toString(){
		return varName + " : "  + varValue;
	}
}
