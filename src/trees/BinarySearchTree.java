/**
 * @author Alexander Mark Thompson
 * @title CS 201 - Project 3 - Kruskal's Implementation
 * @description This file contains a class to create a Binary Search Tree
 */

package trees;
import java.util.ArrayList;

public class BinarySearchTree
{
	protected static Node root;
	protected static long count =0L;
	/**
	 * Constructors for Binary Search Tree class
	 */
	public BinarySearchTree() {}
	public BinarySearchTree(ArrayList<String> words)
	{
		for(String word : words)
		{
			root = insert(word);
		}
	}

	/**
	 * Gets and sets for the variables used in this file
	 * @return [description]
	 */
	public static Node getRootNode() { return root; }
	public void setRootNode(Node node) { root = node; }
	protected long getCount() { return count; }

	protected Node insert(String word)
	{
		root = insert(root, root, word, 1);
		return root;
	}

	private Node insert(Node node, Node node2, String word, int level)
	{
		if(node == null)
		{
			count++;
			node = new Node(word, node2, level);
			if(root == null)
			{
				node.setParentNode(node);
			}
			return node;
		}
		switch(compare(word, node.getData()))
		{
			case(-1):
				node.setLeftNode(insert(node.getLeftNode(), node, word, ++level));
				break;
			case(1):
				node.setRightNode(insert(node.getRightNode(), node, word, ++level));
				break;
			default:
				node.incFreq();
		}
		return node;
	}

	protected int getFrequency(String word)
	{
		return getFrequency(root, word);
	}
	protected int getFrequency(Node node, String word)
	{
		if(node == null)
		{
			return 0;
		}
		switch(compare(word, node.getData()))
		{
			case(-1):
				return getFrequency(node.getLeftNode(), word);
			case(1):
				return getFrequency(node.getRightNode(), word);
			case(0):
				return node.getFrequency();
			default:
				return 0;
		}
	}

	protected int compare(String word, String data)
	{
		if(word.compareTo(data) > 0)
		{
			return 1;
		}
		else if(word.compareTo(data) < 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}