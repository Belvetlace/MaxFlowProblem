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
         System.out.println("\n###########  Test " + i + "  ###############");
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

###########  Test 1  ###############
------------------------
Adj Res List for a: b(1.0) s(0.0) c(3.0) d(4.0)
Adj Res List for b: a(0.0) s(0.0) d(2.0)
Adj Res List for s: a(3.0) b(2.0)
Adj Res List for c: a(0.0) t(2.0)
Adj Res List for d: a(0.0) b(0.0) t(3.0)
Adj Res List for t: c(0.0) d(0.0)

------------------------
Adj Flow List for a: b(0.0) c(0.0) d(0.0)
Adj Flow List for b: d(0.0)
Adj Flow List for s: a(0.0) b(0.0)
Adj Flow List for c: t(0.0)
Adj Flow List for d: t(0.0)
Adj Flow List for t:



Final flow: 5.0
------------------------
Adj Res List for a: b(1.0) s(3.0) c(1.0) d(3.0)
Adj Res List for b: a(0.0) s(2.0) d(0.0)
Adj Res List for s: a(0.0) b(0.0)
Adj Res List for c: a(2.0) t(0.0)
Adj Res List for d: a(1.0) b(2.0) t(0.0)
Adj Res List for t: c(2.0) d(3.0)

------------------------
Adj Flow List for a: b(0.0) c(2.0) d(1.0)
Adj Flow List for b: d(2.0)
Adj Flow List for s: a(3.0) b(2.0)
Adj Flow List for c: t(2.0)
Adj Flow List for d: t(3.0)
Adj Flow List for t:


###########  Test 2  ###############
------------------------
Adj Res List for a: b(1.0) s(0.0) c(2.0) d(4.0)
Adj Res List for b: a(0.0) s(0.0) d(2.0)
Adj Res List for s: a(4.0) b(2.0)
Adj Res List for c: a(0.0) t(3.0)
Adj Res List for d: a(0.0) b(0.0) t(3.0)
Adj Res List for t: c(0.0) d(0.0)

------------------------
Adj Flow List for a: b(0.0) c(0.0) d(0.0)
Adj Flow List for b: d(0.0)
Adj Flow List for s: a(0.0) b(0.0)
Adj Flow List for c: t(0.0)
Adj Flow List for d: t(0.0)
Adj Flow List for t:



Final flow: 5.0
------------------------
Adj Res List for a: b(1.0) s(4.0) c(0.0) d(2.0)
Adj Res List for b: a(0.0) s(1.0) d(1.0)
Adj Res List for s: a(0.0) b(1.0)
Adj Res List for c: a(2.0) t(1.0)
Adj Res List for d: a(2.0) b(1.0) t(0.0)
Adj Res List for t: c(2.0) d(3.0)

------------------------
Adj Flow List for a: b(0.0) c(2.0) d(2.0)
Adj Flow List for b: d(1.0)
Adj Flow List for s: a(4.0) b(1.0)
Adj Flow List for c: t(2.0)
Adj Flow List for d: t(3.0)
Adj Flow List for t:


###########  Test 3  ###############
------------------------
Adj Res List for A: B(2.0) s(0.0) D(0.0) E(2.0)
Adj Res List for B: A(0.0) C(2.0)
Adj Res List for s: A(1.0) D(4.0) G(6.0)
Adj Res List for C: B(0.0) t(4.0) E(0.0) F(0.0)
Adj Res List for D: A(3.0) s(0.0) E(3.0) G(0.0)
Adj Res List for t: C(0.0) F(0.0) I(0.0)
Adj Res List for E: A(0.0) C(2.0) D(0.0) F(3.0) G(0.0) H(0.0) I(3.0)
Adj Res List for F: C(1.0) t(3.0) E(0.0) I(0.0)
Adj Res List for G: s(0.0) D(2.0) E(1.0) H(6.0)
Adj Res List for H: E(2.0) G(0.0) I(6.0)
Adj Res List for I: t(4.0) E(0.0) F(1.0) H(0.0)

------------------------
Adj Flow List for A: B(0.0) E(0.0)
Adj Flow List for B: C(0.0)
Adj Flow List for s: A(0.0) D(0.0) G(0.0)
Adj Flow List for C: t(0.0)
Adj Flow List for D: A(0.0) E(0.0)
Adj Flow List for t:
Adj Flow List for E: C(0.0) F(0.0) I(0.0)
Adj Flow List for F: C(0.0) t(0.0)
Adj Flow List for G: D(0.0) E(0.0) H(0.0)
Adj Flow List for H: E(0.0) I(0.0)
Adj Flow List for I: t(0.0) F(0.0)



