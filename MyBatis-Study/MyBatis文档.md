# MyBatis 简介

mybatis3 中文文档官网：https://mybatis.org/mybatis-3/zh/index.html

GitHub 源码网址：https://github.com/mybatis/mybatis-3

**最新版本是MyBatis 3.5.5 ，其发布时间是2020年6月4日。**

Maven 仓库依赖：

```xml
<!-- 目前最新的 mybatis 版本 -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.5</version>
</dependency>
```

# 第一个MyBatis测试

## 1、 创建测试数据库

```sql
-- 创建测试数据库
CREATE DATABASE `mybatis`;

-- 使用此数据库
USE `mybatis`;

-- 创建 user 表
CREATE TABLE user(
	`id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(30) NOT NULL,
	`pwd` VARCHAR(30) NOT NULL
)ENGINE=INNODB CHARSET UTF8;

-- 添加3条测试数据
INSERT INTO user(`name`, `pwd`) VALUES
('张三', '864531'),
('李四', '1564513'),
('刘鹏飞', '9846513')
```

![image-20201015214225251](MyBatis文档.assets/image-20201015214225251.png)

## 2、 搭建项目

使用 IDEA 创建一个空的 maven 项目

![image-20201015214352045](MyBatis文档.assets/image-20201015214352045.png)

然后删除其中的 src 目录，将此工程作为一个父工程。

## 3、 添加 maven 依赖

```xml
<dependencies>
        <!-- mysql 依赖 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>

        <!-- mybatis 依赖 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.5</version>
        </dependency>

        <!-- junit 依赖 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <!-- lombok 依赖 -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.12</version>
        </dependency>
    </dependencies>
```

## 4、 添加第一个测试子 module 项目

![image-20201015215200737](MyBatis文档.assets/image-20201015215200737.png)

![image-20201015215220022](MyBatis文档.assets/image-20201015215220022.png)

## 5、创建 pojo 实体类

使用 @Data 注解可以省去写大量的 getter/setter 方法

```java
package com.bai.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/15 21:54
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private String pwd;
}
```

## 6、 配置 MyBatis 核心文件 mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- mybatis 核心配置 -->
<configuration>
    <!-- 运行环境 -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf8"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>
</configuration>
```

## 7、 创建一个工具类

此工具类用于获取 SqlSession 的类

```java
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/15 22:04
 */
public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 初始化构建 SqlSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过 SqlSessionFactory 创建一个 SqlSession 对象
     *
     * @return SqlSession 对象
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
```

## 8、 添加 UserMapper 接口

```java
import com.bai.pojo.User;

import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/15 22:07
 */
public interface UserMapper {

    /**
     * 查询所有用户信息
     *
     * @return 用户信息
     */
    public List<User> getUserList();
}
```

## 9、 添加 UserMapper.xml 配置文件编写 SQL

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.bai.dao.UserMapper">

    <!-- 查询所有用户信息 -->
    <select id="getUserList" resultType="com.bai.pojo.User">
        select * from mybatis.user;
    </select>

</mapper>
```

## 10、 添加测试类测试

```java
import com.bai.dao.UserMapper;
import com.bai.pojo.User;
import com.bai.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/15 22:11
 */
public class UserMapperTest {

