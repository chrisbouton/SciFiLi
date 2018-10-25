import java.util.Scanner;
import java.util.regex.*;
import java.util.Random;
import java.io.*;

public class SciFiLi {

    private static BT lib;
    public static void main(String[] args)throws Exception{

        /*
        source file and scanners
        */

        File input = new File("src//input.txt");
        // reads the File input
        Scanner reader = new Scanner(input);//file);
        // takes user input
        Scanner UI = new Scanner(System.in);


        /*
        Static variables
         */

        lib = new BT();

        while(reader.hasNextLine()){
            /*
            Parse Line into Book
             */
            //>>
            String rIn = reader.nextLine();//.replaceAll("\\s", "");
            String[] rArr = rIn.split(",");
            String title = rArr[0];
            String author = rArr[1].trim();
            String CIOparse = rArr[2].trim();
            //System.out.println("#"+CIOparse+"#");
            int importance = Integer.parseInt(rArr[3].trim());
            boolean CIn;
            if(CIOparse.equals("1")){
                CIn = true;
            }
            else if(CIOparse.equals("0")){
                CIn = false;
            }
            else{
                System.out.println("Checked In/Out Error");
                CIn = false;
            }
            Book currBook = new Book(title,author,importance);
            currBook.setCheckedIn(CIn);
            //<<
            
            System.out.println(currBook);
        }
        //while (UI.hasNextLine()){

        //}
    }
}
