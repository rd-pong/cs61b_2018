package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import hw4.puzzle.WorldState;

import java.util.Comparator;
import java.util.LinkedList;

public class Solver {
    private int moveCount;
    private SearchNode solutionSearchNode;

    /**
     * Solver(initial): Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     *
     * @param initial
     */
    public Solver(WorldState initial) {
        MinPQ<SearchNode> searchNodesQ = new MinPQ<>(new SearchNodePriority());
        SearchNode initNode = new SearchNode(initial, 0, null);
        searchNodesQ.insert(initNode);

        while (!searchNodesQ.min().isGoal()) {
            SearchNode X = searchNodesQ.min();
            // ?? what if there are several min values? (the algorithm prevent it)
            searchNodesQ.delMin();
            for (WorldState nei : X.neighbors()) {
                SearchNode neiNode = new SearchNode(nei, X);
                searchNodesQ.insert(neiNode);
            }
        }
        // todo A critical optimization:

        solutionSearchNode = searchNodesQ.min();
        moveCount = solutionSearchNode.getMovesFromInitial();
    }

    private class SearchNode {
        WorldState ws;
        int movesFromInitial;
        SearchNode prevNode;

        // constructors
        public SearchNode(WorldState newWS, SearchNode prevNode) {
            this.ws = newWS;
            if (prevNode == null) {
                this.movesFromInitial = 1;
            } else {
                this.movesFromInitial = prevNode.getMovesFromInitial() + 1;
            }
            this.prevNode = prevNode;
        }

        public SearchNode(WorldState newWS, int movesFromInitial, SearchNode newPrev) {
            this.ws = newWS;
            this.movesFromInitial = movesFromInitial;
            this.prevNode = newPrev;
        }

        // getters
        public WorldState getWs() {
            return ws;
        }

        public int getMovesFromInitial() {
            return movesFromInitial;
        }

        public SearchNode getPrevNode() {
            return prevNode;
        }

        // additional getters
        public boolean isGoal() {
            return this.ws.isGoal();
        }

        public Iterable<WorldState> neighbors() {
            return this.ws.neighbors();
        }

        public int getWSDistToGoal() {
            return this.ws.estimatedDistanceToGoal();
        }




        public Iterable<WorldState> getWSFromInitial(int moveCount) {
            SearchNode solutionNode = this;
            LinkedList<WorldState> ws = new LinkedList<>();

            for (int i = 0; i < moveCount; i++) {
                ws.add(solutionNode.getPrevNode().getWs());
                solutionNode = solutionNode.getPrevNode();
            }

            // todo reverse list

            return ws;
        }
    }

    /**
     * We can think of each search node as having a priority equal to the sum of
     * (the number of moves made to reach this world state from the initial state
     * + the WorldState’s estimatedDistanceToGoal).
     */
    class SearchNodePriority implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return o1.movesFromInitial + o1.getWSDistToGoal() -
                    (o2.movesFromInitial + o2.getWSDistToGoal());
        }
    }

    /**
     * moves():         Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     */
    public int moves() {
        return moveCount;
    }

    /**
     * solution():      Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     */
    public Iterable<WorldState> solution() {
        return solutionSearchNode.getWSFromInitial(this.moveCount);
    }
}
