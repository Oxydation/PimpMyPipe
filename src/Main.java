import entities.Line;
import entities.Word;
import entities.WordSequence;
import filter.*;
import interfaces.Writeable;
import org.apache.commons.cli.CommandLine;
import pipes.Pipe;
import tools.Cli;
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

        //parse arguments with Apache Commons Cli
        Cli cli = new Cli(args);
        CommandLine cmd = cli.parse();

        //save source and target file
        if (cmd.hasOption("source")){
            sourceFile = cmd.getOptionValue("source");
            System.out.println("Sourcefile: " + sourceFile);

        }
        if (cmd.hasOption("target")){
            targetFile = cmd.getOptionValue("target");
            System.out.println("Targetfile: " + targetFile);
        }

        //handle mode argument
        Controller controller = new Controller(sourceFile, targetFile);

        if(cmd.hasOption("aPush")){
            System.out.println("Mode PUSH for exercise A");
            controller.aPush();
        }
        if(cmd.hasOption("aPull")){
            System.out.println("Mode PULL for exercise A");
            controller.aPull();
        }
        if(cmd.hasOption("bPush")){
            System.out.println("Mode PUSH for exercise B");
            controller.bPush();
        }
        if(cmd.hasOption("bPull")){
            System.out.println("Mode PULL for exercise B");
            controller.bPull();
        }
    }
}
