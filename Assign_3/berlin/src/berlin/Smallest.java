package berlin;

/*
 * the class is adding the length between two vertex together to see the shortest length
 * the length between two different vertex can be calculate by using method in Getmatrix class
 * then adding the route together if the total length is smaller than the previous one then return the smaller one
 * otherwise save the current short one and contious
 */

public class Smallest {
	Getmatrix get = new Getmatrix();
	Shuffle shuffle = new Shuffle();
	
	public int[] path;
	public float[] route;
	int [] spath;
	
	public float[] shortest;
	public int numVertices;
	
	public float totalLength;
	public float pretotalLength;
	
	
	
	public Smallest(){
		numVertices = get.gettotal();
		shortest = new float [numVertices];
		spath = new int[numVertices];
		route = new float[numVertices];
	}
	
	/*
	 * this method is to find the shortest path
	 */
	public float findshortest(int[] path){
		route = shuffle.makeroute(path);
			addlength(route);
			if(totalLength<pretotalLength|| pretotalLength ==0){
			//shortest = route.clone();
			pretotalLength = totalLength;
			}	
			return pretotalLength;
	}

	
	/*
	 * this method is to calculate the total length of this path
	 * by adding all  the length together
	 */
	public float addlength(float[] route){
		totalLength = 0;
		//path = shuffle.path;
		for (int i = 0; i<numVertices; i++){
			totalLength = totalLength + route[i];
			
			//System.out.println("After"+totalLength);
		}
		return totalLength;
		
	}


	//public static void main(String[] args) {new Smallest();}
}
