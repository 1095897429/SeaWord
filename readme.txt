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
  a.画图理解 --  T1可以理解为在每个界面中都需要有处理逻辑的P || T2理解为P要绑定界面的V
                   Basecontract
                        |
                     BaseView                             BasePresenter<T> -- 包含RetrofitHelper工厂类
                |                 |                                    |
    BaseActivity<T>         BaseFragment<T>                     RxPresenter<T>
        |                         |       |                            |
        |                         |    BaseRefreshFragment<T>刷新      |
        |                         |       |                            |
        |                         |      Recommend(P)                  |
Home(P)  .... GameCenter    Home(P)....Live(P)                HomeP ... GameCenterP





  b.BaseView模板
    1.定义抽象方法让子类加载布局
    2.统一的ButterKnife绑定,可用Unbinder 绑定和解绑
    3.统一的Toolbar,通用的标题栏
    4.初始化一些逻辑，比如初始化状态栏，初始化变量，初始化数据，P绑定V，初始化注入等等
    5.将activity管理起来，用于退出时销毁
    6.实现BaseView的方法，完成相应的逻辑
  c.Application模板
    1.管理activity数据
    2.单例模式
    3.初始化一些逻辑，比如初始化网络组件，初始化SP，初始化调试信息，初始化Log等等
  d.RxJava模块 -- （1）博客1
    1.涉及RxAppCompatActivity 和 RxFragment
  e.SplashActivity 界面
    1.在module - app 下，我认为是在整个app模块下的一个界面 -- SplashActivity
    2.在mvp - contract - app下，SplashContract的具体逻辑 --  SplashContract
    3.在bean - app下，Splash实体类 -- Splash
    4.引入P -- SplashPresenter，统一规定逻辑都放在P中
    5.引入Dragger -- Component + Module  -- Ctrl + F9 编译生成代码   (4)博客4 备注：3.Dragger2中有一个很小但很讨厌的缺陷，如果父组件和子组件共享相同的范围，编译器会报告完全误导的错误
         https://stackoverflow.com/questions/30260073/dagger-2-error-dependency-cannot-be-provided-without-an-inject-constructor-w
    6.引入Retrofit -- 定义接口文件 -- (5)博客5 -- 拦截器

  f.Main 界面
    1.绘制界面
    2.写BaseFragment
    3.在Fragment中使用Toolbar
    4.关于左侧的菜单一些操作，比如去掉滚动条，菜单项的点击事件
    5.首次接触RxBus -- （6）博客6【eventbus 和 rxbus】 -- http://yydcdut.com/2016/03/07/eventbus3-code-analyse/

  e.RecommendFragment界面
    1.viewpager + FragmentPagerAdapter + tablayout【最好用于有限个静态fragment页面的管理】 实现 滑动选项导航卡
    2.统一处理HttpResponse,网络请求的基类
    3.BaseMultiItemQuickAdapter实现多布局 -- (7)博客7
    4.BaseRefreshFragment中封装官方下拉刷新的抽象类
  f.LiveFragment界面
    1.FlowableTransformer的作用
    2.如何避免嵌套
  e.Region界面
    1."data": [] -- 构建数据返回的时候利用List<Region>
  g.VideoDetail界面





  小技巧：
    1.如何让包有层次显示 -- 找到项目中的设置图标，去掉 ”compact empty middle package“前面勾
    2.GsonFormat -- AS中增加 -- Alt + S


  微技巧：
    1.8.0的适配，不然手机会默认显圆形
    2.GlideLine学习 --



