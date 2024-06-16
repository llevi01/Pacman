package pacman.game;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import pacman.game.util.Config;
import pacman.game.util.Error;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Pontszámok tárolásáért felelős osztály
 */
public class Leaderboard {
    /**
     * Ebben a listában tároljuk a játékosokat
     */
    private static final ArrayList<Player> leaderboard = new ArrayList<>();

    /**
     * Igaz, ha az első szerverhez való csatlakozás hibával zárult.
     */
    private static boolean connectionError = false;

    private static final String FILE_PATH = "leaderboard.json";

    private static void error(Error e) {
        Game.frame.displayErrorMessage(e);
    }

    /**
     * Inicializálja a leaderboardot a játék elején
     */
    public static void init() {
        loadFromFile();
        syncWithServer();
        saveToFile();
    }

    /**
     * Hozzáad egy pontszámot a leaderboard-hoz
     * @param name Játékos neve
     * @param score Játékos pontszáma
     */
    public static void addScore(String name, int score) {
        name = name.substring(0, Math.min(name.length(), 8));
        name = name.toUpperCase();
        String inputName = name;
        Optional<Player> match = leaderboard.stream().filter(p -> p.name.equals(inputName)).findFirst();

        if (match.isPresent()) {
            Player player = match.get();
            if (player.score < score) {
                player.score = score;
            }
        }
        else {
            leaderboard.add(new Player(name, score));
        }

        syncWithServer();
        saveToFile();
    }

    /**
     * Betölti a leaderboard-ot
     */
    public static void loadFromFile() {
        File file = new File(FILE_PATH);
        if (file.length() == 0) {
            return;
        }

        try {
            String readString = Files.readString(Path.of(FILE_PATH));
            Gson gson = new Gson();
            TypeToken<Collection<Player>> collectionType = new TypeToken<>(){};

            Collection<Player> readLeaderboard = gson.fromJson(readString, collectionType);
            leaderboard.addAll(readLeaderboard);

        } catch (IOException e) {
            error(Error.LOADING_LEADERBOARD);
        }
    }

    /**
     * Elmenti a leaderboard-ot
     */
    private static void saveToFile() {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(leaderboard, fw);

        } catch (IOException | JsonIOException e) {
            error(Error.SAVING_LEADERBOARD);
        }
    }

    /**
     * Elküldi a szervernek a fájlból betöltött leaderboardot.
     * Ha érkezik válasz, azt a leaderboardot használjuk tovább, ha nem, akkor nem fordulunk többet a szerverhez.
     */
    private static void syncWithServer() {
        if (connectionError) {
            return;
        }

        try {
            Gson gson = new Gson();
            TypeToken<Collection<Player>> collectionType = new TypeToken<>(){};
            String leaderboardJson = gson.toJson(leaderboard);
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest
                    .newBuilder(new URI("http://" + Config.SERVER_ADDRESS + ":3000/leaderboard"))
                    .timeout(Duration.of(2, ChronoUnit.SECONDS))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(leaderboardJson))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String readString = response.body();
            Collection<Player> readLeaderboard = gson.fromJson(readString, collectionType);

            leaderboard.clear();
            leaderboard.addAll(readLeaderboard);

        } catch (Exception e) {
            connectionError = true;
            error(Error.CONNECTING_TO_SERVER);
        }
    }

    /**
     * Visszaadja a legmagasabb pontszámot
     * @return Legmagasabb pontszám a leaderboardon, ha üres akkor 0
     */
    public static int getHighScore() {
        if (leaderboard.isEmpty()) {
            return 0;
        }
        return leaderboard.stream().max(Comparator.comparing(p -> p.score)).get().score;
    }

    /**
     * Visszaadja az öt legmagasabb pontszámot elért játékost
     */
    public static Collection<Player> getTopPlayers() {
        leaderboard.sort(Comparator.comparing(p -> p.score));
        Collections.reverse(leaderboard);

        return leaderboard.stream().limit(8).collect(Collectors.toList());
    }
}
