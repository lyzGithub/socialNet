package socialDataMain;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import org.jgrapht.graph.*;

public class dataMain{
	
	public static void main(String []args) throws FileNotFoundException{
		// create a member for undirectedGraph
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> netBaseDireG = directGraphB();
		if(null == netBaseDireG)
			System.out.println("build direct graph fail!");
		//System.out.println(netBaseDireG.toString());
		
	}
	
	//direct graph build
	public static  DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> directGraphB() throws FileNotFoundException{
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> netBaseDireG =
	            new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		System.out.println("Please wait, laoding data!!");
		File netDataIn = new File("twitterData\\testUnDirectNet.txt");//("twitterData\\follower_gcc.anony.dat");
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