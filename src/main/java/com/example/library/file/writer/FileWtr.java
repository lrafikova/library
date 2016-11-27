package com.example.library.file.writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class for writing to a files
 *
 * @author Liliya Rafikova
 */
public class FileWtr {

    private static final Log logger = LogFactory.getLog(FileWtr.class);
    private final String fileName;

    /**
     * Creates a new <tt>FileWtr</tt>, given the name of the
     * file to write to.
     *
     * @param fileName the name of the file to read from
     */
    public FileWtr(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Opens or creates a file for writing.
     * The text is encoded
     * into bytes for writing using the {@link StandardCharsets#UTF_8 UTF-8}.
     * method close is called automatically due to using{@code
     * try}-with-resources block
     * {@link Charset charset}.
     *
     * @param list the list of data which need to write
     */
    public void writeToFile(List<String> list) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (String value : list) {
                writer.write(value);
                writer.newLine();
            }
        } catch (IOException e) {
            logger.error("Could not write to file.", e);
        }
    }
}
