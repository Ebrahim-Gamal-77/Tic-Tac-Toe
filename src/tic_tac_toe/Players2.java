package tic_tac_toe;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Players2 {
    private static final String PLAYER1_WINS = "Player 1 Wins!";
    private static final String PLAYER2_WINS = "Player 2 Wins!";
    private static final String TIE_TEXT = "It's Tie!";
    private final JButton[] cells = new JButton[9];
    private final Boolean[] cellsAvailable = new Boolean[9];
    private int roundNum = 1;
    private final JFrame frame;

    public Players2() {
        Arrays.fill(cellsAvailable, true);

        // Main frame
        frame = new JFrame("Player 1 turn ( X )");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(350, 80, 700, 700);
        frame.setIconImage(new ImageIcon(Objects
                .requireNonNull(Main.class.getClassLoader().getResource(Main.ICON_PATH)))
                .getImage());
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
                cells[temp].setEnabled(false);
                if (roundNum % 2 == 1) {
                    cells[temp].setText("X");
                    frame.setTitle("Player 2 turn ( O )");
                } else {
                    cells[temp].setText("O");
                    frame.setTitle("Player 1 turn ( X )");
                }
                cells[temp].setFont(new Font(null, Font.BOLD, 225));
                cells[temp].setForeground(Color.white);
                cellsAvailable[temp] = false;
                roundNum++;

                isGameOver();
            });

            // Add buttons to the frame
            frame.add(cells[i]);
        }

        frame.setVisible(true);
    }

    public void newLastPage(final String text) {
        try {
            new LastPage(text);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void isGameOver() {
        final Optional<String> rows = checkRows();
        if (rows.isPresent()) {
            String text = rows.get();
            newLastPage(text);
            frame.dispose();
            return;
        }

        final Optional<String> columns = checkColumns();
        if (columns.isPresent()) {
            String text = columns.get();
            newLastPage(text);
            frame.dispose();
            return;
        }

        final String diagonals = checkDiagonals();
        if (diagonals != null) {
            newLastPage(diagonals);
            frame.dispose();
            return;
        }

        // Check whether all Cells are used
        if (Arrays.stream(cellsAvailable).noneMatch(b -> b)) {
            newLastPage(TIE_TEXT);
            frame.dispose();
        }
    }

    private Optional<String> checkRows() {
        // Optionally Get the first index of the row that has 3 Buttons with the same Content that is not Empty.
        final OptionalInt rowIdx = IntStream.rangeClosed(0, 2)
                .map(i -> i * 3)
                .filter(i ->
                        !cells[i].getText().isEmpty()
                                && cells[i].getText().equals(cells[i + 1].getText())
                                && cells[i].getText().equals(cells[i + 2].getText())
                )
                .findFirst();

        // Return an optional String. If previously no row has been found
        // that met the conditions an empty Optional is returned.
        return rowIdx.stream()
                .mapToObj(tmp -> getWinnerText(cells[tmp].getText()))
                .findFirst();
    }

    private Optional<String> checkColumns() {
        // Optionally Get the first index of the column that has 3 Buttons with the same Content that is not Empty.
        final OptionalInt columnIdx = IntStream.rangeClosed(0, 2)
                .filter(i ->
                        !cells[i].getText().isEmpty()
                                && cells[i].getText().equals(cells[i + 3].getText())
                                && cells[i].getText().equals(cells[i + 6].getText())
                )
                .findFirst();

        return columnIdx.stream()
                .mapToObj(tmp -> getWinnerText(cells[tmp].getText()))
                .findFirst();
    }

    private String checkDiagonals() {
        final String topLeft = cells[0].getText();
        final String topRight = cells[2].getText();
        final String middle = cells[4].getText();
        final String bottomLeft = cells[6].getText();
        final String bottomRight = cells[8].getText();

        if (!topLeft.isEmpty() && topLeft.equals(middle) && topLeft.equals(bottomRight)) {
            return getWinnerText(topLeft);
        } else if (!topRight.isEmpty() && topRight.equals(middle) && topRight.equals(bottomLeft)) {
            return getWinnerText(topRight);
        } else {
            return null;
        }
    }

    private String getWinnerText(final String text) {
        return text.equals("X") ? PLAYER1_WINS : PLAYER2_WINS;
    }
}
