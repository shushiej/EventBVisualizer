import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.Border;

public class EventBGUI extends JFrame implements ActionListener {
	private JFrame frame;
	private JLabel varLabel,
	eventsLabel,
	initLabel,  
	whereLabel,
	thenLabel, 
	endLabel, 
	eventNameLabel;
	private JTextField varTextField, 
initTextField, 
eventsTextField ,
eventNameTextField, 
whereTextField, 
	thenTextField;
	private JButton addVar, 
	addEvent,
	anotherEvent, 
	addGuard, 
	addAction, 
	save;
	private JPanel 
	labelPanel, 
	eventsPanel,
	varPanel;
	private JTextField newVarTextField;
	private int varCount = 0;
	private boolean initFieldOn = false;
	private boolean firstEventOn = false;
	private Variable var;
	private ArrayList<Variable> varArr = new ArrayList<Variable>();
	private int initCount;
	private Variable initVar;
	private HashMap<String,Integer> varMap = new HashMap<String,Integer>();
	private ArrayList<Variable> initVars = new ArrayList<Variable>();
	private ArrayList<Container> varFieldContainer = new ArrayList<Container>();
	private ArrayList<Container> initFieldcontainer = new ArrayList<Container>();
	private ArrayList<Container> guardFieldContainer = new ArrayList<Container>();
	private ArrayList<Container> actionFieldContainer = new ArrayList<Container>();
	private ArrayList<String> conditions = new ArrayList<String>();
	private String condition;
	private ArrayList<String> actions = new ArrayList<String>();
	private String action;
	private Assignment assign;
	private ArrayList<Assignment> aArr = new ArrayList<Assignment>();
	private ArrayList<Guard> gArr = new ArrayList<Guard>();
	
	public EventBGUI(){
		
	}
	
