# Servlet学习笔记

## 1、Servlet的实现

实现Servlet实现流程
* 1．创建普通Java类
* 2．实现Servlet的规范，继承HttpServlet类
* 3．重写service方法，用来处理请求
* 4．设置注解，指定访问路径

## 2、Servlet的工作流程

当有请求进来的时候,会找到当前电脑的8080端口，来访问主机的服务器。
找到服务器后，会通过请求行当中的路径，访问到具体的应用，
在应用中找到与之相对应的路径（注解）
此时就会找到那个被访问的Servlet。
如果该Servlet是被第一次访问，服务器就会创建一个对应的Servlet。
此时request对象，response对象就会被生成，来处理请求和响应。


## 3、Servlet的生命周期

Servlet没有main()方法，不能独立运行，它的运行完全由Servlet引擎来控制和调度。所谓生命周期是servlet容器何时创建servlet'实例、何时调用其方法进行请求的处理、何时并销毁其实例的整个过程。

Servlet类加载—>实例化—>f服务—>销毁

上述的生命周期可以通过Servlet中的生命周期方法来观察。在Servlet中有三个生命周期方法，不由用户手动调用，而是在特定的时机有容器自动调用，观察这三个生命周期方法，即可观察到Servlet的生命周期。

### 3.1.实例和初始化时机（仅一次）

当请求到达容器时，容器查找该servlet对象是否存在，如果不存在，则会创建实例并进行初始化。

**init**方法，在Servlet实例创建之后执行（证明该Servlet有实例创建了）

```java
public void init(ServletConfig config) throws ServletException{
	System.out.println("实例创建了。。。");
}
```

### 3.2.就绪/调用/服务阶段（多次调用）

有请求到达容器容器调用servlet对象的service()方法,处理请求的方法在整个生命周期中可以被多次调用HttpServlet的service()方法，会依据请求方式来调用doGet()或者doPost()方法。但是，这两个do方法默认情况下，会抛出异常，需要子类去override。

**service**方法，每次有个请求到达某个Servlet方法时执行，用来处理请求（证明该Servlet进行服务了）

```java
public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	System.out.println("服务器调用了。。。");
}
```

### 3.3.销毁时机

当容器关闭时（应用程序停止时)，会将程序中的Servlet 实例进行销毁。

**destroy**方法，Servlet实例销毁时执行（证明该Servlet实例被销毁了）

```java
public void destroy(){
    System.out.println("实例销毁了。。。");
}
```

## 4、Tomcat与Servlet的工作流程

* 1.Web Client 向 Servlet 容器（Tomcat）发出HTTP请求。
* 2.Servlet 容器接收 WebClient 请求
* 3.Servle t容器创建一个 HttpServletRequest 对象，将 Web Client 请求的信息封装到这个对象中
* 4.Servlet 容器创建一个 HttpServletResponse 对象
* 5.Servlet 容器调用 HttpServlet 对象的 service 方法， 把 Request 与 Response 作为参数，传给 HttpServlet
* 6.HttpServlet 调用 HttpServletRequest 对象的有关方法， 获取HTTP信息。
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

#### 5.1.1常用方法

* getRequestURL()        获取客户端请求的完整URL (从 http 开始，到 ? 前)
* getRequestURI()         获取客户端请求的资源名称部分 (从站点名到 ? 前)

* getQueryString()        获取请求行中的参数部分。(从 ? 开始，到结束)
* getMethod()                获取请求方式( GET 和 SET)
* getProtocol()               获取 Http 版本号。(HTTP/1.1)
* getContextPath()        获取项目的站点名。(项目的对外访问路径)

#### 5.12.获取请求参数

* String getParameter(String name)   			获取指定名称的参数。
* String[]  getParameterValues(String name)    获取指定名称参数的所有值

### 5.2.请求乱码问题

​	由于现在的request属于接收客户端的参数，所以必然有其默认的语言编码，主要是由于在解析过程中默认使用的编码方式为IS0-8859-1(此编码不支持中文)，所以解析时一定会出现乱码。要想解决这种乱码问题，需要设置request中的编码方式，告诉服务器以何种方式来解析数据。或者在接收到乱码数据以后，再通过相应的编码格式还原。

乱码原因：
​    由于在解析过程中默认使用的编码方式为ISO-8859-1(此编码不支持中文)，所以编译时会出现乱码。

```
                             GET请求                      POST请求
Tomcat 7 及以前，             会乱码                          会乱码
Tomcat 8 及以上，            不会乱码                         会乱码。
```
无论什么版本的服务器，POST请求都会乱码。解决方法：

* request.setCharacterEncoding("UTF-8");

注意：

* 1.request.setCharacterEncoding("UTF-8") 只针对 POST 请求的乱码有效。
* 2.new String(request.getParameter("uname").getBytes("ISO-8859-1"), "UTF-8")针对任何请求方式。