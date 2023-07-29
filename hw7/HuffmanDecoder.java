import edu.princeton.cs.algs4.BinaryOut;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder {
    public static void main(String[] args) {
        String input = args[0];
        String output = args[1];
        ObjectReader or = new ObjectReader(input);

        Object o1 = or.readObject();
        Object o2 = or.readObject();
        BinaryTrie binaryTrie = (BinaryTrie) o1;
        BitSequence hugeBitSequence = (BitSequence) o2;

        List<Character> symbols = new ArrayList<>();
        while (hugeBitSequence.length() != 0) {
            Match match = binaryTrie.longestPrefixMatch(hugeBitSequence);
            symbols.add(match.getSymbol());
            int len = match.getSequence().length();
            hugeBitSequence = hugeBitSequence.allButFirstNBits(len);
        }

        BinaryOut bo = new BinaryOut(output);
        for (char symbol: symbols) {
            bo.write(symbol);
        }
        bo.close();
    }
}
