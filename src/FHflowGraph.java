import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import cs_1c.*;

public class FHflowGraph<E>
{
   protected HashSet<FHflowVertex<E>> vertexSet;
   private FHflowVertex<E> startVert, endVert;

   public FHflowGraph()
   {
      vertexSet = new HashSet<>();
   }

   //mutators
   public boolean setStartVert(E x)
   {
      this.startVert = new FHflowVertex<>(x);
      return true;
   }

   public boolean setEndVert(E x)
   {
      this.endVert = new FHflowVertex<>(x);
      return true;
   }

   //adds vertices as before, but adjacency lists are handled as follows:
   //resAdjLists will receive two edges based on this one call:
   // a forward edge, exactly  as before and a reverse edge with cost 0
   //flowAdjLists are built as before but with all costs = 0
   public void addEdge(E source, E dest, double cost)
   {
      FHflowVertex<E> src, dst;

      // put both source and dest into vertex list(s) if not already there
      src = addToVertexSet(source);
      dst = addToVertexSet(dest);

      // add dest to source's adjacency list
      src.addToFlowAdjList(dst, .0);
      src.addToResAdjList(dst, cost);
      dst.addToResAdjList(src, .0);

   }

   private FHflowVertex<E> addToVertexSet(E x)
   {
      FHflowVertex<E> retVal, vert;
      boolean successfulInsertion;
      Iterator<FHflowVertex<E>> iter;

      // save sort key for client
      FHflowVertex.pushKeyType();
      FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);

      // build and insert vertex into master list
      retVal = new FHflowVertex<E>(x);
      successfulInsertion = vertexSet.add(retVal);

      if (successfulInsertion)
      {
         FHflowVertex.popKeyType();  // restore client sort key
         return retVal;
      }

      // the vertex was already in the set, so get its ref
      for (iter = vertexSet.iterator(); iter.hasNext(); )
      {
         vert = iter.next();
         if (vert.equals(retVal))
         {
            FHflowVertex.popKeyType();  // restore client sort key
            return vert;
         }
      }

      FHflowVertex.popKeyType();  // restore client sort key
      return null;   // should never happen
   }

   public void clear()
   {
      vertexSet.clear();
   }

   protected FHflowVertex<E> getVertexWithThisData(E x)
   {
      FHflowVertex<E> searchVert, vert;
      Iterator<FHflowVertex<E>> iter;

      // save sort key for client
      FHflowVertex.pushKeyType();
      FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);

      // build vertex with data = x for the search
      searchVert = new FHflowVertex<E>(x);


      // the vertex was already in the set, so get its ref
      for (iter = vertexSet.iterator(); iter.hasNext(); )
      {
         vert = iter.next();
         if (vert.equals(searchVert))
         {
            FHflowVertex.popKeyType();
            return vert;
         }
      }

      FHflowVertex.popKeyType();
      return null;   // not found
   }

   public void showResAdjTable()
   {
      Iterator< FHflowVertex<E> > iter;

      System.out.println( "------------------------ ");
      for (iter = vertexSet.iterator(); iter.hasNext(); )
         (iter.next()).showResAdjList();
      System.out.println();
   }

   public void showFlowAdjTable()
   {
      Iterator< FHflowVertex<E> > iter;

      System.out.println( "------------------------ ");
      for (iter = vertexSet.iterator(); iter.hasNext(); )
         (iter.next()).showFlowAdjList();
      System.out.println();
   }


   // the main public algorithm.
   // (All the remaining algorithms are helpers and can be private.)
   // It returns the maximum flow found.
   public double findMaxFlow()
   {

      return .0;
   }


   //dijkstra() is used as a basis for
   protected boolean establishNextFlowPath()
   {

      return true;
   }

   protected double getLimitingFlowOnResPath()
   {

      return .0;
   }

   //adjusting the residual and flow graphs
   protected boolean adjustPathByCost(double cost)
   {

      return true;
   }

   protected double getCostOfResEdge(FHflowVertex<E> src, FHflowVertex<E> dst)
   {

      return .0;
   }


   protected boolean addCostToResEdge(FHflowVertex<E> src,
                                      FHflowVertex<E> dst, double cost)
   {

      return true;
   }

   protected boolean addCostToFlowEdge(FHflowVertex<E> src,
                                       FHflowVertex<E> dst, double cost)
   {

      return true;
   }


}

class FHflowVertex<E>
{
   public static Stack<Integer> keyStack = new Stack<Integer>();
   public static final int KEY_ON_DATA = 0, KEY_ON_DIST = 1;
   public static int keyType = KEY_ON_DATA;
   public static final double INFINITY = Double.MAX_VALUE;
   public HashSet<Pair<FHflowVertex<E>, Double>> flowAdjList = new HashSet<>();
   public HashSet<Pair<FHflowVertex<E>, Double>> resAdjList = new HashSet<>();

   public E data;
   public double dist;
   public FHflowVertex<E> nextInPath;  // for client-specific info

   public FHflowVertex(E x)
   {
      data = x;
      dist = INFINITY;
      nextInPath = null;
   }

   public FHflowVertex()
   {
      this(null);
   }

   // Modified or Added: addToFlowAdjList(), addToResAdjList()
   public void addToFlowAdjList(FHflowVertex<E> neighbor, double cost)
   {
      flowAdjList.add(new Pair<FHflowVertex<E>, Double>(neighbor, cost));
   }

   public void addToResAdjList(FHflowVertex<E> neighbor, double cost)
   {
      resAdjList.add(new Pair<FHflowVertex<E>, Double>(neighbor, cost));
   }


   public boolean equals(Object rhs)
   {
      FHflowVertex<E> other = (FHflowVertex<E>) rhs;
      switch (keyType)
      {
         case KEY_ON_DIST:
            return (dist == other.dist);
         case KEY_ON_DATA:
            return (data.equals(other.data));
         default:
            return (data.equals(other.data));
      }
   }

   public int hashCode()
   {
      switch (keyType)
      {
         case KEY_ON_DIST:
            Double d = dist;
            return (d.hashCode());
         case KEY_ON_DATA:
            return (data.hashCode());
         default:
            return (data.hashCode());
      }
   }

   public void showFlowAdjList()
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;

      System.out.print("Adj Flow List for " + data + ": ");
      for (iter = flowAdjList.iterator(); iter.hasNext(); )
      {
         pair = iter.next();
         System.out.print(pair.first.data + "("
                 + String.format("%3.1f", pair.second)
                 + ") ");
      }
      System.out.println();
   }

   public void showResAdjList()
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;

      System.out.print("Adj Res List for " + data + ": ");
      for (iter = resAdjList.iterator(); iter.hasNext(); )
      {
         pair = iter.next();
         System.out.print(pair.first.data + "("
                 + String.format("%3.1f", pair.second)
                 + ") ");
      }
      System.out.println();
   }

   public static boolean setKeyType(int whichType)
   {
      switch (whichType)
      {
         case KEY_ON_DATA:
         case KEY_ON_DIST:
            keyType = whichType;
            return true;
         default:
            return false;
      }
   }

   public static void pushKeyType()
   {
      keyStack.push(keyType);
   }

   public static void popKeyType()
   {
      keyType = keyStack.pop();
   }


}