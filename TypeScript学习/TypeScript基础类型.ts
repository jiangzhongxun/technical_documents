// 任意类型 any
// 数字类型 number
let binaryLiteral: number = 0b1010; // 二进制
let octalLiteral: number = 0o744; // 八进制
let decLiteral: number = 6; // 十进值
let hexLiteral: number = 0xf00d; // 十六进制

// 字符串类型 string
let title: string = "Runoob";
let years: number = 5;
let words: string = `您好，今年是 ${title} 发布 ${years + 1} 周年`;

// 布尔类型 boolean
let flag: boolean = true;

// 数组类型
let arr: number[] = [1,2];
let arr2: Array<number> = [1,2];

// 元组 元组类型用来表示已知元素数量和类型的数组,各元素的类型不必相同,对应位置的类型需要相同
let x: [string, number]
x = ['Runnob', 1]; // 运行征程
// x = [1, 'Runnob']; // 报错
console.log(x[0]); // 输出 Runnob

// 枚举 enum 枚举类型用于定义数值集合
enum Color {RED,GREEN,BLUE};
let c: Color = Color.BLUE;
console.log(c); // 输出2

// void 用于标识方法返回值的类型,表示该方法没有返回值
function hello(): void {
    alert("Hello Runnob");
}

// null 表示对象缺失
// undefined 用于初始化变量为一个未定义的变量
// never 是其他类型(包括 null 和 undefined)的子类型,代表从来不会出现的值
// TypeScript 和 JavaScript 没有整数类型
// Any 类型,任意值是 TypeScript 针对编程时类型不明确的变量使用的一种数据类型,他常用于以下三种情况
let x1: any = 1; // 数字类型
x1 = 'I am who I am'; // 字符串类型
x1 = false; // 布尔类型
// 改写现有代码时，任意值允许在编译时可选择地包含或移除类型检查
let x2: any = 4;
x2.ifItExists(); 
x2.toFixed();
// 定义存储各种类型数据的数组时
let arrayList: any[] = [1, false, 'fine'];
arrayList[1] = 100;

// Null 和 Undefined
/**
 * 在 JavaScritp 中 null 表示什么都没有
 * null 是一个只有一个值的特殊类型,表示一个空对象引用
 * 用 typeof 检测 null 返回是 object
 * 
 * 在 JavaScript 中 undefined 是一个没有设置值的变量
 * typeof 一个没有值的变量会返回 undefined
 * Null 和 Undefined 是其他任何类型（包括 void）的子类型，可以赋值给其它类型，如数字类型，此时，赋值后的类型会变成 null 或 undefined。而在TypeScript中启用严格的空校验（--strictNullChecks）特性，就可以使得null 和 undefined 只能被赋值给 void 或本身对应的类型
 */ 

// 启动 --strictNullChecks
let x3: number;
x3 = 1; // 运行正确
x3 = undefined; // 运行错误
x3 = null; // 运行错误

// 上面的例子中变量 x 只能是数字类型。如果一个类型可能出现 null 或 undefined， 可以用 | 来支持多种类型
// 启动 --strictNullChecks
let x4: number | null | undefined;
x4 = 1; // 运行正确
x4 = undefined; // 运行正确
x4 = null; // 运行正确

/**
 * never 是其他类型(包括 null 和 undefined)的子类型,代表从来不会出现的值,这意味着声明为 never 类型的变量只能被 never 类型所赋值
 * 在函数中它通常表现为抛出异常或无法执行到终止点
 */
let a: never;
let b: number;

// 运行错误,数字类型不能转为 never 类型
// a = 123;
// 运行正确, never 类型可以赋值给 never 类型
a = (() => { throw new Error('exception')})();
// 运行正确, never 类型可以赋值给数字类型
b = (() => { throw new Error('exception')})();
// 返回值为 never 的函数可以是抛出异常的情况
function error(message: string): never {
    throw new Error(message);
}
// 返回值为 never 的函数可以是无法被执行到终止点的情况
function loop(): never {
    while(true) {}
}