import com.bai.dao.BlogMapper;
import com.bai.pojo.Blog;
import com.bai.util.IdUtils;
import com.bai.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/24 10:34
 */
public class MyTest {
    @Test
    public void test01() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId(IdUtils.getUUID());
        blog.setTitle("MyBatis确实好用");
        blog.setAuthor("南独酌酒");
        blog.setCreateTime(new Date());
        blog.setViews(5600);
        mapper.saveBlog(blog);

        blog.setId(IdUtils.getUUID());
        blog.setTitle("Spring确实好用");
        mapper.saveBlog(blog);

        blog.setId(IdUtils.getUUID());
        blog.setTitle("SpringBoot确实好用");
        mapper.saveBlog(blog);

        blog.setId(IdUtils.getUUID());
        blog.setTitle("SpringCloud确实好用");
        mapper.saveBlog(blog);

        sqlSession.close();
    }

    @Test
    public void test02() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("title", "Mybatis确实好用");
        List<Blog> blogs = mapper.getAll(reqMap);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void test03() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("title", "Mybatis确实好用");
//        map.put("author", "南独酌酒");
        map.put("views", "5600");
        List<Blog> blogs = mapper.queryChoose(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void test04() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId("4c067d7a925e4bd3a972e802b59e5198");
        blog.setTitle("SpringBoot确实好用3333");
        mapper.updateBlog(blog);
        sqlSession.close();
    }

    @Test
    public void test05() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        List<Blog> blogs = mapper.queryForeach(list);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }
}
