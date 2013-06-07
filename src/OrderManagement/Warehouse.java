package OrderManagement;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *Clasa Warehouse simuleaza depozitul
 * @author Andrei
 */
import OrderManagement.*;
import java.util.TreeSet;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class Warehouse {

    private int nrProduse;
    /**
     * oversock si understock sunt flaguri pentru depistarea erorilor
     */
    private static boolean overstock = false, understock = false;
    private static StockComparator c = new StockComparator();
    private static TreeSet produse = new TreeSet(c);
    private static Iterator i;
    private String numeProduse[] = new String[12];
    private static String k = "";
    private static FileReader r;

    /**
     * constructorul creaza un nou depozit
     */
    Warehouse() {
        init();
    }

    /**
     * @param aOverstock the overstock to set
     */
    public static void setOverstock(boolean aOverstock) {
        overstock = aOverstock;
    }

    /**
     * @param aUnderstock the understock to set
     */
    public static void setUnderstock(boolean aUnderstock) {
        understock = aUnderstock;
    }

    /**
     * @pre Warehouse!=null
     * adaugarea iteratorului
     */
    public static void adaugaIterator() {
        i = produse.iterator();
    }

    /**
     *@pre produse!=NULL && s!=NULL
     * @post produse.last()=getLast() && produse.size()=sizePre+1 && produse.last()=getLast() &&produse.isWellFormed();
     * @param s se adauga in depozit un nou stoc \n un stoc este alcatuit dintr-un Produs si numarul de bucati disponibile ale acestuia
     */
    public void adauga(Stoc s) {
        assert s != null && produse != null;
        assert isWellFormed();
        assert asiguraUnicitate(s);
        int sizePre = getSize();
        produse.add(s);
        nrProduse++;
         assert produse.size() == getSize();
        assert produse.size() == sizePre + 1;
        assert produse.last() == getLast();
        assert isWellFormed();
    }
/**
 * verifica criteriul de unicitate in arborele binar echilibrat TreeSet
 * @param s
 * @return
 */
    protected boolean asiguraUnicitate(Stoc s){
adaugaIterator();Stoc v=null;
while(i.hasNext()){
    v=(Stoc)i.next();
    if((v.equals(s)) && !(s.equals(produse.last())))
        return false;
}
        return true;
    }
    
    /**
     * verifica daca obiectele au fost introduse corespunzator in arbore
     * trebuie mentionat faptul ca clasa StockComparator suprascrie ordinea naturala cu ordinea alfabetica a numelor produselor
     * @return true daca totulo este in ordine, false altfel
     */
    protected boolean isWellFormed() {
        adaugaIterator();
        Stoc v = null, m = null;
        while (i.hasNext()) {
            v = (Stoc) i.next();
            m = (Stoc) i.next();
            if (v.getProduct().getNumeProdus().compareTo(m.getProduct().getNumeProdus()) > 0) {

                return false;
            }


        }
        return true;
    }

    /**
     * se foloseste ca invariant
     * @return ultimul element din TreeSet<produse>
     */
    protected static Stoc getLast() {
        adaugaIterator();
        Stoc v = null;
        while (i.hasNext()) {
            v = (Stoc) i.next();

        }
        return v;
    }

    /**
     * 
     * @return marimea lui produse
     */
    protected int getSize() {
        int marime = 0;
        adaugaIterator();
        while (i.hasNext()) {
            marime++;
            i.next();
        }
        return marime;
    }

    /**
     *@pre true
     * @post nochange
     * @return intr-un String informatii vitale despre depozit
     */
    @Override
    public String toString() {
        assert produse != null;
        String s = "";
        adaugaIterator();
        while (i.hasNext()) {
            Stoc v;
            v = (Stoc) i.next();
            assert v.isWellFormed();
            s = s + "nume produs:" + v.getProduct().getNumeProdus() + " numar unitati:" + v.getNrUnitati() +
                    " pret la bucata:" + v.getProduct().getPret() + "\n";

        }
        return s;
    }

    /**
     *
     * @param stoc stocul peste care s-au efectuat operatii
     * @return un string care contine informatiile actualizate despre stocul unui anumit produs
     */
    public static String info(Stoc stoc) {
        assert stoc != null && stoc.isWellFormed();
        return "pret:" + stoc.getProduct().getPret() + " bucStoc:" + stoc.getNrUnitati();
    }

    /**
     *@pre Iterator i!=null
     * @param p produsul care va fi vandut
     */
    public static void vinde(Product p) {
        assert p != null && produse != null && p.isWellFormed();
        adaugaIterator();
        Stoc v;
        
        while (i.hasNext()) {
            v = (Stoc) i.next();
            if (v.getProduct() == p) {

                {
                    v.vanzare();
                    Fatada.setInformatii(info(v));
                    return;
                }
            }

        }
    }

    /**
     *@pre true
     * @post nochange
     * @param s stocul cautat dupa nume
     * @return returneaza null in caz defavorabil si stocul in caz favorabil
     */
    public Stoc find(String s) {
        assert !produse.isEmpty() && s != null;
        while (i.hasNext()) {
            Stoc v;
            v = (Stoc) i.next();
            if (v.getProduct().getNumeProdus().equals(s)) {
                return v;
            }
        }

        return null;
    }

    /**
     * @pre true
     * @post nochange
     * @return the numeProduse
     */
    public String[] getNumeProduse() {
        return numeProduse;
    }

    /**
     * @pre true
     * @post nochange
     * @return the produse
     */
    public static TreeSet getProduse() {
        return produse;
    }

    /**
     * @return the overstock
     */
    public static boolean isOverstock() {
        assert produse != null;
        return overstock;
    }

    /**
     * @return the understock true daca este sub stoc, false altfel
     */
    public static boolean isUnderstock() {
        assert produse != null;
        return understock;
    }

    /**
     * clasa interioara stoc
     */
    public static class Stoc {

        /**
         * p este Produsul
         */
        private Product p;
        /**
         * nrUnitati contine numarul de unitati al unui produs
         */
        private int nrUnitati;

        /**
         *
         * @param p Produsul
         * @param nrUnitati numarul de unitati care intra in depozit din produsul respectiv
         */
        Stoc(Product p, int nrUnitati) {
            assert nrUnitati > 0 && p.isWellFormed();
            this.nrUnitati = nrUnitati;
            this.p = p;
        }

        /**
         * verifica daca nrUnitati este un numar pozitiv
         * @return true daca nrUnitati este un numar pozitiv, altfel fals
         */
        public boolean isWellFormed() {
            if (this.nrUnitati < 0) {
                return false;
            }
            return true;
        }

        /**
         * @pre true
         * @post nochange
         * @return the p
         */
        public Product getProduct() {
            return p;
        }

        /**
         * @pre true
         *@post nochange
         * @return the nrUnitati
         */
        public int getNrUnitati() {
            return nrUnitati;
        }

        /**
         * @param nrUnitati the nrUnitati to set
         */
        public void setNrUnitati(int nrUnitati) {
            assert nrUnitati > 0;
            this.nrUnitati = nrUnitati;
        }

        /**
         * @return fals daca clientul vrea sa cumpere marfa inexistenta, true altfel
         */
        public boolean vanzare() {
            assert this != null;
            int h = Integer.parseInt(Fatada.getBucati().getText());
            if (this.nrUnitati - h < 0) {
                setUnderstock(true);
                return false;
            } else {
                this.nrUnitati -= h;
                return true;

            }
        }
    }

    /**
     * @return the nrProduse
     */
    public int getNrProduse() {
        assert nrProduse >= 0;
        return nrProduse;
    }

    /**
     * clasa comparator pentru arborele de stocuri
     */
    public static class StockComparator implements Comparator {

        public int compare(Object a, Object b) {
            String A = ((Stoc) a).getProduct().getNumeProdus();
            String B = ((Stoc) b).getProduct().getNumeProdus();
            return A.compareTo(B);
        }
    }

    /**
     * scrierea in fisier
     */
    public void init() {
        try {
            // Create new filereader
            r = new FileReader("produse.txt");
            BufferedReader in = new BufferedReader(r);
            int g = 0;
            while (k != null) {
                k = in.readLine();
                
                    String[] sir = new String[4];
                    String w = "";
                    int d[] = new int[3];
                    d[0] = k.indexOf(" ");
                    w = k.substring(d[0] + 1);
                    sir[0] = k.substring(0, d[0]);
                    numeProduse[g] = sir[0];
                    g++;
                    d[1] = w.indexOf(" ") + d[0] + 1;
                    w = k.substring(d[1] + 1);
                    d[2] = w.indexOf(" ") + d[1] + 1;
                    Stoc stoc;
                    sir[1] = k.substring(d[0] + 1, d[1]);
                    sir[2] = k.substring(d[1] + 1, d[2]);
                    sir[3] = k.substring(d[2] + 1);
                    Product p = new Product(sir[0], Integer.parseInt(sir[1]), Integer.parseInt(sir[2]));
                    if (Integer.parseInt(sir[3]) <= 10) {
                        stoc = new Stoc(p, Integer.parseInt(sir[3]));
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "nu avem spatiu suficient pentru nr de produse de produse\nse iau doar 10");
                        stoc = new Stoc(p, 10);

                    }
                            
                    adauga(stoc);//produse.add(stoc);
             }
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error2: " + e);
        }
        i = produse.iterator();
    }
}
