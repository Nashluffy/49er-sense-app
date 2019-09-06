import java.util.HashSet;
import java.util.Set;

public class LinkedList {

	Node head;
	
	static class Node {
		int data;
		Node next;
		Node(int d) {
			data = d;
			next = null;
		}
	}
	
	public void insert(int data) {
		Node new_node = new Node(data);
		new_node.next = null;
		if(this.head == null) {
			this.head = new_node;
		}
		else {
			Node currentNode = this.head;
			while (currentNode.next != null) {
				currentNode = currentNode.next;
			}
			currentNode.next = new_node;
		}
	}
	
	public void sort() {
		Node outerNode = this.head;
		Node innerNode = this.head;
		while(outerNode.next != null) {
			while(innerNode.next != null) {
				if(innerNode.next.data > outerNode.next.data) {
					int temp = innerNode.next.data;
					innerNode.next.data = outerNode.next.data;
					outerNode.next.data = temp;
				}
				innerNode = innerNode.next;
			}
			innerNode = this.head;
			outerNode = outerNode.next;
		}
	}
	
	
	public void removeDuplicates() {
		Node currentIterator = this.head;
		Node previousIterator = null;
		
		Set<Integer> values = new HashSet<>();
		
		while(currentIterator != null) {
			if (values.contains(currentIterator.data)) {
				previousIterator.next = currentIterator.next;
				values.remove(currentIterator);
			}
			else {
				values.add(currentIterator.data);
				previousIterator = currentIterator;
			}
			currentIterator = previousIterator.next;
		}
		
	}
	
	public void print() {
		Node iteratorNode = this.head;
		while (iteratorNode != null) {
			System.out.print(iteratorNode.data);
			System.out.println();
			iteratorNode = iteratorNode.next;
		}
	}
	
	public void palindrome() {
		Node iteratorNode = this.head;
		StringBuilder WIP = new StringBuilder("");
		String reversed;
		while(iteratorNode != null) {
			WIP.append(iteratorNode.data);
			
			iteratorNode = iteratorNode.next;
		}
		reversed = WIP.reverse().toString();
		WIP.reverse();
		if(reversed.equals(WIP.toString())) {
			System.out.print("The LinkedList is a Palindrome!");
			System.out.println();
			System.out.print("Reversed String: ");
			System.out.print(WIP.reverse().toString());
			System.out.println();
			System.out.print("Final String: ");
			System.out.print(WIP.toString());
			System.out.println();
		}
		else {
			System.out.print("The LinkedList is not a Palindrome");
			System.out.println();
			System.out.print("Reversed String: ");
			System.out.print(WIP.reverse().toString());
			System.out.println();
			System.out.print("Final String: ");
			System.out.print(WIP.toString());
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		
		//Insert a Palindrome
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.insert(4);
		list.insert(5);
		list.insert(5);
		list.insert(4);
		list.insert(3);
		list.insert(2);
		list.insert(1);
	
		// Testing Palindrome
		list.palindrome();
		
		
		//Testing sort
		list.sort();
		list.print();
		
		//Testing duplicate removal
		list.removeDuplicates();
		list.print();
	}
	
}