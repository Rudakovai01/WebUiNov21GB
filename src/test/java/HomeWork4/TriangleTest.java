package HomeWork4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Array;

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
        Assertions.assertThrows(Exception.class, ()-> TriangleSquare.getTriangleSquare(-3,7,8));
    }
 }