    @Test
    public void test01() {
        SqlSession sqlSession = null;
        try {
            // 1.获取到 SqlSession 对象那个
            sqlSession = MyBatisUtil.getSqlSession();
            // 2.通过 getMapper 方法加载对应的 UserMapper 接口
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 3.执行接口方法
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4.关闭 sqlSession 对象
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

}
```

然后通过运行我们发现一个被遗忘的问题， UserMapper文件并没有被注册到 MapperRegistry 中

`Type interface com.bai.dao.UserMapper is not known to the MapperRegistry.`

我们要解决这个问题，需要在 mybatis-config.xml 文件中注册此 Mapper 文件

![image-20201015221718573](MyBatis文档.assets/image-20201015221718573.png)

然后我们还有遇到一个初始化异常错误

`Error parsing SQL Mapper Configuration. Cause: java.io.IOException: Could not find resource com/bai/dao/UserMapper.xml`

发生这个错误是因为在 java 源码中并不能加载 xml 类型的文件，要解决这个问题，需要修改一个 pom.xml 文件，添加一些配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- 父工程 -->
    <groupId>com.bai</groupId>
    <artifactId>MyBatis-Study</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>mybatis-01</module>
    </modules>

    <dependencies>
        <!-- mysql 依赖 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>

        <!-- mybatis 依赖 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.5</version>
        </dependency>

        <!-- junit 依赖 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <!-- lombok 依赖 -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.12</version>
        </dependency>
    </dependencies>

    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <!-- 支持 properties 在 resources 包中存在 -->
                    <include>**/*.properties</include>
                    <!-- 支持 xml 文件在 resources 包中存在 -->
                    <include>**/*.xml</include>
                </includes>
                <!-- 是否过滤 : 是 -->
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <!-- 支持 properties 在 java 包中存在 -->
                    <include>**/*.properties</include>
                    <!-- 支持 xml 文件在 java 包中存在 -->
                    <include>**/*.xml</include>
                </includes>
                <!-- 是否过滤 : 是 -->
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
</project>
```

测试结果

![image-20201015222536545](MyBatis文档.assets/image-20201015222536545.png)

# CURD

- `namespace` Mapper.xml 文件的命名空间，用来寻找要实现的接口
- `id` 类似于方法名，唯一存在
- `resultType` 返回值类型，基本上就是 Class 类型或者就是8大基本类型
- `parameterType` 参数类型

## select

1. 编写接口

   ```java
   /**
        * 跟据 id 查询用户信息
        *
        * @param id 查询条件
        * @return 用户信息
        */
       public User getUserById(int id);
   ```

   

2. 编写 xml 中的 sql 语句

   ```xml
    <!-- 跟据 id 查询用户信息 -->
       <select id="getUserById" resultType="com.bai.pojo.User" parameterType="int">
           select * from mybatis.user where id = #{id};
       </select>
   ```

   

3. 测试

   ```java
   	@Test
       public void getUserById() {
           SqlSession sqlSession = MyBatisUtil.getSqlSession();
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           User user = mapper.getUserById(1);
           System.out.println(user);
           sqlSession.close();
       }
   ```

   

## insert

## update

## delete

```java
	@Test
    public void addUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(new User(4, "哈哈", "135463"));
        sqlSession.commit();    // 一定要记住提交事务
        sqlSession.close();
    }

    @Test
    public void updateUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser(new User(4, "呵呵", "66666"));
        sqlSession.commit();    // 一定要记住提交事务
        sqlSession.close();
    }

    @Test
    public void deleteUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(4);
        sqlSession.commit();
        sqlSession.close();
    }
```

**通过 sqlSession.commit 方法来提交事务**

**注意：增删改一定要提交事务，这样才会将修改的数据更新回数据库中。**

# XML 配置

mybatis-config.xml 核心配置文件。

MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息

## configuration 配置

![image-20201017112350557](MyBatis文档.assets/image-20201017112350557.png)

### properties

1. 在外部 resource 文件下创建 db.properties 数据库连接文件

   ```properties
   driver=com.mysql.jdbc.Driver
   url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&useUnicode=true&characterEncoding=utf8
   username=root
   password=123456
   ```

2. 然后通过 properties 配置到 mybatis 核心配置文件中读取

3. 测试代码是否能够正常运行

方式一：直接引入外部文件

```xml
<properties resource="db.properties"/>

<environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
```

方式二：在 properties 标签内部通过 property 属性定义属性

```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&useUnicode=true&characterEncoding=utf8
```

```xml
<properties resource="db.properties">
        <property name="user" value="root"/>
        <property name="pwd" value="123456"/>
    </properties>

    <!-- 运行环境 -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${user}"/>
                <property name="password" value="${pwd}"/>
            </dataSource>
        </environment>
    </environments>
```

当二者同时配置了相同的信息，优先读取外部的 db.properties 文件

### typeAliases 类型别名

- 类型别名可为 Java 类型设置一个缩写名字
- 意在降低冗余的全限定类名书写

方式一：通过 typeAlias 方式配置对应的类名

```xml
<typeAliases>
        <typeAlias type="com.bai.pojo.User" alias="User"/>
    </typeAliases>	
```

通过配置别名，resultType 可以直接使用别名的方式来。

```xml
 <!-- 查询所有用户信息 -->
    <select id="getUserList" resultType="User">
        select * from mybatis.user;
    </select>
```

方式二：通过 package 包名的方式来配置

```xml
<typeAliases>
        <package name="com.bai.pojo"/>
    </typeAliases>
```

resultType 直接写 Class 类名即可，小写字母开头

```xml
<!-- 查询所有用户信息 -->
    <select id="getUserList" resultType="user">
        select * from mybatis.user;
    </select>
```

如果使用了 package 的方式配置，又不想使用默认的小写字母类名，可以通过配置注解的方式来使用。

```java
@Alias("hello")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
    private String pwd;
}
```

resultType 直接使用 @Alias 注解中声明的名称即可

```xml
<!-- 查询所有用户信息 -->
    <select id="getUserList" resultType="hello">
        select * from mybatis.user;
    </select>
```

### 映射器 mappers

方式一：通过 resource 方式配置

```xml
<mappers>
	<mapper resource="com/bai/dao/UserMapper.xml"/>
</mappers>
```

方式二：通过 class 方式配置

```xml
<mappers>
	<mapper class="com.bai.dao.UserMapper"/>
</mappers>
```

方式三：通过 package 方式配置

```xml
<mappers>
	<package name="com.bai.dao"/>
</mappers>
```

注意点：接口和 xml 文件一定要同名，并且放在同一个包中。

不注意的话可能会出现各种各样的错误。

![image-20201017114400578](MyBatis文档.assets/image-20201017114400578.png)

### environments 环境配置

可以通过此配置指定多种环境的数据库连接配置

比如你现在创建了3套环境、开发、测试、生产

然后我们可以使用 default 属性来切换不同的环境

```xml
<environments default="test">
        <!-- 开发环境 -->
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
        
        <!-- 测试环境 -->
        <environment id="test">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
        
        <!-- 生产环境 -->
        <environment id="product">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
```

- 事务管理器 transactionManager

  他有两种类型，JDBC 、 MANAGED

  ![image-20201017114901934](MyBatis文档.assets/image-20201017114901934.png)

- 数据源 dataSource

  UNPOOLED、POOLED、JNDI