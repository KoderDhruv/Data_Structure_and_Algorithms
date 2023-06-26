import java.util.*;

public class graphs {
  static class Edge {
    int src;
    int dest;
    int wt;

    Edge(int src, int dest, int wt) {
      this.src = src;
      this.dest = dest;
      this.wt = wt;
    }
  }

  static class Pair implements Comparable<Pair> {
    int n;
    int path;

    Pair(int n, int path) {
      this.n = n;
      this.path = path;
    }

    @Override
    public int compareTo(Pair p2) {
      return this.path - p2.path;
    }
  }

  // Dijkstra's Algorithm
  public static void Dijkstra(ArrayList<Edge> graph[], int src) {
    int dist[] = new int[graph.length];
    for (int i = 0; i < graph.length; i++) {
      if (i != src) {
        dist[i] = Integer.MAX_VALUE;
      }
    }
    boolean vis[] = new boolean[graph.length];
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    pq.add(new Pair(src, 0));
    while (!pq.isEmpty()) {
      Pair curr = pq.remove();
      if (!vis[curr.n]) {
        vis[curr.n] = true;
        // neighbours
        for (int i = 0; i < graph[curr.n].size(); i++) {
          Edge e = graph[curr.n].get(i);
          int u = e.src;
          int v = e.dest;
          int wt = e.wt;
          if (dist[u] + wt < dist[v]) {
            dist[v] = dist[u] + wt;
            pq.add(new Pair(v, dist[v]));
          }
        }
      }
    }
    for (int i = 0; i < dist.length; i++) {
      System.out.print(dist[i] + " ");
    }
    System.out.println();
  }

  // All path from source to destination
  public static void printAllPath(ArrayList<Edge> graph[], int src, int dest, String path) {
    if (src == dest) {
      System.out.println(path + dest);
      return;
    }
    for (int i = 0; i < graph.length; i++) {
      Edge e = graph[src].get(i);
      printAllPath(graph, e.dest, dest, path + src);
    }
  }

