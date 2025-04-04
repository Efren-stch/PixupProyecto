package org.soto.chavez.efren.model.secciones.vista.ventana;

import org.soto.chavez.efren.generalUtil.Salidas;

import javax.swing.*;

public class VentanaElementos {
    /*
    public static String cadenaVentana (String cadena) {
        return "<html>" + cadena.replace("\n", "<br>") + "</html>";
    }
    public static JLabel menuFiguras () {
        String menu = cadenaVentana(Salidas.principalFiguras);
        JLabel jLabel = new JLabel(menu);
        jLabel.setBounds(10, 10, 260, 160);
        return jLabel;
    }
    public static JLabel menuTriangulos () {
        String menu = cadenaVentana(Salidas.principalTriangulos);
        JLabel jLabel = new JLabel(menu);
        jLabel.setBounds(10, 10, 260, 160);
        return jLabel;
    }
    public static void imprimirResultado ( String resultado ) {
        String cadena = cadenaVentana(resultado);
        JDialog jDialog = new JDialog((JFrame) null, "Resultado", true);
        jDialog.setSize(300, 150);
        jDialog.setLayout(null);
        jDialog.setLocationRelativeTo(null);

        JLabel jLabel = new JLabel(cadena);
        jLabel.setBounds(10, 10, 260, 100);

        JButton jButton = new JButton("Terminar");
        jButton.setBounds(90, 75, 100, 30);

        jButton.addActionListener(e -> {
            jDialog.dispose();
        });

        jDialog.add(jLabel);
        jDialog.add(jButton);
        jDialog.setVisible(true);
    }
    public static String pedirEntrada(String textoPrincipal){
        JDialog jDialog = new JDialog((JFrame) null, "Figuras", true);
        jDialog.setSize(300, 150);
        jDialog.setLayout(null);
        jDialog.setLocationRelativeTo(null);

        JLabel jLabel = new JLabel(textoPrincipal);
        jLabel.setBounds(10, 10, 200, 20);

        JTextField jTextField = new JTextField();
        jTextField.setBounds(10, 40, 260, 25);

        JButton jButton = new JButton("Enviar");
        jButton.setBounds(90, 75, 100, 30);

        String[] resultado = {null};

        jButton.addActionListener(e -> {
            resultado[0] = jTextField.getText();
            jDialog.dispose();
        });

        jDialog.add(jLabel);
        jDialog.add(jTextField);
        jDialog.add(jButton);

        jDialog.setVisible(true);

        return resultado[0];
    }
     */
}
