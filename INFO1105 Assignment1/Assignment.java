import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.junit.rules.*;

public class Assignment implements Calendar {
	// create a Node root in order to create a tree
	public static Node root;
	// list the location
	public static List<Appointment> AppointmentListonLocation = new ArrayList<Appointment>();
	public ExpectedException thrown = ExpectedException.none();

	// The default constructor for the class should be public
	// We will use this when we test your code!
	public Assignment() {
		this.root = null;
	}

	@Override

	public List<Appointment> getAppointments(String location) {
		if (location == null) {
			throw new IllegalArgumentException();
		}
		AppointmentListonLocation.clear(); // always empty the list when looking
											// for a new location
		if (root != null)
			traversal(root, location);
		return AppointmentListonLocation;
	}

	public void traversal(Node root, String Location) {
		if (root != null) {
			traversal(root.left, Location);
			if (root.data.getLocation().equals(Location)) {
				AppointmentListonLocation.add(root.data);
			}
			traversal(root.right, Location);
		}
	}

	@Override
	public Appointment getNextAppointment(Date when) {
		if (when == null) {
			throw new IllegalArgumentException();
		}
		/*
		 * use the created method called inOrderIterFind to get the next
		 * appointment's time without checking location
		 */
		return inOrderIterFind(root, when, "null");

	}

	@Override
	public Appointment getNextAppointment(Date when, String location) {
		if (when == null || location == null) {
			throw new IllegalArgumentException();
		}
		/*
		 * use the created method called inOrderIterFind to get the next
		 * appointment's time with checking location
		 */
		return inOrderIterFind(root, when, location);
	}

	@Override
	public void add(String description, Date when, String location) {
		if (description == null || when == null || location == null)
			throw new IllegalArgumentException();
		// Initialization
		MyAppointment NewAppoint = new MyAppointment(description, when, location);
		Node newNode = new Node(NewAppoint);

		// check if it's the first node; if so, create as root
		if (root == null) {
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;

		// else find the place to insert the node
		while (true) {
			parent = current;
			// if the parameter "when" is greater than or equal to the current's
			// time
			// then the current node will add the new node as a right child
			// based on the right side of current node is empty, the new node
			// will insert in the current node's right child node.
			// oppositely, if new node is less than current node, it will be
			// added to the current node's left child if empty.
			if (when.compareTo(current.data.getStartTime()) < 0) {
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					return;
				}
			} else {
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					return;
				}
			}
		}
	}

	@Override
	public void remove(Appointment appointment) {
		if (appointment == null)
			throw new IllegalArgumentException();

		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while (!current.AppointmentEqual(appointment)) {
			parent = current;
			if (current.data.getStartTime().compareTo(appointment.getStartTime()) > 0) {
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			if (current == null) {
				break;
			}
		}

		// if i am here that means we have found the node
		// Case 1: if node to be deleted has no children
		if (current.left == null && current.right == null) {
			if (current == root) {
				root = null;
			}
			if (isLeftChild == true) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}

		// Case 2 : if node to be deleted has only one child
		// left child only
		else if (current.right == null) {

			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		}

		// right child only
		else if (current.left == null) {

			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else if (current.left != null && current.right != null) {

			// now we have found the minimum element in the right sub tree
			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		} // while

	}

	// this method is used for searching
	// the next node of the removed node;
	public Node getSuccessor(Node deleteNode) {
		Node successsor = null;
		Node successsorParent = null;
		Node current = deleteNode.right;
		// keep searching the node when the current root
		while (current != null) {
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		// check if successor has the right child, it cannot have left child for
		// sure
		// if it does have the right child, add it to the left of
		// successorParent.
		if (successsor != deleteNode.right) {
			successsorParent.left = successsor.right;
			successsor.right = deleteNode.right;
		}
		return successsor;
	}

	public Appointment inOrderIterFind(Node root, Date when, String location) {
		// Node target = root;
		if (root == null)
			return null;

		Stack<Node> s = new Stack<Node>();
		Node currentNode = root;

		while (!s.empty() || currentNode != null) {

			if (currentNode != null) {
				s.push(currentNode);
				currentNode = currentNode.left;
			} else {
				Node n = s.pop();
				if (n.data.getStartTime().compareTo(when) == 0
						&& (location.equals("null") || n.data.getLocation().equals(location)))
					return n.data;
				else if (n.data.getStartTime().compareTo(when) > 0
						&& (location.equals("null") || n.data.getLocation().equals(location)))
					return n.data;
				else
					currentNode = n.right;
			} // else
		} // while
		return null;
	} // function

	////// For display
	public void display(Node root) {
		if (root != null) {
			display(root.left);
			System.out.print(" " + root.data.getDescription());
			display(root.right);
		}
	}
}