  // Topological sort using BFS (Kahn's Algorithm)
  public static void kahn(ArrayList<Edge> graph[]) {
    int indeg[] = new int[graph.length];
    calcIndeg(graph, indeg);
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < indeg.length; i++) {
      if (indeg[i] == 0) {
        q.add(i);
      }
    }
    while (!q.isEmpty()) {
      int curr = q.remove();
      System.out.print(curr + " ");
      for (int i = 0; i < graph[curr].size(); i++) {
        Edge e = graph[curr].get(i);
        indeg[e.dest]--;
        if (indeg[e.dest] == 0) {
          q.add(e.dest);
        }
      }
    }
    System.out.println();
  }

  public static void calcIndeg(ArrayList<Edge> graph[], int indeg[]) {
    for (int i = 0; i < graph.length; i++) {
      int v = i;
      for (int j = 0; j < graph[v].size(); j++) {
        Edge e = graph[v].get(j);
        indeg[e.dest]++;
      }
    }
  }

  // Topological Sort (Only for Directed Acyclic Graph)
  public static void topologicalSort(ArrayList<Edge> graph[]) {
    boolean vis[] = new boolean[graph.length];
    Stack<Integer> s = new Stack<>();
    for (int i = 0; i < graph.length; i++) {
      if (!vis[i]) {
        topologicalSortUtil(graph, i, vis, s);
      }
    }
    while (!s.isEmpty()) {
      System.out.print(s.pop() + " ");
    }
  }

  public static void topologicalSortUtil(ArrayList<Edge> graph[], int curr, boolean vis[], Stack<Integer> s) {
    vis[curr] = true;
    for (int i = 0; i < graph.length; i++) {
      Edge e = graph[curr].get(i);
      if (!vis[i]) {
        topologicalSortUtil(graph, e.dest, vis, s);
      }
    }
    s.push(curr);
  }

  // Cycle detection in direcetd graph
  public static boolean isCycleDirected(ArrayList<Edge> graph[]) {
    boolean vis[] = new boolean[graph.length];
    boolean stack[] = new boolean[graph.length];
    for (int i = 0; i < graph.length; i++) {
      if (!vis[i]) {
        if (isCycleDirectedUtil(graph, i, vis, stack)) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean isCycleDirectedUtil(ArrayList<Edge> graph[], int curr, boolean vis[], boolean[] stack) {
    vis[curr] = true;
    stack[curr] = true;
    for (int i = 0; i < graph[curr].size(); i++) {
      Edge e = graph[curr].get(i);
      if (stack[e.dest]) {
        return true;
      }
      if (!vis[e.dest] && isCycleDirectedUtil(graph, e.dest, vis, stack)) {
        return true;
      }
    }
    stack[curr] = false;
    return false;
  }

  // Check if graph is bipartite
  public static boolean isBipartite(ArrayList<Edge>[] graph) {
    int col[] = new int[graph.length];
    for (int i = 0; i < graph.length; i++) {
      col[i] = -1;
    }
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < graph.length; i++) {
      if (col[i] == -1) {
        q.add(i);
        col[i] = 0;
        while (!q.isEmpty()) {
          int curr = q.remove();
          for (int j = 0; j < graph[curr].size(); j++) {
            Edge e = graph[curr].get(j);
            if (col[e.dest] == -1) {
              int nextCol = col[curr] == 0 ? 1 : 0;
              col[e.dest] = nextCol;
              q.add(e.dest);
            } else if (col[e.dest] == col[curr]) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  // Detect cycle in a graph
  public static boolean cycleDetect(ArrayList<Edge>[] graph) {
    boolean[] vis = new boolean[graph.length];
    for (int i = 0; i < graph.length; i++) {
      if (!vis[i]) {
        if (cycleDetectUtil(graph, vis, i, -1)) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean cycleDetectUtil(ArrayList<Edge>[] graph, boolean[] vis, int curr, int par) {
    vis[curr] = true;
    for (int i = 0; i < graph[curr].size(); i++) {
      Edge e = graph[curr].get(i);
      if (!vis[e.dest]) {
        if (cycleDetectUtil(graph, vis, e.dest, curr)) {
          return true;
        }
      } else if (vis[e.dest] && e.dest != par) {
        return true;
      }
    }
    return false;
  }

  // BFS for disjoint components in a garph
  public static void bfsConnect(ArrayList<Edge>[] graph) {
    boolean vis[] = new boolean[graph.length];
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < graph.length; i++) {
      if (!vis[i]) {
        bfsUtil(graph, vis, i, q);
      }
    }
  }

  public static void bfsUtil(ArrayList<Edge>[] graph, boolean[] vis, int src, Queue<Integer> q) {
    q.add(src);
    while (!q.isEmpty()) {
      int curr = q.remove();
      if (!vis[curr]) {
        System.out.println(curr + " ");
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
          Edge e = graph[curr].get(i);
          q.add(e.dest);
        }
      }
    }
  }

  // DFS for disjoint component of a graph
  public static void dfsConnect(ArrayList<Edge>[] graph) {
    boolean[] vis = new boolean[graph.length];
    for (int i = 0; i < graph.length; i++) {
      dfsUtil(graph, i, vis);
    }
  }

  public static void dfsUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis) {
    System.out.println(curr + " ");
    vis[curr] = true;
    for (int i = 0; i < graph[curr].size(); i++) {
      Edge e = graph[curr].get(i);
      if (!vis[e.dest]) {
        dfsUtil(graph, e.dest, vis);
      }
    }
  }

  public static boolean hasPath(ArrayList<Edge> graph[], int src, int dest, boolean vis[]) {
    if (src == dest) {
      return true;
    }
    vis[src] = true;
    for (int i = 0; i < graph[src].size(); i++) {
      if (!vis[graph[src].get(i).dest] && hasPath(graph, graph[src].get(i).dest, dest, vis)) {
        return true;
      }
    }
    return false;
  }

  public static void dfs(ArrayList<Edge>[] graph, int curr, boolean vis[]) {
    System.out.print(curr + " ");
    vis[curr] = true;
    for (int i = 0; i < graph[curr].size(); i++) {
      if (!vis[graph[curr].get(i).dest]) {
        dfs(graph, graph[curr].get(i).dest, vis);
      }
    }
  }

  public static void bfs(ArrayList<Edge>[] graph) { // O(V+E)
    Queue<Integer> q = new LinkedList<>();
    boolean vis[] = new boolean[graph.length];
    q.add(0);
    while (!q.isEmpty()) {
      int curr = q.remove();
      if (!vis[curr]) {
        System.out.print(curr + " ");
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
          q.add(graph[curr].get(i).dest);
        }
      }
    }
  }

  public static void createGraph(ArrayList<Edge> graph[]) {
    for (int i = 0; i < graph.length; i++) {
      graph[i] = new ArrayList<Edge>();
    }

    // undirected graph
    // graph[0].add(new Edge(0, 1, 1));
    // graph[0].add(new Edge(0, 2, 1));

    // graph[1].add(new Edge(1, 0, 1));
    // graph[1].add(new Edge(1, 3, 1));

    // graph[2].add(new Edge(2, 0, 1));
    // graph[2].add(new Edge(2, 4, 1));

    // graph[3].add(new Edge(3, 1, 1));
    // graph[3].add(new Edge(3, 4, 1));
    // graph[3].add(new Edge(3, 5, 1));

    // graph[4].add(new Edge(4, 2, 1));
    // graph[4].add(new Edge(4, 3, 1));
    // graph[4].add(new Edge(4, 5, 1));

    // graph[5].add(new Edge(5, 3, 1));
    // graph[5].add(new Edge(5, 4, 1));
    // graph[5].add(new Edge(5, 6, 1));

    // graph[6].add(new Edge(6, 5, 1));

    // directed acyclic graph
    graph[0].add(new Edge(0, 3, 1));

    graph[2].add(new Edge(2, 3, 1));

    graph[3].add(new Edge(3, 1, 1));

    graph[4].add(new Edge(4, 0, 1));
    graph[4].add(new Edge(4, 1, 1));

    graph[5].add(new Edge(5, 0, 1));
    graph[5].add(new Edge(5, 2, 1));
  }

  public static void main(String args[]) {
    // Implementation using adjacency matrix
    int V = 7;
    @SuppressWarnings("unchecked")
    ArrayList<Edge> graph[] = new ArrayList[V];
    createGraph(graph);
    // for (int i = 0; i < graph[2].size(); i++) { // get destination from 2
    // System.out.println(graph[2].get(i).dest + " ");
    // }

    // Traversal
    // bfs(graph);
    // dfs(graph, 0, new boolean[7]);

    // Detect cycle
    // System.out.print(cycleDetect(graph));

    // isBipartite
    // System.out.print(isBipartite(graph));
  }
}
