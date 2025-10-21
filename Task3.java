import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpressionEvaluator {

    /**
     * Головний метод, який запускає програму.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть вираз (напр: 5 + 3 * 2):");

        // Зчитуємо рядок
        String inputExpression = scanner.nextLine();

        try {
            // Передаємо рядок в наш метод "evaluate"
            long result = evaluate(inputExpression);

            // Друкуємо результат
            System.out.println("Результат: " + result);

        } catch (Exception e) {
            // Ловимо помилку, якщо ввели щось не те (напр. "5 + * 3")
            System.err.println("Помилка! Перевірте вираз.");
        }

        scanner.close();
    }

    /**
     * Метод, який обчислює вираз.
     */
    public static long evaluate(String expression) {

        // 1. Прибираємо всі пробіли
        String noSpaceExpr = expression.replaceAll("\\s+", "");

        // 2. Розділяємо рядок на числа та оператори
        // (Це складна частина - "регулярний вираз")
        String[] tokens = noSpaceExpr.split("(?<=\\d)(?=[+\\-*])|(?<=[+\\-*])(?=\\d)");

        List<Long> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        // 3. Розкладаємо все у два списки
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*")) {
                operators.add(token.charAt(0));
            } else {
                numbers.add(Long.parseLong(token)); // Перетворюємо текст "5" на число 5
            }
        }

        // 4. Етап 1: Множення
        int i = 0;
        while (i < operators.size()) {
            if (operators.get(i) == '*') {
                // Беремо числа зліва (i) та справа (i+1)
                long left = numbers.get(i);
                long right = numbers.get(i + 1);

                // Рахуємо
                long result = left * right;

                // Оновлюємо списки:
                numbers.set(i, result);    // Замість "5" ставимо "10"
                numbers.remove(i + 1); // Видаляємо "2"
                operators.remove(i);       // Видаляємо "*"

                // 'i' не збільшуємо, щоб перевірити наступний оператор
            } else {
                // Якщо це '+' або '-', йдемо далі
                i++;
            }
        }

        // 5. Етап 2: Додавання і віднімання

        // Беремо перше число як початковий результат
        long finalResult = numbers.get(0);

        // Ідемо по решті операцій (вже без множення)
        for (int j = 0; j < operators.size(); j++) {
            char op = operators.get(j);
            long nextNum = numbers.get(j + 1); // Беремо наступне число

            if (op == '+') {
                finalResult = finalResult + nextNum;
            } else if (op == '-') {
                finalResult = finalResult - nextNum;
            }
        }

        // Повертаємо кінцевий результат
        return finalResult;
    }
}
