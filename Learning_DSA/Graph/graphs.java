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

  // Detect cycle in a graph
  public static boolean cycleDetect(ArrayList<Edge>[] graph) {
    boolean[] vis = new boolean[graph.length];
    for (int i = 0; i < graph.length; i++) {
      if (!vis[i]) {
        if(cycleDetectUtil(graph, vis, i, -1)){
          return true;
        }
      }
    }
    return false;
  }

  public static boolean cycleDetectUtil(ArrayList<Edge>[] graph, boolean[] vis, int curr, int par) {
    
    return false;
  }

  // BFS for disjoint components in a garph
  public static void bfsConnect(ArrayList<Edge>[] graph) {
    boolean vis[] = new boolean[graph.length];
    for (int i = 0; i < graph.length; i++) {
      if (!vis[i]) {
        bfsUtil(graph, vis);
      }
    }
  }

  public static void bfsUtil(ArrayList<Edge>[] graph, boolean[] vis) {
    Queue<Integer> q = new LinkedList<>();
    q.add(0);
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

    graph[0].add(new Edge(0, 1, 1));
    graph[0].add(new Edge(0, 2, 1));

    graph[1].add(new Edge(1, 0, 1));
    graph[1].add(new Edge(1, 3, 1));

    graph[2].add(new Edge(2, 0, 1));
    graph[2].add(new Edge(2, 4, 1));

    graph[3].add(new Edge(3, 1, 1));
    graph[3].add(new Edge(3, 4, 1));
    graph[3].add(new Edge(3, 5, 1));

    graph[4].add(new Edge(4, 2, 1));
    graph[4].add(new Edge(4, 3, 1));
    graph[4].add(new Edge(4, 5, 1));

    graph[5].add(new Edge(5, 3, 1));
    graph[5].add(new Edge(5, 4, 1));
    graph[5].add(new Edge(5, 6, 1));

    graph[6].add(new Edge(6, 5, 1));
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

  }
}
