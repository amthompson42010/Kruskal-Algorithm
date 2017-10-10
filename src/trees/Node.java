/**
 * @author Alexander Mark Thompson
 * @title CS 201 - Project 3 - Kruskal's Implementation
 * @description This file contains a class to create a node
 */
package trees;
public class Node {
	private Node Left,Right,Parent;
	private String data;
	private boolean red;
	private int frequency;
	private int depth;
	
	public Node(String s,Node p, int i){
		Left=Right=null;
		data=s;
		red=true;
		frequency=1;
		depth=i;
		Parent=p;
	}

	public boolean isRed()
	{
		if(red){return true;}
		else{return false;}
	}
	
	public void setLeftNode(Node n){this.Left=n;}
	
	public void setRightNode(Node n){this.Right=n;}
	
	public void setParentNode(Node n){this.Parent=n;}
	
	public void setData(String s){this.data=s;}
	
	public void makeRed(){this.red=true;}
	
	public void makeBlack(){this.red=false;}
	
	public void incFreq(){this.frequency++;}
	
	public void decFreq(){this.frequency--;}

	public void setDepth(int d){this.depth=d;}
	
	public Node getLeftNode(){return this.Left;}
	
	public Node getRightNode(){return this.Right;}
	
	public Node getParentNode(){return this.Parent;}
	
	public String getData(){return this.data;}
	
	public int getFrequency(){return this.frequency;}
	
	public int getDepth(){return this.depth;}
	
	public String getLorR() {
		if(this.Parent.Left!=null&&this.data==this.Parent.Left.data){return "L";}
		else {return "R";}
	}

	public Node getUncle() {//might be wrong
		if(this.Parent.getLorR()=="L"){return this.Parent.Parent.Right;}
		else{return this.Parent.Parent.Left;}
	}

	public boolean linear() {
		if(this.getLorR()=="L"&&this.Parent.getLorR()=="L"||this.getLorR()=="R"&&this.Parent.getLorR()=="R"){return true;}
		else{return false;}
	}

	public Node getSibbling() {
		if(this.getLorR()=="L"){return this.Parent.Right;}
		else if(this.getLorR()=="R"){return this.Parent.Left;}
		else return null;
	}

	public Node getNephew() {
		if(this.getLorR()=="L"){return this.Parent.Right.Right;}
		else if(this.getLorR()=="R"){return this.Parent.Left.Left;}
		else return null;
	}

	public Node getNeice() {
		if(this.getLorR()=="L"){return this.Parent.Right.Left;}
		else if(this.getLorR()=="R"){return this.Parent.Left.Right;}
		else return null;
	}

	public String isRedPrint() {
		if(red){return "*";}
		else{return "";}
	}

}