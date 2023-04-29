import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Utils {
    static Scanner scanner = new Scanner(System.in);
    public static Map<Integer, String> getWordList(){

        Map<Integer, String> map = new HashMap<>();
        File file = new File("src/dictionary.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String word;
            int index = 0;
            while ((word = bufferedReader.readLine()) != null) {
                map.put(index, word);
                index++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    public static String getWord(Map<Integer, String> map) {

        Random random = new Random();
        return map.get(random.nextInt(map.size() - 1));
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

        boolean isLenghtCorrect = symbol.length() == 1;
        boolean patternMatches = Pattern.matches("[а-яА-Я]", symbol);
        boolean errorLettersAlready =  !errorLetters.contains(symbol.toUpperCase().charAt(0));
        boolean alreadyGuess = !Arrays.asList(secretTemplate).contains(symbol.toUpperCase().charAt(0));

        return (isLenghtCorrect && patternMatches && errorLettersAlready && alreadyGuess);
    }
}