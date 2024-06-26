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
    private static final String PROJECT_GITHUB = "https://github.com/Ebrahim-Gamal-77/Tic-Tac-Toe";
    public static final String BOLI_FONT = "MV Boli";

    HomePage() {

        // Homepage frame
        final JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(400, 100, 600, 600);
        frame.setIconImage(new ImageIcon(Objects
                .requireNonNull(Main.class.getClassLoader().getResource(Main.ICON_PATH)))
                .getImage());
        frame.setResizable(false);

        // Homepage labels
        final JLabel gameName = new JLabel("Tic Tac Toe");
        gameName.setFont(new Font(BOLI_FONT, Font.BOLD, 70));
        gameName.setHorizontalAlignment(SwingConstants.CENTER);
        gameName.setVerticalAlignment(SwingConstants.TOP);
        gameName.setBounds(40, 35, 500, 100);
        gameName.setForeground(Color.orange);

        // Create copyRights
        final JLabel copyRight = getCopyRight();
        final JLabel copyRightName = getCopyRightName();

        // Homepage buttons
        final JButton players1 = new JButton("1 Player");
        players1.setFont(new Font(null, Font.BOLD, 40));
        players1.setBounds(150, 190, 300, 100);
        players1.setFocusable(false);
        players1.addActionListener(e -> {
            new Players1();
            frame.dispose();
        });

        final JButton players2 = new JButton("2 Players");
        players2.setFont(new Font(null, Font.BOLD, 40));
        players2.setBounds(150, 335, 300, 100);
        players2.setFocusable(false);
        players2.addActionListener(e -> {
            new Players2();
            frame.dispose();
        });

        // Main panel
        final JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 15));
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(0x2B2D30));

        // Add labels and buttons to panels
        mainPanel.add(gameName);
        mainPanel.add(players1);
        mainPanel.add(players2);
        mainPanel.add(copyRightName);
        mainPanel.add(copyRight);

        // Add homepage frame components
        frame.add(mainPanel);
        frame.setVisible(true);

    }

    public static JLabel getCopyRight() {
        final JLabel copyRight = new JLabel("Created by: ");
        copyRight.setFont(new Font(BOLI_FONT, Font.PLAIN, 20));
        copyRight.setHorizontalAlignment(SwingConstants.CENTER);
        copyRight.setVerticalAlignment(SwingConstants.CENTER);
        copyRight.setBounds(115, 466, 200, 100);
        copyRight.setForeground(Color.white);
        return copyRight;
    }

    public static JLabel getCopyRightName() {
        final JLabel copyRightName = new JLabel("Ebrahim & Karam");
        copyRightName.setFont(new Font(BOLI_FONT, Font.BOLD, 20));
        copyRightName.setHorizontalAlignment(SwingConstants.CENTER);
        copyRightName.setVerticalAlignment(SwingConstants.CENTER);
        copyRightName.setBounds(270, 500, 185, 35);
        copyRightName.setForeground(Color.red);
        copyRightName.setCursor(new Cursor(Cursor.HAND_CURSOR));
        copyRightName.getCursor();
        copyRightName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(PROJECT_GITHUB));
                } catch (IOException | URISyntaxException ex) {
                    System.out.println("Something is wrong with Github URL");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                copyRightName.setText("""
                        <html>
                          <a style="color:aqua;" href=''>Visit Github</a>
                        </html>""");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                copyRightName.setText("Ebrahim & Karam");
            }
        });
        return copyRightName;
    }
}
