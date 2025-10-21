import java.util.Objects; // Для hashCode()

/**
 * Клас, що реалізує тривимірний вектор (vector3D).
 */
public class vector3D implements Comparable<vector3D> {

    // 1. Координати
    private final double x;
    private final double y;
    private final double z;

    /**
     * Конструктор для створення вектора.
     *
     * @param x координата X
     * @param y координата Y
     * @param z координата Z
     */
    public vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // --- Геттери (для доступу до приватних полів) ---
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    // --- Основні операції ---

    /**
     * 2. Додавання векторів.
     * Створює новий вектор як суму поточного та іншого вектора.
     *
     * @param other Вектор, що додається.
     * @return Новий vector3D (this + other).
     */
    public vector3D add(vector3D other) {
        return new vector3D(
                this.x + other.x,
                this.y + other.y,
                this.z + other.z
        );
    }

    /**
     * 3. Віднімання векторів.
     * Створює новий вектор як різницю поточного та іншого вектора.
     *
     * @param other Вектор, що віднімається.
     * @return Новий vector3D (this - other).
     */
    public vector3D subtract(vector3D other) {
        return new vector3D(
                this.x - other.x,
                this.y - other.y,
                this.z - other.z
        );
    }

    /**
     * 4. Скалярний добуток векторів (dot product).
     *
     * @param other Інший вектор.
     * @return Скалярний добуток (double).
     */
    public double dotProduct(vector3D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * 5. Множення на скаляр.
     *
     * @param scalar Число (double), на яке множиться вектор.
     * @return Новий vector3D (this * scalar).
     */
    public vector3D multiply(double scalar) {
        return new vector3D(
                this.x * scalar,
                this.y * scalar,
                this.z * scalar
        );
    }

    /**
     * 6. Обчислення довжини (модуля) вектора.
     *
     * @return Довжина вектора (double).
     */
    public double length() {
        // Використовуємо lengthSquared() щоб уникнути подвійного обчислення
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Допоміжний метод: обчислює квадрат довжини.
     * Це ефективніше для порівняння довжин, оскільки уникає Math.sqrt().
     *
     * @return Квадрат довжини (x*x + y*y + z*z).
     */
    private double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    // --- Порівняння ---

    /**
     * 7. Порівняння довжини векторів (через реалізацію Comparable).
     * Порівнює довжину поточного вектора з іншим.
     *
     * @param other Інший вектор для порівняння.
     * @return
     * -1, якщо поточний вектор коротший (this.length < other.length),
     * 0, якщо довжини рівні,
     * 1, якщо поточний вектор довший (this.length > other.length).
     */
    @Override
    public int compareTo(vector3D other) {
        // Порівнюємо квадрати довжин, щоб уникнути виклику Math.sqrt()
        // Це математично еквівалентно і швидше.
        return Double.compare(this.lengthSquared(), other.lengthSquared());
    }

    /**
     * 8. Порівняння векторів (на рівність).
     * Перевіряє, чи є вектори рівними (тобто чи рівні всі їхні координати).
     *
     * @param obj Об'єкт для порівняння.
     * @return true, якщо вектори рівні, інакше false.
     */
    @Override
    public boolean equals(Object obj) {
        // Стандартна перевірка equals
        if (this == obj) {
            return true; // Це той самий об'єкт в пам'яті
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Це null або об'єкт іншого класу
        }

        // Приведення типу
        vector3D that = (vector3D) obj;

        // Порівняння координат.
        // Використовуємо Double.compare для коректного порівняння double
        // (враховує NaN та +/- 0.0)
        return Double.compare(that.x, this.x) == 0 &&
                Double.compare(that.y, this.y) == 0 &&
                Double.compare(that.z, this.z) == 0;
    }

    /**
     * Генерує hashCode на основі координат.
     * Обов'язково перевизначається при перевизначенні equals().
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    /**
     * Представлення вектора у вигляді рядка (для зручного виводу).
     */
    @Override
    public String toString() {
        return "vector3D(" + x + ", " + y + ", " + z + ")";
    }


    // --- Приклад використання (main) ---
    public static void main(String[] args) {
        vector3D v1 = new vector3D(1.0, 2.0, 3.0);
        vector3D v2 = new vector3D(4.0, 5.0, 6.0);
        vector3D v3 = new vector3D(1.0, 2.0, 3.0);
        vector3D v4 = new vector3D(2.0, 4.0, 6.0); // v1 * 2

        System.out.println("v1 = " + v1);
        System.out.println("v2 = " + v2);
        System.out.println("v3 = " + v3);
        System.out.println("v4 = " + v4);

        System.out.println("--- Операції ---");
        // Додавання
        System.out.println("v1 + v2 = " + v1.add(v2));
        // Віднімання
        System.out.println("v1 - v2 = " + v1.subtract(v2));
        // Скалярний добуток
        System.out.println("v1 . v2 = " + v1.dotProduct(v2));
        // Множення на скаляр
        System.out.println("v1 * 2.0 = " + v1.multiply(2.0));

        System.out.println("--- Довжина ---");
        // Обчислення довжини
        System.out.printf("Довжина v1 = %.4f\n", v1.length());
        System.out.printf("Довжина v2 = %.4f\n", v2.length());

        System.out.println("--- Порівняння векторів (equals) ---");
        // Порівняння векторів
        System.out.println("v1.equals(v2) (1,2,3 == 4,5,6): " + v1.equals(v2)); // false
        System.out.println("v1.equals(v3) (1,2,3 == 1,2,3): " + v1.equals(v3)); // true
        System.out.println("v1.equals(v4) (1,2,3 == 2,4,6): " + v1.equals(v4)); // false

        System.out.println("--- Порівняння довжини (compareTo) ---");
        // Порівняння довжини векторів
        int lenComp_v1_v2 = v1.compareTo(v2);
        if (lenComp_v1_v2 < 0) {
            System.out.println("v1 коротший за v2");
        } else if (lenComp_v1_v2 > 0) {
            System.out.println("v1 довший за v2");
        } else {
            System.out.println("v1 та v2 однакової довжини");
        }

        // Порівняння v1 та v4 (v4 = v1 * 2, отже v4 довший)
        int lenComp_v1_v4 = v1.compareTo(v4);
        if (lenComp_v1_v4 < 0) {
            System.out.println("v1 коротший за v4");
        }
    }
}
