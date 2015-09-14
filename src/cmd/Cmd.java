package cmd;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Set;

public interface Cmd {
    void execute(String arg, Set<Character> options, InputStream in, PrintStream out);

    static void fail(String message) {
        throw new CmdFailed(message);
    }
}
