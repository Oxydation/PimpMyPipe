import entities.Line;
import entities.Word;
import filter.LineFilter;
import filter.Source;
import filter.WordFilter;
import pipes.Pipe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String sourceFile = "aliceInWonderland.txt";

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(sourceFile));
            Source<Character> source = new Source(bufferedReader);

            LineFilter<Character, Line> lf = new LineFilter<>(source);

            lf.run();

           WordFilter<Line, Word> wf = new WordFilter();
            Pipe<LineFilter, WordFilter> pipe1 = new Pipe<>(lf, wf);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
}
