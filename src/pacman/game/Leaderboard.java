package pacman.game;

import pacman.game.util.Error;

import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * Pontszámok tárolásáért felelős osztály
 */
public class Leaderboard {
    /**
     * HashMap, amiben a játékosok neveit és elért pontszámait tároljuk
     */
    private static Map<String, Integer> scores = new HashMap<>();

    /**
     * Betölti a leaderboard-ot
     */
    public static void load() {
        File file = new File("leaderboard.dat");
        if (file.length() == 0) {
            return;
        }

        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(file))) {


            Object o = input.readObject();
            if (!(o instanceof HashMap<?,?>)) {
                throw new ClassCastException();
            }
            scores = (HashMap<String, Integer>) o;

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(Game.frame, Error.LOADING_LEADERBOARD.message,
                    "Pacman", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elmenti a leaderboard-ot
     */
    public static void save() {
        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream("leaderboard.dat"))) {

            output.writeObject(scores);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(Game.frame, Error.SAVING_LEADERBOARD.message,
                    "Pacman", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Visszaadja a legmagasabb pontszámot
     * @return Legmagasabb pontszám a leaderboardon, ha üres akkor 0
     */
    public static int getHighScore() {
        Collection<Integer> values = scores.values();
        if (values.isEmpty()) {
            return 0;
        }
        return Collections.max(values);
    }

    /**
     * Visszaadja az öt legmagasabb pontszámot elért játékos nevét pontszámát
     */
    public static ArrayList<Map.Entry<String, Integer>> getTopFive() {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(scores.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        Collections.reverse(entries);

        ArrayList<Map.Entry<String, Integer>> res = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (entries.size() <= i) {
                break;
            }

            res.add(entries.get(i));
        }

        return res;
    }

    /**
     * Hozzáad egy pontszámot a leaderboard-hoz
     * @param name Játékos neve
     * @param score Játékos pontszáma
     */
    public static void addScore(String name, int score) {
        name = name.substring(0, Math.min(name.length(), 8));
        name = name.toUpperCase();
        if (!scores.containsKey(name) || scores.get(name) < score) {
            scores.put(name, score);
        }
        save();
    }
}
