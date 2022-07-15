package Atm;
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


    public String toString() {
        var description = "Twenties = " + _twenties + ", Fifties = " + _fifties + ", Total = " + getTotal();
        return description;
    }

    public void addFunds(int twenties, int fifties) {
        _twenties += twenties;
        _fifties += fifties;
    }

    public int getTotal() {
        return 20 * _twenties + 50 * _fifties;
    }
    public void checkNotes() throws AtmException {
        if(_twenties < 0 || _fifties < 0) {
            throw new AtmException("Negative Number of notes, Invalid");
        }
    }

}