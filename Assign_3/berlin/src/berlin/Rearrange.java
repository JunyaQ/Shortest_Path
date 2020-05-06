package berlin;


public class Rearrange {
	Shuffle shuffle = new Shuffle();
	Smallest small = new Smallest();
	Getmatrix get = new Getmatrix();
	
	public int numVertices = get.gettotal();

	public int[] randomlist = new int[3];
	public int[] path = shuffle.path;
	public int[] newpath;
	public float length;
	public float[] route = shuffle.route;
	public float[] shortest = small.shortest;
	
	public Rearrange(){

	}
	/*
	 * this method is to generate 3numbers to represent the position of the number in the list that is waiting for the
	 * further interchanges
	 */
	public int[] getrandomlist(){
		int random1 = (int) (Math.random()*numVertices);
		int random2 = (int) (Math.random()*numVertices);
		int random3 = (int) (Math.random()*numVertices);
		if(random1 == random2||random1 == random3||random2==random3){
		getrandomlist();
		}
		else{
			randomlist[0] = random1;
			randomlist[1] = random2;
			randomlist[2] = random3;
		}
		return randomlist;
	}
	
	/*
	 * holding the first element the same and exchange the 2nd and the 3rd position (132)
	 */
	public int[] arrange1(int[]randomlist){
		//int random1 = randomlist[0];
		int random2 = randomlist[1];
		int random3 = randomlist[2];
		int temp = path[random2];
		path[random2] = path[random3];
		path[random3] = temp;
		return path;
	}
	/*
	 * switch the 1st element with the 2nd element (213) 
	 */
	public int[] arrange2(int[] randomlist){
		int random1 = randomlist[0];
		int random2 = randomlist[1];
		int temp = path[random1];
		path[random1] = path[random2];
		path[random2] = temp;
		return path;
	}
	/*
	 * switching the 3rd element from the origianl list and position it as the 1st element(231)
	 */
	public int[] arrange3(int[] randomlist){
		int random1 = randomlist[0];
		int random2 = randomlist[1];
		int random3 = randomlist[2];
		int temp = path[random1];
		path[random1] = path[random2];
		path[random2] = path[random3];
		path[random3] = temp;
		return path;
		
	}
	/*
	 * switching the 2nd element from the original list and position it as the 1st element(312)
	 */
	public int[] arrange4(int[] randomlist){
		int random1 = randomlist[0];
		int random2 = randomlist[1];
		int random3 = randomlist[2];
		int temp = path[random1];
		path[random1] = path[random3];
		path[random3] = path[random2];
		path[random2] = temp;
		return path;
	}
	/*
	 * the reverse order (321)
	 */
	public int[] arrange5(int[] randomlist){
		int random1 = randomlist[0];
		int random2 = randomlist[1];
		int random3 = randomlist[2];
		int temp = path[random1];
		path[random1] = path[random3];
		path[random2] = path[random2];
		path[random3] = temp;
		return path;
	}
	/*
	 * to random choose a way to rearrange the numbers by random generate 0-5 to choose which way to rearrange the numebrs
	 * new path is the path that get rearranged
	 */
	public int[] getarrangepath(){
		randomlist = getrandomlist();
		int selector = (int)(Math.random()*5);
		//System.out.println(selector);
		if(selector == 0){
			path = arrange1(randomlist);
		}
		if (selector ==1){
			path = arrange2(randomlist);
		}
		if(selector ==2){
			path = arrange3(randomlist);
		}
		if(selector ==3){
			path = arrange4(randomlist);
		}
		if(selector ==4){
			path = arrange5(randomlist);
		}
		return path;	
	}
	//public static void main(String[] args) {new Rearrange();}
}
