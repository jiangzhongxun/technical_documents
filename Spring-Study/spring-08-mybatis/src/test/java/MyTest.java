import com.bai.mapper.UserMapper;
import com.bai.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 15:10
 */
public class MyTest {
    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper2", UserMapper.class);
        for (User user : userMapper.getUserList()) {
            System.out.println(user);
        }
    }
}
