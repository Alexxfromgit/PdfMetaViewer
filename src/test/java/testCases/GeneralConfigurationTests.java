package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestFailureLog;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static utilities.PdfMetaViewerConstants.*;

@Listeners({
        TestFailureLog.class
})
public class GeneralConfigurationTests {

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {

    }

    @Test(groups = "smoke")
    public void testGetAppTitle() {
        assertThat(APP_MAIN_TITLE).isEqualToIgnoringCase("Pdf Meta Viewer");
    }

    @Test(groups = "smoke")
    public void testGetAppDefaultDimensions() {
        assertThat(WIDTH_APP).isEqualTo(800);
        assertThat(HEIGHT_APP).isEqualTo(500);
    }
}
