package org.acme.raidingParty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public record RaidingParty(
        @NotNull(groups = ValidationGroups.Put.class)
        @Null(groups = ValidationGroups.Post.class)
        UUID id,
        @NotBlank
                @Size(max = 16, min = 8)
        String awesomeTitle,
        @Size(max = 4)
        List<@Valid Player> members
) {
}
