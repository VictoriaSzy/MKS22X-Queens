public class QueenBoard {
  // Variables
  private int[][] board ;

  // Constructor
  public QueenBoard(int size) {
    board = new int[size][size] ;
    for (int r = 0 ; r < size ; r++) {
      for (int c = 0 ; c < size ; c++) {
        board[r][c] = 0 ;
      }
    }
  }

  //////////////////// PRIVATE METHODS ///////////////////////////////////////
  private boolean addQueen(int r, int c) {

  }
  private boolean removeQueen(int r, int c) {

  }

  /////////////////////// PUBLIC METHODS ////// /////////////////////////
  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString() {

  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve() {

  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){

  }

}