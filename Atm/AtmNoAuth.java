package Atm;

public class AtmNoAuth extends AtmBase {
    public Boolean isAuthenticated(String username, int pin) {
        return true;
    }
}
