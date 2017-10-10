/**
 * @author Alexander Mark Thompson
 * @title CS 201 - Project 3 - Kruskal's Implementation
 * @description This file contains a class to create a vertex queue node
 */
package trees;
public class VQNode {
	private Vertex index;
	private VQNode next;
	public Integer weight;
	
	public VQNode(){}
	
	public Vertex get(){return index;}
	public VQNode next(){return next;}
	
	public void setItem(Vertex v){index=v;}
	public void setNext(VQNode q){next=q;}
	
}