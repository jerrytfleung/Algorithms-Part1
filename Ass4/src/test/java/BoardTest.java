import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private Board puzzle = null;

    @Before
    public void setUp() throws Exception {
        int[][] board = new int[3][3];
        board[0][0] = 0;
        board[0][1] = 1;
        board[0][2] = 3;
        board[1][0] = 4;
        board[1][1] = 2;
        board[1][2] = 5;
        board[2][0] = 7;
        board[2][1] = 8;
        board[2][2] = 6;
        puzzle = new Board(board);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void dimension() {
        assertEquals(3, puzzle.dimension());
    }

    @Test
    public void hamming() {
        assertEquals(4, puzzle.hamming());
    }

    @Test
    public void manhattan() {
        assertEquals(4, puzzle.manhattan());
    }

    @Test
    public void isGoal() {
        assertFalse(puzzle.isGoal());

        int[][] thatBoard = new int[3][3];
        thatBoard[0][0] = 1;
        thatBoard[0][1] = 2;
        thatBoard[0][2] = 3;
        thatBoard[1][0] = 4;
        thatBoard[1][1] = 5;
        thatBoard[1][2] = 6;
        thatBoard[2][0] = 7;
        thatBoard[2][1] = 8;
        thatBoard[2][2] = 0;
        Board thatPuzzle = new Board(thatBoard);
        assertTrue(thatPuzzle.isGoal());
    }

    @Test
    public void twin() {
        Board anotherBoard = puzzle.twin();
        assertEquals(3, anotherBoard.dimension());
    }

    @Test
    public void equalsTest() {
        int[][] thatBoard = new int[3][3];
        thatBoard[0][0] = 0;
        thatBoard[0][1] = 1;
        thatBoard[0][2] = 3;
        thatBoard[1][0] = 4;
        thatBoard[1][1] = 2;
        thatBoard[1][2] = 5;
        thatBoard[2][0] = 7;
        thatBoard[2][1] = 8;
        thatBoard[2][2] = 6;
        Board thatPuzzle = new Board(thatBoard);
        assertTrue(puzzle.equals(thatPuzzle));

        thatBoard[0][0] = 1;
        thatBoard[0][1] = 0;
        thatBoard[0][2] = 3;
        thatBoard[1][0] = 4;
        thatBoard[1][1] = 2;
        thatBoard[1][2] = 5;
        thatBoard[2][0] = 7;
        thatBoard[2][1] = 8;
        thatBoard[2][2] = 6;
        Board anotherPuzzle = new Board(thatBoard);
        assertFalse(puzzle.equals(anotherPuzzle));
    }

    @Test
    public void neighbors() {
        int count = 0;
        for (Board neighbor : puzzle.neighbors()) {
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    public void toStringTest() {
        String expected = "3\n 0 1 3\n 4 2 5\n 7 8 6\n";
        assertEquals(expected, puzzle.toString());
    }
}