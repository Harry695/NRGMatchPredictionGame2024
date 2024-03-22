class GameAdmin {
    private MatchPrediction currentMatch;
    private String password = "admin123";

    public void setCurrentMatch(MatchPrediction currentMatch) {
        this.currentMatch = currentMatch;
    }

    public MatchPrediction getCurrentMatch() {
        return currentMatch;
    }

    public boolean checkPassword(String input) {
        return input.equals(password);
    }
}