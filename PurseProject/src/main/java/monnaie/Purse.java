package monnaie;

import org.mockito.Mockito;
import verrouillage.CodeSecret;



public class Purse {

    private double solde;
    Purse fakePurse ;
    CodeSecret codeSecret ;
    public Purse(CodeSecret codeSecret) {
        this.codeSecret=codeSecret ;
        fakePurse= Mockito.mock(Purse.class);
        Mockito.when(fakePurse.getSolde()).thenReturn(solde);
    }

    public void credit(double solde) {
        this.solde=solde;
    }

    public double getSolde() {
        return  fakePurse.getSolde();
    }

    public void debit(double montantDebit, String goodCode) throws TransactionRejeteeException {
        if (!codeSecret.verifierCode(goodCode)){
            throw new TransactionRejeteeException();
        }
        this.solde-=montantDebit ;
    }
}
