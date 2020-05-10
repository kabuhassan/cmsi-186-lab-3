import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A dice set holds a collection of Die objects. All of the die objects have
 * the same number of sides.
 */
public class DiceSet {

    private List<Die> dice;
    private int sidesOnEachDie;

    /**
     * Creates a DiceSet containing the given number of dice, each with the
     * given number of sides. All die values start off as 1. Throws an
     * IllegalArgumentException if either less than two dice were provided
     * or if it is asked to make dice with less than 4 sides.
     */
    public DiceSet(int sidesOnEachDie, int numberOfDice) {
        if (numberOfDice < 2) throw new IllegalArgumentException("At least two dice required");
        else if (sidesOnEachDie < 4) throw new IllegalArgumentException("Dice must have at least four sides");
        this.sidesOnEachDie = sidesOnEachDie;
        dice = IntStream.range(0, numberOfDice)
                .mapToObj(o -> new Die(sidesOnEachDie,1))
                .collect(Collectors.toList());
    }

    /**
     * Creates a DiceSet where each die has the given number of sides, with the
     * given values.
     */
    public DiceSet(int sidesOnEachDie, int... values) {
        if (values.length < 2) throw new IllegalArgumentException("At least two dice required");
        else if (sidesOnEachDie < 4) throw new IllegalArgumentException("Dice must have at least four sides");
        this.sidesOnEachDie = sidesOnEachDie;

        dice = IntStream.range(0, values.length)
                .mapToObj(i -> new Die(sidesOnEachDie,values[i]))
                .collect(Collectors.toList());
    }

    /**
     * Returns the descriptor of the dice set; for example "5d20" for a set with
     * five dice of 20 sides each; or "2d6" for a set of two six-sided dice.
     */
    public String descriptor() {
        return dice.size() + "d" + this.sidesOnEachDie;
    }

    /**
     * Returns the sum of the values of each die in the set.
     */
    public int sum() {
        return dice.stream().map(Die::getValue).mapToInt(Integer::intValue).sum();
    }

    /**
     * Rolls all the dice in the set.
     */
    public void rollAll() {
        for (Die d : dice)d.roll();
    }

    /**
     * Rolls the ith die, updating its value.
     */
    public void rollIndividual(int i) {
        dice.get(i).roll();
    }

    /**
     * Returns the value of the ith die.
     */
    public int getIndividual(int i) {
        return dice.get(i).getValue();
    }

    /**
     * Returns the values of each of the dice in a list.
     */
    public List<Integer> values() {
        return dice.stream().map(Die::getValue).collect(Collectors.toList());
    }

    /**
     * Returns whether this dice set has the same distribution of values as
     * an other dice set. The two dice sets must have the same number of dice
     * and the same number of sides per dice, and there must be the same
     * number of each value in each set.
     */
    public boolean isIdenticalTo(DiceSet diceSet) {
        if (diceSet.values().size() != dice.size()) return false;
        List<Integer> values = new ArrayList(dice.stream().map(Die::getValue).collect(Collectors.toList()));
        List<Integer> diceSetValues = new ArrayList(diceSet.values());
        Collections.sort(values);
        Collections.sort(diceSetValues);
        return values.equals(diceSetValues);
    }

    /**
     * Returns a string representation in which each of the die strings are
     * joined without a separator, for example: "[2][5][2][3]".
     */
    @Override
    public String toString() {
        return dice.stream().map(Die::toString).reduce("",String::concat);
    }
}
