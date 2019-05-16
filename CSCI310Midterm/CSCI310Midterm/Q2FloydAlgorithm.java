import java.util.*; 
import java.lang.*; 
import java.io.*; 
import java.lang.Math;

public class Q2FloydAlgorithm{

	static int INFINITY = 1998; //Infinity variable 
	static int V = 7; //Vertices variable

	void floydWarshall(int graph[][]){ 
		int newMatrix[][] = new int[V][V]; 
		int i, j, k; 

		//initialize the matrix to "Initial State Distance Table" Matrix in Exam Answers
		for (i = 0; i < V; i++) { 
			for (j = 0; j < V; j++) { 
				newMatrix[i][j] = graph[i][j]; 
			}
		}

		//create the matrix to "3rd Iteration of Change in Distance Table by going through each other" Matrix in Exam Answers
		for (k = 0; k < V; k++){ 
			for (i = 0; i < V; i++){ 
				for (j = 0; j < V; j++){ 
					// assign the minimum value to the newMatrix 
					newMatrix[i][j] = Math.min((newMatrix[i][k] + newMatrix[k][j]), (newMatrix[i][j])); 
					printMatrix(newMatrix); 
				}
			} 
		} 
		// Print the matrix 
		printMatrix(newMatrix); 
	} 

	//Print the matrix line by line
	void printMatrix(int newMatrix[][]) { 
		
		//print my output to a file
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream("output.txt", true));
			System.setOut(out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
	
		System.out.println("Matrix that shows the shortest newMatrixances between every pair of vertices:"); 
		for (int i=0; i<V; ++i) { 
			for (int j=0; j<V; ++j) { 
				if (newMatrix[i][j]==INFINITY) 
					System.out.print("INFINITY "); 
				else
					System.out.print(newMatrix[i][j]+" "); 
			} 
			System.out.println(); 
		} 
	} 

	public static void main (String[] args) { 
		//create the graph from the exam
		int graph[][] = { 
				{0,7,INFINITY,INFINITY,INFINITY,1,INFINITY},
				{7,0,1,INFINITY,INFINITY,8,2},
				{INFINITY,1,0,10,1,INFINITY,2},
				{INFINITY,INFINITY,10,0,4,INFINITY,INFINITY},
				{INFINITY,INFINITY,1,4,0,6,INFINITY},
				{1,8,INFINITY,INFINITY,6,0,4},
				{INFINITY,2,2,INFINITY,INFINITY,4,0}
		}; 

		//call the class
		Q2FloydAlgorithm a = new Q2FloydAlgorithm(); 

		// Print the solution 
		a.floydWarshall(graph);
		
	} 
}
