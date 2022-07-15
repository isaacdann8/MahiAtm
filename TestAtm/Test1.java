package TestAtm;

import Atm.AtmFactory;
import Atm.AtmFactory.AtmType;
import Atm.*;

import java.util.Random;

public class Test1 {
    public static void main(String[] args) throws Exception {
        System.out.println("Testing ATM");
        var atm = AtmFactory.createAtm(AtmType.noAuthAtm);
        atm.addFunds(5, 3);
        //Tests basic 60 dollar withdraww
        testWithdrawal(60, atm);

        //Tests where ATM doesn't carry enough valid notes to carry out withdrawal
        testForFails(60, atm, "ATM Notes Test");

        //Tests that ATM doesn't allow withdrawals above our upper limit 1000
        testForFails(1010, atm, "Upper Limit Test");

        //Tests that ATM doesn't allow withdrawals below our lower limit 0
        testForFails(-10, atm, "Lower Limit Test");

        //Tests that ATM doesn't allow withdrawals from invalid values
        testForFails(252, atm, "Invalid withdraw value Test");

        //Tests basic valid Withdraw
        testWithdrawal(170, atm);

        atm.addFunds(20, 20);

        //Tests two invalid values, 10 and 30
        testForFails(10, atm, "Invalid withdrawal value Test");
        testForFails(30, atm, "Invalid withdrawal value Test");

        //Tests ATM can withdraw values while keeping number of 50s above 0 *NOT FULLY BALANCED*
        testWithdrawal(520, atm);
        testWithdrawal(520, atm);
        testWithdrawal(180, atm);
        testWithdrawal(200, atm);

        //Tests all values where we have enough money
        testAllWithdraws(10000, 10000);

        //Tests random valid withdrawals to test balancing
        randomWithdraws(200, 10000);
        randomWithdraws(10000, 200);
        randomWithdraws(1000 , 1000);
        randomWithdraws(1000, 300);
    }
    public static void testWithdrawal(int amount, IAtm atm) throws Exception {
        System.out.println("The atm has remaining: " + atm.remainingStock());
        var before = atm.getTotal();
        var cash = atm.withdraw(amount);
        System.out.println(cash);
        var after = atm.getTotal();
        System.out.println("The atm has remaining: " + atm.remainingStock());
        TestUtil.test("Withdrawal", before == after + amount, "ERROR : incorrect new total");
    }
    public static void testForFails(int amount, IAtm atm, String testDetail) throws Exception{
        try{
            testWithdrawal(amount, atm);
            System.out.println(testDetail + " Failed");
        }
        catch(AtmException e) {
            System.out.println(testDetail + " Passed");
        }
    }
    public static void testAllWithdraws(int twenties, int fifties) throws Exception {
        int amount = 50;
        var atm = AtmFactory.createAtm(AtmType.noAuthAtm);
        atm.addFunds(twenties, fifties);
        while(amount < 1000) {
            testWithdrawal(amount, atm);
            amount += 10;
        }
    }
    public static void randomWithdraws(int twenties, int fifties) throws Exception {
        var rand = new Random();
        var atm = AtmFactory.createAtm(AtmType.noAuthAtm);
        atm.addFunds(twenties, fifties);
        int numberOfWithdraws = 0;
        while(numberOfWithdraws < 100) {
            try {
                testWithdrawal((10 * rand.nextInt(97) + 40), atm);
            }
            catch(AtmException e) {
                System.out.println(e.getMessage());
            }
            numberOfWithdraws += 1;
            
        }
    }
}

