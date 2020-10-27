import com.bai.config.SpringConfig;
import com.bai.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/27 22:21
 */
public class MyTest {
    @Test
    public void test1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        User getUser = context.getBean("getUser", User.class);
        System.out.println(getUser.getName());
    }
}
