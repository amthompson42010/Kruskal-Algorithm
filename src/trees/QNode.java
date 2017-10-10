/**
 * @author Alexander Mark Thompson
 * @title CS 201 - Project 3 - Kruskal's Implementation
 * @description This file contains a class to create a queue node
 */
package trees;
public class QNode {
	private Node index;
	private QNode next;
	
	public QNode(){}
	
	public Node get(){return index;}
	public QNode next(){return next;}
	
	public void setItem(Node n){index=n;}
	public void setNext(QNode q){next=q;}
	
}