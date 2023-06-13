package ija.ija2022.homework2.common;

import ija.ija2022.homework2.common.game.ReadFileUI;
import ija.ija2022.homework2.common.lib.MazePresenter;
import ija.ija2022.homework2.common.lib.common.CommonMaze;

import java.io.File;

/**
 * This class represents a Pacman game.
 */
public class main {
    private static CommonMaze maze;
    private File map;
    private ReadFileUI ui;
    private int numCols, numRows;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        main prog = new main();
        ReadFileUI ui = new ReadFileUI();
//        Thread.sleep(10000);
        while (true) {
            System.out.print("");
            if (!ui.getMazeLogs().isEmpty()) {
                MazePresenter presenter = new MazePresenter((CommonMaze) ui.getMazeLogs().get(0));
                presenter.initializeLogInterface();
                for (int i = 1; i < ui.getMazeLogs().size(); i++) {
                    Thread.sleep(1000);
                    presenter.refreshLogInterface();
                    presenter.setMaze((CommonMaze) ui.getMazeLogs().get(i));
                }
                Thread.sleep(1000);
                presenter.openLog();
                presenter.refreshLogInterface();
                ui.clearMazeLogs();
            }
        }
    }
}