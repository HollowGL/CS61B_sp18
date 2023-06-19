/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int maxLen = 0;
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            sorted[i] = asciis[i];
            maxLen = Math.max(asciis[i].length(), maxLen);
        }
        for (int i = 0; i < maxLen; i++) {
            sortHelperLSD(sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] digits = new int[asciis.length];
        int max = 0;
        for (int i = 0; i < asciis.length; i++) {
            String s = asciis[i];
            if (index < s.length()) {
                digits[i] = (int) s.charAt(s.length() - index - 1);
                if (digits[i] > max) {
                    max = digits[i];
                }
            } else {
                digits[i] = 0;
            }
        }

        int[] count = new int[max + 1];
        for (int i : digits) {
            count[i]++;
        }

        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i <= max; i++) {
            starts[i] = pos;
            pos += count[i];
        }

        String[] sorted = new String[asciis.length];
        for (String s : sorted) {
            s = "";
        }
        for (int i = 0; i < asciis.length; i++) {
            int item = digits[i];
            int place = starts[item];
            sorted[place] = (char)item + sorted[place];
            starts[item] += 1;
        }
        asciis = sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] asciis = {"people", "cat", "dog", "mouse"};
        System.out.print("befor: ");
        for (String s : asciis) {
            System.out.print(s + " ");
        }
        System.out.println();
        String[] sorted = RadixSort.sort(asciis);
        System.out.print("after: ");
        for (String s : sorted) {
            System.out.print(s + " ");
        }
    }
}
