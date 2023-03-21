/**
 * Main
 */
public class Main {

    


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public static void main(String[] args) {
        // Представьте, что вы пишете класс Render, который отвечает за вывод на экран текущего уровня жизней и усталости какого-то объекта.
        // (Подразумеваем, что вывод на экран - это просто печать в консоль)
        // У класса есть 1 метод, который принимает тип Object и делает следующее:
        // 1. Если object типа HasHealthPoint, то выводим его уровень жизни
        // 2. Если object типа Tiredness, то выводим его уровень усталости
        // При этом текст значения должен иметь цвет в соответствии с правилом:
        // BLACK(0, 24), RED(25, 50), GREEN(51-100)
        // 3. Создать несколько классов:
        // 3.1 Здание. Имеет только жизни.
        // 3.2 Персноаж. Имеет и жизни, и усталость

    //    System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);



//Не стала добавять цветной текст, т.к. в консоли выводитя ерунда, 
// просторы интернета подсказали, что проблема может быть в том, что у меня винда.
// Это могло бы выглядеть так (строки 82-83):  
// System.out.println(currentValue + " / " + maxValue + " (" + ANSI_GREEN + percentage + ANSI_RESET + "%) (GREEN)");


        Building building = new Building(100, 40);
        Building building1 = new Building(100, 20);
        Building building2 = new Building(100, 80);

        Character character = new Character(100, 40, 100, 40);
        Character character1 = new Character(100, 20, 100, 20);
        Character character2 = new Character(100, 20, 100, 80);

    

        Render render = new Render();
        render.render(building); // 40 - написано красным цветом
        render.render(building1); // 20 - написано черным цветом
        render.render(building2); // 80 - написано зеленым цветом

        System.out.println("");
        System.out.println("");

        render.render(character); // 40 - написано красным цветом
        render.render(character1); // 20 - написано черным цветом
        render.render(character2); // 20-HP(BLACK); 80 - написано зеленым цветом
    }

    static class Render {
        public void render(Object object) {
            if (object instanceof HasHealthPoint hasHealthPoint) {
                int maxHealthPoints = ((HasHealthPoint) object).getMaxHealthPoint();
                int currentHealthPoints = ((HasHealthPoint) object).getCurrentHealthPoint();
                System.out.print("Health Points: ");
                printColoredValue(currentHealthPoints, maxHealthPoints);
            }  if (object instanceof Tiredness) {
                int maxTiredness = ((Tiredness) object).getMaxTiredness();
                int currentTiredness = ((Tiredness) object).getCurrentTiredness();
                System.out.print("Tiredness: ");
                printColoredValue(currentTiredness, maxTiredness);
                System.out.println("");
            }
        }
    
        private void printColoredValue(int currentValue, int maxValue) {
            int percentage = (int) (((double) currentValue / (double) maxValue) * 100);
            if (percentage < 25) {
                System.out.println(currentValue + " / " + maxValue + " (" + percentage + "%) (BLACK)");
            } else if (percentage < 51) {
                System.out.println(currentValue + " / " + maxValue + " (" + percentage + "%) (RED)");
            } else {
                System.out.println(currentValue + " / " + maxValue + " (" + percentage + "%) (GREEN)");
                // System.out.println(currentValue + " / " + maxValue + " (" + ANSI_GREEN + percentage + ANSI_RESET + "%) (GREEN)");

            }
        }
    }
    

    interface HasHealthPoint {

        int getMaxHealthPoint();

        int getCurrentHealthPoint();

    }

    interface Tiredness {

        int getMaxTiredness();

        int getCurrentTiredness();

    }

    static class Building implements HasHealthPoint {

        private final int maxHealthPoint;
        private int currentHealthPoint;

        public Building(int maxHealthPoint, int currentHealthPoint) {
            this.maxHealthPoint = maxHealthPoint;
            this.currentHealthPoint = currentHealthPoint;
        }

        @Override
        public int getMaxHealthPoint() {
            return maxHealthPoint;
        }

        @Override
        public int getCurrentHealthPoint() {
            return currentHealthPoint;
        }
    }

    static class Character implements HasHealthPoint, Tiredness {
        private int maxHealthPoints;
        private int currentHealthPoints;
        private int maxTiredness;
        private int currentTiredness;
    
        public Character(int maxHealthPoints, int currentHealthPoints, int maxTiredness, int currentTiredness) {
            this.maxHealthPoints = maxHealthPoints;
            this.currentHealthPoints = currentHealthPoints;
            this.maxTiredness = maxTiredness;
            this.currentTiredness = currentTiredness;
        }
    
        @Override
        public int getMaxHealthPoint() {
            return maxHealthPoints;
        }
    
        @Override
        public int getCurrentHealthPoint() {
            return currentHealthPoints;
        }
    
        @Override
        public int getMaxTiredness() {
            return maxTiredness;
        }
    
        @Override
        public int getCurrentTiredness() {
            return currentTiredness;
        }
    }
}
