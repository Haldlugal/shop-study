package ru.gb.gbapimay.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "gb.api")
public class GbApiProperties {

    private Connection connection;
    private Endpoint endpoint;

    @Getter
    @Setter
    public static class Endpoint {
        private String manufacturerUrl;
        private String categoryUrl;
        private String productUrl;
        private String userUrl;
    }

    @Getter
    @Setter
    public static class Connection {
        private long period;
        private long maxPeriod;
        private int maxAttempts;
        long connectTimeout;
        long readTimeout;
        boolean followRedirects;
    }
}
