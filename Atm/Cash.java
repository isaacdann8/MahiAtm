package Atm;
// Cash class that represents A collection of Fifties and Twenties.
// Used in Machine as stock and as withdraws from Users.
public class Cash {
    public Cash(int twenties, int fifties){
        _twenties = twenties;
        _fifties = fifties;
    }
    private int _twenties = 0;
    private int _fifties = 0;

    public int getTwenties() {
        return _twenties;
    }
    public int getFifties() {
        return _fifties;
    }

    // Method that prints number of each note and the Total
    public String toString() {
        var description = "Twenties = " + _twenties + ", Fifties = " + _fifties + ", Total = " + getTotal();
        return description;
    }

    // Method to Add (Or Subtract) Funds from Machine
    public void addFunds(int twenties, int fifties) {
        _twenties += twenties;
        _fifties += fifties;
    }

    // Method that gets total value from Notes
    public int getTotal() {
        return 20 * _twenties + 50 * _fifties;
    }
    public void checkNotes() throws AtmException {
        if(_twenties < 0 || _fifties < 0) {
            throw new AtmException("Negative Number of notes, Invalid");
        }
    }

}