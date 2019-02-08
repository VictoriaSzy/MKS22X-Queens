public class QueenBoard {
  // Variables
  private int[][] board ;

  public static void main(String[] args) {
    QueenBoard q = new QueenBoard(3) ;
    System.out.println("Here is the board:\n" + q.toString()) ;
    System.out.println("Let's try to add a queen to 0,0: " + q.addQueen(0,0)) ; //should be true
    System.out.println("We have added a queen to the upper left tile!\n" + q.toString()) ;
    System.out.println("Let's try to add a queen to 1,1: " + q.addQueen(1,1)) ; //should be false
    System.out.println("We cannot add a queen to 1,1! \n" + q.toString()) ;
    System.out.println("Let's try to add a queen to 2,1: " + q.addQueen(2,1)) ; //should be true
    System.out.println("We can add a queen to 2,1!\n" + q.toString()) ;
    System.out.println("Let's try to remove a queen at 2,2: " + q.removeQueen(2,2)) ; //should be false bc there is no queen to remove
    System.out.println("We cannot remove a queen at 2,2 because there is none!\n" + q.toString()) ;
    System.out.println("Let's try to remove a queen at 1,0: " + q.removeQueen(1,0)) ;
    System.out.println("We tried to remove the \"queen\" at 1,0, which doesn't exist!\n" + q.toString()) ;
  }

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
          result += "Q " ;
        }
        else {
          result += board[r][c] + " " ;
        }
      }
      result += "\n" ;
    }
    return result ;
  }
  private boolean singleQueens(int[][] board) {
    int total = 0 ;
    for (int r = 0 ; r < board.length ; r++) {
      for (int c = 0 ; c < board.length ; c++) {
        if (board[r][c] == -1) total++ ;
      }
    }
    return total == board.length ;
  }
  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve() {
    if (singleQueens(board)) {
      return true ;
    }
    else {
      // Make board have zeros only
      for (int r = 0 ; r < board.length ; r++) {
        for (int c = 0 ; c < board.length ; c++) {
          board[r][c] = 0 ;
        }
      }
      return false ;
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  //public int countSolutions(){

  //}

}
