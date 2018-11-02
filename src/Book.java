
/**
 * Creates book objects
 *
 * @author ChrisBouton Andrew Hall
 *
 */
public class Book {
    // instance variables
    String Author;
    String Title;
    boolean checkedIn;
    int importance;


    /**
     * Constructor for objects of class Book
     */
    public Book(String T,String A,  int imp) {
        // initialise instance variables
        Title = T;
        Author = A;
        checkedIn = true;
        importance = imp;
    }

    /**
     * getters and setters
     */
    public String getAuthor() {
        return Author;
    }

    public String getTitle() {
        return Title;
    }

    public int getImportance() {
        return importance;
    }

    public boolean getcheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean cio){
        checkedIn = cio;
    }


    public String toString() {
        String IO;
        if (checkedIn) {
            IO = " is checked in.";
        } else {
            IO = " is checked out.";
        }
        String ret = "" + Title + " by " + Author + IO + " Imp# : " + importance;
        return ret;
    }

    public String toWrite(){
        String IO;
        if (checkedIn) {
            IO = "1";
        } else {
            IO = "0";
        }
        String ret = Title+", "+Author+", "+IO+", "+importance;
        return ret;
    }
}


