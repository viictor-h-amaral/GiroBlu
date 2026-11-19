package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriterHelper {

    public static BufferedWriter criarEscritor(File arquivo) throws IOException {
        return new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));
    }
}
