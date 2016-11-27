package com.example.library.service;

import com.example.library.configuration.CommonConfiguration;
import com.example.library.configuration.Configuration;
import com.example.library.exception.IncorrectFormatException;
import com.example.library.model.Borrower;
import com.example.library.util.Utils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class service which contain the main functions for operation for Library
 *
 * @author Liliya Rafikova
 */
public class LibraryService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final String REGEX = "[A-z]+\\s[A-z]+~[A-z].*~(19|20)\\d\\d-(0?[0-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
    private final CommonConfiguration commonConfiguration = Configuration.INSTANCE.getCommonConfiguration();

    /**
     * Method parse file which has specific format (@see REGEX) otherwise
     * will throw IncorrectFormatException
     *
     * @param dataFromFile data which was read from file
     * @return map key = name of borrower value= debt on current date
     */
    private Map<String, BigDecimal> parseDataFromFile(List<String> dataFromFile) {
        Map<String, BigDecimal> mapOfBorrowers = new HashMap<>();
        for (String line : dataFromFile) {
            if (!line.matches(REGEX)) {
                throw new IncorrectFormatException("Couldn't parse data from file due to incorrect form." + line);
            }
            String[] arrOfLine = line.split("~");
            int months = Utils.diffMonths(DateTime.parse(arrOfLine[2], DATE_TIME_FORMATTER), DateTime.now());
            if (mapOfBorrowers.get(arrOfLine[0]) == null) {
                mapOfBorrowers.put(arrOfLine[0], Utils.countDebt(months, commonConfiguration.rentFee(),
                        commonConfiguration.discount()));
            } else {
                mapOfBorrowers.put(arrOfLine[0], mapOfBorrowers.get(arrOfLine[0]).
                        add(Utils.countDebt(months, commonConfiguration.rentFee(), commonConfiguration.rentFee())));
            }
        }
        return mapOfBorrowers;
    }

    /**
     * Map move to list and sort it by debt from borrower who owe the most money
     *
     * @param dataFromFile dataFromFile data which was read from file
     * @return list of Borrower
     */
    public List<Borrower> getListOfBorrower(List<String> dataFromFile) {
        List<Borrower> listOfBorrower = new ArrayList<>();
        listOfBorrower.addAll(parseDataFromFile(dataFromFile).entrySet().stream().
                map(entry -> new Borrower(entry.getKey(), entry.getValue())).collect(Collectors.toList()));
        Collections.sort(listOfBorrower, (o1, o2) -> o2.getDebt().compareTo(o1.getDebt()));
        return listOfBorrower;
    }

    /**
     * Return the list of 5 borrowers who owe the most money or the whole list if the size() is less than 5
     *
     * @param dataFromFile dataFromFile data which was read from file
     * @return list of name
     */
    public List<String> getTop(List<String> dataFromFile) {
        List<String> listOfName = getListOfBorrower(dataFromFile).stream().map(Borrower::getName)
                .collect(Collectors.toList());
        return listOfName.size() > commonConfiguration.top() ?
                listOfName.subList(0, commonConfiguration.top()) : listOfName;
    }

}
