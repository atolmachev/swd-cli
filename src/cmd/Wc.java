package cmd;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

public class Wc implements Cmd {
    @Override
    public void execute(String arg, Set<Character> options, InputStream in, PrintStream out) {
        int lines = 0, words = 0, bytes = 0;

        try (Scanner scanner = new Scanner(arg == null ? in : new FileInputStream(arg))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines++;
                bytes += line.getBytes().length;
                words += line.split("\\s+").length;
            }
        } catch (FileNotFoundException e) {
            throw new CmdFailed("%s: No such file or directory", arg);
        }

        out.format("%d %d %d", lines, words, bytes);
    }
}
