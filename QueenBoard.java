public class QueenBoard {
  // Variables
  private int[][] board ;

  // Constructor: fills 2D array with 0's to start
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
    if (board[r][c] != 0) return false ; // the tile is threatened so we can't put down a queen there
    else {
      board[r][c] = -1 ;
      for (int row = 0 ; row < board.length ; row++) {
        for (int col = c + 1 ; col < board.length ; col++) {
          if (row == r || Math.abs(r - row) == Math.abs(c - col) ) {
            board[row][col] += 1 ;
          }
        }
      }
      return true ;
    }
  }
  private boolean removeQueen(int r, int c) {
    if (board[r][c] != -1) return false ; // the specified tile is not a Queen
    else {
      board[r][c] = 0 ;
      for (int row = 0 ; row < board.length ; row++) {
        for (int col = c + 1 ; col < board.length ; col++) {
          if (row == r || Math.abs(r - row) == Math.abs(c - col)) board[row][col] -= 1 ;
        }
      }
      return true ;
    }
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
    String result = "" ;
    for (int r = 0 ; r < board.length ; r++) {
      for (int c = 0 ; c < board.length ; c++) {
        if (board[r][c] == -1) {
          // we have found a queen!
          result += "Q" ;
        }
        else {
          result += board[r][c] + " " ;
        }
      }
      result += "\n" ;
    }
    return result ;
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
