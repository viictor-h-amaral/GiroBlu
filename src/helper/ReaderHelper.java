package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderHelper {

    public static BufferedReader criarLeitor(File arquivo) throws IOException {
        return new BufferedReader(
                new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
    }
}
