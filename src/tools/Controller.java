package tools;

import entities.Line;
import entities.Word;
import entities.WordSequence;
import filter.*;
import interfaces.Writeable;
import pipes.Pipe;

import java.io.*;
import java.util.List;

/**
 * Created by Jasmin on 06.11.2015.
 */
public class Controller {
    private String _sourceFile;
    private String _targetFile;

    public Controller(String sourceFile, String targetFile) throws IOException {
         _sourceFile = sourceFile;
        _targetFile = targetFile;
    }

    public void aPush(){

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(_sourceFile));
            bufferedWriter = new BufferedWriter(new FileWriter(_targetFile));

            boolean isPush = true;
            Sink<List<WordSequence>> sink = new Sink<>(bufferedWriter);
            Pipe<List<WordSequence>> pipe4 = new Pipe<>(sink);
            ABCOrderFilter_2<List<WordSequence>> abcdf = new ABCOrderFilter_2<>((Writeable<List<WordSequence>>) pipe4);
            Pipe<List<WordSequence>> pipe3 = new Pipe<>(abcdf, isPush);
            CirculationFilter<WordSequence, List<WordSequence>> cf = new CirculationFilter<>(pipe3);
            Pipe<WordSequence> pipe2 = new Pipe(cf, isPush);
            WordFilter2<Line, List<Word>> wf = new WordFilter2<>(pipe2);
            Pipe<Line> pipe1 = new Pipe(wf, isPush);
            LineFilter<Character, Line> lf = new LineFilter<>(pipe1);
            Source<Character> source = new Source(bufferedReader, lf);

            Thread thread = new Thread(source);
            thread.run();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void aPull(){

    }

    public void bPush(){

    }

    public void bPull(){

    }
}
