package org.example;

import java.util.*;

/*
 Trees: Non-Linear data structure consisting of n nodes and n-1 edges. Trees doesn't contains cycle. Example: Hashing, An organization containing people at diff levels like president, vise president, teachers, students.
 Binary Tree: Tree where each node have atmost 2 nodes.
 Binary Search Tree (BST): Sorted binary tree.
 Max heap:  Value at each node is larger than their child nodes. And vise-versa for min heap.

Depth First search (DFS):
    1. Inorder Traversal (left -> root -> right): Sort traversal of BST.
    2. Preorder Traversal (root -> left -> right).
    3. Postorder Traversal (left -> right -> root).
Breadth First Traversal (BFS): Printing elements breadth/ levelwise.

 Predecessor (Node just before given node in inorder traversal). Successor (Node after given node in inorder traversal).

 Trie: Tree like structure used to store dynamic strings usually.
*/
public class Trees {

    class Node{
        int val;    Node left;  Node right;
        Node(int val){  this.val= val;  this.left= this.right= null; }
    }


    /* Insert element in binary tree. */
    void insert(Node root){
        root.left= new Node('F');
        root.right= new Node('G');
        root.left.left= new Node('H');  root.left.right= new Node('I');
        root.right.left= new Node('J'); root.right.right= new Node('K');
    }


    /* Inorder traversal */
    void inorder(Node root){
        if(root==null){ return; }
        inorder(root.left);     System.out.println(root);       inorder(root.right);
    }


    /* preorder traversal */
    void preorder(Node root){
        if(root==null){ return; }
        System.out.println(root);       preorder(root.left);       preorder(root.right);
    }


    /* Postorder traversal */
    void postorder(Node root){
        if(root==null){ return; }
        postorder(root.left);       postorder(root.right);     System.out.println(root);
    }


    /* reverse order traversal */
    void revorder(Node root){
        if(root==null){ return; }
        revorder(root.right);     System.out.println(root);       revorder(root.left);
    }



/*-------------------------------------------------------------------------------------------------------------------------------------                                                   DFS TRAVERSAL QUESTIONS         ------------------------------------------------------------------------------------------------------------------------------------*/


    /* Tree Size: Find total number of nodes in tree. */

    // Traverse all nodes and ct values while traversing.
    int treeSize(Node root){
        if(root==null){ return 0; }
        int l= treeSize(root.left);     int r= treeSize(root.right);
        return 1+ (l+r);    //+1 for root node.
    }


    /* Find all path from root to leaf node. */

    // add all values, then backtrack to consider different paths.
    void rootToLeaf(Node root, List<Integer> path, List<List<Integer>> ans){
        if(root==null){     return; }

        path.add(root.val);
        if(root.left==null && root.right==null){    ans.add(new ArrayList<>(path)); }

        rootToLeaf(root.left, path, ans);   rootToLeaf(root.right, path, ans);
        path.removeLast();
    }


    /* Print paths from root to node x in binary tree. */

    // Same as above just change add path condition to if(root.val==x){ ans.add(new ArrayList<>(path); }


    /* Delete all leaf nodes with value target. */

    // we need to delete only leaf and their parent nodes if eq to target.
    Node delLeaf(Node root, int target){    // delLeaf(root, target);
        if(root==null){ return null; }
        root.left= delLeaf(root.left, target);      root.right= delLeaf(root.right, target);

        if(root.left==null && root.right==null && root.val==target){    return null; }
        return root;
    }


    /* Return leftmost node of a tree. */

    int leftmost(Node root){
        if(root==null){ return -1; }
        if(root.left==null){    return root.val; }

        return leftmost(root.left);
    }


    /* Boundary Traversal: Print all left, leaf, right boundary nodes respectively. */

    class boundary{
        void bounTrav(Node root, List<Integer> ans){    // return bounTrav(root, ans);
            if(root==null){ return; }
            ans.add(root.val);

            printLeft(root.left, ans);      // excluding leaf nodes
            printLeaves(root.left, ans);     printLeaves(root.right, ans);
            printRight(root.right, ans);    // in reverse order (excluding leaves)
        }

