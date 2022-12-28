package Main;

import controller.DiffcultyController;
import eg.edu.alexu.csd.oop.game.GameEngine;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
public class Main {

    public static void main(String[] args) {
        DiffcultyController diffculty=new DiffcultyController();

        JMenuBar menuBar = new JMenuBar();;

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        //Addin Exit as a menu item and setting it shortcut
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        //Adding new as a menu item and setting it shortcut
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        //Addin Pause as a menu item and setting it shortcut
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        pauseMenuItem.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        //Addin Resume as a menu item and setting it shortcut
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        resumeMenuItem.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        //Adding File menu items
        fileMenu.add(exitMenuItem);
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(pauseMenuItem);
        fileMenu.add(resumeMenuItem);
        menuBar.add(fileMenu);
        final GameEngine.GameController gameController = GameEngine.start("Test Game", diffculty.newGame("Hard", 900, 600), menuBar);
        //Setting each menu Item its function
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.changeWorld(diffculty.newGame("Hard", 900, 600));
            }
        });
        pauseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.pause();
            }
        });
        resumeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.resume();
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}
