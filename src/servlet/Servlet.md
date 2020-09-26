# Servlet学习笔记

## 1、Servlet的实现

实现Servlet实现流程

* 1.创建 Java EE 项目

* 2.创建普通Java类

* 3.实现 Servlet 接口。

* 4.实现接口中的抽象方法。

* 5.配置Servlet  / 设置注解（资源路径），指定访问路径

  * **patten(URL)**—> **name** —> **class**

  ```jsp
  <servlet>
      <servlet-name>Servlet名字1</servlet-name>
      <servlet-class>servlet.Servlet_1</servlet-class>
  </servlet>
  <servlet-mapping>
  	<servlet-name>Servlet名字1</servlet-name>
      <url-pattern>/Servlet_1</url-pattern>
  </servlet-mapping>
  ```

  * @WebServlet()

    ```java
    @WebServlet("/Servlet_1")
    @WebServlet("/path1/path2//Servlet_1")
    @WebServlet("/path1/path2/*") //*表示通配路径（通配访问路径优先级低）
    @WebServlet("Servlet.do")  //注意前面不加 '/'
    ```


## 2、Servlet的工作流程

当有请求进来的时候,会找到当前电脑的8080端口，来访问主机的Tomcat服务器。

找到服务器后，会通过请求行当中的路径，访问到具体的服务器应用。

**patten(URL)**—> **name** —> **class** 在web.xml中找到与路径相对应的Servlet。

通过全类名（包名.包名....类名）加载对应的字节码文件加载进内存。

服务器就会通过反射创建一个对应的Servlet对象。


此时request对象，response对象就会被生成，来处理请求和响应。


## 3、Servlet的生命周期

Servlet没有main()方法，不能独立运行，它的运行完全由Servlet引擎来控制和调度。所谓生命周期是servlet容器何时创建servlet'实例、何时调用其方法进行请求的处理、何时并销毁其实例的整个过程。

Servlet类加载—>实例化—>f服务—>销毁

上述的生命周期可以通过Servlet中的生命周期方法来观察。在Servlet中有三个生命周期方法，不由用户手动调用，而是在特定的时机有容器自动调用，观察这三个生命周期方法，即可观察到Servlet的生命周期。

### 3.1.实例和初始化时机（仅一次）

当请求到达容器时，容器查找该servlet对象是否存在，如果不存在，则会创建实例并进行初始化。

**init**方法，在Servlet实例创建之后执行（证明该Servlet有实例创建了）

```jsp
<servlet>
	<!--对象的创建时间-->
	<!--参数为负数，第一次被访问时创建对象-->
    <!--参数为非负数，服务器启动时时创建对象-->
    <load-on-startup>参数</load-on-startup>
</servlet>
```

### 3.2.就绪/调用/服务阶段（多次调用）

**service**方法，每次有个请求到达某个Servlet方法时执行，用来处理请求（证明该Servlet进行服务了）

**注意事项**:

* service的执行方法存在并发问题，尽量不要定义成员变量，就算定义了成员变量，也不要对其值进行修改。

### 3.3.销毁时机（仅一次）

当容器关闭时（应用程序停止时)，会将程序中的Servlet 实例进行销毁。

**destroy**方法，Servlet实例销毁时执行（证明该Servlet实例被销毁了）

**注意事项**：

* 只有服务器正常关闭时，才会执行desTroy方法。
* destory方法在被销毁前执行，一般用于释放资源。

## 4、Tomcat与Servlet的工作流程

* 1.Web Client 向 Servlet 容器（Tomcat）发出HTTP请求。
* 2.Servlet 容器接收 WebClient 请求
* 3.Servle t容器创建一个 HttpServletRequest 对象，将 Web Client 请求的信息封装到这个对象中
* 4.Servlet 容器创建一个 HttpServletResponse 对象
* 5.Servlet 容器调用 HttpServlet 对象的 service 方法， 把 Request 与 Response 作为参数，传给 HttpServlet
* 6.HttpServlet 调用 HttpServletRequest 对象的有关方法， 获取请求信息。
* 7.HttpServlet 调用 HttpServletResponse 对象的有关方法，生成响应数据。
* 8.Servlet 容器把 HttpServlet 的响应结果传给 Web Client 

## 5、HttpServletRequest对象

* HttpServletRequest 对象：主要是用来接收客户端发送过来的请求信息，例如：请求的参数，发送的头信息，都属于客户端发来的信息，service（）方法中形参接收的就是 HttpServletRequest 接口的实例化对象，表示该对象主要应用在 HTTP 协议上，该对象是由 Tomcat 封装好传递过来的。

* HttpServletRequest 是 ServletRequest 的子接口， ServletRequest 只有一个子接口， 就是 HttpServletRequest。

   既然只有一个子接口， 为什么不将两个接口合二为一：

  * 从长远上来讲：现在主要用的协议是 HTTP 协议，但以后可能出现更多新的协议。若以后想要支持新的协议，只需要继承 ServletRequest 接口就行了。

* 在 HttpServletRequest 接口中， 虽然定义的方法很多， 但都是围绕接收客户端参数的，那这个是怎么拿到该对象呢？

  * 不需要！直接在Service方法中，由容器传入过来，而我们需要做的就是取出对象中的数据，进行分析，处理。

### 5.1.接收请求

#### 5.1.1.获取请求行信息

* `getRequestURL() `       获取客户端请求的完整URL (从 http 开始，到 ? 前)
* `getRequestURI() `        获取客户端请求的资源名称部分 (从站点名到 ? 前)

