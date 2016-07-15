package socialDataMain;

import java.util.HashMap;

//directed graph
public class directedGraph {

	private HashMap<String,String> vertexMap;
	private HashMap<String,Double> edgeMap;
	public directedGraph(){
		vertexMap = new HashMap<String, String>();
		edgeMap = new HashMap<String,Double>();
	}
	
	public boolean addVertex(String sAdd){
		if (false == vertexMap.containsKey(sAdd)) {
			
			vertexMap.put(sAdd, sAdd) ;
			return true;
		}
		return true;
	}
	
	public boolean addEdge(String ver1, String ver2){
		if (vertexMap.containsKey(ver1) && vertexMap.containsKey(ver2)) {
			String edgeKey = ver1 + "," + ver2;
			if (false == vertexMap.containsKey(edgeKey)) {
				edgeMap.put(edgeKey, (double) 0);
				return true;
			}
			return true;
		}
		
		return false;
	}
	
	public boolean addEdge(String ver1, String ver2,double weight){
		if (vertexMap.containsKey(ver1) && vertexMap.containsKey(ver2)) {
			String edgeKey = ver1 + "," + ver2;
			if (false == vertexMap.containsKey(edgeKey)) {
				edgeMap.put(edgeKey, weight);
				return true;
			}
			return false;
		}
		
		return false;
	}
	
	public boolean containVer(){
		
		
		return false;
	}
	
}
