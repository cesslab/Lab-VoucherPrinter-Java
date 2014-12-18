package edu.nyu.cess.payment.ui;

import edu.nyu.cess.payment.io.ConfigurationFile;
import edu.nyu.cess.payment.io.FileConverter;
import edu.nyu.cess.payment.ui.listeners.ConvertPaymentFileListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PrinterPanel extends JPanel
{
	private static final long serialVersionUID = 2853708307420292816L;
	
    private JButton openPaymentFileButton, paymentFileToVoucherButton;
    private JLabel statusLabel;
    private JFileChooser fileChooser;
	private ConfigurationFile config;

    private int verticalShift = 0;
    private int horizontalShift = 0;

	private static String INFO_ICON_PATH = "/images/info.png";
	private static String FILE_OPEN_ICON_PATH = "/images/file_open.png";
	private static String PDF_ICON_PATH = "/images/pdf_ico.png";
	private static String CESS_ICON_PATH = "/images/cess.png";

    protected FileConverter fileConverter = new FileConverter();
    
    public PrinterPanel() {
    	init();
    }
    
    public void init(){
    	GridBagConstraints constraint; 

        // find configuration file for the default path
    	try {
	        this.config = new ConfigurationFile();
    	} catch (IOException e) {
    		JOptionPane.showMessageDialog(this, 
    				"Failed to locate the configuration file config.properties. This file is needed to run this program.");
    		return;
    	}
        
        fileChooser = new JFileChooser(config.getPath());



        ImageIcon infoIcon = createImageIcon("/resources/images/info.png", "info");
        this.statusLabel = new JLabel(infoIcon);
		this.statusLabel.setForeground(Color.BLACK);
        this.statusLabel.setText("Select \"Open File\" to set your payment file.");
        this.statusLabel.setHorizontalAlignment(JLabel.LEFT);
        
        ImageIcon fileOpenIcon = createImageIcon("/resources/images/file_open.png", "FileOpen");
        openPaymentFileButton = new JButton("Open File", fileOpenIcon);
        openPaymentFileButton.setPreferredSize(new Dimension(190, 40));
        openPaymentFileButton.addActionListener(new OpenPaymentFileListener());

        ImageIcon pdfIcon = createImageIcon("/resources/images/pdf_ico.png", "PDF");
        paymentFileToVoucherButton = new JButton("Convert to Voucher", pdfIcon);
        paymentFileToVoucherButton.setPreferredSize(new Dimension(190, 40));
        paymentFileToVoucherButton.addActionListener(new ConvertPaymentFileListener());

        //For layout purposes, put the buttons in a separate panel
        JPanel fileSelectionPanel = new JPanel(new GridBagLayout()); 
        
        JPanel cessLogoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        
        JLabel cessIconLabel = new JLabel(createImageIcon("/resources/images/cess.png", "CESS"));
        cessLogoPanel.add(cessIconLabel);
        
		JLabel cessLabel = new JLabel("CESS Lab Toolkit");
		cessLabel.setFont(new Font("Helvetica", Font.BOLD, 28));
		cessLabel.setForeground(new Color(19, 19, 33));
        cessLogoPanel.add(cessLabel);
			
		constraint = new GridBagConstraints();
		constraint.gridwidth = GridBagConstraints.REMAINDER;
		constraint.insets = new Insets(20, 20, 20, 20);
        fileSelectionPanel.add(cessLogoPanel, constraint);

		constraint = new GridBagConstraints();
		constraint.gridx = 0;
		constraint.gridy = 2;
		constraint.insets = new Insets(0, 0, 10, 0);
        fileSelectionPanel.add(openPaymentFileButton, constraint);
        
        JLabel nextIconLabel = new JLabel(createImageIcon("/resources/images/next.png", ""));
		constraint = new GridBagConstraints();
		constraint.gridx = 1;
		constraint.gridy = 2;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.insets = new Insets(0, 30, 10, 30);
        fileSelectionPanel.add(nextIconLabel, constraint);
        
		constraint = new GridBagConstraints();
		constraint.gridx = 2;
		constraint.gridy = 2;
		constraint.insets = new Insets(0, 0, 10, 0);
        fileSelectionPanel.add(paymentFileToVoucherButton, constraint);
        
		constraint = new GridBagConstraints();
		constraint.gridx = 0;
		constraint.gridy = 3;
		constraint.gridwidth = GridBagConstraints.REMAINDER;
		constraint.insets = new Insets(0, 10, 0, 0);
        fileSelectionPanel.add(statusLabel, constraint);
        
        //Add the buttons and the log to this panel.
        add(fileSelectionPanel, BorderLayout.PAGE_START);
    }
    
    public void setHorizontalShift(int horizontalShift){
    	this.horizontalShift = horizontalShift;
    }
    
    public void setVerticalShift(int verticalShift){
    	this.verticalShift = verticalShift;
    }

}
