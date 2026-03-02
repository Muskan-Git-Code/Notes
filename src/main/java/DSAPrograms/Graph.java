package DSAPrograms;
import java.util.*;

public class Graph {

    /* Convert matrix to adj list. */

    // int n=4 (4 nodes i.e. 0,1,2,3), int[][] edges= {{0, 1, 5}, {0, 2, 8}, {1, 3, 2}, {2, 3, 4}, {3, 0, 7}};      => 0->[1,5],[2,8]; 1->[3,2]; 2-> [3,4]; 3-> [0,7]
    List<List<List<Integer>>> buildAdjList(int n, int mat[][]){     // O(n)
        List<List<List<Integer>>> adj= new ArrayList<>();
        for(int i=0; i<n; i++){     adj.add(new ArrayList<>()); }

        for(int i=0; i<mat.length; i++){
            int u= mat[i][0];   int v= mat[i][1];   int wt= mat[i][2];
            adj.get(u).add(Arrays.asList(v, wt));
        }
        return adj;
    }


/*----------------------------------------------------------------------------------------------------------------------
                                                    BFS TRAVERSAL
----------------------------------------------------------------------------------------------------------------------*/

    /* Return BFS Traversal for given graph. */

    // Traverse queue, for each value, traverse all its adjacent nodes if not already visited
    List<Integer> bfs(int n, List<List<Integer>> adj){  // bfs(number of nodes, adjacency list[u][v]) // O(V+E)
        Queue<Integer> q= new ArrayDeque<>();       List<Integer> ans= new ArrayList<>();
        int vis[]= new int[n];

        q.add(0);   //starting node
        while(!q.isEmpty()){
            int curr= q.remove();
            if(vis[curr]==1){    continue; }     vis[curr]=1;   ans.add(curr);
            for(int i=0; i<adj.get(curr).size(); i++){
                int nei= adj.get(curr).get(i);      q.add(nei);
            }
        }
        return ans;
    }


    /* Dijkstra Algorithm: Find shortest distance from source vertex S to all other vertex in graph adj[u][v][wt], given edge from u to v have dist wt. */
    // n=3, adj[][][]= {{{1,1}, {2,6}}, {{2,3}, {0,1}}, {{1,3}, {0,6}}}, S=2    => {4,3,0}  i.e. shortest dist

    // Shortest dist with proper weights means min heap while BFS. If node not vis and dis is smaller, then only explore that path
    class Dijkstra{     // O(E+NLogV)
        class Pair{
            int dist;   int node;
            Pair(int dist, int node){   this.dist= dist;    this.node= node; }
        }

        int[] dijkstra(int n, List<List<List<Integer>>> adj, int S) {   // dijkstra(number of nodes, adj[u][v][dist], S);
            PriorityQueue<Pair> pq = new PriorityQueue<>( (p1, p2)->{   return Integer.compare(p1.dist, p2.dist); } );     // (dist, node)
            int vis[] = new int[n];    int dist[]= new int[n];      Arrays.fill(dist, Integer.MAX_VALUE);

            pq.add(new Pair(0, S));     dist[S]=0;
            while(!pq.isEmpty()){
                int curr= pq.peek().node;   int dis= pq.peek().dist;    pq.remove();
                if(vis[curr]==1){  continue; }     vis[curr]=1;

                for(int i=0; i<adj.get(curr).size(); i++){
                    int nei= adj.get(curr).get(i).get(0), neiDis= adj.get(curr).get(i).get(1);
                    if(vis[nei]==0 && dis+ neiDis < dist[nei]){    dist[nei]= dis+neiDis;  pq.add(new Pair(dist[nei], nei)); }
                }
            }
            return dist;
        }
    }


    /* In above problem, Print shortest distance path of target node from source. */

    // track parentNode as well each time.
    class DijkstraPath{     // O((E+NlogV)
        class Pair{
            int dist;   int node;
            Pair(int dist, int node){   this.dist= dist;    this.node= node; }
        }

        List<Integer> dijPath(int n, List<List<List<Integer>>> adj, int S, int target) {   // dijPath(number of nodes, adj[u][v][dist], S, target);
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {    return Integer.compare(a.dist, b.dist); });     // (dist, node)
            int vis[] = new int[n];    int dist[]= new int[n];      Arrays.fill(dist, Integer.MAX_VALUE);
            int parent[]= new int[n];   //for path tracking

            pq.add(new Pair(0, S));     dist[S]=0;  parent[S]=S;
            while(!pq.isEmpty()){
                int curr= pq.peek().node;   int dis= pq.peek().dist;    pq.remove();
                if(vis[curr]==1){  continue; }     vis[curr]=1;

                for(int i=0; i<adj.get(curr).size(); i++){
                    int nei= adj.get(curr).get(i).get(0), neiDis= adj.get(curr).get(i).get(1);
                    if(vis[nei]==0 && dis+ neiDis < dist[nei]){    dist[nei]= dis+neiDis;  parent[nei]= curr;  pq.add(new Pair(dist[nei], nei)); }
                }
            }

