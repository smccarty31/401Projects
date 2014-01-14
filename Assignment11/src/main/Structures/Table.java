package main.Structures;

import java.util.ArrayList;

public interface Table<T> {
		public void put(String key, T val);
		public T get(String key);
		public ArrayList<String> getKeys();
		public ArrayList<T> getValues();
}
