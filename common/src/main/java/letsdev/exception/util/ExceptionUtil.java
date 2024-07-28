package letsdev.exception.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class ExceptionUtil {
    public static String getStackTrace(Throwable exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    private ExceptionUtil() {}
}
