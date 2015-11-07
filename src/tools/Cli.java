package tools;

import org.apache.commons.cli.*;

/**
 * Created by Jasmin on 06.11.2015.
 */
public class Cli {
    private String[] _args = null;
    private Options options = new Options();

    public Cli(String[] args){
        _args = args;

        options.addOption("source", true, "Path or name of the source file");
        options.addOption("target", true, "Path or name of the target file");
        options.addOption("targetReformated", true, "Path or name of the target file");

        options.addOption("aPush", false, "Select the mode PUSH for Excercise A");
        options.addOption("aPull", false, "Select the mode PULL for Excercise A");
        options.addOption("bPush", false, "Select the mode PUSH for Excercise B");
        options.addOption("bPull", false, "Select the mode PULL for Excercise B");

        options.addOption("length", true, "Set the desired length of a line in words. Integer value!");
        options.addOption("align", true, "Set the desired alignment of the text. Values: left, center, right.");


        options.getOption("source").setRequired(true);

    }

    public CommandLine parse(){
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, _args);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cmd;
    }
}
