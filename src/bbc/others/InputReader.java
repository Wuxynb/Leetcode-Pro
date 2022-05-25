package bbc.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputReader {
    private final static int BUF_SZ = 65536;
    BufferedReader in;
    StringTokenizer tokenizer;

    public InputReader(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in), BUF_SZ);
        tokenizer = new StringTokenizer("");
    }

    private String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(in.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
