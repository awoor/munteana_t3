package OrderManagement;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *Clasa Customer defineste clientul
 * @author Andrei
 */
public class Customer {

    private String nume, prenume;
    private long CNP;

    /**
     *
     * @param nume numele clientului
     * @param prenume prenumele clientului
     * @param CNP cod numeric personal
     */
    Customer(String nume, String prenume, long CNP) {
        assert CNP/1000>0;
        this.CNP = CNP;
        this.nume = nume;
        this.prenume = prenume;
    }

    
    /**
     * @pre true
     * @post @nochange
     * @return the nume
     */
    public String getNume() {
        return nume;
    }

    /**
     * @pre true
     * @post @nochange
     * @return the prenume
     */
    public String getPrenume() {
        return prenume;
    }

    /**
     * @pre true
     * @post @nochange
     * @return the CNP
     */
    public long getCNP() {
        return CNP;
    }
}
