/**
 * @author  Alexander Mark Thompson
 * @title CS 201 - Project 3 - Kruskal's Implementation
 * @description This class sets up an edge node.
 */
package trees;
public class EdgeNode
{
	public Integer ID, parent, weight;
	public boolean isRoot;

	public EdgeNode(Integer id, Integer par, Integer w)
	{
		ID = id;
		parent = par;
		weight = w;
		isRoot = false;
	}

	public EdgeNode(Integer id)
	{
		ID = id;
		parent = id;
		weight = 1;
		isRoot = true;
	}

	public void print()
	{
		if(isRoot)
		{
			System.out.printf(" " + ID + ";");
		}
		else
		{
			System.out.printf(" " + ID + "(" + parent + ")" + weight + ";");
		}
	}
}