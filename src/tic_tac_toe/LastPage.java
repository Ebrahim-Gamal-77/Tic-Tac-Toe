package tic_tac_toe;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.*;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static tic_tac_toe.HomePage.BOLI_FONT;
import static tic_tac_toe.HomePage.getCopyRightName;

public class LastPage {

    private final JFrame frame;

    public LastPage(String status) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        // Last page frame
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(400 , 100 , 600 , 600);
        frame.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getClassLoader().getResource("tic_tac_toe/additions/xo icon 512.png"))).getImage());
        frame.setResizable(false);

        // Last page labels
        JLabel gameStatus = new JLabel(status);
        if(status.equals("Player 1 Wins!") || status.equals("Player 2 Wins!")){
            gameStatus.setFont(new Font(BOLI_FONT , Font.BOLD , 65));
        }else gameStatus.setFont(new Font(BOLI_FONT , Font.BOLD , 70));
        gameStatus.setHorizontalAlignment(SwingConstants.CENTER);
        gameStatus.setVerticalAlignment(SwingConstants.TOP);
        gameStatus.setBounds(40 , 25 ,  500 , 100);
        // Changing status color
        switch (status) {
            // 1 Player cases
            case "You Win!" -> {
                gameStatus.setForeground(Color.green);
                File file = new File("src/tic_tac_toe/additions/win stat.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-7.0f);
                clip.start();
            }
            case "You Lose!" -> {
                gameStatus.setForeground(Color.red);
                File file = new File("src/tic_tac_toe/additions/lose stat.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-18.0f);
                clip.start();
            }
            case "It's Tie!" -> {
                gameStatus.setForeground(Color.yellow);
                File file = new File("src/tic_tac_toe/additions/tie stat.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-20.0f);
                clip.start();
            }
            // 2 Players cases
            case "Player 1 Wins!" -> {
                gameStatus.setForeground(Color.green);
                File file = new File("src/tic_tac_toe/additions/win stat.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-7.0f);
                clip.start();
            }
            case "Player 2 Wins!" -> {
                gameStatus.setForeground(Color.green);
                File file = new File("src/tic_tac_toe/additions/win stat.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-7.0f);
                clip.start();
            }
            default -> {
                gameStatus.setText("Text Error!");
                gameStatus.setForeground(Color.red);
            }
        }

        // Create copyRights
        JLabel copyRight = HomePage.getCopyRight();
        JLabel copyRightName = getCopyRightName();

        // Last page buttons
        JButton again = new JButton("Again");
        again.setFont(new Font(null , Font.BOLD , 30));
        again.setBounds(200 , 160 , 200 , 75);
        again.setFocusable(false);

        // Getting last class name and play it again
        String lastClass = new Exception().getStackTrace()[1].getClassName();
        Pattern pattern = Pattern.compile("Players[12]" , Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(lastClass);
        again.addActionListener(e -> {
            frame.dispose();
                if (matcher.find()) {
                    if (matcher.group().equals("Players1")) {
                        new Players1();
                    } else if (matcher.group().equals("Players2")) {
                        new Players2();
                    }
                }
        });

        JButton mainMenu = new JButton("Main menu");
        mainMenu.setFont(new Font(null , Font.BOLD , 30));
        mainMenu.setBounds(200 , 270 , 200 , 75);
        mainMenu.setFocusable(false);
        mainMenu.addActionListener(e -> {
            //noinspection InstantiationOfUtilityClass
            new HomePage();
            frame.dispose();
        });

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
        mainPanel.add(mainMenu);
        mainPanel.add(again);
        mainPanel.add(exit);
        mainPanel.add(copyRight);
        mainPanel.add(copyRightName);

        // Add homepage frame components
        frame.add(mainPanel);
        frame.setVisible(true);

    }
}
