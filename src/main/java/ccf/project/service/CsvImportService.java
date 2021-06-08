package ccf.project.service;

import java.io.InputStream;
import java.util.List;

public interface CsvImportService {

    /**
     * Reads file in csv format and gets list of requested Class type if no errors occur
     *
     * @param type Class which to be read from the file
     * @param file : InputStream of file to be imported
     * @return list of read objects
     */
    <T> List<T> loadObjectList(Class<T> type, InputStream file);
}
