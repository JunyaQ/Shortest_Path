package berlin;


/*
 * this class is to make a array list that contain the path that
 * go through from a random point to the whole points and back to the original
 * it will be result as pathList
 */
public class Shuffle {
	Getmatrix get = new Getmatrix();
	
	public int randomstart;
	
	public float[] route;
	public int[] path;// prevent go back to the same point
	
	public int numVertices;
	public float adjacent;
	
	public int xposition;
	public int yposition;
	
	public float[] xlist;
	public float[] ylist;
	

	public Shuffle(){
		numVertices = get.gettotal();
		adjacent = get.adjacent;
		//System.out.println(adjacent[1][0]);
		route = new float[numVertices];
		path = new int [numVertices];
		initial();
		
	}
	
	/*
	 * to initiate the list, assume the path is go through in numeric order
	 */
	public int[] initial(){
		for (int i =0; i<numVertices; i++){
			path[i] = i;
		}
		//System.out.println(startlist);
		return path;
	}
	
	
	/*
	 * the fisher yates shuffle method
	 */
	public int[] shuffle(){
		path = initial();
		for(int i=numVertices-1; i>0; i--){
			int random = (int) (Math.random()*(i+1));
			int tempstart = path[random];
			path[random] = path[i];
			path[i] = tempstart;
		}
		//System.out.println(Arrays.toString(path));
		return path;
	}
	/*
	 * this method returns the xlist that contain all the x numbers in the path order
	 */
	public float[] xlist(int[] path){
		this.path = path;
		for(int i =0; i<path.length-1; i++){
			float x = get.rexposition(i);
			xlist[i] = x;
		}
		return xlist;
	}
	/*
	 * this method returns the ylsit tha contains all the y numbers in the path order
	 */
	public float[] ylist(int[] path){
		this.path = path;
		for(int i=0; i<path.length-1; i++){
			float y = get.reyposition(i);
			ylist[i] = y;
		}
		return ylist;
	}
	/*
	 * this is based on the startlist number that we already generated
	 * the start position is the ith number of the list, then the end position will be the i+1 number of the list
	 * the last path is from the final number to the the first position of the starlist 
	 * thus make the path a circle that start and end at the same position
	 */
	public float[] makeroute(int[] path){
		int temp = 0;
		for(int i =0; i<path.length-1; i++){
			float x1 = get.rexposition(path[i]);
			float x2 = get.rexposition(path[i+1]);
			float y1 = get.reyposition(path[i]);
			float y2 = get.reyposition(path[i+1]);
			
			route[i] = get.setadjacent(x1, x2, y1, y2);
			temp = path[i+1];
		}
		float x1 =get.rexposition(path[0]);
		float x2 = get.rexposition(temp);
		float y1 = get.reyposition(path[0]);
		float y2 = get.reyposition(temp);
		route[path.length-1] =get.setadjacent(x1, x2, y1, y2);

			return route; 
	}
	
	/*
	 * print the length of the route		
	 */
	public void print (){
		for (int i =0; i< numVertices; i++){
			System.out.println(route[i]);
		}
		
	}
		

}
