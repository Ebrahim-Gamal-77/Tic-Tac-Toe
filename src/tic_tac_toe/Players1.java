package tic_tac_toe;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;


public class Players1 {
    private static final String WIN_TEXT = "You Win!";
    private static final String LOSE_TEXT = "You Lose!";
    private static final String TIE_TEXT = "It's Tie!";
    private final JButton[] cells = new JButton[9];
    private final JFrame frame;
    private final LinkedList<Integer> availableCells = new LinkedList<>();
    private int computerTurn = new Random().nextInt(0 ,  9);
    private int roundNum = 0;

    public Players1() {

        // Initializing available cells
        for (int i = 0; i < 9; i++) {
            availableCells.add(i);
        }

        // Main frame
        frame = new JFrame("Tic Tac Toe ( 1 Player )");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(350, 80, 700, 700);
        frame.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getClassLoader().getResource("xo icon 512.png"))).getImage());
        frame.setResizable(true);
        frame.setLayout(new GridLayout(3, 3, 5, 5));
        frame.getContentPane().setBackground(Color.BLACK);

        // Adjust buttons
        for (int i = 0; i < cells.length; i++) {
            // Create button's constructor
            cells[i] = new JButton();

            // Adjust buttons attributes
            cells[i].setBackground(Color.darkGray);
            cells[i].setFocusable(false);
            cells[i].setContentAreaFilled(false);

            // Set buttons Action Listener
            final int temp = i;
            cells[i].addActionListener(e -> {
                // Player turn
                cells[temp].setEnabled(false);
                cells[temp].setText("X");
                cells[temp].setFont(new Font(null, Font.BOLD, 225));
                cells[temp].setForeground(Color.white);
                availableCells.remove(Integer.valueOf(temp)); // need to know why didn't add reserved cells
                roundNum++;

                if (isGameOver(cells)) {
                    return;
                }
                // Check if new computer turn is reserved or not
                if (roundNum < 5) {
                        final int rnd = new Random().nextInt(availableCells.size());
                        computerTurn = availableCells.get(rnd);
                }
                cells[computerTurn].setEnabled(false);
                cells[computerTurn].setText("O");
                cells[computerTurn].setFont(new Font(null, Font.BOLD, 225));
                cells[computerTurn].setForeground(Color.white);
                availableCells.remove(Integer.valueOf(computerTurn));

                isGameOver(cells);

            });

            // Add buttons to the frame
            frame.add(cells[i]);
        }

        frame.setVisible(true);
    }

    public void newLastPage(String text){
        try {
            new LastPage(text);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isGameOver(JButton[] buttons) {
        Optional<String> rows = checkRows(buttons);
        if (rows.isPresent()) {
            String text = rows.get();
            newLastPage(text);
            frame.dispose();
            return true;
        }

        Optional<String> columns = checkColumns(buttons);
        if (columns.isPresent()) {
            String text = columns.get();
            newLastPage(text);
            frame.dispose();
            return true;
        }

        String diagonals = checkDiagonals(buttons);
        if (diagonals != null) {
            newLastPage(diagonals);
            frame.dispose();
            return true;
        }
//         Arrays.stream(buttons).noneMatch(button -> button.getText().isEmpty()) // Declarative programming
        if (availableCells.isEmpty()) {
            newLastPage(TIE_TEXT);
            frame.dispose();
            return true;
        }
        return false;
    }

    private Optional<String> checkRows(JButton[] buttons) {
        for (int i = 0; i <= 6; i += 3) {
            if (buttons[i].getText().isEmpty() || !buttons[i].getText().equals(buttons[i + 1].getText()) || !buttons[i + 1].getText().equals(buttons[i + 2].getText())) {
                continue;
            }

            String text = buttons[i].getText().equals("X") ? WIN_TEXT : LOSE_TEXT;
            return Optional.of(text);
        }
        return Optional.empty();
    }

    private Optional<String> checkColumns(JButton[] cells) {
        for (int i = 0; i <= 2; i++) {
            if (cells[i].getText().isEmpty() || !cells[i].getText().equals(cells[i + 3].getText()) || !cells[i + 3].getText().equals(cells[i + 6].getText())) {
                continue;
            }

            String text = cells[i].getText().equals("X") ? WIN_TEXT : LOSE_TEXT;
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
