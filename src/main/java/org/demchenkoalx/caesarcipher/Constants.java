package org.demchenkoalx.caesarcipher;

public class Constants {

    /**
     * Uncomment lines with numbers or/and symbols to make cipher more complex.
     */
    public final String ALPHABET_EN =
//                    "0123456789" +
//                    " !'\"`’()*+-=_.,/|\\:;?#%@[]{}<>^~«»&$¡¢£¤¥¦§¨©®¬¯°±º×¿" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
    public final String[] COMMON_WORDS = {
            "the", "and", "you", "that", "was", "for", "are", "with", "his", "they", "this", "have", "from", "one",
            "had", "word", "but", "not", "what", "all", "were", "when", "your", "can", "said", "there", "use", "each",
            "which", "she", "how", "their", "will", "other", "about", "out", "many", "then", "them", "these", "some",
            "her", "would", "make", "like", "him", "into", "time", "has", "look", "two", "more", "write", "see",
            "number", "way", "could", "people", "than", "first", "water", "been", "call", "who", "oil", "its", "now",
            "find", "long", "down", "day", "did", "get", "come", "made", "may", "part", "over", "new", "sound", "take",
            "only", "little", "work", "know", "place", "year", "live", "back", "give", "most", "very", "after", "thing",
            "our", "just", "name", "good", "sentence", "man", "think", "say", "great", "where", "help", "through",
            "much", "before", "line", "right", "too", "means", "old", "any", "same", "tell", "boy", "follow", "came",
            "want", "show", "also", "around", "farm", "three", "small", "set", "put", "end", "pray", "exit", "four",
            "five", "six", "seven", "eight", "nine", "ten", "hundred", "thousand", "number", "letter"
    };
    public final String WRONG_ARGUMENT =
            """

                    >>>>>>> Invalid action or offset arguments entered.
                    >>>>>>> Starting console client.\
                    """;
    public final String MAIN_MENU =
            """

                     You could choose between actions listed below:
                         1 -> to encrypt file
                         2 -> to decrypt file
                         3 -> bruteforce cipher (currently not working)

                     Enter number of preferred action: \
                    """;
    public final String ENCRYPT_MENU = "\n Enter path to file to encrypt: ";
    public final String DECRYPT_MENU = "\n Enter path to file to decrypt: ";
    public final String OFFSET_MENU = "\n Enter cipher shift key: ";
    public final String WRONG_ACTION = "\n >>>>>>> Wrong action, please enter a number between 1 and 3.";
    public final String FILE_SAVED = "\n     Result file saved.";

    public int getAlphabetLength() {
        return ALPHABET_EN.length();
    }
}
