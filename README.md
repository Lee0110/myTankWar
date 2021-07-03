# 坦克大战

## 关键字

- java基础练手项目
- swing
- jdbc
- 多线程
- 架构为MVC
- 。。。

## 如何运行程序

1. 在MySql里创建两张表,或者直接执行sql文件。
2. 修改JdbcUtil.java里的IP_ADDR
3. 执行Login.java
4. 注册、登录，按空格开始游戏。WASD移动，J射击

## 程序运行图

![image](https://github.com/Lee0110/myTankWar/blob/master/images/tankWar.gif)

## 架构说明

### MVC之M模型层

模型层我创建了一个**com.Lee.entity**包来存各种实体类。包括：
- Emmo（子弹）
- EnemyTank（敌机）
- MyTank（我的坦克）
- User（对应数据库用户）
- Score（对应数据库分数）
- 。。。

这些类的属性都是私有的，有一系列get、set方法。其中Emmo，EnemyTank都是线程类，按下空格游戏开始，EnemyTank线程启动，坦克死亡，线程结束，发射子弹，Emmo线程启动，子弹打到坦克，或者子弹飞出屏幕，线程死亡。这些类并没有操作的方法，也没有显示的方法，他们就是用来被Controller操作的。然后在View层显示。

### MVC之V视图层
视图层用**com.Lee.view**来存各种显示图形的类。他们都继承java里的JFrame，通过桌面窗口的形式来显示图形。包括：
- HelpFrm（帮助窗口）
- LoginFrm（登录窗口）
- StartGame（游戏窗口）
- 。。。

这些类只负责显示图形，显示子弹，坦克，我的分数等等。这一层只会做一些简单的判断，其他的给Control层做

### MVC之C控制层
控制层**com.Lee.controller**里有一个controller类，里面存放各种各样的方法，所有对数据库的访问，事件监听，初始化数据等都在这里。其中对数据库的访问有三层，第一层是controller，第二层是service，这层主要是对密码加密，正则匹配等等，第三层是dao，访问数据库

