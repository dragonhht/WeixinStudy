package hht.weixin.study.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-22
 */
public class Test2 {
    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    private static class Model {
        private int size;
        private String name;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Model) {
                Model model = (Model) obj;
                if (this.name.equals(model.getName()) && this.size == ((Model) obj).getSize()) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = 111;
            result = result * 31 + name.hashCode();
            result = result * 31 + size;

            return result;
        }
    }

    @Test
    public void test() {
        List<Model> models = new LinkedList<>();
        models.add(new Model(1, "haha"));
        models.add(new Model(1, "haha"));
        models.add(new Model(1, "haha"));
        models.add(new Model(2, "haha"));
        models.add(new Model(3, "haha"));
        models.add(new Model(4, "haha"));

        List<Model> result = models.stream()
                .distinct()
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }

}
