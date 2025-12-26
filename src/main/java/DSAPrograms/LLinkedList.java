package DSAPrograms;
import java.util.*;

public class LLinkedList {

    static class Node{
        int val;    Node next;
        Node(int val){  this.val= val; this.next=null; }
    }


    /* Insert, delete, update value at specific pos */

    // Iterate till pos-1, add tmpNode.
    static void insertVal(Node list, int value, int pos){
        Node tmp= new Node(value);  Node p= list;
        if(pos==1){     tmp.next= list;   list= tmp;    return; }

        for(int i=1; i<pos-1; i++){
            if(p==null){    System.out.println("Position out of range");    return; }
            p= p.next;
        }
        tmp.next= p.next;   p.next= tmp;
    }

    static void deleteVal(Node list, int pos){
        Node p= list;
        if(list == null){  return; }
        if(pos==1){ list = list.next; return; }

        for(int i=1; i<pos-1; i++){
            if(p==null || p.next==null){    System.out.println("Position out of range");    return; }
            p= p.next;
        }
        p.next= p.next.next;
    }

    void updateVal(Node list, int value, int pos){
        Node p= list;
        for(int i=1; i<pos-1; i++){
            if(p==null || p.next==null){    System.out.println("Position out of range");    return; }
            p= p.next;
        }
        p.val= value;
    }


    /* Sort elements of list. */

    static void sort(Node list){
        Node p= list;
        for(Node i=p; i!=null; i= i.next){
            for(Node j=i.next; j!=null; j= j.next){
                if(i.val > j.val){  int tmp=i.val;  i.val=j.val;    j.val=tmp; }
            }
        }
    }


    /* Search if value present in linked list. */
    static void search(Node list, int value){
        Node p= list;  int pos=1;
        while(p!=null){
            if(p.val==value){   System.out.println("Element found at pos: "+ pos); }
            p= p.next;
            pos++;
        }
        System.out.println("Element not found in list");
    }


    /* Print elements of list. */
    static void display(Node list){
        Node p= list;
        while(p!=null){
            System.out.println(p.val+ "->");
            p= p.next;
        }
        System.out.println("NULL");
    }


    /* Reverse the given list. */
    // list= {1,2,3,4,5}    => 5,4,3,2,1

    // each time next node for curr is prev. Then for moving further prev= curr, curr=next, next=next.next
    static Node rev(Node list){     // TC: O(n)
        if(list==null)  return list;
        Node prev=null, curr= list, next= list.next;

        while(curr!=null){
            curr.next= prev;
            prev= curr;     curr= next;     if(next!=null){ next= next.next; }
        }
        list= prev;
        return list;
    }


    /* Reverse list between given left and right. */
    // list= {1,2,3,4,5}, left=2, right=4       => {1,4,3,2,5}

    // prev= left point, then do normal reversal till pos<=right
    static Node revBetween(Node list, int left, int right){
        if(list==null || left==right)   return list;

        Node prev= null, curr= list, next= curr.next;

        for(int i=0; i<left-1 && curr!=null; i++){  prev= curr;     curr= curr.next; }

        Node last= prev, newEnd= curr;      next= curr.next;
        for(int i=left; i<=right && curr!=null; i++){
            curr.next= prev;
            prev= curr;     curr= next;     if(next!=null){ next= next.next; }
        }

        if(last!=null){ last.next=prev; }else{  list= prev; }
        newEnd.next= curr;
        return list;
    }


    /* Check if list is palindrome. */
    // list= {1,2,3,2,1}    => true

    // reverse through middle i.e. (1,2,1,2,3), and check if list through new mid and start is same then yes, else no.


    /* Rearrange the given list in form l0, ln, l1, ln-1, .. */
    // list= {1,2,3,4,5,6,7}    => {1,7,2,6,3,5,4}

    // reverse from mid, and this time instead of compare add values in between i.e. {1,2,3,4}, {7,6,5} => {1,7,2,6,3,5,4}


    /* Merge 2 sorted linked list in sorted way. */
    static Node merge(Node l1, Node l2){
        Node ans= new Node(0);  Node p= ans;
        while(l1!=null && l2!=null){
            if(l1.val < l2.val){    p.next= l1;     l1= l1.next; }
            else{   p.next= l2;     l2= l2.next; }
            p= p.next;
        }
        p.next = (l1!=null) ? l1 : l2;
        return ans.next;
    }