            // now construct the path from parent
            List<Integer> path= new ArrayList<>();  int node= target;
            while(parent[node]!= node){     path.add(node);     node= parent[node]; }       path.add(S);
            return path;
        }
    }


    /* Shorter Path faster algorithm (SPFA, variation of Bellenford Algorithm but faster than that): Find shortest distance of all nodes from a source node, given there can be -ve also. */

    // -ve allowed means path (like 6, -8, 2) whose start dis is more can also have overall lesser dis. So, init make node as not vis each time, for revisiting the paths already visited.
    // iseach time, initially during traversal make node as not vis yet. Then traverse. Only in this case, we are taking vis array inside as even after traversing one path, there might be another path through already vis, having -ve values later on.
    int[] shortPath(int n, List<List<List<Integer>>> adj, int S){  // shortPath(number of nodes, adjacency list[u][v][dist], S);    // O(V*E)

        Queue<Integer> q= new ArrayDeque<>();
        int vis[]= new int[n];      int dist[]= new int[n];     Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(S);   dist[S]=0;   //starting node
        while(!q.isEmpty()){
            int curr= q.remove();     vis[curr]=0;

            for(int i=0; i<adj.get(curr).size(); i++){
                int nei= adj.get(curr).get(i).get(0);   int neiDis= adj.get(curr).get(i).get(1);

                if(dist[curr]+ neiDis < dist[nei]){
                    dist[nei]= dist[curr]+ neiDis;
                    if(vis[nei]==0){    q.add(nei);     vis[nei]=1; }
                }
            }
        }
        return dist;
    }


    /* Detect if there is any cycle form while finding the shortest distance of all nodes from source node. */

    // Add ct array, and track if a node is updated more than n times (i.e. number of vertices) then there is a cycle exists.
    int[] shortPathCycle(int n, List<List<List<Integer>>> adj, int S){  // shortPath(number of nodes, adjacency list[u][v][dist], S);    // O(V*E)

        Queue<Integer> q= new ArrayDeque<>();
        int vis[]= new int[n];      int ct[]= new int[n];      int dist[]= new int[n];     Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(0);   dist[S]=0;  vis[S]=1;   ct[S]=1;   //starting node
        while(!q.isEmpty()){
            int curr= q.remove();     vis[curr]=0;
            for(int i=0; i<adj.get(curr).size(); i++){
                int nei= adj.get(curr).get(i).get(0);   int neiDis= adj.get(curr).get(i).get(1);

                if(dist[curr]+ neiDis < dist[nei]){
                    dist[nei]= dist[curr]+ neiDis;
                    if(vis[nei]==0){
                        q.add(nei);     vis[nei]=1;     ct[nei]++;
                        if(ct[nei]>n){  System.out.println("Negative cycle detected");  return new int[0]; }
                    }
                }
            }
        }
        return dist;
    }


    /* Return minimum number of minutes to rote all fresh oranges (in 4 directions) where every orange takes 1 minute to collapse, 0 represents empty cell, 1 represents fresh orange and 2 represents rotten orange. */

    // Collect all rotten oranges. And apply BFS to traverse breadth wise, and rote all side oranges. Vis[] not required as we are changing oranges to 2, so it will not go to same again.
    class RottenOranges {   // O(m*n)
        class Pair{
            int r;  int c;  int t;
            Pair(int r, int c, int t){  this.r= r;  this.c= c;  this.t= t; }
        }

        int rottenOranges(int mat[][]) {
            int m= mat.length, n= mat[0].length, fresh=0, mx=0;     int vis[][]= new int[m][n];
            Queue<Pair> q= new ArrayDeque<>();
            int dir[][]= {{0,1}, {0,-1}, {1,0}, {-1,0}};    // right, left, down, up

            for(int i=0; i<m; i++){     for(int j=0; j<n; j++){     if(mat[i][j]==2){   q.add(new Pair(i, j, 0)); }else if(mat[i][j]==1){   fresh++; } } }      // collect rotten oranges, and ct fresh ones.

            while(!q.isEmpty()){
                Pair curr= q.remove();

                for(int i=0; i<dir.length; i++){
                    int dx= curr.r+ dir[i][0], dy= curr.c+ dir[i][1];
                    if(dx>=0 && dx<m && dy>=0 && dy<n && mat[dx][dy]==1){
                        mat[dx][dy]=2;      q.add(new Pair(dx, dy, curr.t+1));  fresh--;     mx= Math.max(mx, curr.t+1);
                    }
                }
            }
            return (fresh!=0) ? -1 : mx;
        }
    }


    /* Find the distance of nearest cell having 1. */
    // grid[][]= {{0,1,1,0},{1,1,0,0},{0,0,1,1}}    => {{1,0,0,1},{0,0,1,1},{1,1,0,0}}

    // Same as above, here value of grid is time taken i.e. grid[nx][ny]=t+1; And starting nodes are node with val 0


    /* Find the shortest distance in binary maze going through all 4 directions. */
    // gird[][]= {{1,1,1,1},{1,1,0,1},{1,1,0,0},{1,0,0,1}}, src= {0,1}, des= {2,2}  => 3

    class MazeDis {     // O(m*n) as each cell visited atmost once
        class Pair{
            int r;  int c;  int dis;
            Pair(int r, int c, int dis){    this.r= r;  this.c= c;      this.dis= dis; }
        }

        int mazeDis(int mat[][], int src[], int des[]){     // mazeDis(mat, {x1,y1}, {x2, y2});
            int dir[][]= {{0,1}, {0,-1}, {1,0}, {-1,0}};    // right, left, down, up

            int m= mat.length, n= mat[0].length;        int vis[][]= new int[m][n];
            Queue<Pair> q= new ArrayDeque<>();

            q.add(new Pair(src[0], src[1], 0));
            while(!q.isEmpty()){
                Pair curr= q.remove();    int r= curr.r, c= curr.c, dis= curr.dis;
                if(r==des[0] && c==des[1]){   return dis; }
                if(vis[r][c]==1){ continue; }     vis[r][c]=1;

                for(int i=0; i<4; i++){
                    int dx= r+ dir[i][0], dy= c+ dir[i][1];
                    if(dx>=0 && dx<m && dy>=0 && dy<n && mat[dx][dy]==1 && vis[dx][dy]==0){     q.add(new Pair(dx, dy, dis+1)); }
                }
            }
            return -1;
        }
    }


    /* Find minimum effort required to travel from (0, 0) to (m-1, n-1), where effort is max absolute diff between two consecutive cells in the route. */
    // grid[][]= {{1,2,2},{3,8,2},{5,3,5}}	=> 2 {along path 1→3→5→3→5}

    // This time its not a binary maze, its with proper weights. So, in above approach use dijkstra i.e. priorityQueue (dis sorted). And instead of dis+1, push abs(grid[nx][ny]- grid[r][c]) as distance.
    class MazeEffort {     // O(mnlogn)
        class Pair{
            int r;  int c;  int dis;
            Pair(int r, int c, int dis){    this.r= r;  this.c= c;      this.dis= dis; }
        }

        int mazeDis(int mat[][]){     // mazeDis(mat, {x1,y1}, {x2, y2});
            int dir[][]= {{0,1}, {0,-1}, {1,0}, {-1,0}};    // right, left, down, up

            int m= mat.length, n= mat[0].length;
            PriorityQueue<Pair> pq= new PriorityQueue<>( (p1, p2)-> {    return Integer.compare(p1.dis, p2.dis); } );
            int vis[][]= new int[m][n];     int dist[][]= new int[m][n];     for(int i=0; i<m; i++){    Arrays.fill(dist[i], Integer.MAX_VALUE); }

            pq.add(new Pair(0,0,0));    vis[0][0]=1;    dist[0][0]=0;
            while(!pq.isEmpty()){
                Pair curr= pq.remove();   int dis= curr.dis, r= curr.r, c= curr.c;
                if(r==m-1 && c==n-1){   return dis; }
                if(vis[r][c]==1){   continue; }     vis[r][c]=1;
                for(int i=0; i<4; i++){
                    int dx= r+ dir[i][0], dy= c+ dir[i][1];
                    if(dx>=0 && dx<m && dy>=0 && dy<n && vis[dx][dy]==0){
                        int neiDis= Math.max(dis, Math.abs(mat[r][c]-mat[dx][dy]));
                        if(neiDis < dist[dx][dy]){  dist[dx][dy]= neiDis;   pq.add(new Pair(dx, dy, neiDis)); }
                    }
                }
            }
            return -1;
        }
    }


    /* Find the cheaper way to reach from src to dest, taking k stops at max, given roads[fromCity, toCity, cost]. And distance to travel between cities is 1. */
    // roads[][]= {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}}, src=0, dst=3, k=1      => 700 {0→1→3}

    // Proper weights and need to choose path so dijkstra. Also, possible that lesser cost comes with more dis.
    class Roads{    // O(ElogV)
        class Pair{
            int node;   int cost;   int stops;
            Pair(int node, int cost, int stops){    this.node= node;    this.cost= cost;    this.stops= stops; }
        }

        int roadWay(int n, List<List<List<Integer>>> adj, int src, int dst, int k){
            PriorityQueue<Pair> pq= new PriorityQueue<>( (p1, p2)-> {   return Integer.compare(p1.cost, p2.cost); });   // heap acc to min cost

            pq.add(new Pair(src, 0, 0));
            while(!pq.isEmpty()){
                Pair curr= pq.remove();   int node= curr.node, cost= curr.cost, stops= curr.stops;
                if(stops>k){    continue; }
                if(node==dst){  return cost; }

                for(int i=0; i<adj.get(node).size(); i++){
                    int neiNode= adj.get(node).get(i).get(0);   int neiCost= adj.get(node).get(i).get(1);
                    pq.add(new Pair(neiNode, cost+ neiCost, stops+1));
                }
            }
            return -1;
        }
    }


    /* Find number of smallest routes to reach destination through roads[city][dis] going through n cities. */
    // n=7, roads[][]= {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}}	=> 4 {(0→6), (0➝4➝6), (0➝1➝2➝5➝6), (0➝1➝3➝5➝6)}

    // Smallest route ct.
    class RoadRoutes{   // O(ElogV)
        class Pair{
            int node;   int dis;
            Pair(int node, int dis){    this.node= node;    this.dis= dis; }
        }

        int countPaths(int n, List<List<List<Integer>>> adj, int src, int dst){
            int dist[]= new int[n];     Arrays.fill(dist, Integer.MAX_VALUE);
            int ct[]= new int[n];   Arrays.fill(ct, 0);
            PriorityQueue<Pair> pq= new PriorityQueue<>( (p1, p2)->{  return Integer.compare(p1.dis, p2.dis); } );
            pq.add(new Pair(src, 0));   dist[src]=0;    ct[src]=1;
            while(!pq.isEmpty()){
                Pair curr= pq.remove();   int node= curr.node;   int dis= curr.dis;
                for(int i=0; i<adj.get(node).size(); i++){
                    int nei= adj.get(node).get(i).get(0);    int neiDis= adj.get(node).get(i).get(1);
                    if(dis+neiDis < dist[nei]){ dist[nei]= dis+neiDis;      ct[nei]=ct[node];    pq.add(new Pair(nei, dist[nei])); }
                    else if(dis+ neiDis==dist[nei]){    ct[nei]+= ct[node]; }
                }
            }
            return ct[dst];
        }
    }


    /* Find minimum number of cities need to travel from city A, B to reach city D. Consider they can take common path as well for reducing total number of cities. */
    //    A   —   F
    //    |        \
    //    C — E  –- D
    //    |         |
    //    B –- G  — H           => ans =5 {possible path = (AFD, ACED), (BGHD, BCED); so min cities= ABCED}

    // Consider a common point x. required min dis= dis[A->x]+ dis[B->x]+ dis[D->x].
    // Find distance from A, B, D to every other node. Put each city instead of x, and check min distance.


    /* Find the shortest path from source node 0, to all nodes going through only different path color from current path. Given 2 color paths i.e. Red and Blue. */
    // int n=5, int redEdges[][]= {{0,1},{1,2},{2,3}}, blueEdges[][]= {{1,3},{3,4}}     => {0,1,2,1,2}

    // Between 2 specific nodes there can be atmax 2 paths i.e. red/ blue path
    class AlternativePath{      // O(n+e)
        class Pair{
            int node;   int col;  int dis;
            Pair(int node, int col, int dis){   this.node= node;    this.col= col;      this.dis= dis; }
        }

        int[] shortAltPath(int n, int redEdges[][], int blueEdges[][]){
            // adjacency list
            List<List<List<Integer>>> adj= new ArrayList<>();
            for(int i=0; i<n; i++){   adj.add(new ArrayList<>()); }
            for(int i=0; i< redEdges.length; i++){  adj.get(redEdges[i][0]).add(Arrays.asList(redEdges[i][1], 0)); }
            for(int i=0; i< blueEdges.length; i++){  adj.get(blueEdges[i][0]).add(Arrays.asList(blueEdges[i][1], 1)); }

            // BFS Solution (as problem is in 0,1 format so normal BFS will work)
            int dist[]= new int[n];     Arrays.fill(dist, Integer.MAX_VALUE);
            int vis[][]= new int[n][2];     // (node, color) i.e. 0 for red, 1 for blue
            Queue<Pair> q= new ArrayDeque<>();

            q.add(new Pair(0,0,0));     q.add(new Pair(0,1,0));     dist[0]=0;
            while(!q.isEmpty()){
                Pair curr= q.remove();    int node= curr.node, col= curr.col, dis= curr.dis;
                if(vis[node][col]==1){  continue; }     vis[node][col]=1;

                for(int i=0; i<adj.get(node).size(); i++){
                    int neiNode= adj.get(node).get(i).get(0);   int neiCol= adj.get(node).get(i).get(1);
                    if(vis[neiNode][neiCol]==0 && col!=neiCol){
                        q.add(new Pair(neiNode, neiCol, dis+1));  // explore all the paths even if dis is more for alternative col path purpose
                        dist[neiNode]= Math.min(dist[neiNode], dis+1);
                    }
                }
            }

            for(int i=0; i<n; i++){ if(dist[i]==Integer.MAX_VALUE){ dist[i]=-1; }}
            return dist;
        }
    }


    /* Find all the persons who have secret from n persons. Given secret is shared by person 0 to firstPerson. And then whenever a meeting (personi, personj, time) happen. */
    // n= 4, meetings= [[3,1,3],[1,2,2],[0,3,3]], firstPerson= 3	=> {0,1,3}

    // if pi meets pj only after pi have secret, then secret is shared. And all nodes who are visited during this process have secret in end. Priority needed based on time.
    class Secret{   // O(ElogV)
        class Pair{
            int node;   int time;
            Pair(int node, int time){   this.node= node;    this.time= time; }
        }

        List<Integer> secretShared(int n, int meeting[][], int fp){
            // adjacency list
            List<List<List<Integer>>> adj= new ArrayList<>();     // pi -> (pj1, t), (pj2, t)
            for(int i=0; i< n; i++){   adj.add(new ArrayList<>()); }
            for(int i=0; i< meeting.length; i++){   adj.get(meeting[i][0]).add(Arrays.asList(meeting[i][1], meeting[i][2]));    adj.get(meeting[i][1]).add(Arrays.asList(meeting[i][0], meeting[i][2])); }

            // BFS Solution
            PriorityQueue<Pair> pq= new PriorityQueue<>( (p1, p2)->{    return Integer.compare(p1.time, p2.time); } );  // node, time
            int vis[]= new int[n];      List<Integer> ans= new ArrayList<>();

            pq.add(new Pair(0, 0));     pq.add(new Pair(fp, 0));
            while(!pq.isEmpty()){
                Pair curr= pq.remove();   int node= curr.node;    int time= curr.time;
                if(vis[node]==1){   continue; }     vis[node]=1;

                for(int i=0; i<adj.get(node).size(); i++){
                    int neiNode= adj.get(node).get(i).get(0);   int neiTime= adj.get(node).get(i).get(1);
                    if(vis[neiNode]==0 && neiTime > time){     pq.add(new Pair(neiNode, neiTime)); }
                }
            }

            for(int i=0; i<n; i++){     if(vis[i]==1){  ans.add(i); }}      return ans;
        }
    }


    /* Print all the numbers from 1 to N, where adjacent digits differ by 1. */
    // N= 25	=> {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21, 23}

    // Number diff by 1. Means doing +1, -1 on adjacent digit.
    List<Integer> adjDigit(int n){  // O(n)
        Queue<Integer> q= new ArrayDeque<>();   List<Integer> ans = new ArrayList<>();  ans.add(0);
        for(int i=1; i<=9; i++){    q.add(i); }

        while(!q.isEmpty()){
            int curr= q.remove();
            if(curr>n){ continue; }     ans.add(curr);

            int lastDig= curr%10;
            if(lastDig>0){  q.add( (curr*10+(lastDig-1)) ); }
            if(lastDig<9){  q.add( (curr*10+ (lastDig+1)) ); }
        }
        return ans;
    }


    /* Return min number of steps to transform beginWord to endWord, by changing one character each time through wordList. */
    // beginWord = "hit", endWord = "cog", wordList = {"hot","dot","dog","lot","log","cog"}		=> 5 ("hit"→"hot"→"dot"→"dog"→cog")

    // Change ith char one by one, if present in set then remove as vis and also add it in queue. And also steps need to be updated after each level i.e. after next word is finalized.
    int BeginToEnd(String beginWord, String endWord, String wordList[]){    // O(n*(WordLength^2))
        HashSet<String> set= new HashSet<>(Arrays.asList(wordList));   int steps=1;
        Queue<String> q= new ArrayDeque<>();    q.add(beginWord);
        while(!q.isEmpty()){
            int qSize= q.size();
            for(int qs=0; qs<qSize; qs++){
                StringBuilder curr= new StringBuilder(q.remove());
                if( curr.toString().equals(endWord) ){   return steps; }

                for(int i=0; i<curr.length(); i++){
                    char currCh= curr.charAt(i);
                    for(char c='a'; c<='z'; c++){
                        if(c==currCh){  continue; }
                        curr.setCharAt(i, c);     String nei= curr.toString();
                        if(set.remove(nei)){    q.add(nei); }
                    }
                    curr.setCharAt(i, currCh);
                }
            }
            steps++;
        }
        return -1;
    }



