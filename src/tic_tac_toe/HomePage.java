package tic_tac_toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class HomePage {
    public static final String BOLI_FONT = "MV Boli";

        HomePage() {
        
        // Homepage frame
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(400 , 100 , 600 , 600);
        frame.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getClassLoader().getResource("xo icon 512.png"))).getImage());
        frame.setResizable(false);

        // Homepage labels
        JLabel gameName = new JLabel("Tic Tac Toe");
        gameName.setFont(new Font(BOLI_FONT , Font.BOLD , 70));
        gameName.setHorizontalAlignment(SwingConstants.CENTER);
        gameName.setVerticalAlignment(SwingConstants.TOP);
        gameName.setBounds(40 , 35 ,  500 , 100);
        gameName.setForeground(Color.orange);

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
        JButton player1 = new JButton("1 Player");
        player1.setFont(new Font(null , Font.BOLD , 40));
        player1.setBounds(150 , 190 , 300 , 100);
        player1.setFocusable(false);
        player1.addActionListener(e -> {
            frame.dispose();
            new Players1();
        });

        JButton player2 = new JButton("2 Players");
        player2.setFont(new Font(null , Font.BOLD , 40));
        player2.setBounds(150 , 335 , 300 , 100);
        player2.setFocusable(false);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black , 15));
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(0x2B2D30));

        // Add labels and buttons to panels
        mainPanel.add(gameName);
        mainPanel.add(player1);
        mainPanel.add(player2);
        mainPanel.add(copyRightName);
        mainPanel.add(copyRight);

        // Add homepage frame components
        frame.add(mainPanel);
        frame.setVisible(true);

    }
}
