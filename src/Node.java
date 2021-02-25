/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Program:		AVL Tree													 *
 * File:		Node.java													 *
 * Date:		?															 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package edu.utdallas.oar180004.adt.tree.avl;

import java.util.AbstractMap;

public class Node<K extends Comparable<K>, V extends Comparable<V>>
extends AbstractMap.SimpleImmutableEntry<K, V> implements Cloneable, Comparable<Node<K, V>>
{

	private static final long serialVersionUID = 1L;

	private Node<K, V> left;
	private Node<K, V> right;
	private int height;

	public Node(K key, V value, Node<K, V> left, Node<K, V> right)
	{
		super(key, value);
		this.left = left;
		this.right = right;
		if(left == null && right == null)
		{
			height = 0;
		}
		else if(left == null)
		{
			height = right.height + 1;
		}
		else if(right == null)
		{
			height = left.height + 1;
		}
		else
		{
			height = Math.max(left.height, right.height) + 1;
		}
	}

	public Node(Node<K, V> data, Node<K, V> left, Node<K, V> right)
	{
		this(data.getKey(), data.getValue(), left, right);
	}

	public Node(AbstractMap.Entry<? extends K,? extends V> data)
	{
		this(data.getKey(), data.getValue(), null, null);
	}

	public Node(Node<K, V> data)
	{
		this(data, null, null);
	}

	public Node(K key, V value)
	{
		this(new AbstractMap.SimpleImmutableEntry<K, V>(key, value));
	}

	public Node<K, V> getLeft()
	{
		return left;
	}

	public Node<K, V> getRight()
	{
		return right;
	}

	public void setLeft(Node<K, V> left)
	{
		this.left = left;
	}

	public void setRight(Node<K, V> right)
	{
		this.right = right;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getBalance()
	{
		int leftHeight = (left != null) ? left.height : -1;
		int rightHeight = (right != null) ? right.height : -1;
		return leftHeight - rightHeight;
	}

	public int compareTo(Node<K,V> other)
	{
		return getKey().compareTo(other.getKey());
	}

	@Override
	public Node<K, V> clone()
	{
		return new Node<K, V>(getKey(), getValue(), null, null);
	}

	@Override
	public int hashCode()
	{
		return getKey().hashCode();
	}

	@Override
	public String toString()
	{
		return "Key: " + getKey() + " Height: " + height + " Value: " + getValue();
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
		try
		{
			return equals((Node<K, V>) o);
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public boolean equals(Node<K, V> other)
	{
		return getKey().equals(other.getKey());
	}
}
