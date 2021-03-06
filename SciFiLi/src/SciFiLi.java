/**
 * @author Andrew Hall Chris Bouton
 * @version 3.0 (nearly there)
 *
 * This is the MAIN class for SciFiLi which will run the full Library program
 *
 * A Library has Books read from the last dayOut file
 * A Library can check in and out Books
 *           can make new Books
 *           can display Books in various ways
 *           can run Fire Drills to see what books ge saved
 *
 */
import java.util.Scanner;
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
        System.out.println("Day "+logNum);

        //path for current days' log
        String path = "src//Logs//dayOut"+logNum+".txt";
        //writer for dayOut#.txt
        //System.out.println(path);
        /*
        source file and scanners
        */
        //File input = new File("src//Logs//dayOut1.txt");
        File input = new File(path);
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
                    System.out.println("Please use _ for space.");
                    String currTitle = UI.next().trim();
                    currTitle = currTitle.replaceAll("[_]"," ");
                    if(!search(currTitle,currAuthor)){
                        System.out.println("Unable to find: "+currTitle);
                        System.out.println();
                        System.out.println("would you like to add this book?");
                        System.out.println("1 : yes");
                        System.out.println("2 : no");
                        String addChoice = UI.next().trim();
                        if(addChoice.equals("1")){
                            Book addBook = new Book(currTitle,currAuthor,bookCount+1);
                            addBook.hardCheckIn(true);
                            lib.insert(addBook);
                            impInsert(addBook);
                            abcInsert(addBook);
                        }
                    }
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
                        printAuthorsCio(true);
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
                    System.out.println("Use _ for spaces");
                    String currTitle = UI.next();
                    currTitle = currTitle.replaceAll("[_]"," ");
                    //System.out.println(currTitle);
                    if(!checkIO(true,currTitle,currAuthor)){
                        System.out.println("True");
                    }
                    else{
                        System.out.println("False");
                    }
                }
                else{
                    System.out.println("No books to check out.");
                }
            }
            // #4# check out
            else if(UIin.equals("4")){
                System.out.println("You selected to: ");
                System.out.println("Check out a book\n");
                System.out.println("Who is the Author of the book?");
                String currAuthor = UI.next().trim();
                if(printAuthor(currAuthor,true)){
                    System.out.println("What is the Title of the Book?");
                    System.out.println("Use _ for spaces");
                    String currTitle = UI.next();
                    currTitle = currTitle.replaceAll("[_]"," ");
                    System.out.println(currTitle);
                    if(!checkIO(false,currTitle,currAuthor)){
                        System.out.println("True");
                    }
                    else{
                        System.out.println("False");
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
                System.out.println();
                Random rand = new Random();
                //call to get up to 15 books to return
                int numToReturn = rand.nextInt(15)+1;
                // list returns the first so many books to return
                System.out.println(numToReturn+" book(s) were returned: ");
                Book[] returns = getCheckedOut(numToReturn);
                for(int i=0;i<returns.length;i++){
                    String currTitle = returns[i].getTitle();
                    String currAuthor = returns[i].getAuthor();
                    checkIO(true,currTitle,currAuthor);
                    System.out.println((i+1)+"..."+returns[i]);
                }
                System.out.println();
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
                //fireLog(saved);
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
        UI.close();

        //update log
        String newPath = "src//Logs//dayOut"+(logNum+1)+".txt";
        FileWriter logWriter = new FileWriter("src//log.txt");
        PrintWriter logPrinter = new PrintWriter(logWriter);
        logPrinter.print(logNum+1);
        logPrinter.close();

        //writes books to the new file
        FileWriter fileWriter = new FileWriter(newPath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        //write to dayOut#.txt
        impBooks.First();
        for(int i=0;i<impBooks.GetSize()-1;i++){
            printWriter.println(impBooks.GetValue().toWrite());
            impBooks.Next();
        }
        //write the last book without a new line char
        printWriter.print(impBooks.GetValue().toWrite());
        printWriter.close();
    }

    // print all the authors if you don't care about checked in/out
    private static void printAllNodes() {
        lib.goRoot();
        printAllNode(lib.current);
    }
    private static void printAllNode(BTNode n) throws NullPointerException {
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

    // print all the authors if you care about checked in/out
    private static void printAuthorsCio(boolean cio){

        lib.goRoot();
        printAuthorsCio(cio,lib.current);
    }
    private static void printAuthorsCio(boolean cio,BTNode n){
        String aut = n.getAuthor();
        String io;
        if(cio) io = " in";
        else io = " out";
        System.out.println("Book(s) checked"+io+" by "+aut);
        n.printBooks(cio);
        if(n.getLeft()!=null){
            //System.out.println("left");
            printAuthorsCio(cio,n.getLeft());
        }
        if(n.getRight()!=null){
            //System.out.println("right");
            printAuthorsCio(cio,n.getRight());
        }
    }

    // print the author if you care about checked in/out
    private static boolean printAuthor(String author,boolean cio) {
        lib.goRoot();
        return printAuthor(author,lib.current,cio);
    }
    private static boolean printAuthor(String author,BTNode n,boolean cio) {
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
    private static void printAuthor(String author) {
        lib.goRoot();
        printAuthor(author,lib.current);
    }
    private static void printAuthor(String author,BTNode n) {
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

    private static boolean search(String title, String author){
        lib.goRoot();
        return search(title,author,lib.current);
    }
    private static boolean search(String title, String author,BTNode n){
        if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())==0){
            List<Book> currBooks = n.getBooks();
            currBooks.First();
            boolean found = false;
            for(int i=0; i<currBooks.GetSize();i++){
                if(currBooks.GetValue().getTitle().toLowerCase().equals(title.toLowerCase())){
                    System.out.println(currBooks.GetValue());
                    found = true;
                    break;
                }
                currBooks.Next();
            }
            return found;
        }
        else if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())<0){
            if(n.getRight()==null){
                System.out.println("Author: "+author+" not found");
                return false;
            }
            else{
                search(title, author, n.getRight());
            }
        }
        else if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())>0){
            if(n.getLeft()==null){
                System.out.println("Author: "+author+" not found");
                return false;
            }
            else{
                search(title, author, n.getLeft());
            }
        }
        return false;
    }

    // insert by importance
    private static void impInsert(Book book) {
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

    // print by importance
    private static void impPrint(boolean checkedInOnly) {
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

    // insert alphabetically by title
    private static void abcInsert(Book book) {
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

    // print alphabetically by title
    private static void abcPrint(boolean checkedInOnly) {
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

    //private static void fireLog(Book[] saved){
        //unf
    //}

    // check in or out a book
    private static boolean checkIO(boolean cio, String title, String author) {
        //checkIOAbc(cio,title);
        //checkIOImp(cio,title);
        return checkIOAut(cio,title,author);
    }

    // check in or out by author
    private static boolean checkIOAut(boolean cio, String title, String author) {
        lib.goRoot();
        return checkIOAut(cio,title,author,lib.current);
    }
    private static boolean checkIOAut(boolean cio, String title, String author,BTNode n) {
        if(n.getAuthor().toLowerCase().compareTo(author.toLowerCase())==0){
            System.out.println("AUT");
            boolean ret = n.checkBook(cio,title);
            //System.out.println(ret);
            return ret;
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

    // unused for now
    private static void checkIOAbc(boolean cio, String title) {
        abcBooks.First();
        for(int i=0; i<abcBooks.GetSize();i++){
            if(abcBooks.GetValue().getTitle().toLowerCase().equals(title.toLowerCase())){
                abcBooks.GetValue().setCheckedIn(cio);
                System.out.println("ABC");
                return;
            }
            else{
                abcBooks.Next();
            }
        }

    }
    private static void checkIOImp(boolean cio, String title) {
        impBooks.First();
        for(int i=0; i<impBooks.GetSize();i++){
            if(impBooks.GetValue().getTitle().toLowerCase().equals(title.toLowerCase())){
                impBooks.GetValue().setCheckedIn(cio);
                System.out.println("IMP");
                return;
            }
            else{
                impBooks.Next();
            }
        }
    }

    // future proofing
    private static Book[] getCheckedOut() {
        int size = impBooks.GetSize();
        impBooks.First();
        Book[] preCheckedOut = new Book[size];
        int j = 0;
        for(int i=0;i<size;i++){
            if(!impBooks.GetValue().getcheckedIn()){
                preCheckedOut[j]=impBooks.GetValue();
                j++;
            }
            impBooks.Next();
        }
        Book[] postCheckedOut = new Book[j];
        for(int k=0;k<j;k++){
            postCheckedOut[k]=preCheckedOut[k];
        }
        return postCheckedOut;
    }
    private static Book[] getCheckedIn() {
        int size = impBooks.GetSize();
        impBooks.First();
        Book[] preCheckedOut = new Book[size];
        int j = 0;
        for(int i=0;i<size;i++){
            if(impBooks.GetValue().getcheckedIn()){
                preCheckedOut[j]=impBooks.GetValue();
                j++;
            }
            impBooks.Next();
        }
        Book[] postCheckedOut = new Book[j];
        for(int k=0;k<j;k++){
            postCheckedOut[k]=preCheckedOut[k];
        }
        return postCheckedOut;
    }

    // returns a randomized list of books that are checked out size [0-lim]
    // if the num of checked out books is less than lim, it returns all of them
    private static Book[] getCheckedOut(int lim) {
        int size = impBooks.GetSize();
        impBooks.First();
        // array big enough that it could fit all books if they were all checked out
        Book[] preCheckedOut = new Book[size];
        int j = 0;//counts checked out
        //make a list with size of all books
        //filled only with checked out books
        for(int i=0;i<size;i++){
            if(!impBooks.GetValue().getcheckedIn()){
                preCheckedOut[j]=impBooks.GetValue();
                j++;
            }
            impBooks.Next();
        }
        //there are more checked out books than lim
        if(j>lim){
            // get a string with lim # of rand ints
            // at this point may contain dupes
            Random rand = new Random();
            String indecies = ""+rand.nextInt(j);
            for(int n=0;n<(lim-1);n++){
                indecies +=","+rand.nextInt(j);
            }
            //System.out.println(indecies);
            // turn the string into an array then
            // parse the string array into an int array
            String[] inds = indecies.split(",");
            int[] ints = new int[inds.length];
            for(int m=0;m<inds.length;m++){
                ints[m]= Integer.parseInt(inds[m]);
            }
            // remove dupes
            for(int k=0;k<ints.length;k++){
                for(int h=k+1;h<ints.length;h++){
                    if(ints[k]==ints[h]){
                        ints[h]+=1;
                    }
                }
            }
            //add the books to the list
            Book[] postCheckedOut = new Book[lim];
            for (int k = 0; k < lim; k++) {
                int thisIndex = ints[k];
                postCheckedOut[k] = preCheckedOut[thisIndex];
            }
            return postCheckedOut;
        }
        // there are less or equal checked out books than lim
        else {
            // add all j books to a list and return them
            Book[] postCheckedOut = new Book[j];
            for (int k = 0; k < j; k++) {
                postCheckedOut[k] = preCheckedOut[k];
            }
            return postCheckedOut;
        }
    }
}