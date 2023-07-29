import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> map = new HashMap<>();
        for (char symbol: inputSymbols) {
            map.put(symbol, map.getOrDefault(symbol, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        String filename = args[0];
        char[] inputSymbols = FileUtils.readFile(filename);
        Map<Character, Integer> freTable = buildFrequencyTable(inputSymbols);
        BinaryTrie binaryTrie = new BinaryTrie(freTable);

        // write the binary decoding trie
        ObjectWriter ow = new ObjectWriter(filename + ".huf");
        ow.writeObject(binaryTrie);

        // encode
        Map<Character, BitSequence> lookupTable = binaryTrie.buildLookupTable();
        List<BitSequence> bitSequences = new ArrayList<BitSequence>();
        for (char symbol: inputSymbols) {
            bitSequences.add(lookupTable.get(symbol));
        }

        // write the bitSequences
        BitSequence hugeSequence = BitSequence.assemble(bitSequences);
        ow.writeObject(hugeSequence);
    }
}