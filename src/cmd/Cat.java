package cmd;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

public class Cat implements Cmd {
    @Override
    public void execute(String arg, Set<Character> options, InputStream in, PrintStream out) {
        try (Scanner scanner = new Scanner(arg == null ? in : new FileInputStream(arg))) {
            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new CmdFailed("%s: No such file or directory", arg);
        }
    }
}
