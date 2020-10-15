# JSP语法

## 1.指令：

* 作用： 用于配置JSP页面， 导入资源文件。

* 格式：`<%@ 指令名称 属性名1=属性值1 属性名2=属性值2 ... %>`

  指令名称：

  * **page** ： 配置JSP页面。

    * contentType:

      等同于： `response.contentType(); `

      设置Mime类型，及字符集， 

      设置JSP文件的编码(只能是高级的开发工具， 低级的工具需要设置pageEncodeing属性，来设置编码)

    * language:

      只支持 java 值。

    * buffer：

      设置字符输出流的缓存区大小，默认8kb。

    * import:

      导入java包 ， (推荐一条导包语句使用一条指令)。

    * errorPage:

      当前页面发生异常后， 会自动跳转到指定的错误页面。

    * isErrorPage：

      标识当前页面是否是错误提示页面。

      * true : 可以使用内置对象exception。

        `String msg = exception.getMessage();`

      * false。默认值， 不能使用exception 对象。
    
    * isELIgnored:

      * 默认false：不忽略EL表达式。

      * true：忽略EL表达式，

  * **taglib** ： 导入资源。

    `<%@ taglib prefix="c" uri="hhttp://java.sun.com/jsp/jstl/core" %>`

    * prefix： 定义前缀，用户自定义的。

  * **include** : 导入页面的资源文件。

    比如很多页面的头部内容展示都是一样的。
  
    则可以单独写个头部，其他页面都导入。
  
    `<%@ include file="top.jsp" %>`

## 2.注释

* 1.html注释：`<!--注释内容-->`  

  只能注释html的代码块。

* 2.jsp注释：`<%----%>`

  可以注释所有内容。

在浏览器查看源码时， 能看见html注释内容， 看不到jsp注释内容。

## 3.内置对象

在jsp页面中不需要创建，直接使用的对象

一共有9个：

PageConext						 pageContext		  当前页面共享数据（可以获取其他8个对象）

HttpServletRequest	  	  request				  一次请求访问的多个资源共享数据

HttpSession					 	session				   一次会话中的多个请求间共享数据

ServletContext				    application			所有用户间共享数据（服务器唯一对象）

HttpServletResponse		response				响应对象

Object							  	page						代表当前页面(Servlet)， 类似于 this。

JspWriter  						   out						   输出对象，数据输出到页面上

ServletConfig                     config					   Servlet的配置对象

Throwable						  exception 			   异常对象，声名 `isErorPage="true"`， 才会存在。



# MVC开发模式

M：model  模型  **————JavaBean**

* 封装业务逻辑，进行业务逻辑操作。

V： view  视图    **————Jsp**

* 进行展示数据操作。

C： controller  控制器    **————Servlet**

* 1.获取客户端输入，
* 2.调用模型，进行业务操作。
* 3.交给视图展示。

**优缺点** 

* **优点**：
  * 1.耦合性低，方便维护，利于分工协作。
  * 2.重用性高。
* 缺点：
  * 增加了项目结构的复杂程度，对开发人员的要求较高。

# EL表达式

Expression Language 表达式语言。   简化jsp页面中 java 代码的编写。

语法：`${表达式}`

JSP默认支持EL表达式。想要原格式输出，就在最前面加入反斜杠。

## 1.运算：

* 运算符：

  * 算数运算符  +   -   *    /(div)     %(mod)

  * 比较运算符 >   <   >=    <=    ==   !=

  * 逻辑运算符  &&(and)  ||(or)   !(not)

  * 空运算符  empty——判断字符串，集合，数组对象是否为null， 或者长度为0

    ${empty list}

## 2.获取值

* EL表达式只能从域对象中获取值

* 语法：

  * 1. ${域名称.键名}  从指定域中获取指定键的值。

    域名称:

    * pageScope               -->pageContext
    * requestScope          -->request
    * sessionScope           -->session
    * applicationScope    -->application(ServletContext)

  * 2. ${键名}  依次从最小的域中查找是否有该键对应的值。

  * 3. 获取对象：**`${域名称.键名称.属性}`**

     必须是JavaBean对象，会识别getAbcd方法，获取abcde属性。

    `requestScope.key.abdc`

    想要格式化输出，就写一个虚假的get方法（逻辑视图），返回数据即可。

  * 4. 获取List集合的值：**`${域.键名称[索引值]}` **

    `${域名称.键名称}`//输出整个集合

    `${域名称.键名称[索引值]}`  //输出集合的第n个值

    `${域名称.键名称[索引值].属性}`

  * 5. 获取Map集合的值：

       ​	**`${域名称.键名称.map键}`** 或者**`${域名称.键名称[map键]}`**

# JSTL

用标签替换Jsp页面上的Java代码。

1. 导入 jstl 相关的 jar 包
2. 引入标签库：taglib指令：`<%@ taglib prefix="c" uri="hhttp://java.sun.com/jsp/jstl/core" %>` //约定俗成的叫c
3. 使用标签。

## 1.if

````jsp
<c:if test="${not empty list}"> <%--test属性接收boolean表达式--%>
	遍历集合
</c:if>
````

## 2.choose， when & otherwise

```jsp
<c:choose>
	<c:when test="${number == 1}">星期一</c:when>
	<c:when test="${number == 2}">星期二</c:when>
	<c:when test="${number == 3}">星期三</c:when>
	<c:when test="${number == 4}">星期四</c:when>
	<c:when test="${number == 5}">星期五</c:when>
	<c:when test="${number == 6}">星期六</c:when>
	<c:when test="${number == 7}">星期七</c:when>
</c:choose>
```

## foreach

属性：

* begin： 开始值
* end： 结束值
* var： 临时变量
* step： 步长
* varStatus: 循环状态对象
  * index：容器中元素的索引（从0开始）
  * count： 循环次数。（从1开始）

* items：容器对象
* var：容器中元素的临时变量

```jsp
<%--常用的遍历--%>
<c:foreach start="1" end="10" val="i" step="2" varStatus="s">
	${i}
    ${s.index}
    ${s.count}
</c:foreach>
```

```jsp
<%--List遍历--%>
<c:if test="${not empty list}">
    <table>
       <tr>
           	<th>排名</th>
            <th>姓名</th>
            <th>学号</th>
            <th>年龄</th>
            <th>班级</th>
        </tr> 
        <c:foreach item="${list}" var="element" varStatus="s">
            <c:if test="${s.count % 2 == 0}">
                <tr bgcolor="pink">
                    <td>${s.count}</td>
                    <td>${element.uname}</td>
                    <td>${elemnet.uid}</td>
                    <td>${element.uold}</td>
                    <td>${element.class}</td>
                </tr>
            </c:if><c:if test="${s.count % 2 == 0}">
                <tr bgcolor="blue">
                    <td>${s.count}</td>
                    <td>${element.uname}</td>
                    <td>${elemnet.uid}</td>
                    <td>${element.uold}</td>
                    <td>${element.class}</td>
                </tr>
            </c:if>
        </c:foreach>
    </table>
</c:if>
```

