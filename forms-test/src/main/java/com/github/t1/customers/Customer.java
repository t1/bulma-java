package com.github.t1.customers;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor
public class Customer {
    private Long id;

    @NotEmpty
    private String name;

    private String email;

    @NotNull
    private Level supportLevel;

    public enum Level {
        bronze, silver, gold, platinum;

        public static @NotNull Level of(String name) {
            return (name == null || name.isBlank()) ? null : valueOf(name);
        }

        public static String toString(Level level) {
            return (level == null) ? null : level.name();
        }
    }
}
