package monopoly.init;

import monopoly.api.Property;
import monopoly.board.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class StandardBoardMaker {
    private final static String COMMA_DELIMITER = ",";
    private final static String STANDARD_BOARD_DATA_FILE = "/StandardBoard.csv";
    private final StandardBoardPropertyFactory standardBoardPropertyFactory;

    public StandardBoardMaker() {
        this(new StandardBoardPropertyFactory());
    }

    public StandardBoardMaker(StandardBoardPropertyFactory standardBoardPropertyFactory) {
        this.standardBoardPropertyFactory = standardBoardPropertyFactory;
    }

    public Board makeBoard() {
        List<Property> properties = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(Objects.requireNonNull(getClass().getResource(STANDARD_BOARD_DATA_FILE)).getPath()))) {
            while (scanner.hasNextLine()) {
                List<String> record = getRecordFromLine(scanner.nextLine());
                if(record.get(0).startsWith("#")) {
                    // This is a comment.
                    continue;
                }

                Property property = standardBoardPropertyFactory.makeProperty(record);
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
