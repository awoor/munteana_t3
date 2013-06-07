package OrderManagement;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *Clasa construieste un produs
 * @author Andrei
 */
public class Product {

    private String numeProdus;
    /**
     * cod codul in cifre al produsului
     * pret pretul unitar al produsului
     */
    private int pret, cod;

    /**
     *
     * @param numeProdus numele produsului (ex. calculator)
     * @param pret pretul unitar al produsului
     * @param cod codul unic de identificare(este generat random)
     */
    Product(String numeProdus, int pret, int cod) {
        assert pret>0;
        this.cod = cod;
        this.pret = pret;
        this.numeProdus = numeProdus;
    }

    Product() {
    }
/**
 *
 * @return true daca pretul a fost citit corect, altfel false
 */
    protected boolean isWellFormed(){
        if((this.pret<0)&&(this.cod)<0)
            return false;
        if(this==null)
            return false;
        return true;
    }
    /**
     * @pre true
     * @post @result <=>numeProdus
     * @return the numeProdus
     */
    public String getNumeProdus() {
        return numeProdus;
    }

    /**
     * @pre true
     * @post @nochange
     * @return the pret
     */
    public int getPret() {
        return pret;
    }

    /**
     * @pre true
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     *@pre true
     * @post @nochange
     * @return informatii vitale despre un produs anume
     */
    @Override
    public String toString() {
        assert this!=null;
        return numeProdus + "     " + pret + "        " + cod;
    }
}
