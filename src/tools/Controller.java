package tools;

import entities.Line;
import entities.Word;
import entities.WordSequence;
import enumerations.Alignment;
import filter.*;
import interfaces.Writeable;
import pipes.Pipe;
import pipes.SplitPipe;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jasmin on 06.11.2015.
 */
public class Controller {
    public static final String[] SUPPRESSED_WORDS = new String[]{
            "and", "if", "but", "as", "when", "with", "so", "in","is","of","at",
            "for", "on", "or", "at", "be", "been", "up",
            "tp", "by", "do", "did", "had", "have", "he",
            "she", "it", "i", "may", "me", "of", "the", "this", "a"
    };
    private String _sourceFile;
    private String _targetFile;
    private String _targetFile2;
    private int _amountLineChars;
    private int _lineLength;
    private Alignment _alignment;

    public Controller(String sourceFile, String targetFile) {
        _sourceFile = sourceFile;
        _targetFile = targetFile;
    }

    public Controller(String sourceFile, String targetFile, String targetFile2, int length, Alignment alignment, int amountLineChars) {
        _sourceFile = sourceFile;
        _targetFile = targetFile;
        _targetFile2 = targetFile2;
        _lineLength = length;
        _alignment = alignment;
        _amountLineChars = amountLineChars;
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
            ABCOrderFilter<List<WordSequence>> orderFilter = new ABCOrderFilter<>((Writeable<List<WordSequence>>) pipe4);
            Pipe<List<WordSequence>> pipe3 = new Pipe<>(orderFilter, isPush);
            CirculationFilter<WordSequence, List<WordSequence>> cf = new CirculationFilter<>(pipe3);
            cf.setSuppressedIndexWords(Arrays.asList(SUPPRESSED_WORDS));

            Pipe<WordSequence> pipe2 = new Pipe(cf, isPush);
            WordFilter2<Line, List<Word>> wordFilter = new WordFilter2<>(pipe2);
            Pipe<Line> pipe1 = new Pipe(wordFilter, isPush);
            LineFilter<Character, Line> lineFilter = new LineFilter<>(pipe1);
            Source<Character> source = new Source(bufferedReader, lineFilter);

            // Start processing
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
            LineFilter<Character, Line> lineFilter = new LineFilter<>(source);
            Pipe<Line> pipe1 = new Pipe(lineFilter, isPush);
            WordFilter2<Line, List<Word>> wordFilter = new WordFilter2<>(pipe1);
            Pipe<WordSequence> pipe2 = new Pipe(wordFilter, isPush);
            CirculationFilter<WordSequence, List<WordSequence>> circulationFilter = new CirculationFilter<>(pipe2);
            circulationFilter.setSuppressedIndexWords(Arrays.asList(SUPPRESSED_WORDS));

            Pipe<List<WordSequence>> pipe3 = new Pipe((interfaces.Readable) circulationFilter);
            ABCOrderFilter<List<WordSequence>> orderFilter = new ABCOrderFilter((interfaces.Readable<List<WordSequence>>) pipe3);
            Pipe<List<WordSequence>> pipe4 = new Pipe(orderFilter, isPush);
            Sink<List<WordSequence>> sink = new Sink<>(bufferedWriter, pipe4);

            // Start processing
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
            ABCOrderFilter<List<WordSequence>> abcdf = new ABCOrderFilter<>((Writeable<List<WordSequence>>) pipe4);
            Pipe<List<WordSequence>> pipe3 = new Pipe<>(abcdf, isPush);
            CirculationFilter<WordSequence, List<WordSequence>> cf = new CirculationFilter<>(pipe3);
            cf.setSuppressedIndexWords(Arrays.asList(SUPPRESSED_WORDS));

            Pipe<WordSequence> subPipe2 = new Pipe(sinkReformatedFile);
            Pipe<WordSequence> subPipe1 = new Pipe(cf, isPush);
            SplitPipe<WordSequence> mainPipe = new SplitPipe<>(subPipe1, subPipe2);
            LineConstructorFilter<Word, WordSequence> lineConstructorFilter = new LineConstructorFilter<>(mainPipe);
            lineConstructorFilter.setWordsLimit(_lineLength);

            Pipe<Word> pipe1 = new Pipe(lineConstructorFilter, isPush);
            WordConstructorFilter<Character, Word> lf = new WordConstructorFilter<>(pipe1);
            Source<Character> source = new Source(bufferedReader, lf);

            // Start processing
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
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        BufferedWriter bufferedWriter2 = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(_sourceFile));
            bufferedWriter = new BufferedWriter(new FileWriter(_targetFile));
            bufferedWriter2 = new BufferedWriter(new FileWriter(_targetFile2));

            boolean isPush = false;

            Source<Character> source = new Source(bufferedReader);
            WordConstructorFilter<Character, Word> wordConstructorFilter = new WordConstructorFilter<>(source);
            Pipe<Word> pipe1 = new Pipe(wordConstructorFilter, isPush);
            LineConstructorFilter<Word, WordSequence> lineConstructorFilter = new LineConstructorFilter<>(bufferedWriter2, pipe1);
            lineConstructorFilter.setWordsLimit(_lineLength);
            Pipe<WordSequence> pipe2 = new Pipe(lineConstructorFilter, isPush);
            CirculationFilter<WordSequence, List<WordSequence>> circulationFilter = new CirculationFilter<>(pipe2);
            circulationFilter.setSuppressedIndexWords(Arrays.asList(SUPPRESSED_WORDS));
            Pipe<List<WordSequence>> pipe3 = new Pipe((interfaces.Readable) circulationFilter);
            ABCOrderFilter<List<WordSequence>> orderFilter = new ABCOrderFilter((interfaces.Readable<List<WordSequence>>) pipe3);
            Pipe<List<WordSequence>> pipe4 = new Pipe(orderFilter, isPush);
            Sink<List<WordSequence>> sink = new Sink<>(bufferedWriter, pipe4);

            // Start processing
            Thread thread1 = new Thread(sink);
            thread1.run();

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
}
