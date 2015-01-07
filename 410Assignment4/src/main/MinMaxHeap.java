package main;

public class MinMaxHeap<AnyType extends Comparable<? super AnyType>> {
	
	private int currentSize;
	private AnyType[] arr;
	private int height;
	
	public static void main(String args[]){
		MinMaxHeap testHeap = new MinMaxHeap(100);
		for(int i=1;i<101;i++){
			testHeap.insert(i);
		}
		for(int i=1;i<101;i++){
			System.out.println(testHeap.deleteMax());
		}
		for(int i=1;i<101;i++){
			testHeap.insert(i);
		}
		for(int i=1;i<101;i++){
			System.out.println(testHeap.deleteMin());
		}
	}
	
	public MinMaxHeap(int capacity){//Constructor
		arr = (AnyType[]) new Comparable[capacity+1];
		currentSize = 0;
		height = 0;
		}
	
	public boolean isFull(){return currentSize == arr.length-1;}//first space in the array is left empty, reducing capacity of the array by one}
	
	public boolean isEmpty(){return currentSize == 0;}
	
	// COMPLETE THE FOLLOWING METHODS
	public void insert(AnyType x){//
		currentSize++;
		height=(int)Math.ceil((Math.log((double)currentSize+1)/Math.log((double)2)));
		arr[currentSize]=x;
		if(height==2){//at height one no comparisons need to be made
			if(arr[currentSize].compareTo(arr[1])<0){//if element on the max level is less than the min, max element the min
				swap(currentSize,1);
			}
		}
		else if(height>2){//at height greater than two, comparison with two levels is possible, requiring the pecolate method
		percolateUp(currentSize);//each level
		}
	}
	public AnyType min(){//return root of heap
		return arr[1];
	}//PRE: The heap is not empty
	
	public AnyType max(){
		if(arr[2].compareTo(arr[3])>0){//if left child is max, return left
			return arr[2];
		}
		else{//if the two are equal or right is greater, return right
			return arr[3];
		}
	}//PRE: The heap is not empty
	
	public AnyType deleteMin(){
		AnyType min = arr[1];
		if(height==1){//deleteMin removes the last element and leaves an emtpy heap
			arr[1]=null;
			currentSize=0;
			height=0;
		}
		else if(height==2){
			if(currentSize==2){//delete min leaves the max element as the root when there are only 2 elements
				arr[1]=arr[2];
				arr[2]=null;
				currentSize=1;
				height=1;
			}
			else{//when there are three elements, the remaining two must be compared to see which will become the min
				if(arr[2].compareTo(arr[3])>0){
					arr[1]=arr[3];
					arr[3]=null;
					currentSize=2;
					height=2;
				}
				else{
					arr[1]=arr[2];
					arr[2]=arr[3];
					arr[3]=null;
					currentSize=2;
					height=2;
				}
			}
		}
		else if(height>2){//at height greater than two, comparison with two levels is possible, requiring the pecolate method
			arr[1]=arr[currentSize];//insert the last element at the top and percolate down
			percolateDown(1);
			arr[currentSize]=null;//set last element to null and decrease size of heap
			currentSize--;
			height=(int)Math.ceil((Math.log((double)currentSize+1)/Math.log((double)2)));
		}
		return min;
		
	}//PRE: The heap is not empty
	public AnyType deleteMax(){
		AnyType max= null;
		if(height==1){//deleteMax removes the last element and leaves an emtpy heap
			max = arr[1];
			arr[1]=null;
			currentSize=0;
			height=0;
		}
		else if(height==2){
			if(currentSize==2){//delete leaves the min element as the root when there are only 2 elements
				max = arr[2];
				arr[2]=null;
				currentSize=1;
				height=1;
			}
			else{//when there are three elements, the remaining two must be compared to see which will be deleted
				if(arr[2].compareTo(arr[3])>0){
					max = arr[2];
					arr[2]=arr[3];
					arr[3]=null;
					currentSize=2;
					height=2;
				}
				else{
					max = arr[3];
					arr[3]=null;
					currentSize=2;
					height=2;
				}
			}
		}
		else if(height>2){//at height greater than two, comparison with two levels is possible, requiring the pecolate method
			if(arr[2].compareTo(arr[3])>0){//if the left child is greater, it is deleted
				max = arr[2];
				arr[2]=arr[currentSize];//insert the last element at the top and percolate down
				percolateDown(2);
				arr[currentSize]=null;//set last element to null and decrease size of heap
				currentSize--;
				height=(int)Math.ceil((Math.log((double)currentSize+1)/Math.log((double)2)));
			}
			else{//if right child is greater, it is deleted
				max = arr[3];
				arr[3]=arr[currentSize];//insert the last element at the top and percolate down
				percolateDown(3);
				arr[currentSize]=null;//set last element to null and decrease size of heap
				currentSize--;
				height=(int)Math.ceil((Math.log((double)currentSize+1)/Math.log((double)2)));
			}
			
		}
		return max;
		
		
	}	
	
