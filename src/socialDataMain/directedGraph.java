package socialDataMain;

import java.util.HashMap;

//directed graph
public class directedGraph {

	private HashMap<String,HashMap<String,Double>> vertexMap;
	
	//new map space for ver
	public directedGraph(){
		vertexMap = new HashMap<String, HashMap<String,Double>>();
	}
	
	//add ver add new sapce for edge
	public boolean addVertex(String sAdd){
		
		if(vertexMap.containsKey(sAdd))
			return true;
		vertexMap.put(sAdd, new HashMap<String, Double>());
		return true;
		
	}
	
	//add edges with no weight 
	public boolean addEdge(String ver1, String ver2){
		
		if(true == vertexMap.containsKey(ver1)){
			HashMap<String, Double> temp = vertexMap.get(ver1);
			temp.put(ver2, 0.0);
			return true;
		}
		else{
			return false;
		}
	}
	
	//add edge with weight
	public boolean addEdge(String ver1, String ver2,double weight){
		
		if(true == vertexMap.containsKey(ver1)){
			HashMap<String, Double> temp = vertexMap.get(ver1);
			temp.put(ver2, weight);
			return true;
		}
		else{
			return false;
		}
	}
	
	//contain vertex or not
	public boolean containVer(String ver){
		
		return vertexMap.containsKey(ver);
	}
	
	//contain edge or not
	public boolean containEdge(String ver1, String ver2) {

		if (true == vertexMap.containsKey(ver1)) {
			HashMap<String, Double> temp = vertexMap.get(ver1);
			if (true == temp.containsKey(ver2)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
}