        // if left nodes present then move only left, so that we are always on boundary
        void printLeft(Node root, List<Integer> ans){
            if(root==null){     return; }
            if(root.left==null && root.right==null){    return; }

            ans.add(root.val);
            if(root.left!=null){    printLeft(root.left, ans); }
            else{   printLeft(root.right, ans); }
        }

        void printRight(Node root, List<Integer> ans){      // add ans values in reverse order, through recursive function
            if(root==null){ return; }
            if(root.left==null && root.right==null){    return; }

            if(root.right!=null){    printRight(root.right, ans); }
            else{   printRight(root.left, ans); }
            ans.add(root.val);
        }

        void printLeaves(Node root, List<Integer> ans){
            if(root==null){ return; }
            if(root.left==null && root.right==null){    ans.add(root.val); return; }
            printLeaves(root.left, ans);    printLeaves(root.right, ans);
        }
    }


    /* Height/ Max Depth: Find number of nodes along longest path from root node. */

    int maxDepth(Node root){
        if(root==null){ return 0; }

        int l= maxDepth(root.left);     int r= maxDepth(root.right);
        return 1+ Math.max(l, r);   // +1 for root node.
    }


    /* Min Depth: Find number of nodes along shortest path from root node. */

    int minDepth(Node root){
        if(root==null){ return 0; }

        int l= minDepth(root.left);     int r= minDepth(root.right);
        return 1+ Math.min(l, r);   // +1 for root node.
    }


    /* Balanced Binary Tree: Check if for all subtree height of left and right subtree varies by atmost 1. */

    // check if abs diff is more than 1 then not possible
    // int ans= new int[0];     bbt(root, ans);  if(ans[0]==-1) return false; else true;
    int bbt(Node root, int ans[]){
        if(root==null){ return 0; }

        int l= bbt(root.left, ans);     int r= bbt(root.right, ans);
        if(Math.abs(l-r)>1){    ans[0]=-1; }
        return 1+ Math.max(l, r);
    }


    /* Match Tree: Return true if 2 trees are equal. */

    // i need to match whole tree, so if val not equal at any point then return false.
    boolean matchTree(Node p, Node q){
        if(p==null || q==null){ return (p==q); }
        if(p.val != q.val){ return false; }

        boolean l= matchTree(p.left, q.left);       boolean r= matchTree(p.right, q.right);
        return (l && r);
    }


    /* Match SubTree: Return true if q is subtree of p. */

    boolean matchSubtree(Node p, Node q){
        if(p==null){ return false; }
        if(p.val == q.val){ if(matchTree(p,q))  return true; }

        boolean l= matchTree(p.left, q);    boolean r= matchTree(p.right, q);
        return (l || r);
    }


    /* Symmetric Binary Tree: Check if tree is symmetrical i.e. mirror image. */

    boolean symmetric(Node p, Node q){  // return symmetric(root.left, root.right);
        if(p==null || q==null){ return (p==q); }
        if(p.val != q.val){ return false; }

        boolean l= symmetric(p.left, q.right);       boolean r= symmetric(p.right, q.left);
        return (l && r);
    }


    /* Mirror Image: Make mirror image of current tree by swapping left and right subtrees. */

    void mirror(Node root){
        Node temp;
        if(root==null){ return; }
        temp= root.left;    root.left= root.right;  root.right=temp;
        mirror(root.left);      mirror(root.right);
    }


    /* Return all subtrees of given tree. */
    // root= {1,2,3,4,null,2,4,null,null,4}     => [[4,N,N], [2,4,N,N,N], [4,N,N], [2,4,N,N,N], [4,N,N], [3,2,4,N,N,N,4,N,N], [1,2,4,N,N,N,3,2,4,N,N,N,4,N,N]]

    // traverse for each subtree, and print subtree depth-wise
    String printSub(Node root, List<String> ans){     // printSub(root, ans);   return ans;
        if(root==null){ return "N"; }

        String l= printSub(root.left, ans);   String r= printSub(root.right, ans);
        String s= root.val+ ", "+ l+ ", "+ r;   ans.add(s);
        return s;
    }


    /* Return all duplicate subtree. */
    // root= [1,2,3,4,null,2,4,null,null,4]     => [[4,N,N], [2,4,N,N,N]

