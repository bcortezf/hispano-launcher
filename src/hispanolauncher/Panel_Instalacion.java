/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hispanolauncher;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 *
 * @author inuckles
 */
public class Panel_Instalacion extends javax.swing.JPanel {

    /**
     * Creates new form Inicio
     */
    public Panel_Instalacion() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInstalacion = new javax.swing.JPanel(){

            /* public void paintComponent(Graphics g){
                ImageIcon im = new ImageIcon("bg.png");
                Image i = im.getImage();
                g.drawImage(i,0,0,this.getSize().width,this.getSize().height,this);

            }*/
        };
        lblInstalacion = new javax.swing.JLabel();

        setBackground(new java.awt.Color(84, 84, 84));

        pnlInstalacion.setOpaque(false);

        lblInstalacion.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblInstalacion.setForeground(new java.awt.Color(238, 238, 238));
        lblInstalacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInstalacion.setText("Instalación");

        javax.swing.GroupLayout pnlInstalacionLayout = new javax.swing.GroupLayout(pnlInstalacion);
        pnlInstalacion.setLayout(pnlInstalacionLayout);
        pnlInstalacionLayout.setHorizontalGroup(
            pnlInstalacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInstalacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
        );
        pnlInstalacionLayout.setVerticalGroup(
            pnlInstalacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstalacionLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblInstalacion)
                .addContainerGap(412, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInstalacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInstalacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblInstalacion;
    private javax.swing.JPanel pnlInstalacion;
    // End of variables declaration//GEN-END:variables
}
