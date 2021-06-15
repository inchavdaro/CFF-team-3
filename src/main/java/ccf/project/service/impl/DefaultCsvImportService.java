package ccf.project.service.impl;

import ccf.project.service.CsvImportService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class DefaultCsvImportService implements CsvImportService {

    private final Logger logger = LoggerFactory.getLogger(DefaultCsvImportService.class);

    public <T> List<T> loadObjectList(Class<T> type, InputStream file) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file ", e);
            return Collections.emptyList();
        }
    }
}
