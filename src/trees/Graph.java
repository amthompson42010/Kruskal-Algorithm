/**
 * @author  Alexander Mark Thompson
 * @title CS 201 - Project 3 - Kruskal's Implementation
 * @description This file creates the graph
 */
package trees;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Graph{

	public VertexBST verticesList;
	public ArrayList<Edge> edgeList;
	public ArrayList<ArrayList<Integer>> eList = new ArrayList<ArrayList<Integer>>();
	public ArrayList<Vertex> vList = new ArrayList<Vertex>();

	public Vertex root;
	public Graph() {}
	public Graph(ArrayList<Vertex> vertList, ArrayList<Edge> EList, Integer node)
	{
		edgeList = EList;
		for(Edge edge : EList)
		{
			ArrayList<Integer> tempEdge = new ArrayList<Integer>();
			tempEdge.add(edge.start.Id);
			tempEdge.add(edge.end.Id);
			tempEdge.add(edge.weight);
			eList.add(tempEdge);
		}
		verticesList = MakeVList();
		verticesList.Iterate(verticesList.root);
		vList = verticesList.vlist;
		root = findVertex(node);
	}

	public Graph(ArrayList<ArrayList<Integer>> edgeList, Integer node)
	{
		eList = edgeList;
		verticesList = MakeVList();
		root = findVertex(node);
		verticesList.Iterate(verticesList.root);
		vList = verticesList.vlist;
	}

	public VertexBST MakeVList()
	{
		VertexBST listContains = new VertexBST();
		for(int i = 0; i < eList.size(); i++)
		{
			ArrayList<Integer> edge = eList.get(i);
			Vertex vertex1 = listContains.find(edge.get(0).toString());
			Vertex vertex2 = listContains.find(edge.get(1).toString());
			if((vertex1 == null) && (vertex2 == null))
			{
				vertex1 = new Vertex(edge.get(0));
				vertex2 = new Vertex(edge.get(1));
				vertex1.addEdge(vertex2, edge.get(2));
				vertex2.addEdge(vertex1, edge.get(2));
				listContains.insert(vertex1);
				listContains.insert(vertex2);
			}
			else if((vertex1 != null) && (vertex2 == null))
			{
				vertex2 = new Vertex(edge.get(1));
				vertex1.addEdge(vertex2, edge.get(2));
				vertex2.addEdge(vertex1, edge.get(2));
				listContains.insert(vertex2);
			}
			else if((vertex1 == null) && (vertex2 != null))
			{
				vertex1 = new Vertex(edge.get(0));
				vertex1.addEdge(vertex2, edge.get(2));
				vertex2.addEdge(vertex1, edge.get(2));
				listContains.insert(vertex1);
				listContains.insert(vertex2);
			}
			else
			{
				vertex1.addEdge(vertex2, edge.get(2));
				vertex2.addEdge(vertex1, edge.get(2));
				listContains.insert(vertex1);
				listContains.insert(vertex2);
			}
		}
		return listContains;
	}

	public Vertex findVertex(Integer i)
	{
		return verticesList.find(i.toString());
	}

	@SuppressWarnings("unused")
	public ArrayList<Integer> BFS()
	{
		ArrayList<ArrayList<EdgeNode>> result = new ArrayList<ArrayList<EdgeNode>>();
		ArrayList<EdgeNode> node = new ArrayList<EdgeNode>();
		ArrayList<Integer> results = new ArrayList<Integer>();
		Integer reach = 0;
		Integer weight = 0;
		root.depth = 0;
		VQueue nodes = new VQueue();
		Vertex temp = root;
		temp.bfsroot = true;
		temp.setParentNode(temp);
		nodes.insert(temp);
		reach++;
		if(verticesList.root == null)
		{
			System.out.println("The tree is empty.");
			return new ArrayList<Integer>(2);
		}
		int nextLevel = 1;
		while(nodes.getfront() != null)
		{
			temp = nodes.getHead();
			for(Edge edge : temp.getEdges())
			{
				edge.end.setParentNode(temp);
			}
			if(temp.color == "b")
			{
				continue;
			}
			temp.color = "b";
			if(temp == null)
			{
				continue;
			}
			if(nextLevel == temp.depth)
			{
				nextLevel++;
				result.add(node);
				node = new ArrayList<EdgeNode>();
				node.add(new EdgeNode(temp.Id, temp.getRoot().Id, temp.tempWeight));
				for(Edge edge : temp.getEdges())
				{
					if(edge.end == null)
					{
						continue;
					}
					if(edge.end.color.equals("b"))
					{
						continue;
					}
					weight += edge.weight;
					edge.end.depth = nextLevel;
					edge.end.tempWeight = edge.weight;
					nodes.insert(edge.end);
					reach++;
				}
				continue;
			}
			else
			{
				if(temp.bfsroot == true)
				{
					node.add(new EdgeNode(temp.Id));
				}
				else
				{
					node.add(new EdgeNode(temp.Id, temp.getRoot().Id, temp.tempWeight));
				}
				for(Edge edge : temp.getEdges())
				{
					if(edge.end == null)
					{
						continue;
					}
					if(edge.end.color.equals("b"))
					{
						continue;
					}
					weight += edge.weight;
					edge.end.depth = nextLevel;
					edge.end.setParentNode(temp);
					edge.end.tempWeight = edge.weight;
					nodes.insert(edge.end);
					reach++;
				}
			}
		}
		result.add(node);
		for(ArrayList<EdgeNode> row : result)
		{
			Collections.sort(row, new Comparator<EdgeNode>()
			{
				public int compare(EdgeNode object1, EdgeNode object2)
				{
					return object1.weight - object2.weight;
				}
			});
		}
		for(int i = 0; i < result.size(); i++)
		{
			System.out.printf((i) + ":");
			for(EdgeNode edge : result.get(i))
			{
				edge.print();
			}
			System.out.println();
		}
		results.add(reach);
		results.add(weight);
		return results;

	}
}