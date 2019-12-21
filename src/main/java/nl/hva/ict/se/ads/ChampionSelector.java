package nl.hva.ict.se.ads;

import java.util.*;

/**
 * Given a list of Archer's this class can be used to sort the list using one of three sorting algorithms.
 */
public class ChampionSelector
{
    /**
     * This method uses insertion sort to sort a lost of archers.
     *
     * source: Algorithms Fourth edition by Rober Sedgewick and Kevin Wayne page no. 245
     */
    public static List<Archer> insertionSort(
        List<Archer> archers,
        Comparator<Archer> scoringScheme
    ) {
        int n = archers.size();

        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && scoringScheme.compare(archers.get(j), archers.get(j - 1)) < 0; j--) {
                Collections.swap(archers, j, j - 1);
            }
        }

        return archers;
    }

    /**
     * This method uses quick sort for sorting the archers.
     */
    public static List<Archer> quickSort(
        List<Archer> archers,
        Comparator<Archer> scoringScheme
    ) {
        quickSortLoop(archers, scoringScheme, 0, archers.size() - 1);

        return archers;
    }

    /**
     * Quick sort loop called recursively to split list into small sublists
     * Inspiration: https://www.geeksforgeeks.org/quick-sort/
     *
     * @param archers       List<Archer>
     * @param scoringScheme Comparator<Archer>
     * @param low           int
     * @param high          int
     */
    private static void quickSortLoop(
        List<Archer> archers,
        Comparator<Archer> scoringScheme,
        int low,
        int high
    ) {
        if (low < high) {
            int partition = split(archers, scoringScheme, low, high);

            quickSortLoop(archers, scoringScheme, low, partition - 1);
            quickSortLoop(archers, scoringScheme, partition + 1, high);
        }
    }

    /**
     * Inspiration: https://www.geeksforgeeks.org/quick-sort/
     *
     * @param archers       List<Archer>
     * @param scoringScheme Comparator<Archer>
     * @param low           int
     * @param high          int
     */
    private static int split(
        List<Archer> archers,
        Comparator<Archer> scoringScheme,
        int low,
        int high
    ) {
        Archer archer = archers.get(high);
        int i = (low - 1);

        for (int j = low; j <= (high - 1); j++) {
            if (scoringScheme.compare(archers.get(j), archer) < 0) {
                i++;
                Collections.swap(archers, i, j);
            }
        }

        Collections.swap(archers, i + 1, high);

        return (i + 1);
    }

    /**
     * This method uses the Java collections sort algorithm for sorting the archers.
     */
    public static List<Archer> collectionSort(
        List<Archer> archers,
        Comparator<Archer> scoringScheme
    ) {
        Collections.sort(archers, scoringScheme);

        return archers;
    }

    /**
     * This method uses quick sort for sorting the archers in such a way that it is able to cope with an Iterator.
     *
     * <b>THIS METHOD IS OPTIONAL</b>
     */
    public static Iterator<Archer> quickSort(Iterator<Archer> archers, Comparator<Archer> scoringScheme) {
        return null;
    }
}
