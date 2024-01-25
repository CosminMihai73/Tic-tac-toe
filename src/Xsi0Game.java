import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Xsi0Game extends JFrame {

    private JButton[][] buttons;
    private char currentPlayer;

    public Xsi0Game() {
        setTitle("Tic-tac-toe");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new JButton[3][3];
        currentPlayer = 'X';

        initializeButtons();
    }

    private void initializeButtons() {
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener());
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            if (clickedButton.getText().equals("")) {
                clickedButton.setText(String.valueOf(currentPlayer));
                clickedButton.setEnabled(false);

                if (checkForWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    resetGame();
                } else {
                    switchPlayer();
                }
            }
        }
    }

    private boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true; // Row win
            }

            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true; // Column win
            }
        }

        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true; // Diagonal win
        }

        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true; // Diagonal win
        }

        return false;
    }

    private boolean isBoardFull() {
        // Check if the board is full
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // There's an empty space
                }
            }
        }
        return true; // Board is full
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void resetGame() {
        // Clear the board and enable buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Xsi0Game game = new Xsi0Game();
            game.setVisible(true);
        });
    }
}
