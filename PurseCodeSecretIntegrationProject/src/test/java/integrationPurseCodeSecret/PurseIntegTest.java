package integrationPurseCodeSecret;

import monnaie.Purse;
import monnaie.TransactionRejeteeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verrouillage.CodeSecret;

import java.security.CodeSigner;

public class PurseIntegTest {

    private CodeSecret codeSecret ;
    private Purse purse ;
    private double solde =100 ;
    private double montantDebit =20;
    private String goodCode ;
    private String badCode;

    @BeforeEach
    public void setup(){
        codeSecret = new CodeSecret();
        goodCode=codeSecret.revelerCode();
        badCode =goodCode+"1" ;
        purse= new Purse(codeSecret) ;
        purse.credit(solde) ;
    }

    @Test
    public void testDebitAvecCodeValide() throws TransactionRejeteeException {
        purse.debit(montantDebit,goodCode) ;
        Assertions.assertEquals(solde-montantDebit , purse.getSolde());
    }
// a modifier
//    public void testDebitAvecCodeInvalide() throws TransactionRejeteeException {
//        purse.debit(montantDebit,goodCode) ;
//        Assertions.assertEquals(solde-montantDebit , purse.getSolde());
//    }
}