    // Add it in map instead of list ans, and check if already present in map, then is a valid ans.


    /* Return sum of all elements in each subtree. */

    // traverse and return sum of each subtree
    int sumSub(Node root, List<Integer> ans){   // sumSub(root, ans);   return ans;
        if(root==null){ return 0; }

        int l= sumSub(root.left, ans);   int r= sumSub(root.right, ans);
        int total= root.val+ l+ r;      ans.add(total);
        return total;
    }


    /* Find subtree with maximum sum. */
    // have sum of all subtrees, check if maximum, return mx.


    /* Check if given binary tree have subtree with equal number of 0's and 1's. */

    // In sumSub(), if val is 0 then add -1, else 1. And in end check if totalVal is 0, then only add in ans.
    int sumEqSub(Node root, List<Integer> ans){   // sumSub(root, ans);   return ans;
        if(root==null){ return 0; }

        int l= sumEqSub(root.left, ans);   int r= sumEqSub(root.right, ans);
        int val= (root.val==0) ? -1 : 1;
        int total= val+ l+ r;       if(total==0){ ans.add(total); }
        return total;
    }


    /* Check if every node value is equal to its left and right subtree. */

    int nodeEqSub(Node root, List<Integer> ans){   // nodeEqSub(root, ans);   if(ans.isEmpty()){    return true; } else false;
        if(root==null){ return 0; }

        int l= nodeEqSub(root.left, ans);   int r= nodeEqSub(root.right, ans);
        if(root.val != l+r){  ans.add(-1); };
        int total= root.val+ l+ r;
        return total;
    }


    /* Diameter of binary tree: Find longest path between any 2 nodes. */

    // Possibility is that we are taking left of 1 subtree and right of any other subtree to get max path. So, we take max path each time instead of any sort of total.
    int diamSub(Node root, List<Integer> ans){  // diamSub(root, ans);  return Collections.max(ans);    // return max of ans.
        if(root==null){ return 0; }

        int l= diamSub(root.left, ans);     int r= diamSub(root.right, ans);
        ans.add(l+r);   // current diameter
        return 1+ Math.max(l, r);   // +1 for root node
    }


    /* Maximum Path Sum: Find maximum path sum in the binary tree. Path might or mightnot pass through root. */

    // same as above, just add root.val instead of 1 in end. And also to discard -ve path, if sum becomes -ve any time take max of 0 and currPath.
    int pathSum(Node root, List<Integer> ans){  // pathSum(root, ans);  return Collections.max(ans);    // return max of ans.
        if(root==null){ return 0; }

        int l= Math.max(0, pathSum(root.left, ans));     int r= Math.max(0, pathSum(root.right, ans));
        ans.add(root.val+ l+r);   // current diameter sum
        return root.val+ Math.max(l, r);
    }


    /* Return longest path having same node value. */

    // same as above i.e. not necessary that whole subtree is involve, so taking max left/ right path each time.
    int longPath(Node root, List<Integer> ans){ // longPath(root, ans);  return Collections.max(ans);    // return max of ans.
        if(root==null){ return 0; }

        int lPath= longPath(root.left, ans);    int rPath= longPath(root.right, ans);

        int l=0,r=0;
        if(root.left!=null && root.val==root.left.val){ l= lPath+1; }
        if(root.right!=null && root.val==root.right.val){ r= rPath+1; }

        ans.add(l+r);
        return Math.max(l, r);
    }


    /* Find lowest common ancestor of 2 nodes in a tree. */

    Node lca(Node root, Node p, Node q){
        if(root==null || root==p || root==q){   return root; }

        Node l= lca(root.left, p,q);    Node r= lca(root.right, p,q);

        if(l!=null && r!=null){ return root; }
        else if(l!=null){   return l; }
        else{   return r; }
    }


    /* Find distance between 2 specific nodes p and q. */

    // find lca of given 2 nodes. So, ans= dist(of lcaNode, p)+ dist(lcaNode, q)


    /* Return max from tree, given we can't take 2 adj
    node values. */

