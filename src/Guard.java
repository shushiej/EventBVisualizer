
import java.util.ArrayList;

/**
 * This class records the guards from the text file, and is able to check if the condition 
 * of the guard is valid, if it is valid then update the assignment. 
 * @author shushruth
 *
 */
public class Guard {
	
	private String varName;
	private Integer conditionValue;
	private String condition = " ";
	private GuardCondition c;
	public Guard(String name, String con, Integer value){
		varName = name;
		condition = con;
		conditionValue = value;
	}
	public void convertCondition(){
		switch(c){
			case NOT_EQUAL:
			condition = "!=";
			break;
			case LESS_THAN:
			condition = "<";
			break;
			case GREATER_THAN:
			condition = ">";
			break;
			case LESS_THAN_EQUAL_TO:
			condition = "<=";
			case GREATER_THAN_EQUAL_TO:
			condition = ">=";
			break;
			case EQUAL:
			condition = "=";
			default:
			condition = "";
			break;	
		}
	}

	public boolean checkGuard(Variable other){
			if(varName.equals(other.getVarName())){
				if(condition.equals("=")){
					if(conditionValue == other.getVarValue()){
						return true;
					}
					else if(condition.equals("<")){
						if(conditionValue < other.getVarValue()){
							return true;
						}
					}
					else if(condition.equals(">")){
						if(conditionValue > other.getVarValue()){
							return true;
						}
					}
					else if(condition.equals("<=")){
						if(conditionValue <= other.getVarValue()){
							return true;
						}
					}
					else if(condition.equals(">=")){
						if(conditionValue >= other.getVarValue()){
							return true;
						}
					}
					else if(condition.equals("!=")){
						if(conditionValue != other.getVarValue()){
							return true;
						}
					}
					else{
						return false;
					}
				}
			}
		return false;
	}
	public String getCondition(){
		return condition;
	}
	public String getGuardName(){
		return varName;
	}
	public Integer getConditionValue(){
		return conditionValue;
	}
	public String toString(){
		return varName + " " + condition + " " + conditionValue;
	}
}

