
package com.vxtech.capela.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(ignoreUnknownFields = true)
@Getter
@Setter
public class Properties {

    private final Info info = new Info();

    @Getter
    @Setter
    public static class Info {

        private final App app = new App();

        @Getter
        @Setter
        public static class App {

            private String name;

            private String version;

            private String description;

        }

    }

    private final Capela capela = new Capela();

    @Getter
    @Setter
    public static class Capela {

        private final Liquibase liquibase = new Liquibase();

        @Getter
        @Setter
        public static class Liquibase {

            private Boolean clearLocks;

            private Boolean clearSums;

            private final ChangeLogs main = new ChangeLogs();

            private final ChangeLogs clients = new ChangeLogs();

            @Getter
            @Setter
            public static class ChangeLogs {

                private Boolean enabled;

                private String changeLog;

            }

        }

    }

    private final Servlet servlet = new Servlet();

    @Getter
    @Setter
    public static class Servlet {

        private Integer textMessageBufferSize;

        private Integer maxBinaryMessageBufferSize;

        private Long maxSessionIdleTimeout;

        private Long asyncSendTimeout;

    }
}
