/**
 * @author Alexander Mark Thompson
 * @title CS 201 - Project 3 - Kruskal's Implementation
 * @description This file contains a class to create an edge
 */
package trees;
public class Edge {

	public Integer weight=null;
	public Vertex start=null;
	public Vertex end=null;
	
	public Edge(Vertex id1,Vertex id2){
		start=id1;
		end=id2;
		weight=1;
	}
	public Edge(Vertex id1,Vertex id2,Integer w){
		start=id1;
		end=id2;
		weight=w;
	}
	public Edge(Edge e) {
		start=e.end;
		end=e.start;
		weight=e.weight;
	}
	public int compareTo(Edge e1, Edge e2) {
		int i =e1.weight-e2.weight;
		return i;
	}
	
}