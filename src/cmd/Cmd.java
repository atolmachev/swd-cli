package cmd;

import java.util.Set;

public interface Cmd {
    void execute(String arg, Set<Character> options);

    static void fail(String message) {
        throw new CmdFailed(message);
    }
}
