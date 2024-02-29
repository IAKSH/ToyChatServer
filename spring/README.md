A RESTful API server using...
- Jetty (embedded) : as web server
- ~~Annotation : reduce spring xml config~~
- spring-MVC : create RESTful API without xml
- mybatis-spring : ORM framework compatible with spring
- some other things...

refer:
- https://www.liaoxuefeng.com/wiki/1252599548343744/1282383921807393
- https://www.liaoxuefeng.com/wiki/1252599548343744/1282382596407330
- https://www.liaoxuefeng.com/wiki/1252599548343744/1282384941023266

最后放弃了纯annotation,还是用起了xml。所以还是得换springboot
放弃的原因是，spring-MVC的controller是建在spring IoC中的一个bean下的子IoC容器。
由于Servlet，以及高并发的需求，controller实际上是可以存在多个实例的。然而一开始autowire的只有其中一个实例
等下...我似乎有一种新的方案...等写完了去试试吧
而且，纯annotaion也导致代码可读性降低...除非能保证编程全程遵守annotation和包名的对应规则。
最后，即使是使用xml的方案，也是在controller中使用了static才实现的。