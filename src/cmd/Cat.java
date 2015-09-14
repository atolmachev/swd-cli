package cmd;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

public class Cat implements Cmd {
    @Override
    public void execute(String arg, Set<Character> options, InputStream in, PrintStream out) {
        try (Scanner scanner = createScanner(arg, in)) {
            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
            }
        }
    }

    private Scanner createScanner(String arg, InputStream in) {
        try {
            if (arg != null) {
                File file = new File(arg);
                if (file.isDirectory()) throw new CmdFailed("%s: Is a directory", arg);
                return new Scanner(file, "UTF-8");
            } else {
                return new Scanner(in);
            }
        } catch (FileNotFoundException e) {
            throw new CmdFailed("%s: No such file or directory", arg);
        }
    }
}
