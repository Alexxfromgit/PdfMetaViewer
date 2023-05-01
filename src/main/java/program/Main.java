package program;

import extractor.PDFExtractor;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.EventHandler;
import java.io.File;

public class Main {

    static JFrame jFrame = GetFrame();
    static JPanel jPanel = new JPanel();
    public static int widthApp = 500;
    public static int heightapp = 500;

    public static void main(String[] args) {

        JFrame jFrame = GetFrame();
        Font font = new Font("Bitstream Charter", Font.BOLD, 20);


        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        JButton button = new JButton("Choose file..");
        jPanel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");

                    JFileChooser jFileChooser = new JFileChooser();
                    jFileChooser.setMultiSelectionEnabled(false);
                    jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    jFileChooser.setFileFilter(new FileFilter() {
                        @Override
                        public boolean accept(File f) {
                            if (f.getName().endsWith(".pdf")) return true;
                            return false;
                        }

                        @Override
                        public String getDescription() {
                            return "Only PDF";
                        }
                    });
                    jFileChooser.showOpenDialog(jPanel);

                    File selectedFile = jFileChooser.getSelectedFile();


                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (InstantiationException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                }
                jPanel.setBackground(Color.BLUE);

            }
        });

        jPanel.revalidate();

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int i = 0;
            }
        });

        /*

        Metal
javax.swing.plaf.metal.MetalLookAndFeel
Nimbus
javax.swing.plaf.nimbus.NimbusLookAndFeel
CDE/Motif
com.sun.java.swing.plaf.motif.MotifLookAndFeel
Mac OS X
com.apple.laf.AquaLookAndFeel
         */

        UIManager.LookAndFeelInfo[] lookAndFeelInfo = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo lookAndFeelInfo1:
             lookAndFeelInfo) {
            System.out.println(lookAndFeelInfo1.getName());
            System.out.println(lookAndFeelInfo1.getClassName());
        }

        //button.addActionListener(EventHandler.create(ActionListener.class, jFrame, "title", "source.text"));

        //new PDFExtractor().importPDF("src/main/resources/Amazon-S3-Tutorial.pdf", false);
    }


    static JFrame GetFrame(){
        JFrame jFrame = new JFrame() {};
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //jFrame.setSize(300, 300);
        //jFrame.setLocation(300, 300);
        jFrame.setTitle("PDF Meta Viewer");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        jFrame.setBounds(dimension.width / 2 - (widthApp / 2),dimension.height / 2 - (heightapp / 2),widthApp,heightapp);

        //jFrame.setIconImage();


        return jFrame;
    }
}


