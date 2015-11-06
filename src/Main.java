import entities.Line;
import entities.Word;
import entities.WordSequence;
import filter.*;
import interfaces.Writeable;
import pipes.Pipe;
import tools.Controller;

import java.io.*;
import java.util.List;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String sourceFile = "aliceInWonderland.txt";
        String targetFile = "index_aliceInWonderland.txt";

        Controller controller = new Controller(sourceFile, targetFile);
        controller.aPush();
    }
}
