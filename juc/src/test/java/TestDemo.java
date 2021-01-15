import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2021/1/15 22:44
 */
public class TestDemo {
    private final static List<String> emptyList;

    static {
        emptyList = Arrays.asList("张三", "李四", "王玉", "陈六");
    }

    @Test
    public void test1() {
        Optional<String> first = emptyList.stream().findFirst();
        first.ifPresent(System.out::println);
    }

    @Test
    public void test2() {
        emptyList.stream().sorted().forEach(System.out::println);
    }
}
