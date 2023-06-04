import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.skypro.exceptions.IncorrectIndexException;
import ru.skypro.exceptions.NullItemException;
import ru.skypro.service.impl.IntegerListImpl;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerListImplTest {

    private IntegerListImpl integerList;

    Integer item1;
    Integer item2;
    int expectedSize1;
    int expectedSize2;
    int index0;

    @BeforeEach
    public void beforeEach() {
        integerList = new IntegerListImpl();
        item1 = 1;
        item2 = 2;
        expectedSize1 = 1;
        expectedSize2 = 2;
        index0 = 0;
    }

    @Test
    @DisplayName("Тест на добавление null")
    void addTest_NullItem_Exception() {
        assertThrows(NullItemException.class, () ->
                integerList.add(null));
    }

    @Test
    @DisplayName("Тест на некорректный индекс")
    void addTest_IncorrectIndex_Exception() {
        assertThrows(IncorrectIndexException.class, () -> {
            integerList.add(-1, item1);
            integerList.add(20, item1);
        });

        assertThrows(IncorrectIndexException.class, () ->
                integerList.add(1, item1)
        );
    }


    @Test
    @DisplayName("Тест на добавление элемента")
    void addTest() {

        var result = integerList.add(item1);
        assertEquals(item1, result);
        var size = integerList.size();
        assertEquals(expectedSize1, size);
    }

    @Test
    @DisplayName("Тест на добавление элемента на определённую позицию")
    void addToSpecificPositionTest() {

        var result = integerList.add(item1);
        assertEquals(item1, result);
        var resultWithIndex = integerList.add(index0, item2);
        assertEquals(item2, resultWithIndex);
        assertEquals(integerList.indexOf(item2), index0);
        var size = integerList.size();
        assertEquals(expectedSize2, size);
    }

    @Test
    @DisplayName("Тест на добавление элемента на определённую позицию с перезаписью")
    void addToSpecificPositionWithOverwritingTest() {

        var result = integerList.add(item1);
        assertEquals(item1, result);
        var resultWithIndex = integerList.set(index0, item2);
        assertEquals(item2, resultWithIndex);
        assertEquals(integerList.indexOf(item2), index0);
        var size = integerList.size();
        assertEquals(expectedSize1, size);
    }

    @Test
    @DisplayName("Тест на удаление по элементу")
    void removeByElementTest() {

        var result = integerList.add(item1);
        assertEquals(item1, result);
        var resultWithIndex = integerList.add(index0, item2);
        assertEquals(item2, resultWithIndex);
        assertEquals(integerList.indexOf(item2), index0);
        var size2 = integerList.size();
        assertEquals(expectedSize2, size2);
        var removedItem = integerList.remove(item2);
        assertEquals(removedItem, item2);
        var size1 = integerList.size();
        assertEquals(expectedSize1, size1);
        assertEquals(integerList.indexOf(item1), index0);
    }

    @Test
    @DisplayName("Тест на удаление по индексу")
    void removeByIndexTest() {

        var result = integerList.add(item1);
        assertEquals(item1, result);
        var resultWithIndex = integerList.add(index0, item2);
        assertEquals(item2, resultWithIndex);
        assertEquals(integerList.indexOf(item2), index0);
        var size2 = integerList.size();
        assertEquals(expectedSize2, size2);
        var removedItem = integerList.remove(index0);
        assertEquals(removedItem, item2);
        var size1 = integerList.size();
        assertEquals(expectedSize1, size1);
        assertEquals(integerList.indexOf(item1), index0);
    }

    @Test
    @DisplayName("Тест на существование элемента")
    void containsElementTest() {

        var result = integerList.add(item1);
        assertTrue(integerList.contains(result));
    }

    @Test
    @DisplayName("Тест на поиск индекса по элементу с начала")
    void indexOfElementTest() {

        var result = integerList.add(item1);
        assertEquals(integerList.indexOf(result), index0);
        assertEquals(integerList.indexOf(item2), -1);
    }

    @Test
    @DisplayName("Тест на поиск индекса по элементу с конца")
    void lastIndexOfElementTest() {

        integerList.add(item1);
        var result2 = integerList.add(item2);
        assertEquals(integerList.lastIndexOf(result2), 1);
        assertEquals(integerList.lastIndexOf(10), -1);
    }

    @Test
    @DisplayName("Тест на получение элемента по индексу")
    void getElementTest() {

        var result = integerList.add(item1);
        assertEquals(result, integerList.get(index0));
    }

    @Test
    @DisplayName("Тест на сравнение списка с другим")
    void equalsListElementsTest() {

        assertTrue(integerList.equals(new IntegerListImpl()));
    }

    @Test
    @DisplayName("Тест на получение фактического количества элементов")
    void getSizeTest() {

        integerList.add(item1);
        assertEquals(integerList.size(), expectedSize1);
        integerList.add(item2);
        assertEquals(integerList.size(), expectedSize2);
    }

    @Test
    @DisplayName("Тест на наличие хотя бы одного элемента")
    void isEmptyTest() {

        assertTrue(integerList.isEmpty());
        integerList.add(item1);
        assertFalse(integerList.isEmpty());
    }

    @Test
    @DisplayName("Тест на удаление всех элементов из списка")
    void clearTest() {

        integerList.add(item1);
        integerList.add(item2);
        assertFalse(integerList.isEmpty());
        integerList.clear();
        assertTrue(integerList.isEmpty());
    }

    @Test
    @DisplayName("Тест на преобразование в массив")
    void toArrayTest() {

        Integer[] integers = {item1, item2};

        integerList.add(item1);
        integerList.add(item2);
        assertArrayEquals(integerList.toArray(), integers);
    }
}
