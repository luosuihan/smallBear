# smallBear



mvp模式学习之路：
老规矩先叙述一波为啥要用mvp模式来开发APP。就一个字爽。不吹牛逼。直接进入主题。

一，为什么会出现mvp模式？
    答：解决MVC中C层代码臃肿问题。
二，mvp模式结构：
    M：model层处理数据问题。
    V：数据渲染（activity，fragment）
    p：presenter层处理业务逻辑，也就是对model传递到V层的数据在p层处理之后，通过V层渲染出来。
  
  1，view层与presenter是双向绑定的，presenter与与model单向绑定（P层获取model层数据即可）
