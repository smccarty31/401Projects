package main;
/**
 * Factory used for creating hash tables
 */
public final class HashTableFactory {

    public static <K, V> HashTable<K, V> create() {
    	MyHashTable<K,V> createdTable = new MyHashTable<K,V>();
    	return createdTable;    	
    }

}