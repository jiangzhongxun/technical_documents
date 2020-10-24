package com.bai.dao;

import com.bai.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/24 10:26
 */
public interface BlogMapper {
    /**
     * 添加博客记录
     *
     * @param blog 博客
     * @return 影响行数
     */
    public int saveBlog(Blog blog);

    /**
     * 跟据标题和作者姓名查询指定博客
     *
     * @param reqMap 查询条件
     * @return 博客记录
     */
    public List<Blog> getAll(@Param("reqMap") Map<String, Object> reqMap);

    /**
     * choose、when、otherwise 测试
     *
     * @param map 条件
     * @return 记录
     */
    public List<Blog> queryChoose(Map<String, Object> map);

    /**
     * 更新博客信息
     *
     * @param blog 博客信息
     * @return 影响行数
     */
    public int updateBlog(Blog blog);

    /**
     * 测试 foreach
     *
     * @param ids 条件
     * @return 博客数据
     */
    public List<Blog> queryForeach(@Param("ids") List<Integer> ids);
}
