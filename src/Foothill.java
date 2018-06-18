// --------------------------------------------------------------------
// CIS 1C Assignment #9
// Instructor Solution Client

public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      double finalFlow;

      // build graph
      FHflowGraph<String> myG = new FHflowGraph<String>();

      myG.addEdge("s","a", 3);
      myG.addEdge("s","b", 2);
      myG.addEdge("a","b", 1);
      myG.addEdge("a","c", 3);
      myG.addEdge("a","d", 4);
      myG.addEdge("b","d", 2);
      myG.addEdge("c","t", 2);
      myG.addEdge("d","t", 3);

      // show the original flow graph
      myG.showResAdjTable();
      myG.showFlowAdjTable();

      myG.setStartVert("s");
      myG.setEndVert("t");

      finalFlow = myG.findMaxFlow();

      System.out.println("Final flow: " + finalFlow);

      myG.showResAdjTable();
      myG.showFlowAdjTable();
   }
}


/* --------- output -----------
------------------------ 
Adj Res List for d: t(3.0) b(0.0) a(0.0) 
Adj Res List for t: d(0.0) c(0.0) 
Adj Res List for b: d(2.0) s(0.0) a(0.0) 
Adj Res List for s: b(2.0) a(3.0) 
Adj Res List for c: t(2.0) a(0.0) 
Adj Res List for a: d(4.0) b(1.0) s(0.0) c(3.0) 

------------------------ 
Adj Flow List for d: t(0.0) 
Adj Flow List for t: 
Adj Flow List for b: d(0.0) 
Adj Flow List for s: b(0.0) a(0.0) 
Adj Flow List for c: t(0.0) 
Adj Flow List for a: d(0.0) b(0.0) c(0.0) 

Final flow: 5.0
------------------------ 
Adj Res List for d: t(0.0) b(2.0) a(1.0) 
Adj Res List for t: d(3.0) c(2.0) 
Adj Res List for b: d(0.0) s(2.0) a(0.0) 
Adj Res List for s: b(0.0) a(0.0) 
Adj Res List for c: t(0.0) a(2.0) 
Adj Res List for a: d(3.0) b(1.0) s(3.0) c(1.0) 

------------------------ 
Adj Flow List for d: t(3.0) 
Adj Flow List for t: 
Adj Flow List for b: d(2.0) 
Adj Flow List for s: b(2.0) a(3.0) 
Adj Flow List for c: t(2.0) 
Adj Flow List for a: d(1.0) b(0.0) c(2.0)
*/