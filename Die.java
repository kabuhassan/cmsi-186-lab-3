import java.util.Random;

/**
 * A simple class for representing die objects. A die has a given number of
 * sides (at least) four, set when the die is constructed and which can never
 * be changed. The die's value can be changed, but only by calling its roll()
 * method.
 */
public class Die {

    private static Random random = new Random();
    public static final String SIX_SIDED_DIE_EMOJI = "🎲";
    private final int sides;
    private int value;

    /**
     * Constructs a die with the given number of sides and starting value.
     *
     * @throws IllegalArgumentException if the number of sides is less than 4 or
     *                                  if the starting value is not consistent with the number of sides.
     */
    public Die(int sides, int value) {
        if (sides < 4) throw new IllegalArgumentException("At least four sides required");
        else if (value > sides) throw new IllegalArgumentException("Die value not legal for die shape");
        else if (value < 1) throw new IllegalArgumentException("Die value not legal for die shape");
        this.sides = sides;
        this.value = value;
    }

    /**
     * Simulates a roll by randomly updating the value of this die. In addition to
     * mutating the die's value, this method also returns the new updated value.
     */
    public int roll() {
        this.value = 1 + random.nextInt(this.sides);
        return this.value;
    }

    /**
     * Returns the number of sides of this die.
     */
    public int getSides() {
        return this.sides;
    }

    /**
     * Returns the value of this die.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns a description of this die, which is its value enclosed in square
     * brackets, without spaces, for example "[5]".
     */
    @Override
    public String toString() {
        return "[" + this.value + "]";
    }
}
