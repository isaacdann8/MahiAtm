package AtmClient;

import Atm.AtmException;
import Atm.AtmFactory;
import Atm.AtmFactory.AtmType;
import java.util.Scanner;


import Atm.IAtm;
//import Atm.*;

//import java.util.Random;


public class AtmClient {
    static Scanner _in = new Scanner(System.in);
    public static void main(String args[]) throws Exception {
        System.out.println("Initialising ATM...");
            
        IAtm atm = initialiseAtm();
        runningLoop(atm);
        
        }
    public static IAtm initialiseAtm() throws Exception {
        boolean initial = false;
        int t = Integer.MIN_VALUE;
        int f = Integer.MIN_VALUE;
        var atm = AtmFactory.createAtm(AtmType.noAuthAtm);
        while(!initial) {
            System.out.println("How Many Twenties would you like to add?");
            try{
                t = _in.nextInt();  
                if(t < 0) {
                    t = 0;
                    System.out.println("WARNING: Negative values are not accepted - No Twenties Added");
                }
            }
            catch(Exception e){
                System.out.println("Invalid input, please enter integer value");
                _in.next();
            }
            System.out.println("How Many Fifties would you like to add?");
            try{
                f = _in.nextInt();
                if(f < 0) {
                    f = 0;
                    System.out.println("WARNING: Negative values are not accepted - No Fifties Added");
                }
            }
            catch(Exception e){
                System.out.println("Invalid input, please enter integer value");
                _in.next();
            }
            if(t != Integer.MIN_VALUE && f != Integer.MIN_VALUE) {
                atm.addFunds(t, f);
                System.out.println("Successful initialised ATM with " + t + " Twenties and " + f + " Fifties");
                initial = true;
            }
            else {
                System.out.println("Invalid Input/s, please try again");
            }
        }
        return atm;
    }
    public static void runningLoop(IAtm atm) throws AtmException{
        boolean running = true;
        int input = 0;
        int amount = Integer.MIN_VALUE;
        while(running) {
            if(atm.getTotal() < 1000) {
                System.out.println("STOCK LOW! PLEASE ADD NOTES!");
            }
            System.out.println("1: For Withdrawals");
            System.out.println("2: To Add Notes");
            System.out.println("3: To Show ATM Balance");
            System.out.println("4: To Shut Down ATM");
            try {
                input = _in.nextInt();
            }
            catch(Exception e) {
                System.out.println("Invalid input, please enter integer value");
                _in.next();
            }
            if(input == 1) {
                amount = Integer.MIN_VALUE;
                System.out.println("Withdraw selected, Please enter amount to withdraw");
                try{
                    amount = _in.nextInt();
                    }
                    catch(Exception e){
                        System.out.println("Invalid input, please enter integer value");
                        _in.next();
                    }
                if(amount == Integer.MIN_VALUE) {
                    System.out.println("Withdraw failed please try again");
                }
                else {
                    try{
                        var cash = atm.withdraw(amount);
                        System.out.println("Successfully withdrawn " + cash);
                    }
                    catch(AtmException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            else if(input == 2) {
                System.out.println("Add Notes selected");
                System.out.println("Current remaining stock: "+ atm.remainingStock());
                boolean done = false;
                int t = Integer.MIN_VALUE;
                int f = Integer.MIN_VALUE;
                while(!done) {
                    System.out.println("How Many Twenties would you like to add?");
                    try{
                        t = _in.nextInt();
                        if(t < 0) {
                            t = 0;
                            System.out.println("WARNING: Negative values are not accepted - No Twenties Added");
                        }
                    }
                    catch(Exception e){
                        System.out.println("Invalid input, please enter integer value");
                        _in.next();
                    }
                    System.out.println("How Many Fifties would you like to add?");
                    try{
                        f = _in.nextInt();
                        if(f < 0) {
                            f = 0;
                            System.out.println("WARNING: Negative values are not accepted - No Fifties Added");
                        }
                    }
                    catch(Exception e){
                        System.out.println("Invalid input, please enter integer value");
                        _in.next();
                    }
                    if(t != Integer.MIN_VALUE && f != Integer.MIN_VALUE) {
                        atm.addFunds(t, f);
                        System.out.println("Successfully added notes to ATM: " + t + " Twenties and " + f + " Fifties");
                        System.out.println("New remaining stock: "+ atm.remainingStock());
                        done = true;
                    }
                    else {
                        System.out.println("Invalid Input/s, please try again");
                    }
                }
            }
            else if(input == 3) {
                System.out.println("Current remaining ATM stock: "+ atm.remainingStock());
            }
            else if(input == 4) {
                System.out.println("Shutting Down...");
                running = false;
            }
            
            else {
                System.out.println("Invalid Input, Please Try Again");
            }
            
        }
    }
}
