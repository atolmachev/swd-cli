import cmd.Cmd;

import java.io.*;
import java.util.*;

import static cmd.Cmd.fail;

public class Shell {
    private Map<String, Cmd> commands = new HashMap<>();

    private Shell() {
        commands.put("echo", (arg, options, in, out) -> System.out.println(arg));
        commands.put("pwd", (arg, options, in, out) -> System.out.println(System.getProperty("user.dir")));
        commands.put("exit", (arg, options, in, out) -> System.exit(0));
    }

    public static Builder builder() {
        return new Shell().new Builder();
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                executeLine(scanner.nextLine());
            }
        }
    }

    public void executeLine(String line) {
        String[] commands = line.split("\\|");
        InputStream in = System.in;
        PipedInputStream nextIn = null;
        PrintStream out = null;
        for (int i = 0; i < commands.length; i++) {
            if (out != null) out.close();
            if (i == commands.length - 1) {
                out = System.out;
            } else {
                nextIn = new PipedInputStream();
                try {
                    out = new PrintStream(new PipedOutputStream(nextIn));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            executeCommand(commands[i].trim(), in, out);
            in = nextIn;
        }
    }

    public void executeCommand(String line, InputStream in, PrintStream out) {
        if (line == null) return;
        String[] split = line.split("\\s+");
            Cmd cmd = commands.get(split[0]);
        if (cmd == null) {
            return;
        }
        String arg = null;
        final Set<Character> options = new HashSet<>();
        for (int i = 1; i < split.length; i++) {
            if (split[i].startsWith("-")) {
                for (char c: split[i].toCharArray()) {
                    options.add(c);
                }
            } else if (arg != null) {
                cmd = (arg1, options1, inp, o) -> fail("multiple arguments are not supported");
            } else {
                arg = split[i];
            }
        }

        try {
            cmd.execute(arg, options, in, out);
        } catch (Exception e) {
            System.err.println(String.format("%s: %s", split[0], e.getMessage()));
        }
    }

    public class Builder {
        public Builder on(String name, Cmd impl) {
            commands.put(name, impl);
            return this;
        }

        public Shell build() {
            return Shell.this;
        }
    }
}
