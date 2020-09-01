/**
 * 变量是一种使用方便的占位符,用于引用计算机内存地址
 * TyepScript 变量的命令规则
 * 1. 变量名称可以包含数字和字母
 * 2. 除了下划线_和美元$符号,不能包含其他特殊字符,包括空格
 * 3. 变量名不能以数字开头
 * 变量使用前必须先声明,我们可以使用 var 来声明变量
 */

// var [变量名] : [类型] = 值;
// var uname1:string = "Runnob";
// var [变量名] : [类型]; 没有初始值,变量值会设置为 undefined
// var uname2:string;
// var [变量名] = 值; 不设置类型,可以是任意类型
// var uname3 = "Runnob";
// var [变量名]; 没有设置类型和初始值,类型可以是任意类型,默认值是 undefined
// var uname4;

var uname:string = "Runnob";
var score1:number = 50;
var score2:number = 42.50;
var sum = score1 + score2;
console.log('名字:' + uname);
console.log('第一个科目成绩:' + score1)
console.log('第二个科目成绩:' + score2)
console.log('总成绩:' + sum)

/**
 * 类型断言 Type Assertion
 * 类型断言可以用来手动指定一个值的类型,既允许变量从一种类型更改为了另一种类型
 * 语法格式: 
 * 1. <类型>值
 * 2. 值 as 类型
 */
var str = '1';
var str2:number = <number> <any> str;
console.log(str2);

/**
 * 类型推断
 * 当类型没有给出时,TypeScript 编译器利用类型推断来推断类型
 * 如果由于缺乏声明而不能推断出类型,那么它的类型被视作默认的动态 any 类型
 */
var num = 2; // 类型推断为 number
console.log('num 变量的值为' + num)
// num = '12'; // 编译错误
// console.log(num)

/**
 * 变量作用域指定了变量定义的位置
 * 程序中变量的可用性由变量作用域决定
 * TypeScript 有以下几种作用域
 * 1. 全局作用域:全局变量定义在程序结构的外部,他可以在你代码的任意位置使用
 * 2. 类作用域:这个变量也可以称为字段,类变量声明在一个类里头,但在类的方法外面,该变量可以通过类的对象来访问,类变量也可以是静态的,静态的变量可以通过类名直接访问
 * 3. 局部作用域:局部变量只能在声明它的一个代码块中使用
 */
var gloabl_num = 12; // 全局变量
class Numbers {
    num_val = 13; // 实力变量
    static sval = 10; // 静态变量

    storeNum():void {
        var local_num = 14; //局部变量
    }
}
console.log('全局变量为:' + gloabl_num)
console.log(Numbers.sval); // 静态变量
var obj = new Numbers();
console.log('实例变量:' + obj.num_val);