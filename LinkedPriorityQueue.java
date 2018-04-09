
/**
 * 
 * @author Brandon Mathew
 *
 * This linked priority queue class functions similarly to a
 * linked queue, but also factors in priority in its
 * processing. Priorities are non-negative integers; 0 is
 * the lowest or most important priority.
 */


public class LinkedPriorityQueue<T> implements PriorityQueueADT<T>

{



	/*/
	 * integer 0 is established as lowest or most important priority.
	 * The front, rear, and size (number of elements) of the queue
	 * is also established.
	 */
	public static final int LOW_PRIORITY = 0;

	private PriorityNode<T> front;

	private PriorityNode<T> rear;

	private int size;



	/*/
	 * initialize instance variables
	 */
	public LinkedPriorityQueue(){

		front = null;

		rear = null;

		size = 0;

	}




	/*/
	 * enqueue method created to satisfy ADT
	 */
	public void enqueue(T element) {
		PriorityNode<T> node = new PriorityNode<T>(element);

		if (isEmpty()){
			front = node;
		}
		else {
			rear.setNext(node);
		}

		rear = node;
		size++;

	}




	/*/
	 * the main enqueue method that takes element and priority as parameter
	 */
	public void enqueue(T element, double p){

		PriorityNode<T> current = front;
		PriorityNode<T> previous = null;

		//creating a new node
		PriorityNode<T> node = new PriorityNode<T>(element, p);

		//if the queue is empty, then this node is added
		if (rear == null) {
			front = node;
			rear = node;

		}

		//otherwise, scan through the queue searching
		//for node with priority less than the new node
		else {
			while(current != null && current.getPriority() >= p){
				previous = current;
				current = current.getNext();
			}

			if (current==null){
				rear.setNext(node);
				rear = node;
			}

			else if (previous==null){
				node.setNext(front);
				front = node;
			}

			else{
				previous.setNext(node);
				node.setNext(current);
			}
		}
		size = size + 1;
	} 


	/*/
	 * remove element from front of queue, provided
	 * it is not an empty queue
	 */
	public T dequeue(){

		if (isEmpty()){
			throw new EmptyCollectionException("Empty queue.");
		}

		T result = front.getElement();
		front = front.getNext();

		if (front==null){
			rear = null;
		}

		size = size - 1;

		return result;
	}





	/*/
	 * returns the element from the front of the queue
	 */
	public T first(){

		return front.getElement();
	}




	/*/
	 * boolean check whether queue is empty.
	 * Returns true if empty, false if
	 * otherwise.
	 */
	public boolean isEmpty(){
		return size == 0;
	}



	/*/
	 * returns size of the queue
	 */
	public int size() {
		return size;     
	}



	/*/
	 * toString representation of the queue. Includes information
	 * about the hexagon and associated priority.
	 */	
	public String toString()
	{
		StringBuilder result = new StringBuilder("");
		PriorityNode<T> current = front;
		int count = 0;

		while (current != null)
		{
			count++;
			result.append("Hexagon: " + count + ", ");
			result.append("Priority: " + (int)current.getPriority());
			result.append("\n");

			current = current.getNext();

		}

		return result.toString();

	}




}


