import java.util.Scanner;

public class Keypad {
   private Scanner input; // reads data from the command line
                         
   public Keypad() {
      input = new Scanner(System.in);    
   } 

   public int getInput() {
      //author fajar
       boolean valid=false;
       int i=0;
      while(!valid){
     try{
     i= input.nextInt();
     if(i>=0){valid=true;}
     else {valid=false; System.out.println("The format must number higher than 0. Try again : ");}
     }catch (NumberFormatException ex) {
    //handle exception here
              System.out.println("The format must number higher than 0. Try again : ");
    valid=false;
    }
      }
       return i;
      // user enters an integer
      
   } 
   
} 