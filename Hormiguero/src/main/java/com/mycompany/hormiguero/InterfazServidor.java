/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.hormiguero;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Ivanl
 */
public class InterfazServidor extends javax.swing.JFrame {
    private Refugio refugio;
    private Insecto insecto;
    private Estadisticas estadisticas;
    /**
     * Creates new form Interfaz
     */
    public InterfazServidor(Refugio refugio, Insecto insecto, Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
        this.refugio = refugio;
        this.insecto = insecto;
        initComponents();
        
        setTitle("Hormiguero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void setTextoBuscandoComida (String texto){
        buscandoComida.setText(texto);
    }

    public void setTextoDefendiendo (String texto){
        defendiendo.setText(texto);
    }

    public void setTextoAlmacen (String texto){
        almacen.setText(texto);
    }

    public void setTextoLlevandoComida (String texto){
        llevandoComida.setText(texto);
    }

    public void setTextoInstruyendo (String texto){
        instruyendo.setText(texto);
    }

    public void setTextoDescansando (String texto){
        descansando.setText(texto);
    }

    public void setTextoComidaAlmacen (String texto){
        comidaAlmacen.setText(texto);
    }

    public void setTextoComidaZonaComer (String texto){
        comidaZonaComer.setText(texto);
    }

    public void setTextoZonaComer (String texto){
        zonaComer.setText(texto);
    }

    public void setTextoRefugiadas (String texto){
        refugiadas.setText(texto);
    }

    public JButton getGenerarInsecto() {
        return generarInsecto;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        generarInsecto = new javax.swing.JButton();
        pausaPlay = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        buscandoComida = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        defendiendo = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        almacen = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        llevandoComida = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        instruyendo = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        descansando = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        zonaComer = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        refugiadas = new javax.swing.JTextArea();
        comidaZonaComer = new javax.swing.JTextField();
        comidaAlmacen = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setToolTipText("");
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(665, 670));

        generarInsecto.setText("Generar insecto");
        generarInsecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarInsectoActionPerformed(evt);
            }
        });

        pausaPlay.setText("Pausa");
        pausaPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pausaPlayActionPerformed(evt);
            }
        });

        jLabel1.setText("EXTERIOR DE LA COLONIA:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Hormigas buscando comida:");

        buscandoComida.setEditable(false);
        buscandoComida.setColumns(20);
        buscandoComida.setRows(5);
        jScrollPane2.setViewportView(buscandoComida);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Hormigas repeliendo un insecto invasor:");

        defendiendo.setEditable(false);
        defendiendo.setColumns(20);
        defendiendo.setRows(5);
        jScrollPane3.setViewportView(defendiendo);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("INTERIOR DE LA COLONIA:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Hormigas en el almacén de comida:");

        almacen.setEditable(false);
        almacen.setColumns(20);
        almacen.setRows(5);
        jScrollPane4.setViewportView(almacen);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Hormigas llevando comida a la zona para comer:");

        llevandoComida.setEditable(false);
        llevandoComida.setColumns(20);
        llevandoComida.setRows(5);
        jScrollPane5.setViewportView(llevandoComida);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Hormigas haciendo instrucción:");

        instruyendo.setEditable(false);
        instruyendo.setColumns(20);
        instruyendo.setRows(5);
        jScrollPane6.setViewportView(instruyendo);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Hormigas descansando:");

        descansando.setEditable(false);
        descansando.setColumns(20);
        descansando.setRows(5);
        jScrollPane7.setViewportView(descansando);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Comida en el almacén:");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Comida en la zona de comer:");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Zona para comer:");

        zonaComer.setEditable(false);
        zonaComer.setColumns(20);
        zonaComer.setRows(5);
        jScrollPane10.setViewportView(zonaComer);

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Refugio:");

        refugiadas.setEditable(false);
        refugiadas.setColumns(20);
        refugiadas.setRows(5);
        jScrollPane11.setViewportView(refugiadas);

        comidaZonaComer.setEditable(false);
        comidaZonaComer.setText("0");

        comidaAlmacen.setEditable(false);
        comidaAlmacen.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)))
                .addGap(70, 70, 70))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pausaPlay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generarInsecto))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(33, 33, 33)
                        .addComponent(comidaAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(34, 34, 34)
                        .addComponent(comidaZonaComer, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane10)))
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(comidaAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comidaZonaComer))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pausaPlay)
                    .addComponent(generarInsecto))
                .addGap(30, 30, 30))
        );

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon("src/main/java/Imagenes/Hormiguero2.jpg")

        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pausaPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausaPlayActionPerformed
        if (estadisticas.getPlay()){
            pausaPlay.setText("Play");
            estadisticas.setPlay(false);
            for (Thread thread : estadisticas.getListaHormigas()){
                if (thread != null){
                    thread.interrupt();
                }
            }
        }
        else{
            pausaPlay.setText("Pausa");
            estadisticas.setPlay(true);
            synchronized(estadisticas.getBloqueoPausa()){
                estadisticas.getBloqueoPausa().notifyAll();
            }
        }
    }//GEN-LAST:event_pausaPlayActionPerformed

    private void generarInsectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarInsectoActionPerformed
        generarInsecto.setEnabled(false);
        if (estadisticas.getInterrumpirInsecto()){
            System.out.println("Ya hay un insecto atacando el hormiguero");
        }
        else{
            if (estadisticas.getNumSoldados() == 0){
                System.out.println("Se necesita al menos una hormiga soldado para poder defender el hormiguero");
            }
            else{
                if (!estadisticas.getPlay()){
                    System.out.println("No se puede generar un insecto mientras el programa está pausado");
                }
                else{
                    insecto.GenerarInsecto();
                }
            }
        }
    }//GEN-LAST:event_generarInsectoActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea almacen;
    private javax.swing.JTextArea buscandoComida;
    private javax.swing.JTextField comidaAlmacen;
    private javax.swing.JTextField comidaZonaComer;
    private javax.swing.JTextArea defendiendo;
    private javax.swing.JTextArea descansando;
    private javax.swing.JButton generarInsecto;
    private javax.swing.JTextArea instruyendo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea llevandoComida;
    private javax.swing.JButton pausaPlay;
    private javax.swing.JTextArea refugiadas;
    private javax.swing.JTextArea zonaComer;
    // End of variables declaration//GEN-END:variables
}


