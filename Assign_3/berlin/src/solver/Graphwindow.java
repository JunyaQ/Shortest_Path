package solver;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import berlin.*;
/*
 * this class is to initialize the graphic panel 
 * and initialize the points with xy coordinates on the canvas
 * inorder to make all the points fit the canvas, so i divided the position by 2
 * that the number can fit the canvas and the proportion is correct
 */
public class Graphwindow extends JPanel{
	Getmatrix get = new Getmatrix();
	public int numVertices = get.totalvertex;

	
    	public void paintComponent(Graphics g){
    		super.paintComponent(g);
    		setBackground(Color.WHITE);
    		g.setColor(Color.BLACK);
    		g.drawRect(0, 0, 999, 649);
    		g.setColor(Color.RED);
    		//g.drawLine(0, 0, 40, 500);
    		for (int i = 0; i< numVertices; i++){
    			int tempx = Math.round((get.xnumber[i]/2));
    			int tempy = Math.round(get.ynumber[i]/2);
				g.drawLine(tempx-2, tempy, tempx+2, tempy);
				g.drawLine(tempx, tempy-2, tempx, tempy+2);
			}
    	}
    		
	}


