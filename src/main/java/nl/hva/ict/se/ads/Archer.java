package nl.hva.ict.se.ads;

import java.util.*;

/**
 * Holds the name, archer-id and the points scored for 30 arrows.
 *
 * Archers MUST be created by using one of the generator methods. That is way the constructor is private and should stay
 * private. You are also not allowed to add any constructor with an access modifier other then private unless it is for
 * testing purposes in which case the reason why you need that constructor must be contained in a very clear manner
 * in your report.
 */
public class Archer implements Comparable<Archer>
{
    public static final int MAX_SCORE = 10;
    public static final int MAX_ARROWS = 3;
    public static final int MAX_ROUNDS = 10;
    public static final int BEGIN_ID = 135788;
    public static final int MISS_PENALTY = 7;
    private static Random randomizer = new Random();
    private final int id; // Once assigned a value is not allowed to change.
    private String firstName;
    private String lastName;
    private int[][] scores;
    private int misses;

    /**
     * Constructs a new instance of bowman and assigns a unique ID to the instance. The ID is not allowed to ever
     * change during the lifetime of the instance! For this you need to use the correct Java keyword.Each new instance
     * is a assigned a number that is 1 higher than the last one assigned. The first instance created should have
     * ID 135788;
     *
     * @param firstName the archers first name.
     * @param lastName  the archers surname.
     * @param id        the archers id.
     */
    private Archer(
        String firstName,
        String lastName,
        int id
    ) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.id        = id;
        this.scores = new int[MAX_ROUNDS][MAX_ARROWS];
    }


    /**
     * Registers the point for each of the three arrows that have been shot during a round. The <code>points</code>
     * parameter should hold the three points, one per arrow.
     *
     * @param round the round for which to register the points.
     * @param points the points shot during the round.
     */
    public void registerScoreForRound(
        int round,
        int[] points
    ) {
        this.scores[round] = points;
    }

    /**
     * Get the archer's total score over all rounds.
     *
     * @return int
     */
    public int getMaxScore()
    {
        return MAX_SCORE * MAX_ARROWS * MAX_ROUNDS;
    }

    /**
     * Get total score of Archer.
     *
     * @return int
     */
    public int getTotalScore()
    {
        int total = 0;

        for (int[] round : this.scores) {
            for (int score : round) {
                total += score;
            }
        }
        return total;
    }

    public int getWeightedScore()
    {
        int misses = 0;
        int total  = 0;

        for (int[] round : this.scores) {
            for (int score : round) {
                if (score == 0) {
                    misses += 1;
                } else {
                    total += (score + 1);
                }
            }
        }

        return total - (misses * MISS_PENALTY);
    }

    /**
     * This methods creates a List of archers.
     *
     * @param nrOfArchers the number of archers in the list.
     *
     * @return List<Archer>
     */
    public static List<Archer> generateArchers( int nrOfArchers )
    {
        List<Archer> archers = new ArrayList<>(nrOfArchers);

        for (int i = 0; i < nrOfArchers; i++) {
            Archer archer = new Archer(
                Names.nextFirstName(),
                Names.nextSurname(),
                (BEGIN_ID + i)
            );

            letArcherShoot(archer, nrOfArchers % 100 == 0);
            archers.add(archer);
        }

        return archers;

    }

    /**
     * This methods creates a Iterator that can be used to generate all the required archers. If you implement this
     * method it is only allowed to create an instance of Archer inside the next() method!
     *
     * <b>THIS METHODS IS OPTIONAL</b>
     *
     * @param nrOfArchers the number of archers the Iterator will create.
     * @return
     */
    public static Iterator<Archer> generateArchers( long nrOfArchers )
    {
        return null;
    }

    /**
     * Convert Archer object to String.
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return
            String
                .format(
                    "%d (%d/%d) weighted: (%d, %d) %s %s",
                    this.getId(),
                    this.getTotalScore(),
                    this.getMaxScore(),
                    this.getWeightedScore(),
                    this.getMisses(),
                    this.getFirstName(),
                    this.getLastName()
                )
            ;
    }

    /**
     * Get last name.
     *
     * @return String
     */
    public String getLastName()
    {
        return this.lastName;
    }

    /**
     * Get first name.
     *
     * @return String
     */
    public String getFirstName()
    {
        return this.firstName;
    }

    /**
     * Get the archer's id.
     *
     * @return int
     */
    public int getId()
    {
        return id;
    }

    private static void letArcherShoot(
        Archer archer,
        boolean isBeginner
    ) {
        for (int round = 0; round < MAX_ROUNDS; round++) {
            archer.registerScoreForRound(round, shootArrows(isBeginner ? 0 : 1));
        }
    }

    /**
     * Shoot all archer's arrows at target.
     *
     * @param min int | Minimum value all shots should have.
     *
     * @return int[]
     */
    private static int[] shootArrows( int min )
    {
        int[] points = new int[MAX_ARROWS];

        for (int arrow = 0; arrow < MAX_ARROWS; arrow++) {
            points[arrow] = shoot(min);
        }

        return points;
    }

    /**
     * Generate a random integer between 0-10, considering a minimimum value.
     *
     * @param min int | Minimum value the random integer should have.
     *
     * @return int
     */
    private static int shoot( int min )
    {
        return Math.max(min, randomizer.nextInt(MAX_SCORE + 1));
    }

    @Override
    public int compareTo( Archer archer )
    {
        if (archer.getTotalScore() != this.getTotalScore()) {
            return archer.getTotalScore() - this.getTotalScore();
        }

        if (archer.getWeightedScore() != this.getWeightedScore()) {
            return archer.getWeightedScore() - this.getWeightedScore();
        }

        return archer.getId() - this.getId();
    }

    public int getMisses() {
        return this.misses;
    }
}
