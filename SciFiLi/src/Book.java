/**
 * Creates book objects
 *
 * @author Chris Bouton Andrew Hall
 *
 * Books have Titles, Authors, and importance
 * Books Are either checked in or out
 */
public class Book {
    // instance variables
    String Author;// the author
    String Title;// the title
    boolean checkedIn;// true = checked in, false = checked out
    int importance; // how important it is on the scale from
    // Nick Cage will Defiantly try to steal it to The Cat in the Hat

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
    // returns the Author
    public String getAuthor() {
        return Author;
    }

    //returns the Title
    public String getTitle() {
        return Title;
    }

    //returns the Importance
    public int getImportance() {
        return importance;
    }

    //returns checkedIn
    public boolean getcheckedIn() {
        return checkedIn;
    }

    //sets the value of checked in WITH checking it
    public boolean setCheckedIn(boolean cio){
        if(cio==checkedIn){
            if(cio){
                System.out.println(Title+" was already checked in");
            }
            else{
                System.out.println(Title+" was already checked out");
            }
            return false;
        }
        else {
            if(cio) {
                System.out.println(Title + " has been checked in.");
                checkedIn = true;
            }
            else{

                System.out.println(Title + " has been checked out.");
                checkedIn = true;
            }
            return true;
        }
    }

    //sets the value of checked in WITHOUT checking it
    public void hardCheckIn(boolean cio){
        checkedIn=cio;
    }

    //human readable details
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

    //parser readable details
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