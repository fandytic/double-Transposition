package transpoganda;

import javax.swing.JOptionPane;

/**
 *
 * @author Ganjar Fandy Syarif Risya KemanananData 3 TIC GUI
 */
public class PenerimaEncrypt extends javax.swing.JFrame implements chiperAlpha {

    private String chipertext, kunciPertama, kunciKedua, pad; //DEKLARASI

    /**
     * Creates new form PengirimEncrypt
     */
    public PenerimaEncrypt() {
        initComponents();
        setLocationRelativeTo(null);
        chipertext = "";
        kunciPertama = "";
        kunciKedua = "";
        pad = "";
    }

    /**
     *
     * @param chip
     * @param pertama
     * @param kedua
     * @param pad
     */
    public PenerimaEncrypt(String chip, String pertama, String kedua, String pad) {
        chipertext = chip;
        kunciPertama = pertama;
        kunciKedua = kedua;
        this.pad = pad;
    }

    /**
     *
     * @param chip
     */
    public void setChiper(String chip) {
        chipertext = chip;
    }

    /**
     *
     * @param pertama
     */
    public void setKunciPertama(String pertama) {
        kunciPertama = pertama;
    }

    /**
     *
     * @param kedua
     */
    public void setKunciKedua(String kedua) {
        kunciKedua = kedua;
    }

    /**
     *
     * @param pertama
     * @param kedua
     */
    public void setKunci(String pertama, String kedua) { //gabungan kunci
        setKunciPertama(pertama);
        setKunciKedua(kedua);
    }

    /**
     *
     * @param pad
     */
    public void setPad(String pad) {
        this.pad = pad;
    }

    //Memulai dekripsi

    /**
     *
     * @return
     */
    public String decrypt() {
        return decrypt(chipertext, kunciPertama, kunciKedua, pad);
    }

