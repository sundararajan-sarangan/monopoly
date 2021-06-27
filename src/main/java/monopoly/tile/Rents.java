package monopoly.tile;

import java.util.Map;

public class Rents {
    Map<DevelopedLevel, Integer> developedLevelToRentMap;

    public Rents(Map<DevelopedLevel, Integer> developedLevelToRentMap) {
        this.developedLevelToRentMap = developedLevelToRentMap;
    }
}
