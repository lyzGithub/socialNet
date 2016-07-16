package socialDataMain;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.*;
import org.jgrapht.graph.builder.UndirectedGraphBuilder;

import testBag.testMain;

public class dataMain{
	
	public static void main(String []args) throws FileNotFoundException{
		// create a member for undirectedGraph
		/*
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> netBaseDireG = directGraphB2();
		if(null == netBaseDireG)
			System.out.println("build direct graph fail!");
			
		//System.out.println(netBaseDireG.toString());
		 */
		testMain.main();
		
		//myDirectB();
		
	}
	//////////////////////////////////////////////////////
	//my direct graph	
	public static directedGraph myDirectB() throws FileNotFoundException{
		directedGraph mygraph = new directedGraph();
		System.out.println("laoding data!");
		File netB  = new File("twitterData\\follower_gcc.anony.dat");
		long startTime = 0;
		long endTime = 0;
		if(netB.exists()){
			startTime = System.currentTimeMillis();
			Scanner netVerInput1 = new Scanner(netB);
			String s = "";
			while(netVerInput1.hasNextLine()){
				s = netVerInput1.nextLine();
				String[] ss = s.split(" ");
				mygraph.addVertex(ss[0]);
				mygraph.addVertex(ss[1]);
				mygraph.addEdge(ss[0], ss[1]);
			}
			endTime = System.currentTimeMillis();
			System.out.println("my vertexes add spend : " +( endTime-startTime) + " ms.");
			netVerInput1.close();
			
			/*
			startTime = System.currentTimeMillis();
			Scanner netVerInput2 = new Scanner(netB);
			while(netVerInput2.hasNextLine()){
				s = netVerInput2.nextLine();
				String[] ss = s.split(" ");
				mygraph.addEdge(ss[0], ss[1]);
			}
			endTime = System.currentTimeMillis();
			System.out.println("my edges add spend : " +( endTime-startTime) + " ms.");
			netVerInput2.close();
			*/
			
		}
		
		return null;
	}
	
	
	
	
	
	
	
	/////////////////////////////////////////////////
	//Indirect graph
	public static DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> directGraphB1() throws FileNotFoundException{
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> netWeiGraph = 
				new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		System.out.println("laoding data!");
		File netB  = new File("twitterData\\follower_gcc.anony.dat");
		long startTime = 0;
		long endTime = 0;
		if(netB.exists()){
			Scanner netVerInput1 = new Scanner(netB);
			String s = "";
			startTime = System.currentTimeMillis();
			while(netVerInput1.hasNextLine()){
				s = netVerInput1.nextLine();
				String[] ss = s.split(" ");
				if(false == netWeiGraph.containsVertex(ss[0])){
					//System.out.println("add vertex: " + ss[0]);
					netWeiGraph.addVertex(ss[0]);
				}
				if(false == netWeiGraph.containsVertex(ss[1])){
					//System.out.println("add vertex: " + ss[0]);
					netWeiGraph.addVertex(ss[1]);
				}
				//netWeiGraph.addEdge(ss[0], ss[1]);
				//netWeiGraph.addEdge(ss[1], ss[0]);
			}
			endTime = System.currentTimeMillis();
			System.out.println("vertexes add spend : " +( endTime-startTime) + " ms.");
			netVerInput1.close();
			startTime = System.currentTimeMillis();
			Scanner netVerInput2 = new Scanner(netB);
			while(netVerInput2.hasNextLine()){
				s= netVerInput2.nextLine();
				String[] ss = s.split(" ");
				netWeiGraph.addEdge(ss[0], ss[1],new DefaultWeightedEdge());
				netWeiGraph.addEdge(ss[1], ss[0],new DefaultWeightedEdge());
			}
			endTime = System.currentTimeMillis();
			System.out.println("edges add spend : " +( endTime-startTime) + " ms.");
			netVerInput2.close();
		}
		else{
			System.out.println("file path is wrong!");
			return null;
		}
		return netWeiGraph;
		
	}
	
	///////////////////////////////////////////////////////
	//direct graph build
	public static  DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> directGraphB2() throws FileNotFoundException{
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> netBaseDireG =
	            new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		System.out.println("Please wait, laoding data!!");
		File netDataIn = new File("twitterData\\mention_gcc.anony.dat");//("twitterData\\follower_gcc.anony.dat");
		//File edgeDataIn = new File("twitterData\\testDirectEdgeInfo.txt");
		if(netDataIn.exists() ){ 
			// add the vertices and edges
			long startTime = System.currentTimeMillis();
			Scanner netVerInput = new Scanner(netDataIn);
			while(netVerInput.hasNextLine()){
				String s = netVerInput.nextLine();
				String[] ss = s.split(" ");
				//add vertexes
				if(false == netBaseDireG.containsVertex(ss[0])){
					netBaseDireG.addVertex(ss[0]);
				}
				if(false == netBaseDireG.containsVertex(ss[1])){
					netBaseDireG.addVertex(ss[1]);
				}
				//add edge
				netBaseDireG.addEdge(ss[0], ss[1]);
				netBaseDireG.addEdge(ss[1], ss[0]);
				//set weight
				netBaseDireG.setEdgeWeight(netBaseDireG.getEdge(ss[0], ss[1]), Integer.parseInt(ss[2]));
				netBaseDireG.setEdgeWeight(netBaseDireG.getEdge(ss[1], ss[0]), Integer.parseInt(ss[2]));
				/*System.out.println("weight: " + netBaseDireG.getEdge(ss[0], ss[1]).toString() 
						+netBaseDireG.getEdgeWeight(netBaseDireG.getEdge(ss[0], ss[1])));*/
				
			}
			netVerInput.close();
			long endTime = System.currentTimeMillis();
			System.out.println("Graph built! TimeMillis spend: " + (endTime-startTime) + " ms.");
			
			//compute possibility for each edge to translate info
			Set<String> vertexSet = netBaseDireG.vertexSet();
			for(Object element1: vertexSet){//direction of edge is element2 to element1.
				//System.out.println(element1.toString());
				int allInDegree = 0;
				for(Object element2: vertexSet){
					if(netBaseDireG.containsEdge(element2.toString(), element1.toString())){
						//System.out.println("in add!");
						DefaultWeightedEdge e = netBaseDireG.getEdge(element2.toString(), element1.toString() );
						allInDegree += netBaseDireG.getEdgeWeight(e);
					}
				}
				for(Object element2: vertexSet){
					if(netBaseDireG.containsEdge(element2.toString(), element1.toString())){
						DefaultWeightedEdge e = netBaseDireG.getEdge(element2.toString(), element1.toString());
						double dPlusp = netBaseDireG.getEdgeWeight(e)+(double)1/(allInDegree);
						//System.out.println("dplusp: "+dPlusp);
						netBaseDireG.setEdgeWeight(e, dPlusp);
					}
				}
				
			}
			for(Object element1: vertexSet){
				for(Object element2: vertexSet){
					if(netBaseDireG.containsEdge(element2.toString(), element1.toString())){
						DefaultWeightedEdge e = netBaseDireG.getEdge(element2.toString(), element1.toString());
						double temp1 = netBaseDireG.getEdgeWeight(e);
						int temp2 = (int)temp1;
						double p = temp1-(double)temp2;
						System.out.println("possibility for "+element2.toString()+" to "+element1.toString()
						+" is "+ p );
					}
				}
			}
			
			return netBaseDireG;
		}
		else {
			System.out.println("net data file path wrong !" +netDataIn.getAbsolutePath());
			return null;
		}
	}
	
	
}