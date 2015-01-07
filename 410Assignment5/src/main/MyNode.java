package main;

public class MyNode implements Node {
	String nodeName;
	int nodeID;
	static int nodeValue=0;
	
	public MyNode(String name){
		nodeName=name;
		nodeID=nodeValue;
		nodeValue++;
	}
		
	public String getName() {
		return nodeName;
	}

	public int getId() {
		return nodeID;
	}

	public boolean equals(Object obj){//made obsolete by using strings as keys since use of hashCode not actually required
		if(getClass() != obj.getClass()){//check if comparison between two Nodes
			return false;
		}
		MyNode compareNode = (MyNode) obj;//if object is a Node, cast to Node
		if(compareNode.nodeName.equals(nodeName)){//compare names of nodes
			return true;
		}
		else{
			return false;
		}
	}

	public int hashCode(){//iteratively multiplies the integer values of the characters of the name of the node by prime numbers
		int hashVal=0;
		int nameLen = nodeName.length();
		for (int i=0;i<nameLen;i++){
			hashVal = ((hashVal*37)+nodeName.charAt(i))*43;
		}
		return hashVal;
		
	}
	
	
	
}
