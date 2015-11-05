import entities.Line;
import entities.Word;
import entities.WordSequence;
import filter.*;
import interfaces.Writeable;
import pipes.Pipe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String sourceFile = "apples.txt";

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(sourceFile));

            // PUSH
            Pipe<List<WordSequence>> pipe4 = new Pipe(null);
            ABCOrderFilter<List<WordSequence>> abcdf = new ABCOrderFilter<>((Writeable<List<WordSequence>>) pipe4);
            Pipe<List<WordSequence>> pipe3 = new Pipe(abcdf);
            CirculationFilter<List<Word>, List<WordSequence>> cf = new CirculationFilter<>(pipe3);
            Pipe<List<Word>> pipe2 = new Pipe(cf);
            WordFilter2<Line, List<Word>> wf = new WordFilter2<>(pipe2);
            Pipe<Line> pipe1 = new Pipe(wf);
            LineFilter<Character, Line> lf = new LineFilter<>(pipe1);
            Source<Character> source = new Source(bufferedReader, lf);

            Thread thread = new Thread(source);
            thread.run();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
}
