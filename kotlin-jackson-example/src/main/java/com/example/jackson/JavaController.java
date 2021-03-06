package com.example.jackson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class JavaController {
    @GetMapping("/java/vanilla")
    public JavaPerson getJavaPerson() {
        return new JavaPerson(
                "test",
                true,
                true
        );
    }

    @GetMapping("/java/vanillaNonIsPrefix")
    public JavaPersonNonIsPrefix getJavaPersonNonIsPrefix() {
        return new JavaPersonNonIsPrefix(
                "test",
                true,
                true
        );
    }

    @GetMapping("/java/usingData")
    public JavaPersonUsingData getJavaPersonUsingData() {
        return new JavaPersonUsingData(
                "test",
                true,
                true
        );
    }

    @GetMapping("/java/nonIsPrefixUsingData")
    public JavaPersonNonIsPrefixUsingData getJavaPersonNonIsPrefixUsingData() {
        return new JavaPersonNonIsPrefixUsingData(
                "test",
                true,
                true
        );
    }

    public static class JavaPerson {
        private final String name;
        private final boolean canWalk;
        private final boolean isTestUser;

        public JavaPerson(
                String name,
                boolean canWalk,
                boolean isTestUser
        ) {
            this.name = name;
            this.canWalk = canWalk;
            this.isTestUser = isTestUser;
        }

        public String getName() {
            return name;
        }

        public boolean isCanWalk() {
            return canWalk;
        }

        public boolean isTestUser() {
            return isTestUser;
        }
    }

    public static class JavaPersonNonIsPrefix {
        private final String name;
        private final boolean canWalk;
        private final boolean testUser;

        public JavaPersonNonIsPrefix(
                String name,
                boolean canWalk,
                boolean testUser
        ) {
            this.name = name;
            this.canWalk = canWalk;
            this.testUser = testUser;
        }

        public String getName() {
            return name;
        }

        public boolean isCanWalk() {
            return canWalk;
        }

        public boolean isTestUser() {
            return testUser;
        }
    }

    @AllArgsConstructor
    @Data
    public static class JavaPersonUsingData {
        private String name;
        private boolean canWalk;
        private boolean isTestUser;
    }

    @AllArgsConstructor
    @Data
    public static class JavaPersonNonIsPrefixUsingData {
        private String name;
        private boolean canWalk;
        private boolean testUser;
    }
}
