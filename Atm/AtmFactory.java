package Atm;

public class AtmFactory {
    public enum AtmType {
        noAuthAtm, 
        authAtm,
        multiCurrencyAtm
    }

    public static IAtm createAtm(AtmType t) {
        switch(t) {
            case noAuthAtm:
                return new AtmNoAuth();
            case authAtm:
                throw new UnsupportedOperationException("method not implemented");
            case multiCurrencyAtm:
                throw new UnsupportedOperationException("method not implemented");
            default:
                throw new UnsupportedOperationException("invalid ATM type");
        }
    }
}
