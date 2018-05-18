2018.5.18 周五
1.完成和github的连接，包括上传和下载
  a.安装git -- 从官网https://git-scm.com/downloads中下载Windows对应的Git最新版本
  b.配置git -- Settings 中 Version Control 中的Git 配置git的执行路径 并点击 Test完成测试
  c.配置Github -- Settings 中 Version Control 中的GitHub 输入GitHub的账号
  d.上传工程 -- CVS -- Share Project On Github ，最后会在右下角显示提交成功
  e.更新工程 -- 该类右击->Git->Add，将类加入到本地git中，再次右击->Git-> Commit File... ，再次右击->Git->Repository->Push,上传成功
  c.本地删除了类 -- 重新提交即可
  git教程 -- https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000
           -- https://www.cnblogs.com/imqsl/p/6763133.html

2.基类设计【View接口，Presenter接口】
  a.画图理解 --  T可以理解为在每个界面中都需要有处理逻辑的P
                   Basecontract
                        |
        BaseView<T>               BasePresenter
            |                         |
    BaseActivity                  RxPresenter
        |                              |
Home  .... GameCenter         HomeP ... GameCenterP

  b.观察BaseView模板
    1.定义抽象方法让子类加载布局
    2.统一的ButterKnife绑定
    3.统一的Toolbar
    4.初始化一些逻辑，比如初始化状态栏，初始化变量，初始化数据，P绑定V，初始化注入等等
    5.将activity管理起来，用于退出时销毁
    6.实现BaseView的方法，完成相应的逻辑