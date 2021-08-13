package monopoly.init;

import monopoly.board.Board;
import monopoly.tile.DevelopedLevel;
import monopoly.tile.Group;
import monopoly.tile.Property;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StandardBoardMaker {
    private final static String COMMA_DELIMITER = ",";
    private final static String STANDARD_BOARD_DATA_FILE = "/StandardBoard.csv";
    public Board makeBoard() {
        List<Property> properties = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(Objects.requireNonNull(getClass().getResource(STANDARD_BOARD_DATA_FILE)).getPath()))) {
            while (scanner.hasNextLine()) {
                List<String> record = getRecordFromLine(scanner.nextLine());
                if(record.get(0).startsWith("#")) {
                    // This is a comment.
                    continue;
                }

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
                Property property = new Property(record.get(0), Group.valueOf(record.get(2).toUpperCase(Locale.ROOT)), costs, rents, DevelopedLevel.NO_HOUSES);
                properties.add(property);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return new Board(properties);
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }

        return values;
    }
}
