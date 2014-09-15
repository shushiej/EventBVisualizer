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
	private JLabel varLabel, eventsLabel, initLabel,  whereLabel, thenLabel, endLabel, eventNameLabel;
	private JTextField varTextField, initTextField, eventsTextField ,eventNameTextField, whereTextField, 
	thenTextField;
	private JButton addVar, addEvent,anotherEvent, addGuard, addAction, save;
	private JPanel labelPanel, eventsPanel, varPanel;
	private JTextField newVarTextField;
	private int varCount = 0;
	private boolean initFieldOn = false;
	private boolean firstEventOn = false;
	private Variable var;
	private ArrayList<Variable> varArr = new ArrayList<Variable>();
	private int initCount;
	private HashMap<String,Integer> varMap = new HashMap<String,Integer>();
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
			int varIndex =0;
			String valStr;
			int valIndex =0;
			String guardCon;
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component c : initFieldcontainer){
					JTextField j = (JTextField) c;
					String[] val = j.getText().split("=");
					String varName = val[0];
					String varValS = val[1];
					Integer varVal = Integer.parseInt(varValS);
					var = new Variable(varName, varVal);
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
						}
					}
					
					
				}
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