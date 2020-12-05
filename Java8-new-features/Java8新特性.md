# 1. Lambda 表达式

Lambda 是一个匿名函数，我们可以把 Lambda 表达式理解为是一段可以传递的代码（将代码像数据一样进行传递），可以写出更简洁、更灵活的代码，作为一种更紧凑的代码风格，使 Java 的语言表达能力得到了提升。



Lamdba 表达式的基础语法：Java8 中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符，箭头操作符将 Lambda 表达式拆分为两部分：

1. 左侧：Lambda 表达式的参数列表
2. 右侧：Lambda 表达式中所需列表的功能，即 Lambda 体



```java
语法格式一：无参数，无返回值
    () -> System.out.println("hello lambda!");

语法格式二：有一个参数，并且无返回值
    (x) -> System.out.println(x);
	System.out::println;
语法格式三：有两个以上的参数，并且有返回值，Lambda 体中有多条语句
    Comparator<Integer> comparator = (o1, o2) -> {
            System.out.println("函数式编程");
            return Integer.compare(o1, o2);
        };
语法格式四：在格式三基础上，若只有一条语句，可以省略大括号和 return
    Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
	Comparator<Integer> comparator = Integer::compare;
语法格式五：Lambda 表达式的参数列表的数据类型可以省略不写，因为 JVM 编译器通过上下文推断出，数据类型，即“类型推断”
    
    
总结：
    上联：左右遇一括号省
    下联：左侧推断类型省
    横批：能省则省
    
Lambda 表达式需要 “函数式接口” 的支持
函数式接口：接口中只有一个抽象方法的接口，称为函数式接口，可以使用注解 @FunctionalInterface 修饰，可以检查是否是函数式接口
```



## 1.1 Java8 内置的四大核心函数式接口

- `Consumer<T>:消费型接口`
  - `void accept(T t);`
- `Supplier<T>:供给型接口`
  - `T get();`
- `Function(T, R):函数型接口`
  - `R apply(T t);`
- `Predicate<T>:断言型接口`
  - `boolean test(T t);`

测试代码

```java
public class TestLambda4 {
    public static void main(String[] args) {
        test4();
    }

    // Predicate<T> 断言型接口案例 ->

    public static void test4() {
        List<String> list = Arrays.asList("Android", "IOS", "MacOS", "Linux", "windows");
        filterStr(list, (str) -> str.length() < 5).forEach(System.out::println);
    }

    public static List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                strList.add(s);
            }
        }
        return strList;
    }

    // Function<T, R> 函数型接口案例 ->

    public static void test3() {
        System.out.println(strHandler("我是小刚蛋", str -> str.substring(2, 5)));
        System.out.println(strHandler("abcdefg", String::toUpperCase));
    }

    public static String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    // Supplier<T> 供给型接口案例 ->

    public static void test2() {
        getNumList(10, () -> (int) (Math.random() * 100)).forEach(System.out::println);
    }

    public static List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    // Consumer<T> 消费性接口案例 ->

    public static void test1() {
        happy(1000.00, m -> System.out.println("我每天都需要消费" + m + "元"));
    }

    public static void happy(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }
}
```

## 1.2 方法引用

若 Lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”（可以理解为方法引用是Lambda表达式的另外一种表现形式）

主要有三种语法：

1. 对象：：实例方法名
2. 类：：静态方法名
3. 类：：实例方法名

注意：

1. Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型保持一致。
2. 若 Lambda 参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用 ClassName::method

```java
	// 类::实例方法名 ->
    public static void test4() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        BiPredicate<String, String> biPredicate1 = String::equals;
        System.out.println(biPredicate1.test("hello", "hello"));
    }

    // 类::静态方法名 ->
    public static void test3() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> comparator1 = Integer::compare;
    }

    // 对象::实例方法名 ->
    public static void test1() {
        /*
         * 这两句代码表达意思相同，都是输出语句
         * 使用方法引用的前提是保持和 Consumer 方法一致
         *   todo Consumer 中的 accept 方法参数类型和无返回值
         *   todo 而 System.out 类中的 println 方法参数类型也是 String 和 无返回值
         * 在二者方法的参数类型和返回值类型一致的情况下，可以使用方法引用的方式来替换 Lambda 表达式
         * */
        Consumer<String> consumer = str -> System.out.println(str);
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("abc");
    }

    public static void test2() {
        Student student = new Student("bai", 18, 3333.33);
        Supplier<String> supplier = () -> student.getName();
        System.out.println(supplier.get());
        // ==========方法引用改造上面的代码================
        Supplier<Integer> supplier1 = student::getAge;
        System.out.println(supplier1.get());
    }
```



## 1.3 构造器引用

格式：ClassName::new

需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！

```java
	// 构造器引用
    public static void test5() {
        Supplier<Student> studentSupplier = () -> new Student();
        System.out.println(studentSupplier.get());
        // 构造器引用方式
        Supplier<Student> studentSupplier1 = Student::new;
        System.out.println(studentSupplier1.get());
    }
```

## 1.4 数组引用

格式：Type[]::new

```java
	// 数组引用
    public static void test6() {
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] apply = function.apply(10);
        System.out.println(apply.length);

        Function<Integer, String[]> function1 = String[]::new;
        String[] apply1 = function1.apply(10);
        System.out.println(apply1.length);
    }
```

