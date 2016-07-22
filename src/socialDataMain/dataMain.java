package socialDataMain;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.jgrapht.graph.*;

public class dataMain{
	
	public static void main(String []args) throws IOException{
		// create a member for directedGraph
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> newDDWG = directGraphBuild();
		System.out.println(newDDWG.toString());
		
	}
	
	///////////////////////////////////////////////////////
	//direct graph build
	/*
	 * first build direct graph by follower_gcc.anony.dat
	 * second add edge weight by mention or retweet data
	 */
	public static  DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> directGraphBuild() throws IOException{
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> netBaseDireG =
	            new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		System.out.println("Please wait, laoding data!!");
		//File followerTest = new File("twitterData\\follower_gcc.anony.dat");
		//File mentionTest = new File("twitterData\\mention_gcc.anony.dat");
		//File retweetTest =new File("twitterData\\retweet_gcc.anony.dat");
		File followerTest = new File("twitterData\\followerNet.txt");
		File mentionTest = new File("twitterData\\mentionNet.txt");
		File retweetTest = new File("twitterData\\retweetNet.txt");
		if(followerTest.exists() && mentionTest.exists() && retweetTest.exists()){ 
			// add the vertices and edges
			//follower
			long startTime = System.currentTimeMillis();
			Scanner netVerInput = new Scanner(followerTest);
			while(netVerInput.hasNextLine()){
				String s = netVerInput.nextLine();
				String[] ss = s.split(" ");
				//add vertexes
				netBaseDireG.addVertex(ss[0]);
				netBaseDireG.addVertex(ss[1]);
				//add edge
				netBaseDireG.addEdge(ss[0], ss[1]);
				netBaseDireG.addEdge(ss[1], ss[0]);
				//System.out.println("one line error finish, s:" + s
						//+ " ss[0]:"+ " ss[1]:"+ ss[1]);
				//System.out.println("one line finish!" + s);
				
			}
			netVerInput.close();
			long endTime = System.currentTimeMillis();
			System.out.println("Graph built! seconds spend: " + (endTime-startTime)/1000.0 + " s.");
			
			//add weight of the edge if it's weight is > 0
			//mention
			
			System.out.println("mention add!");
			Scanner mentionInput = new Scanner(mentionTest);
			while(mentionInput.hasNextLine()){
				String s = mentionInput.nextLine();
				String []ss = s.split(" ");
				DefaultWeightedEdge e1 = netBaseDireG.getEdge(ss[0], ss[1]);
				if(null != e1){
					netBaseDireG.setEdgeWeight(e1, Double.parseDouble(ss[2]));
					DefaultWeightedEdge e2 = netBaseDireG.getEdge(ss[1], ss[0]);
					if(null != e2){
						netBaseDireG.setEdgeWeight(e2, Double.parseDouble(ss[2]));
					}
					else {
						System.out.println("graph build error!");
					}
				}
				else{
					System.out.println("mention pair: "+ s +" do not exit in follower!");
				}
			}
			mentionInput.close();
			
			
			//retweet
			System.out.println("retweet add!");
			Scanner retweetInput = new Scanner(retweetTest);
			while(retweetInput.hasNextLine()){
				String s = retweetInput.nextLine();
				String []ss = s.split(" ");
				DefaultWeightedEdge e1 = netBaseDireG.getEdge(ss[0], ss[1]);
				if(null != e1){
					netBaseDireG.setEdgeWeight(e1,netBaseDireG.getEdgeWeight(e1)+ Double.parseDouble(ss[2]));
					DefaultWeightedEdge e2 = netBaseDireG.getEdge(ss[1], ss[0]);
					if(null != e2){
						netBaseDireG.setEdgeWeight(e2, netBaseDireG.getEdgeWeight(e2)+Double.parseDouble(ss[2]));
					}
					else {
						System.out.println("graph build error!");
					}
				}
				else{
					System.out.println("retweet pair: "+ s +" do not exit in follower!");
				}
			}
			retweetInput.close();
			
			
			//compute possibility for each edge to translate info
			System.out.println("compute add !");
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
				for (Object element2 : vertexSet) {
					if (netBaseDireG.containsEdge(element2.toString(), element1.toString())) {
						DefaultWeightedEdge e = netBaseDireG.getEdge(element2.toString(), element1.toString());
						double dPlusp = netBaseDireG.getEdgeWeight(e) + (double) 1 / (allInDegree);
						// System.out.println("dplusp: "+dPlusp);
						netBaseDireG.setEdgeWeight(e, dPlusp);
					}
				}
				
			}
			File outPro = new File("twitterData\\outPro.txt");
			FileWriter fileWriter = new FileWriter(outPro);  
			for(Object element1: vertexSet){
				for(Object element2: vertexSet){
					if(netBaseDireG.containsEdge(element2.toString(), element1.toString())){
						DefaultWeightedEdge e = netBaseDireG.getEdge(element2.toString(), element1.toString());
						double temp1 = netBaseDireG.getEdgeWeight(e);
						int temp2 = (int)temp1;
						double p = temp1-(double)temp2;
						String wr = element2.toString() + " " + element1.toString() + " " + p + "\n";
						fileWriter.write(wr);
					}
				}
			}
			fileWriter.close();
			return netBaseDireG;
		}
		else {
			System.out.println("net data file path wrong !" +followerTest.getAbsolutePath());
			return null;
		}
	}
	
	
}