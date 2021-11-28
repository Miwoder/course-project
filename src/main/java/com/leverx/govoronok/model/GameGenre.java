package com.leverx.govoronok.model;

public enum GameGenre {
    PLATFORM("PLATFORM"),
    SHOOTER("SHOOTER"),
    FIGHTING("FIGHTING"),
    STEALTH("STEALTH"),
    SURVIVAL("SURVIVAL"),
    RHYTHM("RHYTHM"),
    BATTLEROYALE("BATTLEROYALE"),
    ADVENTURE("ADVENTURE"),
    RPG("RPG"),
    MMORPG("MMORPG"),
    SIMULATOR("SIMULATOR"),
    STRATEGY("STRATEGY"),
    MOBA("MOBA"),
    RACING("RACING"),
    SPORT("SPORT"),
    BOARDGAME("BOARDGAME"),
    CASINO("CASINO"),
    CASUAL("CASUAL"),
    HORROR("HORROR"),
    LOGIC("LOGIC"),
    SANDBOX("SANDBOX");

    private final String gameGenre;

    private GameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public String getGameGenre() {
        return gameGenre;
    }

}
