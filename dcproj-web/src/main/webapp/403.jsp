<%--
  Created by IntelliJ IDEA.
  User: zhaodongchao
  Date: 2017/5/8 10:09
  telphone:17621008632
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
403
<script type="text/javascript" >
/*  function f1(base) {
      return function (max) {
          var total = 0 ;
          for (var i=0 ; i<= max ; i++){
              total += i ;
          }
          return total + base ;
      }
  }
 var a =  f1(2);
 alert(a(3)) ;*/
var f =function (id) {
    return  (function (id) {
            alert(id) ;
        }
    )(id)
};
//f(123);
var person = {
    name :"",
    _age : 22,
    work :function () {
        console.log("------> work") ;
    },
    get age(){
       if (this._age == 0 || this._age > 150 ){
           throw new Error("invalidate agruments !");
       }
       return this._age ;
    },
    set age(val){
       this._age = val ;
    },
    address:{
        home : "macheng",
        city : "hubei"
    }

} ;
console.log(person.name);
console.log(person.work());
console.log(person.age);
person.age = 30 ;
console.log(person.age);
var home  = person && person.address && person.address.home ;
console.log(home) ;
var oo = {};

//
  function Persion() {
      this.name = "dongchao";
      this.age = 22 ;
  }
  var p1 = new Persion();
  var p2 = new Persion();
  //只有函数才会有prototype 对象才会有__proto__ (IE没有这个属性) ,__proto__指向父类的prototype
  Persion.prototype.clazz = "person" ;
</script>
</body>
</html>
