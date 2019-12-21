package nl.hva.ict.se.ads;

import org.junit.jupiter.api.*;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChampionSelectorTest
{
    protected static List<Archer> unsortedArchers;
    protected Comparator<Archer> comparator;

    @Test
    public void selInsSortAndCollectionSortResultInSameOrder() {
        List<Archer> unsortedArchersForSelIns = Archer.generateArchers(23);

        List<Archer> sortedArchersSelIns = ChampionSelector.insertionSort(unsortedArchersForSelIns, this.comparator);
        List<Archer> sortedArchersCollection = ChampionSelector.collectionSort(unsortedArchersForSelIns, this.comparator);

        assertEquals(sortedArchersCollection, sortedArchersSelIns);
    }

    @BeforeAll
    public static void generateArchers()
    {
        unsortedArchers = Archer.generateArchers(3276800);
    }

    @BeforeEach
    private void createComparator()
    {
        this.comparator = (archer1, archer2) -> archer1.compareTo(archer2);
    }

    public ChampionSelectorTest()
    {
        this.createComparator();
    }

    /**
     * Test sorting efficiency of standard Collections.sort sort.
     *
     * @param repetitionInfo repetitionInfo
     */
    @RepeatedTest(17)
    public void collectionSort( RepetitionInfo repetitionInfo )
    {
        if (repetitionInfo.getCurrentRepetition() != 1) {
            int nrOfArchers = 100;

            for (int i = 1; i < (repetitionInfo.getCurrentRepetition() - 1); i++) {
                nrOfArchers *= 2;
            }

            ChampionSelector.collectionSort(unsortedArchers.subList(0, nrOfArchers), this.comparator);
        }
    }

    /**
     * Test sorting efficiency of insertion sort.
     *
     * @param repetitionInfo repetitionInfo
     */
    @RepeatedTest(11)
    public void insertionSort( RepetitionInfo repetitionInfo )
    {
        if (repetitionInfo.getCurrentRepetition() != 1) {
            int nrOfArchers = 100;

            for (int i = 1; i < (repetitionInfo.getCurrentRepetition() - 1); i++) {
                nrOfArchers *= 2;
            }

            ChampionSelector.insertionSort(unsortedArchers.subList(0, nrOfArchers), this.comparator);
        }
    }

    /**
     * Test sorting efficiency of quick sort.
     *
     * @param repetitionInfo repetitionInfo
     */
    @RepeatedTest(17)
    public void quickSort( RepetitionInfo repetitionInfo )
    {
        if (repetitionInfo.getCurrentRepetition() != 1) {
            int nrOfArchers = 100;

            for (int i = 1; i < (repetitionInfo.getCurrentRepetition() - 1); i++) {
                nrOfArchers *= 2;
            }

            ChampionSelector.quickSort(unsortedArchers.subList(0, nrOfArchers), this.comparator);
        }
    }
}