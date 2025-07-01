package org.example;

import java.util.*;

public class ques {


    class Pair{
        int x;   int y;
        Pair(int x, int y){   this.x= x;    this.y=y; }
    }

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int ix=0, n= nums.length;
        Set<Integer> set= new TreeSet<>();

        for(int i=0; i<n; i++){
            if(nums[i]==key){
                ix= i;  set.add(i);

                // ix-k, ix+k all numbers in set
                int val= Math.abs(ix-k);
                for(int j=val; j<ix; j++){
                    set.add(j);
                }

                val= (ix+k);
                for(int j=ix; j<Math.min(val, n); j++){
                    set.add(j);
                }
            }
        }

        List<Integer> ans= new ArrayList<>();
        for(int i: set){
            ans.add(i);
        }
        return ans;

    }

    public static void main(String args[]){
        int[][] a = {
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };

        int m = a.length, n = a[0].length;

        int nums[]= {3,4,9,1,3,9,5};

        ques q= new ques();
        List<Integer> ans = q.findKDistantIndices(nums, 9, 1);    // both start at (0,0)

        System.out.println(ans);    // should print: 21
    }

}
