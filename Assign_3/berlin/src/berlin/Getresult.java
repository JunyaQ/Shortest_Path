package berlin;


/*
 * using thread that doing reaches 3 or 4 times based on the input, 
 * then doing iterations for the input times and doing the searches for the input time
 * the method getshortest will return the shortest length to the GUI
 * the method memorizpath will return the shortest path from this class to the GUI
 * 
 */
public class Getresult implements Runnable  {
	Getmatrix get = new Getmatrix();
	Shuffle shuffle = new Shuffle();
	Smallest small = new Smallest();
	Rearrange re = new Rearrange();

	public int[] path = re.path; 
	public int[] newpath;
	public int[] memorizepath;
	public float[] route = shuffle.route;
	public float pretotalLength;
	public float shortest;
	
	public int threads;
	public int searches;
	public int iterations;
	
	public Thread t1;
	public Thread t2;
	public Thread t3;
	public Thread t4;

	
	public Getresult(){
		
	}
	
	
	public void getthreads(int threads){
		this.threads = threads;
	}
	public void getsearches (int searches){
		this.searches = searches;
	}
	public void getiterations (int iterations){
		this.iterations = iterations;
	}
	@Override
	public void run(){
	
		path = shuffle.initial();
		pretotalLength = 0;
		if(threads ==3){
			Thread t1 = new Thread(new Getresult());
			t1.start();
		for(int i =0; i<searches; i++){
		path = shuffle.shuffle();
		for(int j =0; j<iterations; j++){
			newpath = re.getarrangepath();
			shortest = small.findshortest(newpath);
		if(shortest < pretotalLength|| pretotalLength ==0){
			memorizepath = newpath;
			pretotalLength = shortest;
		}
		else{
			j= j+1;
		}
		}
		}
		if(t1.isAlive()==false){
			Thread t2 = new Thread(new Getresult());
			t2.start();
			for(int i =0; i<searches; i++){
				path = shuffle.shuffle();
				for(int j =0; j<iterations; j++){
					newpath = re.getarrangepath();
					shortest = small.findshortest(newpath);
				if(shortest < pretotalLength|| pretotalLength ==0){
					memorizepath = newpath;
					pretotalLength = shortest;
				}
				else{
					j= j+1;
				}
				}
				}
		if(t2.isAlive()==false){
			Thread t3 = new Thread(new Getresult());
			t3.start();
			for(int i =0; i<searches; i++){
				path = shuffle.shuffle();
				for(int j =0; j<iterations; j++){
					newpath = re.getarrangepath();
					shortest = small.findshortest(newpath);
				if(shortest < pretotalLength|| pretotalLength ==0){
					memorizepath = newpath;
					pretotalLength = shortest;
				}
				else{
					j= j+1;
				}
				}
				}
		}

			
			
		}
		}
		if(threads ==4){
			Thread t1 = new Thread(new Getresult());
			t1.start();
		for(int i =0; i<searches; i++){
		path = shuffle.shuffle();
		for(int j =0; j<iterations; j++){
			newpath = re.getarrangepath();
			shortest = small.findshortest(newpath);
		if(shortest < pretotalLength|| pretotalLength ==0){
			memorizepath = newpath;
			pretotalLength = shortest;
		}
		else{
			j= j+1;
		}
		}
		}
		if(t1.isAlive()==false){
			Thread t2 = new Thread (new Getresult());
			t2.start();
			for(int i =0; i<searches; i++){
				path = shuffle.shuffle();
				for(int j =0; j<iterations; j++){
					newpath = re.getarrangepath();
					shortest = small.findshortest(newpath);
				if(shortest < pretotalLength|| pretotalLength ==0){
					memorizepath = newpath;
					pretotalLength = shortest;
				}
				else{
					j= j+1;
				}
				}
			}
		if(t2.isAlive()==false){
			Thread t3 = new Thread(new Getresult());
			t3.start();
			for(int i =0; i<searches; i++){
				path = shuffle.shuffle();
				for(int j =0; j<iterations; j++){
					newpath = re.getarrangepath();
					shortest = small.findshortest(newpath);
				if(shortest < pretotalLength|| pretotalLength ==0){
					memorizepath = newpath;
					pretotalLength = shortest;
				}
				else{
					j= j+1;
				}
				}
				}
		
		if(t3.isAlive()==false){
			Thread t4 = new Thread(new Getresult());
			t4.start();
			for(int i =0; i<searches; i++){
				path = shuffle.shuffle();
				for(int j =0; j<iterations; j++){
					newpath = re.getarrangepath();
					shortest = small.findshortest(newpath);
				if(shortest < pretotalLength|| pretotalLength ==0){
					memorizepath = newpath;
					pretotalLength = shortest;
				}
				else{
					j= j+1;
				}
				}
				}
		}	
		}
		}
		}
	}
	
	/*
	 * wait to return the shortest path length to the GUI
	 */
	public float getshortest(){
		run();
		return shortest;
	}

		/*
		 * enter the memorizepath into the method in shuffle that can get the x, y coordinate for each one
		 * for drawing path to the last
		 */
		public void setshortestpath(int[] memorizepath){
			this.memorizepath = memorizepath;
		}
		
		public int[] getshortestpath(){
			return memorizepath;
		}


		
	
		
}
