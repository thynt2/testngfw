package browsers;

public class BrowserNotSupport extends IllegalStateException {
    private static final long serialVersionUID = 1L;

    public BrowserNotSupport(String browser) {
        super(String.format("Browser not supported: %s", browser));
    }
}

