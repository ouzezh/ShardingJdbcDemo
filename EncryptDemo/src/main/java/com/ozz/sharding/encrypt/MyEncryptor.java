package com.ozz.sharding.encrypt;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.encrypt.spi.EncryptAlgorithm;
import org.apache.shardingsphere.encrypt.spi.EncryptAlgorithmMetaData;
import org.apache.shardingsphere.infra.algorithm.core.config.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.algorithm.core.context.AlgorithmSQLContext;

import java.util.Objects;
import java.util.Properties;

@Slf4j
public class MyEncryptor implements EncryptAlgorithm {
    @Override
    public String getType() {
        return "myEncryptor";
    }

    @Override
    public void init(final Properties props) {
        if(props != null) {
            for (String key : props.stringPropertyNames()) {
                log.info("{} property: {}={}", getClass().getSimpleName(), key, props.get(key));
            }
        } else {
            log.info("{} property is empty", getClass().getSimpleName());
        }
    }

    @Override
    public Object encrypt(Object o, AlgorithmSQLContext algorithmSQLContext) {
        String s = Objects.toString(o, null);
        if(StrUtil.isEmpty(s)) {
            return o;
        }
        return s.replaceFirst("^ENC_", StrUtil.EMPTY);
    }

    @Override
    public Object decrypt(Object o, AlgorithmSQLContext algorithmSQLContext) {
        String s = Objects.toString(o, null);
        if(StrUtil.isEmpty(s)) {
            return o;
        }
        return StrUtil.format("ENC_{}", s);
    }

    @Override
    public EncryptAlgorithmMetaData getMetaData() {
        return new EncryptAlgorithmMetaData(true, false, false);
    }

    @Override
    public AlgorithmConfiguration toConfiguration() {
        return new AlgorithmConfiguration(getType(), new Properties());
    }
}