Final flow: 11.0
------------------------
Adj Res List for A: B(0.0) s(1.0) D(1.0) E(2.0)
Adj Res List for B: A(2.0) C(0.0)
Adj Res List for s: A(0.0) D(0.0) G(0.0)
Adj Res List for C: B(2.0) t(0.0) E(2.0) F(0.0)
Adj Res List for D: A(2.0) s(4.0) E(0.0) G(0.0)
Adj Res List for t: C(4.0) F(3.0) I(4.0)
Adj Res List for E: A(0.0) C(0.0) D(3.0) F(0.0) G(1.0) H(1.0) I(3.0)
Adj Res List for F: C(1.0) t(0.0) E(3.0) I(0.0)
Adj Res List for G: s(6.0) D(2.0) E(0.0) H(1.0)
Adj Res List for H: E(1.0) G(5.0) I(2.0)
Adj Res List for I: t(0.0) E(0.0) F(1.0) H(4.0)

------------------------
Adj Flow List for A: B(2.0) E(0.0)
Adj Flow List for B: C(2.0)
Adj Flow List for s: A(1.0) D(4.0) G(6.0)
Adj Flow List for C: t(4.0)
Adj Flow List for D: A(1.0) E(3.0)
Adj Flow List for t:
Adj Flow List for E: C(2.0) F(3.0) I(0.0)
Adj Flow List for F: C(0.0) t(3.0)
Adj Flow List for G: D(0.0) E(1.0) H(5.0)
Adj Flow List for H: E(1.0) I(4.0)
Adj Flow List for I: t(4.0) F(0.0)


###########  Test 4  ###############
------------------------
Adj Res List for a: b(5.0) s(0.0) e(5.0)
Adj Res List for b: a(0.0) t(5.0) i(0.0)
Adj Res List for s: a(5.0) c(5.0) d(5.0) h(5.0)
Adj Res List for c: s(0.0) t(5.0) d(0.0)
Adj Res List for d: s(0.0) c(5.0) g(5.0)
Adj Res List for t: b(0.0) c(0.0) g(0.0) j(0.0)
Adj Res List for e: a(0.0) j(5.0)
Adj Res List for g: d(0.0) t(5.0)
Adj Res List for h: s(0.0) i(5.0)
Adj Res List for i: b(5.0) h(0.0)
Adj Res List for j: t(5.0) e(0.0)

------------------------
Adj Flow List for a: b(0.0) e(0.0)
Adj Flow List for b: t(0.0)
Adj Flow List for s: a(0.0) c(0.0) d(0.0) h(0.0)
Adj Flow List for c: t(0.0)
Adj Flow List for d: c(0.0) g(0.0)
Adj Flow List for t:
Adj Flow List for e: j(0.0)
Adj Flow List for g: t(0.0)
Adj Flow List for h: i(0.0)
Adj Flow List for i: b(0.0)
Adj Flow List for j: t(0.0)



Final flow: 20.0
------------------------
Adj Res List for a: b(5.0) s(5.0) e(0.0)
Adj Res List for b: a(0.0) t(0.0) i(5.0)
Adj Res List for s: a(0.0) c(0.0) d(0.0) h(0.0)
Adj Res List for c: s(5.0) t(0.0) d(0.0)
Adj Res List for d: s(5.0) c(5.0) g(0.0)
Adj Res List for t: b(5.0) c(5.0) g(5.0) j(5.0)
Adj Res List for e: a(5.0) j(0.0)
Adj Res List for g: d(5.0) t(0.0)
Adj Res List for h: s(5.0) i(0.0)
Adj Res List for i: b(0.0) h(5.0)
Adj Res List for j: t(0.0) e(5.0)

------------------------
Adj Flow List for a: b(5.0) e(5.0)
Adj Flow List for b: t(5.0)
Adj Flow List for s: a(5.0) c(5.0) d(5.0) h(5.0)
Adj Flow List for c: t(5.0)
Adj Flow List for d: c(0.0) g(5.0)
Adj Flow List for t:
Adj Flow List for e: j(5.0)
Adj Flow List for g: t(5.0)
Adj Flow List for h: i(5.0)
Adj Flow List for i: b(5.0)
Adj Flow List for j: t(5.0)


*/