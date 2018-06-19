import java.util.*;

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
      FHflowVertex<E> start, temp = new FHflowVertex<>(x);
      Iterator<FHflowVertex<E>> iter;
      for (iter = vertexSet.iterator(); iter.hasNext(); )
      {
         start = iter.next();
         if (start.equals(temp))
         {
            this.startVert = start;
            return true;
         }
      }
      return false;
   }

   public boolean setEndVert(E x)
   {
      FHflowVertex<E> end, temp = new FHflowVertex<>(x);
      Iterator<FHflowVertex<E>> iter;
      for (iter = vertexSet.iterator(); iter.hasNext(); )
      {
         end = iter.next();
         if (end.equals(temp))
         {
            this.endVert = end;
            return true;
         }
      }
      return false;
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
      Iterator<FHflowVertex<E>> iter;

      System.out.println("------------------------ ");
      for (iter = vertexSet.iterator(); iter.hasNext(); )
         (iter.next()).showResAdjList();
      System.out.println();
   }

   public void showFlowAdjTable()
   {
      Iterator<FHflowVertex<E>> iter;

      System.out.println("------------------------ ");
      for (iter = vertexSet.iterator(); iter.hasNext(); )
         (iter.next()).showFlowAdjList();
      System.out.println();
   }

   // loops, calling establishNextFlowPath(), getLimitingFlowOnResPath()
   // adjusts the residual and flow graphs using adjustPathByCost().
   // When establishNextFlowPath() returns false the loop ends.
   // returns the maximum flow found.
   public double findMaxFlow()
   {
      double minCost, maxFlow = .0;
      while (establishNextFlowPath())
      {
         minCost = getLimitingFlowOnResPath();
         System.out.println("minCost " + minCost);
         adjustPathByCost(minCost);
      }

      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
      for (iter = startVert.flowAdjList.iterator(); iter.hasNext(); )
      {
         pair = iter.next();
         maxFlow += pair.second;
      }
      return maxFlow;
   }

   //dijkstra() is used as a basis
   private boolean establishNextFlowPath()
   {
      System.out.println("\nin dijkstra--------------");
      FHflowVertex<E> w, v;
      Pair<FHflowVertex<E>, Double> edge;
      Iterator<FHflowVertex<E>> iter;
      Iterator<Pair<FHflowVertex<E>, Double>> edgeIter;
      Double costVW;
      Deque<FHflowVertex<E>> partiallyProcessedVerts = new LinkedList<>();

      // clear all nextInPath references
      for (iter = vertexSet.iterator(); iter.hasNext(); )
         iter.next().nextInPath = null;


      System.out.println("startVert " + startVert.data);
      System.out.println("endVert " + endVert.data);
      //startVert.showResAdjList();
      partiallyProcessedVerts.addLast(startVert);

      // outer dijkstra loop
      while (!partiallyProcessedVerts.isEmpty())
      {
         v = partiallyProcessedVerts.removeFirst();
         // Ends the loop as soon as it finds a path to endVert.
         // skip edges with costVW == 0
         v.showResAdjList();
         for (edgeIter = v.resAdjList.iterator(); edgeIter.hasNext(); )
         {
            edge = edgeIter.next();
            w = edge.first;
            costVW = edge.second;
            if (costVW != .0)
            {
               if (w.nextInPath == null)
               {
                  w.nextInPath = v;
               }
               partiallyProcessedVerts.addLast(w);
               System.out.println("v: " + v.data + "  w: " + w.data);
               if (w.equals(endVert))
               {
                  System.out.println("------end reached\n\n\n\n");
                  return true;
               }
            }
         }
      }
      return false;
   }

   private double getLimitingFlowOnResPath()
   {
      double minCost, currentCost;
      FHflowVertex<E> dst = endVert;
      FHflowVertex<E> src;
      minCost = Double.MAX_VALUE;
      //traverse the path util startVert is reached
      System.out.println("in getLimitingFlowOnResPath----------\nstartVert " + startVert.data);
      startVert.showResAdjList();
      do
      {
         src = dst.nextInPath;
         currentCost = getCostOfResEdge(src, dst);
         if (currentCost < minCost)
         {
            minCost = currentCost;
         }
         System.out.println("current minCost: " + minCost);
         dst = dst.nextInPath; // next step up the path

         System.out.print("srs " + src.data + "  ");
         src.showResAdjList();

         if (src.equals(startVert))
         {
            System.out.println("limited flow: " + minCost);
            return minCost;
         }
      } while (!src.equals(startVert));

      return minCost;
   }

   //adjusting the residual and flow graphs
   //flow edge += minCost
   //residual edge -= minCost
   //residual reverse edge += minCost
   private boolean adjustPathByCost(double cost)
   {
      System.out.println("\nin adjustPathByCost--------");
      FHflowVertex<E> src, w, dst = endVert;
      double currentCost;
      boolean result1, result2, result3;
      do
      {
         src = dst.nextInPath;
         currentCost = getCostOfResEdge(src, dst);
         result1 = addCostToResEdge(src, dst, currentCost - cost);
         //reverse edge:
         currentCost = getCostOfResEdge(dst, src);
         result2 = addCostToResEdge(dst, src, currentCost + cost);
         currentCost = getCostOfFlowEdge(src, dst);
         result3 = addCostToFlowEdge(src, dst, currentCost + cost);
         dst = dst.nextInPath; // next step up the path
      } while (!src.equals(startVert));
      //debugging:
      showResAdjTable();
      showFlowAdjTable();
      System.out.println("out adjustPathByCost--------");

      return (result1 && result2 && result3);
   }

   private double getCostOfResEdge(FHflowVertex<E> src, FHflowVertex<E> dst)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> edge;
      //iterate src resAdjList until dst vertex is found, get its cost
      for (iter = src.resAdjList.iterator(); iter.hasNext(); )
      {
         edge = iter.next();
         if (edge.first.equals(dst))
         {
            return edge.second;
         }
      }
      return 0;
   }

   private double getCostOfFlowEdge(FHflowVertex<E> src, FHflowVertex<E> dst)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> edge;
      //iterate src resAdjList until dst vertex is found, get its cost
      for (iter = src.flowAdjList.iterator(); iter.hasNext(); )
      {
         edge = iter.next();
         if (edge.first.equals(dst))
         {
            return edge.second;
         }
      }
      return 0;
   }


   private boolean addCostToResEdge(FHflowVertex<E> src,
                                    FHflowVertex<E> dst, double cost)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> edge;
      for (iter = src.resAdjList.iterator(); iter.hasNext(); )
      {
         edge = iter.next();
         if (edge.first.equals(dst))
         {
            edge.second = cost;
            return true;
         }
      }
      return false;
   }

   private boolean addCostToFlowEdge(FHflowVertex<E> src,
                                     FHflowVertex<E> dst, double cost)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> edge;
      for (iter = src.flowAdjList.iterator(); iter.hasNext(); )
      {
         edge = iter.next();
         if (edge.first.equals(dst))
         {
            edge.second = cost;
            return true;
         }
      }
      return false;
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