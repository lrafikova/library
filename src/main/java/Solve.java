import com.example.library.file.reader.FileRdr;
import com.example.library.file.writer.FileWtr;
import com.example.library.service.LibraryService;

import java.util.List;

/**
 * Class for running application
 *
 * @author Liliya Rafikova
 */
public class Solve {

    public static void main(String[] arg) {
        FileRdr fileReader = new FileRdr("files/input/TC1.txt");
        FileWtr fileWriter = new FileWtr("files/output/TCActualResult.txt");
        LibraryService library = new LibraryService();
        List<String> borrowerList = library.getTop(fileReader.readFromFile());
        fileWriter.writeToFile(borrowerList);
    }
}


