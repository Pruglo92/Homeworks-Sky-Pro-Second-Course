
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.skypro.exceptions.ArrayIsFullException;
import ru.skypro.exceptions.IncorrectIndexException;
import ru.skypro.exceptions.NullItemException;
import ru.skypro.service.impl.StringListImpl;

import static org.junit.jupiter.api.Assertions.*;

public class StringListImplTest {

    private StringListImpl stringList;

    String item1;
    String item2;
    int expectedSize1;
    int expectedSize2;
    int index0;

    @BeforeEach
    public void beforeEach() {
        stringList = new StringListImpl();
        item1 = "Элемент1";
        item2 = "Элемент2";
        expectedSize1 = 1;
        expectedSize2 = 2;
        index0 = 0;
    }

    @Test
    @DisplayName("Тест на переполнение массива")
    void addTest_ArrayIsFull_ExceptionThrown() {
        assertThrows(ArrayIsFullException.class, () -> {
            for (int i = 0; i < 11; i++) {
                stringList.add("i");
            }
        });
    }

    @Test
    @DisplayName("Тест на добавление null")
    void addTest_NullItem_Exception() {
        assertThrows(NullItemException.class, () ->
                stringList.add(null));
    }

    @Test
    @DisplayName("Тест на некорректный индекс")
    void addTest_IncorrectIndex_Exception() {
        assertThrows(IncorrectIndexException.class, () -> {
            stringList.add(-1, "");
            stringList.add(20, "");
        });

        assertThrows(IncorrectIndexException.class, () ->
                stringList.add(1, "")
        );
    }


    @Test
    @DisplayName("Тест на добавление элемента")
    void addTest() {

        var result = stringList.add(item1);
        assertEquals(item1, result);
        var size = stringList.size();
        assertEquals(expectedSize1, size);
    }

    @Test
    @DisplayName("Тест на добавление элемента на определённую позицию")
    void addToSpecificPositionTest() {

        var result = stringList.add(item1);
        assertEquals(item1, result);
        var resultWithIndex = stringList.add(index0, item2);
        assertEquals(item2, resultWithIndex);
        assertEquals(stringList.indexOf("Элемент2"), index0);
        var size = stringList.size();
        assertEquals(expectedSize2, size);
    }

    @Test
    @DisplayName("Тест на добавление элемента на определённую позицию с перезаписью")
    void addToSpecificPositionWithOverwritingTest() {

        var result = stringList.add(item1);
        assertEquals(item1, result);
        var resultWithIndex = stringList.set(index0, item2);
        assertEquals(item2, resultWithIndex);
        assertEquals(stringList.indexOf("Элемент2"), index0);
        var size = stringList.size();
        assertEquals(expectedSize1, size);
    }

    @Test
    @DisplayName("Тест на удаление по элементу")
    void removeByElementTest() {

        var result = stringList.add(item1);
        assertEquals(item1, result);
        var resultWithIndex = stringList.add(index0, item2);
        assertEquals(item2, resultWithIndex);
        assertEquals(stringList.indexOf(item2), index0);
        var size2 = stringList.size();
        assertEquals(expectedSize2, size2);
        var removedItem = stringList.remove(item2);
        assertEquals(removedItem, item2);
        var size1 = stringList.size();
        assertEquals(expectedSize1, size1);
        assertEquals(stringList.indexOf(item1), index0);
    }

    @Test
    @DisplayName("Тест на удаление по индексу")
    void removeByIndexTest() {

        var result = stringList.add(item1);
        assertEquals(item1, result);
        var resultWithIndex = stringList.add(index0, item2);
        assertEquals(item2, resultWithIndex);
        assertEquals(stringList.indexOf(item2), index0);
        var size2 = stringList.size();
        assertEquals(expectedSize2, size2);
        var removedItem = stringList.remove(index0);
        assertEquals(removedItem, item2);
        var size1 = stringList.size();
        assertEquals(expectedSize1, size1);
        assertEquals(stringList.indexOf(item1), index0);
    }

    @Test
    @DisplayName("Тест на существование элемента")
    void containsElementTest() {

        var result = stringList.add(item1);
        assertTrue(stringList.contains(result));
    }

    @Test
    @DisplayName("Тест на поиск индекса по элементу с начала")
    void indexOfElementTest() {

        var result = stringList.add(item1);
        assertEquals(stringList.indexOf(result), index0);
        assertEquals(stringList.indexOf(item2), -1);
    }

    @Test
    @DisplayName("Тест на поиск индекса по элементу с конца")
    void lastIndexOfElementTest() {

        stringList.add(item1);
        var result2 = stringList.add(item2);
        assertEquals(stringList.lastIndexOf(result2), 1);
        assertEquals(stringList.lastIndexOf(""), -1);
    }

    @Test
    @DisplayName("Тест на получение элемента по индексу")
    void getElementTest() {

        var result = stringList.add(item1);
        assertEquals(result, stringList.get(index0));
    }

    @Test
    @DisplayName("Тест на сравнение списка с другим")
    void equalsListElementsTest() {

        assertTrue(stringList.equals(new StringListImpl()));
    }

    @Test
    @DisplayName("Тест на получение фактического количества элементов")
    void getSizeTest() {

        stringList.add(item1);
        assertEquals(stringList.size(), expectedSize1);
        stringList.add(item2);
        assertEquals(stringList.size(), expectedSize2);
    }

    @Test
    @DisplayName("Тест на наличие хотя бы одного элемента")
    void isEmptyTest() {

        assertTrue(stringList.isEmpty());
        stringList.add(item1);
        assertFalse(stringList.isEmpty());
    }

    @Test
    @DisplayName("Тест на удаление всех элементов из списка")
    void clearTest() {

        stringList.add(item1);
        stringList.add(item2);
        assertFalse(stringList.isEmpty());
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    @DisplayName("Тест на преобразование в массив")
    void toArrayTest() {

        String[] strings = {item1, item2};

        stringList.add(item1);
        stringList.add(item2);
        assertArrayEquals(stringList.toArray(), strings);
    }
}
