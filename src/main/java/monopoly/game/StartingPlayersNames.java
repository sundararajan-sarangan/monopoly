package monopoly.game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartingPlayersNames {
    private final List<String> names;

    public StartingPlayersNames(List<String> names) throws Exception {
        Set<String> seenNames = new HashSet<>();
        if(names.size() < 2 || names.size() > 4) {
            throw new Exception();
        }
        for(String name : names) {
            if (name.equals("")) {
                throw new Exception();
            }

            if(seenNames.contains(name)) {
                throw new Exception("Two players cannot have the same name");
            } else {
                seenNames.add(name);
            }
        }

        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }
}
