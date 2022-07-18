package Atm;

public class AtmFactory {
    public enum AtmType {
        noAuthAtm, 
        authAtm,
        multiCurrencyAtm
    }
    /* Method to create ATM. Currently only supported type is No Auth which lets anybody use it. This 
    leaves room open for development of more features such as different currencys and authorisation */
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
