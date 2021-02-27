import java.io.*;
import java.util.Scanner;



public class Driver{
    public static void main(String args[]){
        int numTestCases;
        Scanner sc = new Scanner(System.in);
        numTestCases = sc.nextInt();


    	while(numTestCases-->0){
            int size;
            size = sc.nextInt();
            A2DynamicMem obj = new A2DynamicMem(size,2);
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

                if (toPrint == true) {
                    System.out.println(result);
                    System.out.println("alloc " + obj.allocBlk.sanity());
                    System.out.println("free " + obj.freeBlk.sanity());
                }
            }
            
        }




        


        // A1DynamicMem obj = new A1DynamicMem(100);
        // obj.Allocate(100);
        // obj.Allocate(10);
        // obj.Free(0);


        // Dictionary curr = obj.freeBlk.getFirst();
        // while (curr != null) {
        //     System.out.println(curr.address);
        //     curr = curr.getNext();
        // }

        // curr = obj.allocBlk.getFirst();
        // while (curr != null) {
        //     System.out.println(curr.address);
        //     curr = curr.getNext();
        // }

        // A1List obj = new A1List();
        // obj = obj.Insert(0,5,5);
        // obj = obj.Insert(5,15,15);
        // obj = obj.Insert(20,5,5);
        // System.out.println(obj.Find(6,false).address);
        // A1List del = new A1List(20,5,5);

        // obj.Delete(del);

        // A1List curr = obj.getFirst();
        // while (curr.key != -1) {
        //     System.out.println(curr.address);
        //     curr = curr.getNext();
        // }
        
    }
}


