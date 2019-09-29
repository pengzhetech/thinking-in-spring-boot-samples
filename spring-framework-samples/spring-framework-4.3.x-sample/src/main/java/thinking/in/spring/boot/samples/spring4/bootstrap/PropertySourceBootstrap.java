package thinking.in.spring.boot.samples.spring4.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author pengzhe
 * @date 2019-09-29 14:40
 */
@Configuration
@ComponentScan("thinking.in.spring.boot.samples.spring4")
public class PropertySourceBootstrap {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 配置Bean ConditionalMessageConfiguration 到 Spring 上下文
        context.register(PropertySourceBootstrap.class);
        // 启动上下文
        context.refresh();

        TestBean bean = context.getBean(TestBean.class);
        System.out.println(bean.getName());
    }

    @Bean
    public TestBean testBean() {
        TestBean testBean = new TestBean();
        testBean.setName(environment.getProperty("testKey"));
        return testBean;
    }

    class TestBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
