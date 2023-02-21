package izly;

public class Wallet {
    private SecretCode code;
    private double balance = 0;
    private double creditLimit = 0;

    public Wallet(double i) {
        this.creditLimit = i;
    }

    public Wallet(double i, SecretCode code) {
        this.creditLimit = i;
        this.code = code;
    }

    public Wallet() {
        creditLimit = 100;
        code = new SecretCode("1234");
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount, SecretCode code) throws NegativeBalanceProhibited, WrongCodeException {
        if (balance - amount < 0) {
            throw new NegativeBalanceProhibited();
        }
        if (this.code.checkCode(code.getCode())) {
            balance -= amount;
        } else {
            throw new WrongCodeException();
        }
    }
}