    /**
     *
     * @param chip
     * @param pertama
     * @param kedua
     * @param p
     * @return
     */
    public String decrypt(String chip, String pertama, String kedua, String p) {
        // DECRYPT ONE
        String[][] grid;
        double rowCount = kedua.length();
        rowCount = chip.length() / rowCount;

        rowCount = (Math.ceil(rowCount));
        int rows = (int) rowCount;
        grid = new String[rows][kedua.length()];

        chip = chip.toLowerCase();
        kedua = kedua.toLowerCase();
        pertama = pertama.toLowerCase();

        int[] values = new int[kedua.length()];
        for (int i = 0; i < values.length; i++) {
            values[i] = alphabet.indexOf(kedua.substring(i, i + 1));
        }

        String tempMess = chip;
        int low = 0;
        for (int i = 0; i < values.length; i++) {
            for (int n = 0; n < values.length; n++) {
                if (values[n] < values[low]) {
                    low = n;
                }
            }

            for (int n = 0; n < grid.length; n++) {
                if (tempMess.length() > 0) {
                    grid[n][low] = tempMess.substring(0, 1);
                    tempMess = tempMess.substring(1, tempMess.length());
                }

                values[low] = 27;
            }
        }

        for (int n = 0; n < grid[0].length; n++) {
            if (values[n] < values[low]) {
                low = n;
            }
        }

        for (int n = 0; n < grid.length; n++) {
            if (tempMess.length() > 0) {
                grid[n][low] = tempMess.substring(0, 1);
                tempMess = tempMess.substring(1, tempMess.length());
            }
            values[low] = 27;
        }

        String g = "";
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                g += grid[r][c] + " ";
            }
            g += "\n";
        }

        String decoded1 = "";
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                decoded1 += grid[r][c];
            }
        }

        String padding = "";
        for (int i = 0; i < grid.length; i++) {
            padding += pad;
        }

        if (decoded1.substring(decoded1.length() - padding.length(), decoded1.length()).equals(padding)) {
            decoded1 = decoded1.substring(0, decoded1.length() - padding.length());
        }

        // DECRYPT TWO
        String[][] grid2;
        double rowCount2 = pertama.length();
        rowCount2 = decoded1.length() / rowCount2;

        int rows2 = (int) rowCount2;
        grid2 = new String[rows2][pertama.length()];

        decoded1 = decoded1.toLowerCase();

        int[] values2 = new int[pertama.length()];
        for (int i = 0; i < values2.length; i++) {
            values2[i] = alphabet.indexOf(pertama.substring(i, i + 1));
        }

        String values2Array = "";
        for (int val : values2) {
            values2Array += val + " ";
        }

        String tempMess2 = decoded1;
        int low2 = 0;
        for (int i = 0; i < values2.length; i++) {
            for (int n = 0; n < values2.length; n++) {
                if (values2[n] < values2[low2]) {
                    low2 = n;
                }
            }

            for (int n = 0; n < grid2.length; n++) {
                if (tempMess2.length() > 0) {
                    grid2[n][low2] = tempMess2.substring(0, 1);
                    tempMess2 = tempMess2.substring(1, tempMess2.length());
                } else {
                    grid2[n][low2] = pad;
                }
            }

            values2[low2] = 27;
        }

        for (int n = 0; n < values2.length; n++) {
            if (values2[n] < values2[low2]) {
                low2 = n;
            }
        }

        for (int n = 0; n < grid2.length; n++) {
            if (tempMess2.length() > 0) {
                grid2[n][low2] = tempMess2.substring(0, 1);
                tempMess2 = tempMess2.substring(1, tempMess2.length());
            }
        }

        values2[low2] = 27;

        String g2 = "";
        for (int r = 0; r < grid2.length; r++) {
            for (int c = 0; c < grid2[r].length; c++) {
                g2 += grid2[r][c] + " ";
            }

            g2 += "\n";
        }

        String decoded2 = "";
        for (int r = 0; r < grid2.length; r++) {
            for (int c = 0; c < grid2[r].length; c++) {
                decoded2 += grid2[r][c];
            }
        }

        for (int i = 0; i < chipertext.length(); i++) {
            if (decoded2.substring(decoded2.length() - 1, decoded2.length()).equals(p)) {
                decoded2 = decoded2.substring(0, decoded2.length() - 1);
            }
        }
        return decoded2;
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
        labelPesan = new javax.swing.JLabel();
        labelKunciPertama = new javax.swing.JLabel();
        labelKunciKedua = new javax.swing.JLabel();
        labelKuncilNull = new javax.swing.JLabel();
        keyNull = new javax.swing.JTextField();
        ChiperText = new javax.swing.JTextField();
        keyPertama = new javax.swing.JTextField();
        keyKedua = new javax.swing.JTextField();
        btnMenu = new javax.swing.JButton();
        btnUlang = new javax.swing.JButton();
        btnProses = new javax.swing.JButton();
        labelHasil = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Hasil = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Transposisi Ganda - Penerima");

        labelPesan.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        labelPesan.setText("Pesan (Chipertext) :");

        labelKunciPertama.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        labelKunciPertama.setText("Kunci Pertama :");

        labelKunciKedua.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        labelKunciKedua.setText("Kunci Kedua :");

        labelKuncilNull.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        labelKuncilNull.setText("Kunci Jika Null :");

        keyNull.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N

        ChiperText.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N

        keyPertama.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N

        keyKedua.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N

        btnMenu.setText("Kembali Ke Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnUlang.setText("Ulang");
        btnUlang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUlangActionPerformed(evt);
            }
        });

        btnProses.setText("Proses");
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });

        labelHasil.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        labelHasil.setText("Hasil (Plaintext) :");

        jLabel7.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        jLabel7.setText("---------------------------------------------------------------------------------------------------------------------------------");

        Hasil.setColumns(20);
        Hasil.setRows(5);
        jScrollPane1.setViewportView(Hasil);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelPesan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ChiperText))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelKunciKedua)
                            .addComponent(labelKuncilNull)
                            .addComponent(labelKunciPertama))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUlang)
                                .addGap(18, 18, 18)
                                .addComponent(btnProses)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(keyNull, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(keyKedua, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(keyPertama)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelHasil)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPesan)
                    .addComponent(ChiperText, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelKunciPertama)
                    .addComponent(keyPertama, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelKunciKedua)
                    .addComponent(keyKedua, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelKuncilNull)
                    .addComponent(keyNull, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUlang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHasil)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        try {
            String inText, inPertama, inKedua, inPad;
            String hasilAkhir = "";
            inText = ChiperText.getText();
            inPertama = keyPertama.getText();
            inKedua = keyKedua.getText();
            inPad = keyNull.getText();

            PenerimaEncrypt s = new PenerimaEncrypt(inText, inPertama, inKedua, inPad);
            Hasil.append(s.decrypt());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada yang Salah!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnProsesActionPerformed

    private void btnUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUlangActionPerformed
        //RESTART ULANG FORM
        PenerimaEncrypt x = new PenerimaEncrypt();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUlangActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        this.dispose(); //form ini keluar
        index x = new index();
        x.setVisible(true); //form menu nampil
    }//GEN-LAST:event_btnMenuActionPerformed

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
            java.util.logging.Logger.getLogger(PenerimaEncrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PenerimaEncrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PenerimaEncrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PenerimaEncrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PenerimaEncrypt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ChiperText;
    private javax.swing.JTextArea Hasil;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnUlang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField keyKedua;
    private javax.swing.JTextField keyNull;
    private javax.swing.JTextField keyPertama;
    private javax.swing.JLabel labelHasil;
    private javax.swing.JLabel labelKunciKedua;
    private javax.swing.JLabel labelKunciPertama;
    private javax.swing.JLabel labelKuncilNull;
    private javax.swing.JLabel labelPesan;
    // End of variables declaration//GEN-END:variables

}
