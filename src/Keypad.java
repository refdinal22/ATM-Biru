import java.util.Scanner;

public class Keypad {
   private Scanner input; // reads data from the command line
                         
   public Keypad() {
      input = new Scanner(System.in);    
   } 

   public int getInput() {
      //author fajar
      int number;
    do {
    while (!input.hasNextInt()) {
        System.out.print("Invalid input!\nTry again!");
        input.next(); // this is important!
    }
    number = input.nextInt();
    if(number<0){
        System.out.print("Invalid input!\nTry again!");
    }
    } while (number < 0);
       return number;
      // user enters an integer
      
   } 
    
} 