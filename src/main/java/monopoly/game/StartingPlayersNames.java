package monopoly.game;

import java.util.List;

public class StartingPlayersNames {
    private final List<String> names;

    public StartingPlayersNames(List<String> names) throws Exception {
        if(names.size() < 2 || names.size() > 4) {
            throw new Exception();
        }
        for(String name : names) {
            if (name.equals("")) {
                throw new Exception();
            }
        }

        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }
}
