import org.apache.commons.cli.CommandLine;
import tools.Cli;
import tools.Controller;

import java.io.*;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        String sourceFile = "aliceInWonderland.txt";
//        //default target name if no name is given as parameter
//        String targetFile = "index_output.txt";
//        String targetFile2 = "reformated_text.txt";

        int amountLineChars = 80;

        //parse arguments with Apache Commons Cli
        Cli cli = new Cli(args);
        CommandLine cmd = cli.parse();

        // Get data from command line and create a controller
        Controller controller = new Controller(cli.getSourceFile(), cli.getTargetFile(), cli.getTargetReformated(), cli.getLength(), cli.getAlignment(), amountLineChars);

        //handle mode argument
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
