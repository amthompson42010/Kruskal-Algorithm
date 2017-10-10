/**
 * @author Alexander Mark Thompson
 * @title CS 201 - Project 3 - Kruskal's Implementation
 * @description This file contains a class to create a vertex queue
 */
package trees;
public class VQueue {
	private VQNode head,tail;
	private int size;
	
	public VQueue(){
		size=0;
		tail=head=null;
	}

	public VQNode getfront(){return head;}
	public VQNode getnext(){return head.next();}
	public int getsize(){return size;}
	
	public void insert(Vertex v){
		VQNode temp = new VQNode();
		temp.setItem(v);
		temp.setNext(null);
		if(size==0)head=tail=temp;
		else {tail.setNext(temp);tail=temp;}
		size++;
		}
	
	public Vertex getHead(){
		Vertex temp =null;
		if(size>0){
			temp=head.get();
			head=head.next();
			size--;
			if(size==0){tail=head=null;}
		}
		return temp;
	}	
}