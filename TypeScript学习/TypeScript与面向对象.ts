/**
 * 定义了一个类 Site，该类中有一个方法 name() 
 */
class Site {
    name():void {
        console.log("Runoob")
    }
}
var obj = new Site();
obj.name();     

/**
 * 通过 tsc 命令转换为 JavaScript 代码
 * 执行结果为 Runoob
 */