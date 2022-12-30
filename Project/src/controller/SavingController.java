/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PlayerScore;

/**
 *
 * @author omari
 */
public class SavingController {

    private ArrayList<PlayerScore> scores = new ArrayList<>();

    public SavingController() {
        this.loadFromFile();
    }

    public void addScore(PlayerScore score) {
        scores.add(score);
    }

    public void saveToFile() {
        try {
            int i;
            FileWriter fw = new FileWriter("HighScores.txt");
            for (i = 0; i < scores.size() - 1; i++) {
                fw.write(scores.get(i).lineRepresentation());
                fw.write("\n");
            }
            if (scores.size() == 0) {
                fw.close();
            } else {
                fw.write(scores.get(i).lineRepresentation());
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Error Writing in File");

        }
    }

    public void loadFromFile() {
        try {
            File file = new File("./HighScores.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                PlayerScore score = new PlayerScore(data[0], Integer.parseInt(data[1]));
                scores.add(score);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public ArrayList<Vector> getAllScores() {
        ArrayList<Vector> tableData = new ArrayList<Vector>();
        System.out.println(scores.size());
        for (int i = 0; i < scores.size(); i++) {
            System.out.println(scores.get(i).lineRepresentation());
            Vector columnData = new Vector();
            columnData.add(scores.get(i).getName());
            columnData.add(scores.get(i).getScore());
            tableData.add(columnData);
        }
        return tableData;
    }

}

