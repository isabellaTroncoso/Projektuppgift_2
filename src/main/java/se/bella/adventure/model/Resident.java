package se.bella.adventure.model;

public class Resident extends Entity {
    public Resident() {
        super("Resident", 12, 3);
    }

    public Resident(String role, int health, int damage) {
        super(role, health, damage);
    }
}
