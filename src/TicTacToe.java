import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 700;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel scorePanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX ="X";
    String playerO ="O";
    String currentPlayer= playerX;

    boolean gameOver = false;
    int turns = 0;
    int scoreX = 0;
    int scoreO = 0;
    JLabel scoreLabel = new JLabel();

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textLabel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.blue);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board [r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.pink);
                tile.setForeground(Color.black);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile= (JButton) e.getSource();
                        if (tile.getText() == ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver){
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }
    
    JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.BOLD, 20));
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(restartButton, BorderLayout.SOUTH);
        updateScore();
        scorePanel.add(scoreLabel, BorderLayout.NORTH);
        frame.add(scorePanel, BorderLayout.SOUTH);
    }

            
    void checkWinner()  {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {
                    setWinner(r, 0, r, 1, r, 2);
                    return;
            }
        }

        //vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;
            
            if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {
        
                setWinner(0, c, 1, c, 2, c);
                return;
            }
        }

        //diagonally
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "") {
            setWinner(0, 0, 1, 1, 2, 2);
            return;
        }

        //anti-diagonally
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
            setWinner(0, 2, 1, 1, 2, 0);
            return;
        }

        if (turns ==9){
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
            }
        }
        textLabel.setText("It's a Tie!");
        gameOver = true;            
                
        
    }
    }
    void setWinner(int r1, int c1, int r2, int c2, int r3, int c3) {
        board[r1][c1].setForeground(Color.green);
        board[r1][c1].setBackground(Color.gray);
        board[r2][c2].setForeground(Color.green);
        board[r2][c2].setBackground(Color.gray);
        board[r3][c3].setForeground(Color.green);
        board[r3][c3].setBackground(Color.gray);

        textLabel.setText(currentPlayer + " is the winner!");
        if (currentPlayer.equals(playerX)) {
            scoreX++;
        } else {
            scoreO++;
        }
        updateScore();
        gameOver = true;
    }

    void setTie(JButton tile){
            tile.setForeground(Color.orange);
            tile.setBackground(Color.gray);

    }
    void updateScore() {
        scoreLabel.setText("Score - X: " + scoreX + " | O: " + scoreO);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    void resetBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(Color.pink);
                board[r][c].setForeground(Color.black);
            }
        }
        gameOver = false;
        turns = 0;
        currentPlayer = playerX;
        textLabel.setText("Tic-Tac-Toe");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

