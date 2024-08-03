package testCases;

import extractor.PDFExtractor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import repository.DocumentRecord;
import utils.TestFailureLog;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Listeners({
        TestFailureLog.class
})
public class PdfConvertLogicTests {

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {

    }

    @Test(groups = "smoke")
    public void testPdfExtractor() {
        assertThat(new PDFExtractor().getDocumentRecordFromImportedPDF("src/test/resources/Amazon-S3-Tutorial.pdf")
                .getContent()).isNotEmpty();
    }
}
