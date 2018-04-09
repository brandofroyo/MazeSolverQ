import java.io.*;

/**
 * 
 * @author Brandon Mathew 
 * The MazeSolverQ intelligently traverses through a maze and uses a 
 * priority queue that evaluates tiles, and ultimately paths,
 * by priority to efficiently find the exit. This program implements
 * the A* algorithm.
 */


public class MazeSolverQ {



	/*/
	 * the args parameter allows the program to take a maze file.
	 * exceptions are thrown/caught to prevent the maze solver
	 * program from crashing
	 */

	public static void main (String[] args) throws FileNotFoundException,
	IOException, EmptyCollectionException {
		try{
			if(args.length<1)

			{
				throw new IllegalArgumentException(
						"Please provide a Maze file as a "
								+ "command line argument.");
			}

		}

		catch(InvalidNeighbourIndexException e){
			System.out.println("Invalid Neighbour Index Exception!");
		}

		catch(UnknownMazeCharacterException e){
			System.out.println("Unknown Maze Character Exception!");
		}


		catch(EmptyCollectionException e){
			System.out.println("Empty Collection Exception!");
		}



		/*/
		 * a maze object is created and passed as argument 0.
		 * a hexagon object is created and references the
		 * hexagon tile at the start of the maze
		 */

		String mazeFile = args[0];		
		Maze mazeObj = new Maze(mazeFile);
		Hexagon hexObj = mazeObj.getStart();
		hexObj.setSteps(0);



		/*/
		 * a LinkedPriorityQueue object is created
		 */
		LinkedPriorityQueue<Hexagon> hexQueue = new LinkedPriorityQueue<Hexagon>();

		hexQueue.enqueue(hexObj, hexObj.getSteps() + 
				hexObj.distanceToEnd(mazeObj));
		boolean flag = false;
		int count = 0;
		hexObj.setStarted();
		Hexagon thisTile = null;




		/*/
		 * while the queue is not empty...
		 */
		while(!hexQueue.isEmpty() && !flag){
			count++;
			thisTile=hexQueue.dequeue();
			thisTile.setDequeued();


			/*/
			 * if the end has been reached, mark the flag
			 * as true and repaint the maze and process the
			 * hexagon.
			 */
			if(thisTile.equals(mazeObj.getEnd()))
			{
				flag = true;
				mazeObj.repaint();
				thisTile.setFinished();
			}



			/*
			 * otherwise, scan through the 6 possible neighbors
			 * or a hexagon. If the hexagon's neighbour is not
			 * null, is not a wall, has not been enqueued, and
			 * has not been dequeued...enqueue the neighbour
			 * hexagon with its priority calculated as the sum
			 * of number of steps plus distance to end.
			 */
			else
			{
				for(int i = 0; i < 6; i++)
				{
					Hexagon tempHex = thisTile.getNeighbour(i);
					if(tempHex!=null && !tempHex.isWall() 
							&& !tempHex.isEnqueued() 
							&& !tempHex.isDequeued())
					{
						tempHex.setSteps(thisTile.getSteps() + 1);
						hexQueue.enqueue(tempHex, tempHex.getSteps() + tempHex.distanceToEnd(mazeObj)); 
						tempHex.setEnqueued();
					}
				}	
			}



			/*/
			 * update tile colour and repaint the maze
			 */
			thisTile.setDequeued();  
			mazeObj.repaint();
		}




		/*/
		 * finally, our print statements...
		 */
		if(thisTile.isEnd()) 
		{
			System.out.println("We have found the end!");
		}
		else
		{
			System.out.println("We did not find the end...");
		}

		System.out.println("Also, it took " + (int)thisTile.getSteps() + " steps to reach the end.");

		System.out.println("There are " + hexQueue.size() + " tiles left on queue.");

		System.out.println("The program took " + count + " steps overall.");

		System.out.println("\n" + "Additional information: " + "\n" + hexQueue.toString());




	}


}
