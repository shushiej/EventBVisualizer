import edu.uci.ics.jung.algorithms.filters.KNeighborhoodFilter.EdgeType;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.event.GraphEvent.Vertex;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.security.auth.callback.LanguageCallback;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent.EventType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.Border;

public class MainGUI {
	//Form Variables
	private JTabbedPane tabbedPane;
	private JFrame frame;
	private JLabel varLabel, eventsLabel, initLabel,  whereLabel, thenLabel, endLabel, eventNameLabel;
	private JTextField varTextField, initTextField, eventsTextField ,eventNameTextField, whereTextField, 
	thenTextField;
	private JButton addVar, addEvent,anotherEvent, addGuard, addAction, save, updateGraph;
	private JPanel labelPanel, eventsPanel, varPanel, graphPanel;
	private JTextField newVarTextField;
	private int varCount = 0;
	private boolean initFieldOn = false;
	private boolean firstEventOn = false;
	private Variable var;
	private Variable initVar;
	private ArrayList<Variable> varArr = new ArrayList<Variable>();
	private ArrayList<Variable> initVars = new ArrayList<Variable>();
	private int initCount;
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
	
	//Graph Variables
	private Graph<String,String> graph;
	private VisualizationViewer<String,String> gpanel;
	private Layout<String,String> layout;
	private ArrayList<String> initVarVals = new ArrayList<String>();
	private ArrayList<String> newVarVals = new ArrayList<String>();
	private String initVarVal;
	private String newVarVal;

	public MainGUI(){
		tabbedPane = new JTabbedPane();
		JComponent panel1 = createEventBForm();
		tabbedPane.addTab("Event-B Form", panel1);
		
		JComponent panel2 = createEventBGraph();
		tabbedPane.addTab("Event-B Graph", panel2);
		
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}	
	

	public JComponent createEventBForm(){

		
		varLabel = new JLabel("VARIABLES");
		eventsLabel =new JLabel("EVENTS");
		initLabel = new JLabel("INITIALSATION");
		for(Variable var : varArr){
			graph.addVertex(var.toString());
			if(initVar.getVarName().equals(var.getVarName())){
				graph.addEdge(var.toString(), initVar.getVarName(),var.getVarName());
			}
		}
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
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component c : initFieldcontainer){
					JTextField j = (JTextField) c;
					String[] val = j.getText().split("=");
					String varName = val[0];
					String varValS = val[1];
					Integer varVal = Integer.parseInt(varValS);
					var = new Variable(varName, varVal);
					initVar = new Variable(varName, varVal);
					initVars.add(initVar);
					varArr.add(var);
				}
				for(Component a : actionFieldContainer){
					JTextField act = (JTextField) a;
					String fullAction = act.getText();
					actions = getAllActions();
					for(String x: actions){
						if(fullAction.contains(x)){
							action = x;
							action = action.trim();
							String[] assignmentArr = fullAction.split("=");
							String avName = assignmentArr[0].trim();
							String assignment = assignmentArr[1].trim();
							String update[] = assignment.split("\\"+action); 
							String updateValS = update[1].trim();
							Integer updateVal = Integer.parseInt(updateValS);
							assign = new Assignment(avName, updateVal, action);
							aArr.add(assign);	
						}
					}	
				}
				for(Component c : guardFieldContainer){
					JTextField g = (JTextField) c;
					String fullGuard = g.getText();
					conditions = getAllConditions();
					for(String con : conditions){
						if(fullGuard.contains(con)){
							condition = con;
							String[] guardSplit = fullGuard.split(condition);
							String vName = guardSplit[0];
							String vValS = guardSplit[1];
							System.out.println("Value Before is: " + vName + " : " + vValS);
							Integer vValI =  Integer.parseInt(vValS);
					
							Guard guard = new Guard(vName, condition, vValI);
							gArr.add(guard);
							for(Variable v : varArr){
								System.out.println("before guard");
									for(Assignment a : aArr){
										if(guard.checkGuard(v)){
											System.out.println("checked guard");
											if(a.getVarName().equals(v.getVarName())){
												a.update(v, a.getUpdateVal(), a.getCondition());
												System.out.println("Value After is: " + v.toString());
											}
									}
								};
							}
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
		return labelPanel;

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
	
	public JComponent createEventBGraph(){
		graphPanel = new JPanel();
		updateGraph = new JButton("Update Graph");
		updateGraph.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				graph = new DirectedSparseMultigraph<String,String>();
				for(Variable initVar : initVars){
					initVarVal = initVar.getVarName();
					initVarVals.add(initVarVal);
					for(Variable v : varArr){
						if(initVar.getVarName().equals(v.getVarName())){
							graph.addEdge(initVar.toString(),initVar.toString(),v.toString());
						}
						newVarVal = v.getVarName();
						newVarVals.add(newVarVal);
					}
					
				}
				
				
				
				setUpGraph();
			}
		});
		
		graphPanel.add(updateGraph);
		return graphPanel;
	}
	public String createEdge(int i){
		return "Edge " + i;
	}
	public String createGuardEdge(Guard g){
		return g.toString();
	}
	public void setUpGraph(){
		layout = new FRLayout<>(graph);
		layout.setSize(new Dimension(1360,760));
		gpanel = new VisualizationViewer<String,String>(layout);
		gpanel.setPreferredSize(new Dimension(1360,760));
		gpanel.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        gpanel.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		frame.add(gpanel);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public void createGUI(){
		frame = new JFrame("Event B Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.pack();
		frame.setSize(new Dimension(1300,760));
		frame.setVisible(true); 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainGUI g = new MainGUI();
		g.createGUI();
	}

}
