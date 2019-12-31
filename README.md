# SpringBootTest
一、创建项目

    file-->new project --->next-->填写包名、项目名-->选择相应的模块和版本-->完成

二、属性文件配置

    1、server.port=8080 ：设置访问端口号
    2、pom.xml中依赖jdbc启动器、mysql驱动依赖
    3、配置属性：数据源 spring.datasource.url、driver-class-name、username、password
    spring:
      datasource:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3306/springboot
        username: root
        password: root
     编写持久层实现类，依赖 jdbcTemplate。依赖的模板对象，不需要自定义和配置。自动创建

jdbcTemplate

	是Spring对JDBC的封装，旨在使JDBC更加易于使用。并且JdbcTemplate处理了资源的建立和释放。需要配置数据源，但是spring boot已经自动帮我们初始化好了jdbcTemplate对象，并且默认采用tomcat数据源dataSource

@Transactional不仅可以注解在方法上，也可以注解在类上。当注解在类上的时候意味着所有的public方法都是开启事务的。

步骤：1、在application类添加开启事物支持注解：@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />

	2、在service中对应的方法上：@Transactional

默认遇到throw new RuntimeException(“…”);会回滚 ,需要捕获的throw new Exception(“…”);不会回滚.可以通过属性设置指定回滚

    指定回滚   @Transactional(rollbackFor=Exception.class) 
    指定不回滚  @Transactional(noRollbackFor=Exception.class)

@Transactional属性

            属性          	类型                         	说明                                      
       propagation      	枚举：Propagation             	可选的传播性设置。@transactional(propagation=Propagation.REQUIRES_NEW)
        isolation       	枚举型：Esolation              	可选的隔离性级别。使用举例：@Transactional(isolation=Isolation.READ  COMMITTED)
         readOnly       	布尔型                        	是否为只读型事务。使用举例：@Trans actional(readOnly=true)
         tlmeoUt        	int型（以秒为单位）                	事务超时。使用举例：@T ransactional(timeout=1 0)  
       roIlbackFor      	一组Class类的实例，必须是Throwable的予类	一组异常类，遇到时必须进行圆滚。使用举例：@Transactional(rollbackFor={S  QLException.class})，多个异常可用英文逗号隔开
   rollbackForClassName 	一组Class类的名称，必须是Throwable的子类	一组异常类名，遇到时必须进行圆滚。使用举例：@Transactional(rollbackForClassName={”SQLException”}),多个异常可用英文逗号隔开
      noRollbackFor     	一组Class类的实例，必须是Throwable的子类	一组异常类，遇到时必须不圆滚                          
  noRollbackForClassName	一组Class类的名字，必须是Throwable的子类	一组异常类名，遇到时必须不圆滚                         

readOnly    该属性用于设置当前事务是否为只读事务，设置为true表示只读，false则表示可读写，默认值为false。例如：@Transactional(readOnly=true)
 rollbackFor     该属性用于设置需要进行回滚的异常类数组，当方法中抛出指定异常数组中的异常时，则进行事务回滚。例如：指定单一异常类：@Transactional(rollbackFor=RuntimeException.class)指定多个异常类：@Transactional(rollbackFor={RuntimeException.class,  Exception.class})
 rollbackForClassName     该属性用于设置需要进行回滚的异常类名称数组，当方法中抛出指定异常名称数组中的异常时，则进行事务回滚。例如：指定单一异常类名称@Transactional(rollbackForClassName=”RuntimeException”)指定多个异常类名称：@Transactional(rollbackForClassName={“RuntimeException”,”Exception”})
 noRollbackFor     该属性用于设置不需要进行回滚的异常类数组，当方法中抛出指定异常数组中的异常时，不进行事务回滚。例如：指定单一异常类：@Transactional(noRollbackFor=RuntimeException.class)指定多个异常类：@Transactional(noRollbackFor={RuntimeException.class,  Exception.class})
 noRollbackForClassName     该属性用于设置不需要进行回滚的异常类名称数组，当方法中抛出指定异常名称数组中的异常时，不进行事务回滚。例如：指定单一异常类名称：@Transactional(noRollbackForClassName=”RuntimeException”)指定多个异常类名称：@Transactional(noRollbackForClassName={“RuntimeException”,”Exception”})
 propagation    该属性用于设置事务的传播行为。例如：@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
 isolation    该属性用于设置底层数据库的事务隔离级别，事务隔离级别用于处理多事务并发的情况，通常使用数据库的默认隔离级别即可，基本不需要进行设置
 timeout    该属性用于设置事务的超时秒数，默认值为-1表示永不超时
 \4. 事务属性

事务隔离级别 
 隔离级别是指若干个并发的事务之间的隔离程度。TransactionDefinition 接口中定义了五个表示隔离级别的常量：

