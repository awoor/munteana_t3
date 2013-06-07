package OrderManagement;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *Clasa AllOrders contruieste arhiva comezilor intr-un arbore
 * @author Andrei
 **/
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Vector;
import java.util.Comparator;

public class AllOrders {

    private OrderComparator comp = new OrderComparator();
    private TreeSet<Vector<Order>> comenzi = new TreeSet<Vector<Order>>(comp);
    private Iterator i = comenzi.iterator();

    /**
     *@pre o!=null && isWellFormed() && comenzi.size()==getSize()
     *@post isWellFormed() && comenzi.size()==preSize+1;
     * @param o Vectorul de comenzi adaugat in arhiva
     */
    public void add(Vector<Order> o) {
        assert !o.isEmpty() && comenzi != null;
        assert comenzi.size() == getSize();
        assert isWellFormed();
        int preSize = getSize();
        comenzi.add(o);
        assert comenzi.size() == getSize();
        assert comenzi.size() == preSize + 1;
        assert isWellFormed();
    }


    /**
     * verifica daca elementele din arbore respecta ordinea
     * @return false daca elementele nu respecta conditia de arbore echilibrat, true daca totul se respecta
     */
    protected boolean isWellFormed() {
        Vector<Order> v = null, m = null;
        while (i.hasNext()) {
            v = (Vector<Order>) i.next();
            m = (Vector<Order>) i.next();

            if (comp.compare(v, m) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return marimea lui comenzi
     */
    protected int getSize() {
        int marime = 0;

        while (i.hasNext()) {
            marime++;
            i.next();
        }
        return marime;
    }

    /**
     * deoarece un vector de comenzi ale aceluiasi client are  numele sau este destul sa comparam pe indexul 0 din vectorul de comezi
     * o noua clasa comparator este implementata pentru a se putea insera in arbore
     */
    public class OrderComparator implements Comparator {

        public int compare(Object a, Object b) {
            String A = ((Vector<Order>) a).get(0).getClient().getNume();
            String B = ((Vector<Order>) b).get(0).getClient().getNume();
            return A.compareTo(B);
        }
    }
}
