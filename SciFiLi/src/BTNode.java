/**
 * @author Andrew Hall Chris Bouton
 *  Java Program to Implement Binary Tree
 *  https://www.sanfoundry.com/java-program-implement-binary-tree/
 */
/* Class BTNode */

class BTNode
{
    // links
    BTNode left, right, parent;
    // Author is used to sort node into BT
    private String Author;
    // all the books by this Author
    private List<Book> books;

    /* Default Constructor */
    public BTNode()
    {
        left = null;
        right = null;
        parent = null;
        Author = "Unknown";
        books = new List<>();
    }
    /* Constructor */
    public BTNode(String author)
    {
        left = null;
        right = null;
        parent = null;
        Author = author;
        books = new List<>();
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

    /* Function to get Author */
    public String getAuthor(){
        return Author;
    }

    /* Adds a Book to Books
    * returns boolean if it is able to insert or not*/
    public boolean addBook(Book book){
        books.First();
        for(int i = 0; i < books.GetSize(); i++){

            //if current book in books is after, the desired addBook
            if(books.GetValue().getTitle().compareTo(book.getTitle()) > 0)
            {books.InsertBefore(book); return true;}

            //if current book in books is before the desired addBook
            if(books.GetValue().getTitle().compareTo(book.getTitle()) < 0)
            {books.Next(); }

            if(books.GetValue().getTitle().compareTo(book.getTitle()) == 0)
                return true;
        }
        books.InsertAfter(book);
        return true;
    }

    /* returns the LL of books*/
    public List<Book> getBooks() {
        return books;
    }

    /* prints the Books in books */
    public void printBooks(){
        books.First();
        int size = books.GetSize();
        //System.out.println(size);
        for(int i=0; i<size;i++) {
            Book curr = books.GetValue();
            System.out.println((i+1)+"... "+curr);
            books.Next();
        }
    }

    /* prints the Books in books,
    *  if cio is true, prints only checked in books
    *  if cio is false, prints only checked out books*/
    public boolean printBooks(boolean cio){
        books.First();
        int size = books.GetSize();
        //System.out.println(size);
        int j=0;
        for(int i=0; i<size;i++) {
            Book curr = books.GetValue();
            if(curr.getcheckedIn()==cio){
                j++;
                System.out.println((j)+"... "+curr);
            }
            books.Next();
        }
        //System.out.println(j);
        if(j==0){
            return false;
        }
        else return true;
    }

    /* checks a book in or out
     * returns boolean true if check in/out is successful */
    public boolean checkBook(boolean cio, String title){
        books.First();
        int size =books.GetSize();
        for(int i=0; i<size;i++) {
            Book curr = books.GetValue();
            if(curr.getTitle().toLowerCase().equals(title.toLowerCase())){
                curr.setCheckedIn(cio);
                return true;
            }
        }
        return false;
    }
}