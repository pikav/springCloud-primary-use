对springCloud框架的初级使用实践，是一个用户服务+课程服务的微服务架构，使用到了 eureka，fegin，zuul，hystrix组件
首先要搞清楚用到的四个组件是干嘛的?

1. eureka： 服务注册中心, 注册其他服务

  @EnableEurekaServer
  
  @SpringBootApplication 
  
  public class EurekaAppliction {
  
      public static void main(String[] args) {
      
          SpringApplication.run(EurekaAppliction.class, args);
          
      }
      
  }
  
  增加yml配置：
  
  eureka:
  
  instance:
  
    hostname: localhost
    
  client:
  
    #表示是否从 Eureka Server 中获取注册信息，默认true， 单点eureka server 不需要同步其他节点数据，所以配置false
    
    fetch-registry: false
    
    #是否将自己注册到 Eureka Server, 默认true， 单点不需要注册自己
    
    register-with-eureka: false
    
    service-url:
    
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
      
      
2. fegin：用于调用其他服务，提供基于接口的声明式服务调用

  @FeignClient(value = "eureka-client-mainpage-course",  // 要调用的服务名（通过http协议, 调用目标服务的controller）
  fallback = CourseClientHystrix.class)
    
  public interface CourseClient {
  
      final String SERVICE_CONTEXT = "/mainpage-course";
      
      @RequestMapping(value = SERVICE_CONTEXT + "/get/course",
              method = RequestMethod.GET)
              
      CourseInfo getCourseInfo(Long id);
      
      @RequestMapping(value = SERVICE_CONTEXT + "/get/courses",
              method = RequestMethod.POST)
              
      List<CourseInfo> getCourseInfos(@RequestBody CourseInfosRequest request);
      
  }
  
 3. zuul：其实就是一组过滤器, API网关服务, 所有服务调用请求不直接输入url调用(直接输入也行)而是先进入zuul服务,然后通过zuul分发请求
 
  @EnableZuulProxy
  
  @SpringCloudApplication
  
  public class ZuulGatewayApplication {
  
      public static void main(String[] args) {
      
          SpringApplication.run(ZuulGatewayApplication.class,args);
          
      }
      
  }
  
  增加yml配置：
  
  #网关服务配置
  
  zuul:
  
    prefix: /pikav  #通过网关服务调用前缀
    
    routes:         #路由
    
      course:
      
        path: /mainpage-course/**    #url前缀为此, 则路由到course服务
        
        serviceId: eureka-client-mainpage-course    # 服务实例
        
        strip-prefix: false          #true: url前缀将被隐藏  false: url前缀显示
        
      user:
      
        path: /mainpage-user/**      #url前缀为此, 则路由到course服务
        
        serviceId: eureka-client-mainpage-user    # 服务实例
        
        strip-prefix: false          #true: url前缀将被隐藏  false: url前缀显示
        
   
 4. hystrix： 防止服务雪崩, 当一个服务出现问题不能正常使用, 让该服务降级, 返回一个缓存结果或者其他信息(需继承feginClient实现类)
 
    @Component
    
    @Slf4j
    
    public class CourseClientHystrix implements CourseClient{
    
        @Override
        
        public CourseInfo getCourseInfo(Long id) {
        
            log.error("getCourseInfo() 出现熔断");
            
            return CourseInfo.invalid();
            
        }
        
        @Override
        
        public List<CourseInfo> getCourseInfos(CourseInfosRequest request) {
        
            log.error("getCourseInfos() 出现熔断");
            
            return Collections.emptyList();
            
        }
        
    }
    
 实践过程遇到的问题：
 
 1.Jpa自动出入时间注解@CreateDate格式问题
 
    定义自动插入时间的属性为String类型，日期格式为：  2018-18-4 中午5：53
    
    定义自动插入时间的属性为Date类型，日期格式为： 2019-12-4 ：22：22：22
  
 2. Zuul使用问题：定义的过滤器没有被执行
 
    @Component  // 未给过滤器类增加spring bean注解
    
    public class PreRequestFilter extends ZuulFilter {
  
  
