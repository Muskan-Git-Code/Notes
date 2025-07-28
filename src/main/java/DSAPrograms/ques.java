package DSAPrograms;

import java.util.*;

public class ques {

    static int maximumGain(String s, int x, int y) {

        Stack<Character> st= new Stack<>();

        String mxStr="";    StringBuilder sb= new StringBuilder(s);     int n= s.length(), res=0;
        if(x>y){    mxStr= "ab"; }
        else{   mxStr= "ba"; }

        for(int i=0; i<n; ){
            while(!st.isEmpty() && i<n && st.peek() == mxStr.charAt(0) && s.charAt(i)==mxStr.charAt(1)){
                st.pop();   i++;
                res += (x > y) ? x : y;
            }

            if(i<n){
                st.push(s.charAt(i));
                i++;
            }
        }

        System.out.println(res);

        s= "";
        while(!st.isEmpty()){
            s+= st.pop();
        }

        System.out.println(s);

        mxStr= (mxStr=="ab") ? "ba" : "ab";
        for(int i=0; i<s.length(); ){
            while(!st.isEmpty() && i<n && st.peek() == mxStr.charAt(0) && s.charAt(i)==mxStr.charAt(1)){
                st.pop();   i++;
                res += (x > y) ? y : x;
            }

            if(i<n){
                st.push(s.charAt(i));
                i++;
            }
        }

        return res;

    }

    interface Animal {  void makeSound();  void sleep(); }
    abstract static class Cat implements Animal {
        public void makeSound() {   System.out.println("Meow"); }
        public void sleep(){    System.out.println("zz"); }
    }




    public static void main(String args[]){

        Animal a=new Cat(){};     a.makeSound();

        String s= "cdbcbbaaabab";
        int x=4, y=x;
        System.out.println(x==y);

//        System.out.println(maximumGain(s,x,y));

//        String a= "hi", b= new String("hi");
//        System.out.println(a==b);


    }

}
