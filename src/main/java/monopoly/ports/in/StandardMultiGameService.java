package monopoly.ports.in;

class StandardMultiGameService implements MultiGameService {
    private StandardMultiGameService() {
    }

    private static class LazyHolder {
        static final StandardMultiGameService INSTANCE = new StandardMultiGameService();
    }

    public static StandardMultiGameService getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public String prepareNewGame() {
        return "";
    }
}
