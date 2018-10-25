/**
 * @author Andrew Hall Chris Bouton
 *  Java Program to Implement Binary Tree
 *  https://www.sanfoundry.com/java-program-implement-binary-tree/
 */
/* Class BTNode */

class BTNode
{
    BTNode left, right, parent;
    private String Author;
    private LinkedList<Book> books;

    /* Constructor */
    public BTNode()
    {
        left = null;
        right = null;
        parent = null;
        Author = "Unknown";
    }
    /* Constructor */

    public BTNode(String author)
    {
        left = null;
        right = null;
        parent = null;
        Author = author;
    }

    /* Function to set left node */

    public void setLeft(BTNode n)
    {
        left = n;
    }

    /* Function to set right node */

    public void setRight(BTNode n)
    {
        right = n;
    }

    /* Function to set parent node */

    public void setParent(BTNode n)
    {
        parent = n;
    }

    /* Function to get left node */

    public BTNode getLeft()
    {
        return left;
    }

    /* Function to get right node */

    public BTNode getRight()
    {
        return right;
    }

    /* Function to get parent node */

    public BTNode getParent()
    {
        return parent;
    }

    public String getAuthor(){
        return Author;
    }

    public boolean addBook(Book book){
        /*
        unimplemented
         */
        return true;
    }

    public LinkedList<Book> getBooks() {
        return books;
    }

    public boolean checkBook(Book book){
         /*
        unimplemented
         */
        return false;
    }

    //    /* Function to set data to node */
//
//    public void setData(int d)
//    {
//        data = d;
//    }
//
//    /* Function to get data from node */
//
//    public int getData()
//    {
//        return data;
//    }
}
