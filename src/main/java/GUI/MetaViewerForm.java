package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alexxfromgit
 */
public class MetaViewerForm {
    private JPanel panelMain;
    private JButton choosePDFButton;
    private JLabel resultLabel;
    private JButton readMetaButton;

    static JFrame GetFrame(){
        JFrame jFrame = new JFrame() {};
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //jFrame.setSize(300, 300);
        //jFrame.setLocation(300, 300);
        jFrame.setTitle("PDF Meta Viewer");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        //jFrame.setBounds(dimension.width / 2 - (widthApp / 2),dimension.height / 2 - (heightapp / 2),widthApp,heightapp);

        //jFrame.setIconImage();


        return jFrame;
    }

}
