package hw4.puzzle;

import edu.princeton.cs.algs4.StdOut;

public class WordPuzzleSolver {
    /***********************************************************************
     * Test routine for your Solver class. Uncomment and run to test
     * your basic functionality.
     **********************************************************************/
    public static void main(String[] args) {
        String start = "stories";//3
        String goal = "shore";

//        String start = "face"; //3
//        String goal = "rates";

//        String start = "mines"; //4
//        String goal = "loved";

        Word startState = new Word(start, goal);
        Solver solver = new Solver(startState);

        StdOut.println("Minimum number of moves = " + solver.moves());
        for (WorldState ws : solver.solution()) {
            StdOut.println(ws);
        }
    }
}
