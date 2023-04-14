import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;

public class ArrayListTest {
    class Person{
        private String name;
        private int age;

        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    ArrayList<Person> list;

    @BeforeEach
    void init(){
        list = new ArrayList<>();
    }

    @Test
    @DisplayName("addAll의 인자로 Collection을 구현한 LinkedList 넘기기")
    void addAllTest() {
        // given
        LinkedList<Person> subList = new LinkedList<>();
        subList.add(new Person("1", 1));
        subList.add(new Person("2", 2));
        subList.add(new Person("3", 3));
        subList.add(new Person("4", 4));

        // when
        list.addAll(subList);

        // then
        assertThat(list.size()).isEqualTo(subList.size());
    }

    @Test
    @DisplayName("clone의 return값인 Object가 ArrayList인지 확인하기")
    void cloneReturnTest(){
        // given
        list.add(new Person("1", 1));
        list.add(new Person("2", 2));
        list.add(new Person("3", 3));
        list.add(new Person("4", 4));

        // when
        Object cloneList = list.clone();

        // then
        assertThat(cloneList instanceof ArrayList<?>).isEqualTo(true);
    }

    @Test
    @DisplayName("clone의 shallow copy인지 확인")
    void cloneShallowCopyTest(){
        // given
        list.add(new Person("1", 1));
        list.add(new Person("2", 2));
        list.add(new Person("3", 3));
        list.add(new Person("4", 4));

        // when
        ArrayList cloneList = (ArrayList)list.clone();

        // then
        assertThat(cloneList.get(0)).isEqualTo(list.get(0));
    }

    @Test
    @DisplayName("clone의 shallow copy 삭제는 독립적으로 이뤄짐")
    void cloneShallowCopyDeleteTest(){
        // given
        list.add(new Person("1", 1));
        list.add(new Person("2", 2));
        list.add(new Person("3", 3));
        list.add(new Person("4", 4));

        // when
        ArrayList cloneList = (ArrayList)list.clone();
        cloneList.remove(3);
        cloneList.remove(2);

        // then
        assertThat(cloneList.size()).isEqualTo(2);
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("clone의 shallow copy 변경은 참조하는 모든 리스트에 반영됨")
    void cloneShallowCopyUpdateTest(){
        // given
        list.add(new Person("1", 1));
        list.add(new Person("2", 2));
        list.add(new Person("3", 3));
        list.add(new Person("4", 4));

        // when
        ArrayList<Person> cloneList = (ArrayList)list.clone();
        cloneList.get(0).setName("5");

        // then
        assertThat(list.get(0).getName()).isEqualTo("5");
    }
}
