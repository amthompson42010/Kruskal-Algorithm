/**
 * @author Alexander Mark Thompson
 * @title CS 201 - Project 3 - Kruskal's Implementation
 * @description This file contains a class to create a disjoint set.
 */
package trees;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DisJointSet {
	public Graph G;
	public Integer root;
	public ArrayList<Edge> DisEdges= new ArrayList<Edge>();
	public DisJointSet(){}
	public DisJointSet(Graph g){
		G=g;
		try{root=g.root.Id;}
		catch(NullPointerException e){root=G.verticesList.root.getData().Id;}
		makeSet();
		PrintSpanningTree(root);
	}

	public void makeSet() {
		getEdges();
		BinarySearchTree verts =new BinarySearchTree(); 
		for(Edge edge : G.edgeList){
			verts=Union(edge,verts);
			verts.insert(edge.start.Id.toString());
			verts.insert(edge.end.Id.toString());
		}
	}
	
	private BinarySearchTree Union(Edge edge,BinarySearchTree disSet) {
		Vertex s=edge.start,d=edge.end;
		s=findSet(s);
		d=findSet(d);
		//check for cycle here
		//			|
		//			|
		//			v
		if(disSet.getFrequency(edge.start.Id.toString())!=0&&disSet.getFrequency(edge.end.Id.toString())!=0){
			if(edge.start.Parent.Id.equals(edge.end.Parent.Id)){
			//		^
			//		|
				edge.start.getEdges().remove(edge);
				edge.end.getEdges().remove(edge);
				return disSet;
			}
		}
		if(s.Parent.rank>d.Parent.rank){
			d.setParentNode(s.Parent);
			d.root=false;
		}
		else if(s.Parent.rank<d.Parent.rank){
			s.setParentNode(d.Parent);
			s.root=false;
		}
		else{
			s.setParentNode(d.Parent);
			d.rank++;
			s.root=false;
		}
		DisEdges.add(edge);
		return disSet;
	}

	public Vertex findSet(Vertex vert) {
		if(vert.Parent==vert){return vert;}
		else{
			vert.Parent=findSet(vert.Parent);
			return vert.Parent;
		}
	}
	
	public void PrintSpanningTree(Integer r){
		if(G.findVertex(r)==null){
			System.out.println("root "+r+" not in Graph");
			return;
		}
		Vertex root=findSet(G.findVertex(r));
		if(root==null){
			System.out.println("root "+r+" not in Graph");
			return;
			}
		G.verticesList.Iterate(G.verticesList.root);
		G.vList=G.verticesList.vlist;
		ArrayList<Vertex> V = G.vList;
		ArrayList<Vertex> VL = new ArrayList<Vertex>();
		Graph g=null;
		int i=0;
		int j=0;		
		for(Vertex v : V){
			i++;
			if(v.getRoot().Id.equals(root.Id)){
				j++;
				VL.add(v);
				
			}
		}
		g=new Graph(VL,DisEdges,r);//should have set with no cycle and no other nodes in graph g original in graph G
		ArrayList<Integer> unReach=g.BFS();
		System.out.println("weight: "+unReach.get(1));
		System.out.println("unreachable: "+(G.verticesList.count-g.verticesList.count));
	}

	public Vertex findRoot(Integer root) {
		for(Vertex v : G.vList){
			if(v.Id.equals(root)){
				G.root=v;
				v=findRoot(v,root);
				return v;
			}
		}
		return null;
	}
	
	public Vertex findRoot(Vertex v ,Integer r) {
		if((v.getRoot()==v)){
			return v;
		}
		else{
			v.setParentNode(findRoot(v.getRoot().Id));
			return v;
		}
	}
	
	public void getEdges(){
		ArrayList<Edge> elis =new ArrayList<Edge>();
		for(Vertex v : G.vList){
			ArrayList<Edge> Vedges=v.getEdges();
			for(Edge e : Vedges){	
				elis.add(e);
			}
		}
		Collections.sort(elis,new Comparator<Edge>(){
			public int compare(Edge  o1, Edge o2){
				return o1.weight-o2.weight;
			}
		});
		G.edgeList=elis;
	}

}