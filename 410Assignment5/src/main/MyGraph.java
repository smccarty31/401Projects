package main;
public class MyGraph extends Graph {
	final int TABLE_SIZE = 1019;
	MyHashTable<String,MyNode> nameTable = new MyHashTable<String,MyNode>();//hash table used for name searching
	MyNode[] IDTable = new MyNode[TABLE_SIZE];//array used for ID searching
	Edge[] adjacencyList = new Edge[TABLE_SIZE];//array of linked lists for adjacency list
	int graphNodeCount = 0;
	
	private class Edge {
		MyNode inNode;
		Edge nextEdge;
		private Edge(MyNode node, Edge edge){
			inNode=node;
			nextEdge=edge;
		}
	}
	
	private class Stack<T> {//code from assignment1 used for stack in sort and isAcyclic method
		private int MAX_SIZE;
		private int currentSize;
		StackNode<T> front;
		
		private Stack (int capacity){
			MAX_SIZE = capacity;
			currentSize = 0;
		}
		
		private class StackNode<T> {
			T data;
			StackNode<T> next;
			
			private StackNode (T theData, StackNode<T> theNext){
				data = theData;
				next = theNext;}

			private StackNode (T theData){
				data = theData;
				next = null;
			}
			
			
		}

		
		private boolean isEmpty(){
			return currentSize == 0;
		}
		
		private boolean isFull(){
			return currentSize == MAX_SIZE;		
		}
		private T peek(){
			if (!(isEmpty())){
				return front.data;//show top value without removing node
			}
			else{return null;}
		}
		private T pop(){
			if (!(isEmpty())){
			StackNode<T> tmpNode = front;
			front = front.next;//assign top of stack to next node
			tmpNode.next = null;//remove top node from linked list
			currentSize--;
			return tmpNode.data;//return data from old top node
			}
			else{return null;}
		}
		private void push(T element){
			if (isEmpty()){
				front = new StackNode<T>(element);//if stack is empty, create node that doesn't point to another node
			}
			else{
				StackNode<T> newTop = new StackNode<T>(element, front);//otherwise, create node that points to top node and assign it as the new top of the stack
				front = newTop;	
			}
			currentSize++;
		}
		
	}

	public void addNode(Node node) {
		nameTable.put(node.getName(), (MyNode) node);
		IDTable[node.getId()]=(MyNode) node;
		graphNodeCount++;
	}

	public void addEdge(Node node1, Node node2) {
		if ((IDTable[node1.getId()]!=null) && (IDTable[node2.getId()]!=null)){//checks that both nodes are in the graph
			if (adjacencyList[node1.getId()] == null){//if no adjacencies in the list, create node for destination node in list
				adjacencyList[node1.getId()] = new Edge((MyNode) node2,null);
				
			}
			else {//if node1 has any adjacencies, adds the edge to the front of the list 
				adjacencyList[node1.getId()] = new Edge((MyNode) node2,adjacencyList[node1.getId()]); 		
			}
		}
	}
	
	public Node lookupNode(int id) {
		return IDTable[id];
	}

	public Node lookupNode(String name) {
		return nameTable.get(name);
	}

	public boolean isAcyclic() {
		int[] inDegreeList = new int[graphNodeCount];
		for(int i=0;i<graphNodeCount;i++){//calculates indegrees of all nodes in the graph
			Edge adjacentEdge = adjacencyList[i];
			while(adjacentEdge!=null){
				inDegreeList[adjacentEdge.inNode.getId()]++;
				adjacentEdge=adjacentEdge.nextEdge;
			}
		}
		Stack<MyNode> S = new Stack<MyNode>(graphNodeCount);//using Stack code from assignment 1 to improve efficiency of isAcyclic and sort
		for(int i=0;i<graphNodeCount;i++){//puts all the zero indegrees into a stack
			if (inDegreeList[i]==0){
				S.push(IDTable[i]);
			}
		}
		for(int i=0;i<graphNodeCount;i++){
			MyNode zeroNode = S.pop();//assigns node to "remove" by decrementing from adjacent nodes
			if(zeroNode==null){//if no nodes of zero inDegree can be found at any point of, a cycle has been reached, thus the method returns false
				return false;
			}
			else{
				Edge adjacentEdge = adjacencyList[zeroNode.getId()];
				while(adjacentEdge!=null){
					inDegreeList[adjacentEdge.inNode.getId()]--;
					if(inDegreeList[adjacentEdge.inNode.getId()]==0){//if a node reaches zero inDegree during decrement, it is pushed onto the stack to increase efficiency
						S.push(adjacentEdge.inNode);
					}
					adjacentEdge=adjacentEdge.nextEdge;
				}
			}
		}
		return true;
	}

	@Override
	public int[] sort() {//similar code to isAcyclic except that instead of checking for cycles, it saves the index/ID of the zero nodes to an integer array
		int[] sortedNodes = new int[graphNodeCount];
		int[] inDegreeList = new int[graphNodeCount];
		for(int i=0;i<graphNodeCount;i++){//calculates indegrees of all nodes in the graph
			Edge adjacentEdge = adjacencyList[i];
			while(adjacentEdge!=null){
				inDegreeList[adjacentEdge.inNode.getId()]++;
				adjacentEdge=adjacentEdge.nextEdge;
			}
		}
		Stack<MyNode> S = new Stack<MyNode>(graphNodeCount);//using Stack code from assignment 1 to improve efficiency of isAcyclic and sort
		for(int i=0;i<graphNodeCount;i++){
			if (inDegreeList[i]==0){
				S.push(IDTable[i]);
			}
		}
		for(int i=0;i<graphNodeCount;i++){
			MyNode zeroNode = S.pop();
			Edge adjacentEdge = adjacencyList[zeroNode.getId()];
			sortedNodes[i]=zeroNode.getId();//node indices/IDs assigned in sorted order to be used for printing T-sort
				while(adjacentEdge!=null){
					inDegreeList[adjacentEdge.inNode.getId()]--;
					if(inDegreeList[adjacentEdge.inNode.getId()]==0){
						S.push(adjacentEdge.inNode);
					}
					adjacentEdge=adjacentEdge.nextEdge;
				}
			}
		return sortedNodes;
		}
	}
	

	
	

