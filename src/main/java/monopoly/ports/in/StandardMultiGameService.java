package monopoly.ports.in;

import org.apache.commons.lang.RandomStringUtils;

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
        return RandomStringUtils.random(4, true, true);
    }
}
