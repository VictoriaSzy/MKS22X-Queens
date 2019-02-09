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
    /*QueenBoard a = new QueenBoard(4) ;
    if (a.solve()) System.out.println("The board (a) is solvable! Good!") ;
    else {
      System.out.println("Work on solve method! It's not working correctly right now...") ;
    }
    QueenBoard b = new QueenBoard(2) ;
    if (b.solve()) System.out.println("The board (b) is solvable! WHAT HAPPPENEDDDDDDDDDDDDDDDD!") ;
    else {
      System.out.println("Good job! (b) was not solvable!") ;
    }*/
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
    if (board[r][c] != 0) return false ; // the tile is threatened OR has a queen already so we can't put down a queen
    else {
      int l = board.length ;
      board[r][c] = -1 ;
      // add one horizontally & vertically
      for (int row = 0 ; row < l ; row++) {
        for (int col = 0 ; col < l ; col++) {
          if (row == r && col != c) board[row][col] += 1 ;
          if (col == c && row != r) board[row][col] += 1 ;
        }
      }
      // add one diagonally right up
      if (r > 0 && c < l-1) {
        int row = r - 1 ;
        int col = c + 1 ;
        while (row >= 0 && col < l) {
          board[row][col] += 1 ;
          col++ ;
          row-- ;
        }
      }
      // add one diagonally right down
      if (r < l-1 && c < l-1) {
        int row = r + 1 ;
        int col = c + 1 ;
        while (row < l && col < l) {
          board[row][col] += 1 ;
          row++ ;
          col++ ;
        }
      }
      return true ;
    }
  }
  private boolean removeQueen(int r, int c) {
    if (board[r][c] != -1) return false ; // the specified tile is not a Queen
    else {
      int l = board.length ;
      board[r][c] = 0 ;
      // remove one horizontally
      for (int col = 0 ; col < l ; col++) {
        if (col != c) board[r][col]-- ;
      }
      // remove one vertically
      for (int row = 0 ; row < l ; row++) {
        if (row != r) board[row][c]-- ;
      }
      for (int row = 0 ; row < l ; row++) {
        for (int col = 0 ; col < l ; col++) {
          if (r - row == c - col && row != r && col != c) board[row][col]-- ;
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
          //result += "board[r][c]" + " " ;
          result += "_ " ;
        }
      }
      result += "\n" ;
    }
    return result ;
  }
  // Private helper method to calculate # of queens on the board
  private int numberQueens(int[][] board) {
    int total = 0 ;
    for (int r = 0 ; r < board.length ; r++) {
      for (int c = 0 ; c < board.length ; c++) {
        if (board[r][c] == -1) total++ ;
      }
    }
    return total ;
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
    return solveH(0) ;
  }
  // Helper method for solve() that uses recursion
  public boolean solveH(int c) {
    int l = board.length ;
    if (c >= l) {
      // we're at the end of the board
      System.out.println(this) ;
      return numberQueens(board) == l ;
    }
    else {
      for (int r = 0 ; r < l ; r++) {
        System.out.println("Here is how the board looks:\n"+this.toString()) ;
        if (addQueen(r,c)) {
          System.out.println("Queen was added:\n"+this.toString()) ;
          if ( solveH(c+1) ) return true ; // it can be solved
          else {
            removeQueen(r,c) ; // otherwise remove the queen
            System.out.println("The queen was removed:\n"+this.toString()) ;
          }
        }
      }
      return false ;
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions() {
    for (int r = 0 ; r < board.length ; r++) {
      for (int c = 0 ; c < board.length ; c++) {
        if (board[r][c] != 0) throw new IllegalStateException("Why is the board not starting with 0? IllegalStateException is thrown!") ;
      }
    }
    return 1 ;
  }

}
