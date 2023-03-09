package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E>
{
	BinaryNode<E> root;
	int size;
	private Comparator<E> comparator;
	private ArrayList<E> array = new ArrayList<>();

	public static void main(String[] args)
	{
		BSTVisualizer bstv = new BSTVisualizer("Binary Search Tree", 800, 500);
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();

		//Select which tree to build
		bst.addLeft(bst);

		//Balances tree
		bst.rebuild();

		bstv.drawTree(bst);
		bst.printTree();
	}

	private void addRandom(BinarySearchTree<Integer> bst)
	{
		bst.add(50);
		for (int i = 0; i < 50; i++)
		{
			bst.add((int) (Math.random() * 100));
		}
	}

	private void addLeft(BinarySearchTree<Integer> bst)
	{
		bst.add(1);
		bst.add(2);
		bst.add(4);
		bst.add(8);
		bst.add(16);
		bst.add(32);
		bst.add(64);
		bst.add(128);
		bst.add(256);
		bst.add(512);
		bst.add(1024);
	}

	private void addRight(BinarySearchTree<Integer> bst)
	{
		bst.add(1);
		bst.add(2);
		bst.add(4);
		bst.add(8);
		bst.add(16);
		bst.add(32);
		bst.add(64);
		bst.add(128);
		bst.add(256);
		bst.add(512);
		bst.add(1024);
	}

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree()
	{
		root = null;
		size = 0;
		this.comparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator)
	{
		root = null;
		size = 0;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x)
	{
		return add(x, root);
	}

	private boolean add(E x, BinaryNode<E> n)
	{
		if (n != null)
		{
			int comp = comparator.compare(x, n.element);
			if (comp == 0)
			{
				return false;
			}
			if (comp < 0)
			{
				if (n.left != null)
					return add(x, n.left);
				n.left = new BinaryNode<E>(x);
				size++;
				return true;
			} 
			else if (comp > 0)
			{
				if (n.right != null)
					return add(x, n.right);
				n.right = new BinaryNode<E>(x);
				size++;
				return true;
			}
		} 
		else
		{
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}

		return false;
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height()
	{
		return height(root);
	}

	private int height(BinaryNode<E> n)
	{
		if (n != null)
		{
			return 1 + Math.max(height(n.left), height(n.right));
		}
		
		return 0;
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear()
	{
		size = 0;
		root = null;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree()
	{
		printTree(root);
	}

	private void printTree(BinaryNode<E> n)
	{
		if (n != null)
		{
			printTree(n.left);
			System.out.println(n.element);
			printTree(n.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild()
	{
		toArray(root, array);

		root = buildTree(array, 0, array.size() - 1);
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted)
	{
		if (n != null)
		{
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}

	/*
	 * Builds a complete tree from the elements from position first to
	 * last in the list sorted.
	 * Elements in the list are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last)
	{
		int mid = first + (last - first) / 2;

		if (first > last)
		{
			return null;
		}
		BinaryNode<E> newRoot = new BinaryNode<E>(sorted.get(mid));

		newRoot.left = buildTree(sorted, first, mid - 1);
		newRoot.right = buildTree(sorted, mid + 1, last);

		return newRoot;
	}

	static class BinaryNode<E>
	{
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element)
		{
			this.element = element;
		}
	}
}
