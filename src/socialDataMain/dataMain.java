package socialDataMain;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.jgrapht.*;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.*;

public class dataMain{
	@SuppressWarnings("resource")
	public static void main(String []args) throws FileNotFoundException{
		// create a menber for undirectedGraph
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> netBaseDireG =
	            new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	
		
		System.out.println("Please wait, laoding data!!");
		File netDataIn = new File("twitterData\\testNet.txt");//("twitterData\\follower_gcc.anony.dat");
		File edgeDataIn = new File("twitterData\\testEdgeInfo.txt");
		if(netDataIn.exists() && edgeDataIn.exists()){ 
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
				//netBaseDireG.addEdge(ss[0], ss[1]);
				//DefaultWeightedEdge we = new DefaultWeightedEdge();
				netBaseDireG.addEdge(ss[0], ss[1]);
				netBaseDireG.addEdge(ss[1], ss[0]);
				
			}
			netVerInput.close();
			long endTime = System.currentTimeMillis();
			System.out.println("Graph built! TimeMillis spend: " + (endTime-startTime) + " ms.");
			
			//set weight for edges
			startTime = System.currentTimeMillis();
			Scanner edgeInfoInput = new Scanner(edgeDataIn);
			while(edgeInfoInput.hasNextLine()){
				String s = edgeInfoInput.nextLine();
				String[] ss = s.split(" ");
				try{
					netBaseDireG.setEdgeWeight(netBaseDireG.getEdge(ss[0], ss[1]), Integer.parseInt(ss[2]));
					netBaseDireG.setEdgeWeight(netBaseDireG.getEdge(ss[1], ss[0]), Integer.parseInt(ss[2]));
				}
				catch(Exception e){
					System.out.println(e.toString());
				}
			}
			endTime = System.currentTimeMillis();
			System.out.println("Graph edges weight seted! TimeMillis spend: " + (endTime-startTime) + " ms.");
			//algorithm for possibility
			startTime = System.currentTimeMillis();
			
			endTime = System.currentTimeMillis();
			System.out.println("Compute possibility for each edges to translate info! TimeMillis spend: " + (endTime-startTime) + " ms.");
		}
		else {
			System.out.println("net data file path wrong !" +netDataIn.getAbsolutePath());
			return;
		}
		System.out.println(netBaseDireG.toString());
		
	}
	
	
}