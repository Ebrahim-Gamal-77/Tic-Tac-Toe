package Tic_Tac_Toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        // Homepage frame
        JFrame homePage = new JFrame("Tic Tac Toe");
        homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homePage.setBounds(400 , 100 , 600 , 600);
        try {
            homePage.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getClassLoader().getResource("xo icon 512(1).png"))).getImage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        homePage.setResizable(false);


        // Homepage labels
        JLabel gameName = new JLabel("Tic Tac Toe");
        gameName.setFont(new Font("MV Boli" , Font.BOLD , 70));
        gameName.setHorizontalAlignment(JLabel.CENTER);
        gameName.setVerticalAlignment(JLabel.TOP);
        gameName.setBounds(40 , 35 ,  500 , 100);
        gameName.setForeground(Color.white);

        JLabel copyRight = new JLabel("Created by: ");
        copyRight.setFont(new Font("MV Boli" , Font.PLAIN , 20));
        copyRight.setHorizontalAlignment(JLabel.CENTER);
        copyRight.setVerticalAlignment(JLabel.TOP);
        copyRight.setBounds(115 , 500 ,  200 , 100);
        copyRight.setForeground(Color.white);


        JLabel copyRightName = new JLabel("Ebrahim Gamal");
        copyRightName.setFont(new Font("MV Boli" , Font.BOLD , 20));
        copyRightName.setHorizontalAlignment(JLabel.CENTER);
        copyRightName.setVerticalAlignment(JLabel.TOP);
        copyRightName.setBounds(250 , 500 ,  200 , 100);
        copyRightName.setForeground(new Color(0xE00A0A));

        // Homepage buttons
        JButton player_1 = new JButton("1 Player");
        player_1.setFont(new Font(null , Font.BOLD , 40));
        player_1.setBounds(150 , 190 , 300 , 100);
        player_1.setFocusable(false);
        player_1.addActionListener(e -> {
            homePage.dispose();
            new Players_1();
        });

        JButton player_2 = new JButton("2 Players");
        player_2.setFont(new Font(null , Font.BOLD , 40));
        player_2.setBounds(150 , 335 , 300 , 100);
        player_2.setFocusable(false);

        // Homepage panels

            // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black , 15));
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(0x2B2D30));



        // Add labels and buttons to panels
        mainPanel.add(gameName);
        mainPanel.add(player_1);
        mainPanel.add(player_2);
        mainPanel.add(copyRight);
        mainPanel.add(copyRightName);





        // Add homepage frame components

        homePage.add(mainPanel);
        homePage.setVisible(true);

        new Players_1();
    }
}