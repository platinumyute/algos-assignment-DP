/**
 * Rod cutting problem described in Chapter 15 of textbook
 */
public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
	  int [] memBest = new int[rodLength];
	  int best = rodCuttingMoization(rodLength, lengthPrices, memBest);
	  
    return best;
  }
  
public int rodCuttingMoization(int rodLength, int[] lengthPrices,int[] memBest) {
	  
	  if(rodLength == 1)
		  return lengthPrices[0];
	  
	  if(memBest[rodLength-1] != 0)
		  return memBest[rodLength-1];
	  
	  int best = lengthPrices[rodLength-1];
	  
	  for(int i = 1; i  <= rodLength/2; i++)
	  {
		  int cutA = rodCuttingMoization(i, lengthPrices, memBest);
		  int cutB = rodCuttingMoization(rodLength-i, lengthPrices, memBest);
		  int totValueFromCuts = cutA + cutB;
		  best = Math.max(totValueFromCuts, best);
	  }
	  memBest[rodLength-1] = best;
    return best;
}

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
	int [] memBest = new int[rodLength+1];
	memBest[1] = lengthPrices[0];
	for(int i = 1; i <= rodLength; i++)
	{
		int best=lengthPrices[i-1];
		for(int j = 1; j <= i; j++)
		{	
			best = Math.max(best, memBest[j] + memBest[(i-j)]);
		}
		memBest[i] = best;
	}
    return memBest[rodLength];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
