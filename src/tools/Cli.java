package tools;

import enumerations.Alignment;
import org.apache.commons.cli.*;

/**
 * Created by Jasmin on 06.11.2015.
 */
public class Cli {
    private String[] _args = null;
    private Options options = new Options();

    private String _sourceFile;
    private String _targetFile;
    private String _targetReformated;
    private int _length;
    private Alignment _alignment;


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
        options.addOption("align", true, "Set the desired alignment of the text. Values: LEFT, CENTER, RIGHT.");


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
        init(cmd);
        return cmd;
    }

    private void init(CommandLine cmd){
        //save source and target file
        if (cmd.hasOption("source")){
            _sourceFile = cmd.getOptionValue("source");
            System.out.println("Sourcefile: " + _sourceFile);
        }else{
            System.out.println("No source file found! Please add parameter -source and restart application.");
            System.exit(0);
        }

        _targetFile = "index_output.txt";
        if (cmd.hasOption("target")){
            _targetFile = cmd.getOptionValue("target");
            System.out.println("Targetfile: " + _targetFile);
        }else{
            System.out.println("Default target file: index_output.txt");
           if(_targetFile == null || _targetFile.equals("")){
               _targetFile = "index_output.txt";
           }
        }


        _targetReformated = "reformated_text.txt";
        if (cmd.hasOption("targetReformated")){
            _targetReformated = cmd.getOptionValue("targetReformated");
            System.out.println("Targetfile2: " + _targetReformated);
        }else{
            System.out.println("Default target file: reformated_text.txt");
            if(_targetReformated == null || _targetReformated.equals("")){
                _targetReformated = "reformated_text.txt";
            }
        }

        if(cmd.hasOption("align")){
            switch(cmd.getOptionValue("align").toUpperCase()){
                case "LEFT":
                    _alignment = Alignment.LEFT;
                    break;
                case "CENTER":
                    _alignment = Alignment.CENTER;
                    break;
                case "RIGHT":
                    _alignment = Alignment.RIGHT;
                    break;
                default:
                    System.out.println("No correct value for alignment found: used default LEFT");
                    _alignment = Alignment.LEFT;
            }
        }else{
            System.out.println("No value for alignment found: used default LEFT");
            _alignment = Alignment.LEFT;
        }

        _length = 5;
        if(cmd.hasOption("length")){
            try {
                _length = Integer.parseInt(cmd.getOptionValue("length"));
            }catch(Exception e){
                System.out.println("length-value was not a number! Used default 5 instead.");
                _length = 5;
            }
        }else{
            System.out.println("No value for length found: used default 5");
        }
    }

    //Getter

    public String getSourceFile() {
        return _sourceFile;
    }

    public String getTargetFile() {
        return _targetFile;
    }

    public String getTargetReformated() {
        return _targetReformated;
    }

    public int getLength() {
        return _length;
    }

    public Alignment getAlignment() {
        return _alignment;
    }
}
