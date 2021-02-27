import java.io.*;  // Import the File class
import java.util.Scanner;

public class time { 

  public static void main(String[] args) {
  	long start = System.nanoTime();
    File myObj = new File("fileQ.txt");
    if (myObj.exists()) {

        int numTestCases;

        try {
            // File file = new File("tym.txt");
            // file.createNewFile();
            // FileWriter fw = new FileWriter(file);
            Scanner sc = new Scanner(myObj);

            numTestCases = sc.nextInt();
            while(numTestCases-->0){
                int size;
                size = sc.nextInt();
                A2DynamicMem obj = new A2DynamicMem(size,3);
                int numCommands = sc.nextInt();
                // int n = numCommands;

                while(numCommands-->0) {

                    String command;
                    command = sc.next();
                    int argument;
                    argument = sc.nextInt();
                    // boolean toPrint = true;
                    int result = -5;
                    switch (command) {
                        case "Allocate":
                            result = obj.Allocate(argument);
                            break;
                        case "Free":
                            result = obj.Free(argument);
                            break;
                        case "Defragment":
                            obj.Defragment();
                            // toPrint = false;
                            break;
                        default:
                            break;
                    }

                    // if (toPrint) {
                        // System.out.println(result);
                        // System.out.println("alloc " + obj.allocBlk.sanity());
                        // System.out.println("free " + obj.freeBlk.sanity());

                    // }
                }
                
                // BufferedWriter bw = new BufferedWriter(fw);
                // bw.write(n + " " + (end - start) + "\r\n");
                // bw.flush();
            }

        }

        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    else {
      System.out.println("The file does not exist.");
    }

    long end = System.nanoTime();
    System.out.println((end - start)/1000000000.0);
  }
}