import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class MatchPredictionGame {
    private List<User> users;
    private List<MatchPrediction> matchResults;
    private GameAdmin gameAdmin;
    private Map<User, MatchPrediction> predictions;
    private int currentMatch;
    private Scanner scan = new Scanner(System.in);

    public MatchPredictionGame() {
        users = new ArrayList<>();
        predictions = new HashMap<>();
        gameAdmin = new GameAdmin();
        currentMatch = 1;
    }

    public void addUser(String username) {
        users.add(new User(username));
    }

    public void AddUserPrompt() {
        System.out.println("Creating new user. Please enter desired username: ");
        addUser(scan.nextLine());
    }

    public void makePrediction(User user, int matchNumber, Alliance winner, int redAlliancePoints,
            int blueAlliancePoints) {
        // if (!users.contains(user)) {
        // System.out.println("User not found!");
        // return;
        // }
        predictions.put(user, new MatchPrediction(matchNumber, winner, redAlliancePoints, blueAlliancePoints));
    }

    public void predictionPrompt() {
        System.out.println("Enter username: ");
        String username = scan.nextLine();
        Optional<User> user = findUser(username);
        for (int i = 0; i < 3; i++) {
            if (user.isPresent()) {
                break;
            }
            AddUserPrompt();
            user = findUser(username);
        }

        System.out.println("Please enter predicted winning alliance, R for red and B for blue: ");
        Optional<Alliance> winner = Alliance.stringToAlliance(scan.nextLine());
        while (winner.isEmpty()) {
            System.out.println("Invalid input. Please enter predicted winning alliance, R for red and B for blue: ");
            scan.next();
            winner = Alliance.stringToAlliance(scan.nextLine());
        }

        System.out.println("Enter predicted Red Alliance ranking point earned: ");
        int redRP = scanInt();
        System.out.println("Enter predicted Blue Alliance ranking point earned: ");
        int blueRP = scanInt();

        makePrediction(user.get(), currentMatch, winner.get(), redRP, blueRP);
    }

    public void enterResultPrompt() {
        System.out.println("Enter password: ");
        String password = scan.nextLine();
        for (int i = 0; i < 3; i++) {
            if (gameAdmin.checkPassword(password)) {
                break;
            } else {
                System.out.println("Wrong password. Please try again.");
                System.out.println("Enter password:");
                password = scan.nextLine();
            }
        }
        if (!gameAdmin.checkPassword(password)) {
            System.out.println("Fail to identify. Exiting.");
            return;
        }

        System.out.println("Please enter the winning alliance, R for red and B for blue: ");
        Optional<Alliance> winner = Alliance.stringToAlliance(scan.nextLine());
        while (winner.isEmpty()) {
            System.out.println("Invalid input. Please enter predicted winning alliance, R for red and B for blue: ");
            scan.next();
            winner = Alliance.stringToAlliance(scan.nextLine());
        }

        System.out.println("Enter Red Alliance ranking point earned: ");
        int redRP = scanInt();
        System.out.println("Enter Blue Alliance ranking point earned: ");
        int blueRP = scanInt();

        enterMatchResult(currentMatch, winner.get(), redRP, blueRP);
    }

    public Optional<User> findUser(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return Optional.of(user);
            }
        }
        System.out.println("User cannot be found!");
        return Optional.empty();
    }

    private int scanInt() {
        int number;
        while (true) {
            System.out.print("Enter an integer: ");
            if (scan.hasNextInt()) { // Check if the next token is an integer
                number = Integer.parseInt(scan.nextLine()); // Read the integer
                break; // Exit the loop if a valid integer is entered
            } else {
                System.out.println("Invalid input. Please enter an integer."); // Prompt user again if input is not an
                                                                               // integer
                scan.next(); // Consume the invalid input
            }
        }
        return number;
    }

    public void enterMatchResult(int matchNumber, Alliance winner, int redAlliancePoints, int blueAlliancePoints) {
        MatchPrediction match = new MatchPrediction(matchNumber, winner, redAlliancePoints, blueAlliancePoints);
        gameAdmin.setCurrentMatch(match);
        calculatePoints();
        displayLeaderboard();
    }

    private void calculatePoints() {
        for (Map.Entry<User, MatchPrediction> entry : predictions.entrySet()) {
            User user = entry.getKey();
            MatchPrediction prediction = entry.getValue();
            int userPoints = 0;
            if (prediction.getRedAlliancePoints() == gameAdmin.getCurrentMatch().getRedAlliancePoints() &&
                    prediction.getBlueAlliancePoints() == gameAdmin.getCurrentMatch().getBlueAlliancePoints()) {
                userPoints += 10; // Correct prediction, award 10 points
            }
            user.addPoints(userPoints);
        }
    }

    private void displayLeaderboard() {
        System.out.println("Leaderboard:");
        Collections.sort(users, Comparator.comparingInt(User::getPoints).reversed());
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println((i + 1) + ". " + user.getUsername() + " - " + user.getPoints() + " points");
        }
    }

    private void nextMatch() {
        currentMatch++;
    }

    public static void main(String[] args) {
        MatchPredictionGame game = new MatchPredictionGame();
        game.addUser("user1");
        game.addUser("user2");

        // Assume match result is entered by the admin
        while (true) {
            while (game.menuPrompt()) {
                continue;
            }

            game.nextMatch();
        }
    }

    private boolean menuPrompt() {
        System.out.println(
                    "Welcome! ------------------- \nEnter 0 to make a prediction.\nEnter 1 to enter match result. \nEnter 2 to create user.");
        int input = scanInt();
        if (input == 0) {
            predictionPrompt();
        } else if (input == 1) {
            enterResultPrompt();
        } else if (input == 2) {
            AddUserPrompt();
        }
        return (input != 1);
    }
}