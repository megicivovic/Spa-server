/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import baza.ModelBaze;
import gui.podesavanja.KonfiguracioniModelTabele;
import java.awt.Color;
import javax.swing.JOptionPane;
import server.Server;

/**
 *
 * @author Ivana
 */
public class KonfiguracionaForma extends javax.swing.JFrame {

    /**
     * Creates new form KonfiguracionaForma
     */
    public KonfiguracionaForma() {
        initComponents();
        srediFormu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        aktivnaKonfiguracijaComboBox = new javax.swing.JComboBox();
        sacuvajButton = new javax.swing.JButton();
        startServerButton = new javax.swing.JButton();
        dodajKonfiguracijuTab = new javax.swing.JTabbedPane();
        jpnlKonfiguracija = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtxtSUBP = new javax.swing.JTextField();
        jtxtDrajver = new javax.swing.JTextField();
        jtxtURL = new javax.swing.JTextField();
        jtxtUsername = new javax.swing.JTextField();
        jtxtPassword = new javax.swing.JTextField();
        dodajButton = new javax.swing.JButton();
        pregledKofiguracijaTab = new javax.swing.JPanel();
        pregledKonfiguracijaTable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jtxtBrojPorta = new javax.swing.JTextField();
        jlblServerStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Aktivna konfiguracija:");

        sacuvajButton.setText("Sacuvaj");
        sacuvajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sacuvajButtonActionPerformed(evt);
            }
        });

        startServerButton.setText("Start Server");
        startServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Ime SUBP-a:");

        jLabel3.setText("Ime drajvera:");

        jLabel4.setText("URL:");

        jLabel5.setText("Korisnicko ime:");

        jLabel6.setText("Sifra:");

        jtxtURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtURLActionPerformed(evt);
            }
        });

        dodajButton.setText("Dodaj");
        dodajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlKonfiguracijaLayout = new javax.swing.GroupLayout(jpnlKonfiguracija);
        jpnlKonfiguracija.setLayout(jpnlKonfiguracijaLayout);
        jpnlKonfiguracijaLayout.setHorizontalGroup(
            jpnlKonfiguracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlKonfiguracijaLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jpnlKonfiguracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlKonfiguracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtxtSUBP)
                    .addComponent(jtxtDrajver)
                    .addComponent(jtxtURL)
                    .addComponent(jtxtUsername)
                    .addComponent(jtxtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlKonfiguracijaLayout.createSequentialGroup()
                .addContainerGap(418, Short.MAX_VALUE)
                .addComponent(dodajButton)
                .addGap(103, 103, 103))
        );
        jpnlKonfiguracijaLayout.setVerticalGroup(
            jpnlKonfiguracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlKonfiguracijaLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jpnlKonfiguracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSUBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlKonfiguracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtDrajver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlKonfiguracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlKonfiguracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlKonfiguracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dodajButton)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        dodajKonfiguracijuTab.addTab("Dodaj konfiguraciju", jpnlKonfiguracija);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        pregledKonfiguracijaTable.setViewportView(jTable1);

        javax.swing.GroupLayout pregledKofiguracijaTabLayout = new javax.swing.GroupLayout(pregledKofiguracijaTab);
        pregledKofiguracijaTab.setLayout(pregledKofiguracijaTabLayout);
        pregledKofiguracijaTabLayout.setHorizontalGroup(
            pregledKofiguracijaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
            .addGroup(pregledKofiguracijaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pregledKofiguracijaTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pregledKonfiguracijaTable, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pregledKofiguracijaTabLayout.setVerticalGroup(
            pregledKofiguracijaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
            .addGroup(pregledKofiguracijaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pregledKofiguracijaTabLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pregledKonfiguracijaTable, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        dodajKonfiguracijuTab.addTab("Pregled svih konfiguracija", pregledKofiguracijaTab);

        jLabel7.setText("Broj porta:");

        jlblServerStatus.setForeground(new java.awt.Color(255, 0, 0));
        jlblServerStatus.setText("Server nije pokrenut!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(dodajKonfiguracijuTab, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(27, 27, 27)
                                .addComponent(jtxtBrojPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startServerButton)
                                .addGap(27, 27, 27)
                                .addComponent(jlblServerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(54, 54, 54)
                                .addComponent(aktivnaKonfiguracijaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(sacuvajButton)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startServerButton)
                    .addComponent(jLabel7)
                    .addComponent(jtxtBrojPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblServerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aktivnaKonfiguracijaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(sacuvajButton))
                .addGap(18, 18, 18)
                .addComponent(dodajKonfiguracijuTab, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtURLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtURLActionPerformed

    private void sacuvajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sacuvajButtonActionPerformed
        ModelBaze.getInstance().postaviTrenutnuBazu((String) aktivnaKonfiguracijaComboBox.getSelectedItem());
    }//GEN-LAST:event_sacuvajButtonActionPerformed

    private void dodajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajButtonActionPerformed
        String dbms = jtxtSUBP.getText().trim();
        if (!dbms.equals("") && !ModelBaze.getInstance().dajNaziveSvhPodrzanihBaza().contains(dbms)) {
            JOptionPane.showMessageDialog(this, "Nedozvoljeno ime za SUBP");
            return;
        }
        
        if (jtxtUsername.getText().equals("")&&jtxtDrajver.getText().equals("") && jtxtPassword.getText().equals("")&&
                jtxtURL.getText().equals("")&& jtxtSUBP.getText().equals("")){          
              JOptionPane.showMessageDialog(this, "Ne mozete uneti praznu konfiguraciju!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{

        String url = jtxtURL.getText().trim();
        String drajver = jtxtDrajver.getText().trim();
        String korisnik = jtxtUsername.getText().trim();
        String sifra = jtxtPassword.getText().trim();

        ModelBaze.getInstance().postaviURL(dbms, url);
        ModelBaze.getInstance().postaviDrajver(dbms, drajver);
        ModelBaze.getInstance().postaviKorisnika(dbms, korisnik);
        ModelBaze.getInstance().postaviSifru(dbms, sifra);
        ((KonfiguracioniModelTabele) jTable1.getModel()).fireTableDataChanged();
        JOptionPane.showMessageDialog(this, "Uspešno ste dodali konfiguraciju baze!");
        }
    }//GEN-LAST:event_dodajButtonActionPerformed

    private void startServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerButtonActionPerformed
        String sBrPorta = jtxtBrojPorta.getText();
        int brPorta = 0;
        try {
            brPorta = Integer.parseInt(sBrPorta);
            Server server = new Server(brPorta);
            server.start();
            jlblServerStatus.setText("Server je pokrenut!");
            jlblServerStatus.setForeground(Color.green);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Neispravan broj porta!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
        }

//        jpnlKonfiguracija.setEnabled(true);
//        dodajButton.setEnabled(true);
//        dodajKonfiguracijuTab.setEnabled(true);
//        dodajButton.setEnabled(true);
//        imeSubpaTextField.setEnabled(true);
//        imeDrajveraTextField.setEnabled(true);
//        urlTextField.setEnabled(true);
//        korisnickoImeTextField.setEnabled(true);
//        sifraTextField.setEnabled(true);

    }//GEN-LAST:event_startServerButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KonfiguracionaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KonfiguracionaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KonfiguracionaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KonfiguracionaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KonfiguracionaForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox aktivnaKonfiguracijaComboBox;
    private javax.swing.JButton dodajButton;
    private javax.swing.JTabbedPane dodajKonfiguracijuTab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlblServerStatus;
    private javax.swing.JPanel jpnlKonfiguracija;
    private javax.swing.JTextField jtxtBrojPorta;
    private javax.swing.JTextField jtxtDrajver;
    private javax.swing.JTextField jtxtPassword;
    private javax.swing.JTextField jtxtSUBP;
    private javax.swing.JTextField jtxtURL;
    private javax.swing.JTextField jtxtUsername;
    private javax.swing.JPanel pregledKofiguracijaTab;
    private javax.swing.JScrollPane pregledKonfiguracijaTable;
    private javax.swing.JButton sacuvajButton;
    private javax.swing.JButton startServerButton;
    // End of variables declaration//GEN-END:variables

    private void srediFormu() {
        for (String dbms : ModelBaze.getInstance().dajNaziveSvhPodrzanihBaza()) {
            aktivnaKonfiguracijaComboBox.addItem(dbms);
        }
        aktivnaKonfiguracijaComboBox.setSelectedItem(ModelBaze.getInstance().dajNazivTrenutneBaze());
        jTable1.setModel(new KonfiguracioniModelTabele());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ModelBaze.getInstance().sacuvajPodesavanja();
            }

        });
//        dodajKonfiguracijuTab.setEnabled(false);
//        dodajButton.setEnabled(false);
//        imeSubpaTextField.setEnabled(false);
//        imeDrajveraTextField.setEnabled(false);
//        urlTextField.setEnabled(false);
//        korisnickoImeTextField.setEnabled(false);
//        sifraTextField.setEnabled(false);
    }
}
