import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Utils {
    static Scanner scanner = new Scanner(System.in);
    public static List<String> getWordList(){

        List<String> list = new ArrayList<>();
        File file = new File("src/dictionary.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String word;
            int index = 0;
            while ((word = bufferedReader.readLine()) != null) {
                list.add(word);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public static String getWord(List<String> list) {

        Random random = new Random();
        return list.get(random.nextInt(list.size() - 1));
    }

    public static Boolean isPlay(){
        System.out.println("Сыграем в ВИСИЛИЦУ ?");
        System.out.println("Введите 1 - да, 0 - нет");
        String input = "";
        boolean isPlay = true;

        while (!input.equals("1") && !input.equals("0")) {
            input = Utils.scanner.nextLine();
            if (input.equals("1")) {
                isPlay = true;
            } else if (input.equals("0")) {
                isPlay = false;
                System.out.println("Нет так нет. Спасибо за внимание.");
            } else {
                System.out.println("Неверный ввод. Введите 1 - если хотите играть, введите 0 - если не будете играть.");
            }
        }

        return isPlay;
    }

    public static Boolean isCorrectSymbol(String symbol, List<Character> errorLetters, char[] secretTemplate) {

        if (!symbol.isEmpty()){
            boolean isLenghtCorrect = symbol.length() == 1;
            boolean patternMatches = Pattern.matches("[а-яА-Я]", symbol);
            boolean errorLettersAlready =  !errorLetters.contains(symbol.toUpperCase().charAt(0));
            boolean alreadyGuess = !Arrays.asList(secretTemplate).contains(symbol.toUpperCase().charAt(0));

            return (isLenghtCorrect && patternMatches && errorLettersAlready && alreadyGuess);
        }
        return false;
    }

    public static String getNewSymbol(List<Character> errorLetters, char[] secretTemplate){
        String symbol = Utils.scanner.nextLine();
        while (!Utils.isCorrectSymbol(symbol, errorLetters, secretTemplate)) {
            System.out.println("Вы ввели неверную букву или вы уже вводили ее ранее, введите одну букву русского алфавита");
            System.out.print("Итак, ваша буква: ");
            symbol = Utils.scanner.nextLine();
        }

        return symbol.toUpperCase();
    }

    public static boolean isLetterInSecretWord(String symbol, char[] secretWord){
        return String.valueOf(secretWord).contains(symbol);
    }

    public static char[] refreshSecretTemplate(char[] secretWord, char[] secretTemplate, String symbol){
        for (int i = 0; i < secretWord.length; i++) {
            char temp = symbol.toUpperCase().charAt(0);
            if (secretWord[i] == temp) {
                secretTemplate[i] = temp;
            }
        }
        return secretTemplate;
    }
}
