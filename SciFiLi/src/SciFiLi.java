/**
 * @author Andrew Hall Chris Bouton
 *
 */

import java.util.Scanner;
import java.util.regex.*;
import java.util.Random;
import java.io.*;

public class SciFiLi {

    private static BT lib;
    private static int bookCount;
    private static List<Book> impBooks;
    private static List<Book> abcBooks;
    private static int DEBUG;

    public static void main(String[] args)throws Exception{

        // This website has good info for file reading and writing
        // https://www.baeldung.com/java-write-to-file

        //log reader
        File log = new File("src//log.txt");
        Scanner logReader = new Scanner(log);
        int logNum = Integer.parseInt(logReader.next());
        //System.out.println(logNum);

        //path for current days' log
        String path = "src//Logs//dayOut"+logNum+".txt";
        //writer for dayOut#.txt
        //System.out.println(path);
        /*
        source file and scanners
        */
        File input = new File("src//Logs//dayOut1.txt");
        //File input = new File(path);
        // reads the File input
        Scanner reader = new Scanner(input);//file);
        // takes user input
        Scanner UI = new Scanner(System.in);
        /*
        Static variables
         */

        lib = new BT();
        bookCount = 0;
        impBooks = new List<>();
        abcBooks = new List<>();
        DEBUG = 0;

        /*
        Main loop for reader
         */
        while(reader.hasNextLine()) {
            /*
            Parse Line into Book
             */
            //>>
            String rIn = reader.nextLine();
            String[] rArr = rIn.split(",");
            String title = rArr[0];
            String author = rArr[1].trim();
            String CIOparse = rArr[2].trim(); // "0" or "1"
            //System.out.println("#"+CIOparse+"#");
            int importance = Integer.parseInt(rArr[3].trim());
            boolean CIn;
            if (CIOparse.equals("1")) {
                CIn = true;
            } else if (CIOparse.equals("0")) {
                CIn = false;
            } else {
                System.out.println("Checked In/Out Error");
                CIn = false;
            }
            Book currBook = new Book(title, author, importance);
            currBook.hardCheckIn(CIn);
            //<<
            //sorted by author
            lib.insert(currBook);
            //sorted by importance
            impInsert(currBook);
            //sorted by title
            abcInsert(currBook);
            //#Chris# lib.acbInsert(currBook);
            bookCount++;
            //System.out.println(currBook);

        }
        if (DEBUG==1) {
            System.out.println("Authors:");
            lib.inorder();
        }

        /*
        Main loop for UI menu
         */
        System.out.println("Welcome!");
        while (true){
            System.out.println("Menu Options: ");
            System.out.println("1 : Search for a book.");
            System.out.println("2 : Access Lists.");
            System.out.println("3 : Check in a book.");
            System.out.println("4 : Check out a book.");
            System.out.println("5 : Process the returns.");
            System.out.println("6 : Add a new Book.");
            System.out.println("7 : Run a Fire Drill.");
            System.out.println("8 : Exit the program.");
            /*
            menu executables
            */
            //>>
            String UIin = UI.next();
            // #1# search
            if(UIin.equals("1")){
                System.out.println("You selected to: ");
                System.out.println("Search for a book.");
                System.out.println();
                System.out.println("Who is the Author of the book?");
                String currAuthor = UI.next().trim();
                System.out.println("Would you like to: ");
                System.out.println("1 : Look for a specific a title");
                System.out.println("2 : See all books by "+currAuthor);
                String searchChoice = UI.next().trim();

                if(searchChoice.equals("1")){
                    System.out.println("What is the Title of the Book?");
                    String bookTitle = UI.next().trim();

                    System.out.println("Im sorry, I have not implemented this feature yet");
                    System.out.println("Unable to find: "+bookTitle);
                    /*
                    * find the book and see if it is in or checked out
                    */
                }
                else if(searchChoice.equals("2")){
                    printAuthor(currAuthor);
                }
                else{
                    System.out.println("Invalid entry");
                }
            }
            // #2# lists
            else if(UIin.equals("2")){
                System.out.println("You selected to: ");
                System.out.println("Access Lists.");
                System.out.println();
                System.out.println("Do you want: ");
                System.out.println("1 : Only books that are checked in");
                System.out.println("2 : All books in the system");
                String cioChoice = UI.next().trim();
                boolean onlyCheckedIn;
                onlyCheckedIn = cioChoice.equals("1");
                System.out.println("Would you like to display lists by Title, Author, or Importance?");
                System.out.println("1 : Title");
                System.out.println("2 : Author");
                System.out.println("3 : Importance");
                System.out.println();
                String listChoice = UI.next().trim();
                if(listChoice.equals("1")){
                    if(onlyCheckedIn){
                        System.out.println("Checked in Books by Title: ");
                        System.out.println();
                        abcPrint(onlyCheckedIn);
                    }
                    else{
                        System.out.println("All Books sorted by Title: ");
                        System.out.println();
                        abcPrint(onlyCheckedIn);
                    }
                }
                else if(listChoice.equals("2")){
                    if(onlyCheckedIn){
                        System.out.println("Checked in Books by Author: ");
                        System.out.println();
                    }
                    else{
                        System.out.println("All Books sorted by Author: ");
                        System.out.println();
                        printAllNodes();
                    }
                }
                else if(listChoice.equals("3")){
                    if(onlyCheckedIn){
                        System.out.println("Checked in Books by Importance: ");
                        System.out.println();
                        impPrint(onlyCheckedIn);
                    }
                    else{
                        System.out.println("All Books sorted by Importance: ");
                        System.out.println();
                        impPrint(onlyCheckedIn);
                    }
                }
                else{
                    System.out.println("Invalid entry");
                }

            }
            // #3# check in
            else if(UIin.equals("3")){
                System.out.println("You selected to: ");
                System.out.println("Check in a book");
                System.out.println();
                System.out.println("Who is the Author of the book?");
                String currAuthor = UI.nextLine().trim();
                UI.next();
                if(printAuthor(currAuthor,false)){
                    System.out.println("What is the Title of the Book?");
                    String currTitle = UI.nextLine().trim();
                    checkIO(true,currTitle,currAuthor);
                }
                else{
                    System.out.println("No books to check in.");
                }
            }
            // #4# check out
            else if(UIin.equals("4")){
                System.out.println("You selected to: ");
                System.out.println("Check out a book\n");
                System.out.println("Who is the Author of the book?");
                String currAuthor = UI.next().trim();
                if(printAuthor(currAuthor,true)){
                    System.out.println("What is the Number of the Book?");
                    String currTitle = UI.next();
                    currTitle = currTitle.replaceAll("[_]"," ");
                    System.out.println(currTitle);
                    if(checkIO(false,currTitle,currAuthor)){
                        System.out.println("True");
                    }
                    else{
                        System.out.println("false");
                    }
                }
                else{
                    System.out.println("No books to check out.");
                }

            }
            // #5# returns
            else if(UIin.equals("5")){
                System.out.println("You selected to: ");
                System.out.println("Process the returns.");
                Random rand = new Random();
                // int numToReturn = rand.nextInt(# of checked out books)
                // list returns the first so many books to return
                System.out.println("Im sorry, I have not implemented this feature yet");
            }
            // #6# add
            else if(UIin.equals("6")){
                System.out.println("You selected to: ");
                System.out.println("Add a new Book");
                System.out.println();
                System.out.println("Would you like to: ");
                System.out.println("1 : Create a new Book");
                System.out.println("2 : Ask Cthulhu for a recommendation");
                System.out.println();
                String nbChoice = UI.next().trim();
                if(nbChoice.equals("1")){
                    System.out.println("What is the Title of your Book?");
                    System.out.println();
                    String currTitle = UI.next().trim();
                    System.out.println("Who is the Author of your Book?");
                    System.out.println();
                    String currAuthor = UI.next().trim();
                    int currImportance = bookCount+1;
                    Book bookAdd = new Book(currTitle,currAuthor,currImportance);
                    bookAdd.hardCheckIn(true);
                    lib.insert(bookAdd);
                    impInsert(bookAdd);
                    abcInsert(bookAdd);
                }
                // #c# lolz
                else if(nbChoice.equals("2")){
                    System.out.println("The Immortal Cthulhu presents you");
                    Random rand = new Random();
                    String magic;
                    int mType = rand.nextInt(8);
                    System.out.println(mType);
                    switch (mType){
                        case 0 : magic = "Abyssal";
                            break;
                        case 1 : magic = "Arcane";
                            break;
                        case 2 : magic = "Cursed";
                            break;
                        case 3 : magic = "Forbidden";
                            break;
                        case 4 : magic = "Terrible";
                            break;
                        case 5 : magic = "Infernal";
                            break;
                        case 6 : magic = "Daedric";
                            break;
                        case 7 : magic = "Void";
                            break;
                        //Unreachable should quite latterly be unreachable
                        default: magic = "Unreachable";
                            break;
                    }
                    System.out.println("a Tome of "+magic+" Knowledge: ");
                    int tLen = 9+rand.nextInt(10);
                    String title = "";
                    char T = (char) (rand.nextInt(26) + 'A');
                    title += T;
                    for(int i = 0; i < tLen; i++){
                        char c = (char) (rand.nextInt(26) + 'a');
                        title += c;
                    }
                    int aLen = 4+rand.nextInt(11);
                    String author = "";
                    char A = (char) (rand.nextInt(26) + 'A');
                    author += A;
                    for(int i = 0; i < aLen; i++){
                        char c = (char) (rand.nextInt(26) + 'a');
                        author += c;
                    }
                    System.out.println(title+" by "+author);
                    int currImportance = bookCount+1;
                    Book bookAdd = new Book(title,author,currImportance);
                    bookAdd.hardCheckIn(true);
                    lib.insert(bookAdd);
                    abcInsert(bookAdd);
                    impInsert(bookAdd);

                    System.out.println("Continue? y/n");
                    String cont = UI.next().trim();
                    if(!(cont.equals("y")||cont.equals("Y"))){
                        System.out.println("Your brain melts at the sound the book");
                        System.out.println("made when it materialized from the bowels of");
                        System.out.println("Yogsageroth.");
                        break;
                    }
                }
                else{
                    System.out.println("Invalid entry");
                }
            }
            // #7# fire
            else if(UIin.equals("7")){
                System.out.println("You selected to: ");
                System.out.println("Run a Fire Drill");
                Random rand = new Random();
                int numOfBook = rand.nextInt((bookCount/2)+1);
                System.out.println("You managed to save "+numOfBook+" books");
                System.out.println();
                Book[] saved = new Book[numOfBook];

                impBooks.First();
                int i=0;
                while(i<numOfBook){
                    Book curr = impBooks.GetValue();
                    if(curr.checkedIn){
                        saved[i]= curr;
                        System.out.println((i+1)+"..."+curr);
                        i++;
                    }
                    impBooks.Next();
                }
                fireLog(saved);
            }
            // #8# exit
            else if(UIin.equals("8")){
                System.out.println("You selected to: ");
                System.out.println("Exit the program");
                break;
            }
            else{
                System.out.println("Invalid entry");
            }
            System.out.println("Thank you user!");
        }
        System.out.println("Goodbye User");
//        String newPath = "src//Logs//dayOut"+(logNum+1)+".txt";
//        FileWriter fileWriter = new FileWriter(newPath);
//        PrintWriter printWriter = new PrintWriter(fileWriter);
//        //update log
//        FileWriter logWriter = new FileWriter("src//log.txt");
//        PrintWriter logPrinter = new PrintWriter(logWriter);
//        logPrinter.print(logNum+1);
//        logPrinter.close();

//        //write to dayOut#.txt
//        printWriter.println(logNum);
//        printWriter.println("bookTitle, bookAuthor, Cio, importance");
//        printWriter.close();
    }

