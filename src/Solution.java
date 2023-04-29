import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        Gallow gallow = new Gallow();
        Map<Integer, String> listWords = Utils.getWordList();

        int maxMistake = 7;

        while (Utils.isPlay()) {
            String[] board = gallow.makeNewGallow();
            char[] secretWord = Utils.getWord(listWords).toUpperCase().toCharArray();
            char[] secretTemplate = new char[secretWord.length];
            Arrays.fill(secretTemplate, '_');
            boolean newError = false;
            List<Character> errorLetters = new ArrayList<>();

            while (true) {
                if (newError) {
                    board = gallow.addElementToGallow(board, errorLetters.size());
                    newError = false;
                }

                Arrays.stream(board).forEach(System.out::println);

                if (errorLetters.size() >= maxMistake) {
                    System.out.println();
                    System.out.println("К сожалению, вы проиграли :(  ::: было загадано слово: " + String.valueOf(secretWord));
                    System.out.println("Может быть в следующий раз повезет");
                    System.out.println("Нажмите ENTER...");
                    Utils.scanner.nextLine();
                    System.out.println();
                    break;
                }
                if (!(String.valueOf(secretTemplate).indexOf("_") >= 0)) {
                    System.out.println();
                    System.out.println("Верно!!! Это слово: " + String.valueOf(secretWord));
                    System.out.println("Вы победили! Еще пару раз и вы станете игроком дня! :)");
                    System.out.println("Нажмите ENTER...");
                    Utils.scanner.nextLine();
                    System.out.println();
                    break;
                }

                String lineWord = String.valueOf(secretTemplate);
                System.out.println();
                System.out.println("Слово: " + lineWord);
                System.out.format("Ошибки (%d): ", errorLetters.size());
                errorLetters.forEach(System.out::print);
                System.out.println();
                System.out.print("Следующая буква: ");

                String symbol = Utils.scanner.nextLine();

                while (!Utils.isCorrectSymbol(symbol, errorLetters, secretTemplate)) {
                    System.out.println("Вы ввели неверную букву или вы уже вводили ее ранее, введите одну букву русского алфавита");
                    System.out.print("Итак, ваша буква: ");
                    symbol = Utils.scanner.nextLine();
                }

                boolean isLetterInSecretWord = false;
                for (int i = 0; i < secretWord.length; i++) {
                    char temp = symbol.toUpperCase().charAt(0);
                    if (secretWord[i] == temp) {
                        secretTemplate[i] = temp;
                        isLetterInSecretWord = true;
                    }
                }
                if (!isLetterInSecretWord) {
                    errorLetters.add(symbol.toUpperCase().charAt(0));
                    newError = true;
                }
            }
        }
    }

}
