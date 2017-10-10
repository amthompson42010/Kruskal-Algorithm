/**
 * @author Alexander Mark Thompson
 * @title CS 201 - Project 3 - Kruskal's Implementation
 * @description This file contains a class to create a vertex Binary Search Tree
 */
package trees;
import java.util.ArrayList;

public class VertexBST {
	protected static VNode root;
	protected static long count=0;
	protected static int minDep=100000;
	protected static int maxDep=0; 
	public ArrayList<Vertex> vlist=new ArrayList<Vertex>();
	
	public VertexBST(){}

	public static VNode getRoot(){return root;}
	public void setRoot(VNode n){root=n;}
	protected long getCount(){return count;}
 	protected int minDepth(){return minDep;}
 	protected int maxDepth(){return maxDep;}
 
	public void Iterate(VNode v){
		if(v==null){return;}
		Iterate(v.getLeftNode());
		vlist.add(v.getData());
		Iterate(v.getRightNode());		
	}
	protected VNode insert(Vertex word) {
		root=insert(root,root,word,1);
		return root;
	}
	private VNode insert(VNode n,VNode p,Vertex word, int lvl){
		if(n==null){
			count++;
			n=new VNode(word,p,lvl);
			if(root==null){n.setParentNode(n);}
			return n;
			}
		switch(compare(word.Id.toString(),n.getData())){
			case(-1):
				n.setLeftNode(insert(n.getLeftNode(),n,word,++lvl));
				break;
			case(1):
				n.setRightNode(insert(n.getRightNode(),n,word,++lvl));
				break;
			default:
				n.incFreq();
			}
		return n;
		}
	
	protected VNode del(Vertex word) {
		return root;
	}
	
	protected VNode del(VNode n,Vertex word) {
		if(n==null){
			System.out.println(word +" not in Tree");
			return null;
		}
		switch(compare(word.Id.toString(),n.getData())){
			case(-1):
				n.setLeftNode(del(n.getLeftNode(),word));
				break;
			case(1):
				n.setRightNode(del(n.getRightNode(),word));
				break;
			case(0):
				if(n.getFreq()>1){n.decFreq();break;}
				else{
					if(n.getRightNode()==null){return n.getLeftNode();}
					if(n.getLeftNode()==null){return n.getRightNode();}
					VNode temp = n;
					n = min(temp.getRightNode());
					n.setRightNode(delMin(temp.getRightNode()));
					n.setLeftNode(temp.getLeftNode());
					count--;
					break;
				}
			default:
				System.out.println("item not in Tree");
			}
		return n;
	}
	private VNode delMin(VNode n){
		if(n.getLeftNode()==null){return n.getRightNode();}
		n.setLeftNode(delMin(n.getLeftNode()));
		return n;
	}
	
	protected int getFreq(String word) {
		return getFreq(root,word);
	}
	protected int getFreq(VNode n,String word) {
		if(n==null){
			return 0;
		}
		switch(compare(word,n.getData())){
			case(-1):
				return getFreq(n.getLeftNode(),word);
			case(1):
				return getFreq(n.getRightNode(),word);
			case(0):
				return n.getFreq();
			default:
				return 0;
		}
	}
	
	
	
	
	protected Vertex find(String word) {
		Vertex v=null;
		try{v = find(root,word).getData();}
		catch(NullPointerException e){}
		return v;
	}
	protected VNode find(VNode n,String word) {
		if(n==null){
			return null;
		}
		switch(compare(word,n.getData())){
			case(-1):
				return find(n.getLeftNode(),word);
			case(1):
				return find(n.getRightNode(),word);
			case(0):
				return n;
			default:
				return null;
		}
	}
	
	private VNode min(VNode n){
		if(n.getLeftNode()==null){return n;}
		else{return min(n.getLeftNode());}
	}
	
	protected static String isRoot(VNode n){
		if(n.getParentNode()==null||n.getData().Id==root.getData().Id){return "X";}
		else{return n.getLorR();}
		}
	
 	protected int compare(String word, Vertex data) {
		if(word.compareTo(data.Id.toString())>0){return 1;}
		else if(word.compareTo(data.Id.toString())<0){return -1;}
		else {return 0;}
	}
}