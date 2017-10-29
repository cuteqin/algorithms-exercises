package Ex04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Administrator on 2017/10/29.
 */
//找出图中起点s到其他顶点最短路径
public class BreadFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    public BreadFirstSearch(Graph G,int s){
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        this.s=s;
        bfs(G,s);
    }
    public void bfs(Graph G,int s){
        Queue<Integer> queue=new Queue();
        marked[s]=true;
        queue.enqueue(s);
        while (!queue.isEmpty()){
            int v=queue.dequeue();
            for(int w:G.adj(v)){
                if(!marked[w]){
                    edgeTo[w]=v;
                    marked[w]=true;
                    queue.enqueue(w);
                }
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path=new Stack<>();
        for(int x=v;x!=s;x=edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }
    public static void main(String[] args) {
        Graph G=new Graph(new In(args[0]));
        int s=Integer.parseInt(args[1]);
        DepthFirstPaths paths=new DepthFirstPaths(G,s);
        for(int v=0;v<G.V();v++){
            System.out.println(s+" to "+v+" : ");
            if (paths.hasPathTo(v)){
                for(int x:paths.pathTo(v)){
                    if(x==s) StdOut.print(x);
                    else StdOut.print("-"+x);
                }
                StdOut.println();
            }

        }
    }
}
