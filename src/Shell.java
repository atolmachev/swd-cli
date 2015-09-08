import cmd.Cmd;

import java.util.*;

import static cmd.Cmd.fail;

public class Shell {
    private Map<String, Cmd> commands = new HashMap<>();

    private Shell() {
        commands.put("echo", (arg, options) -> System.out.println(arg));
        commands.put("pwd", (arg, options) -> System.out.println(System.getProperty("user.dir")));
        commands.put("exit", (arg, options) -> System.exit(0));
    }

    public static Builder builder() {
        return new Shell().new Builder();
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                execute(scanner.nextLine());
            }
        }
    }

    public void execute(String line) {
        if (line == null) return;
        String[] split = line.split("\\s+");
        Cmd cmd = commands.get(split[0]);
        if (cmd == null) {
            cmd = (arg, options) -> fail("command not found");
        }
        String arg = null;
        final Set<Character> options = new HashSet<>();
        for (int i = 1; i < split.length; i++) {
            if (split[i].startsWith("-")) {
                for (char c: split[i].toCharArray()) {
                    options.add(c);
                }
            } else if (arg != null) {
                cmd = (arg1, options1) -> fail("multiple arguments are not supported");
            } else {
                arg = split[i];
            }
        }

        try {
            cmd.execute(arg, options);
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