/*----------------------------------------------------------------------------------------------------------------------
                                                    DFS TRAVERSAL
----------------------------------------------------------------------------------------------------------------------*/

    /* Return DFS Traversal for given graph. */

    void dfs(int x, List<List<Integer>> adj, int vis[], List<Integer> ans){      // dfs(start node, adjacency list, vis, ans);       //O(V+E)
        vis[x]=1;    ans.add(x);      // most of the logic part of dfs is here.

        for(int i=0; i<adj.get(x).size(); i++){
            int nei= adj.get(x).get(i);
            if(vis[nei]==0){   dfs(nei, adj, vis, ans); }
        }
    }


    /* Return number of provinces/ connected component. */

    // count total number of nodes which need to visit through main function
    int province_main(int n, List<List<Integer>> adj){
        int ct=0;   int vis[]= new int[n];
        for(int i=0; i<n; i++){ if(vis[i]==0){  dfs(i, adj, vis, new ArrayList<>());    ct++; }}
        return ct;
    }


    /* Return length of each connected component */

    // In DFS() fn, in ans, do ans[0]++ each time. And then in main we have length of connected component each time.


    /* In 0-1 matrix, replace all 0's into 1, which are surrounded by 1. */

    // Apply DFS for all border 0, and mark them as vis. Then all 0, who are not vis yet, mark them as 1.


    /* Return all numbers in range 1 to n, sorted in lexographical order. */
    // n=13     => {1,10,11,12,13,2,3,4,5,6,7,8,9}

    // print numbers starting from 1, then 2 then 3, etc.
    // for(int i=1;i<10;i++){  solveRec(i,n, ans); }
    public void lexOrder(int x, int n, List<Integer> ans){  // TC: O(n)
        if(x>n)return;
        ans.add(x);
        for(int i=0; i<=9; i++){    lexOrder(x*10+i, n, ans); }
    }


    /* Find possible ways to travel from (0,0) to (m-1, n-1) in m*n matrix, given you can move in all 4 direction, but can't move in cells with value 0, and can visit each cell once. */

    // for number of ways, always use backtracking method, i.e. add value before recursion but remove after recursion, for different paths.
    // int ct[]= new int[1];    dfsWays(0,0,mat,vis,ct);
    void dfsWays(int x, int y, int mat[][], int vis[][], int ct[]){     // O(3^(m*n)) as 4 directions.
        vis[x][y]=1;
        if(x== mat.length-1 && y==mat[0].length-1){     ct[0]++; return; }

        int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // up, down, left, right
        for(int i=0; i<4; i++){
            int dx= x+ dir[i][0];   int dy= y+ dir[i][1];
            if(dx>=0 && dx<mat.length && dy>=0 && dy<mat[0].length && vis[dx][dy]==0 && mat[dx][dy]!=0){    dfsWays(dx, dy, mat, vis, ct); }
        }
        vis[x][y]=0;
    }


    /* Find maximum coins possible to collect when travel from (0,0) to (m-1, n-1) in m*n matrix, given you can move in all 4 direction, but can't move in cells with value 0, and can visit each cell once. */
    // grid[][]= [[0,6,0],[5,8,7],[0,9,0]], src= (2, 1)	=> 24 {9->8->7}

    void dfsMaxCoin(int x, int y, int mat[][], int vis[][], int ct[], int mx[]){     // O(3^(m*n)) as 4 directions.
        vis[x][y]=1;    ct[0]+= mat[x][y];
        if(x== mat.length-1 && y==mat[0].length-1){     mx[0]= Math.max(mx[0], ct[0]); return; }

        int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // up, down, left, right
        for(int i=0; i<4; i++){
            int dx= x+ dir[i][0];   int dy= y+ dir[i][1];
            if(dx>=0 && dx<mat.length && dy>=0 && dy<mat[0].length && vis[dx][dy]==0 && mat[dx][dy]!=0){    dfsMaxCoin(dx, dy, mat, vis, ct, mx); }
        }
        vis[x][y]=0;    ct[0]-= mat[x][y];
    }


    /* Find minimum color required to color all n nodes of a graph, such that no two adjacent nodes have same color. */

    // Each time starting from node 0, check if possible to color in k colors.
    class MColoring{
        int mainCol(int n, List<List<Integer>> adj){
            for(int k=1; k<n; k++){
                int col[]= new int[n];      Arrays.fill(col, -1);
                if(dfsColor(0, adj, n, col, k)){    return k; }
            }
            return n;
        }

        // coloring with k colors is possible only if none of its nei is colored with same col
        boolean dfsColor(int x, List<List<Integer>> adj, int n, int col[], int k){
            if(x==n){   return true; }

            for(int c=0; c<k; c++){
                boolean safe= true;
                for(int i=0; i<adj.get(x).size(); i++){
                    int nei= adj.get(x).get(i);
                    if(col[nei]==c){    safe=false; break; }
                }

                if(safe){
                    col[x]=c;
                    if(dfsColor(x+1, adj, n, col, k)){  return true; }
                    col[x]=-1;
                }
            }
            return false;
        }
    }


    /* Given n equations, and values to those. Return the answer to the given query, by multiplying queries included. */
    // equations= [["a","b"],["b","c"]], values= [2.0,3.0], query= ["a","c"]	=> 6

    // Make adj graph with weights as values. Then calc value as asked.
    class Equations{    // O(V+E)
        class Pair{
            int node;  double val;
            Pair(int node, double val){   this.node= node;    this.val= val; }
        }

        double dfs_main(char eq[][], double val[], int q[]){
            List<List<Pair>> adj= new ArrayList<>();    int vis[]= new int[26];  double ans= 1.0;
            for(int i=0; i<26; i++){ adj.add(new ArrayList<>()); }
            for(int i=0; i<eq.length; i++){ int u= eq[i][0]-'a', v= eq[i][1]-'a';   double wt= val[i];      adj.get(u).add(new Pair(v, wt));     adj.get(v).add(new Pair(u, 1/wt)); }
            return dfsEq(q[0]-'a', adj, vis, q, ans);
        }

        double dfsEq(int x, List<List<Pair>> adj, int vis[], int q[], double ans){
            vis[x]=1;
            if(x== (q[1]-'a')){    return ans; }

            double res=-1.0;
            for(int i=0; i<adj.get(x).size(); i++){
                int nei= adj.get(x).get(i).node;    double neiVal= adj.get(x).get(i).val;
                if(vis[nei]==0){    double sub= dfsEq(nei, adj, vis, q, ans*neiVal);     if(sub!=-1.0){  res= sub; } }
            }
            return res;
        }
    }


    /* Find all permutations of nums[]. */
    // nums[]= [1,2,3]	=> [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

    // Similiar to explore diff ways. Fix first value and explore.
    class Permutation{  // O(n!)
        List<List<Integer>> dfs_main(int nums[]){
            int n= nums.length;     int vis[]= new int[n];      List<List<Integer>> ans= new ArrayList<>();
            for(int i=0; i<n; i++){     dfs(i, nums, vis, new ArrayList<>(), ans); }    return ans;
        }

        void dfs(int x, int nums[], int vis[], List<Integer> tmp, List<List<Integer>> ans){
            vis[x]=1;   tmp.add(nums[x]);
            if(tmp.size()==nums.length){    ans.add(new ArrayList<>(tmp));   return; }

            for(int i=0; i<nums.length; i++){
                if(vis[i]==0){  dfs(i, nums, vis, tmp, ans); }
            }
            vis[x]=0;   tmp.remove(tmp.size()-1);
        }
    }


    /* Travelling salesman problem: Find shortest path to traverse all n nodes of graph adj[u][v][dis] and return to starting node. */

    // This is DFS i.e. take a bitmask (showing if a node vis or not). Apply DP for optimal
    int tsp(int curr, int mask, List<List<List<Integer>>> adj, int n){     // O(n^2 * 2^n)      // tsp(0, 1, adj, n)
        if(Integer.bitCount(mask) == n){    // if all nodes vis then return to starting point if edge exist, else route not possible
            for(int i=0; i<adj.get(curr).size(); i++) {
                int nei= adj.get(curr).get(i).get(0);       int neiDis= adj.get(curr).get(i).get(1);
                if(nei==0) {     return neiDis; }    // assuming 0 as starting node
            }
            return (int)1e9;
        }

        int mn= (int)1e9;
        for(int i=0; i<adj.get(curr).size(); i++){
            int nei= adj.get(curr).get(i).get(0);   int neiDis= adj.get(curr).get(i).get(1);
            if( (mask & (1<< nei)) ==0) {   // check if nei not visited yet
                int cost= neiDis+ tsp(nei, (mask| (1<<nei)), adj, n);       mn= Math.min(mn, cost);
            }
        }
        return mn;
    }


    /* Print path also for the above TSP. */

    // Track the parent node, and build path through parent. For building path, starting with startNode, go to parent node each time till all nodes are not vis.
    class TSPPath{

        List<Integer> tspPath(int startNode, List<List<List<Integer>>> adj, int n){
            int parent[][]= new int[n][(int) Math.pow(2, n)];   // (currNode, currBitMask)
            tsp(0, 1, adj, n, parent);

            List<Integer> path= new ArrayList<>();      // now construct the path from parent
            int curr= startNode;    int mask= (int)Math.pow(2, startNode);  // the starting point where only startNode is vis.

            path.add(curr);
            while(Integer.bitCount(mask)<n){    int nei= parent[curr][mask];    path.add(nei);      mask |= (1<<nei);   curr= nei; }
            path.add(startNode);

            return path;
        }

        int tsp(int curr, int mask, List<List<List<Integer>>> adj, int n, int parent[][]){     // O(n^2 * 2^n)      // tsp(0, 1, adj, n)
            if(Integer.bitCount(mask) == n){
                for(int i=0; i<adj.get(curr).size(); i++) {
                    int nei= adj.get(curr).get(i).get(0);       int neiDis= adj.get(curr).get(i).get(1);
                    if(nei==0) {     return neiDis; }
                }
                return (int)1e9;
            }

            int mn= (int)1e9;
            for(int i=0; i<adj.get(curr).size(); i++){
                int nei= adj.get(curr).get(i).get(0);   int neiDis= adj.get(curr).get(i).get(1);
                if( (mask & (1<< nei)) ==0) {
                    int cost= neiDis+ tsp(nei, (mask| (1<<nei)), adj, n, parent);
                    if(cost< mn){   mn= cost;   parent[curr][mask]= nei; }      // add parent node
                }
            }
            return mn;
        }
    }


    /* Detect cycle in an undirected graph. */

    // During DFS, if you visit a neighbor that is already visited and not the parent, a cycle exists. Example: 1->2->3->1, where parent of 3 is 2, but it goes back to 1 which is already visited.
    boolean undirCycle(int x, int par, List<List<Integer>> adj, int vis[]){ // undirCycle(i, -1, adj, vis);     // O(V+E)
        vis[x]=1;
        for(int i=0; i<adj.get(x).size(); i++){
            int nei= adj.get(x).get(i);
            if(vis[nei]==0){    if(undirCycle(nei, x, adj, vis)){   return true; } }
            else if(nei!=par){  return true; }  // if node already vis, and nei is not parent i.e. cycle
        }
        return false;
    }


    /* Detect cycle in a directed graph. */

    // directed graph, so possible that parent is visited but during another path. So, check if vis, and is during same path then cycle.
    boolean dirCycle(int x, int path[], List<List<Integer>> adj, int vis[]){
        vis[x]=1;   path[x]=1;
        for(int i=0; i<adj.get(x).size(); i++){
            int nei= adj.get(x).get(i);
            if(vis[nei]==0){    if(dirCycle(nei, path, adj, vis)){  return true; } }
            else if(path[nei]==1){  return true; }
        }
        path[x]=0;      return false;
    }



