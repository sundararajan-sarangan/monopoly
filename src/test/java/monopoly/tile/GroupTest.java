package monopoly.tile;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupTest {
    @Test
    public void GroupEnumDefaultValuesTest() {
        Set<String> groupNames = Set.of("BROWN", "LIGHT_BLUE", "PINK", "ORANGE", "RED", "YELLOW", "GREEN", "DARK_BLUE", "RAIL_ROAD", "UTILITIES", "NONE", "TAX", "CHEST");
        Group[] groups = Group.values();
        assertEquals(13, groups.length);
        for(Group group : groups) {
            assertTrue(groupNames.contains(group.name()));
        }
    }
}
