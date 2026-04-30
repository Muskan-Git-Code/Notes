package DSAPrograms;
import java.util.*;

public class ques {

    String perform(String num1, String num2, String opr1, String opr2){
        int n1= Integer.parseInt(num1);     int n2= Integer.parseInt(num2);
        if(opr1.equals("-")){  n1= -n1; }
        if(opr2.equals("-")){   n2= -n2; }

        System.out.println(n1+ " "+ n2);
        int ix= Integer.parseInt(num1) + Integer.parseInt(num2);

        return String.valueOf(ix);
    }

    public int calculate(String s) {
        int n= s.length();
        Stack<String> st= new Stack<>();

        for(int i=0; i<n; i++){
            String curr= ""+ s.charAt(i);
            // System.out.println(curr);
            if(curr.equals(" ")){  continue; }
            else if(curr.equals("(") ){  st.push(curr); }
            else if(curr.equals("+") || curr.equals("-")){  st.push(curr); }

            else if(curr== ")"){
                // pop all values till opening bracket, perform operations and pop opening bracket and push ans to stack

                while(st.size()>1){
                    String num2= "0", num1= "0", opr1= "+", opr2= "+";
                    System.out.println(st);

                    if(!st.isEmpty() && !st.peek().equals("(") ){
                        num2= st.pop();
                    }
                    if(!st.isEmpty() && !st.peek().equals("(") ){
                        opr2= st.pop();
                    }
                    if(!st.isEmpty() && !st.peek().equals("(") ){
                        num1= st.pop();
                    }
                    if(!st.isEmpty() && !st.peek().equals("(") ){
                        opr1= st.pop();
                    }

                    st.add(perform(num1, num2, opr1, opr2));
                    st.pop();   // opening bracket
                    System.out.println(st);
                }

            }

            else if(Character.isDigit(s.charAt(i))){
                // make num
                StringBuilder num= new StringBuilder("");
                while(i<n && Character.isDigit(s.charAt(i)) ){
                    num.append(s.charAt(i));
                    i++;
                }
                st.add(num.toString());    i--;
            }

        }

        // System.out.println(st);

        while(st.size()>1){
            String num2= "0", num1= "0", opr1= "+", opr2= "+";
            if(!st.isEmpty() ){
                num2= st.pop();
            }
            if(!st.isEmpty() ){
                opr2= st.pop();
            }
            if(!st.isEmpty() ){
                num1= st.pop();
            }
            if(!st.isEmpty() ){
                opr1= st.pop();
            }

            st.add(perform(num1, num2, opr1, opr2));
            // System.out.println(st);
        }

        if(st.size()==1){   return Integer.parseInt(st.pop()); }
        return 0;
    }

    public static void main(String args[]){
        ques q= new ques();

        String s= "(1+(4+5+2)-3)+(6+8)";

        q.calculate(s);

        String x= "65";

        System.out.println( (int)x.charAt(0) );



    }

}
