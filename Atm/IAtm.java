package Atm;

// Groups all related methods for ATM
public interface IAtm {
    public Cash withdraw(int amount) throws AtmException;
    public void addFunds(int twenties, int fifties) throws AtmException;
    public Cash remainingStock();
    // Authentication is unimplemented in current version
    public Boolean isAuthenticated(String username, int pin);
    public int getTotal();



}