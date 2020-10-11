# JSP学习

## 1.指令：

* 作用： 用于配置JSP页面， 导入资源文件。

* 格式：`<%@ 指令名称 属性名1=属性值1 属性名2=属性值2 ... %>`

  指令名称：

  * **page** ： 配置JSP页面。

    属性

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

  * **taglib** ： 导入资源。

    `<%@ taglib prefix="c" uri="hhttp://java.sun.com/jsp/jstl/core" %>`

    * prefix： 定义前缀，用户自定义的。

  * **include** : 导入页面的资源文件。

    比如很多页面的头部内容展示都是一样的。

    则可以单独写个头部，其他页面都导入。

    `<%@ include file="top.jsp" %>`

## 2，注释

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