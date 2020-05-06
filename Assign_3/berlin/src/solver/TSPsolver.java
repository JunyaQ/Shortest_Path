package solver;
/*
 * this class is to make the GUI include the file input, showing the current best as a result
 * showing the number of thread, input he number of searches and input the number of iterations
 * the makebutton method include a action listener that can implement the method in getreuslt class
 * with three threads 
 * also, it can draw the graph that is the shortest path in to the canvas and replace the original one
 * with using the method that connecting the points in the shortestpath 
 * and connecting the last element in the shortestpath with the first element to make a circle
 * g.draw(original x, original y, end x, end y);
 * when creating the original one , using the method of paint component in graphic
 * the method is shown in the other class called graphwindow.
 * the result canvas that using method of paint componet in graphic 
 * the private class is shown in this class called drawresult 
 */
import berlin.*;
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TSPsolver extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Getmatrix get = new Getmatrix();
	Shuffle rp = new Shuffle();
	
	public String name;
	public String file;
	
	public int threads;
	public int searches;
	public int iterations;
	public JPanel jp;
	
	private JButton button;
	
	private Box fbox;// File Path
	private Box bbox;// Current best: totalLength
	private Box tbox;//Number of Threads ?????
	private Box sbox;// Number of Searches: 30  by input
	private Box ibox;// Number of Iterations ????
	
	
	public JTextField ftextfield;
	private JTextField btextfield;
	public JTextField ttextfield;
	public JTextField stextfield;
	public JTextField itextfield;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 650;
	
	public Graphwindow canvas;
	public Drawresult canvasresult;
	
	public int[] shortestpath;
	public float shortestlength;
	
	private int numVertices = rp.numVertices;

public TSPsolver(){
	jp = new JPanel();
	setInitial();
	makefile();
	makebutton();
	makebest();
	makethreads();
	makesearches();
	setiteration();
	makecanvas();
	
}
/*
 * these method is to make the GUI form
 */
