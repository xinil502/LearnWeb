# CSS学习笔记

CSS：Cascading Style Sheet  层叠样式表语言

修饰HTML页面，设置HTML的元素样式，让HTML页面更好看。

HTML是主体，CSS是依赖HTML的化妆品。



HTML嵌套CSS的三种方式

* 标签内部使用style属性来设置元素的CSS样式

  * 语法格式：<标签 style="样式名:值:样式名:值"></标签>

* 在head标签中使用style块定义样式块。

  ```html
  <head>
      <style type="text/css">
          选择器{
              样式名：值；
              样式名：值；
              ......
          }
          选择器{
              ......
          }
          .....
      </style>
  </head>
  ```

* 链入外部样式表文件（最常用）。将样式写入.css文件中，需要时直接引入

  ```html
  <link type="text/css" rel="stylesheet" href="文件路径">
  ```

  维护成本低。