package nl.hva.ict.se.ads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Place all your own tests for Archer in this class. Tests in any other class will be ignored!
 */
public class ExtendedArcherTest extends ArcherTest
{
    private List<Archer> archers;
    private int nrOfArchers = 50;

    @BeforeEach
    private void generateArchers()
    {
        this.archers = Archer.generateArchers(this.nrOfArchers);
    }

    /**
     * If a list of archers is generated
     *   The list must contain exactly the count of archers generated.
     */
    @Test
    public void assertArcherListIsCorrectSize()
    {
        assertEquals(
            this.archers.size(),
            this.nrOfArchers
        );
    }
}