public void setInitial(){
	setSize(1200, 700);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("TSP Solver");
}
/*
 * file path when open the file, choose the input file that you want to use in this program
 */
    public void makefile(){
    fbox = Box.createHorizontalBox();
    fbox.add(new JLabel("File Path: "));
    ftextfield = new JTextField();
    ftextfield.setPreferredSize(new Dimension(200, 20));// field length, field height
    ftextfield.setBounds(10, 5, 200, 20);
    fbox.add(ftextfield);
    fbox.add(Box.createHorizontalStrut(10));// the distance between the horizontal components
    jp.add(fbox, ftextfield);// add to the J panel
    add(jp,BorderLayout.WEST);// clear the position of the J panel
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Open Data File");
    fileChooser.showOpenDialog(fileChooser);
    File file = fileChooser.getSelectedFile();
    name = file.getName();
    ftextfield.setText(name);
    get.loaddata(file.getAbsolutePath());
    }
    /*
     * make the button
     */
    public void makebutton(){
    button = new JButton("Run Search");
    jp.add(button);
    add(jp,BorderLayout.AFTER_LAST_LINE);
    button.addActionListener(new ActionListener() {
/*
 * (non-Javadoc)
 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 * acttion listener, while input the iteration, searches number and threads, then it will generate the shortest path 
 * and draw the graph and erase the original one
 */

		@Override
        public void actionPerformed(ActionEvent e) {// when the run search button is clicked
           String stringthreads = ttextfield.getText();
           String stringsearches = stextfield.getText();
           String stringiterations =itextfield.getText();
           String filename = ftextfield.getText();
           get.dataFile = filename;
  
            try {
            	threads = Integer.parseInt(stringthreads);
            	searches = Integer.parseInt(stringsearches);
            	iterations = Integer.parseInt(stringiterations);
            	setresult();

            } catch (NumberFormatException err) {
                err.printStackTrace();
            }
        }
    });
    }
    /*
     * current best that will not allow input and it will return the best result after the search button pressed
     */
    public void makebest(){
    bbox = Box.createHorizontalBox();
    bbox.add(new JLabel("Current Best: "));
    btextfield = new JTextField();
    btextfield.setEditable(false);
    btextfield.setPreferredSize(new Dimension(200, 20));
    bbox.add(btextfield);
    bbox.add(Box.createHorizontalStrut(10));
    jp.add(bbox,btextfield);
    add(jp, BorderLayout.AFTER_LINE_ENDS);
    }
    /*
     * number of threads , 3 
     */
    public void makethreads(){
    tbox = Box.createHorizontalBox();
    tbox.add(new JLabel("Number of Thread: "));
    ttextfield = new JTextField();// the number of threads
    ttextfield.setPreferredSize(new Dimension(200, 20));
    tbox.add(ttextfield);
    tbox.add(Box.createHorizontalStrut(10));
    jp.add(tbox,ttextfield);
    add(jp, BorderLayout.AFTER_LINE_ENDS);
    }
    /*
     * number of searches, the input number will be put into the getreuslt class
     */
    public void makesearches(){
    sbox = Box.createHorizontalBox();
    sbox.add(new JLabel("Number of Searches:"));
    stextfield = new JTextField();
    // should be activated while the run search button is pressed the number should be the number of searches in the smallest method
    stextfield.setPreferredSize(new Dimension(200, 20));
    sbox.add(stextfield);
    sbox.add(Box.createHorizontalStrut(10));
    jp.add(Box.createVerticalStrut(20));
    jp.add(sbox,stextfield);
    add(jp, BorderLayout.CENTER);
    }
    /*
     * number of iteration, the input number will be put into the getresult class
     */
    public void setiteration(){
    ibox = Box.createHorizontalBox();
    ibox.add(new JLabel("Number of Iterations: "));
    itextfield = new JTextField();
    itextfield.setPreferredSize(new Dimension(200, 20));
    ibox.add(itextfield);
    ibox.add(Box.createHorizontalStrut(20));
    jp.add(Box.createVerticalStrut(20));
    jp.add(ibox,itextfield);
    add(jp,BorderLayout.CENTER);
    }
    /*
     * this is the canvas that before the button is pressed, this is the canvas with only the points 
     */
    public void makecanvas(){
    	canvas = new Graphwindow();
    	canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	jp.add(canvas);
    	add(jp,BorderLayout.SOUTH);	
    }
    /*
     * get and set the searches, iterations and the threads
     */
    public void getsearches(int searches){
    	this.searches = searches;
    }
    public int setsearches(){
    	return searches;
    }
    public void getiterations(int iterations){
    	this.iterations = iterations;
    }
    public int setiterations(){
    	return iterations;
    }
    public void getthread(int threads){
    	this.threads = threads;
    }
    public int setthreads(){
    	return threads;
    }

   /*
    * this is the result canvas that after the button is pressed, it will graph the shortest path  
    */
    public void setresult(){
    	canvasresult = new Drawresult();
    	canvasresult.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	canvas.setVisible(false);
    	canvasresult.setVisible(true);
    	jp.add(canvasresult);
    	add(jp,BorderLayout.SOUTH);	
    }
    /*
     * in this class is shown how to draw the canvas after implement the find shortest path
     * the canvas will draw the shortest path 
     * because of the space is limit so i draw the length of all dividing by 2
     * so that it can be drawn all in this canvas
     * so that the length is dividing by 2 but the proportion is correct
     */
    	private class Drawresult extends JPanel{
		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
   	    		super.paintComponent(g);
   	    		setBackground(Color.WHITE);
   	    		g.setColor(Color.BLACK);
   	    		g.drawRect(0, 0, 999, 649);
   	    		g.setColor(Color.GREEN);
   	    		for (int i = 0; i< numVertices; i++){
   	    			int tempx = Math.round((get.xnumber[i]/2));
   	    			int tempy = Math.round(get.ynumber[i]/2);
   					g.drawLine(tempx-2, tempy, tempx+2, tempy);
   					g.drawLine(tempx, tempy-2, tempx, tempy+2);
   				}
   	    		g.setColor(Color.RED);
   	    		Getresult getresult = new Getresult();
   	    		//getresult.shortestresult(searches, iterations);// this method should have thread into it too
   	    		getresult.getsearches(searches);
   	    		getresult.getiterations(iterations);
   	    		getresult.getthreads(threads);// make the numbers into the getresult class for further implementation
   	    		getresult.run();// to check if it equals to 3 threads or 4 threads
   	    		shortestlength = getresult.shortest;
   	    	    shortestpath = getresult.memorizepath;
   	    		btextfield.setText(String.valueOf(shortestlength));
   	    		
            	int templast = 0;
   	    		for(int i =0; i<shortestpath.length-1; i++){
   		    			int temp1 = shortestpath[i];
   		    			int temp2 = shortestpath[i+1];
   		    			int tempx1 = (int) get.xnumber[temp1]/2;
   		    			int tempy1 = (int) get.ynumber[temp1]/2;
   		    			int tempx2 = (int) get.xnumber[temp2]/2;
   		    			int tempy2 = (int) get.ynumber[temp2]/2;
   		    		g.drawLine(tempx1, tempy1, tempx2, tempy2);
   		    		templast = shortestpath[i+1];
   		    		}
   	    		int tempx1 = (int)get.xnumber[0]/2;
   	    		int tempy1 = (int)get.ynumber[0]/2;
   	    		int tempx2 = (int)get.xnumber[templast]/2;
   	    		int tempy2 = (int)get.ynumber[templast]/2;
   	    		g.drawLine(tempx1, tempy1, tempx2, tempy2);
    }
    	}
    	
    
 //main method
public static void main(String[] args) {
	TSPsolver run = new TSPsolver();
    run.setVisible(true);
}
}

