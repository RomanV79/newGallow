public class Gallow {

    public static int MAX_MISTAKE = 7;

    public static String[] makeNewGallow(){
        String[] element = {"   ________", // 0
                "   |      |", // 1
                "   |      ", // 2
                "   |      ", // 3
                "   |      ", // 4
                "   |     ", // 5
                "   |      ", // 6
                "   |____________"};
        return element;
    }
    public static String[] addElementToGallow(String[] array, int qntError) {

        switch (qntError) {
            case 1:
                array[2] += Man.HEAD;
                break;

            case 2:
                array[3] += Man.BODY_ONE;
                break;

            case 3:
                array[4] += Man.BODY_TWO;
                break;

            case 4:
                array[3] = array[3].substring(0, array[3].length() - 2) + Man.LEFT_ARM + Man.BODY_TWO;
                break;

            case 5:
                array[3] += Man.RIGHT_ARM;
                break;

            case 6:
                array[5] += Man.LEFT_FOOT + " ";
                break;

            case 7:
                array[5] += Man.RIGHT_FOOT;
                break;

            default: break;
        }

        return array;
    }
}