    // at each node 2 choices, either take this, or next node. Here instead of i+1, we have node.left and node.right.
    int maxNonAdj(Node root, Map<Node, Integer> dp){    // maxNonAdj(root, dp);
        if(root==null){ return 0; }
        if(dp.containsKey(root)){   return dp.get(root); }

        int take= 0, subTake1=0, subTake2=0;
        if(root.left!=null){    subTake1= maxNonAdj(root.left.left, dp)+ maxNonAdj(root.left.right, dp); }
        if(root.right!=null){    subTake2= maxNonAdj(root.right.left, dp)+ maxNonAdj(root.right.right, dp); }
        take= root.val+ subTake1+ subTake2;

        int notTake= maxNonAdj(root.left, dp) + maxNonAdj(root.right, dp);

        int ans= Math.max(take, notTake);   dp.put(root, ans);  return ans;
    }



    /*----------------------------------------------------------------------------------------------                                   BFS TRAVERSAL QUESTIONS         ------------------------------------------------------------------------------------------------*/

    /* BFS Traversal */

    // at each level for all values added in queue, traverse its left and right nodes.
    List<List<Integer>> bfs(Node root){
        List<List<Integer>> ans= new ArrayList<>();
        if(root==null){ return ans; }

        Queue<Node> q= new ArrayDeque<>();  q.add(root);
        while(!q.isEmpty()){
            List<Integer> level= new ArrayList<>(); int n= q.size();
            for(int i=0; i<n; i++){
                Node p= q.poll();   level.add(p.val);
                if(p.left != null){ q.add(p.left); }        if(p.right != null){    q.add(p.right); }
            }
            ans.add(level);
        }
        return ans;
    }


    /* Get level of element x in tree. */

    // Do BFS Traversal, and check level.


    /* Find zig-zag traversal of tree. */

    // Means, 1st row direct, 2nd in reverse order, 3rd again direct and so on. BFS traversal, and add even levels in reverse order.


    /* Print vertical order traversal of binary tree. (Means vertically 1st line, then 2nd line and so on) */

    // BFS Traversal, storing axis, level both this time. And in end, print values axis wise, instead of level wise.
    class VerticalTrav {
        class Pair{
            int axis;   int lvl;    Node node;
            Pair(int axis, int lvl, Node node){     this.axis= axis;    this.lvl= lvl;  this.node= node; }
        }

        List<List<Integer>> vertTrav(Node root) {
            if(root==null){ return new ArrayList<>(); }

            TreeMap<Integer, TreeMap<Integer, List<Integer>> > map= new TreeMap<>();   // (axis, level, node values) in sorted order
            Queue<Pair> q= new ArrayDeque<>();      q.add(new Pair(0, 0, root));   // {axis, level, node} for iteration

            while(!q.isEmpty()){
                int n= q.size();
                for(int i=0; i<n; i++){
                    Pair p= q.poll();   int axis= p.axis;   int lvl= p.lvl;     Node curr= p.node;

                    TreeMap<Integer, List<Integer>> lvlMap= map.getOrDefault(axis, new TreeMap<>());    lvlMap.getOrDefault(lvl, new ArrayList<>()).add(curr.val);
                    map.put(axis, lvlMap);       // update with modified list

                    if(curr.left!=null){  q.add(new Pair(axis-1, lvl+1, curr.left)); }         if(curr.right!=null){   q.add( new Pair(axis+1, lvl+1, curr.right)); }
                }
            }

            // add list values to result
            List<List<Integer>> ans= new ArrayList<>();
            for(TreeMap<Integer, List<Integer>> i: map.values()){
                List<Integer> col= new ArrayList<>();
                for(List<Integer> j: i.values()){
                    col.addAll(j);
                }
                ans.add(col);
            }
            return ans;
        }
    }


    /* Find top/bottom view of binary tree (means 1st node on every axis from top/ bottom). */

    // Vertical order traversal, and return 1st node only instead of all.


    /* Find left/ right view of binary tree (means 1st node at every level). */

    // BFS traversal, and return 1st node only instead of all.


    /* Print maximum width of binary tree. */

    // Vertical order traversal, at each level get the max axis difference.


    /* Print all nodes which are at distance k from target node. */

