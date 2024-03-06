import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class mathGame {

    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    private static int currentNum;
    private static int numToGet; 

    private static String[] staticOptions = {"Add", "Substract", "Multiply", "Divide"};
    private static String[] options = {"Add", "Substract", "Multiply", "Divide",};
    private static ArrayList<Integer> modifiers = new ArrayList<>();
    
    static{
        modifiers.add(getRandomNumber(-10, 10));
        modifiers.add(getRandomNumber(-10, 10));
        modifiers.add(getRandomNumber(-10, 10));
    }

    private static int getRandomNumber(int range){
        return random.nextInt(range) + 1;
    }
    private static int getRandomNumber(int minRange, int maxRange){
        return random.nextInt(minRange, maxRange) + 1;
    }
    private static void add(int num){
        currentNum += num;
    }
    private static void substract(int num){
        currentNum -= num;
    }
    private static void multiply(int num){
        currentNum *= num;
    }
    private static void divide(int num){
        currentNum /= num;
    }
    private static void updateModifiers(){
        modifiers.remove(0);
        modifiers.add(getRandomNumber(10));
    }
    private static void updateOptions(int index){
        options[index - 1] = staticOptions[random.nextInt(4)];
    }

    private static int getInput(){
        int userChoice = scanner.nextInt();
        while (!(userChoice >= 0 && userChoice <= 4)){
            System.out.println("Choose a valid option: ");
            userChoice = scanner.nextInt();
        }
        return userChoice;
    }

    private static void handleInput(int choice){
        String choiceAsString = "0";
        if (choice >= 1){
            choiceAsString = options[choice - 1];
        }
        switch (choiceAsString){
            case "Add":
                add(modifiers.get(0));
                break;
            case "Substract":
                substract(modifiers.get(0));
                break;
            case "Multiply":
                multiply(modifiers.get(0));
                break;
            case "Divide":
                divide(modifiers.get(0));
                break;
            case "0":
                System.out.println("You Gave up");
                numToGet = 0;
                currentNum = 0;
                break;
        }
        if (choice >= 1 && choice <= 4) {
            updateModifiers();
            updateOptions(choice);
        }
    }

    public static void startGame(){
        currentNum = 1;
        numToGet = 12;
        while (numToGet != currentNum) {
            System.out.println("You are at "+ currentNum + " / " + numToGet);
            System.out.println("your modifier is " + modifiers.get(0) + ", Followed by " + modifiers.get(1) + " and " + modifiers.get(2));
            
            System.out.print(String.format("1: %s, 2: %s, 3: %s, 4: %s, Exit: 0", options[0], options[1], options[2], options[3]));
            System.out.println();

            int userChoice = getInput();
            handleInput(userChoice);
            
            if (numToGet == 12 && currentNum == numToGet){
                System.out.println("You got it");
            }
        }        
    }

    public static void main(String[] args) {
        startGame();
    }
}

// TODO make the game restartable aaa
// TODO implement amount of tries