# 2. Stream 流

流（Stream）到底是什么呢?

是数据渠道，用于操作数据源（集合，数组等）所生成的元素序列。

***集合讲的是数据，流讲的是计算***

注意：

1. Stream 自己不会存储元素
2. Stream 不会改变源对象，相反，它们会返回一个持有结果的新 Stream
3. Stream 操作会延迟执行的，这意味着它们会等到需要结果的时候才执行

## 2.1 Stream 的操作步骤

1. 创建 Stream

   一个数据源（比如：集合、数组），获取一个流

2. 中间操作

   一个中间操作，对数据源的数据进行处理

3. 终止操作（终端操作）

   一个终止操作，执行中间操作链，并产生结果

![image-20201130205646776](Java8新特性.assets/image-20201130205646776.png)

## 2.2 创建 Stream 的四种方式

```java
	/**
     * 创建 Stream 流的四种方式
     */
    private static void test1() {
        // 1.可以通过 Collection 系列集合创建 stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        // 2.可以通过 Arrays 中的静态方法 stream() 获取数组流
        Student[] students = new Student[10];
        Stream<Student> stream2 = Arrays.stream(students);

        // 3.可以通过 Stream 类中的静态方法 of() 获取流
        Stream<String> stream3 = Stream.of("java", "python", "javascript");

        // 4.创建无限流
        // 可以通过 iterate() 迭代方式获取流
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(5).forEach(System.out::println);

        // 可以通过 generate() 生成方法获取流
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
```

## 2.3 Stream 的中间操作

多个中间操作可以连接起来形成一个流水线，除非流水线上触发了终止操作，否则***中间操作不会执行任何的处理***，而在***终止操作时一次性全部处理，称为“惰性求值”或者称为延时处理。***

### 筛选与切片

|        方法         |                             描述                             |
| :-----------------: | :----------------------------------------------------------: |
| filter(Predicate p) |                接收 Lambda,从流中排除某些元素                |
|     distinct()      | 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素 |
| limit(long maxSize) |               截断流，使其元素不超过给定数量。               |
|    skip(long n)     | 跳过元素，返回一个扔掉了前 n 个元素的流，若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补 |

### 映射

| 方法      | 描述                                                         |
| --------- | ------------------------------------------------------------ |
| map()     | 接收 Lambda，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。 |
| flatMap() | 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流 |

map 和 flatMap 的区别类似于这种：

map: { {aa,bb} , {cc,dd} }

flatMap: { aa,bb,cc,dd }

有点类似于 List 集合中的 add 和 addAll 方法

![image-20201130221224533](Java8新特性.assets/image-20201130221224533.png)

![image-20201130221320335](Java8新特性.assets/image-20201130221320335.png)

### 排序

| 方法                   | 描述     |
| ---------------------- | -------- |
| sorted()               | 自然排序 |
| sorted(Comparator com) | 定制排序 |

### 查找与匹配

| 方法      | 描述                     |
| --------- | ------------------------ |
| allMatch  | 检查是否匹配所有元素     |
| anyMatch  | 检查是否至少匹配一个元素 |
| noneMatch | 检查是否没有匹配所有元素 |
| findFirst | 返回第一个元素           |
| findAny   | 返回当前流中的任意元素   |
| count     | 返回流中元素的总个数     |
| max       | 返回流中最大值           |
| min       | 返回流中最小值           |

### 规约

| 方法                                                         | 描述                                   |
| ------------------------------------------------------------ | -------------------------------------- |
| reduce(T identity, BinaryOperator)    /    reduce(BinaryOperator)     /    BinaryOperator 是一个二元运算函数 | 可以将流中元素反复结合起来，得到一个值 |

### 收集

| 方法                 | 描述                                                         |
| -------------------- | ------------------------------------------------------------ |
| collect(Collector c) | 将流转换为其他形式，接收一个 Collector 接口的实现，用于给 Stream 中元素做汇总的方法 |

Collector 接口中方法的实现决定了如何对流执行收集操作（如收集到 List，Set，Map），但是Collectors 实用类提供了很多静态方法，可以方便地创建常见收集器实例。

# 3. 并行流与顺序流

并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。

Java8 中将并行进行了优化，我们可以很容易的对数据进行并行操作，Stream API 可以声明性地通过 parallel() 与 sequential() 在并行流与顺序流之间进行切换

# 4. Optional 类

Optional<T> 类(java.util.Optional) 是一个容器类，代表一个值存在或者不存在，原来用 null 表示一个值不存在，现在用 Optional 可以更好的表达这个概念，并且可以避免空指针异常。

常用方法：

1. Optional.of(T t)	创建一个 Optional 实例
2. Optional.empty()   创建一个空的 Optional 实例
3. Optional.ofNullable(T t)    若 t 不为 null，创建 Optional 实例，否则创建空实例
4. isPresent()  判断是否包含值
5. orElse(T t)   如果调用对象包含值，返回该值，否则返回 t
6. orElseGet(Supplier s)   如果调用对象包含值，返回该值，否则返回 s 获取的值
7. map(Function f)    如果有值对其处理，并返回处理后的 Optional，否则返回 Optional.empty()
8. flatMap(Function mapper)   与 map 类似，要求返回值必须是 Optional