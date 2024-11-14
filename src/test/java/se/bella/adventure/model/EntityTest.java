package se.bella.adventure.model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EntityTest {
    Resident resident;
    Burglar burglar;

    @BeforeEach
    void setUp() {
        resident = new Resident("Resident",10,3);
        burglar = new Burglar("Burglar",10,4);
    }

    @Test
    public void testPunch() {
        resident = new Resident("Resident",10,3);
        burglar = new Burglar("Burglar",10,4);

      resident.punch(burglar);
      assertEquals(7,burglar.getHealth());
    }

    @Test
    public void testTakeHit() {
        resident = new Resident("Resident",10,3);
        burglar = new Burglar("Burglar",10,4);

        resident.takeHit(4);
        assertEquals(6,resident.getHealth());

    }

    @Test
    public void testIsConsciousTrue() {
        resident = new Resident("Resident",10,3);
        burglar = new Burglar("Burglar",10,4);

        resident.punch(burglar);
        boolean result = burglar.isConscious();
        assertTrue(result);
    }

    @Test
    public void testIsConsciousFalse() {
        resident = new Resident("Resident",10,3);
        burglar = new Burglar("Burglar",10,4);

        burglar.takeHit(10);
        boolean result = burglar.isConscious();
        assertFalse(result);

    }

}