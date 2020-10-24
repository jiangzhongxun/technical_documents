import com.bai.dao.UserMapper;
import com.bai.pojo.User;
import com.bai.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/24 12:44
 */
public class MyTest {
    @Test
    public void test01() {
        // 测试一级缓存
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 一级缓存的作用域只限定于 sqlSession 一次会话中有效
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        System.err.println("================第一次查询 user==================");
        User user = mapper.getById(1);
        System.err.println(user);

        System.err.println("================手动清除缓存==================");
        sqlSession.clearCache();

        System.err.println("================第二次查询 user==================");
        User user2 = mapper.getById(1);
        System.err.println(user2);

        sqlSession.close();
    }

    @Test
    public void test02() {
        // 测试二级缓存
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getById(1);
        System.out.println(user);
        sqlSession.close();

        SqlSession sqlSession2 = MyBatisUtil.getSqlSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.getById(1);
        System.out.println(user2);
        sqlSession2.close();
    }
}
