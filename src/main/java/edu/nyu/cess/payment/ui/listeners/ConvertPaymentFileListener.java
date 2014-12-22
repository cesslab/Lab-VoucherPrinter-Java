package edu.nyu.cess.payment.ui.listeners;

import edu.nyu.cess.payment.io.FileConverter;
import edu.nyu.cess.payment.ui.IconFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ConvertPaymentFileListener implements ActionListener
{
    private static final String ERROR_ICON_PATH = "/images/error.png";

    private FileConverter fileConverter;
    private ImageIcon errorIcon;

    public ConvertPaymentFileListener(FileConverter fileConverter)
    {
        this.fileConverter = fileConverter;
        errorIcon = IconFactory.createImageIcon(ERROR_ICON_PATH, "error");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Error: File Not Set
        if(!fileConverter.isFileInfoSet()){
            statusLabel.setForeground(Color.RED);
            statusLabel.setIcon(errorIcon);
            statusLabel.setText("Error: Please select \"Open File\" first.");
            return;
        }

        // Error: Failed To Covert File
        if(!fileConverter.convertPaymentToVoucherPDF(horizontalShift, verticalShift)){
            ImageIcon errorIcon = createImageIcon("/resources/images/error.png", "error");
            statusLabel.setIcon(errorIcon);
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Error: Failed to convert payment file, check format.");
            return;
        }

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(fileConverter.getPDFLocation());
                Desktop.getDesktop().open(myFile);
            }
            // Error: Unable to Open File
            catch (IOException ex) {
                ImageIcon errorIcon = createImageIcon("/resources/images/error.png", "error");
                statusLabel.setIcon(errorIcon);
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Error: Unable to auto open File ("+ fileConverter.getPDFName()+".pdf)");
            }
        }

        ImageIcon infoIcon = createImageIcon("/resources/images/info.png", "info");
        statusLabel.setIcon(infoIcon);
        statusLabel.setText("");
        statusLabel.setText("Output File: " + fileConverter.getPDFName()+".pdf");
    }

}

