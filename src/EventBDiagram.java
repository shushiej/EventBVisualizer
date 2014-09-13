/*
 * EditingGraphViewer.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.event.GraphEvent.Vertex;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.event.HyperlinkEvent.EventType;

import org.apache.commons.collections15.Factory;

/**
 *This is a modified version of JUNG, in which it takes the Event-B class and 
 *uses the guards and variables in order to make an interactive graph. The vertices
 *represent Variable names and their values, and the Edges represent the guard condition
 * @author Shushruth Joshi
 */
public class EventBDiagram {
    Graph<String, String> g;
    int nodeCount, edgeCount;
    EventBGUI eb = new EventBGUI();  
    ArrayList<Variable> varArr = eb.getVarArray();
    Factory <String> vertexFactory;
    Factory<String> edgeFactory;
    //call EventB.readInfo() to fill in all the values for Variable and Guard
    
    /** Creates a new instance of SimpleGraphView */
    public EventBDiagram() {
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        //this section is responsible to determining what the vertix and Edge displayed is.
    	createGraph();
    }
    public void createGraph(){
    	   g = new SparseMultigraph<String, String>();
    	   System.out.println(varArr.size());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventBDiagram sgv = new EventBDiagram();
        // Layout<V, E>, VisualizationViewer<V,E>
        Layout<Integer, String> layout = new StaticLayout(sgv.g);
        layout.setSize(new Dimension(300,300));
        VisualizationViewer<Integer,String> vv = new VisualizationViewer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,350));
        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        // Create a graph mouse and add it to the visualization viewer
        // Our Vertices are going to be Integer objects so we need an Integer factory
        JFrame frame = new JFrame("Editing Graph Viewer 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        
        // Let's add a menu for changing mouse modes
        frame.pack();
        frame.setVisible(true);   
    }
}