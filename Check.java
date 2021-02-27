import java.io.*;  // Import the File class
import java.util.Scanner;

public class Check { 

  public static void main(String[] args) {
    File myObj = new File("fileQ.txt");
    if (myObj.exists()) {

        int numTestCases;

        try {
            File file = new File("myans.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            Scanner sc = new Scanner(myObj);

            numTestCases = sc.nextInt();
            while(numTestCases-->0){
                int size;
                size = sc.nextInt();
                A2DynamicMem obj = new A2DynamicMem(size,3);
                int numCommands = sc.nextInt();
                while(numCommands-->0) {
                    String command;
                    command = sc.next();
                    int argument;
                    argument = sc.nextInt();
                    boolean toPrint = true;
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
                            toPrint = false;
                            break;
                        default:
                            break;
                    }

                    if (toPrint) {
                        // System.out.println(result);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(result + "\r\n");
                        bw.flush();
                        // System.out.println("alloc " + obj.allocBlk.sanity());
                        // System.out.println("free " + obj.freeBlk.sanity());

                    }
                }
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
  }
}