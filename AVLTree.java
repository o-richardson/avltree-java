package edu.utdallas.oar180004.adt.tree.avl;

import edu.utdallas.oar180004.adt.tree.avl.Node;

import java.util.TreeMap;

public class AVLTree<K extends Comparable<K>, V extends Comparable<V>>
extends TreeMap<K, V> implements Cloneable, Comparable<AVLTree<K, V>>
{

	private static final long serialVersionUID = 1L;

	private Node<K, V> root;

	public AVLTree()
	{
		root = null;
	}

	private int heightOf(Node<K, V> current)
	{
		return current != null ? current.getHeight() : -1;
	}

	private void fixHeight(Node<K, V> current)
	{
		current.setHeight(Math.max(heightOf(current.getLeft()), heightOf(current.getRight())) + 1);
	}

	public Node<K, V> insert(K key, V value)
	{
		return (root = insert(new Node<K, V>(key, value), root));
    }

	private Node<K, V> insert(Node<K, V> temp, Node<K, V> current)
	{
		if(current == null)
		{
			current = new Node<K, V>(temp);
		}
		else if(temp.compareTo(current) >= 0)
		{
			current.setLeft(insert(temp, current.getLeft()));
			if(Math.abs(heightOf(current.getLeft()) - heightOf(current.getRight())) >= 2)
			{
				if(temp.compareTo(current.getLeft()) >= 0)
				{
					current = leftRotation(current);
				}
				else
				{
					current = rightLeftRotation(current);
				}
			}
		}
		else if(temp.compareTo(current) < 0)
		{
			current.setRight(insert(temp, current.getRight()));
			if(Math.abs(heightOf(current.getRight()) - heightOf(current.getLeft())) >= 2)
			{
				if(temp.compareTo(current.getRight()) < 0)
				{
					current = rightRotation(current);
				}
				else
				{
					current = leftRightRotation(current);
				}
			}
		}
		fixHeight(current);
		return current;
	}

	private Node<K, V> leftRotation(Node<K, V> current)
	{
	    Node<K, V> pivot = current.getLeft();
	    current.setLeft(pivot.getRight());
	    pivot.setRight(current);
	    fixHeight(current);
	    fixHeight(pivot);
	    return pivot;
	}

	private Node<K, V> rightRotation(Node<K, V> current)
	{
		Node<K, V> pivot = current.getRight();
		current.setRight(pivot.getLeft());
		pivot.setLeft(current);
		fixHeight(current);
	    fixHeight(pivot);
	    return pivot;
	}

	private Node<K, V> rightLeftRotation(Node<K, V> current)
	{
		current.setLeft(rightRotation(current.getLeft()));
		return leftRotation(current);
	}

	private Node<K, V> leftRightRotation(Node<K, V> current)
	{
		current.setRight(leftRotation(current.getRight()));
		return rightRotation(current);
	}

	private void makeString(StringBuilder temp, Node<K, V> current)
	{
		if(current != null)
		{
			if(current.getLeft() != null)
			{
				makeString(temp, current.getLeft());
			}
			temp.append(current.toString() + "\n");
			if(current.getRight() != null)
			{
				makeString(temp, current.getRight() );
			}
		}
	}

	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		makeString(result, root);
		return result.toString();
	}

	private AVLTree<K, V> makeClone(AVLTree<K, V> tree, Node<K, V> copy, Node<K, V> original)
	{
		if(original != null)
		{
			if(original.getLeft() != null)
			{
				tree.insert(original.getLeft().getKey(), original.getLeft().getValue());
				makeClone(tree, copy.getLeft(), original.getLeft());
			}
			tree.insert(original.getKey(), original.getValue());
			if(original.getRight() != null)
			{
				tree.insert(original.getRight().getKey(), original.getRight().getValue());
				makeClone(tree, copy.getRight(), original.getRight());
			}
		}
		return tree;
	}

	@Override
	public AVLTree<K, V> clone()
	{
		AVLTree<K, V> clone = new AVLTree<K, V>();
		if(root == null)
		{
			return clone;
		}
		clone.insert(root.getKey(), root.getValue());
		return makeClone(clone, clone.root, root);
	}

	@Override
	public int hashCode()
	{
		return toString().hashCode();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o)
	{
		if(o == null)
		{
			return false;
		}
		if(getClass() != o.getClass())
		{
			return false;
		}
		if(this == o)
		{
			return true;
		}
		AVLTree<K, V> other;
		try
		{
			other = (AVLTree<K, V>) o;
		}
		catch(Exception e)
		{
			return false;
		}
		return toString().equals(other.toString());
	}

	@Override
	public int compareTo(AVLTree<K, V> other)
	{
		return toString().compareTo(other.toString());
	}

}
