import cmd.Cat;
import cmd.Wc;

public class Cli {
    static Shell shell;

    static {
        shell = Shell.builder()
                .on("cat", new Cat())
                .on("wc", new Wc())
                .build();
    }

    public static void main(String[] args) {
        shell.start();
    }
}
