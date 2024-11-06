package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GT4500Test {

    private GT4500 ship;

    @BeforeEach
    public void init() {
        this.ship = new GT4500();
    }

    @Test
    void fireTorpedo_Single_Success() {
        // Arrange

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fireTorpedo_All_Success() {
        // Arrange

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        assertEquals(true, result);
    }
    @Test
    void fireTorpedo_Single_PrimaryEmpty_SecondaryFires() {
        // Arrange
        ship.fireTorpedo(FiringMode.SINGLE); // Primary fires
        ship.fireTorpedo(FiringMode.SINGLE); // Secondary fires
        // Fire primary until it's empty
        for (int i = 0; i < 9; i++) {
            ship.fireTorpedo(FiringMode.SINGLE);
        }

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result, "Secondary should fire when primary is empty.");
    }

    @Test
    void fireTorpedo_Single_BothStoresEmpty_Fail() {
        // Arrange
        for (int i = 0; i < 10; i++) {
            ship.fireTorpedo(FiringMode.SINGLE); // Empty both stores
        }

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result, "Firing should fail if both torpedo stores are empty.");
    }

    @Test
    void fireTorpedo_All_BothStoresEmpty_Fail() {
        // Arrange
        for (int i = 0; i < 10; i++) {
            ship.fireTorpedo(FiringMode.SINGLE); // Empty both stores
        }

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        assertEquals(true, result, "Firing should fail in ALL mode if both stores are empty.");
    }

    @Test
    void fireTorpedo_All_OneStoreEmpty_Success() {
        // Arrange
        for (int i = 0; i < 10; i++) {
            ship.fireTorpedo(FiringMode.SINGLE); // Empty primary store
        }

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        assertEquals(true, result, "Firing should succeed in ALL mode if only one store is empty.");
    }
}
