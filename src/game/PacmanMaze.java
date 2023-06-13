package ija.ija2022.homework2.common.game;

import ija.ija2022.homework2.common.lib.common.CommonField;
import ija.ija2022.homework2.common.lib.common.CommonMaze;
import ija.ija2022.homework2.common.lib.common.CommonMazeObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Pacman maze.
 */
public class PacmanMaze implements CommonMaze {
    private int rows, cols;
    /**
     * The Board.
     */
    public CommonField[][] board;
    private List<CommonMazeObject> ghostsList;
    private PacmanObject pacman;
    private int keyCounter;
    private String input;

    /**
     * Instantiates a new Pacman maze.
     *
     * @param rows       the rows
     * @param cols       the cols
     * @param ghostsList the ghosts list
     * @param pacman     the pacman
     * @param keyCounter the key counter
     */
    public PacmanMaze(int rows, int cols, List<CommonMazeObject> ghostsList, PacmanObject pacman, int keyCounter) {
        this.ghostsList = ghostsList;
        this.rows = rows;
        this.cols = cols;
        this.pacman = pacman;
        this.keyCounter = keyCounter;
    }

    @Override
    public CommonField getField(int row, int col) {
        return board[row][col];
    }

    @Override
    public int numRows() {
        return rows;
    }

    @Override
    public int numCols() {
        return cols;
    }

    @Override
    public List<CommonMazeObject> ghosts() {
        List<CommonMazeObject> ghostsListCopy = new ArrayList<>(ghostsList);
        return ghostsListCopy;
    }

    public PacmanObject getPacman() {
        return pacman;
    }

    @Override
    public boolean noKey() {
        return (keyCounter == 0);
    }

    @Override
    public void decCounter() {
        keyCounter--;
    }

    @Override
    public String toText() {
        String output = new String();
        int actualRows = this.numRows()-2;
        int actualCols = this.numCols()-2;
        output += actualRows + " " + actualCols + '\n';
        for (int i = 1; i < this.rows-1; i++) {
            for (int k = 1; k < this.cols-1; k++) {
                CommonField thisField = this.getField(i, k);
                if (thisField.isEmpty()) {
                    if (thisField.isKey()) {
                        output += "K";
                    } else if (thisField.isTarget()) {
                        output += "T";
                    } else if (thisField.isWall()){
                        output += "X";
                    } else {
                        output += ".";
                    }
                } else {
                    if (thisField.get() != null) {
                        if (thisField.get().isPacman()) {
                            output += "S";
                        } else {
                            output += "G";
                        }
                    }
                }
            }
            output += "\n";
        }
        return output;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String getInput() {
        return this.input;
    }

    @Override
    public int getKeyCounter() {
        return this.keyCounter;
    }
}