    // At target node, traverse left, right, parent till dist k. Take vis array for already visited nodes track. Initially find parent node for each node.
    class TargetDisK{
        List<Integer> disK(Node root, Node target, int k){
            List<Integer> res= new ArrayList<>();
            if(root==null || target==null || k<0){ return res; }

            Map<Node, Node> parent= new HashMap<>();    // (node, parentOfNode)
            markParents(root, parent);

            Queue<Node> q= new ArrayDeque<>();      q.add(target);
            Set<Node> vis= new HashSet<>();     vis.add(target);
            int lvl=0;

            while(!q.isEmpty()){
                if(lvl==k){ break; }
                int n= q.size();

                for(int i=0; i<n; i++){
                    Node curr= q.poll();

                    if(curr.left!=null && !vis.contains(curr.left)){    q.add(curr.left);   vis.add(curr.left); }
                    if(curr.right!=null && !vis.contains(curr.right)){    q.add(curr.right);   vis.add(curr.right); }
                    if(parent.get(curr)!=null && !vis.contains(parent.get(curr))){  q.add(parent.get(curr));    vis.add(parent.get(curr)); }
                }
            }

            while(!q.isEmpty()){    res.add(q.poll().val); }
            return res;
        }

        void markParents(Node root, Map<Node, Node> parent){    // Traverse complete tree, and just add node with its child
            Queue<Node> q= new ArrayDeque<>();      q.add(root);
            while(!q.isEmpty()){
                Node curr= q.poll();
                if(curr.left!=null){    parent.put(curr.left, curr);    q.add(curr.left); }
                if(curr.right!=null){   parent.put(curr.right, curr);   q.add(curr.right); }
            }
        }
    }


    /* Find time taken to burn entire tree, starting from given node. */

    // Exactly same as TargetDisK. Just count number of iterations or levels.



    /*-----------------------------------------------------------------------------------------------    BST TRAVERSAL QUESTIONS: Sorted Binary Tree i.e. elements in left are smaller than root -------------------------------------------------------------------------------------------------*/

    /* Insert an element in BST. */

    // value is smaller than root then insert in left side, else in right.
    Node insertBST(Node root, int val){
        if(root==null){ return new Node(val); }

        if(val<= root.val){     root.left= insertBST(root.left, val); }
        else{   root.right= insertBST(root.right, val); }
        return root;
    }


    /* Search an element in BST. */

    Node searchBST(Node root, int val){
        if(root==null){ return null; }

        if(val==root.val){  return root; }
        else if(val<root.val){  return searchBST(root.left, val); }
        else{   return searchBST(root.right, val); }
    }


    /* Delete an element in BST. */

    // After finding val, for deleting, if both of its child present, then min value is inorder successor (min in right subtree)
    Node delBST(Node root, int val){
        if(root==null){ return null; }

        if(val < root.val){     root.left= delBST(root.left, val); }
        else if(val > root.val){    root.right= delBST(root.right, val); }
        else if(val==root.val){
            if(root.left==null || root.right==null) {
                root = (root.left == null) ? root.right : root.left;
            }
            else{
                Node succ= root.right;
                while(succ.left!=null){ succ= succ.left; }
                root.val= succ.val;
                root.right= delBST(root.right, root.val);
            }
        }
        return root;
    }


    /* Find kth smallest element in BST. */

    // BST so sorted in inorder traversal. Do inorder traversal, and increment count each time.  if(ct==k){ return root.val; }


    /* Find kth largest element in BST. */

    // from start if want to find required node = total nodes - k. Then inorder traversal.


    /* Validate if given tree is BST or not. */

    // check if inorder traversal comes in sorted order then yes it's a BST, else no.


    /* Find the lowest common ancestor of p, q. */

    // if p,q less then move left, if both greater move right, else return root.
    Node lcsBST(Node root, Node p, Node q){
        if(root==null){ return null; }

        if(p.val<root.val && q.val<root.val){   return lcsBST(root.left, p, q); }
        else if(p.val>root.val && q.val>root.val){  return lcsBST(root.right, p, q); }
        else{   return root; }
    }


    /* Return balanced BST of given tree. Balanced BST is tree where left, right child nodes differ by atmost 1. */

    // For current tree, store sorted elements through inorder traversal, choose middle element as root each time, for creating balanced BST.
    class BalancedBST{

