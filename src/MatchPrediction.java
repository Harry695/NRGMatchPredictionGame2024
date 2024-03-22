class MatchPrediction {
    private int matchNumber;
    private int redAlliancePoints;
    private int blueAlliancePoints;
    private Alliance winnerAlliance;

    public MatchPrediction(int matchNumber, Alliance winnerAlliance, int redAlliancePoints, int blueAlliancePoints) {
        this.matchNumber = matchNumber;
        this.winnerAlliance = winnerAlliance;
        this.redAlliancePoints = redAlliancePoints;
        this.blueAlliancePoints = blueAlliancePoints;
    }

    public Alliance getWinningAlliance() {
        return winnerAlliance;
    }

    public int getRedAlliancePoints() {
        return redAlliancePoints;
    }

    public int getBlueAlliancePoints() {
        return blueAlliancePoints;
    }

    public int getMatchNumber() {
        return matchNumber;
    }
}