    private static void printAllNodes(){
        lib.goRoot();
        printAllNode(lib.current);
    }

    private static void printAllNode(BTNode n)throws NullPointerException{
        String aut = n.getAuthor();
        System.out.println("Book(s) by: "+aut);
        n.printBooks();
        if(n.getLeft()!=null){
            //System.out.println("left");
            printAllNode(n.getLeft());
        }
        if(n.getRight()!=null){
            //System.out.println("right");
            printAllNode(n.getRight());
        }
    }

    // print the author if you care about checked in/out
    private static boolean printAuthor(String author,boolean cio){
        lib.goRoot();
        return printAuthor(author,lib.current,cio);
    }
    private static boolean printAuthor(String author,BTNode n,boolean cio){
        if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())==0){
            System.out.println("The book(s) by "+author+" are:");
            boolean ret = n.printBooks(cio);
            System.out.println();
            return ret;
        }
        else if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())<0){
            if(n.getRight()==null){
                System.out.println("Author: "+author+" not found");
                return false;
            }
            else {
                return printAuthor(author, n.getRight(),cio);
            }
        }
        else if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())>0){
            if(n.getLeft()==null){
                System.out.println("Author: "+author+" not found");
                return false;
            }
            else {
                return printAuthor(author, n.getLeft(),cio);
            }
        }
        if(n.getLeft()==null&&n.getRight()==null){
            System.out.println("Author: "+author+" not found");
            return false;
        }
        else{
            return true;
        }
    }

    // print the author if you don't care about checked in/out
    private static void printAuthor(String author){
        lib.goRoot();
        printAuthor(author,lib.current);
    }
    private static void printAuthor(String author,BTNode n){
        if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())==0){
            System.out.println("The book(s) by "+author+" are:");
            System.out.println();
            n.printBooks();
            System.out.println();
            return;
        }
        else if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())<0){
            if(n.getRight()==null){
                System.out.println("Author: "+author+" not found");
                return;
            }
            else {
                printAuthor(author, n.getRight());
            }
        }
        else if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())>0){
            if(n.getLeft()==null){
                System.out.println("Author: "+author+" not found");
                return;
            }
            else {
                printAuthor(author, n.getLeft());
            }
        }
        if(n.getLeft()==null&&n.getRight()==null){
            System.out.println("Author: "+author+" not found");
            return;
        }
    }

    private static void impInsert(Book book){
        int impSize = impBooks.GetSize();
        //System.out.println(impSize);
        impBooks.First();
        if(impSize==0){
            impBooks.InsertAfter(book);
            return;
        }
        else {
            int bookImportance = book.getImportance();
            for (int i = 0; i < impSize; i++) {
                int currImportance = impBooks.GetValue().getImportance();
                if(DEBUG==2) {
                    System.out.println("Current: " + currImportance);
                    System.out.println("Book: " + bookImportance);
                    System.out.println();
                }

                //if current book in book by importance is greater than desired book
                if (bookImportance < currImportance) {
                    impBooks.InsertBefore(book);
                    return;
                }
                impBooks.Next();
            }
            impBooks.InsertAfter(book);
            return;
        }

    }

    private static void impPrint(boolean checkedInOnly){
        System.out.println("impPrint");
        impBooks.First();
        int j=0;
        for(int i=0; i<impBooks.GetSize();i++){
//            System.out.println((i+1)+"..."+impBooks.GetValue());
//            impBooks.Next();
            if(checkedInOnly){
                Book curr = impBooks.GetValue();
                boolean ci = curr.checkedIn;
                if(ci){
                    System.out.println(j+"..."+curr);
                    j++;
                }
                impBooks.Next();
            }
            else{
                System.out.println((i+1)+"..."+impBooks.GetValue());
                impBooks.Next();
            }
        }
    }

    private static void abcInsert(Book book){
        abcBooks.First();
        for(int i = 0; i < abcBooks.GetSize(); i++){
            //if current book in abcBooks is after, the desired addBook
            if(abcBooks.GetValue().getTitle().compareTo(book.getTitle()) > 0)
            {
                abcBooks.InsertBefore(book);
                return;
            }
            //if current book in abcBooks is before the desired addBook
            if(abcBooks.GetValue().getTitle().compareTo(book.getTitle()) < 0)
            {
                abcBooks.Next();
            }
            if(abcBooks.GetValue().getTitle().compareTo(book.getTitle()) == 0)
                return;
        }
        abcBooks.InsertAfter(book);
        return;
    }

    private static void abcPrint(boolean checkedInOnly){
        System.out.println("abcPrint");
        abcBooks.First();
        int j=0;
        for(int i=0; i<abcBooks.GetSize();i++){
            if(checkedInOnly){
                Book curr = abcBooks.GetValue();
                boolean ci = curr.checkedIn;
                if(ci){
                    System.out.println(j+"..."+curr);
                    j++;
                }
                abcBooks.Next();
            }
            else{
                System.out.println((i+1)+"..."+abcBooks.GetValue());
                abcBooks.Next();
            }
        }
    }

    private static void fireLog(Book[] saved){
        //unf
    }

    private static boolean checkIO(boolean cio, String title, String author){
        checkIOAbc(cio,title);
        checkIOImp(cio,title);
        return checkIOAut(cio,title,author);
    }

    private static boolean checkIOAut(boolean cio, String title, String author){
        lib.goRoot();
        return checkIOAut(cio,title,author,lib.current);
    }
    private static boolean checkIOAut(boolean cio, String title, String author,BTNode n){
        if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())==0){
            return n.checkBook(cio,title);
        }
        else if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())>0){
            if(n.getLeft()==null){
                System.out.println("Author: "+author+" not found");
            }
            else {
                checkIOAut(cio,title,author,n.getLeft());
            }
        }
        else if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())<0){
            if(n.getRight()==null){
                System.out.println("Author: "+author+" not found");
            }
            else {
                checkIOAut(cio,title,author,n.getRight());
            }
        }
        return false;
    }

    private static void checkIOAbc(boolean cio, String title){
        abcBooks.First();
        for(int i=0; i<abcBooks.GetSize();i++){
            if(abcBooks.GetValue().getTitle().equals(title)){
                abcBooks.GetValue().setCheckedIn(cio);
            }
            else{
                abcBooks.Next();
            }
        }

    }
    private static void checkIOImp(boolean cio, String title){
        impBooks.First();
        for(int i=0; i<impBooks.GetSize();i++){
            if(impBooks.GetValue().getTitle().equals(title)){
                impBooks.GetValue().setCheckedIn(cio);
            }
            else{
                impBooks.Next();
            }
        }
    }

//    private static String getTitle(String titleNum){
//
//    }

}
