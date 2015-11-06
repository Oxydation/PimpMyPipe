package tools;

import entities.Line;
import entities.Word;
import entities.WordSequence;
import filter.*;
import interfaces.Writeable;
import pipes.Pipe;
import pipes.SplitPipe;

import java.io.*;
import java.util.List;

/**
 * Created by Jasmin on 06.11.2015.
 */
public class Controller {
    private String _sourceFile;
    private String _targetFile;
    private String _targetFile2;

    public Controller(String sourceFile, String targetFile) {
        _sourceFile = sourceFile;
        _targetFile = targetFile;
    }

    public Controller(String sourceFile, String targetFile, String targetFile2) {
        _sourceFile = sourceFile;
        _targetFile = targetFile;
        _targetFile2 = targetFile2;
    }

    /**
     * Using a push pipe, ...
     */
    public void aPush() {

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
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void aPull() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(_sourceFile));
            bufferedWriter = new BufferedWriter(new FileWriter(_targetFile));

            boolean isPush = false;

            Source<Character> source = new Source(bufferedReader);
            LineFilter<Character, Line> lf = new LineFilter<>(source);
            Pipe<Line> pipe1 = new Pipe(lf, isPush);
            WordFilter2<Line, List<Word>> wf = new WordFilter2<>(pipe1);
            Pipe<WordSequence> pipe2 = new Pipe(wf, isPush);
            CirculationFilter<WordSequence, List<WordSequence>> cf = new CirculationFilter<>(pipe2);
            Pipe<List<WordSequence>> pipe3 = new Pipe((interfaces.Readable) cf);
            ABCOrderFilter_2<List<WordSequence>> abcdf = new ABCOrderFilter_2((interfaces.Readable<List<WordSequence>>) pipe3);
            Pipe<List<WordSequence>> pipe4 = new Pipe(abcdf, isPush);
            Sink<List<WordSequence>> sink = new Sink<>(bufferedWriter, pipe4);

            Thread thread = new Thread(sink);
            thread.run();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void bPush() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        BufferedWriter bufferedWriter2 = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(_sourceFile));
            bufferedWriter = new BufferedWriter(new FileWriter(_targetFile));
            bufferedWriter2 = new BufferedWriter(new FileWriter(_targetFile2));

            boolean isPush = true;
            Sink<List<WordSequence>> sink = new Sink<>(bufferedWriter);
            SinkB<WordSequence> sinkReformatedFile = new SinkB<>(bufferedWriter2);

            Pipe<List<WordSequence>> pipe4 = new Pipe<>(sink);
            ABCOrderFilter_2<List<WordSequence>> abcdf = new ABCOrderFilter_2<>((Writeable<List<WordSequence>>) pipe4);
            Pipe<List<WordSequence>> pipe3 = new Pipe<>(abcdf, isPush);
            CirculationFilter<WordSequence, List<WordSequence>> cf = new CirculationFilter<>(pipe3);

            Pipe<WordSequence> subPipe2 = new Pipe(sinkReformatedFile);
            Pipe<WordSequence> subPipe1 = new Pipe(cf, isPush);
            SplitPipe<WordSequence> mainPipe = new SplitPipe<>(subPipe1, subPipe2);
            LineConstructorFilter<Word, WordSequence> wf = new LineConstructorFilter<>(mainPipe);
            wf.setWordsLimit(4);

            Pipe<Word> pipe1 = new Pipe(wf, isPush);
            WordConstructorFilter<Character, Word> lf = new WordConstructorFilter<>(pipe1);
            Source<Character> source = new Source(bufferedReader, lf);

            Thread thread = new Thread(source);
            thread.run();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void bPull() {

    }
}
