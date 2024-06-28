/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.sistema_de_monitoreo_salud_alumno.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ELVIS
 */
public class SeguimientoTraslado extends javax.swing.JPanel {
  String enlace1="https://667e380173781dbd58b397fa--courageous-sable-52907a.netlify.app/#/";
    /**
     * Creates new form SeguimientoDeTrasladoEstudiante
     */
    public SeguimientoTraslado() {
        initComponents();
        
    }

    
   

     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnUbicacion = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(900, 450));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnUbicacion.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.GreyInline"));
        btnUbicacion.setFont(new java.awt.Font("Sitka Display", 3, 24)); // NOI18N
        btnUbicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mapa.png"))); // NOI18N
        btnUbicacion.setText("Ver Ubicacion");
        btnUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbicacionActionPerformed(evt);
            }
        });
        jPanel1.add(btnUbicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 250, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo-ucv_color (1).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/LogoMap (3).png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbicacionActionPerformed
      try {
          abrirEnlace(enlace1);
      } catch (IOException ex) {
          Logger.getLogger(SeguimientoTraslado.class.getName()).log(Level.SEVERE, null, ex);
      }

    }//GEN-LAST:event_btnUbicacionActionPerformed

private void abrirEnlace(String enlace) throws IOException {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI(enlace);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUbicacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
