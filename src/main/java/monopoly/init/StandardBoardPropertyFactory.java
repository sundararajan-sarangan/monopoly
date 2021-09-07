package monopoly.init;

import monopoly.tile.DevelopedLevel;
import monopoly.tile.Group;
import monopoly.tile.Property;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StandardBoardPropertyFactory {
    public Property makeProperty(List<String> record) {
        Costs costs = new Costs(
                Integer.parseInt(record.get(4)),
                Integer.parseInt(record.get(12)),
                Integer.parseInt(record.get(5)),
                Integer.parseInt(record.get(5)));

        Map<DevelopedLevel, Integer> rentMap = new HashMap<>();
        rentMap.put(DevelopedLevel.NO_HOUSES, Integer.parseInt(record.get(6)));
        rentMap.put(DevelopedLevel.ONE_HOUSE, Integer.parseInt(record.get(7)));
        rentMap.put(DevelopedLevel.TWO_HOUSES, Integer.parseInt(record.get(8)));
        rentMap.put(DevelopedLevel.THREE_HOUSES, Integer.parseInt(record.get(9)));
        rentMap.put(DevelopedLevel.FOUR_HOUSES, Integer.parseInt(record.get(10)));
        rentMap.put(DevelopedLevel.HOTEL, Integer.parseInt(record.get(11)));
        Rents rents = new Rents(rentMap);

        if("None".equals(record.get(2))) {
            if ("GoToJail".equals(record.get(1))) {
                return new GoToJailProperty(record.get(0), Group.valueOf(record.get(2).toUpperCase(Locale.ROOT)), costs, rents, DevelopedLevel.NO_HOUSES);
            } else {
                return new NoneProperty(record.get(0), Group.valueOf(record.get(2).toUpperCase(Locale.ROOT)), costs, rents, DevelopedLevel.NO_HOUSES);
            }
        }

        return new Property(record.get(0), Group.valueOf(record.get(2).toUpperCase(Locale.ROOT)), costs, rents, DevelopedLevel.NO_HOUSES);
    }

}
