package berlin;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import solver.*;
/*
 * in this class is to read the file that include the total number of the vertex (totalvertex)
 * the x coordinates are stored in the array float[] xnumber,
 * the y coordinates are stored in the array float[] ynumber,
 * the adjacent is can be calculated by the method set adjacent that can be use in other class in order to calculate
 * the length between two points
 */

import solver.TSPsolver;
public class Getmatrix {

	public  String dataFile = "berlin52.txt";
	private StringTokenizer token;
	private String line;
	public  static int totalvertex;
	public float[] xnumber;
	public float[] ynumber; 

	public float adjacent;//length
	public Getmatrix(){
		int temptotal = gettotal();
	
	xnumber = new float[temptotal];
	ynumber = new float[temptotal];

		loaddata(dataFile);	
	}
	public void loaddata(String dataFile){
		load(dataFile);
	}
	public int gettotal(){
		Scanner input = null;
		try{
			input = new Scanner (new FileInputStream(dataFile));
			line = input.nextLine();
			token = new StringTokenizer(line);
			totalvertex = Integer.parseInt((token.nextToken()));// the total number of the vertex
		}
		catch (FileNotFoundException e) {
	        System.out.println("File not found " + e.getMessage());
	    } finally {
	        if (input != null) {
	            input.close();
	        }
	    }
		return totalvertex;
	}
	/*
	 * the load method is to load all information from the text file
	 * it is to read the total number of vertex and x, y coordinator for each vertex
	 */
	private void load(String dataFile){
		Scanner input = null;
		try{
			input = new Scanner (new FileInputStream(dataFile));
			line = input.nextLine();
			token = new StringTokenizer(line);
			totalvertex = Integer.parseInt((token.nextToken()));// the total number of the vertex
		
			while (input.hasNextLine()){
				for (int i =0; i< totalvertex; i++){
				line = input.nextLine();
				token = new StringTokenizer(line);
				int vertexnum = Integer.parseInt(token.nextToken());
				xnumber[i] = Float.parseFloat(token.nextToken());// the x coordinate for each vertex
				ynumber[i] = Float.parseFloat(token.nextToken());// the y coordinate for each vertex	
				}
			}
		}
			 catch (FileNotFoundException e) {
			        System.out.println("File not found " + e.getMessage());
			    } finally {
			        if (input != null) {
			            input.close();
			        }
			    }
	}
			/*
			 * this part is to calculate the length between two adjacent point;
			 * for example when i = 1, j = 0 that means the distance between the first point and the second point
			 * in real life the distance from point 0 to point 1 should be the same as the distance from point 1 to point 0
			 * therefore, in this adjacent [i][j] is same as adjacent [j][i]
			 * by this i mean, for example adjacent[1][2]should be equal to adjacent[2][1] and they have the same meaning 
			 */

	public float setadjacent(float x1, float x2, float y1, float y2){		
				adjacent = (float) (Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))));
			return adjacent;
			}
		

/*
 * this rexposition can be used in other class that return the x piston when giving the vertex number in the path
 * this reyposition can be used in other class that return the y posion when giving the vertex number in the path
 */
	public float rexposition(int vertex){
		float x = xnumber[vertex];
		return x;
		
	}
	public float reyposition(int vertex){
		float y = ynumber[vertex];
		return y;
	}
	}

