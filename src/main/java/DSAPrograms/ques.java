package DSAPrograms;

import java.util.*;

public class ques {

    public int subarrayBitwiseORs(int[] arr) {
        int n= arr.length;
        HashSet<Integer> set= new HashSet<>();

        for(int i=0; i<n; i++){
            int ct=0;
            for(int j=i; j<n; j++){
                ct|= arr[i];
                set.add(ct);


                System.out.println(ct+" "+ arr[i]);
            }
            System.out.println();
        }
        return set.size();
    }

    public static void main(String args[]){
        ques q= new ques();
        int arr[]= {1,1,2};

        q.subarrayBitwiseORs(arr);

    }

}
