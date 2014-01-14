package main.Structures;
import java.util.ArrayList;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.Visible;
@Tags("Table")
@StructurePattern("No Pattern")
@PropertyNames({"keys","values"})


public class ProjectTable<T> implements Table<T> {
	
	private ArrayList<String> keys = new ArrayList<String>();
	private ArrayList<T> values = new ArrayList<T>();

	public void put(String key, T val) {
		if (key==null || val==null){}//does nothing if either input is null
		else if (keys==null){//if keys is empty, adds both to their respective arrays
			keys.add(key.toLowerCase());
			values.add(val);
		}
		else if (keys.contains(key.toLowerCase())){//if key is used, deletes val at key's index, then inserts new val at that index
			values.remove(keys.indexOf(key.toLowerCase()));//vals shift to the left 1 element
			values.add(keys.indexOf(key.toLowerCase()), val);}//shift back to the right 1 element
		else {
			keys.add(key.toLowerCase());//if key is new, adds both to the end of their respective arrays to maintain matching indices
			values.add(val);
		}
		

	}
	public T get(String key) {
		if (key == null){
			return null;
		}
		if (!(keys.contains(key.toLowerCase()))){//if key does not match any of the keys, return null
			return null;
		}
		else {
			return (T) values.get(keys.indexOf(key.toLowerCase()));//return the value at the index of the matching key
		}
	}
@Visible(false)
	public ArrayList<String> getKeys(){
		return keys;
	}
@Visible(false)
	public ArrayList<T> getValues(){
		return values;
	}
}
