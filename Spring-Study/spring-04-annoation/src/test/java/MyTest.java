import com.bai.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/27 21:49
 */
public class MyTest {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean("user222", User.class);
        User user2 = context.getBean("user222", User.class);
        System.out.println(user.getName());
        System.out.println(user == user2);
    }
}
