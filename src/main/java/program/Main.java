package program;

import extractor.PDFExtractor;

public class Main {

    public static void main(String[] args) {
        new PDFExtractor().importPDF("src/main/resources/Amazon-S3-Tutorial.pdf", false);
    }
}
