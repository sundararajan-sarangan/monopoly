package monopoly.game;

public class StartingPlayersNames {
    private final String[] names;

    public StartingPlayersNames(String... names) throws Exception {
        if(names.length < 2 || names.length > 4) {
            throw new Exception();
        }
        for(String name : names) {
            if (name.equals("")) {
                throw new Exception();
            }
        }

        this.names = names;
    }

    public String[] getNames() {
        return names;
    }
}
