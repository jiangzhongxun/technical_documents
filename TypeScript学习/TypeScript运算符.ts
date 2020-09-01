/**
 * TypeScript 主要包含以下几种运算符
 * 算术运算符
 * 逻辑运算符
 * 关系运算符
 * 按位运算符
 * 赋值运算符
 * 三元/条件运算符
 * 字符串运算符
 * 类型运算符
 */

 var num1:number = 10;
 var num2:number = 2;
 var res:number = 0;

 res = num1 + num2;
 console.log('加:' + res)
 res = num1 - num2;
 console.log('减:' + res)
 res = num1 * num2;
 console.log('乘:' + res)
 res = num1 / num2;
 console.log('除:' + res)
 res = num1 % num2;
 console.log('余:' + res)

 console.log('num1 自增运算:' + ++num1)
 console.log('num2 自减运算:' + --num2)

 /**
  * 关系运算符
  * ==
  * != 
  * >
  * <
  * >=
  * <=
  */
var num1:number = 5;
var num2:number = 9;
 
console.log("num1 的值为: "+num1); 
console.log("num2 的值为:"+num2);
 
var res2 = num1>num2 
console.log("num1 大于n num2: "+res2)
 
res2 = num1<num2 
console.log("num1 小于 num2: "+res2)  

res2 = num1>=num2 
console.log("num1 大于或等于  num2: "+res2)
 
res2 = num1<=num2
console.log("num1 小于或等于 num2: "+res2)  
 
res2 = num1==num2 
console.log("num1 等于 num2: "+res2)  
 
res2 = num1!=num2  
console.log("num1 不等于 num2: "+res2)

/**
 * 逻辑运算符
 * && and
 * || or
 * !  not
 */
var avg:number = 20; 
var percentage:number = 90; 
 
console.log("avg 值为: "+avg+" ,percentage 值为: "+percentage);
    
var res3:boolean = ((avg>50)&&(percentage>80)); 
console.log("(avg>50)&&(percentage>80): ",res3);
 
var res3:boolean = ((avg>50)||(percentage>80)); 
console.log("(avg>50)||(percentage>80): ",res3);
 
var res3:boolean=!((avg>50)&&(percentage>80)); 
console.log("!((avg>50)&&(percentage>80)): ",res3);