package tech.mhuang.ext.jwt.springboot.configuration;

import tech.mhuang.core.check.CheckAssert;
import tech.mhuang.ext.jwt.admin.JwtFramework;
import tech.mhuang.ext.jwt.admin.external.IJwtExternal;
import tech.mhuang.ext.springboot.context.ContextAutoConfiguration;
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
@AutoConfigureAfter({ContextAutoConfiguration.class})
@Slf4j
public class JwtAutoConfiguration {

    private final JwtProperties properties;

    public JwtAutoConfiguration(JwtProperties properties){
        this.properties = properties;
    }

    private IJwtExternal jwtExternal(){
        return new SpringJwtExternal();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtFramework jwtFramework(){
        CheckAssert.check(this.properties, "jwt properties invalid...");
        JwtFramework framework = new JwtFramework(this.properties);
        framework.external(jwtExternal()).start();
        return framework;
    }
}
