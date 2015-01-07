package main;

public class MyHashTable<K, V> implements HashTable<K, V> {
	private int TABLE_SIZE = 1019;//table size is prime of form 4k+3 that keeps load factor under 1
	@SuppressWarnings("unchecked")
	private Object[] hashTable = new Object[TABLE_SIZE];
	
	private class hashNode<K,V>{//nodes used for chaining
		V hashValue;
		K hashKey;
		hashNode<K,V> next;				
		hashNode(K key, V val, hashNode<K,V> nextNode){
			hashKey = key;
			hashValue = val;
			next = nextNode;			
		}
	}

	public void put(K key, V value) {
		int address = key.hashCode()%TABLE_SIZE;
		if (address<0){
			address = address+TABLE_SIZE;
		}
		if (hashTable[address]==null){
			hashTable[address]=new hashNode<K,V>(key, value,null);//create a new node if nothing hashed to cell
		}
		else {
			hashNode<K,V> newNode = new hashNode<K,V>(key, value, (hashNode<K,V>) hashTable[address]);//if collision occurs, insert node at front of LL
			hashTable[address] = newNode;
		}
		
	}

	public V get(K key) {
		if(key == null){//if null value is searched, return null
			return null;
		}
		int address = key.hashCode()%TABLE_SIZE;
		if (address<0){
			address = address+TABLE_SIZE;
		}
		if (hashTable[address] == null){//if not found at hash position, return null;
			return null;
		}
		else {
			hashNode<K,V> checkNode = (hashNode<K,V>) hashTable[address];
			while (checkNode!=null){//check each node  to see if there is a match in the chain at the hash position
				if (key.equals(checkNode.hashKey)){
					return checkNode.hashValue;
				}
				else {
					checkNode = checkNode.next;
				}
			}
			return null;
		}
	}

	public V remove(K key) {//same as get, except node is removed after it is found
		if (key == null){
			return null;
		}
		int address = key.hashCode()%TABLE_SIZE;
		if (address<0){
			address = address+TABLE_SIZE;
		}
		if (hashTable[address] == null){
			return null;
		}
		else {
			hashNode<K,V> checkNode = (hashNode<K,V>) hashTable[address];
			while (checkNode!=null){
				if (checkNode.hashKey == key){
					V returnVal = checkNode.hashValue;
					if(checkNode.next == null){
						hashTable[address] = null;
					}
					else{
						checkNode.hashValue = checkNode.next.hashValue;//copy data from next node into node
						checkNode.hashKey = checkNode.next.hashKey;
						checkNode.next = checkNode.next.next;//then delete next node, thus deleting current node
					}
					return returnVal;
				}
				else {
					checkNode = checkNode.next;
				}
			}
			return null;
		}
	}


}
