package org.acme.raidingParty;

import java.util.List;
import java.util.UUID;

public record RaidingParty(
        UUID id,
        String awesomeTitle,
        List<Player> members
) {
}
