package main;
/**
 * Some examples of tests. You'll want to write more tests.
 */
public class ExampleTests {

    private static interface Animal {
        public String speak();
    }
    private static class Dog implements Animal {
        @Override
        public String speak() {
            return "woof";
        }
    }

    private static class Cat implements Animal {
        @Override
        public String speak() {
            return "meow";
        }
    }

    public static void main(String[] args) {

        // Hash table
        HashTable<String, Animal> animals = HashTableFactory.create();
        animals.put("dog", new Dog());
        animals.put("cat", new Cat());
        System.out.println("Expecting 'woof': " + animals.get("dog").speak());
        System.out.println("Expecting 'meow': " + animals.get("cat").speak());
        
        for(int i=0;i<20;i++){
        	if (i%2==0){
        	animals.put(Integer.toString(i), new Dog());}
        	else{
        	animals.put(Integer.toString(i), new Cat());}
        }
        
        for(int i=0;i<20;i++){
        	if (i%2==0){
        	System.out.println("Expecting 'woof': " + animals.get(Integer.toString(i)).speak());}
        	else{
            System.out.println("Expecting 'meow': " + animals.get(Integer.toString(i)).speak());}
        }

        // Graph building
        Graph graph = GraphFactory.create();
        Node blue = NodeFactory.create("blue");
        Node red = NodeFactory.create("red");
        Node orange = NodeFactory.create("orange");
        Node green = NodeFactory.create("green");
        Node yellow = NodeFactory.create("yellow");
        Node purple = NodeFactory.create("purple");
        
        graph.addNodes(blue,red,orange,green,yellow,purple);
        graph.addEdge(yellow, red);
        graph.addEdge(yellow, green);
        graph.addEdge(yellow, purple);
        graph.addEdge(blue, green);
        graph.addEdge(orange, green);
        graph.addEdge(orange, purple);
        graph.addEdge(purple, green);
        System.out.println("Expecting unique ids from nodes blue, red, orange, green, yellow, purple:");
        System.out.println("ID for blue: " + graph.lookupNode("blue").getId());
        System.out.println("ID for red: " + graph.lookupNode("red").getId());
        System.out.println("ID for orange: " + graph.lookupNode("orange").getId());
        System.out.println("ID for green: " + graph.lookupNode("green").getId());
        System.out.println("ID for yellow: " + graph.lookupNode("yellow").getId());
        System.out.println("ID for purple: " + graph.lookupNode("purple").getId());
        
       
        // Graph analysis
        System.out.println("Expecting an acyclic graph with sorted output:");
        graph.analyze();
    }

}