TransactionDefinition.ISOLATION_DEFAULT：这是默认值，表示使用底层数据库的默认隔离级别。对大部分数据库而言，通常这值就是TransactionDefinition.ISOLATION_READ_COMMITTED。 
 TransactionDefinition.ISOLATION_READ_UNCOMMITTED：该隔离级别表示一个事务可以读取另一个事务修改但还没有提交的数据。该级别不能防止脏读，不可重复读和幻读，因此很少使用该隔离级别。比如PostgreSQL实际上并没有此级别。 
 TransactionDefinition.ISOLATION_READ_COMMITTED：该隔离级别表示一个事务只能读取另一个事务已经提交的数据。该级别可以防止脏读，这也是大多数情况下的推荐值。 
 TransactionDefinition.ISOLATION_REPEATABLE_READ：该隔离级别表示一个事务在整个过程中可以多次重复执行某个查询，并且每次返回的记录都相同。该级别可以防止脏读和不可重复读。 
 TransactionDefinition.ISOLATION_SERIALIZABLE：所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。

事务传播行为 
 所谓事务的传播行为是指，如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为。在TransactionDefinition定义中包括了如下几个表示传播行为的常量：

TransactionDefinition.PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。 
 TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。 
 TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。 
 TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。 
 TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。 
 TransactionDefinition.PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。 
 TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。

事务超时 
 所谓事务超时，就是指一个事务所允许执行的最长时间，如果超过该时间限制但事务还没有完成，则自动回滚事务。在 TransactionDefinition 中以 int 的值来表示超时时间，其单位是秒。 
 默认设置为底层事务系统的超时值，如果底层数据库事务系统没有设置超时值，那么就是none，没有超时限制。

事务只读属性 
 只读事务用于客户代码只读但不修改数据的情形，只读事务用于特定情景下的优化，比如使用Hibernate的时候。 
 默认为读写事务。

    @Repository("userDao")
    public class UserDaoIm implements UserDaoI {
        @Autowired
        private JdbcTemplate jdbcTemplate;
        
       /**
        * 功能描述:查询所有数据
        * @Param: []
        * @Return: java.util.List<com.example.pojo.User>
        * @Author: Administrator
        * @Date: 2019/12/25 17:50
        */
        @Override
        public List<User> findAll() {
            String sql = "SELECT userId,userName,passWord,address from User";
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));
        }
        /**
         * 功能描述: 由userId查询数据
         * @Param: [userId]
         * @Return: com.example.pojo.User
         * @Author: Administrator
         * @Date: 2019/12/25 17:50
         */
        @Override
        public User findById(Integer userId) {
            String sql = "select userId,userName,passWord,address from User where userId=?";
            Object[] params = {userId};
            return jdbcTemplate.queryForObject(sql,params,new BeanPropertyRowMapper<User>(User.class));
        }
        /**
         * 功能描述: 添加数据
         * @Param: [user]
         * @Return: boolean
         * @Author: Administrator
         * @Date: 2019/12/25 17:51
         */
        @Override
        public boolean addUser(User user) {
            String sql = "insert into User(userName,passWord,address)values(?,?,?)";
            Object[] params = {user.getUserName(),user.getPassWord(),user.getAddress()};
            return jdbcTemplate.update(sql,params)>0;
        }
        /**
         * 功能描述: 由userId删除数据
         * @Param: [userId]
         * @Return: boolean
         * @Author: Administrator
         * @Date: 2019/12/25 17:51
         */
        @Override
        public boolean deleteById(Integer userId) {
            String sql = "delete from User where userId=?";
            Object[] params = {userId};
            return jdbcTemplate.update(sql,params)>0;
        }
        /**
         * 功能描述:由userId修改数据
         * @Param: [userId, userName]
         * @Return: boolean
         * @Author: Administrator
         * @Date: 2019/12/25 17:51
         */
        @Override
        public boolean changeById(Integer userId,String userName) {
            String sql = "update User set userName=? where userId=?";
            Object[] params = {userName,userId};
            return jdbcTemplate.update(sql,params)>0;
        }
    }



