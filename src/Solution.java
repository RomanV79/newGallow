import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {

        List<String> listWords = Utils.getWordList();

        while (Utils.isPlay()) {
            String[] board = Gallow.makeNewGallow();
            char[] secretWord = Utils.getWord(listWords).toUpperCase().toCharArray();
            char[] secretTemplate = new char[secretWord.length];
            Arrays.fill(secretTemplate, '_');
            boolean newError = false;
            List<Character> errorLetters = new ArrayList<>();

            while (true) {
                if (newError) {
                    board = Gallow.addElementToGallow(board, errorLetters.size());
                    newError = false;
                }

                Arrays.stream(board).forEach(System.out::println);

                String lineTemplateWord = String.valueOf(secretTemplate);
                System.out.println("\n" + "Слово: " + lineTemplateWord);
                System.out.format("Ошибки (%d): ", errorLetters.size());
                errorLetters.forEach(System.out::print);
                System.out.print("\n" + "Следующая буква: ");

                String newSymbol = Utils.getNewSymbol(errorLetters, secretTemplate);

                if (Utils.isLetterInSecretWord(newSymbol, secretWord)) {
                    secretTemplate = Utils.refreshSecretTemplate(secretWord, secretTemplate, newSymbol);
                } else {
                    errorLetters.add(newSymbol.toUpperCase().charAt(0));
                    newError = true;
                }

                if (errorLetters.size() >= Gallow.MAX_MISTAKE) {
                    System.out.println("\n" + "К сожалению, вы проиграли :(  ::: было загадано слово: " + String.valueOf(secretWord));
                    System.out.println("Может быть в следующий раз повезет");
                    System.out.println("Нажмите ENTER...");
                    Utils.scanner.nextLine();
                    System.out.println();
                    break;
                }
                if (!(String.valueOf(secretTemplate).indexOf("_") >= 0)) {
                    System.out.println("\n" + "Верно!!! Это слово: " + String.valueOf(secretWord));
                    System.out.println("Вы победили! Еще пару раз и вы станете игроком дня! :)");
                    System.out.println("Нажмите ENTER...");
                    Utils.scanner.nextLine();
                    System.out.println();
                    break;
                }
            }
        }

        Utils.scanner.close();
    }

}
