package OrderManagement;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *Clasa care construieste o comanda a unui client
 * @author Andrei
 */
public class Order {

    private int buc;
    private Product prod;
    private Customer Client;

    /**
     *
     * @param prod produsul comandat
     * @param buc numarul de bucati
     * @param Client clientul care a efectuat comanda
     */
    public Order(Product prod, int buc, Customer Client) {
       assert prod.isWellFormed() && buc>0;
        this.prod = prod;
        this.buc = buc;
        this.Client = Client;
    }
/**
 * 
 * @return true daca comanda este formata corect, false altfel
 */
    public boolean isWellFormed(){
   if(this.buc<0)
    return false;
       return true;
}
    /**
     * @return the cod
     */
    public Product getCod() {
        return prod;
    }

   
    /**
     * @return nr de buc comandate
     */
    public int getBuc() {
        return buc;
    }

    /**
     * @return the Customer
     */
    public Customer getClient() {
        return Client;
    }

    /**
     *
     * @return vitally informations about the orders
     */
    public String com() {
     assert prod.isWellFormed();
        return prod.getNumeProdus() + "    " + prod.getPret() + "         " + buc + "    " + prod.getCod();
    }
}
