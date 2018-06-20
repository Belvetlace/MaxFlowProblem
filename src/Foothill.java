// --------------------------------------------------------------------
// CIS 1C Assignment #9
// Instructor Solution Client

public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      double finalFlow;

      FHflowGraph<String> myG = new FHflowGraph<String>();

      for (int i = 1; i < 5; i++)
      {
         System.out.println("###########  Test " + i + "  ###############");
         switch (i)
         {
            case 1:
               setGraph1(myG);
               break;
            case 2:
               setGraph2(myG);
               break;
            case 3:
               setGraph3(myG);
               break;
            case 4:
               setGraph4(myG);
               break;
         }
         // show the original flow graph
         myG.showResAdjTable();
         myG.showFlowAdjTable();

         myG.setStartVert("s");
         myG.setEndVert("t");

         finalFlow = myG.findMaxFlow();

         System.out.println("\n\nFinal flow: " + finalFlow);

         myG.showResAdjTable();
         myG.showFlowAdjTable();
      }

   }

   public static void setGraph4(FHflowGraph<String> myG)
   {
      myG.clear();
      myG.addEdge("s","h", 5);
      myG.addEdge("h","i", 5);
      myG.addEdge("i","b", 5);
      myG.addEdge("a","b", 5);
      myG.addEdge("s","a", 5);
      myG.addEdge("s","c", 5);
      myG.addEdge("s","d", 5);
      myG.addEdge("a","e", 5);
      myG.addEdge("e","j", 5);
      myG.addEdge("j","t", 5);
      myG.addEdge("d","g", 5);
      myG.addEdge("g","t", 5);
      myG.addEdge("b","t", 5);
      myG.addEdge("c","t", 5);
      myG.addEdge("d","c", 5);
   }

   public static void setGraph3(FHflowGraph<String> myG)
   {
      myG.clear();
      myG.addEdge("s","A", 1);
      myG.addEdge("s","D", 4);
      myG.addEdge("s","G", 6);
      myG.addEdge("A","B", 2);
      myG.addEdge("A","E", 2);
      myG.addEdge("B","C", 2);
      myG.addEdge("C","t", 4);
      myG.addEdge("D","E", 3);
      myG.addEdge("D","A", 3);
      myG.addEdge("E","C", 2);
      myG.addEdge("E","F", 3);
      myG.addEdge("E","I", 3);
      myG.addEdge("F","C", 1);
      myG.addEdge("F","t", 3);
      myG.addEdge("G","D", 2);
      myG.addEdge("G","E", 1);
      myG.addEdge("G","H", 6);
      myG.addEdge("H","E", 2);
      myG.addEdge("H","I", 6);
      myG.addEdge("I","F", 1);
      myG.addEdge("I","t", 4);
   }

   public static void setGraph2(FHflowGraph<String> myG)
   {
      myG.clear();
      myG.addEdge("s","a", 4);
      myG.addEdge("s","b", 2);
      myG.addEdge("a","b", 1);
      myG.addEdge("a","c", 2);
      myG.addEdge("a","d", 4);
      myG.addEdge("b","d", 2);
      myG.addEdge("c","t", 3);
      myG.addEdge("d","t", 3);
   }

   public static void setGraph1(FHflowGraph<String> myG)
   {
      myG.clear();

      myG.addEdge("s","a", 3);
      myG.addEdge("s","b", 2);
      myG.addEdge("a","b", 1);
      myG.addEdge("a","c", 3);
      myG.addEdge("a","d", 4);
      myG.addEdge("b","d", 2);
      myG.addEdge("c","t", 2);
      myG.addEdge("d","t", 3);
   }
}


/* --------- output -----------

*/