package Ex04;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class SAP {
    private Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        int[] result = shortest(v, w);
        return result[0];
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        int[] result = shortest(v, w);
        return result[1];
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int[] result = shortest(v, w);
        return result[0];
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int[] result = shortest(v, w);
        return result[1];
    }

    private int[] shortest(int v, int w) {
        int[] result = new int[2];
        DeluxeBFS vDeluexBFS = new DeluxeBFS(G, v);
        DeluxeBFS wDeluexBFS = new DeluxeBFS(G, w);
        boolean[] vMarked = vDeluexBFS.getMarked();
        boolean[] wMarked = wDeluexBFS.getMarked();
        int shortestLength = Integer.MAX_VALUE;
        int tempLength = Integer.MAX_VALUE;
        int shortestAncestor = Integer.MAX_VALUE;
        for (int i = 0; i < vMarked.length; i++) {
            if (vMarked[i] && wMarked[i]) {
                tempLength = vDeluexBFS.distTo(i) + wDeluexBFS.distTo(i);
                if (tempLength < shortestLength) {
                    shortestLength = tempLength;
                    shortestAncestor = i;
                }
            }
        }
        if (shortestLength == Integer.MAX_VALUE) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        result[0] = shortestLength;
        result[1] = shortestAncestor;
        return result;
    }

    private int[] shortest(Iterable<Integer> v, Iterable<Integer> w) {
        int shortestAncestor = Integer.MAX_VALUE;
        int shortestLength = Integer.MAX_VALUE;
        int[] result = new int[2];
        for (int vNode : v) {
            for (int wNode : w) {
                int[] tempResult = shortest(vNode, wNode);
                if (tempResult[0] != -1 && tempResult[0] < shortestLength) {
                    shortestLength = tempResult[0];
                    shortestAncestor = tempResult[1];
                }
            }
        }
        if (shortestLength == Integer.MAX_VALUE) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        result[0] = shortestLength;
        result[1] = shortestAncestor;
        return result;
    }


}