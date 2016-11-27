package com.example.library.file.reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for reading from a files
 *
 * @author Liliya Rafikova
 */
public class FileRdr {

    private static final Log logger = LogFactory.getLog(FileRdr.class);
    private final String fileName;

    /**
     * Creates a new <tt>FileRdr</tt>, given the name of the
     * file to read from.
     *
     * @param fileName the name of the file to read from
     */
    public FileRdr(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Read all lines from a file as a {@code Stream}. Bytes from the file are
     * decoded into characters using the {@link StandardCharsets#UTF_8 UTF-8}
     * {@link Charset charset}.
     * method close is called automatically due to using{@code
     * try}-with-resources block
     *
     * @return list of lines from file
     */
    public List<String> readFromFile() {
        List<String> listOfString = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            listOfString = stream.collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Error while reading file", e);
        }
        return listOfString;
    }
}
