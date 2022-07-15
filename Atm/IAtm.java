package Atm;
public interface IAtm {
    public Cash withdraw(int amount) throws AtmException;
    public void addFunds(int twenties, int fifties) throws AtmException;
    public Cash remainingStock();
    public Boolean isAuthenticated(String username, int pin);
    public int getTotal();



}