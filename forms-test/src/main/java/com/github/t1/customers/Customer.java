package com.github.t1.customers;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @FormParam("id")
    private Long id;

    @NotEmpty
    @FormParam("name")
    @Size(min = 6, max = 256)
    private String name;

    @FormParam("email")
    private String email;

    @NotNull
    @FormParam("supportLevel")
    private Level supportLevel;

    public enum Level {
        bronze, silver, gold, platinum;

        public static String toString(Level level) {
            return (level == null) ? null : level.name();
        }
    }
}
