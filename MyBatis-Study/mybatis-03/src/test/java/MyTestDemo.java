import com.bai.dao.StudentMapper;
import com.bai.dao.TeacherMapper;
import com.bai.pojo.Student;
import com.bai.pojo.Teacher;
import com.bai.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/23 22:15
 */
public class MyTestDemo {
    @Test
    public void test01() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.getStudents();
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void test02() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getById(1);
        System.out.println(teacher);
        sqlSession.close();
    }
}
