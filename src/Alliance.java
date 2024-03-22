import java.util.Optional;

public enum Alliance {
    RED,
    BLUE;

    private static final String[] redString = {"red", "Red", "R", "r"};
    private static final String[] blueString = {"blue", "Blue", "B", "b"};

    public static Optional<Alliance> stringToAlliance(String in) {
        for (String s : redString) {
            if (s.equals(in)) {
                return Optional.of(Alliance.RED);
            }
        }
        for (String s : blueString) {
            if (s.equals(in)) {
                return Optional.of(Alliance.BLUE);
            }
        }

        return Optional.empty();
    }
}
