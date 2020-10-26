import com.bai.pojo.Student;
import com.bai.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/26 20:55
 */
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.err.println(student.toString());

        /*
        Student(
        name=大圣,
        address=Address(
            name=上海
        ),
        wife=null,
        hobbys=[听歌, 敲代码, 打游戏],
        games=[LOL, CF, DNF],
        card={
            身份证=111111222222223333,
            学号=123456798613
        },
        books=[红楼梦, 西游记, 水浒传, 三国演义],
        info={
            邮箱=bai211425401@126.com,
            公司=杭州阿里巴巴,
            职业=程序猿
        })
         */
    }

    @Test
    public void test02() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = context.getBean("user2", User.class);
        System.out.println(user);
    }
}
