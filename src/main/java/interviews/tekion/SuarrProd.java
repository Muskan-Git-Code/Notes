package interviews.tekion;


public class SuarrProd {

    int subProd(int nums[], int k){
        // op, cond, calc

        int ct=0, n= nums.length, ans=1, i=0;

        for(int j=0; j<n; j++){
            ans*= nums[j];

            while(i<=j && ans>= k){
                ans/= nums[i];  i++;
            }

            ct+= j-i+1;
        }

        return ct;
    }


    public static void main(String args[]){

        SuarrProd prod= new SuarrProd();

        int nums[]= {10,5,2,6}, k=100;
        int ans= prod.subProd(nums, k);
        System.out.println(ans);

        int nums2[]= {1,2,3};    k=0;
        ans= prod.subProd(nums2, k);
        System.out.println(ans);
    }
}
