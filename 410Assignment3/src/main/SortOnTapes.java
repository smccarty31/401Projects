package main;
import java.util.Random;

public class SortOnTapes {

    public static int TapeSize = 100;
    public static int MainMemorySize = 10;
    private static Integer[] memory = new Integer[MainMemorySize];

    public static TapeDrive<Integer> generateRandomTape(int n) {//Runtime Complexity: O(N), since it requires N writes in the tape
        TapeDrive<Integer> outputTape = new TapeDrive<Integer>(n);//Space Complexity: O(N), the tape contains an array size N of integers
        for (int i=0;i<n;i++){
        	Random generator = new Random();
        	outputTape.write(generator.nextInt());}
        return outputTape;}

    public static void sort(TapeDrive<Integer> t1) {//Runtime Complexity: O(N log N), Initial quicksorting is M log M for each chunk of the tape, where M is memory size
        TapeDrive<Integer> t2 = new TapeDrive<Integer>(TapeSize);//There are N/M chunks, thus the quick sorting takes N log M time. Each merge of the chunks takes
        TapeDrive<Integer> t3 = new TapeDrive<Integer>(TapeSize);//linear time, and there are log N/M merges, thus the merging takes N log N/M. Asymptotically, the runtime of this algorithm approaches N log N
        TapeDrive<Integer> t4 = new TapeDrive<Integer>(TapeSize);//Space Complexity: O(N), The algorithm uses four tapes of size N and memory of size M,        
        														//	 in addition to a few constant variables, for a total space allotment of 4N + M 

        //initial separation and sorting via memory

        int count = 0;
        while (count++ < Math.ceil(((double) TapeSize/MainMemorySize))-1){
        	for (int j = 0; j < MainMemorySize; j++){//fill memory with data from tape
        		memory[j] = t1.read();}
        	quicksort(memory,0,memory.length-1);//sort first chunks in memory using quicksort borrowed from the textbook
        	if (count%2 != 0){//alternate between writing from memory to t3 and t4
        		for (int k = 0; k < MainMemorySize; k++){//odd chunks written to upper tape
            		t3.write(memory[k]);}}
        	else {
        		for (int k = 0; k < MainMemorySize; k++){//even chunks written to lower tape
            		t4.write(memory[k]);}}}
        
        for (int j = 0; j <TapeSize-(count-1)*MainMemorySize; j++){//repeat for possibly non complete remainder in last chunk
        	memory[j] = t1.read();}
        	quicksort(memory,0,TapeSize-(count-1)*MainMemorySize-1);
        if ((count%2 != 0) == true){
        	for (int k = 0; k < TapeSize-(count-1)*MainMemorySize; k++){
           		t3.write(memory[k]);}}
        else {
        	for (int k = 0; k < TapeSize-(count-1)*MainMemorySize; k++){
           		t4.write(memory[k]);}}
        t1.reset();//reset all tapes to the front for merging
        t3.reset();
        t4.reset();
        
        //iterative merging
        
        int passNumber = 1;
        while (passNumber < Math.ceil(Math.log(Math.ceil(((double) TapeSize/MainMemorySize)))/Math.log(2))){//first pass already completed above into chunks of size M
        	
        	int chunkSize = (int) Math.pow(2, passNumber)*MainMemorySize;//size of merged chunk doubles with each pass
        	
        	if (passNumber%2 == 0){//even passes write to lower tapes (t3,t4)
        		memory[0] = t1.read();
    			memory[1] = t2.read();
    			int chunkNumber = 1;
        		while ((double) chunkNumber <= ((double) TapeSize/chunkSize)){
	        		if(chunkNumber%2 != 0){//odd chunks written to upper tape of pairs (t1,t3)
	        				int firstHalf = 0;
	        				int secondHalf = 0;
	        				while (firstHalf < chunkSize/2 && secondHalf < chunkSize/2){//each tape limited to contributing half of the chunk
	        					if (memory[0]>memory[1]){
	        						t3.write(memory[1]);
	        						memory[1] = t2.read();
	        						secondHalf++;}
	        					else if (memory[0]<memory[1]){
	        						t3.write(memory[0]);
	        						memory[0] = t1.read();
	        						firstHalf++;}
	        					else{
	        						t3.write(memory[0]);
	        						t3.write(memory[1]);
	        						memory[0]=t1.read();
	        						memory[1]=t2.read();
	        						firstHalf++;
	        						secondHalf++;}}
	        				while (firstHalf < chunkSize/2){
	        					t3.write(memory[0]);
	    						memory[0] = t1.read();
	    						firstHalf++;}
	        				while (secondHalf < chunkSize/2){
	        					t3.write(memory[1]);
	    						memory[1] = t2.read();
	    						secondHalf++;}}
	        		
	        		else{//even chunks written to lower of pair (t2,t4)
	        				int firstHalf = 0;
	        				int secondHalf = 0;
	        				while (firstHalf < chunkSize/2 && secondHalf < chunkSize/2){
	        					if (memory[0]>memory[1]){
	        						t4.write(memory[1]);
	        						memory[1] = t2.read();
	        						secondHalf++;}
	        					else if (memory[0]<memory[1]){
	        						t4.write(memory[0]);
	        						memory[0] = t1.read();
	        						firstHalf++;}
	        					else{
	        						t4.write(memory[0]);
	        						t4.write(memory[1]);
	        						memory[0]=t1.read();
	        						memory[1]=t2.read();
	        						firstHalf++;
	        						secondHalf++;}}
	        				while (firstHalf < chunkSize/2){
	        					t4.write(memory[0]);
	    						memory[0] = t1.read();
	    						firstHalf++;}
	        				while (secondHalf < chunkSize/2){
	        					t4.write(memory[1]);
	    						memory[1] = t2.read();
	    						secondHalf++;}
	        			}
	        		chunkNumber++;	
	        		}
        		int remainderSize = TapeSize-(chunkNumber-1)*chunkSize;//once all full chunks have been written, the remainder chunk is calculated
        		if (remainderSize == 0){}//if previous chunk completed merging, no remainder is written
        		else if (remainderSize ==1){//size one indicates remainder single chunk of one element
        			if(chunkNumber%2 != 0){
        				t3.write(memory[0]);
        				memory[0] = t1.read();}
        			else{
        				t4.write(memory[0]);
        				memory[0] = t1.read();
        			}
        		}
         		else if (remainderSize>chunkSize/2){//if remainder chunk is comprised of elements from two previous chunks it will be larger than half the current size
	        		if(chunkNumber%2 != 0){ 
	        			int remainderCount = 0;
	        			int fullCount = 0;//upper tape's chunk is always the full chunk, by the nature of the algorithm
	        			while (fullCount < chunkSize/2 && remainderCount<remainderSize-chunkSize/2){
	        				if (memory[0]>memory[1]){
	    						t3.write(memory[1]);
	    						memory[1] = t2.read();
	    						remainderCount++;}
	    					else if (memory[0]<memory[1]){
	    						t3.write(memory[0]);
	    						memory[0] = t1.read();
	    						fullCount++;}
	    					else{
	    						t3.write(memory[0]);
	    						t3.write(memory[1]);
	    						memory[0]=t1.read();
	    						memory[1]=t2.read();
	    						remainderCount++;
	    						fullCount++;}}
	    				while (fullCount < chunkSize/2){
	    					t3.write(memory[0]);
							memory[0] = t1.read();
							fullCount++;}
	    				while (remainderCount < chunkSize/2){
	    					t3.write(memory[1]);
							memory[1] = t2.read();
							remainderCount++;}}
	    		
	        		else{
	        			int remainderCount = 0;
	        			int fullCount = 0;
	        			while (fullCount < chunkSize/2 && remainderCount<remainderSize-chunkSize/2){
	        				if (memory[0]>memory[1]){
	    						t4.write(memory[1]);
	    						memory[1] = t2.read();
	    						remainderCount++;}
	    					else if (memory[0]<memory[1]){
	    						t4.write(memory[0]);
	    						memory[0] = t1.read();
	    						fullCount++;}
	    					else{
	    						t4.write(memory[0]);
	    						t4.write(memory[1]);
	    						memory[0]=t1.read();
	    						memory[1]=t2.read();
	    						remainderCount++;
	    						fullCount++;}}
	    				while (fullCount < chunkSize/2){
	    					t4.write(memory[0]);
							memory[0] = t1.read();
							fullCount++;}
	    				while (remainderCount < chunkSize/2){
	    					t4.write(memory[1]);
							memory[1] = t2.read();
							remainderCount++;}}}
           		else {//remainder size smaller than or equal to previous chunk size indicates remainder coming from only top chunk
        			int remainderCount=0;
        			if((chunkNumber%2 != 0) == true){
        			while (remainderCount<remainderSize){
        				t3.write(memory[0]);
        				memory[0] = t1.read();
        				remainderCount++;
        			}
        			}
        			else{
        				while (remainderCount<remainderSize){
            				t4.write(memory[0]);
            				memory[0] = t1.read();
            				remainderCount++;
            			}
        			}
        		}}
	        else {//repetition of the algorithm for writing to the upper pair during an odd pass
        		memory[0] = t3.read();
    			memory[1] = t4.read();
    			int chunkNumber = 1;
        		while ((double) chunkNumber <= ((double) TapeSize/chunkSize)){
	        		if((chunkNumber%2 != 0) == true){
	        				int firstHalf = 0;
	        				int secondHalf = 0;
	        				while (firstHalf < chunkSize/2 && secondHalf < chunkSize/2){
	        					if (memory[0]>memory[1]){
	        						t1.write(memory[1]);
	        						memory[1] = t4.read();
	        						secondHalf++;}
	        					else if (memory[0]<memory[1]){
	        						t1.write(memory[0]);
	        						memory[0] = t3.read();
	        						firstHalf++;}
	        					else{
	        						t1.write(memory[0]);
	        						t1.write(memory[1]);
	        						memory[0]=t3.read();
	        						memory[1]=t4.read();
	        						firstHalf++;
	        						secondHalf++;}}
	        				while (firstHalf < chunkSize/2){
	        					t1.write(memory[0]);
	    						memory[0] = t3.read();
	    						firstHalf++;}
	        				while (secondHalf < chunkSize/2){
	        					t1.write(memory[1]);
	    						memory[1] = t4.read();
	    						secondHalf++;}}
	        		else{
	        				int firstHalf = 0;
	        				int secondHalf = 0;
	        				while (firstHalf < chunkSize/2 && secondHalf < chunkSize/2){
	        					if (memory[0]>memory[1]){
	        						t2.write(memory[1]);
	        						memory[1] = t4.read();
	        						secondHalf++;}
	        					else if (memory[0]<memory[1]){
	        						t2.write(memory[0]);
	        						memory[0] = t3.read();
	        						firstHalf++;}
	        					else{
	        						t2.write(memory[0]);
	        						t2.write(memory[1]);
	        						memory[0]=t3.read();
	        						memory[1]=t4.read();
	        						firstHalf++;
	        						secondHalf++;}}
	        				while (firstHalf < chunkSize/2){
	        					t2.write(memory[0]);
	    						memory[0] = t3.read();
	    						firstHalf++;}
	        				while (secondHalf < chunkSize/2){
	        					t2.write(memory[1]);
	    						memory[1] = t4.read();
	    						secondHalf++;}
	        			}
	        		chunkNumber++;
	        		}
	        	
        		int remainderSize = TapeSize-(chunkNumber-1)*chunkSize;
        		if (remainderSize == 0){}
        		else if (remainderSize ==1){//size one indicates remainder single chunk of one elements
        			if((chunkNumber%2 != 0) == true){
        				t1.write(memory[0]);
        				memory[0] = t3.read();}
        			else{
        				t2.write(memory[0]);
        				memory[0] = t3.read();
        			}
        		}
        		else if (remainderSize>chunkSize/2){
	        		if((chunkNumber%2 != 0) == true){//even number of chunks indicates remainder is combination of two chunks
	        			int remainderCount = 0;
	        			int fullCount = 0;
	        			while (fullCount < chunkSize/2 && remainderCount<remainderSize-chunkSize/2){
	        				if (memory[0]>memory[1]){
	    						t1.write(memory[1]);
	    						memory[1] = t4.read();
	    						remainderCount++;}
	    					else if (memory[0]<memory[1]){
	    						t1.write(memory[0]);
	    						memory[0] = t3.read();
	    						fullCount++;}
	    					else{
	    						t1.write(memory[0]);
	    						t1.write(memory[1]);
	    						memory[0]=t3.read();
	    						memory[1]=t4.read();
	    						remainderCount++;
	    						fullCount++;}}
	        			while (fullCount < chunkSize/2){
	    					t1.write(memory[0]);
							memory[0] = t3.read();
							fullCount++;}
	    				while (remainderCount < chunkSize/2){
	    					t1.write(memory[1]);
							memory[1] = t4.read();
							remainderCount++;}}
	    		
	        		else{
	        			int remainderCount = 0;
	        			int fullCount = 0;
	        			while (fullCount < chunkSize/2 && remainderCount<remainderSize-chunkSize/2){
	        				if (memory[0]>memory[1]){
	    						t2.write(memory[1]);
	    						memory[1] = t4.read();
	    						remainderCount++;}
	    					else if (memory[0]<memory[1]){
	    						t2.write(memory[0]);
	    						memory[0] = t3.read();
	    						fullCount++;}
	    					else{
	    						t2.write(memory[0]);
	    						t2.write(memory[1]);
	    						memory[0]=t3.read();
	    						memory[1]=t4.read();
	    						remainderCount++;
	    						fullCount++;}}
	    				while (fullCount < chunkSize/2){
	    					t2.write(memory[0]);
							memory[0] = t3.read();
							fullCount++;}
	    				while (remainderCount < chunkSize/2){
	    					t2.write(memory[1]);
							memory[1] = t4.read();
							remainderCount++;}}}
        		else {//remainder size smaller than or equal to previous chunk size indicates remainder coming from only top chunk
        			int remainderCount=0;
	        			if((chunkNumber%2 != 0) == true){
		        			while (remainderCount<remainderSize){
		        				t1.write(memory[0]);
		        				memory[0] = t3.read();
		        				remainderCount++;}}
	        			else{
	        				while (remainderCount<remainderSize){
	            			t2.write(memory[0]);
	            			memory[0]=t3.read();
	            			remainderCount++;
	            			}}}   
        	  }
        	t1.reset();//reset the tapes for another merge and increment the number of merges
            t2.reset();
            t3.reset();
            t4.reset();
            passNumber++;}	
        if (passNumber%2==0){//for the final merge, variables are adjusted to merge the complete chunk with the remainder chunk 
        		memory[0] = t1.read();//once again, an even pass writes to the lower pair
    			memory[1] = t2.read();
    			int completeSize = (int) Math.pow(2, passNumber-1)*MainMemorySize;//size of chunks from previous pass
    			int remainderSize = TapeSize-completeSize;//difference between tape and size of complete chunk is all the remains to be merged
        		int completeCount = 0;
	        	int remainderCount = 0;
	        	
	        	while (completeCount < completeSize && remainderCount < remainderSize){
	        					if (memory[0]>memory[1]){
	        						t3.write(memory[1]);
	        						memory[1] = t2.read();
	        						remainderCount++;}
	        					else if (memory[0]<memory[1]){
	        						t3.write(memory[0]);
	        						memory[0] = t1.read();
	        						completeCount++;}
	        					else{
	        						t3.write(memory[0]);
	        						t3.write(memory[1]);
	        						memory[0]=t1.read();
	        						memory[1]=t2.read();
	        						completeCount++;
	        						remainderCount++;}}
	        	while (completeCount < completeSize){
	        					t3.write(memory[0]);
	    						memory[0] = t1.read();
	    						completeCount++;}
	        	while (remainderCount < remainderSize){
	        					t3.write(memory[1]);
	    						memory[1] = t2.read();
	    						remainderCount++;}
	        				t1.reset();
	        				t2.reset();
	        				t3.reset();
	        				t4.reset();
        					for (int i=0;i<TapeSize;i++){//sorted final tape is copied over from t3 to t1
        						t1.write(t3.read());
        				 	}}
	        		
	        else{//odd pass writes to upper pair, no need to copy over to t1
	        	memory[0] = t3.read();
    			memory[1] = t4.read();
    			int completeSize = (int) Math.pow(2, passNumber-1)*MainMemorySize;
    			int remainderSize = TapeSize-completeSize;
        		int completeCount = 0;
	        	int remainderCount = 0;
	        	
	        	while (completeCount < completeSize && remainderCount < remainderSize){
	        					if (memory[0]>memory[1]){
	        						t1.write(memory[1]);
	        						memory[1] = t4.read();
	        						remainderCount++;}
	        					else if (memory[0]<memory[1]){
	        						t1.write(memory[0]);
	        						memory[0] = t3.read();
	        						completeCount++;}
	        					else{
	        						t1.write(memory[0]);
	        						t1.write(memory[1]);
	        						memory[0]=t3.read();
	        						memory[1]=t4.read();
	        						remainderCount++;
	        						completeCount++;}}
	        				while (completeCount < completeSize){
	        					t1.write(memory[0]);
	    						memory[0] = t3.read();
	    						completeCount++;}
	        				while (remainderCount < remainderSize){
	        					t1.write(memory[1]);
	    						memory[1] = t4.read();
	    						remainderCount++;}
	        			
        				t1.reset();
        				t2.reset();
        				t3.reset();
        				t4.reset();}
    }
        		
        	    	
        
    

