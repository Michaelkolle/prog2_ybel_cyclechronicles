package cyclechronicles;

import java.io.IOException;
import java.util.logging.*;

public class ShopLogger {
    private static final Logger logger = Logger.getLogger("ShopLogger");

    static {
        try {
            FileHandler handler = new FileHandler("src/main/java/cyclechronicles/shoplog.csv", true);
            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format("%s,%s,%s,%s%n",
                        record.getLevel(),
                        record.getSourceMethodName(),
                        record.getSourceClassName(),
                        record.getMessage());
                }
            });
            logger.addHandler(handler);
            logger.setUseParentHandlers(false); // Deaktiviert Konsolenausgabe
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}

