public class QueenBoard {
  // Variables
  private int[][] board ;

  public static void main(String[] args) {
    QueenBoard q = new QueenBoard(4) ;
    System.out.println("Here is the board:\n" + q.toString()) ;
    System.out.println("Let's try to add a queen to 1,0: " + q.addQueen(1,0)) ; //should be true
    System.out.println("We have added a queen to 1,0!\n" + q.toString()) ;
    System.out.println("Let's try to add a queen to 1,1: " + q.addQueen(1,1)) ; //should be false
    System.out.println("We cannot add a queen to 1,1! \n" + q.toString()) ;
    System.out.println("Let's try to add a queen to 3,1: " + q.addQueen(3,1)) ; //should be true
    System.out.println("We can add a queen to 3,1!\n" + q.toString()) ;
    System.out.println("Let's try to add a queen to 0,2:" + q.addQueen(0,2)) ; // should be true
    System.out.println("We can add a queen to 0,2!\n" + q.toString()) ;
    System.out.println("Let's try to add a queen to 2,3:" + q.addQueen(2,3)) ; // should be true
    System.out.println("We can add a queen to 2,3!\n" + q.toString()) ;
    if (q.solve()) System.out.println("The board is solvable! Good!") ;
    else {
      System.out.println("Work on solve method!") ;
    }
    /*System.out.println("Let's try to remove a queen at 2,2: " + q.removeQueen(2,2)) ; //should be true
    System.out.println("We can remove a queen at 2,2!\n" + q.toString()) ;
    System.out.println("Let's try to remove a queen at 1,0: " + q.removeQueen(1,0)) ;
    System.out.println("We tried to remove the \"queen\" at 1,0, which doesn't exist!\n" + q.toString()) ;*/
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
  // Private helper method for solve()
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
  *@throws IllegalStateException when the board starts with any non-zero value on the board
  */
  public boolean solve() {
    for (int r = 0 ; r < board.length ; r++) {
      for (int c = 0 ; c < board.length ; c++) {
        if (board[r][c] != 0) throw new IllegalStateException("Why is the board not starting with 0? IllegalStateException is thrown!") ;
      }
    }
    if (singleQueens(board)) {
      return true ;
    }
    else {
      return solveH(0,0,board.length,0) ;
    }
  }
  // Second helper method for solve that uses recursion
  public boolean solveH(int r, int c, int target, int current) {
    int l = board.length ;
    if (r < l && c < l && board[r][c] == 0) {
      // the tile can get a queen
      addQueen(r, c) ;
      current++ ;
      // then we move on to the next column
      return solveH(0, c + 1, target, current) ;
    }
    // if we can't put a queen at [r][c]:
    else {
      if (r == l - 1) {
        // @ bottom row
        if ( board[l - 1][0] == -1 ) {
          return false ;
        }
        c-- ;
        // checking rest of the rows in this col
        for (int i = 0 ; i < l ; i++) {
          if (board[i][c] == -1) {
            r = i ;
          }
        }
        removeQueen(r, c) ;
        current-- ;
        if (r == l - 1) {
          c-- ;
          for (int i = 0 ; i < l ; i++) {
            if (board[i][c] == -1) {
              r = i ;
            }
          }
          removeQueen(r, c);
          current-- ;
        }
      }
      return solveH(r + 1, c, target, current) ;
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  //public int countSolutions(){

  //}

}
