package socialDataMain;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.net.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;

public class dataMain{
	@SuppressWarnings("resource")
	public static void main(String []args) throws FileNotFoundException{
		// create a menber for undirectedGraph
		UndirectedGraph<String, DefaultEdge> netBaseG =
	            new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	
		System.out.println("Please wait, laoding data!!");
		File netDataIn = new File("twitterData\\follower_gcc.anony.dat");
		
        
		if(netDataIn.exists()){
			// add the vertices and edges
			Scanner netVerInput = new Scanner(netDataIn);
			long startTime = System.currentTimeMillis();
			while(netVerInput.hasNextLine()){
				String s = netVerInput.nextLine();
				String[] ss = s.split(" ");
				if(false == netBaseG.containsVertex(ss[0])){
					netBaseG.addVertex(ss[0]);
				}
				if(false == netBaseG.containsVertex(ss[1])){
					netBaseG.addVertex(ss[1]);
				}
				netBaseG.addEdge(ss[0], ss[1]);
				
			}
			long endTime = System.currentTimeMillis();
			netVerInput.close();
			System.out.println("Graph built! TimeMillis spend: " + (endTime-startTime));
		}
		else {
			System.out.println("net data file path wrong !" +netDataIn.getAbsolutePath());
			return;
		}
		//System.out.println(netBaseG.toString());
	}
	
	
}