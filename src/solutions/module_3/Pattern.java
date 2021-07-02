package solutions.module_3;

import java.io.*;
import java.util.Stack;

public class Pattern {

    private static final int X = 263;
    private static final int P = 1_000_000_007;

    private final String pattern;
    private final int pLen;
    private final int pHash;

    public Pattern(String pattern) {
        this.pattern = pattern;
        this.pLen = pattern.length();
        this.pHash = hash(pattern);
    }

    public Stack<Integer> findIn(String text) {
        Stack<Integer> indices = new Stack<>();

        int txtLen = text.length();

        if (txtLen < pLen) {
            return indices;
        }

        txtLen -= pLen;

        long hash = hash(text.substring(txtLen));

        if (hash == pHash && patternEquals(text, txtLen)) {
            indices.push(txtLen);
        }

        long pow = 1;
        for (int i = 0; i < pLen - 1; i++) {
            pow = (pow * X) % P;
        }

        long h = (text.charAt(text.length() - 1) * pow) % P;

        for (int i = txtLen - 1; i >= 0; i--) {
            hash = hash - h;

            if (hash < 0) {
                hash += P;
            }

            hash = (hash * X) % P;
            hash += text.charAt(i);

            if (hash == pHash && patternEquals(text, i)) {
                indices.push(i);
            }

            h = (text.charAt(i + pLen - 1) * pow) % P;
        }
        return indices;
    }

    private static int hash(String string) {
        int hash = string.charAt(0);
        long x = X;

        for (int i = 1; i < string.length(); i++) {
            int product = (int) ((string.charAt(i) * x) % P);
            hash = (hash + product) % P;
            x = (x * X) % P;
        }
        return hash;
    }

    private boolean patternEquals(String str, int beginIndex) {
        for (int i = 0; i < pLen; i++) {
            if (pattern.charAt(i) != str.charAt(beginIndex + i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        // Zhanybekadiev
        // ZhanybekadievIIIIZhanybekadievIIIIZhanybekadiev

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Pattern pattern = new Pattern(br.readLine());
        Stack<Integer> indices = pattern.findIn(br.readLine());
        br.close();

        while (!indices.isEmpty()) {
            bw.write(Integer.toString(indices.pop()));
            bw.write(' ');
        }

        bw.write('\n');
        bw.close();
    }
}