	private boolean hasLeftChild(int index){
		if (currentSize>=(index*2)){
			return true;
		}
		else{
			return false;
		}
	}	
	private boolean hasRightChild(int index){
		if (currentSize>=(index*2)+1){
			return true;
		}
		else{
			return false;
		}
	}	
	private int parentIndex(int index){
		if (index%2==0){//even position, left subtree
			return index/2;
		}
		else{//odd position, right subtree
			return (index-1)/2;
		}
	}
	private int smallestGrandchildIndex(int index){
		int sgc = index*4;//set smallest equal to first grandchild
		for(int i=1;i<4;i++){//check next three gc's
			if((index*4)+i>currentSize){}//if subtree incomplete skip empty grandchildren
			else if(arr[sgc].compareTo(arr[(index*4)+i])>0){//if current smallest is larger than next, update smallest to that gc
				sgc = (index*4)+i;
			}
		}
		return sgc;
	}
	private boolean hasGrandchildren(int index){
		if(currentSize>=(4*index)){
			return true;
		}
		else{
			return false;
		}
	}
	private int largestGrandchildIndex(int index){
		int lgc = index*4;//set largest equal to first gc
		for(int i=1;i<4;i++){//check others
			if((index*4)+i>currentSize){}//if subtree incomplete skip empty grandchildren
			else if(arr[lgc].compareTo(arr[(index*4)+i])<0){//if current largest is smaller than next, update largest to that gc
				lgc = (index*4)+i;
			}
		}
		return lgc;
	}	
	private void percolateUp(int index){
		
		int level = (int)Math.ceil((Math.log((double)index+1)/Math.log((double)2)));//level of element is ceiling  of lg(position of element+1)
		int position = index;
		
		
		
		if(level%2==0){//current height of heap is even, inserted element begins on a max level
			
			if(arr[position].compareTo(arr[(parentIndex(position))])<0){//if element on max level less than parent, swap to min level and percolate up min levels
				swap(position,parentIndex(position));
				position = parentIndex(position);
				level=level-1;//level becomes odd
				
				while(level>2){//continue percolating up to top of min levels
					
					if(arr[position].compareTo(arr[parentIndex(parentIndex(position))])<0){//if element is less than its grandparent, swap the two elements
						swap(position,parentIndex(parentIndex(position)));
						position = parentIndex(parentIndex(position));
						level = level-2;}
						
					else{//stop percolating when element is greater than gparent
						break;}
				}	
			}
				
			else{//if element on max level greater than parent, stay on max level and percolate up max levels
				
				while(level>2){//continue percolating to top of max levels
				
					if(arr[position].compareTo(arr[parentIndex(parentIndex(position))])>0){//if element is greater than its grandparent, swap the two elements
						swap(position,parentIndex(parentIndex(position)));
						position = parentIndex(parentIndex(position));
						level = level-2;}
					
					else{//stop percolating when element is greater than gparent
						break;}
					
				}
			}
		}
		
		else{//current height of heap is odd, inserted element begins on min level
			
			if(arr[position].compareTo(arr[(parentIndex(position))])>0){//if element on min level greater than parent, swap to max level and percolate up max levels
				swap(position,parentIndex(position));
				position = parentIndex(position);
				level=level-1;//level becomes even
				
				while(level>2){//continue percolating until at top of min or max levels
					if(arr[position].compareTo(arr[parentIndex(parentIndex(position))])>0){//if element is greater than its grandparent, swap the two elements
						swap(position,parentIndex(parentIndex(position)));
						position = parentIndex(parentIndex(position));
						level = level-2;}
						
					else{//stop percolating when element is less than gparent
						break;
					}
				}
			}
			
			else{
				
				while(level>2){//continue percolating until at top of min or max levels
					if(arr[position].compareTo(arr[parentIndex(parentIndex(position))])<0){//if element is less than its grandparent, swap the two elements
						swap(position,parentIndex(parentIndex(position)));
						position = parentIndex(parentIndex(position));
						level = level-2;
					}
					else{//stop percolating when element is greater than gparent
						break;
					}
					
				}
			}
		}
	}	
	private void percolateDown(int index){
		
		int level = (int)Math.ceil((Math.log((double)index+1)/Math.log((double)2)));
		
		if (level%2==0){//even level indicates max level
			percolateDownMax(index);
		}
		else{//odd level indicates min level
			percolateDownMin(index);
		}		
	}	
	private void percolateDownMin(int index){
		if(hasLeftChild(index)){//arr[i] has children
			int smallest = 0;
			if(hasGrandchildren(index)){//arr[i] has grandchildren
				AnyType smallestGC = arr[smallestGrandchildIndex(index)];
				if(smallestGC.compareTo(arr[index*2])<0){//smallest grandchild less than left child
					if(smallestGC.compareTo(arr[(index*2)+1])<0){//smallest grandchild less than right child
						smallest = smallestGrandchildIndex(index);
					}
					else{//smallest grandchild less than left child but greater than right
						smallest = (index*2)+1;
					}
				}
				else{//left child smaller than grandchildren
					if(arr[index*2].compareTo(arr[(index*2)+1])>0){//left child less than grandchildren but greater than right child
						smallest = (index*2)+1;
					}
					else{//left child less than both right child and grand children
						smallest = index*2;
					}
				}					
			}
			else{//arr[i] has no grandchildren, only need to compare children
				if(hasRightChild(index)){
					if(arr[index*2].compareTo(arr[(index*2)+1])>0){//left greater than right
						smallest = (index*2)+1;
					}
					else{//right greater than left
						smallest = index*2;
					}
				}
				else{//arr[i] only has left child
					smallest=index*2;
				}
			}
			if (smallest>(index*4)-1 && smallest<(index*4)+4){//arr[smallest] is a grandchild of arr[index]
				if(arr[smallest].compareTo(arr[index])<0){
					swap(smallest,index);
					if(arr[smallest].compareTo(arr[smallest/2])>0){
						swap(smallest,smallest/2);
					}
					percolateDownMin(smallest);
				}
			}
			else{//arr[smallest] is a child of arr[index]
				if(arr[smallest].compareTo(arr[index])<0){
					swap(smallest,index);
				}
			}
		}
	}
	private void percolateDownMax(int index){
		if(hasLeftChild(index)){//arr[i] has children
			int largest = 0;
			if(hasGrandchildren(index)){//arr[i] has grandchildren
				AnyType largestGC = arr[largestGrandchildIndex(index)];
				if(largestGC.compareTo(arr[index*2])>0){//largest grandchild greater than left child
					if(largestGC.compareTo(arr[(index*2)+1])>0){//largest grandchild greater than right child
						largest = largestGrandchildIndex(index);
					}
					else{//largest grandchild greater than left child but less than right
						largest = (index*2)+1;
					}
				}
				else{//left child smaller than grandchildren
					if(arr[index*2].compareTo(arr[(index*2)+1])<0){//left child greater than grandchildren but less than right child
						largest = (index*2)+1;
					}
					else{//left child greater than both right child and grand children
						largest = index*2;
					}
				}					
			}
			else{//arr[i] has no grandchildren, only need to compare children
				if(hasRightChild(index)){
					if(arr[index*2].compareTo(arr[(index*2)+1])<0){//left less than right
						largest = (index*2)+1;
					}
					else{//right less than left
						largest = index*2;
					}
				}
				else{//arr[i] only has left child
					largest=index*2;
				}
			}
			if (largest>(index*4)-1 && largest<(index*4)+4){//arr[largest] is a grandchild of arr[index]
				if(arr[largest].compareTo(arr[index])>0){
					swap(largest,index);
					if(arr[largest].compareTo(arr[largest/2])<0){
						swap(largest,largest/2);
					}
					percolateDownMax(largest);
				}
			}
			else{//arr[largest] is a child of arr[index]
				if(arr[largest].compareTo(arr[index])>0){
					swap(largest,index);
				}
			}
		}
	}
	private void swap(int x1, int x2){
		AnyType tmp = arr[x1];
		arr[x1] = arr[x2];
		arr[x2] = tmp;
	}
	
	
	
	

	}
