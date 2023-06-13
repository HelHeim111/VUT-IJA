package ija.ija2022.homework2.common.game;

import ija.ija2022.homework2.common.lib.MazePresenter;
import ija.ija2022.homework2.common.lib.common.CommonMaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * The type Read file ui.
 */
public class ReadFileUI extends JFrame implements ActionListener {
    private JButton openButton, startButton, readLog;
    private JTextArea textArea;
    private File map, log;
    private int numCols, numRows;
    private CommonMaze maze;
    private ArrayList mazeLogs;
    private BufferedReader logReader;
    private FileReader logFile;
    private boolean firstIter = true;
    //CountDownLatch latch;

    /**
     * Gets maze logs.
     *
     * @return the maze logs
     */
    public ArrayList getMazeLogs() {
        return this.mazeLogs;
    }
    private void start() {
        MazeConfigure cfg = new MazeConfigure();

        // Read the file line by line
        try {
            FileReader fileReader = new FileReader(this.map);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            StringBuilder stringBuilder = new StringBuilder();
            int counter = 0;
            while ((line = bufferedReader.readLine()) != null && !line.trim().isEmpty()) {
                stringBuilder.append(line).append(System.lineSeparator());
                if (counter == 0) {
                    String[] splitted = line.split(" ");
                    if (splitted.length == 2) {
                        numRows = Integer.parseInt(splitted[0]);
                        numCols = Integer.parseInt(splitted[1]);
                    } else {
                        System.err.println("Wrong map!");
                    }
                    cfg.startReading(numRows, numCols);
                } else {
                    cfg.processLine(line);
                }
                counter++;
            }
            cfg.stopReading();
            maze = cfg.createMaze();
            maze.setInput(stringBuilder.toString());

            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void startLog() {
        MazeConfigure cfg = new MazeConfigure();
        mazeLogs = new ArrayList();
            int counter = 0;

                try {
                    String line;
                    while ((line = logReader.readLine()) != null) {
                        if (line.trim().isEmpty() && counter != 1) {
                            counter = 0;
                            cfg.stopReading();
                            this.mazeLogs.add(cfg.createMaze());
                            continue;
                        }
                        if (counter == 0) {
                            String[] splitted = line.split(" ");
                            if (splitted.length == 2) {
                                numRows = Integer.parseInt(splitted[0]);
                                numCols = Integer.parseInt(splitted[1]);
                            } else {
                                System.err.println("Wrong map!");
                            }
                            cfg = new MazeConfigure();
                            cfg.startReading(numRows, numCols);
                        } else {
                            cfg.processLine(line);
                        }
                        counter++;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    //latch.countDown();
                }
    }

    private void closeLogReader() {
        try {
            logReader.close();
            logFile.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Instantiates a new Read file ui.
     */
    public ReadFileUI() {
        super("Choose Map");
        mazeLogs = new ArrayList();

        // Create GUI components
        openButton = new JButton("Open Map");
        openButton.addActionListener(this);
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        startButton.setEnabled(false);
        readLog = new JButton("Read from log file");
        readLog.addActionListener(this);
        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);

        // Add components to the JFrame
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.add(openButton);
        buttonPanel.add(startButton);
        buttonPanel.add(readLog);
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Set JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("data/maps"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                startButton.setEnabled(true);
                this.map = fileChooser.getSelectedFile();
                textArea.setText("Selected file: " + this.map.getName());
            }
        } else if (e.getSource() == startButton) {
            this.start();
            MazePresenter presenter = new MazePresenter(maze);
            presenter.open();
        } else if (e.getSource() == readLog) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("data/logs"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                this.log = fileChooser.getSelectedFile();
                textArea.setText("Selected file: " + this.log.getName());
            }
            try {
                this.logFile = new FileReader(this.log);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            logReader = new BufferedReader(this.logFile);

            startLog();
            closeLogReader();
        }
    }

    /**
     * Clear maze logs.
     */
    public void clearMazeLogs() {
        mazeLogs.clear();
    }

    /**
     * Gets selected file.
     *
     * @return the selected file
     */
    public File getSelectedFile() {
        return map;
    }
}