    /* Find intersection node of 2 linkedlist.  */
    // listA = [1,9,1,2,4], listB = [3,2,4]	=> 2

    // Traverse both list. If anyone reach NULL, point it to opposite list, so that the diff between list size will automatically be nullify.
    static Node intersectingNode(Node l1, Node l2){
        Node p1= l1, p2= l2;
        while(p1!=p2){
            p1= p1.next;    p2= p2.next;
            if(p1==null){   p1= l2; }       if(p2==null){   p2= l1; }
        }
        return p1;
    }


    /* Copy a linked list to new memory location. */

    // taking new node each time while iterating.
    static Node copyList(Node l1){
        Node ans= new Node(0);  Node p= ans;
        while(l1 != null){
            p.next= new Node(l1.val);   p= p.next;
        }
        return ans.next;
    }


    /* Delete given node in linked list. (Starting point is not given) */

    // copy nextNode value to current, and then delete nextNode instead
    static void deleteGivenNode(Node node){
        node.val= node.next.val;
        node.next= node.next.next;
    }


    /* Find middle element of given linkedlist. */

    // use slow and fast (move by 2x speed) pointer. If fast reaches null then slow will reach halfway till then.
    static Node middle(Node l1){    // O(n)
        Node slow=l1, fast= l1;
        while(fast!=null && fast.next!=null){
            slow= slow.next;    fast= fast.next.next;
        }
        return slow;
    }


    /* Return length and starting point of cycle in linkedlist. */

    // detect cycle: taking fast/ slow pointer, if they crash at same point means cycle.
    static void detectCycle(Node l1){   // O(n)
        Node slow= l1, fast= l1;
        while(fast!=null && fast.next!=null){
            slow= slow.next;    fast= fast.next.next;
            if (slow == fast) {     System.out.println("Cycle is detected");  break; }
        }

        // starting point of cycle: if starting point is equal to slow pointer that's the start point
        Node p= l1;
        while(p != slow){   p= p.next;  slow= slow.next; }
        System.out.println("Starting point of cycle: "+ p.val);

        // length of cycle: starting from startPt.next till again start point is length.
        int ct=1;   p= l1.next;
        while(p!=l1){   p= p.next;  ct++; }
        System.out.println("Length of cycle: "+ ct);
    }


    /* Replace number with sum of square of digits until number equals 1. Check if possible or not.  */
    // n= 19  => true,    n=5  => false (endless loop)

    // if not able to become 1, then its an endless cycle. That means linkedlist cycle approach.
    class squareDigits {
        boolean isEqualPossible(int n) {
            int slow = n, fast = n;
            do {
                slow = findSquare(slow);
                fast = findSquare(findSquare(fast));
            } while (slow != fast);

            if (slow == 1) {    return true; }
            return false;
        }

        int findSquare(int n) {
            int ans = 0;
            while (n > 0) {
                int rem = n % 10;
                ans += rem * rem;
                n /= 10;
            }
            return ans;
        }
    }


    /* Implement get(), put() method for Least Recently Used (LRU) cache i.e. remove least recently used value if capacity exceeds. */
    // ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"], [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]      => {null, null, null, 1, null, -1, null, -1, 3, 4}

    // LinkedHashMap for insertion order. If size exceeds, then remove first used value i.e. LRU.
    static class LRUCache{  // O(1)
        int capacity;
        LinkedHashMap<Integer, Integer> map= new LinkedHashMap<>();

        LRUCache(int capacity){     this.capacity= capacity; }

        int get(int key){
            int val= map.getOrDefault(key, -1);
            if(val!=-1){    map.put(key, val); }    return val;
        }

        void put(int key, int value){
            map.put(key, value);

            // remove least recently used (i.e. first entry)
            if(map.size() > capacity){
                Integer lruKey = map.keySet().iterator().next();    map.remove(lruKey);
            }
        }

        void display(){     System.out.println("Current cache state: "+ map); }
    }


    public static void main(String args[]){

    }
}
