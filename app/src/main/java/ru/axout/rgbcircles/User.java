package ru.axout.rgbcircles;

public class User {
    private int health;
    private int score;

    public User() {
        health = 3;
        score = 0;
    }

    public void injury() {
        this.health -= 1;
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
