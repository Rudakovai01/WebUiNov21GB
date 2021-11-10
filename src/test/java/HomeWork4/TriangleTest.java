package HomeWork4;

import org.junit.jupiter.api.*;

public class TriangleTest {
    @BeforeAll
    static void beforeAllMethod(){
        System.out.println("Метод выполнится 1 раз перед всеми тестами сайта");
    }
    @BeforeEach
    void beforeEachMethod(){
        System.out.println("Метод выполняется перед каждым тестом");
    }
    @AfterEach
    void afterEachMethod(){
        System.out.println("Метод выполняется перед каждым тестом");
    }
    @AfterAll
    static void afterAllMethod(){
        System.out.println("Метод выполнится 1 раз после всех тестов сайта");
    }

    @Test
    @DisplayName("Проверка выброса исключения при длине стороны треугольника <=0")
    void exeptionIsTriangleSidePositive () throws Exception {
        TriangleSquareCalculator result = new TriangleSquareCalculator();
        Assertions.assertThrows(Exception.class, ()-> result.getTriangleSquare(-3,7,8));
    }

    @Test
    @DisplayName("Проверка правильности рассчета")
    void calculationCheck () throws Exception {
        TriangleSquareCalculator result2 = new TriangleSquareCalculator();
        Assertions.assertEquals(17.41,result2.getTriangleSquare(7,5,9), .01);
    }
 }
