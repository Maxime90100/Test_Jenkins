package izly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WalletUnitTest {
    @Test
    public void testCredit() throws Exception {
        Wallet wallet = new Wallet();
        double balance = wallet.getBalance();
        wallet.credit(50);
        Assertions.assertEquals(balance + 50, wallet.getBalance());
    }

    @Test
    public void testDebit() throws Exception {
        Wallet wallet = new Wallet();
        wallet.credit(50);
        double balance = wallet.getBalance();
        wallet.debit(50, "xxxx");
        Assertions.assertEquals(balance - 50, wallet.getBalance());
    }

    @Test
    public void testBalanceStillPositiveOnDebit() {
        Wallet wallet = new Wallet();
        double balance = wallet.getBalance();
        Assertions.assertThrows(NegativeBalanceProhibited.class, () -> wallet.debit(balance+1));
    }

    @Test
    public void testBalanceStillInferiorOfCreditLimit() {
        double creditLimit = 100;
        Wallet wallet = new Wallet(creditLimit);
        Assertions.assertThrows(PassingLimitProhibited.class, () -> wallet.credit(creditLimit+1));
    }

    @Test
    public void testBalanceNeverNegative() {
        Wallet wallet = new Wallet();
        wallet.credit(-50);
        Assertions.assertTrue(wallet.getBalance() >= 0);
    }

    @Test
    public void TestCreditNegativeProhibited() {
        Wallet wallet = new Wallet();
        Assertions.assertThrows(NegativeCreditProhibited.class, () -> wallet.credit(-50));
    }

    @Test
    public void testDebitWrongCode() throws Exception {
        SecretCode secretCode = Mockito.mock(SecretCode.class);
        Mockito.when(secretCode.checkCode("1234")).thenReturn(false);
        Wallet wallet = new Wallet(100, secretCode);
        wallet.credit(50);
        Assertions.assertThrows(WrongCodeException.class, () -> wallet.debit(20));
    }
}
