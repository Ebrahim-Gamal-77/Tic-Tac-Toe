package tic_tac_toe;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static tic_tac_toe.HomePage.BOLI_FONT;

public class LastPage {

    private final JFrame frame;

    public LastPage(String status) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        System.out.println(status); // I need to know from where ( any class ) this data get passed

        // Last page frame
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(400 , 100 , 600 , 600);
        frame.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getClassLoader().getResource("xo icon 512.png"))).getImage());
        frame.setResizable(false);

        // Last page labels
        JLabel gameStatus = new JLabel(status);
        gameStatus.setFont(new Font(BOLI_FONT , Font.BOLD , 70));
        gameStatus.setHorizontalAlignment(SwingConstants.CENTER);
        gameStatus.setVerticalAlignment(SwingConstants.TOP);
        gameStatus.setBounds(40 , 20 ,  500 , 100);
        // Changing status color
        switch (status) {
            case "You Win!" -> {
                gameStatus.setForeground(Color.green);
                File file = new File("src/win stat.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f);
                clip.start();
            }
            case "You Lose!" -> {
                gameStatus.setForeground(Color.red);
                File file = new File("src/lose stat.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-18.0f);
                clip.start();
            }
            case "It's Tie!" -> {
                gameStatus.setForeground(Color.yellow);
                File file = new File("src/tie stat.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-20.0f);
                clip.start();
            }
            default -> {
                gameStatus.setText("Text Error!");
                gameStatus.setForeground(Color.red);
            }
        }

        // Create copyRights
        JLabel copyRight = new JLabel("Created by: ");
        copyRight.setFont(new Font(BOLI_FONT , Font.PLAIN , 20));
        copyRight.setHorizontalAlignment(SwingConstants.CENTER);
        copyRight.setVerticalAlignment(SwingConstants.CENTER);
        copyRight.setBounds(115 , 466 ,  200 , 100);
        copyRight.setForeground(Color.white);

        JLabel copyRightName = new JLabel("Ebrahim & Karam");
        copyRightName.setFont(new Font(BOLI_FONT , Font.BOLD , 20));
        copyRightName.setHorizontalAlignment(SwingConstants.CENTER);
        copyRightName.setVerticalAlignment(SwingConstants.CENTER);
        copyRightName.setBounds(270 , 500 ,  185 , 35);
        copyRightName.setForeground(Color.red);
        copyRightName.setCursor(new Cursor(Cursor.HAND_CURSOR));
        copyRightName.getCursor();
        copyRightName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Ebrahim-Gamal-77/Tic-Tac-Toe"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                copyRightName.setText("<html><a style=\"color:aqua;\" href=''>" + "Visit Github" + "</a></html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                copyRightName.setText("Ebrahim & Karam");
            }
        });

        // Homepage buttons
        JButton again = new JButton("Again");
        again.setFont(new Font(null , Font.BOLD , 30));
        again.setBounds(200 , 160 , 200 , 75);
        again.setFocusable(false);
//        System.out.println(new Exception().getStackTrace()[1].getClassName().substring(new Exception().getStackTrace()[1].getClassName().lastIndexOf('.') + 1));

        /* "Again" button steps:-
        1- create a lastClassName
        2- remove package name
        3- make if else condition and create previous class
        */

        String lastClass = new Exception().getStackTrace()[1].getClassName(); // add subString to it or regex.
        switch (lastClass){
            case "Players1" -> new Players1();
            case "Players2" -> new Players2();
            default -> System.out.println("There is statusLastClass Error");
        }


        again.addActionListener(e -> {
            frame.dispose();
            new Players1(); // Here I want to see last class was playing in either (Players1 or Players2)
            // then create it depend on from which class did status come from.
            // ****************** Important ******************
            /*
            Need to learn:-
            1- Stack Trace
            2- subString
             */


        });

        JButton mainMenu = new JButton("Main menu");
        mainMenu.setFont(new Font(null , Font.BOLD , 30));
        mainMenu.setBounds(200 , 270 , 200 , 75);
        mainMenu.setFocusable(false);

        JButton exit = new JButton("Exit");
        exit.setFont(new Font(null , Font.BOLD , 30));
        exit.setBounds(200 , 380 , 200 , 75);
        exit.setFocusable(false);
        exit.addActionListener(e -> System.exit(0));

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black , 15));
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(0x2B2D30));

        // Add labels and buttons to panels
        mainPanel.add(gameStatus);
        mainPanel.add(again);
        mainPanel.add(mainMenu);
        mainPanel.add(exit);
        mainPanel.add(copyRight);
        mainPanel.add(copyRightName);

        // Add homepage frame components
        frame.add(mainPanel);
        frame.setVisible(true);

    }
}
