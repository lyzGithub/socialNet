package myGraph;

import java.util.HashMap;

//directed graph
public class directedGraph {
	
	private class verInfor{//contain vertex's edge ver2 and prob for transmit, vertex's degree
		private HashMap<String,double[]> edgeInfor;//store infor for edge: ver2, weight, pro
		private double allDegree;//verDegree,direct graph in and out plus or indirect graph degree
		private double inDegree;//in degree
		private double outDegree;
		public verInfor(){
			edgeInfor = new HashMap<String,double[]>();
			allDegree = 0;
			inDegree = 0;
			outDegree = 0;
		}
		public boolean iscontainVer2(String ver2){
			return edgeInfor.containsKey(ver2);
		}
		public boolean addVer2(String ver2){//add ver2 with no weight and prob
			if(edgeInfor.containsKey(ver2) == false){//build as this will not follow away the value contain
				double []add = {0.0,0.0};
				edgeInfor.put(ver2, add);
				return true;
			}
			return false;
			
		}
		public boolean addVer2(String ver2, double w,boolean i){//add ver2 with weight or prob,sele:false weight, true prob 
			
			if(edgeInfor.containsKey(ver2) == false){//build as this will not follow away the value contain
				
				if(false == i){
					double []add = {w,0};
					edgeInfor.put(ver2, add);
					return true;
				}
				if(true == i){
					double []add = {0,w};
					edgeInfor.put(ver2, add);
					return true;
				}
				else{
					System.out.println("wrong select number!");
					return false;
				}
			}
			return false;
			
		}
		
		public boolean addVer2(String ver2,double weight, double prob){//add ver2 with weight and prob
			
			if(edgeInfor.containsKey(ver2) == false){//build as this will not follow away the value contain
				double []add = {weight,prob};
				edgeInfor.put(ver2, add);
				return true;
			}
			return false;
			
		}
		public boolean setEdgePro(String ver2, double prob){
			if(edgeInfor.containsKey(ver2)){
				double []tm = edgeInfor.get(ver2);
				tm[1] = prob;
				return true;
			}
			return false;
		}
		public boolean plusEdgePro(String ver2, double prob){
			if(edgeInfor.containsKey(ver2)){
				double []tm = edgeInfor.get(ver2);
				tm[1] += prob;
				return true;
			}
			return false;
		}
		public double getEdgePro(String ver2){
			if(edgeInfor.containsKey(ver2)){
				double []tm = edgeInfor.get(ver2);
				return tm[1];
			}
			return -1;
			
		}
		public boolean setEdgeWeight(String ver2, double w){
			if(edgeInfor.containsKey(ver2)){
				double []tm = edgeInfor.get(ver2);
				tm[0] = w;
				return true;
			}
			return false;
		}
		public boolean plusEdgeWeight(String ver2, double plus){
			if(edgeInfor.containsKey(ver2)){
				double []tm = edgeInfor.get(ver2);
				tm[0] += plus;
				return true;
			}
			return false;
		}
		
		public double getEdgeWeight(String ver2){
			if(edgeInfor.containsKey(ver2)){
				double[]tm = edgeInfor.get(ver2);
				return tm[0];
			}
			return -1;
		}
		public boolean setAllDegree(double dg){
			allDegree = dg;
			return true;
		}
		public double getAllDegree(){
			return allDegree;
		}
		public boolean setInDegree(double dg){
			inDegree = dg;
			return true;
		}
		public double getInDegree(){
			return inDegree;
		
		}
		public boolean setOutDegree(double dg){
			outDegree = dg;
			return true;
		}
		public double getOutDegree(){
			return  outDegree;
		}
	}
	
	private HashMap<String,verInfor> vertexMap;
	
	//new map space for ver
	public directedGraph(){
		vertexMap = new HashMap<String, verInfor>();
		System.out.println("directedGraph build, init func!");
	}
	
	//add vertex add new space for vertex information edge
	public boolean addVertex(String sAdd){
		
		if(true == containVer(sAdd))
			return true;
		vertexMap.put(sAdd, new verInfor());
		return true;
		
	}
	
	//add edges with no weight 
	public boolean addEdge(String ver1, String ver2){
		
		if(true == containVer(ver1)){
			verInfor temp = vertexMap.get(ver1);
			temp.addVer2(ver2);
			return true;
		}
		else{
			return false;
		}
	}
	
	//add edge with weight
	public boolean addEdge(String ver1, String ver2,double weight){
		
		if(true == containVer(ver1)){
			verInfor temp = vertexMap.get(ver1);
			temp.addVer2(ver2,weight,0);
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
	
	//if do not contain, no set and return false,else set weight add return true.
	public boolean setEdgeWeight(String ver1, String ver2, double weight){
		if (true == containVer(ver1)) {
			verInfor temp = vertexMap.get(ver1);
			return	temp.setEdgeWeight(ver2, weight);
			
		}
		return false;
	}
	
	
	//if do not contain, no set and return false,else plus weight add return true.
	public boolean plusEdgeWeight(String ver1, String ver2, double weight){
		if (true == vertexMap.containsKey(ver1)) {
			verInfor temp = vertexMap.get(ver1);
			return temp.plusEdgeWeight(ver2, weight);
		}
		return false;
	}
	
	
	//contain edge or not
	public boolean containEdge(String ver1, String ver2) {

		if (true == vertexMap.containsKey(ver1)) {
			verInfor temp = vertexMap.get(ver1);
			return temp.iscontainVer2(ver2);
			
		}
		return false;
	}
	
	public String toString(){
		String s = "";
		
		return null;
		
	}
	
}
