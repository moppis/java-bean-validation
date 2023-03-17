package org.acme.raidingParty;

import javax.validation.groups.Default;

public interface ValidationGroups {
    interface Post extends Default {}
    interface Put extends Default {}
    interface Warrior extends Default {}
    interface Wizard extends Default {}

}
