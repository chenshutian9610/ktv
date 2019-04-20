package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.testng.annotations.Test;

/**
 * @author er_dong_chen
 * @date 2019/3/14
 */
public class CommonTest {
    @Test
    public void test() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        template.opsForZSet().incrementScore("favorite",1L,1);
    }
}
