

/**
 * Glass Falling
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
 	
	  if(floors == 1)
		  return 1;

	  if(floors == 0)
		  return 0;

	  if(sheets == 1)
		  return floors;
	 
	  int maxSteps = 0;
      int botHalf =  floors / 2;
      int topHalf =  floors - (floors / 2);
      maxSteps = 1 + Math.max(glassFallingRecur(botHalf, sheets - 1), glassFallingRecur(topHalf, sheets));
      return maxSteps;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors, int sheets) {
	  return glassFallingMemoizedBackEnd(floors, sheets, new int[floors+1][sheets+1]);
  }
  
  private int glassFallingMemoizedBackEnd(int floors, int sheets, int[][] mem) {
	  
	  if(floors == 1)
		  return 1;

	  if(floors == 0)
		  return 0;
	  
	  if(sheets == 1)
		  return floors;
	  
	  int maxSteps = 0;
      int botHalf =  floors / 2;
      int topHalf =  floors - (floors / 2);
      
      if(mem[botHalf][sheets] == 0)
    	 mem[botHalf][sheets] = glassFallingMemoizedBackEnd(botHalf, sheets - 1, mem);
      
      if(mem[topHalf][sheets] == 0)
    	  mem[topHalf][sheets] = glassFallingMemoizedBackEnd(topHalf, sheets, mem);
    	
      maxSteps = 1 + Math.max(mem[topHalf][sheets], mem[botHalf][sheets]);
      
      return maxSteps;
  }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
	  int[][] mem = new int[floors+1][sheets+1];
	  if(mem[floors][sheets] != 0)
		  return mem[floors][sheets];
	  for(int i = 1; i <= floors; i++)
	  {
		  for(int j = 1; j <= sheets; j++)
		  {
			  if(i == 1)
				  mem[i][j] = 1;
			  else
			  {
				  if(j == 1)
					  mem[i][j] = i;
				  else
				  {
					  int a = i / 2;
				  
					  int b =  i - a;
					  mem[i][j] = 1 + (Math.max(mem[a][j-1], mem[b][j]));
				  }
			  }
 		  }
	  }
   	  return mem[floors][sheets];  
  }
  
  //after some observation of the recursion trees, 
  //i came up with this additional solution
  public int glassFallingViaCalculations(int floors, int sheets) {
    // Fill in here and change the return
	 int i = floors;
	 int log2floors = 0;
	 while(i / 2 != 0)
	 {
		 i /= 2;
		 log2floors++;
	 }
	 
	 int k = Math.min(sheets, log2floors) - 1;
	 
	 int result = k + (floors /((int)Math.pow(2,k)));
    return result;
  }
 

  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Recur = gf.glassFallingMemoized(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
  
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Recur + " " + minTrials2Bottom);
  }
}
