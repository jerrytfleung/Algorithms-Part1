import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private final List<Board> solutions;
    private boolean solvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        List<Board> s1 = new ArrayList<>();
        Board twin = initial.twin();
        MinPQ<SearchNode> minPQ1 = new MinPQ<>();
        MinPQ<SearchNode> minPQ2 = new MinPQ<>();
        minPQ1.insert(new SearchNode(initial, 0, null));
        minPQ2.insert(new SearchNode(twin, 0, null));
        while (!minPQ1.isEmpty() && !minPQ2.isEmpty()) {
            SearchNode n1 = minPQ1.delMin();
            if (n1.currentBoard.isGoal()) {
                solvable = true;
                // create solution
                while (n1 != null) {
                    s1.add(0, n1.currentBoard);
                    n1 = n1.prev;
                }
                break;
            } else {
                Iterable<Board> neighbour1 = n1.currentBoard.neighbors();
                for (Board neighbour : neighbour1) {
                    if (n1.prev == null || !neighbour.equals(n1.prev.currentBoard)) {
                        minPQ1.insert(new SearchNode(neighbour, n1.move + 1, n1));
                    }
                }
            }

            SearchNode n2 = minPQ2.delMin();
            if (n2.currentBoard.isGoal()) {
                solvable = false;
                break;
            } else {
                Iterable<Board> neighbour2 = n2.currentBoard.neighbors();
                for (Board neighbour : neighbour2) {
                    if (n2.prev == null || !neighbour.equals(n2.prev.currentBoard)) {
                        minPQ2.insert(new SearchNode(neighbour, n2.move + 1, n2));
                    }
                }
            }
        }
        if (solvable) {
            solutions = s1;
        } else {
            solutions = null;
        }
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable()) {
            return solutions.size() - 1;
        } else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable()) {
            return solutions;
        } else {
            return null;
        }
    }

    private class SearchNode implements Comparable<SearchNode> {

        Board currentBoard;
        int move;
        SearchNode prev;

        SearchNode(Board current, int move, SearchNode prev) {
            this.currentBoard = current;
            this.move = move;
            this.prev = prev;
        }

        @Override
        public int compareTo(SearchNode that) {
            return this.currentBoard.manhattan() + this.move - (that.currentBoard.manhattan() + that.move);
        }
    }
}