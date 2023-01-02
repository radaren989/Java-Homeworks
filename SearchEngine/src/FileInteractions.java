import java.io.File;
import java.util.Scanner;

public class FileInteractions {
    private String DELIMITERS;
    private String STOP_WORDS;

    FileInteractions() {
        this.STOP_WORDS = _GetStopWords();
        this.DELIMITERS = "[-+=" +

                " " + // space

                "\r\n " + // carriage return line fit

                "1234567890" + // numbers

                "’'\"" + // apostrophe

                "(){}<>\\[\\]" + // brackets

                ":" + // colon

                "," + // comma

                "‒–—―" + // dashes

                "…" + // ellipsis

                "!" + // exclamation mark

                "." + // full stop/period

                "«»" + // guillemets

                "-‐" + // hyphen

                "?" + // question mark

                "‘’“”" + // quotation marks

                ";" + // semicolon

                "/" + // slash/stroke

                "⁄" + // solidus

                "␠" + // space?

                "·" + // interpunct

                "&" + // ampersand

                "@" + // at sign

                "*" + // asterisk

                "\\" + // backslash

                "•" + // bullet

                "^" + // caret

                "¤¢$€£¥₩₪" + // currency

                "†‡" + // dagger

                "°" + // degree

                "¡" + // inverted exclamation point

                "¿" + // inverted question mark

                "¬" + // negation

                "#" + // number sign (hashtag)

                "№" + // numero sign ()

                "%‰‱" + // percent and related signs

                "¶" + // pilcrow

                "′" + // prime

                "§" + // section sign

                "~" + // tilde/swung dash

                "¨" + // umlaut/diaeresis

                "_" + // underscore/understrike

                "|¦" + // vertical/pipe/broken bar

                "⁂" + // asterism

                "☞" + // index/fist

                "∴" + // therefore sign

                "‽" + // interrobang

                "※" + // reference mark

                "]";
    }

    public void fillTable(HashTable<String> table, int[] txtWordCount) {
        for (int i = 1; i < 101; i++) {
            _ReadFile(i, table, txtWordCount);

        }
    }

    public void getSearchWords(String[] words) {
        try {
            // change the path for the seach.txt
            Scanner scn = new Scanner(new File("others/search.txt"));
            int index = 0;
            while (scn.hasNextLine()) {
                words[index] = scn.nextLine();
                index++;
            }
            scn.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void _ReadFile(int txtNumber, HashTable<String> table, int[] txtWordCount) {
        try {
            // change the path for the first 100 txt file
            Scanner scn = new Scanner(new File("texts/" + _GetTxtName(txtNumber)));
            while (scn.hasNext()) {
                String[] word = scn.next().toLowerCase().split(DELIMITERS);
                if (word.length != 0 && !STOP_WORDS.contains(word[0])) {
                    table.add(new Entry<>(word[0]), txtNumber);
                    txtWordCount[txtNumber]++;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private String _GetTxtName(int txtNumber) {
        return String.format("%03d", txtNumber) + ".txt";
    }

    private String _GetStopWords() {
        try {
            Scanner scn = new Scanner(new File("others/stop_words_en.txt"));
            String stopWords = "";
            // file starts witb a word and and ends with a word to make it symmetric i skip
            // the first word
            stopWords += scn.nextLine() + " ";
            while (scn.hasNextLine()) {
                // file has one word line and one empty line so i skip the empty line
                scn.nextLine();
                stopWords += scn.nextLine() + " ";
            }
            return stopWords;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
