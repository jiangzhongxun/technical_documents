# 1. Spring 2020/10/25

## 1.1 简介

spring官网：https://spring.io/

spring Framework 核心框架文档：https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/

spring maven仓库：

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.9.RELEASE</version>
</dependency>
```



## 1.2 Spring 的优点

- Spring 是一个开源免费的框架（容器）
- Spring 是一个轻量级、非入侵式的框架
- 控制反转（IOC）、面向切面编程（AOP）
- 支持事务的处理、对框架整合的支持

***总结：Spring 就是一个轻量级的控制反转（IOC）和面向切面编程（AOP）的框架***



## 1.3 Spring 的组成

![1](Spring文档.assets/2e2eb9389b504fc245d07093e5dde71191ef6d9d.jpg)



# 2. Hello Spring

开始编写第一个 Spring Demo

1. 创建一个普通的 maven 项目

2. 添加 spring 依赖

   ```xml
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-webmvc</artifactId>
       <version>5.2.9.RELEASE</version>
   </dependency>
   ```

3. 删除 maven 项目的 src 文件夹

4. 将此项目作为父项目，在其里面创建一个个的测试demo，创建子 Module 

   ![image-20201025111902551](Spring文档.assets/image-20201025111902551.png)

   ![image-20201025112029018](Spring文档.assets/image-20201025112029018.png)

5. 创建 Hello 类

   ```java
   /**
    * @author: 南独酌酒 <211425401@126.com>
    * @date: 2020/10/25 11:21
    */
   public class Hello {
       private String str;
   
       public String getStr() {
           return str;
       }
   
       public void setStr(String str) {
           this.str = str;
       }
   
       @Override
       public String toString() {
           return "Hello{" +
                   "str='" + str + '\'' +
                   '}';
       }
   }
   ```

   

6. 在 resource 目录下创建 Spring 核心配置文件，取名为 beans.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <!--
       在 Spring 配置文件中添加 Hello 类的配置
       bean 就等于 Hello 对象
       id = 变量名 hello
       class 引用类的位置
       property 是给 Hello 类中的属性 str 赋值为 Hello Spring!!!
       ==================================================
       下面的意思就像在 Java 中创建对象一样
       Hello hello = new Hello();
       hello.setStr("Hello Spring!!!");
       =================================================
       只不过我们只是将创建对象的步骤交给了 Spring 容器来帮我们做
       实现了代码之间的解耦，这就是 IOC 的设计理念
       -->
       <bean id="hello" class="com.bai.pojo.Hello">
           <property name="str" value="Hello Spring!!!"/>
       </bean>
   
   </beans>
   ```

   

7. 测试

   首先在父项目的 pom.xml 文件中添加 junit 依赖，方便单元测试

   ```xml
   		<!-- 添加 junit 依赖 -->
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.13</version>
           </dependency>
   ```

   ```java
   	@Test
       public void test01() {
           ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
           Hello hello = (Hello) context.getBean("hello");
           System.out.println(hello.toString());
       }
   ```

   测试结果

   ![image-20201025113526791](Spring文档.assets/image-20201025113526791.png)

***到了现在，我们彻底不用在程序中去改动代码了，要实现不同的操作，只需要在 xml 配置文件中进行修改，所谓的 IOC,一句话搞定：对象由 Spring 来创建、管理、装配***

# 3. IOC 创建对象的方式

1. **默认通过类的无参构造创建对象**

2. 通过类的带参构造创建对象分为三种方式

   - 通过下标创建对象

     ```xml
     <!-- 方式一：通过下标的方式创建对象 -->
         <bean id="hello" class="com.bai.pojo.Hello">
             <constructor-arg index="0" value="哪吒"/>
         </bean>
     ```

     

   - 通过 type 类型创建对象

     ```xml
     <!-- 方式二：通过 type 类型创建对象 -->
         <bean id="hello" class="com.bai.pojo.Hello">
             <constructor-arg type="java.lang.String" value="孙大圣"/>
         </bean>
     ```

   - 通过 name 的方式创建对象

     ```xml
     <!-- 方式三：通过 name 的方式创建对象 -->
         <bean id="hello" class="com.bai.pojo.Hello">
             <constructor-arg name="str" value="郭靖"/>
         </bean>
     ```

***总结：在配置文件加载的时候，容器中管理的所有都对象都已经初始化了！***

# 4. Spring 配置说明

## 4.1 alias 配置

```xml
<!-- alias 用于配置别名，配置了别名后，原本的 hello 和现在的 hello2 都可以使用 -->
<alias name="hello" alias="hello2"/>
```

## 4.2 bean 配置

```xml
<!--
    id 就是 bean 的唯一限定名
    class 就是 bean 引用的对象
    name 是别名，比 alias 更为高级，可以配置多个别名，别名之间也可以通过逗号，空格，分号分隔等等...
    -->
<bean id="hello" class="com.bai.pojo.Hello" name="h1,h2 h3;h4">
        <property name="str" value="李逵"/>
    </bean>
```

## 4.3 import 配置

import 配置用于引入其他 xml 中配置的信息

一般用于团队开发，假设现在有三个人开发此项目

员工 A：创建了 springBeanA.xml

员工 B：创建了 springBeanB.xml

员工 C：创建了 springBeanC.xml

这时由于三个人自己写的 xml 内容都不一样，可以通过 Import 配置来将三个人开发的内容引入到一个总的配置文件中去，一般总配置文件命名都为：applicationContext.xml

```xml
<import resource="springBeanA.xml"/>
<import resource="springBeanB.xml"/>
<import resource="springBeanC.xml"/>
```

***注意：就算三个 bean.xml 文件中有相同的数据时，取相同的 bean 时，也会随机取出其中一个 bean 用来使用的，并不会发生报错等情况！***