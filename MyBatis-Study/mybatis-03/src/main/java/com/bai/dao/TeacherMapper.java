package com.bai.dao;

import com.bai.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/23 22:07
 */
public interface TeacherMapper {
    public Teacher getById(@Param("tid") Integer id);
}