* `getQueryString()`        获取请求行中的参数部分。(从 ? 开始，到结束)
* `getMethod() `               获取请求方式( GET 和 SET)
* `getProtocol()  `             获取 Http 版本号。(HTTP/1.1)
* `getContextPath()`        获取项目的虚拟目录。

#### 5.1.2.获取请求头信息

* `String getHeader(String name); ` 通过请求头的名称获取请求头的值。

* `Enumeration\<String> getHeaderNames(); ` 获取所有的请求头名称。

* 判断agent的浏览器版本 (对于不同的浏览器有不同的兼容)

  * `request.getHeader("user-agent")`

    ```java
    String agent = request.getHeader("user-agent");
    if(agent.contains("Chrome")){
    	System.out.println("这是谷歌");
    }else if(agent.contains("Firefox")){
    	System.out.println("这是火狐");
    }
    ```

#### 5.1.3.获取请求体数据

* 只有POST请求方式，才有请求体，在请求体中封装了POST请求的请求参数。
* 步骤：
  * 1.获取流对象。
    * `BufferedReader getReader();`  获取字符输入流，只能操作字符数据。
    * `ServletInputStream getInputStream(); ` 获取自己输入流，可以操作所有类型数据。
  * 2.从流中拿数据。

### 5.2.获取请求参数

* 1.`String getParameter(String name) `  			        获取指定名称的参数。
* 2.`String[]  getParameterValues(String name)`       获取指定名称参数的所有值(一键多指)
* 3.`Enumeration\<String> getParameterNames();`   获取所有请求的参数名称
* 4.`Map<String, String[]> getParameterMap();`        获取所有参数的Map集合

### 5.3.请求乱码问题

​	由于现在的request属于接收客户端的参数，所以必然有其默认的语言编码，主要是由于在解析过程中默认使用的编码方式为IS0-8859-1(此编码不支持中文)，所以解析时一定会出现乱码。要想解决这种乱码问题，需要设置request中的编码方式，告诉服务器以何种方式来解析数据。或者在接收到乱码数据以后，再通过相应的编码格式还原。

乱码原因：
​    由于在解析过程中默认使用的编码方式为ISO-8859-1(此编码不支持中文)，所以编译时会出现乱码。

```
                             GET请求                      POST请求
Tomcat 7 及以前，             会乱码                          会乱码
Tomcat 8 及以上，            不会乱码                         会乱码。
```
无论什么版本的服务器，POST请求都会乱码。解决方法：

* `request.setCharacterEncoding("UTF-8");`

注意：

* 1.`request.setCharacterEncoding("UTF-8")` 只针对 POST 请求的乱码有效。
* 2`.new String(request.getParameter("uname").getBytes("ISO-8859-1"), "UTF-8")`针对任何请求方式。

使用方法总结：

* 第一行加`request.setCharacterEncoding("UTF-8") `解决 所有POST， Tomcat 8的GET自动解决。
* Tomcat 7  GET使用最麻烦的new `String(request.getParameter("uname").getBytes("ISO-8859-1"), "UTF-8") `挨个转译。

### 5.4.请求转发跳转

​	可以让请求从服务端到达客户端（或跳转到指定Servlet）。

* 实现方式如下，达到多个资源协同相应的效果。

  * 通过request对象获取请求转发器对象 `RequestDispatcher getRequestDispatcher(String path);`

    `request.getRequestDispatcher(String path).forward(request, response);` //完成转发

**请求转发的特点**

* 请求转发，是一种**服务器内的行为**，域行为。只能转发到**当前服务器**的内部资源中
* 当客户端请求到达后，服务器进行转发，此时会将请求对象进行保存，地址栏中的 **URL 地址不会改变** 。
* 得到响应后，服务器端再将响应发送给客户端，**从始至终只有一个请求发出** 。

### 5.5.共享数据

* 域对象：一个有作用范围的对象，可以在范围内共享数据。
* request域：代表一次请求的范围，一般用于请求多个资源中的共享数据。
* 方法：
  * `void setAttribute(String name, Object obj); `存储数据
  * `Object getAttribute(String name);  `通过键获取值
  * `void removeAttribute(String name);  通过键移除键`值对

## 6.HttpServletResponse对象

功能：设置响应消息

* 1.设置相应行  HTTP/1.1 200 OK

  * 设置状态码：`setStatus(int sc)`

* 2.设置响应头

  设置一对键值对

  `setHeader(String name, String value)`

  `setIntHeader(String name, int value)`

* 3.设置响应体：

  * 使用步骤

    1.获取输出流

    * **字符输出流**：`PrintWriter getWriter()`
    * **字节输出流**：`ServletOutputStream getOutputStream`

    2.使用输出流，将输出流输出到客户端浏览器。

## 7.重定向和转发

**方法一**：

* `response.sendRedirect(String URI);`  

**方法二**：

* 设置状态码为302

  `response.setStatus(302);`

* 设置响应头location

  `response.setHeader("location",String URI)`

### 7.1.redirect重定向的特点

* 地址栏发生变化
* 重定向可以访问其他服务器下的资源
* 重定向是两次请求。(两个Request对象，不能共享数据)

### 7.2.forword转发的特点

* 转发地址栏路径不变。
* 转发只能访问当前服务器下的资源。
* 转发只是一次请求。(共享一个Request对象，可以共享数据)