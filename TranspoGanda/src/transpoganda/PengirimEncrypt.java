package transpoganda;

import javax.swing.JOptionPane;

/**
 *
 * @author Ganjar Fandy Syarif Risya KemanananData 3 TIC GUI
 */
public class PengirimEncrypt extends javax.swing.JFrame implements chiperAlpha {
    
    private String message, keywordOne, keywordTwo, pad; //DEKLARASI

    /**
     * Creates new form PengirimEncrypt
     */
    public PengirimEncrypt() {
        initComponents();
        setLocationRelativeTo(null);
        message = "";
        keywordOne = "";
        keywordTwo = "";
        pad = "";
    }
    
    /**
     *
     * @param text
     * @param pertama
     * @param kedua
     * @param pad
     */
    public PengirimEncrypt(String text, String pertama, String kedua, String pad) {
        message = text;
        keywordOne = pertama;
        keywordTwo = kedua;
        this.pad = pad;
    }
    
    /**
     *
     * @param text
     */
    public void setPlain(String text) {
        message = text;
    }
    
    /**
     *
     * @param pertama
     */
    public void setKunciPertama(String pertama) {
        keywordOne = pertama;
    }
    
    /**
     *
     * @param kedua
     */
    public void setKunciKedua(String kedua) {
        keywordTwo = kedua;
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

    //Memulai Enkripsi

    /**
     *
     * @return
     */
    public String encrypt() {
        return encrypt(message, keywordOne, keywordTwo, pad);
    }
    
    /**
     *
     * @param m
     * @param k
     * @param w
     * @param p
     * @return
     */
    public String encrypt(String m, String k, String w, String p) {
        String[][] grid;
        int[] values;
        message = m;
        message = message.replace(" ", "");
        message = message.toLowerCase();
        
        keywordOne = k.toLowerCase();
        keywordTwo = w.toLowerCase();
        pad = p.toLowerCase();

        //COLUMNAR TRANSPOSITION
        double lineCount = keywordOne.length();
        lineCount = message.length() / lineCount;
        lineCount = Math.ceil(lineCount);
        
        int lines = (int) lineCount;
        
        grid = new String[lines][keywordOne.length()];
        values = new int[keywordOne.length()];
        
        String tempMess = message;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (tempMess.length() > 0) {
                    grid[r][c] = tempMess.substring(0, 1);
                    tempMess = tempMess.substring(1, tempMess.length());
                } else {
                    grid[r][c] = pad;
                }
            }
        }
        
        for (int i = 0; i < values.length; i++) {
            values[i] = alphabet.indexOf(keywordOne.substring(i, i + 1));
        }
        
        String columns = "";
        int low = 0;
        for (int i = 0; i < values.length; i++) {
            for (int n = 0; n < values.length; n++) {
                if (values[n] < values[low]) {
                    low = n;
                } else if (values[n] == values[low]) {
                    if (n < low) {
                        low = n;
                    }
                }
            }
            
            for (int c = 0; c < grid.length; c++) {
                columns += grid[c][low];
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

        //DOUBLE TRANSPOSITION
        String[][] grid2;
        int[] values2;
        double lineCount2 = keywordTwo.length();
        lineCount2 = columns.length() / lineCount2;
        lineCount2 = Math.ceil(lineCount2);
        
        int lines2 = (int) lineCount2;
        
        grid2 = new String[lines2][keywordTwo.length()];
        values2 = new int[keywordTwo.length()];
        
        String tempMess2 = columns;
        for (int r = 0; r < grid2.length; r++) {
            for (int c = 0; c < grid2[r].length; c++) {
                if (tempMess2.length() > 0) {
                    grid2[r][c] = tempMess2.substring(0, 1);
                    tempMess2 = tempMess2.substring(1, tempMess2.length());
                } else {
                    grid2[r][c] = pad;
                }
            }
        }
        
        for (int i = 0; i < values2.length; i++) {
            values2[i] = alphabet.indexOf(keywordTwo.substring(i, i + 1));
        }
        
        String columns2 = "";
        int low2 = 0;
        for (int i = 0; i < values2.length; i++) {
            for (int n = 0; n < values2.length; n++) {
                if (values2[n] < values2[low2]) {
                    low2 = n;
                } else if (values2[n] == values2[low2]) {
                    if (n < low2) {
                        low2 = n;
                    }
                }
            }
            
            for (int c = 0; c < grid2.length; c++) {
                columns2 += grid2[c][low2];
            }
            
            values2[low2] = 27;
        }
        
        String g2 = "";
        for (int r = 0; r < grid2.length; r++) {
            for (int c = 0; c < grid2[r].length; c++) {
                g2 += grid2[r][c] + " ";
            }
            g2 += "\n";
        }
        
        return columns2;
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
        PlainText = new javax.swing.JTextField();
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
        jLabel1.setText("Transposisi Ganda - Pengirim");

        labelPesan.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        labelPesan.setText("Pesan (Plaintext) :");

        labelKunciPertama.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        labelKunciPertama.setText("Kunci Pertama :");

        labelKunciKedua.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        labelKunciKedua.setText("Kunci Kedua :");

        labelKuncilNull.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        labelKuncilNull.setText("Kunci Jika Null :");

        keyNull.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N

        PlainText.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N

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
        labelHasil.setText("Hasil (Chipertext) :");

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
                        .addComponent(PlainText))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelKunciKedua)
                            .addComponent(labelKuncilNull)
                            .addComponent(labelKunciPertama))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(keyNull, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(keyKedua, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(keyPertama)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMenu)
                                .addGap(18, 18, 18)
                                .addComponent(btnUlang)
                                .addGap(18, 18, 18)
                                .addComponent(btnProses)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHasil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
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
                    .addComponent(PlainText, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            inText = PlainText.getText();
            inPertama = keyPertama.getText();
            inKedua = keyKedua.getText();
            inPad = keyNull.getText();
            
            PengirimEncrypt s = new PengirimEncrypt(inText, inPertama, inKedua, inPad);
            Hasil.append(s.encrypt());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada yang Salah!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnProsesActionPerformed

    private void btnUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUlangActionPerformed
        //RESTART ULANG FORM
        PengirimEncrypt x = new PengirimEncrypt();
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
            java.util.logging.Logger.getLogger(PengirimEncrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PengirimEncrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PengirimEncrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PengirimEncrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PengirimEncrypt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Hasil;
    private javax.swing.JTextField PlainText;
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
