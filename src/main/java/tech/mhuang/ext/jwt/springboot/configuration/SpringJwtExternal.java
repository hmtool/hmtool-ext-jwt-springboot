package tech.mhuang.ext.jwt.springboot.configuration;

import tech.mhuang.ext.jwt.admin.JwtHelper;
import tech.mhuang.ext.jwt.admin.external.IJwtExternal;
import tech.mhuang.ext.jwt.admin.external.IJwtProducer;
import tech.mhuang.ext.spring.start.SpringContextHolder;

/**
 *
 * jwt spring实现
 *
 * @author mhuang
 * @since 1.0.0
 */
public class SpringJwtExternal implements IJwtExternal {

    @Override
    public IJwtProducer create(String key) {
        return SpringContextHolder.registerBean(key, JwtHelper.class);
    }
}