	public void readInfo(){
		frame = new JFrame("Event B Form");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		varLabel = new JLabel("VARIABLES");
		eventsLabel =new JLabel("EVENTS");
		initLabel = new JLabel("INITIALSATION");
		
		addVar = new JButton("Add new Variable");
		addVar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				initFieldOn = true;
				varCount += 1;
				newVarTextField = new JTextField();
				varFieldContainer.add(newVarTextField);
				labelPanel.add(newVarTextField);
				labelPanel.add(addEvent);
				labelPanel.add(eventsLabel);
				labelPanel.add(initLabel); 
				labelPanel.revalidate();
				labelPanel.repaint();
			}
		});
		//get all the components and get the text from each component
		
		addEvent = new JButton("Add new Event");
		addEvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				firstEventOn = true;
				String eventName = JOptionPane.showInputDialog(null, "Enter Event Name: ");
				eventNameLabel = new JLabel(eventName.toUpperCase());
				whereLabel = new JLabel("WHERE");
				thenLabel = new JLabel("THEN");
				endLabel = new JLabel("END");
				eventsTextField = new JTextField();
				eventNameTextField = new JTextField("Add New Event Name");
				whereTextField = new JTextField();
				thenTextField = new JTextField();
				if(initFieldOn){
					for(int i = 1 ;i <= varCount ; i ++){
						initCount += 1;
						initFieldOn = false;	
						initTextField = new JTextField();
						initFieldcontainer.add(initTextField);
						labelPanel.add(initTextField);
						labelPanel.revalidate();
						labelPanel.repaint();
					}
				labelPanel.add(eventNameLabel);
				labelPanel.add(whereLabel);				
				labelPanel.add(whereTextField);	
				guardFieldContainer.add(whereTextField);
				labelPanel.add(addGuard);
				labelPanel.add(thenLabel);
				labelPanel.add(thenTextField);
				actionFieldContainer.add(thenTextField);
				labelPanel.add(addAction);
				labelPanel.add(endLabel);
				labelPanel.add(anotherEvent);
				initTextField.selectAll();
				labelPanel.revalidate();
				labelPanel.repaint();
			}
			}
		});
		JScrollPane scrPane = new JScrollPane(labelPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		addGuard = new JButton("Add new Guard");

		addAction = new JButton("Add new Action");
		anotherEvent =new JButton("Add Another Event");
		anotherEvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(firstEventOn){
					String eventName = JOptionPane.showInputDialog(null, "Enter Event Name: ");
					eventNameLabel = new JLabel(eventName.toUpperCase());
					whereLabel = new JLabel("WHERE");
					thenLabel = new JLabel("THEN");
					endLabel = new JLabel("END");
					eventsTextField = new JTextField();
					eventNameTextField = new JTextField("Add New Event Name");
					whereTextField = new JTextField();
					thenTextField = new JTextField();
					labelPanel.add(eventNameLabel);
					labelPanel.add(whereLabel);				
					labelPanel.add(whereTextField);
					guardFieldContainer.add(whereTextField);
					labelPanel.add(addGuard);
					labelPanel.add(thenLabel);
					labelPanel.add(thenTextField);
					actionFieldContainer.add(thenTextField);
					labelPanel.add(addAction);
					labelPanel.add(endLabel);
					initTextField.selectAll();
					labelPanel.revalidate();
					labelPanel.repaint();
				}
			}
		});
		
		save = new JButton("Save Code");
		//Stores all the values of the form
		save.addActionListener(new ActionListener() {
			String guardStr;
			Integer guardVal;
			String guardCon;
			String actionVar;
			Integer actionVal;
			String action;
			boolean readyToUpdate = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component c : initFieldcontainer){
					JTextField j = (JTextField) c;
					String[] val = j.getText().split("=");
					String varName = val[0].trim();
					String varValS = val[1].trim();
					Integer varVal = Integer.parseInt(varValS);
					var = new Variable(varName, varVal);
					initVar = new Variable(varName, varVal);
					initVars.add(initVar);
					varArr.add(var);
					varMap.put(varName, varVal);
				}
				for(Component c : guardFieldContainer){
					JTextField g = (JTextField) c;
					String fullGuard = g.getText();
					conditions = getAllConditions();
					for(String con : conditions){
						if(fullGuard.contains(con)){
							guardCon = con;	
							guardCon = guardCon.trim();
						}
					}
					guardStr = getGuardVarName(fullGuard);
					guardVal = getGuardVarValue(fullGuard);
					Guard guard = new Guard(guardStr,guardCon,guardVal);
					System.out.println(guard.getGuardName() + " "  + guard.getCondition() + " " + guard.getConditionValue());
					gArr.add(guard);
				}
				for(Variable v : varArr){
					for(Guard g : gArr){
						if(v.getVarName().equals(g.getGuardName())){
							if(checkGuardCondition(g.getCondition(),v, g)){
								for(Component a : actionFieldContainer){
									JTextField act = (JTextField) a;
									String fullAction = act.getText();
									actions = getAllActions();
									for(String ac : actions){
										if(fullAction.contains(ac)){
											action = ac;
											action = action.trim();
										}
									}
									String[] assignmentArr = fullAction.split("=");
									actionVar = assignmentArr[0].trim();
									String assignment = assignmentArr[1].trim();
									String update[] = assignment.split("\\"+action); 
									String updateValS = update[1].trim();
									actionVal = Integer.parseInt(updateValS);
									assign = new Assignment(actionVar, actionVal, action);
									aArr.add(assign);
									readyToUpdate = true;	
							}
						}
					}
				}
				
			}
			//do the update here.
			for(Variable v: varArr){
				for(Assignment assign : aArr){
					if(readyToUpdate){
						if(v.getVarName().equals(assign.getVarName())){
							assign.update(v,assign.getUpdateVal(), assign.getCondition());
							System.out.println(v.getVarName() +" : " +  v.getVarValue());
						}
					}
				}		
			}	
			readyToUpdate = false;
		}
		});		
		labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
		labelPanel.add(varLabel);
		labelPanel.add(addVar);

		labelPanel.add(save);
		frame.pack();
		frame.setSize(new Dimension(1300,760));
		frame.add(scrPane);
		frame.add(labelPanel);
		frame.setVisible(true); 
	}
	public boolean checkGuardCondition(String con, Variable v, Guard g){
		if(con.equals("=")){
			if(v.getVarValue() == g.getConditionValue()){
				return true;
			}
		}
		else if(con.contains("<=")){
			System.out.println("Reached" + con);
			if(v.getVarValue().intValue() <= g.getConditionValue().intValue() ){
				System.out.println("Evaluated" + con);
				return true;
			}
		}
		else if(con.equals(">=")){
			System.out.println("Reached" + con);
			if(v.getVarValue().intValue()  >= g.getConditionValue().intValue() ){
				System.out.println("Evaluated" + con);
				return true;
			}
		}
		else if(con.equals("!=")){
			System.out.println("Reached" + con);
			if(v.getVarValue().intValue()  != g.getConditionValue().intValue() ){
				System.out.println("Evaluated" + con);
				return true;
			}
		}
		else if(con.equals("<")){
			System.out.println("Reached" + con);
			if(v.getVarValue().intValue()  < g.getConditionValue().intValue() ){
				System.out.println("Evaluated" + con);
				return true;
			}
		}
		else if(con.equals(">")){
			System.out.println("Reached" + con);
			if(v.getVarValue().intValue()  > g.getConditionValue().intValue() ){
				System.out.println("Evaluated" + con);
				return true;
			}
		}
		else {
			return false;
		}
		return false;
	}
	public Integer getGuardVarValue(String s){
		String delimiter;
		String[] split;
		String valString;
		Integer val;
		if(s.contains("<=")){
			delimiter = "@";
			s = s.replace("<=", delimiter);
		}
		else if(s.contains(">=")){
			delimiter = "#";
			s = s.replace(">=", delimiter);
		}
		else if(s.contains("!=")){
			delimiter = "$";
			s = s.replace("!=", delimiter);
		}
		else if(s.contains("<")){
			delimiter = "<";
			s = s.replace("<", delimiter);
		}
		else if(s.contains(">")){
			delimiter = ">";
			s = s.replace(">", delimiter);
		}
		else if(s.contains("=")){
			delimiter = "=";
			s = s.replace("=", delimiter);
		}
		else{
			return 0;
		}
		split = s.split(delimiter);
		valString = split[1];	
		val = Integer.parseInt(valString);
		return val;
	}
	public String getGuardVarName(String s){
		String delimiter;
		String[] split;
		String valString;
		if(s.contains("<=")){
			delimiter = "@";
			s = s.replace("<=", delimiter);
		}
		else if(s.contains(">=")){
			delimiter = "#";
			s = s.replace(">=", delimiter);
		}
		else if(s.contains("!=")){
			delimiter = "$";
			s = s.replace("!=", delimiter);
		}
		else if(s.contains("<")){
			delimiter = "<";
			s = s.replace("<", delimiter);
		}
		else if(s.contains(">")){
			delimiter = ">";
			s = s.replace(">", delimiter);
		}
		else if(s.contains("=")){
			delimiter = "=";
			s = s.replace("=", delimiter);
		}
		else{
			return "No delimter found";
		}
		split = s.split(delimiter);
		valString = split[0];		
		return valString;
	}
	public void paintComponent(Graphics g){
		super.paintComponents(g);

	}
	public static void main(String[] args){
		EventBGUI e = new EventBGUI();
		e.readInfo();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	public ArrayList<String> getAllConditions(){
		conditions.add("=");
		conditions.add(">");
		conditions.add("<");
		conditions.add("<=");
		conditions.add(">=");
		conditions.add("!=");
		return conditions;
	}
	public ArrayList<String> getAllActions(){
		actions.add("+");
		actions.add("-");
		return actions;
	}
	public ArrayList<Variable> getVarArray(){
		return varArr;
	}
	public ArrayList<Assignment> getAssignmentArray(){
		return aArr;
	}
	public ArrayList<Guard> getGuardArray(){
		return gArr;
	}
}