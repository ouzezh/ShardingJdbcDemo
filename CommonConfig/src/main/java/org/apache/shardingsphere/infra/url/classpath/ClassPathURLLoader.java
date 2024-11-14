/*
 * org.apache.shardingsphere:shardingsphere-infra-url-classpath:5.5.1
 */
package org.apache.shardingsphere.infra.url.classpath;

import lombok.SneakyThrows;
import org.apache.shardingsphere.infra.url.spi.ShardingSphereURLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Class path URL loader.
 */
public final class ClassPathURLLoader implements ShardingSphereURLLoader {

    private static final Logger log = LoggerFactory.getLogger(ClassPathURLLoader.class);

    @Override
    @SneakyThrows(IOException.class)
    public String load(final String configurationSubject, final Properties queryProps) {
        String data;
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configurationSubject)) {
            Objects.requireNonNull(inputStream);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                data = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
        String key = "${my-db}";
        String value = "my_db";
        while (data.contains(key)) {
            log.info("{} replace property: {} -> {}", getClass().getSimpleName(), key, value);
            data = data.replace(key, value);
        }
        return data;
    }
    
    @Override
    public String getType() {
        return "classpath:";
    }
}
