package ija.ija2022.homework2.common.lib;

import ija.ija2022.homework2.common.game.PacmanMaze;
import ija.ija2022.homework2.common.lib.common.CommonField;
import ija.ija2022.homework2.common.lib.common.CommonMaze;
import ija.ija2022.homework2.common.lib.common.CommonMazeObject;
import ija.ija2022.homework2.common.lib.view.FieldView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Maze presenter.
 */
public class MazePresenter {
    private CommonMaze maze;
    private boolean firstFlag = true;
    private JFrame frame;
    private JPanel buttonPanel;

    /**
     * Instantiates a new Maze presenter.
     *
     * @param maze the maze
     */
    public MazePresenter(CommonMaze maze) {
        this.maze = maze;
    }

    /**
     * Open.
     */
    public void open() {
        SwingUtilities.invokeLater(this::initializeInterface);
    }

    /**
     * Open log.
     */
    public void openLog() {
        try {
            SwingUtilities.invokeAndWait(this::refreshLogInterface);
        } catch (InvocationTargetException | InterruptedException var2) {
            Logger.getLogger(MazePresenter.class.getName()).log(Level.SEVERE, (String)null, var2);
        }
    }

    private void initializeInterface() {
        JFrame frame = new JFrame("Pacman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setPreferredSize(new Dimension(350, 400));
        frame.setResizable(false);

        JLabel stepLabel = new JLabel("Steps: ");
        JLabel keyLabel = new JLabel("Keys: ");

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(stepLabel);
        labelPanel.add(keyLabel);

        frame.getContentPane().add(labelPanel, BorderLayout.SOUTH);
        // Add KeyListener to the JFrame
        frame.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                // Handle key press event here
                int keyCode = e.getKeyCode();
                if (!maze.getPacman().isDead()) {
                    if (keyCode == KeyEvent.VK_UP) {
                        maze.getPacman().move(CommonField.Direction.U);
                    } else if (keyCode == KeyEvent.VK_DOWN) {
                        maze.getPacman().move(CommonField.Direction.D);
                    } else if (keyCode == KeyEvent.VK_LEFT) {
                        maze.getPacman().move(CommonField.Direction.L);
                    } else if (keyCode == KeyEvent.VK_RIGHT) {
                        maze.getPacman().move(CommonField.Direction.R);
                    }
                }
                stepLabel.setText("Steps: " + maze.getPacman().getSteps());
                keyLabel.setText("Keys remaining: " + maze.getKeyCounter());
            }
        });

        //Add MouseListener to the JFrame
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JOptionPane.showMessageDialog(frame, "This is Pacman game, you are Pacman(yellow dot), in order to win you have to get to the Target(green dot) and collect Key(blue dot) if such exists. Avoid ghosts(red dots) or you will lose.");
                }
            }
        });

        for (CommonMazeObject ghost : maze.ghosts()) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ghost.randomMove();
                }
            }, 0, 1000); // move every 1000 milliseconds (1 second)
        }

    // Get the current time with seconds precision
    LocalDateTime now = LocalDateTime.now();

    // Format the time as a string, e.g. "2023-05-07-14-35-10"
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    String timestamp = now.format(formatter);

    // Construct the file name with the timestamp, e.g. "file-2023-05-07-14-35-10.txt"
    String logName = "data/logs/log-" + timestamp + ".txt";
    final boolean[] firstIter = {true};

    Timer gameOverCheck = new Timer();
    gameOverCheck.schedule(new TimerTask() {
        public void run() {
            String curLog = maze.toText();
            if (!curLog.equals(maze.getInput())) {
                try {
                    // Open the file in append mode
                    BufferedWriter writer = new BufferedWriter(new FileWriter(logName, true));

                    // Write the string to the file
                    if (firstIter[0]) {
                        writer.write(maze.getInput());
                        writer.newLine();
                        firstIter[0] = false;
                    }
                    writer.write(curLog);

                    // Add a newline character to the end of the string
                    writer.newLine();

                    // Close the writer
                    writer.close();
                    maze.setInput(curLog);
                } catch (IOException e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
            if (maze.getPacman().isDead() || maze.getPacman().isVictory()) {
                // Cancel the timer
                gameOverCheck.cancel();

                // Show game over or victory screen
                JPanel gameOverPanel = new JPanel(new BorderLayout());
                JLabel gameOverLabel = new JLabel(maze.getPacman().isDead() ? "Game Over" : "You Won!", SwingConstants.CENTER);
                gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
                gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);

                // Create a Close button
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(e -> {
                    frame.dispose();
                });

                // Add the Close button to the panel
                gameOverPanel.add(closeButton, BorderLayout.SOUTH);

                frame.getContentPane().removeAll();
                frame.getContentPane().add(gameOverPanel);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        }
    }, 0, 100);

        int rows = this.maze.numRows();
        int cols = this.maze.numCols();
        GridLayout layout = new GridLayout(rows, cols);
        JPanel content = new JPanel(layout);

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                FieldView field = new FieldView(this.maze.getField(i, j));
                content.add(field);
            }
        }

        frame.getContentPane().add(content, "Center");
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Sets maze.
     *
     * @param maze the maze
     */
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    /**
     * Sets frame.
     *
     * @param frame the frame
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Gets maze.
     *
     * @return the maze
     */
    public CommonMaze getMaze() {
        return new PacmanMaze(this.maze.numRows(), this.maze.numCols(), this.maze.ghosts(), this.maze.getPacman(), this.maze.getKeyCounter());
    }

    /**
     * Refresh log interface.
     */
    public void refreshLogInterface() {
        int rows = this.maze.numRows();
        int cols = this.maze.numCols();
        GridLayout layout = new GridLayout(rows, cols);
        JPanel content = new JPanel(layout);

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                FieldView field = new FieldView(this.maze.getField(i, j));
                content.add(field);
            }
        }

        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().add(content, BorderLayout.CENTER);
        this.frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        this.frame.revalidate();
        this.frame.repaint();
    }

    /**
     * Initialize log interface.
     */
    public void initializeLogInterface() {
        this.frame = new JFrame("Pacman Log");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(350, 400);
        this.frame.setPreferredSize(new Dimension(350, 400));
        this.frame.setResizable(false);

        int rows = this.maze.numRows();
        int cols = this.maze.numCols();
        GridLayout layout = new GridLayout(rows, cols);
        JPanel content = new JPanel(layout);

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                FieldView field = new FieldView(this.maze.getField(i, j));
                content.add(field);
            }
        }
        // create panel to hold the close button
        buttonPanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // close the JFrame when the button is clicked
            }
        });
        buttonPanel.add(closeButton);

        // add both panels to the JFrame
        this.frame.getContentPane().add(content, BorderLayout.CENTER);
        this.frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        this.frame.pack();
        this.frame.setVisible(true);
        this.firstFlag = false;
    }

}
