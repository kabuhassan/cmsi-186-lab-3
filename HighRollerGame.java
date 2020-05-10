public class HighRollerGame {

    public static void main(String[] args) {
        var console = System.console();
        DiceSet diceSet = null;
        var highest = 0;
        System.out.println("Welcome " + Die.SIX_SIDED_DIE_EMOJI.repeat(5));
        while (true) {
            System.out.println();
            try {
                var command = console.readLine("Enter a command (h for help): ").trim();
                if (command.matches("h(elp)?")) {
                    showHelp();
                } else if (command.matches("q(uit)?")) {
                    System.out.println("I'm glad you played today. You look great!");
                    break;
                } else if (command.matches("use\\s+\\d\\d?\\s+\\d\\d?")) {
                    var tokens = command.split("\\s+");
                    var sides = Integer.parseInt(tokens[1].trim());
                    var number = Integer.parseInt(tokens[2].trim());
                    System.out.println("number : " + number);
                    System.out.println("sides : " + sides);
                    diceSet = new DiceSet(sides, number);
                    highest = Math.max(highest, diceSet.sum());
                    System.out.println("You are now using a " + diceSet.descriptor());
                    System.out.println(diceSet);
                } else if (command.matches("roll\\s+all")) {
                    if (diceSet == null) {
                        throw new IllegalStateException("You don't have any dice yet");
                    }
                    System.out.println("roll all");
                    diceSet.rollAll();
                    highest = Math.max(highest, diceSet.sum());
                    // if necessary, and then print out the dice set
                } else if (command.matches("roll\\s+\\d+")) {
                    if (diceSet == null) {
                        throw new IllegalStateException("You don't have any dice yet");
                    }
                    System.out.println("rollIndividual("+command.substring(4).trim()+")");
                    diceSet.rollIndividual(Integer.parseInt(command.substring(4).trim()));
                    highest = Math.max(highest, diceSet.sum());
                    System.out.println(diceSet);
                } else if (command.matches("high(est)?")) {
                    if (highest == 0) {
                        System.out.println("there is no highest score yet");
                    } else {
                        System.out.println(highest);
                    }
                } else {
                    System.out.println("I don't understand");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void showHelp() {
        System.out.println("h or help\n" +
                "Print a help message, showing all the commands and what they do\n" +
                "q or quit\n" +
                "Quits the program, but prints a nice message before just before quitting, saying something nice to the user, like they have nice hair, or that you were glad they played the game today.\n" +
                "use <s> <n>\n" +
                "Obtain a new set of dice. Here <n> is the number of dice, which must be between 2 and 99; and <s> is the number of sides for each die in the set, which must be between 4 and 99. Prints the descriptor of dice set just obtained and the dice set too.\n" +
                "roll all\n" +
                "Rolls all the dice, then prints the dice set.\n" +
                "roll <i>\n" +
                "Rolls the ith die in the set, then prints the dice set.\n" +
                "high or highest\n" +
                "Prints the highest roll so far");
    }
}
