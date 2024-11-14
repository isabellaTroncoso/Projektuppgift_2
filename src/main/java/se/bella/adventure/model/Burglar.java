package se.bella.adventure.model;

public class Burglar extends Entity{
    public Burglar() {
        super("Thief", 12, 4);
    }

    public Burglar(String role, int health, int damage) {
        super(role, health, damage);
    }
}
