package Atm;

public abstract class AtmBase implements IAtm{
    public AtmBase() {
        _stock = new Cash(0, 0);
    }
    protected Cash _stock;
    private int dailyLimit = 1000;
    public Cash withdraw(int amount) throws AtmException {
        if(amount > dailyLimit) {
            throw new AtmException("Withdraw limit is $" + dailyLimit);
        }
        if(amount <= 0) {
            throw new AtmException("Negative Withdraws are invalid");
        }
        if(amount > _stock.getTotal()) {
            throw new AtmException("Not enough total cash in ATM");
        }
        int tempRem = amount % 50;
        int retFifties = 0;
        int retTwenties = 0;
        if(tempRem % 20 == 0) {
            retFifties = (amount - tempRem) / 50;
            retTwenties = tempRem / 20;
        }
        else if(amount > 50 && (tempRem + 50) % 20 == 0) {
            retFifties = (amount - tempRem - 50) / 50;
            retTwenties = (tempRem + 50) / 20;
        }
        else {
            throw new AtmException("Invalid withdrawal amount: " + amount);
        }
        boolean finished = false;
        while(!finished) {
            if(_stock.getFifties() - retFifties >= 0 && _stock.getTwenties() - retTwenties >= 0 && retFifties >= 2) {
                if(_stock.getFifties() - retFifties < _stock.getTwenties() - retTwenties - 5){
                    retFifties -= 2;
                    retTwenties += 5;
                }
                else {
                    finished = true;
                }
            }
            else if(_stock.getFifties() - retFifties >= 0 && _stock.getTwenties() - retTwenties >= 0) {
                finished = true;
            }
            else if(_stock.getFifties() - retFifties < 0 && _stock.getTwenties() - retTwenties >= 5 && retFifties >= 2) {
                retFifties -= 2;
                retTwenties += 5;
            }
            else {
                retFifties = 0;
                retTwenties = 0;
                throw new AtmException("ATM Error, not enough notes");
            }
        }
        _stock.addFunds(-retTwenties, -retFifties);
        return new Cash(retTwenties, retFifties);
    }
    public void addFunds(int twenties, int fifties) throws AtmException {
        _stock.addFunds(twenties, fifties);
    }
    public Cash remainingStock() {
        return _stock;
    }
    public int getTotal() {
        return _stock.getTotal();
    }
    public abstract Boolean isAuthenticated(String username, int pin);
}
