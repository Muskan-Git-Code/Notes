package DSAPrograms;
import java.util.*;

public class ques {

    public int[] avoidFlood(int[] rains) {
        int j=0, i=0, n=rains.length;
        HashMap<Integer, Integer> imap= new HashMap<>();
        HashSet<Integer> set= new HashSet<>();
        int ans[]= new int[n];  Arrays.fill(ans, -1);

        while(j<n){

            int flag=0;
            if(rains[j]==0){   ans[j]=1; j++; continue; }

            if(imap.getOrDefault(rains[j], 0) >= 1){

                while(i<j && rains[i]!=0){
                    set.add(rains[i]);
//                    imap.put(rains[i], imap.get(rains[i])-1);
                    i++;
                }


                if(rains[i]==0){
                    if(set.contains(rains[j])){
                        imap.put(rains[j], 0);  ans[i]=rains[j];    set.remove(rains[j]);
                        flag=1;
                    }
                    else{
                        return new int[0];
                    }
                    i++;
                }

                if(i==j && flag==0){
                    return new int[0];
                }
            }

//            if(flag==0)
            imap.put(rains[j], imap.getOrDefault(rains[j], 0)+1 );
            j++;
        }

        return ans;
    }

    public static void main(String args[]){
        ques q= new ques();
        int nums[]= {1,0,2,0,2,1}, k=6;

        System.out.println(Arrays.toString(nums));

        StringBuilder sb= new StringBuilder("hello");
//        q.avoidFlood(nums);

    }

}
