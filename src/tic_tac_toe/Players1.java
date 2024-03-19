package tic_tac_toe;


import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;


public class Players1 {
    private static final Random random = new Random();
    private static final String WIN_TEXT = "You Win!";
    private static final String LOSE_TEXT = "You Lose!";
    private static final String TIE_TEXT = "It's a Tie!";
    private final JButton[] buttons = new JButton[9];
    private final JFrame frame;
    private int computerTurn = random.nextInt(0, 9);
    private int roundNum = 0;

    public Players1() {
        // Main frame
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(350, 80, 700, 700);
        frame.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getClassLoader().getResource("xo icon 512(1).png"))).getImage());
        frame.setResizable(true);
        frame.setLayout(new GridLayout(3, 3, 5, 5));
        frame.getContentPane().setBackground(Color.BLACK);

        // Adjust buttons
        for (int i = 0; i < buttons.length; i++) {
            // Create button's constructor
            buttons[i] = new JButton();

            // Adjust buttons attributes
            buttons[i].setBackground(Color.darkGray);
            buttons[i].setFocusable(false);
            buttons[i].setContentAreaFilled(false);

            // Set buttons Action Listener
            final int temp = i;
            buttons[i].addActionListener(e -> {
                // Player turn
                buttons[temp].setEnabled(false);
                buttons[temp].setText("X");
                buttons[temp].setFont(new Font(null, Font.BOLD, 225));
                buttons[temp].setForeground(Color.white);
                roundNum++;

                if (isGameOver(buttons)) {
                    return;
                }
                // Check if new computer turn is reserved or not
                if (roundNum < 5) {
                    while (!buttons[computerTurn].getText().isEmpty()) { // need to exclude reserved places
                        computerTurn = random.nextInt(0, 9);
                    }
                }
                buttons[computerTurn].setEnabled(false);
                buttons[computerTurn].setText("O");
                buttons[computerTurn].setFont(new Font(null, Font.BOLD, 225));
                buttons[computerTurn].setForeground(Color.white);

                isGameOver(buttons);

            });

            // Add buttons to the frame
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    public boolean isGameOver(JButton[] buttons) {
        Optional<String> rows = checkRows(buttons);
        if (rows.isPresent()) {
            String text = rows.get();
            new LastPage(text);
            frame.dispose();
            return true;
        }

        Optional<String> columns = checkColumns(buttons);
        if (columns.isPresent()) {
            String text = columns.get();
            new LastPage(text);
            frame.dispose();
            return true;
        }

        String diagonals = checkDiagonals(buttons);
        if (diagonals != null) {
            new LastPage(diagonals);
            frame.dispose();
            return true;
        }

        if (Arrays.stream(buttons).noneMatch(button -> button.getText().isEmpty())) {
            new LastPage(TIE_TEXT);
            frame.dispose();
            return true;
        }
        return false;
    }

    private Optional<String> checkRows(JButton[] buttons) {
        for (int i = 0; i < 6; i += 3) {
            if (buttons[i].getText().isEmpty() || !buttons[i].getText().equals(buttons[i + 1].getText()) || !buttons[i + 1].getText().equals(buttons[i + 2].getText())) {
                continue;
            }

            String text = buttons[i].getText().equals("X") ? WIN_TEXT : LOSE_TEXT;
            return Optional.of(text);
        }
        return Optional.empty();
    }

    private Optional<String> checkColumns(JButton[] buttons) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().isEmpty() || !buttons[i].getText().equals(buttons[i + 3].getText()) || !buttons[i + 3].getText().equals(buttons[i + 6].getText())) {
                continue;
            }

            String text = buttons[i].getText().equals("X") ? WIN_TEXT : LOSE_TEXT;
            return Optional.of(text);
        }
        return Optional.empty();
    }

    private String checkDiagonals(JButton[] buttons) {
        String text = null;
        if (!buttons[0].getText().isEmpty() && buttons[0].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText())) {
            text = buttons[0].getText().equals("X") ? WIN_TEXT : LOSE_TEXT;
        } else if (!buttons[2].getText().isEmpty() && buttons[2].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[6].getText())) {
            text = buttons[2].getText().equals("X") ? WIN_TEXT : LOSE_TEXT;
        }
        return text;
    }
}
