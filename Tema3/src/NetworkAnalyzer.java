import java.util.*;

public class NetworkAnalyzer {
    private Map<Node, Set<Node>> adj;
    private int time;
    private Map<Node, Integer> disc;
    private Map<Node, Integer> low;
    private Map<Node, Node> parent;
    private Set<Node> articulationPoints;
    private List<Set<Node>> biconnectedComponents;
    private Deque<Edge> edgeStack;

    static class Edge {
        Node u, v;
        Edge(Node u, Node v) { this.u = u; this.v = v; }
    }

    public NetworkAnalyzer(List<Node> nodes) {
        adj = new HashMap<>();
        for (Node n : nodes) {
            adj.putIfAbsent(n, new HashSet<>());
            if (n.getRelationships() != null) {
                for (Object target : n.getRelationships().keySet()) {
                    if (target instanceof Node) {
                        Node v = (Node) target;
                        adj.get(n).add(v);
                        adj.putIfAbsent(v, new HashSet<>());
                        adj.get(v).add(n);
                    }
                }
            }
        }
    }

    public void analyze() {
        time = 0;
        disc = new HashMap<>();
        low = new HashMap<>();
        parent = new HashMap<>();
        articulationPoints = new HashSet<>();
        biconnectedComponents = new ArrayList<>();
        edgeStack = new ArrayDeque<>();

        for (Node u : adj.keySet()) {
            if (!disc.containsKey(u)) {
                dfs(u);
                if (!edgeStack.isEmpty()) {
                    Set<Node> component = new HashSet<>();
                    while (!edgeStack.isEmpty()) {
                        Edge e = edgeStack.pop();
                        component.add(e.u);
                        component.add(e.v);
                    }
                    if (component.size() >= 2) {
                        biconnectedComponents.add(component);
                    }
                }
            }
        }
    }

    private void dfs(Node u) {
        disc.put(u, ++time);
        low.put(u, time);
        int children = 0;

        for (Node v : adj.get(u)) {
            if (!disc.containsKey(v)) {
                children++;
                parent.put(v, u);
                edgeStack.push(new Edge(u, v));

                dfs(v);

                low.put(u, Math.min(low.get(u), low.get(v)));

                if ((parent.get(u) == null && children > 1) || (parent.get(u) != null && low.get(v) >= disc.get(u))) {
                    articulationPoints.add(u);
                    Set<Node> component = new HashSet<>();
                    while (true) {
                        Edge e = edgeStack.pop();
                        component.add(e.u);
                        component.add(e.v);
                        if (e.u == u && e.v == v) break;
                    }
                    biconnectedComponents.add(component);
                }
            } else if (v != parent.get(u) && disc.get(v) < disc.get(u)) {
                low.put(u, Math.min(low.get(u), disc.get(v)));
                edgeStack.push(new Edge(u, v));
            }
        }
    }

    public Set<Node> getArticulationPoints() { return articulationPoints; }
    public List<Set<Node>> getBiconnectedComponents() { return biconnectedComponents; }
}