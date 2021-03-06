 /**
  * @author Andew Hall Chris Bouton
  *
  *  Class BT specilized for SciFiLi project to deal with the special properties of Books
  *  it isn't just a strait YOINK
  *  Java Program to Implement Binary Tree
  *  https://www.sanfoundry.com/java-program-implement-binary-tree/
  */

 class BT
 {
     //the root node
     private BTNode root;
     //the current pointer node
     public BTNode current;

     /* Constructor */
     public BT()
     {
         root = null;
     }

     /* Function to check if tree is empty */

     public boolean isEmpty()
     {
         return root == null;
     }

     public void goRoot(){
         current = root;
     }

     /* Functions to insert data */
     public void insert(Book book)
     {
         root = insert(root, null, book);
     }

     /* Function to insert author recursively */
     private BTNode insert(BTNode node, BTNode parent, Book book)
     {
         String author = book.getAuthor();
         //base case
         if (node == null) {
             node = new BTNode(author);
             node.setParent(parent);
             node.addBook(book);
         }
         else if(node.getAuthor().compareTo(author)==0){
             node.addBook(book);
         }
         else
         {
             if (author.compareTo(node.getAuthor()) > 0){
                 node.right = insert(node.right, node, book);
                 node.setParent(parent);
             }
             else if(author.compareTo(node.getAuthor()) < 0){
                 node.left = insert(node.left, node, book);
                 node.setParent(parent);
             }
         }
         return node;
     }

     /* Function to count number of nodes */
     public int countNodes()
     {
         return countNodes(root);
     }

     /* Function to count number of nodes recursively */
     private int countNodes(BTNode r)
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += countNodes(r.getLeft());
             l += countNodes(r.getRight());
             return l;
         }
     }

     /* Function to search for an element */
     public boolean search(String aut)
     {
         return search(root, aut);
     }
     /* Function to search for an element recursively */
     private boolean search(BTNode r, String aut)
     {
         if (r.getAuthor().equals(aut)){
             current = r;
             return true;
         }
         if (r.getLeft() != null)
             if (search(r.getLeft(), aut))
                 return true;

         if (r.getRight() != null)
             if (search(r.getRight(), aut))
                 return true;

         return false;
     }

     /* Function for inorder traversal */
     public void inorder()
     {
         inorder(root);
     }
     private void inorder(BTNode r)
     {
         if (r != null)
         {
             inorder(r.getLeft());
             System.out.println(r.getAuthor() +" ");
             inorder(r.getRight());
         }
     }

     /* Function for preorder traversal */
     public void preorder()
     {
         preorder(root);
     }
     private void preorder(BTNode r)
     {
         if (r != null)
         {
             System.out.print(r.getAuthor() +" ");
             preorder(r.getLeft());
             preorder(r.getRight());
         }
     }

     /* Function for postorder traversal */
     public void postorder()
     {
         postorder(root);
     }
     private void postorder(BTNode r)
     {
         if (r != null)
         {
             postorder(r.getLeft());
             postorder(r.getRight());
             System.out.print(r.getAuthor() +" ");
         }
     }
 }