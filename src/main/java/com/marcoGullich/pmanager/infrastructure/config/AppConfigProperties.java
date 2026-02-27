package com.marcoGullich.pmanager.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppConfigProperties {

    private final General general;
    private final Security security;

    public AppConfigProperties(General general, Security security) {
        this.general = general;
        this.security = security;
    }

    public General getGeneral() {
        return general;
    }

    public Security getSecurity() {
        return security;
    }

    public static class General {
        private final int pageSize;

        public General(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageSize() {
            return pageSize;
        }
    }

    public static class Security {
        private final String apiKey;

        public Security(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getApiKey() {
            return apiKey;
        }
    }
}
