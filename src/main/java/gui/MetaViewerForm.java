package gui;

import converters.ConvertJavaMapToJson;
import extractor.PDFExtractor;
import repository.DocumentRecord;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import static utilities.LookAndFeelConstants.MAC_OSS_X;
import static utilities.PdfMetaViewerConstants.*;

/**
 * @author Alexxfromgit
 */
public class MetaViewerForm extends JFrame {
    File selectedFile;
    DocumentRecord documentRecord;
    private JPanel panelMain;
    private JButton choosePDFButton;
    private JButton readMetaButton;
    private JTextArea textArea1;
    private JButton saveAsJsonButton;
    private JLabel statusLabel;

    public MetaViewerForm() {
        choosePDFButton.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setMultiSelectionEnabled(false);
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().endsWith(EXTENSION_PDF);
                }

                @Override
                public String getDescription() {
                    return DESCRIPTION_PDF;
                }
            });
            jFileChooser.showOpenDialog(panelMain);
            selectedFile = jFileChooser.getSelectedFile();

            if (selectedFile != null && selectedFile.isFile()) {
                statusLabel.setText(DESCRIPTION_FILE_SELECTED);
                statusLabel.setForeground(Color.GREEN);
            } else {
                statusLabel.setText(DESCRIPTION_FILE_NOT_SELECTED);
                statusLabel.setForeground(Color.RED);
            }
        });

        readMetaButton.addActionListener(e -> {
            if (selectedFile != null) {
                documentRecord = new PDFExtractor().getDocumentRecordFromImportedPDF(selectedFile.getAbsolutePath());

                textArea1.setText(new ConvertJavaMapToJson()
                        .convertMapToJson(documentRecord.getMetadata())
                        .replace(",", "," + System.lineSeparator()));
                statusLabel.setText(DESCRIPTION_META_RETRIEVED);
                statusLabel.setForeground(Color.GREEN);
            } else {
                statusLabel.setText(DESCRIPTION_FILE_NOT_SELECTED);
                statusLabel.setForeground(Color.RED);
            }
        });

        saveAsJsonButton.addActionListener(e -> {
            if (statusLabel.getText().equals(DESCRIPTION_META_RETRIEVED)) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setMultiSelectionEnabled(false);
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jFileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.getName().endsWith(EXTENSION_JSON);
                    }

                    @Override
                    public String getDescription() {
                        return DESCRIPTION_JSON;
                    }
                });
                int userSelection = jFileChooser.showSaveDialog(panelMain);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = jFileChooser.getSelectedFile();
                    new PDFExtractor().storeDocumentRecordAs(documentRecord, fileToSave.getAbsolutePath(), false);
                    statusLabel.setText(DESCRIPTION_FILE_EXPORTED);
                    statusLabel.setForeground(Color.GREEN);
                }
            } else {
                statusLabel.setText(DESCRIPTION_META_NOT_RETRIEVED);
                statusLabel.setForeground(Color.RED);
            }
        });
    }

    public static void main(String[] args) {
        setUpFrame(new MetaViewerForm());
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(MAC_OSS_X);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    private static MetaViewerForm setUpFrame(MetaViewerForm jFrame) {
        jFrame.setContentPane(jFrame.panelMain);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle(APP_MAIN_TITLE);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        jFrame.setBounds(dimension.width / 2 - (WIDTH_APP / 2),
                dimension.height / 2 - (HEIGHT_APP / 2),
                WIDTH_APP, HEIGHT_APP);
        return jFrame;
    }

    // TODO:: jFrame.setIconImage();
    // TODO:: panelMain.setBackground(Color.BLUE);
    // TODO:: Font font = new Font("Bitstream Charter", Font.BOLD, 20);

}
