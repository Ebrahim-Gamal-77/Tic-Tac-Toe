package tic_tac_toe;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;
import java.util.Timer;

public class Players1 {
    private static final String WIN_TEXT = "You Win!";
    private static final String LOSE_TEXT = "You Lose!";
    private static final String TIE_TEXT = "It's Tie!";

    private final Random random = new Random();
    private final JButton[] cells = new JButton[9];
    private final Boolean[] availableCells = new Boolean[9];
    private final JFrame frame;

    private int computerTurn = random.nextInt(0, 9);
    private int roundNum = 0;

    public Players1() {

        // Initializing available cells
        Arrays.fill(availableCells, true);

        // Main frame
        frame = new JFrame("Tic Tac Toe ( 1 Player )");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(350, 80, 700, 700);
        frame.setIconImage(new ImageIcon(Objects
                .requireNonNull(Main.class.getClassLoader().getResource(Main.ICON_PATH)))
                .getImage());
        frame.setResizable(true);
        frame.setLayout(new GridLayout(3, 3, 5, 5));
        frame.getContentPane().setBackground(Color.BLACK);

        // Adjust buttons

        final AtomicBoolean isPlayerTurn = new AtomicBoolean(true);

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
                if (!isPlayerTurn.get()) {
                    return;
                }
                cells[temp].setEnabled(false);
                cells[temp].setText("X");
                cells[temp].setFont(new Font(null, Font.BOLD, 225));
                cells[temp].setForeground(Color.white);
                availableCells[temp] = false; // need to know why didn't add reserved cells
                roundNum++;

                if (isGameOver()) {
                    return;
                }

                // This to stop player from putting 'X' while it's computer turn
                isPlayerTurn.set(false);

                // Check if new computer turn is reserved or not
                if (roundNum < 5) {

                    // Adding a timer for computer turn
                    Timer computerTimer = new Timer();
                    computerTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // Give me all available cells by index
                            final int[] arr = IntStream.range(0, availableCells.length).filter(idx -> availableCells[idx])
                                    .toArray();
                            computerTurn = arr[random.nextInt(arr.length)];
                            cells[computerTurn].setEnabled(false);
                            cells[computerTurn].setText("O");
                            cells[computerTurn].setFont(new Font(null, Font.BOLD, 225));
                            cells[computerTurn].setForeground(Color.white);
                            availableCells[computerTurn] = false;
                            isGameOver();

                            isPlayerTurn.set(true);
                            computerTimer.cancel();
                        }
                    }, 300);


                }

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

    public boolean isGameOver() {
        final Optional<String> rows = checkRows();
        if (rows.isPresent()) {
            String text = rows.get();
            newLastPage(text);
            frame.dispose();
            return true;
        }

        final Optional<String> columns = checkColumns();
        if (columns.isPresent()) {
            String text = columns.get();
            newLastPage(text);
            frame.dispose();
            return true;
        }

        final String diagonals = checkDiagonals();
        if (diagonals != null) {
            newLastPage(diagonals);
            frame.dispose();
            return true;
        }

        // Check Whether all Cells are Occupied
        if (Arrays.stream(availableCells).noneMatch(b -> b)) {
            newLastPage(TIE_TEXT);
            frame.dispose();
            return true;
        }
        return false;
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
        return text.equals("X") ? WIN_TEXT : LOSE_TEXT;
    }
}