三、注解的使用

    RestConller  自动返回json类型数据 相当于controller+reponseBody
    Controller  控制类，程序的入口
     @ResponseBody  返回json字符串
    RequestMapping(value="访问名",method=RequestMethod.get);@RequestMapping表示请求的URL上下文路径，该路径不能重复
    @Repository("userDao") dao层
    @Service("userService) 业务类
    @Autowrite  自动注入
    @ @Qualifier("userDao") 指定注入
    @Transactional 事务管理

接收参数方式：

    通过实体Bean接收请求参数   public String login(Student stu, Model model)
    通过方法的参数接收   public String login(String name, String password, Model model)
    通过HttpServletRequest接收请求参数 public String login(HttpServletRequest req,Model model)
    通过@PathVariable接收URL中的请求参数  public String test4(@PathVariable String name)
    通过@RequestParam接收请求参数 public String test5(@RequestParam String name,@RequestParam String passwd)
    通过@ModelAttribute接收请求参数  public String test6(@ModelAttribute("stu") Student stu)

四、JPA方式

JPA(Java Persistence API)是Sun官方提出的Java持久化规范. 为Java开发人员提供了一种对象/关联映射工具来管理Java应用中的关系数据. 它的出现是为了简化现有的持久化开发工作和整合ORM技术. 结束各个ORM框架各自为营的局面.

Spring Data JPA是Spring基于ORM框架、JPA规范的基础上封装的一套JPA应用框架,是基于Hibernate之上构建的JPA使用解决方案,用极简的代码实现了对数据库的访问和操作,包括了增、删、改、查等在内的常用功能.

1.实体类

2.一个接口（继承JpaRepository<User,Integer>）

3.可控制类直接调用JPA方法

    public interface UserRepository extends JpaRepository<User,Integer>{
    }
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value = "/add",method = RequestMethod.GET)
        public String add(Integer userId){
            System.out.println("add........");
            List<User> save = userRepository.findAll();

实体类注解

     @Entity(name="EntityName") 必须，用来标注一个数据库对应的实体，数据库中创建的表名默认和类名一致。其中，name 为可选，对应数据库中一个表，使用此注解标记 Pojo 是一个 JPA 实体。
     @Table(name=，catalog=，schema="") 可选，用来标注一个数据库对应的实体，数据库中创建的表名默认和类名一致。通常和 @Entity 配合使用，只能标注在实体的 class 定义处，表示实体对应的数据库表的信息。
    @Id 必须，@Id 定义了映射到数据库表的主键的属性，一个实体只能有一个属性被映射为主键。
    @GeneratedValue(strategy=GenerationType，generator="") 可选，strategy: 表示主键生成策略，有 AUTO、INDENTITY、SEQUENCE 和 TABLE 4 种，分别表示让 ORM 框架自动选择，generator: 表示主键生成器的名称。
    @Column(name = "user_code"， nullable = false， length=32) 可选，@Column 描述了数据库表中该字段的详细定义，这对于根据 JPA 注解生成数据库表结构的工具。name: 表示数据库表中该字段的名称，默认情形属性名称一致；nullable: 表示该字段是否允许为 null，默认为 true；unique: 表示该字段是否是唯一标识，默认为 false；length: 表示该字段的大小，仅对 String 类型的字段有效。
    @Transient可选，@Transient 表示该属性并非一个到数据库表的字段的映射，ORM 框架将忽略该属性。
    @Enumerated 可选，使用枚举的时候，我们希望数据库中存储的是枚举对应的 String 类型，而不是枚举的索引值，需要在属性上面添加 @Enumerated(EnumType.STRING) 注解。

    在使用JPA自带方法时，生成的SQL语句中会将字段名改变，所以应在属性文件中加上转换策略（必须放在属性文件中才有效）
    spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    实体类中使用注解@Column(name="")指定

事务管理依然用注解@Transactional

注解原理、自定义注解

注解的本质就是一个继承了 Annotation 接口的接口

JAVA 中有以下几个『元注解』：

- @Target：注解的作用目标
- @Retention：注解的生命周期
- @Documented：注解是否应当被包含在 JavaDoc 文档中
- @Inherited：是否允许子类继承该注解
      @Target(value = {ElementType.FIELD})
      ElementType 是一个枚举类型，有以下一些值：
      	- ElementType.TYPE：允许被修饰的注解作用在类、接口和枚举上
      	- ElementType.FIELD：允许作用在属性字段上
      	- ElementType.METHOD：允许作用在方法上
      	- ElementType.PARAMETER：允许作用在方法参数上
      	- ElementType.CONSTRUCTOR：允许作用在构造器上
      	- ElementType.LOCAL_VARIABLE：允许作用在本地局部变量上
      	- ElementType.ANNOTATION_TYPE：允许作用在注解上
      	- ElementType.PACKAGE：允许作用在包上

    @Retention(value = RetentionPolicy.RUNTIME
    RetentionPolicy 依然是一个枚举类型，它有以下几个枚举值可取：
        RetentionPolicy.SOURCE：当前注解编译期可见，不会写入 class 文件
        RetentionPolicy.CLASS：类加载阶段丢弃，会写入 class 文件
        RetentionPolicy.RUNTIME：永久保存，可以反射获取
    

@Documented 注解修饰的注解，当我们执行 JavaDoc 文档打包时会被保存进 doc 文档，反之将在打包时丢弃。

@Inherited 注解修饰的注解是具有可继承性的，也就说我们的注解修饰了一个类，而该类的子类将自动继承父类的该注解。

-     JDK 还为我们预定义了另外三种注解，它们是：
      	- @Override
      	- @Deprecated 『标记式注解』，永久存在，可以修饰所有的类型，作用是，标记当前的类或者方法或者字段等已经不再被推荐使用了，可能下一次的 JDK 版本就会删除。
      	- @SuppressWarnings  通过设置 value 属性的值即可压制警告。
      对于一个类或者接口来说，Class 类中提供了以下一些方法用于反射注解。
      	getAnnotation：返回指定的注解
      	isAnnotationPresent：判定当前元素是否被指定注解修饰
      	getAnnotations：返回所有的注解
      	getDeclaredAnnotation：返回本元素的指定注解
      	getDeclaredAnnotations：返回本元素的所有注解，不包含父类继承而来的
      
  注解的属性可支持数据类型有如下： 
1.所有基本数据类型（int,float,boolean,byte,double,char,long,short) 
2.String类型 
3.Class类型 
4.enum类型 
5.Annotation类型 
6.以上所有类型的数组
