public class User {
    private String username;
    private int points;

    public User(String username) {
        this.username = username;
        this.points = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}