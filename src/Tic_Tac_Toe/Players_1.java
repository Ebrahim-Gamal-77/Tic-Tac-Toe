package Tic_Tac_Toe;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Players_1 {
    // Computer round
    Random random = new Random();
    JFrame frame;
    int computerTurn = random.nextInt(0 , 9);
    private final JButton[] buttons = new JButton[9];
    private int roundNum = 0;
    public Players_1() {

        // Main frame
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(350, 80, 700, 700);
        try {
            frame.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getClassLoader().getResource("xo icon 512(1).png"))).getImage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        frame.setResizable(true);
        frame.setLayout(new GridLayout(3 , 3 , 5 , 5));
        frame.getContentPane().setBackground(Color.BLACK);

        // Adjust buttons
        for (int i = 0; i < buttons.length; i++) {
            // Create buttons constructor
            buttons[i] = new JButton();

            // Adjust buttons attributes
            buttons[i].setBackground(Color.darkGray);
            buttons[i].setFocusable(false);
            buttons[i].setContentAreaFilled(false);

            // Set buttons Action Listener
            int temp = i;
            buttons[i].addActionListener(e -> {
                // Player turn
                buttons[temp].setEnabled(false);
                buttons[temp].setText("X");
                buttons[temp].setFont(new Font(null , Font.BOLD , 225));
                buttons[temp].setForeground(Color.white);
                roundNum++;

                if(roundNum >= 3) {
                    checkWinner(buttons);
                }
                // Check if new computer turn is reserved or not
                if(roundNum < 5) {
                    while (!buttons[computerTurn].getText().isEmpty()) { // need to exclude reserved places
                        computerTurn = random.nextInt(0, 9);
                    }
                }

                buttons[computerTurn].setEnabled(false);
                buttons[computerTurn].setText("O");
                buttons[computerTurn].setFont(new Font(null , Font.BOLD , 225));
                buttons[computerTurn].setForeground(Color.white);

                if(roundNum >= 3) {
                    checkWinner(buttons);
                }

            });

            // Add buttons to the frame
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    // Create method of your entire code
    public void checkWinner(JButton[] buttons){
        if (!buttons[0].getText().isEmpty() && buttons[0].getText().equals(buttons[1].getText()) && buttons[1].getText().equals(buttons[2].getText())) {
            if (buttons[0].getText().equals("X")) {
                new LastWindow("You Win!");
                frame.dispose();
            }else {
                new LastWindow("You Lose!");
                frame.dispose();
            }
        }
        else if(!buttons[3].getText().isEmpty() && buttons[3].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[5].getText())) {
            if (buttons[3].getText().equals("X")) {
                new LastWindow("You Win!");
                frame.dispose();
            }else {
                new LastWindow("You Lose!");
                frame.dispose();
            }
        }
        else if(!buttons[6].getText().isEmpty() && buttons[6].getText().equals(buttons[7].getText()) && buttons[7].getText().equals(buttons[8].getText())) {
            if (buttons[6].getText().equals("X")) {
                new LastWindow("You Win!");
                frame.dispose();
            }else {
                new LastWindow("You Lose!");
                frame.dispose();
            }
        }
        else if(!buttons[0].getText().isEmpty() && buttons[0].getText().equals(buttons[3].getText()) && buttons[3].getText().equals(buttons[6].getText())) {
            if (buttons[0].getText().equals("X")) {
                new LastWindow("You Win!");
                frame.dispose();
            }else {
                new LastWindow("You Lose!");
                frame.dispose();
            }
        }
        else if(!buttons[1].getText().isEmpty() && buttons[1].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText())) {
            if (buttons[1].getText().equals("X")) {
                new LastWindow("You Win!");
                frame.dispose();
            }else {
                new LastWindow("You Lose!");
                frame.dispose();
            }
        }
        else if(!buttons[2].getText().isEmpty() && buttons[2].getText().equals(buttons[5].getText()) && buttons[5].getText().equals(buttons[8].getText())) {
            if (buttons[2].getText().equals("X")) {
                new LastWindow("You Win!");
                frame.dispose();
            }else {
                new LastWindow("You Lose!");
                frame.dispose();
            }
        }
        else if(!buttons[0].getText().isEmpty() && buttons[0].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText())) {
            if (buttons[0].getText().equals("X")) {
                new LastWindow("You Win!");
                frame.dispose();
            }else {
                new LastWindow("You Lose!");
                frame.dispose();
            }
        }
        else if(!buttons[2].getText().isEmpty() && buttons[2].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[6].getText())) {
            if (buttons[2].getText().equals("X")) {
                new LastWindow("You Win!");
                frame.dispose();
            }else {
                new LastWindow("You Lose!");
                frame.dispose();
            }
        } else {
            int counter = 0;
            for (int i = 0; i < 9; i++) {
                if(!buttons[i].getText().isEmpty()) {
                    counter++;
                }
            }
            if (counter == 9) {
                new LastWindow("It's Tie!");
            }
        }
    }

}
