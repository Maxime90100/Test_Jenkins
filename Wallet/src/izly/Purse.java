package izly;

public class Purse {

    private double solde = 0;
    private double plafond;
    private CodeSecret codeSecret;

    public Purse(double plafond, CodeSecret codeSecret) {
        this.plafond = plafond;
        this.codeSecret = codeSecret;
    }

    public double getSolde() {
        return solde;
    }
    public void credite(double montant) throws DeplacementPlafondInterdit, MontantNegatifInterdit {
        if(montant < 0) throw new MontantNegatifInterdit();
        if (solde+montant > plafond) throw new DeplacementPlafondInterdit();
            solde += montant;
    }

    public void debite(double montant, String codePin) throws SoldeNegatifInterdit, MontantNegatifInterdit, CodePinFaux, CodeBloqueException {
        if(!codeSecret.verifierCode(codePin)) throw new CodePinFaux();
        if(montant < 0) throw new MontantNegatifInterdit();
        if(solde - montant < 0) throw new SoldeNegatifInterdit();
        solde -= montant;
    }
}
