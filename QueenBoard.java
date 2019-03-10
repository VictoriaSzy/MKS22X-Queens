public class QueenBoard {
  // Variable
  private int[][] board ;

  public static void main(String[] args) {
    /*System.out.println("The size is: " + 15) ;
    QueenBoard a = new QueenBoard(15) ;
    System.out.println("We are finding all possible solutions now!") ;
    System.out.println(a.countSolutions()) ;
    System.out.println("We are going to run the test!") ;
    for (int i = 0 ; i < 6 ; i++) {
      System.out.println("The current index is " + i) ;
      runTest(i) ;
    }
    // I used this visualizer to also help me debug: http://pythontutor.com/visualize.html#mode=edit
    System.out.println("*********************************************************************************************************************") ;
    System.out.println("*********************************************************************************************************************") ;
    System.out.println("*********************************************************************************************************************") ;
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
    System.out.println("*********************REMOVING******************************************************************") ;
    System.out.println("Let's try to remove a queen at 2,3: " + q.removeQueen(2,3)) ; //should be true
    System.out.println("We can remove a queen at 2,3!\n" + q.toString()) ;
    System.out.println("Let's try to remove a queen at 1,1: " + q.removeQueen(1,1)) ;
    System.out.println("We tried to remove the \"queen\" at 1,1, which doesn't exist!\n" + q.toString()) ;
    System.out.println("*****************SOLVING**********************************************************") ;
    QueenBoard a = new QueenBoard(4) ;
    if (a.solve()) System.out.println("The board (a) is solvable! Good!") ;
    else {
      System.out.println("Work on solve method! It's not working correctly right now...") ;
    }
    QueenBoard b = new QueenBoard(2) ;
    if (b.solve()) System.out.println("The board (b) is solvable! WHAT HAPPPENEDDDDDDDDDDDDDDDD!") ;
    else {
      System.out.println("Good job! (b) was not solvable!") ;
    }
    System.out.println("*****************COUNTING SOLUTIONS**********************************************************") ;
    QueenBoard c = new QueenBoard(2) ;
    System.out.println("Let's create a QueenBoard of size 2 and run countSolutions. It should be 0 because there are no solutions!") ;
    //System.out.println("Here is the empty board:\n" + c.toString()) ;
    if (c.countSolutions() == 0) {
      System.out.println("Good job! countSolutions works on a board of size 2!") ;
    }
    else {
      System.out.println("There is something wrong with countSolutions because it didn't return 0 for a board of size 2!") ;
    }
    System.out.println("*********************************************************************************************************************") ;
    QueenBoard d = new QueenBoard(3) ;
    System.out.println("Let's create a QueenBoard of size 3 and run countSolutions. It should be 0 because there are no solutions!") ;
    //System.out.println("Here is the empty board:\n" + d.toString()) ;
    if (d.countSolutions() == 0) {
      System.out.println("Good job! countSolutions works on a board of size 3!") ;
    }
    else {
      System.out.println("There is something wrong with countSolutions because it didn't return 0 for a board of size 3!") ;
    }
    System.out.println("*********************************************************************************************************************") ;
    QueenBoard e = new QueenBoard(4) ;
    System.out.println("Let's create a QueenBoard of size 4 and run countSolutions. It should be 2!") ;
    //System.out.println("Here is the empty board:\n" + e.toString()) ;
    if (e.countSolutions() == 2) {
      System.out.println("Good job! countSolutions works on a board of size 4!") ;
    }
    else {
      System.out.println("There is something wrong with countSolutions because it didn't return 2 for a board of size 4!") ;
    }*/
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
    if (board[r][c] > 0) return false ; // the tile is threatened OR has a queen already so we can't put down a queen
    else {
      int l = board.length ;
      board[r][c] = -1 ;
      for (int i = 1 ; c + i < l ; i++) {
        board[r][c + i] += 1 ;
        // if it doesn't go past the last row
        if (r + i < l) board[r + i][c + i] += 1 ;
        // if it doesn't go less than 0
        if (r - i > -1) board[r - i][c + i] += 1 ;
      }
      return true ;
    }
  }
  private boolean removeQueen(int r, int c) {
    if (board[r][c] != -1) return false ; // the specified tile is not a Queen
    else {
      int l = board.length ;
      board[r][c]++ ;
      for (int i = 1 ; c + i < l ; i++) {
        board[r][c + i]-= 1 ;
        // if it doesn't go past the last row
        if (r + i < l) board[r + i][c + i]-= 1 ;
        // if it doesn't go less than 0
        if (r - i > -1) board[r - i][c + i]-= 1 ;
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
    //System.out.println("Column is: " + c) ;
    if (c >= l) {
      // we're at the end of the board
      //System.out.println("We're at the end!:\n"+this.toString()) ;
      return true ;
    }
    else {
      for (int r = 0 ; r < l ; r++) {
        //System.out.println("Trying to add to row " + r + "\nHere is how the board looks:\n"+this.toString()) ;
        if (addQueen(r,c)) {
          //System.out.println("Queen was added:\n"+this.toString()) ;
          if ( solveH(c+1) ) return true ; // it can be solved
          else {
            removeQueen(r,c) ; // otherwise remove the queen
            //System.out.println("The queen was removed:\n"+this.toString()) ;
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
    //System.out.println("*************************Welcome to countSolutions()!*********************") ;
    for (int r = 0 ; r < board.length ; r++) {
      for (int c = 0 ; c < board.length ; c++) {
        if (board[r][c] != 0) throw new IllegalStateException("Why is the board not starting with 0? IllegalStateException is thrown!") ;
      }
    }
    return countH(0) ;
  }
  public int countH(int c) {
    int l = board.length ;
    //System.out.println(this.toString()) ;
    if (c >= l) return 1 ;
    int total = 0 ;
    for (int r = 0 ; r < l ; r++) {
      if (addQueen(r,c)) {
        total += countH(c + 1) ;
        removeQueen(r,c) ;
      }
    }
    //System.out.println("\nThe method countH() has concluded!") ;
    return total ;
  }

  ////////////////////////////////////////////////////////////////////////////////////
  //testcase must be a valid index of your input/output array
  public static void runTest(int i){
    QueenBoard b;
    int[]tests =   {1,2,3,4,5,8};
    int[]answers = {1,0,0,2,10,92};
    if(i >= 0 && i < tests.length ){
      int size = tests[i];
      int correct = answers[i];
      b = new QueenBoard(size);
      int ans  = b.countSolutions();

      if(correct==ans){
        System.out.println("PASS board size: "+tests[i]+" "+ans);
      }else{
        System.out.println("FAIL board size: "+tests[i]+" "+ans+" vs "+correct);
      }
    }
  }

}
