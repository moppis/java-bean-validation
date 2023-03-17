package org.acme.raidingParty;

public record Player(
        String name,
        PlayerClass playerClass,
        Integer health,
        Integer mana
) {
}
