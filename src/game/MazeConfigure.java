package ija.ija2022.homework2.common.game;

import ija.ija2022.homework2.common.common.Field;
import ija.ija2022.homework2.common.lib.common.CommonField;
import ija.ija2022.homework2.common.lib.common.CommonMaze;
import ija.ija2022.homework2.common.lib.common.CommonMazeObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Maze configure.
 */
public class MazeConfigure {
    private int rows, cols, curRow=1;
    private CommonField[][] board;
    private List<CommonMazeObject> ghostsList;
    private PacmanObject pacman;
    private boolean started=false, error=false;
    private int keyCounter = 0;

    /**
     * Instantiates a new Maze configure.
     */
    public MazeConfigure() {}

    /**
     * Start reading.
     *
     * @param rows the rows
     * @param cols the cols
     */
    public void startReading(int rows, int cols) {
        this.rows = rows+2;
        this.cols = cols+2;
        this.board = new CommonField[this.rows][this.cols];
        this.ghostsList = new ArrayList<CommonMazeObject>();
        for (int i = 0; i < this.cols; i++) {
            board[0][i] = new WallField(0, i);
            board[this.rows-1][i] = new WallField(this.rows-1, i);
        }
        for (int i = 1; i < this.rows-1; i++) {
            board[i][0] = new WallField(i, 0);
            board[i][this.cols-1] = new WallField(i, this.cols-1);
        }
        this.started = true;
    }

    /**
     * Process line boolean.
     *
     * @param line the line
     * @return the boolean
     */
    public boolean processLine(String line) {
        if (started && !error && curRow <= rows-1 && line.length() == cols-2) {
            for (int i = 1; i < line.length()+1; i++) {
                switch (line.charAt(i-1)) {
                    case '.':
                        board[this.curRow][i] = new PathField(this.curRow, i);
                        break;

                    case 'S':
                        board[this.curRow][i] = new PathField(this.curRow, i);
                        PacmanObject pacman = new PacmanObject(board[this.curRow][i]);
                        this.pacman = pacman;
                        ((Field) board[this.curRow][i]).put(pacman);
                        break;

                    case 'X':
                        board[this.curRow][i] = new WallField(this.curRow, i);
                        break;

                    case 'G':
                        board[this.curRow][i] = new PathField(this.curRow, i);
                        GhostObject ghost = new GhostObject(board[this.curRow][i]);
                        ((Field) board[this.curRow][i]).put(ghost);
                        this.ghostsList.add(ghost);
                        break;

                    case 'K':
                        board[this.curRow][i] = new PathField(this.curRow, i);
                        ((Field)board[this.curRow][i]).setKey(true);
                        keyCounter++;
                        break;

                    case 'T':
                        board[this.curRow][i] = new PathField(this.curRow, i);
                        ((Field)board[this.curRow][i]).setTarget(true);
                        break;

                    default:
                        return false;
                }
            }
            this.curRow++;
            return true;
        }
        error = true;
        return false;
    }

    /**
     * Stop reading boolean.
     *
     * @return the boolean
     */
    public boolean stopReading() {
        if (started && !error) {
            started = false;
            return true;
        }
        return false;
    }

    /**
     * Create maze common maze.
     *
     * @return the common maze
     */
    public CommonMaze createMaze() {
        if (error) {
            return null;
        } else {
            PacmanMaze maze = new PacmanMaze(this.rows, this.cols, this.ghostsList, this.pacman, this.keyCounter);
            maze.board = board;
            for (int i = 1; i < this.rows; i++) {
                for (int k = 1; k < this.cols-1; k++) {
                    ((Field) maze.board[i][k]).setMaze(maze);
                }
            }
            return maze;
        }
    }
}
