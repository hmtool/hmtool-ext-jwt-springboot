package tech.mhuang.ext.jwt.springboot.configuration;

import tech.mhuang.ext.jwt.admin.bean.Jwt;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * jwt属性类
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = "mhuang.jwt")
public class JwtProperties extends Jwt{

    /**
     * auto open jwt enable.
     *  default enable false
     */
    private boolean enable;

    public JwtProperties(){
        super();
        enable = true;
    }
}
