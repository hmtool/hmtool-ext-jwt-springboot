package tech.mhuang.ext.jwt.springboot.configuration;

import tech.mhuang.core.check.CheckAssert;
import tech.mhuang.ext.jwt.admin.JwtFramework;
import tech.mhuang.ext.jwt.admin.external.IJwtExternal;
import tech.mhuang.ext.spring.start.SpringContextHolder;
import tech.mhuang.ext.springboot.context.SpringBootExtAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * jwt构建类
 *
 * @author mhuang
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass(JwtFramework.class)
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnProperty(prefix = "mhuang.jwt",name = "enable",havingValue = "true",matchIfMissing = true)
@AutoConfigureAfter({SpringBootExtAutoConfiguration.class})
@Slf4j
public class JwtAutoConfiguration {

    private final JwtProperties properties;

    public JwtAutoConfiguration(JwtProperties properties){
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public IJwtExternal jwtExternal()
    {
        return new SpringJwtExternal();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtFramework framework(IJwtExternal jwtExternal, SpringContextHolder springContextHolder){
        CheckAssert.check(this.properties, "jwt properties invalid...");
        CheckAssert.check(springContextHolder,"SpringContextHolder不存在、请设置mhuang.holder.enable=true");
        JwtFramework framework = new JwtFramework(this.properties);
        framework.external(jwtExternal);
        framework.start();
        return framework;
    }
}
