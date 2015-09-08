package cmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Wc implements Cmd {
    @Override
    public void execute(String arg, Set<Character> options) {
        int lines = 0, words = 0;

        File file = new File(arg);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines++;
                words += line.split("\\s+").length;
            }
        } catch (FileNotFoundException e) {
            throw new CmdFailed("%s: No such file or directory", arg);
        }

        System.out.format("%d %d %d %s", lines, words, new File(arg).length(), arg);
    }
}