        // List<Integer> list;  inorderTrav(root, list);     return balBST(0, list.size()-1, list);
        Node balBST(int l, int r, List<Integer> list){
            if(l>r){    return null; }

            int mid= (l+r)/2;
            Node curr= new Node(list.get(mid));
            curr.left= balBST(l, mid-1, list);      curr.right= balBST(mid+1, r, list);
            return curr;
        }

        void inorderTrav(Node root, List<Integer> list) {
            if(root==null){     return; }
            inorderTrav(root.left, list);       list.add(root.val);     inorderTrav(root.right, list);
        }
    }



/*-------------------------------------------------------------------------------------------------------------------------------------                     Trie: Tree like structure used to store dynamic strings usually -------------------------------------------------------------------------------------------------------------------------------------*/

    /* Return output for commands i.e. count freq of word, freq of words with given prefix, insert word, erase word. */
    // words["apple", "apple", "app", "apex"]   => insert all; erase; ct freq for word apple=2; ct freq of words with prefix app= 3

    // Trie are formed like for words hell, hello, we append h->e->l->l->o which stores both.
    class TrieNode{

        class Node{
            Node child[]= new Node[26];
            int wordCt=0, prefixCt=0;
        }

        // Check if child is null then add the new value as tree node, else move ahead.
        void insTrie(Node root, String word){
            Node p= root;

            for(int i=0; i<word.length(); i++){
                int ix= word.charAt(i)-'a';
                if(p.child[ix]==null){   p.child[ix]= new Node(); }
                p= p.child[ix];     p.prefixCt++;
            }
            p.wordCt++;
        }

        // erase a word
        void eraseWord(Node root, String word){
            Node p= root;
            for(int i=0; i<word.length(); i++){
                int ix= word.charAt(i)-'a';
                p= p.child[ix];     p.prefixCt--;
            }
            p.wordCt--;
        }

        // ct freq of given word, and also number of words with given word as prefix
        void ctprefixWord(Node root, String word){
            Node p= root;
            for(int i=0; i<word.length(); i++){
                int ix= word.charAt(i)-'a';
                if(p.child[ix]==null){  return; }   // given word doesn't exist in trie
                p= p.child[ix];
            }

            System.out.println("Freq of given word: "+ p.wordCt );
            System.out.println("Number of words with given prefix: "+ p.prefixCt );
        }
    }


    /* Find max xor taken any one number from arr1[], arr2[] each. */
    // arr1[]= {6,6,0,6,8,5,6}, arr2[]= {1,7,1,7,8,0,2}     => 15 i.e. (8^7)

    // For diff bits xor is 1. Add all values of arr1 into trie. Then for max valXor will be max if able to make bits 1 as much as possible

    // insArr1(root, arr1);     return mxXor(root, arr2);
    class MaxXOR{

        class Node{
            Node child[]= new Node[2];
        }

        // starting from leftmost bit, check each time if bit is 0/1. And add it to the trie.
        void insArr1(Node root, int arr1[]){
            for(int i=0; i<arr1.length; i++){
                int val= arr1[i];   Node p= root;
                for(int j=31; j>=0; j--){
                    int ix= val & (1<<j);
                    if(p.child[ix]==null){  p.child[ix]= new Node(); }
                    p= p.child[ix];
                }
            }
        }

        // from arr2, check if opposite of current bit is present then set that bit in res.
        int mxXor(Node root, int arr2[]){
            int res=0;
            for(int i=0; i<arr2.length; i++){
                int val= arr2[i];   Node p= root;
                for(int j=31; j>=0; j--){
                    int ix= val & (1<<j);   int oppBit= (ix==0)?1:0;    // change bit from (0 to 1) or (1 to 0), as we want to compare opposite bit
                    if(p.child[oppBit]!=null){  res= res | (1<<j);    p= p.child[oppBit]; }
                    else{   p= p.child[ix]; }
                }
            }
            return res;
        }
    }


    public static void main(String args[]){

    }
}


// Graph: BFS:  https://leetcode.com/problems/longest-subsequence-repeated-k-times/submissions/1677953703/?envType=daily-question&envId=2025-06-27

