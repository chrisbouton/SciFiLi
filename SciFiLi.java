import java.util.Scanner;
import java.util.regex.*;
import java.util.Random;
import java.io.*;

public class SciFiLi {

    private static BT lib;
    private static int bookCount;
    public static void main(String[] args)throws Exception{

        //log reader
         File log = new File("log.txt");
        Scanner logReader = new Scanner(log);
        int logNum = Integer.parseInt(logReader.next());
        System.out.println(logNum);

        //path for current days' log
        String path = "Logs//dayOut"+logNum+".txt";
//        writer for dayOut#.txt
//        System.out.println(path);
//        FileWriter fileWriter = new FileWriter(path);
//        PrintWriter printWriter = new PrintWriter(fileWriter);
        /*
        source file and scanners
        */
        File input = new File("Logs//dayOut1.txt");
        //File input = new File(path);
        // reads the File input
        Scanner reader = new Scanner(input);//file);
        // takes user input
        Scanner UI = new Scanner(System.in);

//        //update log
//        FileWriter logWriter = new FileWriter("src//log.txt");
//        PrintWriter logPrinter = new PrintWriter(logWriter);
//        logPrinter.print(logNum+1);
//        logPrinter.close();
//
//        //write to dayOut#.txt
//        printWriter.println(logNum);
//        printWriter.println("bookTitle, bookAuthor, Cio, importance");
//        printWriter.close();

        /*
        Static variables
         */

        lib = new BT();
        bookCount = 0;

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
            currBook.setCheckedIn(CIn);
            //<<
            lib.insert(currBook);
            //#Chris# lib.abcInsert(currBook);
            bookCount++;
            //System.out.println(currBook);

        }
        lib.inorder();

        /*
        Main loop for UI menu
         */
        System.out.println("Welcome!");
        while (true){
            System.out.println("Menu Options: ");
            System.out.println("1 : Search for a book");
            System.out.println("2 : Access Lists");
            System.out.println("3 : Check in a book");
            System.out.println("4 : Check out a book");
            System.out.println("5 : Process the returns");
            System.out.println("6 : Add a new Book");
            System.out.println("7 : Run a Fire Drill");
            System.out.println("8 : Exit the program");
            System.out.println();
            /*
            menu executables
            */
            //>>
            String UIin = UI.nextLine();
            // #1# search
            if(UIin.equals("1")){
                System.out.println("You selected to: ");
                System.out.println("Search for a book");
                System.out.println();
                System.out.println("Who is the Author of the book?");
                System.out.println();
                String currAuthor = UI.next().trim();
                System.out.println("Would you like to: ");
                System.out.println("1 : Look for a specific a title");
                System.out.println("2 : See all books by "+currAuthor);
                System.out.println();
                String searchChoice = UI.next().trim();

                if(searchChoice.equals("1")){
                    System.out.println("What is the Title of the Book?");
                    System.out.println();
                    String bookTitle = UI.next().trim();

                    System.out.println("Im sorry, I have not implemented this feature yet");
                    System.out.println("Unable to find: "+bookTitle);
                    System.out.println();
                    /*
                    * find the book and see if it is in or checked out
                    */
                }
                else if(searchChoice.equals("2")){
                    System.out.println("The books by "+currAuthor+" are:");
                    System.out.println();

                    System.out.println("Im sorry, I have not implemented this feature yet");
                    /*
                    * Print the books
                    */
                }
                else{
                    System.out.println("Invalid entry");
                }
            }
            // #2# lists
            else if(UIin.equals("2")){
                System.out.println("You selected to: ");
                System.out.println("Access Lists");
                System.out.println();
                System.out.println("Do you want: ");
                System.out.println("1 : Only books that are checked in");
                System.out.println("2 : All books in the system");
                String cioChoice = UI.next().trim();
                boolean onlyCheckedIn;
                if(cioChoice.equals("1")){
                    onlyCheckedIn = true;
                }
                else onlyCheckedIn = false;
                System.out.println("Would you like to display lists by Title or Author?");
                System.out.println("1 : Title");
                System.out.println("2 : Author");
                System.out.println();
                String listChoice = UI.next().trim();
                if(listChoice.equals("1")){
                    if(onlyCheckedIn){
                        System.out.println("Checked in Books by Title: ");
                        System.out.println();
                    }
                    else{
                        System.out.println("All Books sorted by Title: ");
                        System.out.println();
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
                else{
                    System.out.println("Invalid entry");
                }
                System.out.println("Im sorry, I have not implemented this feature yet");

            }
            // #3# check in
            else if(UIin.equals("3")){
                System.out.println("You selected to: ");
                System.out.println("Check in a book");
                System.out.println();

                System.out.println("Who is the Author of the book?");
                System.out.println();
                String currAuthor = UI.next().trim();
                System.out.println("What is the Title of the Book?");
                System.out.println();
                String bookTitle = UI.next().trim();

                /*
                find the book and check it in
                 */
                System.out.println("Im sorry, I have not implemented this feature yet");
            }
            // #4# check out
            else if(UIin.equals("4")){
                System.out.println("You selected to: ");
                System.out.println("Check out a book");
                System.out.println();

                System.out.println("Who is the Author of the book?");
                System.out.println();
                String currAuthor = UI.next().trim();
                System.out.println("What is the Title of the Book?");
                System.out.println();
                String bookTitle = UI.next().trim();
                /*
                find the book and check it out
                 */
                System.out.println("Im sorry, I have not implemented this feature yet");
            }
            // #5# returns
            else if(UIin.equals("5")){
                System.out.println("You selected to: ");
                System.out.println("Process the returns");
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
                    bookAdd.setCheckedIn(true);
                    lib.insert(bookAdd);
                }
                // #c# lolz
                else if(nbChoice.equals("2")){
                    System.out.println("The Imortal Cthulhu presents you");
                    System.out.println("a Tome of Abyssal Knowlege: ");
                    Random rand = new Random();
                    int tLen = 8+rand.nextInt(8);
                    String title = "";
                    for(int i = 0; i < tLen; i++){
                        char c = (char) (rand.nextInt(26) + 'a');
                        title += c;
                    }
                    int aLen = 4+rand.nextInt(11);
                    String author = "";
                    for(int i = 0; i < aLen; i++){
                        char c = (char) (rand.nextInt(26) + 'a');
                        author += c;
                    }
                    System.out.println(title+" by "+author);
                    int currImportance = bookCount+1;
                    Book bookAdd = new Book(title,author,currImportance);
                    bookAdd.setCheckedIn(true);
                    lib.insert(bookAdd);

                    System.out.println("Continue? y/n");
                    String cont = UI.next().trim();
                    if(!(cont.equals("y")||cont.equals("Y"))){
                        break;
                    }
                }
                else{
                    System.out.println("Invalid entry");
                }
                //System.out.println("Im sorry, I have not implemented this feature yet");
            }
            // #7# fire
            else if(UIin.equals("7")){
                System.out.println("You selected to: ");
                System.out.println("Run a Fire Drill");
                Random rand = new Random();
                int numOfBook = rand.nextInt((bookCount/2)+1);
                System.out.println("You managed to save "+numOfBook+" books");
                System.out.println("Im sorry, I have not implemented this feature yet");
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
    }

    static void printAllNodes(){
        lib.goRoot();
        printAllNode(lib.current);
    }

    static void printAllNode(BTNode n)throws NullPointerException{
        System.out.println("Book(s) by: "+n.getAuthor());
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
}
