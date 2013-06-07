package OrderManagement;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *Clasa care prelucreaza apasarea celor patru butoane din interfata grafica
 * @author Andrei
 */
import java.awt.event.*;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ClickListener implements ActionListener {

    private Customer client;
    private Vector<Order> ord = new Vector<Order>();
    private AllOrders all = new AllOrders();
    private Chitanta c;
    private static int pretTotal = 0;

    /**
     * @return the pretTotal
     */
    public static int getPretTotal() {
        return pretTotal;
    }

    /**
     *
     * @param event se verifica care buton a fost apasat
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("submit")) {
            try {
                client = new Customer(Fatada.getNume().getText(), Fatada.getForname().getText(), Long.parseLong(Fatada.getCnp().getText()));
                Fatada.setSubmit();
            } catch (NumberFormatException e) {
            }
        }
        if (event.getActionCommand().equals("Adauga")) {
            try {
                Order O = new Order(Fatada.produsulCumparat(), Integer.parseInt(Fatada.getBucati().getText()), client);
                assert O.isWellFormed();
                ord.add(O);
                assert !Warehouse.isUnderstock();
                Warehouse.vinde(Fatada.produsulCumparat());
                if (Warehouse.isUnderstock() == true) {
                    JOptionPane.showMessageDialog(null, "nu avem nr solociat de produse");
                    Warehouse.setUnderstock(false);
                    return;
                }
                Fatada.setAfisor((Fatada.getAfisor().getText() + "\n").concat(O.com()));
                pretTotal = getPretTotal() + Fatada.produsulCumparat().getPret() * Integer.parseInt(Fatada.getBucati().getText());
                assert pretTotal>=0;
                Fatada.setPretTotal(Integer.toString(getPretTotal()));

            } catch (NumberFormatException ex) {
            System.err.print(ex);
            }
        }
        if (event.getActionCommand().equals("Client nou")) {
            Fatada.setNewCustomer();
            Fatada.setPretTotal("");
            Fatada.setPretUnitar("");
            Fatada.setAfisor();
        }
        if (event.getActionCommand().equals("confirma")) {
            c = new Chitanta(client.getNume() + " " + client.getPrenume(), client, getOrd());
            assert c != null;
            c.scrie();
            assert ord != null;
            all.add(ord);
            JOptionPane.showMessageDialog(null, "s-a eliberat chitanta");
        }

    }

    /**
     * @return o comanda (mai bine zis o comanda se compuse din mai multe comenzi)
     */
    public Vector<Order> getOrd() {
        return ord;
    }
}
