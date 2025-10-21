/**
 * Клас, що описує правильний конус.
 */
public class Cone {

    // --- Поля класу ---

    // Радіус основи
    private final double radius;
    // Висота конуса
    private final double height;

    /**
     * Конструктор для створення конуса.
     *
     * @param radius Радіус основи (повинен бути > 0).
     * @param height Висота конуса (повинна бути > 0).
     */
    public Cone(double radius, double height) {
        // Валідація вхідних даних
        if (radius <= 0 || height <= 0) {
            throw new IllegalArgumentException("Радіус та висота повинні бути додатніми числами.");
        }
        this.radius = radius;
        this.height = height;
    }

    // --- Геттери (для доступу до полів) ---
    public double getRadius() {
        return radius;
    }

    public double getHeight() {
        return height;
    }

    // --- Приватні допоміжні методи ---

    /**
     * Обчислює твірну (l) конуса за теоремою Піфагора.
     * l = sqrt(r^2 + h^2)
     * @return Довжина твірної.
     */
    private double getSlantHeight() {
        // Math.hypot(a, b) = Math.sqrt(a*a + b*b)
        return Math.hypot(radius, height);
    }

    // --- Основні методи (за умовою) ---

    /**
     * 1. Обчислює площу бічної поверхні конуса.
     * Формула: A_lateral = π * r * l
     * (де l - твірна)
     *
     * @return Площа бічної поверхні.
     */
    public double getLateralSurfaceArea() {
        double slantHeight = getSlantHeight(); // Отримуємо твірну
        return Math.PI * radius * slantHeight;
    }

    /**
     * 2. Обчислює об'єм конуса.
     * Формула: V = (1/3) * π * r^2 * h
     *
     * @return Об'єм конуса.
     */
    public double getVolume() {
        // (1.0 / 3.0) гарантує ділення з плаваючою комою
        return (1.0 / 3.0) * Math.PI * (radius * radius) * height;
    }

    // --- Додаткові корисні методи ---

    /**
     * Обчислює площу основи конуса.
     * Формула: A_base = π * r^2
     *
     * @return Площа основи.
     */
    public double getBaseArea() {
        return Math.PI * (radius * radius);
    }

    /**
     * Обчислює повну площу поверхні конуса.
     * (Площа бічної поверхні + Площа основи)
     *
     * @return Повна площа поверхні.
     */
    public double getTotalSurfaceArea() {
        return getLateralSurfaceArea() + getBaseArea();
    }

    /**
     * Представлення конуса у вигляді рядка.
     */
    @Override
    public String toString() {
        return "Cone[radius=" + radius + ", height=" + height + "]";
    }


    // --- Приклад використання (main) ---
    public static void main(String[] args) {
        // Створюємо конус (класичний приклад: r=3, h=4, твірна l=5)
        Cone cone1 = new Cone(3.0, 4.0);

        System.out.println("Створено конус: " + cone1);

        // 1. Обчислюємо площу бічної поверхні
        // A = π * 3 * 5 = 15π ≈ 47.12
        double lateralArea = cone1.getLateralSurfaceArea();
        System.out.printf("Площа бічної поверхні: %.4f\n", lateralArea);

        // 2. Обчислюємо об'єм
        // V = (1/3) * π * 3^2 * 4 = (1/3) * π * 9 * 4 = 12π ≈ 37.70
        double volume = cone1.getVolume();
        System.out.printf("Об'єм конуса:           %.4f\n", volume);

        // Додатково: Повна площа
        // A_total = 15π (бічна) + 9π (основа) = 24π ≈ 75.40
        double totalArea = cone1.getTotalSurfaceArea();
        System.out.printf("Повна площа поверхні:   %.4f\n", totalArea);

        // Приклад з невірними даними
        try {
            Cone invalidCone = new Cone(-5.0, 10.0);
        } catch (IllegalArgumentException e) {
            System.out.println("\nПомилка при створенні конуса: " + e.getMessage());
        }
    }
}
