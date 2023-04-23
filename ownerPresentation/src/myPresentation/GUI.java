package myPresentation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class GUI extends JFrame {
    //atributos
    private JButton myPhoto, myHobby;
    private JPanel containerButtons, containerImage;
    private Listener listener;
    private Title title;
    private JLabel imageLabel;
    private JTextArea expectations;


    //metodos
    public GUI(){
        initGUI();

        this.setTitle("My Presentation");
        this.setSize(600, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {

        title = new Title("Mi presentación personal", Color.gray);
        myPhoto = new JButton("Este soy yo");
        myHobby = new JButton("Esta es mi pasion");
        containerButtons = new JPanel();
        containerImage = new JPanel();
        listener = new Listener();
        imageLabel = new JLabel();
        expectations = new JTextArea(10, 12);

        containerImage.setBorder(BorderFactory.createTitledBorder(null, "About me", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.SANS_SERIF,Font.PLAIN,20), Color.red));
        containerImage.add(imageLabel);

        containerButtons.add(myPhoto);
        containerButtons.add(myHobby);

        myPhoto.addActionListener(listener);
        myHobby.addActionListener(listener);

        this.add(title, BorderLayout.NORTH);
        this.add(containerButtons, BorderLayout.SOUTH);
        this.add(containerImage, BorderLayout.CENTER);

        containerImage.setFocusable(true);
        containerImage.requestFocusInWindow();
        containerImage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                imageLabel.setIcon(null);
                char keyChar = Character.toLowerCase(e.getKeyChar());
                System.out.println(keyChar);
                if (keyChar == 'm') {
                    expectations.setText("Mis metas a corto plazo son graduarme de Ingenieria Topografica en 3 semestres y de Desarrollo\n" +
                            "de software en 5. Me encanta el futbol, quiza sea la pasion mas grande que tengo y el America de cali,\n" +
                            "tambien amo ver peliculas.");
                    expectations.setBackground(null);
                    expectations.setForeground(Color.BLACK);
                    containerImage.add(expectations);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        containerImage.requestFocus();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI myGui = new GUI();
            }
        });
    }

    private class Listener implements ActionListener
    {
        private ImageIcon image;
        private int hobbyClicks;
        @Override
        public void actionPerformed(ActionEvent e)
        {
            imageLabel.setIcon(null);
            containerImage.remove(expectations);
            if(e.getSource() == myPhoto)
            {
                this.image = new ImageIcon(getClass().getResource("/resources/Me.png"));
                imageLabel.setIcon(image);
            }
            else if(e.getSource() == myHobby)
            {
                hobbyClicks++;
                if (hobbyClicks == 2)
                {
                    this.image = new ImageIcon(getClass().getResource("/resources/Hobby.jpeg"));
                    imageLabel.setIcon(image);
                    hobbyClicks = 0;
                }
            }
            revalidate();
            repaint();
        }
    }
}