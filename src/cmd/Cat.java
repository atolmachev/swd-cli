package cmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Cat implements Cmd {
    @Override
    public void execute(String arg, Set<Character> options) {
        try (Scanner scanner = new Scanner(new File(arg))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new CmdFailed("%s: No such file or directory", arg);
        }
    }
}