    private static <Anytype extends Comparable> Anytype median3(Anytype[] a, int left, int right ){//median-of-3 partioning borrowed from textbook
	    int center = (left+right)/2;
	    if(a[center].compareTo(a[left])<0){
	    swapReferences(a,left, center);}
	    if(a[right].compareTo(a[left])<0){
	    swapReferences(a,left, right);}
	    if(a[right].compareTo(a[center])<0){
	    swapReferences(a,center,right);}
	    swapReferences(a,center,right-1);
	    return a[right-1];
    }
    
    private static <AnyType> void swapReferences( AnyType[] a, int index1, int index2 ){//reference swap borrowed from textbook
        AnyType tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }
    
    private static <Anytype extends Comparable> void quicksort( Anytype[] a, int left, int right ){//quicksort borrowed from textbook
    	final int CUTOFF = 10;
        if( left + CUTOFF <= right ){
            Anytype pivot = median3( a, left, right );
            int i = left,j = right - 1;
            for( ; ; ){
                while( a[++i ].compareTo( pivot ) < 0 ) { }
                while( a[--j ].compareTo( pivot ) > 0 ) { }
                if( i < j ){
                    swapReferences( a, i, j );}
                else{
                    break;}
            }

            swapReferences( a, i, right - 1 );   // Restore pivot

            quicksort( a, left, i - 1 );    // Sort small elements
            quicksort( a, i + 1, right );   // Sort large elements
        }
        else  // Do an insertion sort on the subarray
            insertionSort( a, left, right );
    }

    private static <Anytype extends Comparable> void insertionSort( Anytype[] a, int left, int right ){//insertion sort borrowed from textbook
        for( int p = left + 1; p <= right; p++ ){
            Anytype tmp = a[ p ];
            int j;
            for( j = p; j > left && tmp.compareTo( a[ j - 1 ] ) < 0; j-- ){
                a[ j ] = a[ j - 1 ];}
            a[ j ] = tmp;
        }
    }

    public static void main(String[] args) {
    TapeDrive<Integer> drive = generateRandomTape(TapeSize);
    sort(drive);

    // This tests to see if the drive is, indeed, sorted.
    boolean sorted = true;
    int prev = Integer.MIN_VALUE;
    for (int i = 0; i < TapeSize; i++) {
        int p = prev;
        sorted &= p <= (prev = drive.read());
    }
    if (sorted){
        System.out.println("Success! The tape drive is sorted.");}
    else{
        System.out.println("Failure! The tape drive is not sorted.");}
    }
}