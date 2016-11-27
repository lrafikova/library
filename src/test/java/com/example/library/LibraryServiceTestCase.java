package com.example.library;

import com.example.library.exception.IncorrectFormatException;
import com.example.library.file.reader.FileRdr;
import com.example.library.file.writer.FileWtr;
import com.example.library.model.Borrower;
import com.example.library.service.LibraryService;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * A set of tests
 *
 * @author Liliya Rafikova
 */
public class LibraryServiceTestCase {

    /**
     * Getting Borrowers from input file counting debt
     * and write to file mathcing with actual result and expected result
     * from file
     */
    @Test
    public void getListTop5TC1() {
        FileRdr fileReader = new FileRdr("files/input/TC1.txt");
        FileWtr fileWriter = new FileWtr("files/output/TC1ActualResult.txt");
        LibraryService library = new LibraryService();
        List<String> borrowerList = library.getTop(fileReader.readFromFile());
        fileWriter.writeToFile(borrowerList);
        List<String> actualResult = new FileRdr("files/output/TC1ActualResult.txt").readFromFile();
        List<String> expectedResult = new FileRdr("files/input/TC1ExpectedResult.txt").readFromFile();
        assertThat("The size is not matched", borrowerList, hasSize(5));
        assertThat("The actual result doesn't matche expected result", actualResult,
                contains(expectedResult.toArray()));
    }

    /**
     * Input file is empty
     * Output file should also be empty
     */
    @Test
    public void emptyFileTC2() {
        FileRdr fileReader = new FileRdr("files/input/TC2.txt");
        FileWtr fileWriter = new FileWtr("files/output/TC2ActualResult.txt");
        LibraryService library = new LibraryService();
        List<String> borrowerList = library.getTop(fileReader.readFromFile());
        fileWriter.writeToFile(borrowerList);
        List<String> actualResult = new FileRdr("files/output/TC2ActualResult.txt").readFromFile();
        assertThat("The list is not empty. ", borrowerList, is(empty()));
        assertThat("The file is not empty. ", actualResult, is(empty()));
    }

    /**
     * Expected exception if input file has incorrect format
     */
    @Test(expectedExceptions = IncorrectFormatException.class)
    public void checkFormatExceptionTC3() {
        FileRdr fileReader = new FileRdr("files/input/TC3.txt");
        LibraryService library = new LibraryService();
        library.getTop(fileReader.readFromFile());
    }

    /**
     * Check that if one person rent a few books the debt counts correct
     */
    @Test
    public void checkWithOneBorrowerAndManyBooksTC4() {
        FileRdr fileReader = new FileRdr("files/input/TC4.txt");
        FileWtr fileWriter = new FileWtr("files/output/TC4ActualResult.txt");
        LibraryService library = new LibraryService();
        List<String> borrowerList = library.getTop(fileReader.readFromFile());
        fileWriter.writeToFile(borrowerList);
        List<String> actualResult = new FileRdr("files/output/TC4ActualResult.txt").readFromFile();
        List<String> expectedResult = new FileRdr("files/input/TC4ExpectedResult.txt").readFromFile();
        assertThat("The size is not matched", borrowerList, hasSize(3));
        assertThat("The actual result doesn't match expected result", actualResult,
                contains(expectedResult.toArray()));
    }

    /**
     * the start date is in future
     * expected exception
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void futureDateTC5() {
        FileRdr fileReader = new FileRdr("files/input/TC5.txt");
        LibraryService library = new LibraryService();
        library.getTop(fileReader.readFromFile());
    }

    /**
     * Checked that first month should be free for borrowers
     */
    @Test
    public void checkThatFirstMonthFreeTC6() {
        String fileInput = "files/input/TC6.txt";
        FileRdr fileReader = new FileRdr(fileInput);
        LibraryService library = new LibraryService();
        List<Borrower> borrowerList = library.getListOfBorrower(fileReader.readFromFile());
        assertThat("The first month should be free, please check start date.", borrowerList.get(0).getDebt(),
                is(new BigDecimal(0)));
    }

    /**
     * Checked that debt counts correct
     */
    @Test
    public void checkThatCountRentForFewBooksTC7() {
        String fileInput = "files/input/TC7.txt";
        FileRdr fileReader = new FileRdr(fileInput);
        LibraryService library = new LibraryService();
        List<Borrower> borrowerList = library.getListOfBorrower(fileReader.readFromFile());
        assertThat("The first month should be free, please check start date.", borrowerList.get(0).getDebt(),
                is(new BigDecimal(10)));
    }
}