/*----------------------------------------------------------------------------------------------------------------------
                                      TOPOLOGICAL SORT: Sort vertices of directed acyclic graph.
----------------------------------------------------------------------------------------------------------------------*/

    /* Find topological sort of given directed acyclic graph (DAG). */

    // Simple DFS, then return values in reverse order as leaf node values are the ones which comes first in sorted order.
    // topoSort(start node, adjacency list, vis, ans);      Collections.reverse(ans);
    void topoSort(int x, List<List<Integer>> adj, int vis[], List<Integer> ans){      //        //O(V+E)
        vis[x]=1;

        for(int i=0; i<adj.get(x).size(); i++){
            int nei= adj.get(x).get(i);
            if(vis[nei]==0){   topoSort(nei, adj, vis, ans); }
        }
        ans.add(x);
    }


    /* Course Schedule: Find order if possible to complete all courses, given you need to complete course b before course a for every {a,b}. */
    // n=4, a[][]= {{1,0}, {2,0}, {3,1}, {3,2}}     => {0,2,1,3}

    // To check if possible i.e. check if graph is acyclic or not. If acyclic then return ans.
    List<Integer> course_main(int n, int a[][]){
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0; i<n; i++){ adj.add(new ArrayList<>()); }
        for(int i=0; i<a.length; i++){  adj.get(a[i][1]).add(a[i][0]); }    // as b should be completed before a, so adj is b->a.

        int vis[]= new int[n];      int path[]= new int[n];     List<Integer> ans= new ArrayList<>();
        // Detect if cycle
        for(int i=0; i<n; i++){     if(vis[i]==0){  if(dirCycle(i, path, adj, vis)){    return new ArrayList<>(); }}}

        // apply toposort
        Arrays.fill(vis, 0);    // reset vis array
        for(int i=0; i<n; i++){ if(vis[i]==0){  topoSort(i, adj, vis, ans); }}
        Collections.reverse(ans);
        return ans;
    }


    /* Alien Dictionary: Find the order of words according to alien dictionary having k starting alphabets of standard dictionary. */
    //  dict[]= {"baa","abcd","abca","cab","cad"}, k=4	=> "bdac"

    // For each adjacent pair of words, create a directed edge from the first differing character in word1 → word2. Then perform topological sort.
    String dict_main(String dict[], int k){
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0; i<k; i++){ adj.add(new ArrayList<>()); }
        for(int i=1; i< dict.length; i++){
            String w1= dict[i-1];   String w2= dict[i];
            for(int j=0; j<Math.min(w1.length(), w2.length()); j++){    if(w1.charAt(j)!=w2.charAt(j)){     adj.get(w1.charAt(j)-'a').add(w2.charAt(j)-'a');    break; }}
        }

        int vis[]= new int[k];      List<Integer> ans= new ArrayList<>();   StringBuilder res= new StringBuilder();
        for(int i=0; i<k; i++){ if(vis[i]==0){  topoSort(i, adj, vis, ans); }}
        Collections.reverse(ans);
        for(int i=0; i<k; i++){ res.append(ans.get(i)+'a'); }       return res.toString();
    }


    public static void main(String args[]){

    }
}
