package tekion;

import DSAPrograms.ques;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottenOranges {

    // Rotten Oranges
    class Pair{
        int x, y;   int time;
        Pair(int x, int y, int time){
            this.x= x;  this.y= y;  this.time= time;
        }
    }

    int rottenOranges(int grid[][]){
        int m= grid.length, n= grid[0].length, fresh=0, mx=0;
        Queue<Pair> q= new ArrayDeque<>();   // (i, j, time)
        int dx[]= {-1, 1, 0, 0}; int dy[]= {0, 0, -1, 1};     // up, down, left, right

        // check for all rotten oranges
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++) {
                if (grid[i][j]==2){
                    q.add(new Pair(i, j,0));
                }
                else if(grid[i][j]==1){
                    fresh++;
                }
            }
        }

        if(fresh==0){   return 0; }

        // check if
        while(!q.isEmpty()){

            Pair curr= q.remove();

            for(int i=0; i<4; i++){
                int nx= curr.x + dx[i], ny= curr.y + dy[i];     int t= curr.time;
                if(nx>=0 && nx<m && ny>=0 && ny<n && grid[nx][ny]==1){
                    // then rote this orange at time t+1

                    q.add(new Pair(nx, ny, t+1));   mx= Math.max(mx, curr.time+1);    grid[nx][ny]=2;     fresh--;
                }
            }
        }

        if(fresh!=0){   return -1; }

        return mx;
    }

    public static void main(String[] args){

        RottenOranges q= new RottenOranges();

        int grid[][]= {{2,1,1},{1,1,0},{0,1,1}};
        int ans= q.rottenOranges(grid);
        System.out.println(ans);

        int grid2[][]= {{2,1,1}, {0,1,1}, {1,0,1}};
        ans= q.rottenOranges(grid2);
        System.out.println(ans);

        int grid3[][]= {{0,2}};
        ans= q.rottenOranges(grid3);
        System.out.println(ans);
    }
}
