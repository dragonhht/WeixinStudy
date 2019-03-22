package hht.weixin.study.test;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Before;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-22
 */
public class Test {
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    private static class Model{
        private int Calories;
        private String name;
    }

    private List<Model> models = new LinkedList<>();
    private int size = 10000;
    @Before
    public void before() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            models.add(new Model(random.nextInt(1000), "model : " + i));
        }
    }

    @org.junit.Test
    public void test() {
        long start = System.currentTimeMillis();
        List<String> result = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            Model model = models.get(i);
            if (model.Calories > 300) {
                result.add(model.getName());
            }
        }
        System.out.println("for : " + (System.currentTimeMillis() - start) + " ," + result.size());

        start = System.currentTimeMillis();
        List<String> result1 = new LinkedList<>();
        for (Model model : models) {
            if (model.Calories > 300) {
                result1.add(model.getName());
            }
        }
        System.out.println("foreach : " + (System.currentTimeMillis() - start) + " ," + result1.size());

        start = System.currentTimeMillis();
        List<String> result2 = models.stream()
                .filter(d -> d.Calories > 300)
                .map(Model::getName)
                .collect(Collectors.toList());
        System.out.println("stream : " + (System.currentTimeMillis() - start) + " ," + result2.size());

    }

}
