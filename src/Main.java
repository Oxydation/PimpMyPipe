import entities.Line;
import entities.Word;
import filter.LineFilter;
import filter.Source;
import filter.WordFilter;
import filter.WordFilter2;
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
            Pipe<Line> pipe2 = new Pipe(null);
            WordFilter<Line, List<Word>> wf = new WordFilter<>(pipe2);
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
