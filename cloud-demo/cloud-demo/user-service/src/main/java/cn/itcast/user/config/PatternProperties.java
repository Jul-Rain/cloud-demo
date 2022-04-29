package cn.itcast.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//第二种配置nacos管理配置热更新
@Data
@Component
@ConfigurationProperties(prefix = "pattern")      //前缀pattern和成员变量名dateformat和配置文件相匹配就能完成注入
public class PatternProperties {
    private String dateformat;
}
