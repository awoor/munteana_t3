package OrderManagement;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;
import java.io.*;
import java.util.Vector;

/**
 * Clasa care tipareste chitanta
 * @author Andrei
 */
class Chitanta {

    private static String[] c = new String[9];
    private String numeChitanta;
    private Customer cus;
    private Random r = new Random();
    private Vector<Order> ord = new Vector<Order>();

    /**
     *
     * @param numeChitanta pe ce nume se elibereaza chitanta
     * @param cus cumparatorul
     * @param ord vectorul de comanda
     */
    Chitanta(String numeChitanta, Customer cus, Vector<Order> ord) {
        assert !ord.isEmpty();
        this.numeChitanta = numeChitanta;
        this.cus = cus;
        this.ord = ord;
    }

    /**
     * metoda aceasta scrie in fisier chitanta propriu-zisa
     */
    public void scrie() {
        c[0] = "Numar chitanta:";
        c[1] = "Informatii client";
        c[2] = "Nume:";
        c[3] = "Prenume:";
        c[4] = "CNP:";
        c[5] = "Produse cumparate:";
        c[6] = "nume          pret 	buc	cod";
        c[7] = "total:";
        c[8] = "Semnatura:";
        try {

            FileWriter fstream = new FileWriter(numeChitanta + ".txt");
            BufferedWriter out = new BufferedWriter(fstream);

            out.write(c[0] + r.nextInt(900000) );
            out.newLine();
            out.write(c[1]);
            out.newLine();
            out.write(c[2] + cus.getNume());
            out.newLine();
            out.write(c[3] + cus.getPrenume());
            out.newLine();
            out.write(c[4] + cus.getCNP());
            out.newLine();
            out.write(c[5]);
            out.newLine();
            out.write(c[6]);
            out.newLine();
            for (int j = 0; j < ord.size(); j++) {
                out.write(ord.get(j).com());
                out.newLine();
            }
            assert ClickListener.getPretTotal()>=0;
            out.write(c[7] + ClickListener.getPretTotal());
            out.newLine();
            out.newLine();
            out.newLine();
            out.write(c[8]);
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}

