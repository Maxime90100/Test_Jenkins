package izly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PurseUniTest {

    private CodeSecret secretCode;

    @BeforeEach
    public void setup() throws Exception {
        secretCode = Mockito.mock(CodeSecret.class);
        Mockito.when(secretCode.verifierCode("9876")).thenReturn(true);

    }

    @Test
    public void testCredit() throws Exception {
       Purse purse = new Purse(100,secretCode);
       double solde = purse.getSolde();
       purse.credite(50);
       Assertions.assertEquals(solde + 50, purse.getSolde());
    }

    @Test
    public void testDebit() throws Exception {
        Purse purse = new Purse(100,secretCode);
        purse.credite(50);
        double solde = purse.getSolde();
        purse.debite(50, "9876");
        Assertions.assertEquals(solde - 50, purse.getSolde());
    }

    @Test
    public void testSoldeToujoursPositifSurDebit() {
        Purse purse = new Purse(100,secretCode);
        double solde = purse.getSolde();
        Assertions.assertThrows(SoldeNegatifInterdit.class, () -> purse.debite(solde + 1, "9876"));

    }

    @Test
    public void testSoldeToujoursInf√©rieurAuPlafondSurCredit() throws Exception {
        double plafond = 100;
        Purse purse = new Purse(plafond,secretCode);
        Assertions.assertThrows(DeplacementPlafondInterdit.class, () -> purse.credite( plafond + 1));

    }

    @Test
    public void testD√©bitJamaisNegatif() throws Exception {
        Purse purse = new Purse(100,secretCode);
        Assertions.assertThrows(MontantNegatifInterdit.class, ()->purse.debite(-1, "9876"));
    }

    @Test
    public void testCreditJamaisNegatif() throws Exception {
        Purse purse = new Purse(100,secretCode);
        Assertions.assertThrows(MontantNegatifInterdit.class, ()->purse.credite(-1));
    }

    @Test
    public void testDebitRejeteSurCodeFaux() throws Exception {
        CodeSecret codeSecret = Mockito.mock(CodeSecret.class);
        Mockito.when(codeSecret.verifierCode("1234")).thenReturn(false);
        Purse purse = new Purse(100, codeSecret);
        purse.credite(50);
        Assertions.assertThrows(CodePinFaux.class, ()->purse.debite(20, "1234"));
    }

    @Test
    public void testDebitRejet√©SurCodeBloqu√©() throws Exception{
        CodeSecret codeSecret = Mockito.mock(CodeSecret.class);
        Mockito.when(codeSecret.verifierCode("9876")).thenThrow(new CodeBloqueException());
        Purse purse = new Purse(100, codeSecret);
        purse.credite(50);
        Assertions.assertThrows(CodeBloqueException.class, ()->purse.debite(20, "9876"));
    }

    @Test
    public void testCreditRejet√©SurCodeBloqu√©() throws Exception{
        CodeSecret codeSecret = Mockito.mock(CodeSecret.class);
        Mockito.when(codeSecret.verifierCode("9876")).thenThrow(new CodeBloqueException());
        Purse purse = new Purse(100, codeSecret);
        purse.credite(50);
        Assertions.assertThrows(CodeBloqueException.class, ()->purse.credite(20));
    }

}
