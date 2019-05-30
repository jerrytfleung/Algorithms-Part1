import java.util.ArrayList;
import java.util.List;


public class Board {

    private final int[][] board;
    private int hamming = -1;
    private int manhattan = -1;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        board = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }

    // board dimension n
    public int dimension() {
        return board.length;
    }

    // number of blocks out of place
    public int hamming() {
        if (hamming == -1) {
            int numberOfBlockOutOfPlace = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] != 0) {
                        if (i * board.length + j + 1 != board[i][j]) {
                            numberOfBlockOutOfPlace++;
                        }
                    }
                }
            }
            hamming = numberOfBlockOutOfPlace;
        }
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        if (manhattan == -1) {
            int sumOfDistance = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] != 0) {
                        int x = (board[i][j] - 1) / board.length;
                        int y = (board[i][j] - 1) % board.length;
                        sumOfDistance += Math.abs(x - i) + Math.abs(y - j);
                    }
                }
            }
            manhattan = sumOfDistance;
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        Board twinBoard = new Board(board);
        if (board[0][0] != 0 && board[0][1] != 0) {
            twinBoard.swap(0, 0, 0, 1);
        } else {
            twinBoard.swap(1, 0, 1, 1);
        }
        return twinBoard;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        
        if (y == null) {
            return false;
        }
        
        if (y.getClass() != this.getClass()){
            return false;
        }
        
        Board that = (Board) y;
        if (that.board.length != board.length) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != that.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void swap(int x1, int y1, int x2, int y2) {
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbours = new ArrayList<>();
        int xZero = -1;
        int yZero = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    xZero = i;
                    yZero = j;
                }
            }
        }
        // up
        if (yZero - 1 >= 0) {
            Board b = new Board(board);
            b.swap(xZero, yZero, xZero, yZero - 1);
            neighbours.add(b);
        }
        // down
        if (yZero + 1 < board.length) {
            Board b = new Board(board);
            b.swap(xZero, yZero, xZero, yZero + 1);
            neighbours.add(b);
        }
        // left
        if (xZero - 1 >= 0) {
            Board b = new Board(board);
            b.swap(xZero, yZero, xZero - 1, yZero);
            neighbours.add(b);
        }
        // right
        if (xZero + 1 < board.length) {
            Board b = new Board(board);
            b.swap(xZero, yZero, xZero + 1, yZero);
            neighbours.add(b);
        }
        return neighbours;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(board.length + "\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                sb.append(" " + board[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
