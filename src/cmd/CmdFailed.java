package cmd;

import static java.lang.String.format;

public class CmdFailed extends RuntimeException {
    public CmdFailed(Throwable cause) {
        super(cause);
    }

    public CmdFailed(String message) {
        super(message);
    }

    public CmdFailed(String format, Object... args) {
        this(format(format, args));
    }
}
