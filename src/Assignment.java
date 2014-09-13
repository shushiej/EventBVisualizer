import java.util.ArrayList;

public class Assignment {
	private AssignmentCondition ac;
	private String varName;
	private Integer value;
	private String assignmentCondition;
	private boolean hasUpdated = false;
	
	public Assignment(String name, Integer val, String action){
		varName = name; 
		value = val;
		assignmentCondition = action;
		 }
	public String getVarName(){
		return varName;
	}
	
	public Integer getUpdateVal(){
		return value;
	}
	public void convertAssignmentCondition(){
		switch(ac){
			case SUBTRACT:
			assignmentCondition = "-";
			break;
			case ADD:
			assignmentCondition = "+";
			break;
			default:
			assignmentCondition =  "";
			break;
		}
	}
	public String getCondition(){
		return assignmentCondition;
	}
	public boolean hasUpdated(){
		return hasUpdated;
	}
	public void update(Variable variable, Integer val, String action){
		Integer newVal;
		assignmentCondition = action;
			if(assignmentCondition.equals("+")){
				newVal = variable.getVarValue() + val;
				variable.setVarValue(newVal);
				hasUpdated = true;

			}
			else if(assignmentCondition.equals("-")){
				newVal = variable.getVarValue() - val;
				variable.setVarValue(newVal);
				hasUpdated = true;

			}
			else{
				hasUpdated = false;
				System.out.println("Could not update");
			}

	}

}
