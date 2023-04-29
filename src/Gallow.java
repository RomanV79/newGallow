public class Gallow {

    public String[] makeNewGallow(){
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
    public String[] addElementToGallow(String[] array, int qntError) {
        Man man = new Man();
        switch (qntError) {
            case 1:
                array[2] += man.head;
                break;

            case 2:
                array[3] += man.bodyOne;
                break;

            case 3:
                array[4] += man.bodyTwo;
                break;

            case 4:
                array[3] = array[3].substring(0, array[3].length() - 2) + man.leftArm + man.bodyTwo;
                break;

            case 5:
                array[3] += man.rightArm;
                break;

            case 6:
                array[5] += man.leftFoot + " ";
                break;

            case 7:
                array[5] += man.rightFoot;
                break;

            default: break;
        }

        return array;
    }
}
