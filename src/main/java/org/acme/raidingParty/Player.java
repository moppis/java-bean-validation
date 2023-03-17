package org.acme.raidingParty;

import javax.validation.constraints.*;

public record Player(
        @NotNull
        @Size(max = 10)
                @NotBlank
        String name,
        @NotNull
        PlayerClass playerClass,
        @Max(value = 400, groups = ValidationGroups.Warrior.class)
        @Max(value = 200)
                @Min(0)
        Integer health,
        @Min(0)
                @Max(value = 0, groups = ValidationGroups.Warrior.class)
                @Max(value = 400, groups = ValidationGroups.Wizard.class)
        Integer mana
) {
}
