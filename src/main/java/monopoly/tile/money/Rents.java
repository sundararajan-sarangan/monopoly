package monopoly.tile.money;

import monopoly.tile.DevelopedLevel;

import java.util.Map;

public class Rents {
    private Map<DevelopedLevel, Integer> developedLevelToRentMap;

    public Rents(Map<DevelopedLevel, Integer> developedLevelToRentMap) {
        this.developedLevelToRentMap = developedLevelToRentMap;
    }

    public Map<DevelopedLevel, Integer> developedLevelToRentMap() {
        return this.developedLevelToRentMap;
    }
}
