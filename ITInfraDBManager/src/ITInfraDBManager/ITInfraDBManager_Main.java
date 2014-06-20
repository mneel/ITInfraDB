package ITInfraDBManager;

/**
 * @Author Mike neel
 * @Date 06.13.2014
 * Version 1.01
 */

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.SystemColor;

import ITInfraDBManager.ProgressBar;

public class ITInfraDBManager_Main {

	//Variable Declarations
	JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
	private String[] values = new String[20];
	JFrame frmServersdb;
	private JTable jTable_pServers;
	private JTable jTable_vServers;
	private JTable jTable_netEQ; 
	private JTable jTable_AWS_EC2;
	private JTable jTable_pServer_PSU;
	private JTable jTable_Reports;
	private JTable jTable_UPS;

	//PSERVER Tab variables
	private JTextField textField_pServer_Model;
	private JTextField textField_pServer_SerialNumber;
	private JTextField textField_pServer_Rat;
	private JTextField textField_pServer_OperatingSystem;
	private JTextField textField_pServer_IPAdd01;
	private JTextField textField_pServer_IPAdd02;
	private JTextField textField_pServer_IPAdd03;
	private JTextField textField_pServer_IPAdd04;
	private JTextField textField_pServer_ProductionDate;
	private JTextField textField_pServer_DracIpAdd;
	final JComboBox<String> comboBox_pServer_Name = new JComboBox<String>();
	final JComboBox<String> comboBox_pServer_RackNum = new JComboBox<String>();
	final JComboBox<String> comboBox_pServer_RackPos = new JComboBox<String>();
	final JComboBox<String> comboBox_pServers_Usize = new JComboBox<String>();
	final JComboBox<String> comboBox_pServer_StatusOn = new JComboBox<String>();
	final JTextArea textArea_pServer_services = new JTextArea();
	final JTextArea textArea_pServer_Notes = new JTextArea();

	//VSERVERS Tab Variables
	final JComboBox<String> comboBox_vServers_vServer_Name = new JComboBox<String>();
	final JComboBox<String> comboBox_vServer_pServer_Name = new JComboBox<String>();
	final JComboBox<String> comboBox_vServer_StatusON = new JComboBox<String>();	
	final JComboBox<String> comboBox_vServer_VHDsize = new JComboBox<String>();
	final JComboBox<String> comboBox_vServer_AssignedMem = new JComboBox<String>();
	private JTextField textField_vServer_backupDestination;
	private JTextField textField_vServer_moreBackUp;
	private JTextField textField_vServer_pointOfContact;
	private JTextField textField_vServer_OS;
	private JTextField textField_vServer_IPAdd01;
	private JTextField textField_vServer_IPAdd02;
	private JTextField textField_vServer_IPAdd03;
	private JTextField textField_vServer_IPAdd04;
	private JTextField textField_vServer_Services;

	//PSERVER_PSU Tab variables
	final JComboBox<String> comboBox_pServer_PSU_outletNum = new JComboBox<String>();
	final JComboBox<String> comboBox_pServer_PSU_PDUName = new JComboBox<String>();
	final JComboBox<String> comboBox_pServer_PSU_UpsName = new JComboBox<String>();
	final JComboBox<String> comboBox_pServer_PSU_pServerName = new JComboBox<String>();
	final JComboBox<String> comboBox_pServer_PSU_psuNum = new JComboBox<String>();

	//Network Equipment Variables
	final JComboBox<String> comboBox_NetEQ_SerialNum = new JComboBox<String>();
	final JComboBox<String> comboBox_NetEQ_hostName = new JComboBox<String>();
	final JComboBox<String> comboBox_NetEQ_Type = new JComboBox<String>();
	final JComboBox<String> comboBox_NetEQ_StatusOn = new JComboBox<String>();
	final JComboBox<String> comboBox_NetEQ_RackNum = new JComboBox<String>();
	private JTextField textField_NetEQ_RAT;
	private JTextField textField_NetEQ_Manufacturer;
	private JTextField textField_NetEQ_Model;
	private JTextField textField_NetEQ_IPAdd01;
	private JTextField textField_NetEQ_IPAdd02;
	private JTextField textField_NetEQ_IPAdd03;
	private JTextField textField_NetEQ_IPAdd04;
	final JTextArea textArea_NetEQ_Services = new JTextArea();
	final JTextArea textArea_NetEQ_notes = new JTextArea();

	//AWS_EC2 Variables
	final JComboBox<String> comboBox_AWSEC2_Instance = new JComboBox<String>();
	private JTextField textField_AWSEC2_IPAdd;
	private JTextField textField_AWSEC2_Hostname;
	private JTextField textField_AWSEC2_OS;
	private JTextField textField_AWSEC2_URL;
	private JTextField textField_AWSEC2_Services;

	//UPS Variables
	final JComboBox<String> comboBox_UPS_Name = new JComboBox<String>();
	final JComboBox<String> comboBox_UPS_rackNum = new JComboBox<String>();
	private JTextField textField_UPS_IPAdd;
	private JTextField textField_UPS_model;
	private JTextField textField_UPS_serialNum;
	private JTextField textField_UPS_URL;
	private JTextField textField_UPS_powCapacity;
	private JTextField textField_UPS_maccAdd;
	private JTextField textField_UPS_circuitNum;


	/**
	 * Create the application.
	 */
	public ITInfraDBManager_Main() {
		ProgressBar progressWindow = new ProgressBar();
		progressWindow.frame.setVisible(true);
		initialize();
		progressWindow.frame.setVisible(false);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {



		frmServersdb = new JFrame();
		frmServersdb.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mike.neel\\Pictures\\DBIcon.jpg"));
		frmServersdb.setTitle("IT Infra DB Manager");
		frmServersdb.setBounds(100, 100, 1000, 900);
		frmServersdb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServersdb.getContentPane().setLayout(new MigLayout("", "[784px,grow]", "[21px][grow]"));

		JMenuBar menuBar = new JMenuBar();
		frmServersdb.getContentPane().add(menuBar, "cell 0 0,growx,aligny top");

		JMenu mnFileMenu = new JMenu("File");
		mnFileMenu.setBackground(SystemColor.controlHighlight);
		menuBar.add(mnFileMenu);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.setBackground(SystemColor.inactiveCaptionBorder);
		mntmClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		mnFileMenu.add(mntmClose);

		JMenu mnReportsMenu = new JMenu("Reports");
		mnReportsMenu.setBackground(SystemColor.inactiveCaptionBorder);
		menuBar.add(mnReportsMenu);

		JMenu subMN_Ups = new JMenu("UPS");
		subMN_Ups.setBackground(SystemColor.inactiveCaptionBorder);
		mnReportsMenu.add(subMN_Ups);

		JMenuItem mntm_UPSHealth = new JMenuItem("Health Report");
		mntm_UPSHealth.setBackground(SystemColor.inactiveCaptionBorder);
		subMN_Ups.add(mntm_UPSHealth);

		JMenuItem mntm_UPSReport = new JMenuItem("Detailed Report");
		mntm_UPSReport.setBackground(SystemColor.inactiveCaptionBorder);
		mntm_UPSReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showReport("UPS Report", "SELECT * FROM dbo.UPS");
			}
		});
		subMN_Ups.add(mntm_UPSReport);

		JMenuItem mntmRackDetail = new JMenuItem("Rack Detail");
		mntmRackDetail.setBackground(SystemColor.inactiveCaptionBorder);
		mnReportsMenu.add(mntmRackDetail);

		JMenu mnVirtualMachines = new JMenu("Virtual Machines");
		mnVirtualMachines.setBackground(SystemColor.inactiveCaptionBorder);
		mnReportsMenu.add(mnVirtualMachines);

		JMenuItem menuItem = new JMenuItem("New menu item");
		mnVirtualMachines.add(menuItem);


		frmServersdb.getContentPane().add(tabbedPane, "cell 0 1,grow");

		////////////////////////////////////////
		//Start Code for Physical Servers Tab //
		////////////////////////////////////////
		JPanel pServersTab = new JPanel();
		tabbedPane.addTab("Physical Servers", null, pServersTab, null);
		jTable_pServers = createTable("pServers");
		jTable_pServers.setBackground(Color.WHITE);
		jTable_pServers.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		jTable_pServers.setSurrendersFocusOnKeystroke(true);
		jTable_pServers.setFillsViewportHeight(true);
		jTable_pServers.setColumnSelectionAllowed(true);
		jTable_pServers.setCellSelectionEnabled(true);
		JScrollPane scrollPane_pServers = new JScrollPane(jTable_pServers);
		scrollPane_pServers.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_pServers.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JPanel panel_pServer_dataEntry = new JPanel();
		GroupLayout gl_pServersTab = new GroupLayout(pServersTab);
		gl_pServersTab.setHorizontalGroup(
				gl_pServersTab.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pServersTab.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pServersTab.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_pServer_dataEntry, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
								.addComponent(scrollPane_pServers, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE))
								.addContainerGap())
				);
		gl_pServersTab.setVerticalGroup(
				gl_pServersTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pServersTab.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane_pServers, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(panel_pServer_dataEntry, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
				);
		GridBagLayout gbl_panel_pServer_dataEntry = new GridBagLayout();
		gbl_panel_pServer_dataEntry.columnWidths = new int[] {100, 100, 0, 100, 100};
		gbl_panel_pServer_dataEntry.rowHeights = new int[]{14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_pServer_dataEntry.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		gbl_panel_pServer_dataEntry.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_pServer_dataEntry.setLayout(gbl_panel_pServer_dataEntry);

		JLabel lbl_pServer_ServerName = new JLabel("Server Name");
		GridBagConstraints gbc_lbl_pServer_ServerName = new GridBagConstraints();
		gbc_lbl_pServer_ServerName.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_ServerName.fill = GridBagConstraints.VERTICAL;
		gbc_lbl_pServer_ServerName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_ServerName.gridx = 0;
		gbc_lbl_pServer_ServerName.gridy = 0;
		panel_pServer_dataEntry.add(lbl_pServer_ServerName, gbc_lbl_pServer_ServerName);

		//comboBox_pServer_Name code
		populateCombobox(comboBox_pServer_Name, "dbo.pServers", "pServer_Name", false);
		comboBox_pServer_Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_pServer_Name.getSelectedItem() != null)
					populateFields("PSERVERS");
			}
		});
		comboBox_pServer_Name.setEditable(true);
		GridBagConstraints gbc_comboBox_pServer_Name = new GridBagConstraints();
		gbc_comboBox_pServer_Name.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_pServer_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_pServer_Name.gridx = 1;
		gbc_comboBox_pServer_Name.gridy = 0;

		panel_pServer_dataEntry.add(comboBox_pServer_Name, gbc_comboBox_pServer_Name);

		JLabel lbl_pServer_Rat = new JLabel("RAT");
		GridBagConstraints gbc_lbl_pServer_Rat = new GridBagConstraints();
		gbc_lbl_pServer_Rat.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_Rat.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_Rat.gridx = 2;
		gbc_lbl_pServer_Rat.gridy = 0;
		panel_pServer_dataEntry.add(lbl_pServer_Rat, gbc_lbl_pServer_Rat);

		textField_pServer_Rat = new JTextField();
		GridBagConstraints gbc_txtRat = new GridBagConstraints();
		gbc_txtRat.insets = new Insets(0, 0, 5, 5);
		gbc_txtRat.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRat.gridx = 3;
		gbc_txtRat.gridy = 0;
		panel_pServer_dataEntry.add(textField_pServer_Rat, gbc_txtRat);
		textField_pServer_Rat.setColumns(10);

		JLabel lbl_pServer_RackNum = new JLabel("Rack Number");
		GridBagConstraints gbc_lbl_pServer_RackNum = new GridBagConstraints();
		gbc_lbl_pServer_RackNum.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_RackNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_RackNum.gridx = 0;
		gbc_lbl_pServer_RackNum.gridy = 1;
		panel_pServer_dataEntry.add(lbl_pServer_RackNum, gbc_lbl_pServer_RackNum);

		//code for pServer_RackNum comboBox
		comboBox_pServer_RackNum.addItem("");
		populateCombobox(comboBox_pServer_RackNum, "dbo.Racks", "RackNum", false);
		GridBagConstraints gbc_comboBox_pServer_RackNum = new GridBagConstraints();
		gbc_comboBox_pServer_RackNum.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_pServer_RackNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_pServer_RackNum.gridx = 1;
		gbc_comboBox_pServer_RackNum.gridy = 1;
		panel_pServer_dataEntry.add(comboBox_pServer_RackNum, gbc_comboBox_pServer_RackNum);

		JLabel lbl_pServer_ProdDate = new JLabel("Production Date");
		GridBagConstraints gbc_lbl_pServer_ProdDate = new GridBagConstraints();
		gbc_lbl_pServer_ProdDate.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_ProdDate.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_ProdDate.gridx = 2;
		gbc_lbl_pServer_ProdDate.gridy = 1;
		panel_pServer_dataEntry.add(lbl_pServer_ProdDate, gbc_lbl_pServer_ProdDate);

		textField_pServer_ProductionDate = new JTextField();
		GridBagConstraints gbc_txtProductionDate = new GridBagConstraints();
		gbc_txtProductionDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductionDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductionDate.gridx = 3;
		gbc_txtProductionDate.gridy = 1;
		panel_pServer_dataEntry.add(textField_pServer_ProductionDate, gbc_txtProductionDate);
		textField_pServer_ProductionDate.setColumns(10);

		JLabel lblexample = new JLabel("(Example: 01/01/2000)");
		GridBagConstraints gbc_lblexample = new GridBagConstraints();
		gbc_lblexample.insets = new Insets(0, 0, 5, 0);
		gbc_lblexample.gridx = 4;
		gbc_lblexample.gridy = 1;
		panel_pServer_dataEntry.add(lblexample, gbc_lblexample);

		JLabel lbl_pServer_RackPosition = new JLabel("Rack Position");
		GridBagConstraints gbc_lbl_pServer_RackPosition = new GridBagConstraints();
		gbc_lbl_pServer_RackPosition.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_RackPosition.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_RackPosition.gridx = 0;
		gbc_lbl_pServer_RackPosition.gridy = 2;
		panel_pServer_dataEntry.add(lbl_pServer_RackPosition, gbc_lbl_pServer_RackPosition);

		// code for pServer_RackPos comboBox
		comboBox_pServer_RackPos.setModel(new DefaultComboBoxModel());
		comboBox_pServer_RackPos.addItem("");
		for(int i = 1; i < 45; i++){
			comboBox_pServer_RackPos.addItem(Integer.toString(i));
		}
		GridBagConstraints gbc_comboBox_pServer_RackPos = new GridBagConstraints();
		gbc_comboBox_pServer_RackPos.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_pServer_RackPos.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_pServer_RackPos.gridx = 1;
		gbc_comboBox_pServer_RackPos.gridy = 2;
		panel_pServer_dataEntry.add(comboBox_pServer_RackPos, gbc_comboBox_pServer_RackPos);

		JLabel lbl_pServer_OS = new JLabel("OS");
		GridBagConstraints gbc_lbl_pServer_OS = new GridBagConstraints();
		gbc_lbl_pServer_OS.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_OS.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_OS.gridx = 2;
		gbc_lbl_pServer_OS.gridy = 2;
		panel_pServer_dataEntry.add(lbl_pServer_OS, gbc_lbl_pServer_OS);

		textField_pServer_OperatingSystem = new JTextField();
		GridBagConstraints gbc_txtOperatingSystem = new GridBagConstraints();
		gbc_txtOperatingSystem.insets = new Insets(0, 0, 5, 5);
		gbc_txtOperatingSystem.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOperatingSystem.gridx = 3;
		gbc_txtOperatingSystem.gridy = 2;
		panel_pServer_dataEntry.add(textField_pServer_OperatingSystem, gbc_txtOperatingSystem);
		textField_pServer_OperatingSystem.setColumns(10);

		JLabel lbl_pServer_USize = new JLabel("U Size");
		GridBagConstraints gbc_lbl_pServer_USize = new GridBagConstraints();
		gbc_lbl_pServer_USize.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_USize.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_USize.gridx = 0;
		gbc_lbl_pServer_USize.gridy = 3;
		panel_pServer_dataEntry.add(lbl_pServer_USize, gbc_lbl_pServer_USize);

		//code for pServer_Usize comboBox
		comboBox_pServers_Usize.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4"}));
		GridBagConstraints gbc_comboBox_Usize = new GridBagConstraints();
		gbc_comboBox_Usize.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Usize.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Usize.gridx = 1;
		gbc_comboBox_Usize.gridy = 3;
		panel_pServer_dataEntry.add(comboBox_pServers_Usize, gbc_comboBox_Usize);

		JLabel lbl_pServer_IpAdd01 = new JLabel("IP Address 01");
		GridBagConstraints gbc_lbl_pServer_IpAdd01 = new GridBagConstraints();
		gbc_lbl_pServer_IpAdd01.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_IpAdd01.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_IpAdd01.gridx = 2;
		gbc_lbl_pServer_IpAdd01.gridy = 3;
		panel_pServer_dataEntry.add(lbl_pServer_IpAdd01, gbc_lbl_pServer_IpAdd01);

		textField_pServer_IPAdd01 = new JTextField();
		GridBagConstraints gbc_txtIpAddress = new GridBagConstraints();
		gbc_txtIpAddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtIpAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIpAddress.gridx = 3;
		gbc_txtIpAddress.gridy = 3;
		panel_pServer_dataEntry.add(textField_pServer_IPAdd01, gbc_txtIpAddress);
		textField_pServer_IPAdd01.setColumns(10);

		JLabel lbl_pServer_StatusOn = new JLabel("Status ON");
		GridBagConstraints gbc_lbl_pServer_StatusOn = new GridBagConstraints();
		gbc_lbl_pServer_StatusOn.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_StatusOn.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_StatusOn.gridx = 0;
		gbc_lbl_pServer_StatusOn.gridy = 4;
		panel_pServer_dataEntry.add(lbl_pServer_StatusOn, gbc_lbl_pServer_StatusOn);

		// code for pServer_StatusON comboBox
		comboBox_pServer_StatusOn.setModel(new DefaultComboBoxModel(new String[] {"", "T", "F"}));
		GridBagConstraints gbc_comboBox_pServer_StatusOn = new GridBagConstraints();
		gbc_comboBox_pServer_StatusOn.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_pServer_StatusOn.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_pServer_StatusOn.gridx = 1;
		gbc_comboBox_pServer_StatusOn.gridy = 4;
		panel_pServer_dataEntry.add(comboBox_pServer_StatusOn, gbc_comboBox_pServer_StatusOn);

		JLabel lbl_pServer_IPAdd02 = new JLabel("IP Address 02");
		GridBagConstraints gbc_lbl_pServer_IPAdd02 = new GridBagConstraints();
		gbc_lbl_pServer_IPAdd02.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_IPAdd02.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_IPAdd02.gridx = 2;
		gbc_lbl_pServer_IPAdd02.gridy = 4;
		panel_pServer_dataEntry.add(lbl_pServer_IPAdd02, gbc_lbl_pServer_IPAdd02);

		textField_pServer_IPAdd02 = new JTextField();
		GridBagConstraints gbc_txtIpAddress_1 = new GridBagConstraints();
		gbc_txtIpAddress_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtIpAddress_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIpAddress_1.gridx = 3;
		gbc_txtIpAddress_1.gridy = 4;
		panel_pServer_dataEntry.add(textField_pServer_IPAdd02, gbc_txtIpAddress_1);
		textField_pServer_IPAdd02.setColumns(10);

		JLabel lblp_Server_Model = new JLabel("Model");
		GridBagConstraints gbc_lblp_Server_Model = new GridBagConstraints();
		gbc_lblp_Server_Model.anchor = GridBagConstraints.EAST;
		gbc_lblp_Server_Model.insets = new Insets(0, 0, 5, 5);
		gbc_lblp_Server_Model.gridx = 0;
		gbc_lblp_Server_Model.gridy = 5;
		panel_pServer_dataEntry.add(lblp_Server_Model, gbc_lblp_Server_Model);

		textField_pServer_Model = new JTextField();
		GridBagConstraints gbc_txtModel = new GridBagConstraints();
		gbc_txtModel.insets = new Insets(0, 0, 5, 5);
		gbc_txtModel.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtModel.gridx = 1;
		gbc_txtModel.gridy = 5;
		panel_pServer_dataEntry.add(textField_pServer_Model, gbc_txtModel);
		textField_pServer_Model.setColumns(10);

		JLabel lbl_pServer_IPAdd03 = new JLabel("IP Address 03");
		GridBagConstraints gbc_lbl_pServer_IPAdd03 = new GridBagConstraints();
		gbc_lbl_pServer_IPAdd03.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_IPAdd03.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_IPAdd03.gridx = 2;
		gbc_lbl_pServer_IPAdd03.gridy = 5;
		panel_pServer_dataEntry.add(lbl_pServer_IPAdd03, gbc_lbl_pServer_IPAdd03);

		textField_pServer_IPAdd03 = new JTextField();
		GridBagConstraints gbc_txtIpAddress_2 = new GridBagConstraints();
		gbc_txtIpAddress_2.insets = new Insets(0, 0, 5, 5);
		gbc_txtIpAddress_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIpAddress_2.gridx = 3;
		gbc_txtIpAddress_2.gridy = 5;
		panel_pServer_dataEntry.add(textField_pServer_IPAdd03, gbc_txtIpAddress_2);
		textField_pServer_IPAdd03.setColumns(10);

		JLabel lbl_pServer_SerialNumber = new JLabel("Serial Number");
		GridBagConstraints gbc_lbl_pServer_SerialNumber = new GridBagConstraints();
		gbc_lbl_pServer_SerialNumber.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_SerialNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_SerialNumber.gridx = 0;
		gbc_lbl_pServer_SerialNumber.gridy = 6;
		panel_pServer_dataEntry.add(lbl_pServer_SerialNumber, gbc_lbl_pServer_SerialNumber);

		textField_pServer_SerialNumber = new JTextField();
		GridBagConstraints gbc_txtSerialNumber = new GridBagConstraints();
		gbc_txtSerialNumber.insets = new Insets(0, 0, 5, 5);
		gbc_txtSerialNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSerialNumber.gridx = 1;
		gbc_txtSerialNumber.gridy = 6;
		panel_pServer_dataEntry.add(textField_pServer_SerialNumber, gbc_txtSerialNumber);
		textField_pServer_SerialNumber.setColumns(10);

		JLabel lbl_pServer_IPAdd04 = new JLabel("IP Address 04");
		GridBagConstraints gbc_lbl_pServer_IPAdd04 = new GridBagConstraints();
		gbc_lbl_pServer_IPAdd04.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_IPAdd04.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_IPAdd04.gridx = 2;
		gbc_lbl_pServer_IPAdd04.gridy = 6;
		panel_pServer_dataEntry.add(lbl_pServer_IPAdd04, gbc_lbl_pServer_IPAdd04);

		textField_pServer_IPAdd04 = new JTextField();
		GridBagConstraints gbc_txtIpAddress_3 = new GridBagConstraints();
		gbc_txtIpAddress_3.insets = new Insets(0, 0, 5, 5);
		gbc_txtIpAddress_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIpAddress_3.gridx = 3;
		gbc_txtIpAddress_3.gridy = 6;
		panel_pServer_dataEntry.add(textField_pServer_IPAdd04, gbc_txtIpAddress_3);
		textField_pServer_IPAdd04.setColumns(10);

		JLabel lbl_pServer_ServicesApplications = new JLabel("Services / Applications");
		GridBagConstraints gbc_lbl_pServer_ServicesApplications = new GridBagConstraints();
		gbc_lbl_pServer_ServicesApplications.anchor = GridBagConstraints.NORTHEAST;
		gbc_lbl_pServer_ServicesApplications.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_ServicesApplications.gridx = 0;
		gbc_lbl_pServer_ServicesApplications.gridy = 7;
		panel_pServer_dataEntry.add(lbl_pServer_ServicesApplications, gbc_lbl_pServer_ServicesApplications);

		// code for pServer_Services textArea
		GridBagConstraints gbc_textArea_pServer_services = new GridBagConstraints();
		gbc_textArea_pServer_services.gridheight = 3;
		gbc_textArea_pServer_services.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_pServer_services.fill = GridBagConstraints.BOTH;
		gbc_textArea_pServer_services.gridx = 1;
		gbc_textArea_pServer_services.gridy = 7;
		panel_pServer_dataEntry.add(textArea_pServer_services, gbc_textArea_pServer_services);

		JLabel lbl_pServer_DracIpAdd = new JLabel("DRAC IP Address");
		GridBagConstraints gbc_lbl_pServer_DracIpAdd = new GridBagConstraints();
		gbc_lbl_pServer_DracIpAdd.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_DracIpAdd.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_DracIpAdd.gridx = 2;
		gbc_lbl_pServer_DracIpAdd.gridy = 7;
		panel_pServer_dataEntry.add(lbl_pServer_DracIpAdd, gbc_lbl_pServer_DracIpAdd);

		textField_pServer_DracIpAdd = new JTextField();
		GridBagConstraints gbc_txtDracIpAddress = new GridBagConstraints();
		gbc_txtDracIpAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDracIpAddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtDracIpAddress.gridx = 3;
		gbc_txtDracIpAddress.gridy = 7;
		panel_pServer_dataEntry.add(textField_pServer_DracIpAdd, gbc_txtDracIpAddress);
		textField_pServer_DracIpAdd.setColumns(10);

		//code for pServer_Notes textArea
		GridBagConstraints gbc_textArea_pServer_Notes = new GridBagConstraints();
		gbc_textArea_pServer_Notes.gridheight = 2;
		gbc_textArea_pServer_Notes.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_pServer_Notes.fill = GridBagConstraints.BOTH;
		gbc_textArea_pServer_Notes.gridx = 3;
		gbc_textArea_pServer_Notes.gridy = 8;
		panel_pServer_dataEntry.add(textArea_pServer_Notes, gbc_textArea_pServer_Notes);

		JLabel lbl_pServer_Notes = new JLabel("Notes:");
		GridBagConstraints gbc_lbl_pServer_Notes = new GridBagConstraints();
		gbc_lbl_pServer_Notes.anchor = GridBagConstraints.NORTHEAST;
		gbc_lbl_pServer_Notes.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_Notes.gridx = 2;
		gbc_lbl_pServer_Notes.gridy = 8;
		panel_pServer_dataEntry.add(lbl_pServer_Notes, gbc_lbl_pServer_Notes);


		JButton btn_pServer_Add = new JButton("Add");
		btn_pServer_Add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("ADD", "PSERVERS");
			}
		});
		GridBagConstraints gbc_btn_pServer_Add = new GridBagConstraints();
		gbc_btn_pServer_Add.insets = new Insets(0, 0, 0, 5);
		gbc_btn_pServer_Add.gridx = 1;
		gbc_btn_pServer_Add.gridy = 10;
		panel_pServer_dataEntry.add(btn_pServer_Add, gbc_btn_pServer_Add);

		JButton button_pServer_update = new JButton("Update");
		button_pServer_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("UPDATE", "PSERVERS");
			}
		});
		GridBagConstraints gbc_button_pServer_update = new GridBagConstraints();
		gbc_button_pServer_update.anchor = GridBagConstraints.SOUTHEAST;
		gbc_button_pServer_update.insets = new Insets(0, 0, 0, 5);
		gbc_button_pServer_update.gridx = 2;
		gbc_button_pServer_update.gridy = 10;
		panel_pServer_dataEntry.add(button_pServer_update, gbc_button_pServer_update);

		JButton button_pServer_delete = new JButton("Delete");
		button_pServer_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("DELETE", "PSERVERS");
			}
		});
		GridBagConstraints gbc_button_pServer_delete = new GridBagConstraints();
		gbc_button_pServer_delete.insets = new Insets(0, 0, 0, 5);
		gbc_button_pServer_delete.gridx = 3;
		gbc_button_pServer_delete.gridy = 10;
		panel_pServer_dataEntry.add(button_pServer_delete, gbc_button_pServer_delete);
		gl_pServersTab.setAutoCreateGaps(true);
		gl_pServersTab.setAutoCreateContainerGaps(true);
		pServersTab.setLayout(gl_pServersTab);

		///////////////////////////////////////
		//Start Code for Virtual Servers Tab //
		///////////////////////////////////////
		JPanel vServersTab = new JPanel();
		tabbedPane.addTab("Virtual Servers", null, vServersTab, null);
		jTable_vServers = createTable("vserver");
		jTable_vServers.setBackground(Color.WHITE);
		jTable_vServers.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		jTable_vServers.setSurrendersFocusOnKeystroke(true);
		jTable_vServers.setFillsViewportHeight(true);
		jTable_vServers.setColumnSelectionAllowed(true);
		jTable_vServers.setCellSelectionEnabled(true);
		JScrollPane scrollPane_vServers = new JScrollPane(jTable_vServers);

		JPanel panel_vServer_dataEntry = new JPanel();
		GroupLayout gl_vServersTab = new GroupLayout(vServersTab);
		gl_vServersTab.setHorizontalGroup(
				gl_vServersTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_vServersTab.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_vServersTab.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_vServers, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
								.addGroup(gl_vServersTab.createSequentialGroup()
										.addComponent(panel_vServer_dataEntry, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
										.addGap(12)))
										.addGap(12))
				);
		gl_vServersTab.setVerticalGroup(
				gl_vServersTab.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_vServersTab.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane_vServers, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(panel_vServer_dataEntry, GroupLayout.PREFERRED_SIZE, 302, Short.MAX_VALUE)
						.addContainerGap())
				);
		GridBagLayout gbl_panel_vServer_dataEntry = new GridBagLayout();
		gbl_panel_vServer_dataEntry.columnWidths = new int[] {100, 100, 0, 100, 100};
		gbl_panel_vServer_dataEntry.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panel_vServer_dataEntry.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		gbl_panel_vServer_dataEntry.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel_vServer_dataEntry.setLayout(gbl_panel_vServer_dataEntry);

		JLabel lbl_vServer_ServerName = new JLabel("Server Name");
		GridBagConstraints gbc_lbl_vServer_ServerName = new GridBagConstraints();
		gbc_lbl_vServer_ServerName.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_ServerName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_ServerName.gridx = 0;
		gbc_lbl_vServer_ServerName.gridy = 0;
		panel_vServer_dataEntry.add(lbl_vServer_ServerName, gbc_lbl_vServer_ServerName);

		// code for comboBox_vServers_vServer_Name
		populateCombobox(comboBox_vServers_vServer_Name, "dbo.vServer", "vServer_Name", false);
		populateSecondaryComboBox (comboBox_vServer_pServer_Name, comboBox_vServers_vServer_Name, "dbo.vServer", "pServer_Name", false);
		comboBox_vServers_vServer_Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_vServers_vServer_Name.getSelectedItem() != null)
					populateFields("VSERVERS");
				//populateSecondaryComboBox (comboBox_vServer_pServer_Name, comboBox_vServers_vServer_Name, "dbo.vServer", "pServer_Name", true);
			}
		});
		comboBox_vServers_vServer_Name.setEditable(true);
		GridBagConstraints gbc_comboBox_vServers_vServer_Name = new GridBagConstraints();
		gbc_comboBox_vServers_vServer_Name.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_vServers_vServer_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_vServers_vServer_Name.gridx = 1;
		gbc_comboBox_vServers_vServer_Name.gridy = 0;
		panel_vServer_dataEntry.add(comboBox_vServers_vServer_Name, gbc_comboBox_vServers_vServer_Name);

		textField_vServer_pointOfContact = new JTextField();
		GridBagConstraints gbc_textField_vServer_pointOfContact = new GridBagConstraints();
		gbc_textField_vServer_pointOfContact.insets = new Insets(0, 0, 5, 5);
		gbc_textField_vServer_pointOfContact.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_vServer_pointOfContact.gridx = 3;
		gbc_textField_vServer_pointOfContact.gridy = 0;
		panel_vServer_dataEntry.add(textField_vServer_pointOfContact, gbc_textField_vServer_pointOfContact);
		textField_vServer_pointOfContact.setColumns(10);

		JLabel lbl_vServer_PhysicalHost = new JLabel("Physical Host");
		GridBagConstraints gbc_lbl_vServer_PhysicalHost = new GridBagConstraints();
		gbc_lbl_vServer_PhysicalHost.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_PhysicalHost.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_PhysicalHost.gridx = 0;
		gbc_lbl_vServer_PhysicalHost.gridy = 1;
		panel_vServer_dataEntry.add(lbl_vServer_PhysicalHost, gbc_lbl_vServer_PhysicalHost);

		// code for comboBox_vServer_pServer_Name
		GridBagConstraints gbc_comboBox_vServer_pServer_Name = new GridBagConstraints();
		gbc_comboBox_vServer_pServer_Name.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_vServer_pServer_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_vServer_pServer_Name.gridx = 1;
		gbc_comboBox_vServer_pServer_Name.gridy = 1;
		panel_vServer_dataEntry.add(comboBox_vServer_pServer_Name, gbc_comboBox_vServer_pServer_Name);

		JLabel lbl_vServer_OS = new JLabel("OS");
		GridBagConstraints gbc_lbl_vServer_OS = new GridBagConstraints();
		gbc_lbl_vServer_OS.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_OS.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_OS.gridx = 2;
		gbc_lbl_vServer_OS.gridy = 1;
		panel_vServer_dataEntry.add(lbl_vServer_OS, gbc_lbl_vServer_OS);

		textField_vServer_OS = new JTextField();
		GridBagConstraints gbc_textField_vServer_OS = new GridBagConstraints();
		gbc_textField_vServer_OS.insets = new Insets(0, 0, 5, 5);
		gbc_textField_vServer_OS.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_vServer_OS.gridx = 3;
		gbc_textField_vServer_OS.gridy = 1;
		panel_vServer_dataEntry.add(textField_vServer_OS, gbc_textField_vServer_OS);
		textField_vServer_OS.setColumns(10);

		JLabel lbl_vServer_StatusON = new JLabel("Status_ON");
		GridBagConstraints gbc_lbl_vServer_StatusON = new GridBagConstraints();
		gbc_lbl_vServer_StatusON.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_StatusON.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_StatusON.gridx = 0;
		gbc_lbl_vServer_StatusON.gridy = 2;
		panel_vServer_dataEntry.add(lbl_vServer_StatusON, gbc_lbl_vServer_StatusON);

		// code forcomboBox_vServer_StatusON
		comboBox_vServer_StatusON.setModel(new DefaultComboBoxModel(new String[] {"", "T", "F"}));
		GridBagConstraints gbc_comboBox_vServer_StatusON = new GridBagConstraints();
		gbc_comboBox_vServer_StatusON.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_vServer_StatusON.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_vServer_StatusON.gridx = 1;
		gbc_comboBox_vServer_StatusON.gridy = 2;
		panel_vServer_dataEntry.add(comboBox_vServer_StatusON, gbc_comboBox_vServer_StatusON);

		JLabel lbl_vServer_IpAddress01 = new JLabel("IP Address 01");
		GridBagConstraints gbc_lbl_vServer_IpAddress01 = new GridBagConstraints();
		gbc_lbl_vServer_IpAddress01.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_IpAddress01.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_IpAddress01.gridx = 2;
		gbc_lbl_vServer_IpAddress01.gridy = 2;
		panel_vServer_dataEntry.add(lbl_vServer_IpAddress01, gbc_lbl_vServer_IpAddress01);

		textField_vServer_IPAdd01 = new JTextField();
		GridBagConstraints gbc_textField_vServer_IPAdd01 = new GridBagConstraints();
		gbc_textField_vServer_IPAdd01.insets = new Insets(0, 0, 5, 5);
		gbc_textField_vServer_IPAdd01.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_vServer_IPAdd01.gridx = 3;
		gbc_textField_vServer_IPAdd01.gridy = 2;
		panel_vServer_dataEntry.add(textField_vServer_IPAdd01, gbc_textField_vServer_IPAdd01);
		textField_vServer_IPAdd01.setColumns(10);

		JLabel lbl_vServer_AssignedMem = new JLabel("Assigned Memory");
		GridBagConstraints gbc_lbl_vServer_AssignedMem = new GridBagConstraints();
		gbc_lbl_vServer_AssignedMem.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_AssignedMem.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_AssignedMem.gridx = 0;
		gbc_lbl_vServer_AssignedMem.gridy = 3;
		panel_vServer_dataEntry.add(lbl_vServer_AssignedMem, gbc_lbl_vServer_AssignedMem);

		//code for comboBox_vServer_AssignedMem
		populateCombobox(comboBox_vServer_AssignedMem, "dbo.vServer", "assignedMemory", false);
		comboBox_vServer_AssignedMem.setEditable(true);
		GridBagConstraints gbc_comboBox_vServer_AssignedMem = new GridBagConstraints();
		gbc_comboBox_vServer_AssignedMem.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_vServer_AssignedMem.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_vServer_AssignedMem.gridx = 1;
		gbc_comboBox_vServer_AssignedMem.gridy = 3;
		panel_vServer_dataEntry.add(comboBox_vServer_AssignedMem, gbc_comboBox_vServer_AssignedMem);

		JLabel lbl_vServer_IpAddress02 = new JLabel("IP Address 02");
		GridBagConstraints gbc_lbl_vServer_IpAddress02 = new GridBagConstraints();
		gbc_lbl_vServer_IpAddress02.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_IpAddress02.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_IpAddress02.gridx = 2;
		gbc_lbl_vServer_IpAddress02.gridy = 3;
		panel_vServer_dataEntry.add(lbl_vServer_IpAddress02, gbc_lbl_vServer_IpAddress02);

		textField_vServer_IPAdd02 = new JTextField();
		GridBagConstraints gbc_textField_vServer_IPAdd02 = new GridBagConstraints();
		gbc_textField_vServer_IPAdd02.insets = new Insets(0, 0, 5, 5);
		gbc_textField_vServer_IPAdd02.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_vServer_IPAdd02.gridx = 3;
		gbc_textField_vServer_IPAdd02.gridy = 3;
		panel_vServer_dataEntry.add(textField_vServer_IPAdd02, gbc_textField_vServer_IPAdd02);
		textField_vServer_IPAdd02.setColumns(10);

		JLabel lbl_vServer_VHD_Size = new JLabel("VHD Size");
		GridBagConstraints gbc_lbl_vServer_VHD_Size = new GridBagConstraints();
		gbc_lbl_vServer_VHD_Size.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_VHD_Size.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_VHD_Size.gridx = 0;
		gbc_lbl_vServer_VHD_Size.gridy = 4;
		panel_vServer_dataEntry.add(lbl_vServer_VHD_Size, gbc_lbl_vServer_VHD_Size);

		//code for comboBox_vServer_VHDsize
		populateCombobox(comboBox_vServer_VHDsize, "dbo.vServer", "VHD_Size", false);
		comboBox_vServer_VHDsize.setEditable(true);
		GridBagConstraints gbc_comboBox_vServer_VHDsize = new GridBagConstraints();
		gbc_comboBox_vServer_VHDsize.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_vServer_VHDsize.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_vServer_VHDsize.gridx = 1;
		gbc_comboBox_vServer_VHDsize.gridy = 4;
		panel_vServer_dataEntry.add(comboBox_vServer_VHDsize, gbc_comboBox_vServer_VHDsize);

		JLabel lbl_vServer_IpAddress03 = new JLabel("IP Address 03");
		GridBagConstraints gbc_lbl_vServer_IpAddress03 = new GridBagConstraints();
		gbc_lbl_vServer_IpAddress03.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_IpAddress03.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_IpAddress03.gridx = 2;
		gbc_lbl_vServer_IpAddress03.gridy = 4;
		panel_vServer_dataEntry.add(lbl_vServer_IpAddress03, gbc_lbl_vServer_IpAddress03);

		textField_vServer_IPAdd03 = new JTextField();
		GridBagConstraints gbc_textField_vServer_IPAdd03 = new GridBagConstraints();
		gbc_textField_vServer_IPAdd03.insets = new Insets(0, 0, 5, 5);
		gbc_textField_vServer_IPAdd03.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_vServer_IPAdd03.gridx = 3;
		gbc_textField_vServer_IPAdd03.gridy = 4;
		panel_vServer_dataEntry.add(textField_vServer_IPAdd03, gbc_textField_vServer_IPAdd03);
		textField_vServer_IPAdd03.setColumns(10);

		JLabel lbl_vServer_BackupDestination = new JLabel("Backup Destination");
		GridBagConstraints gbc_lbl_vServer_BackupDestination = new GridBagConstraints();
		gbc_lbl_vServer_BackupDestination.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_BackupDestination.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_BackupDestination.gridx = 0;
		gbc_lbl_vServer_BackupDestination.gridy = 5;
		panel_vServer_dataEntry.add(lbl_vServer_BackupDestination, gbc_lbl_vServer_BackupDestination);

		textField_vServer_backupDestination = new JTextField();
		GridBagConstraints gbc_textField_vServer_backupDestination = new GridBagConstraints();
		gbc_textField_vServer_backupDestination.insets = new Insets(0, 0, 5, 5);
		gbc_textField_vServer_backupDestination.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_vServer_backupDestination.gridx = 1;
		gbc_textField_vServer_backupDestination.gridy = 5;
		panel_vServer_dataEntry.add(textField_vServer_backupDestination, gbc_textField_vServer_backupDestination);
		textField_vServer_backupDestination.setColumns(10);

		JLabel lbl_vServer_PointOfContact = new JLabel("Point of Contact");
		GridBagConstraints gbc_lbl_vServer_PointOfContact = new GridBagConstraints();
		gbc_lbl_vServer_PointOfContact.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_PointOfContact.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_PointOfContact.gridx = 2;
		gbc_lbl_vServer_PointOfContact.gridy = 0;
		panel_vServer_dataEntry.add(lbl_vServer_PointOfContact, gbc_lbl_vServer_PointOfContact);

		JLabel lbl_vServer_IpAddress04 = new JLabel("IP Address 04");
		GridBagConstraints gbc_lbl_vServer_IpAddress04 = new GridBagConstraints();
		gbc_lbl_vServer_IpAddress04.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_IpAddress04.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_IpAddress04.gridx = 2;
		gbc_lbl_vServer_IpAddress04.gridy = 5;
		panel_vServer_dataEntry.add(lbl_vServer_IpAddress04, gbc_lbl_vServer_IpAddress04);

		textField_vServer_IPAdd04 = new JTextField();
		GridBagConstraints gbc_textField_vServer_IPAdd04 = new GridBagConstraints();
		gbc_textField_vServer_IPAdd04.insets = new Insets(0, 0, 5, 5);
		gbc_textField_vServer_IPAdd04.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_vServer_IPAdd04.gridx = 3;
		gbc_textField_vServer_IPAdd04.gridy = 5;
		panel_vServer_dataEntry.add(textField_vServer_IPAdd04, gbc_textField_vServer_IPAdd04);
		textField_vServer_IPAdd04.setColumns(10);

		JLabel lbl_vServer_MoreBackups = new JLabel("More Backups");
		GridBagConstraints gbc_lbl_vServer_MoreBackups = new GridBagConstraints();
		gbc_lbl_vServer_MoreBackups.anchor = GridBagConstraints.EAST;
		gbc_lbl_vServer_MoreBackups.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vServer_MoreBackups.gridx = 0;
		gbc_lbl_vServer_MoreBackups.gridy = 6;
		panel_vServer_dataEntry.add(lbl_vServer_MoreBackups, gbc_lbl_vServer_MoreBackups);

		textField_vServer_moreBackUp = new JTextField();
		GridBagConstraints gbc_textField_vServer_moreBackUp = new GridBagConstraints();
		gbc_textField_vServer_moreBackUp.insets = new Insets(0, 0, 5, 5);
		gbc_textField_vServer_moreBackUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_vServer_moreBackUp.gridx = 1;
		gbc_textField_vServer_moreBackUp.gridy = 6;
		panel_vServer_dataEntry.add(textField_vServer_moreBackUp, gbc_textField_vServer_moreBackUp);
		textField_vServer_moreBackUp.setColumns(10);

		JLabel lblServicesApplications = new JLabel("Services / Applications");
		GridBagConstraints gbc_lblServicesApplications = new GridBagConstraints();
		gbc_lblServicesApplications.anchor = GridBagConstraints.EAST;
		gbc_lblServicesApplications.insets = new Insets(0, 0, 5, 5);
		gbc_lblServicesApplications.gridx = 2;
		gbc_lblServicesApplications.gridy = 6;
		panel_vServer_dataEntry.add(lblServicesApplications, gbc_lblServicesApplications);

		textField_vServer_Services = new JTextField();
		GridBagConstraints gbc_textField_vServer_Services = new GridBagConstraints();
		gbc_textField_vServer_Services.insets = new Insets(0, 0, 5, 5);
		gbc_textField_vServer_Services.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_vServer_Services.gridx = 3;
		gbc_textField_vServer_Services.gridy = 6;
		panel_vServer_dataEntry.add(textField_vServer_Services, gbc_textField_vServer_Services);
		textField_vServer_Services.setColumns(10);

		JButton btn_vServer_Add = new JButton("Add");
		btn_vServer_Add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("ADD", "VSERVERS");
			}
		});
		GridBagConstraints gbc_btn_vServer_Add = new GridBagConstraints();
		gbc_btn_vServer_Add.insets = new Insets(0, 0, 0, 5);
		gbc_btn_vServer_Add.gridx = 1;
		gbc_btn_vServer_Add.gridy = 7;
		panel_vServer_dataEntry.add(btn_vServer_Add, gbc_btn_vServer_Add);

		JButton btn_vServer_Update = new JButton("Update");
		btn_vServer_Update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("UPDATE", "VSERVERS");
			}
		});
		GridBagConstraints gbc_btn_vServer_Update = new GridBagConstraints();
		gbc_btn_vServer_Update.anchor = GridBagConstraints.EAST;
		gbc_btn_vServer_Update.insets = new Insets(0, 0, 0, 5);
		gbc_btn_vServer_Update.gridx = 2;
		gbc_btn_vServer_Update.gridy = 7;
		panel_vServer_dataEntry.add(btn_vServer_Update, gbc_btn_vServer_Update);

		JButton btn_vServer_Delete = new JButton("Delete");
		btn_vServer_Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("DELETE", "VSERVERS");
			}
		});
		GridBagConstraints gbc_btn_vServer_Delete = new GridBagConstraints();
		gbc_btn_vServer_Delete.insets = new Insets(0, 0, 0, 5);
		gbc_btn_vServer_Delete.gridx = 3;
		gbc_btn_vServer_Delete.gridy = 7;
		panel_vServer_dataEntry.add(btn_vServer_Delete, gbc_btn_vServer_Delete);
		vServersTab.setLayout(gl_vServersTab);

		/////////////////////////////////////////
		//Start Code for Network Equipment Tab //
		/////////////////////////////////////////
		JPanel netEQTab = new JPanel();
		tabbedPane.addTab("Network Equipment", null, netEQTab, null);
		jTable_netEQ = createTable("Network_Equipment");
		jTable_netEQ.setBackground(Color.WHITE);
		jTable_netEQ.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		jTable_netEQ.setSurrendersFocusOnKeystroke(true);
		jTable_netEQ.setFillsViewportHeight(true);
		jTable_netEQ.setColumnSelectionAllowed(true);
		jTable_netEQ.setCellSelectionEnabled(true);
		JScrollPane scrollPane_netEQ = new JScrollPane(jTable_netEQ);

		JPanel panel_NetEQ_dataEntry = new JPanel();
		GroupLayout gl_netEQTab = new GroupLayout(netEQTab);
		gl_netEQTab.setHorizontalGroup(
				gl_netEQTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_netEQTab.createSequentialGroup()
						.addGroup(gl_netEQTab.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_netEQTab.createSequentialGroup()
										.addContainerGap()
										.addComponent(scrollPane_netEQ, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
										.addGroup(gl_netEQTab.createSequentialGroup()
												.addContainerGap()
												.addComponent(panel_NetEQ_dataEntry, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
												.addGap(12)))
												.addContainerGap())
				);
		gl_netEQTab.setVerticalGroup(
				gl_netEQTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_netEQTab.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane_netEQ, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(panel_NetEQ_dataEntry, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
						.addContainerGap())
				);
		GridBagLayout gbl_panel_NetEQ_dataEntry = new GridBagLayout();
		gbl_panel_NetEQ_dataEntry.columnWidths = new int[] {100, 100, 0, 100, 100};
		gbl_panel_NetEQ_dataEntry.rowHeights = new int[] {14, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_NetEQ_dataEntry.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0};
		gbl_panel_NetEQ_dataEntry.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_NetEQ_dataEntry.setLayout(gbl_panel_NetEQ_dataEntry);

		JLabel lbl_NetEQ_SerialNumber = new JLabel("NetEQ Serial Number");
		GridBagConstraints gbc_lbl_NetEQ_SerialNumber = new GridBagConstraints();
		gbc_lbl_NetEQ_SerialNumber.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_SerialNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_SerialNumber.gridx = 0;
		gbc_lbl_NetEQ_SerialNumber.gridy = 0;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_SerialNumber, gbc_lbl_NetEQ_SerialNumber);

		//code for comboBox_NetEQ_SerialNum
		populateCombobox(comboBox_NetEQ_SerialNum, "dbo.Network_Equipment", "Net_EQ_Serial_Num", false);
		comboBox_NetEQ_SerialNum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_NetEQ_SerialNum.getSelectedItem() != null)
					populateFields("NETEQ");
			}
		});
		comboBox_NetEQ_SerialNum.setEditable(true);
		GridBagConstraints gbc_comboBox_NetEQ_SerialNum = new GridBagConstraints();
		gbc_comboBox_NetEQ_SerialNum.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_NetEQ_SerialNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_NetEQ_SerialNum.gridx = 1;
		gbc_comboBox_NetEQ_SerialNum.gridy = 0;
		panel_NetEQ_dataEntry.add(comboBox_NetEQ_SerialNum, gbc_comboBox_NetEQ_SerialNum);

		JLabel lbl_NetEQ_Manufacturer = new JLabel("Manufacturer");
		GridBagConstraints gbc_lbl_NetEQ_Manufacturer = new GridBagConstraints();
		gbc_lbl_NetEQ_Manufacturer.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_Manufacturer.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_Manufacturer.gridx = 2;
		gbc_lbl_NetEQ_Manufacturer.gridy = 0;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_Manufacturer, gbc_lbl_NetEQ_Manufacturer);

		textField_NetEQ_Manufacturer = new JTextField();
		GridBagConstraints gbc_textField_NetEQ_Manufacturer = new GridBagConstraints();
		gbc_textField_NetEQ_Manufacturer.insets = new Insets(0, 0, 5, 5);
		gbc_textField_NetEQ_Manufacturer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_NetEQ_Manufacturer.gridx = 3;
		gbc_textField_NetEQ_Manufacturer.gridy = 0;
		panel_NetEQ_dataEntry.add(textField_NetEQ_Manufacturer, gbc_textField_NetEQ_Manufacturer);
		textField_NetEQ_Manufacturer.setColumns(10);

		JLabel lbl_NetEQ_Hostname = new JLabel("HostName");
		GridBagConstraints gbc_lbl_NetEQ_Hostname = new GridBagConstraints();
		gbc_lbl_NetEQ_Hostname.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_Hostname.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_Hostname.gridx = 0;
		gbc_lbl_NetEQ_Hostname.gridy = 1;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_Hostname, gbc_lbl_NetEQ_Hostname);

		// code for comboBox_NetEQ_hostName
		populateCombobox(comboBox_NetEQ_hostName, "dbo.Network_Equipment", "hostName", false);
		comboBox_NetEQ_hostName.setEditable(true);
		GridBagConstraints gbc_comboBox_NetEQ_hostName = new GridBagConstraints();
		gbc_comboBox_NetEQ_hostName.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_NetEQ_hostName.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_NetEQ_hostName.gridx = 1;
		gbc_comboBox_NetEQ_hostName.gridy = 1;
		panel_NetEQ_dataEntry.add(comboBox_NetEQ_hostName, gbc_comboBox_NetEQ_hostName);

		JLabel lbl_NetEQ_Model = new JLabel("Model");
		GridBagConstraints gbc_lbl_NetEQ_Model = new GridBagConstraints();
		gbc_lbl_NetEQ_Model.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_Model.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_Model.gridx = 2;
		gbc_lbl_NetEQ_Model.gridy = 1;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_Model, gbc_lbl_NetEQ_Model);

		textField_NetEQ_Model = new JTextField();
		GridBagConstraints gbc_textField_NetEQ_Model = new GridBagConstraints();
		gbc_textField_NetEQ_Model.insets = new Insets(0, 0, 5, 5);
		gbc_textField_NetEQ_Model.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_NetEQ_Model.gridx = 3;
		gbc_textField_NetEQ_Model.gridy = 1;
		panel_NetEQ_dataEntry.add(textField_NetEQ_Model, gbc_textField_NetEQ_Model);
		textField_NetEQ_Model.setColumns(10);

		JLabel lbl_NetEQ_Type = new JLabel("Type");
		GridBagConstraints gbc_lbl_NetEQ_Type = new GridBagConstraints();
		gbc_lbl_NetEQ_Type.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_Type.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_Type.gridx = 0;
		gbc_lbl_NetEQ_Type.gridy = 2;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_Type, gbc_lbl_NetEQ_Type);

		// code for comboBox_NetEQ_Type
		populateCombobox(comboBox_NetEQ_Type, "dbo.Network_Equipment", "Type", false);
		comboBox_NetEQ_Type.setEditable(true);
		GridBagConstraints gbc_comboBox_NetEQ_Type = new GridBagConstraints();
		gbc_comboBox_NetEQ_Type.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_NetEQ_Type.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_NetEQ_Type.gridx = 1;
		gbc_comboBox_NetEQ_Type.gridy = 2;
		panel_NetEQ_dataEntry.add(comboBox_NetEQ_Type, gbc_comboBox_NetEQ_Type);

		JLabel lbl_NetEQ_IPAdd01 = new JLabel("IP Address 01");
		GridBagConstraints gbc_lbl_NetEQ_IPAdd01 = new GridBagConstraints();
		gbc_lbl_NetEQ_IPAdd01.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_IPAdd01.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_IPAdd01.gridx = 2;
		gbc_lbl_NetEQ_IPAdd01.gridy = 2;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_IPAdd01, gbc_lbl_NetEQ_IPAdd01);

		textField_NetEQ_IPAdd01 = new JTextField();
		GridBagConstraints gbc_textField_NetEQ_IPAdd01 = new GridBagConstraints();
		gbc_textField_NetEQ_IPAdd01.insets = new Insets(0, 0, 5, 5);
		gbc_textField_NetEQ_IPAdd01.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_NetEQ_IPAdd01.gridx = 3;
		gbc_textField_NetEQ_IPAdd01.gridy = 2;
		panel_NetEQ_dataEntry.add(textField_NetEQ_IPAdd01, gbc_textField_NetEQ_IPAdd01);
		textField_NetEQ_IPAdd01.setColumns(10);

		JLabel lbl_NetEQ_StatusOn = new JLabel("Status ON");
		GridBagConstraints gbc_lbl_NetEQ_StatusOn = new GridBagConstraints();
		gbc_lbl_NetEQ_StatusOn.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_StatusOn.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_StatusOn.gridx = 0;
		gbc_lbl_NetEQ_StatusOn.gridy = 3;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_StatusOn, gbc_lbl_NetEQ_StatusOn);

		//code for comboBox_NetEQ_StatusOn
		comboBox_NetEQ_StatusOn.setModel(new DefaultComboBoxModel(new String[] {"", "T", "F"}));
		comboBox_NetEQ_StatusOn.setEditable(true);
		GridBagConstraints gbc_comboBox_NetEQ_StatusOn = new GridBagConstraints();
		gbc_comboBox_NetEQ_StatusOn.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_NetEQ_StatusOn.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_NetEQ_StatusOn.gridx = 1;
		gbc_comboBox_NetEQ_StatusOn.gridy = 3;
		panel_NetEQ_dataEntry.add(comboBox_NetEQ_StatusOn, gbc_comboBox_NetEQ_StatusOn);

		JLabel lbl_NetEQ_IPAdd02 = new JLabel("IP Address 02");
		GridBagConstraints gbc_lbl_NetEQ_IPAdd02 = new GridBagConstraints();
		gbc_lbl_NetEQ_IPAdd02.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_IPAdd02.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_IPAdd02.gridx = 2;
		gbc_lbl_NetEQ_IPAdd02.gridy = 3;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_IPAdd02, gbc_lbl_NetEQ_IPAdd02);

		textField_NetEQ_IPAdd02 = new JTextField();
		GridBagConstraints gbc_textField_NetEQ_IPAdd02 = new GridBagConstraints();
		gbc_textField_NetEQ_IPAdd02.insets = new Insets(0, 0, 5, 5);
		gbc_textField_NetEQ_IPAdd02.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_NetEQ_IPAdd02.gridx = 3;
		gbc_textField_NetEQ_IPAdd02.gridy = 3;
		panel_NetEQ_dataEntry.add(textField_NetEQ_IPAdd02, gbc_textField_NetEQ_IPAdd02);
		textField_NetEQ_IPAdd02.setColumns(10);

		JLabel lbl_NetEQ_RackNum = new JLabel("Rack Number");
		GridBagConstraints gbc_lbl_NetEQ_RackNum = new GridBagConstraints();
		gbc_lbl_NetEQ_RackNum.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_RackNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_RackNum.gridx = 0;
		gbc_lbl_NetEQ_RackNum.gridy = 4;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_RackNum, gbc_lbl_NetEQ_RackNum);

		// code for comboBox_NetEQ_RackNum
		comboBox_NetEQ_RackNum.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03"}));
		GridBagConstraints gbc_comboBox_NetEQ_RackNum = new GridBagConstraints();
		gbc_comboBox_NetEQ_RackNum.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_NetEQ_RackNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_NetEQ_RackNum.gridx = 1;
		gbc_comboBox_NetEQ_RackNum.gridy = 4;
		panel_NetEQ_dataEntry.add(comboBox_NetEQ_RackNum, gbc_comboBox_NetEQ_RackNum);

		JLabel lbl_NetEQ_IPAdd03 = new JLabel("IP Address 03");
		GridBagConstraints gbc_lbl_NetEQ_IPAdd03 = new GridBagConstraints();
		gbc_lbl_NetEQ_IPAdd03.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_IPAdd03.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_IPAdd03.gridx = 2;
		gbc_lbl_NetEQ_IPAdd03.gridy = 4;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_IPAdd03, gbc_lbl_NetEQ_IPAdd03);

		textField_NetEQ_IPAdd03 = new JTextField();
		GridBagConstraints gbc_textField_NetEQ_IPAdd03 = new GridBagConstraints();
		gbc_textField_NetEQ_IPAdd03.insets = new Insets(0, 0, 5, 5);
		gbc_textField_NetEQ_IPAdd03.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_NetEQ_IPAdd03.gridx = 3;
		gbc_textField_NetEQ_IPAdd03.gridy = 4;
		panel_NetEQ_dataEntry.add(textField_NetEQ_IPAdd03, gbc_textField_NetEQ_IPAdd03);
		textField_NetEQ_IPAdd03.setColumns(10);

		JLabel lbl_NetEQ_Rat = new JLabel("RAT");
		GridBagConstraints gbc_lbl_NetEQ_Rat = new GridBagConstraints();
		gbc_lbl_NetEQ_Rat.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_Rat.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_Rat.gridx = 0;
		gbc_lbl_NetEQ_Rat.gridy = 5;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_Rat, gbc_lbl_NetEQ_Rat);

		textField_NetEQ_RAT = new JTextField();
		GridBagConstraints gbc_textField_NetEQ_RAT = new GridBagConstraints();
		gbc_textField_NetEQ_RAT.insets = new Insets(0, 0, 5, 5);
		gbc_textField_NetEQ_RAT.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_NetEQ_RAT.gridx = 1;
		gbc_textField_NetEQ_RAT.gridy = 5;
		panel_NetEQ_dataEntry.add(textField_NetEQ_RAT, gbc_textField_NetEQ_RAT);
		textField_NetEQ_RAT.setColumns(10);

		JLabel lbl_NetEQ_IPAdd04 = new JLabel("IP Address 04");
		GridBagConstraints gbc_lbl_NetEQ_IPAdd04 = new GridBagConstraints();
		gbc_lbl_NetEQ_IPAdd04.anchor = GridBagConstraints.EAST;
		gbc_lbl_NetEQ_IPAdd04.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_IPAdd04.gridx = 2;
		gbc_lbl_NetEQ_IPAdd04.gridy = 5;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_IPAdd04, gbc_lbl_NetEQ_IPAdd04);

		textField_NetEQ_IPAdd04 = new JTextField();
		GridBagConstraints gbc_textField_NetEQ_IPAdd04 = new GridBagConstraints();
		gbc_textField_NetEQ_IPAdd04.insets = new Insets(0, 0, 5, 5);
		gbc_textField_NetEQ_IPAdd04.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_NetEQ_IPAdd04.gridx = 3;
		gbc_textField_NetEQ_IPAdd04.gridy = 5;
		panel_NetEQ_dataEntry.add(textField_NetEQ_IPAdd04, gbc_textField_NetEQ_IPAdd04);
		textField_NetEQ_IPAdd04.setColumns(10);

		JLabel lbl_NetEQ_ServicesApplications = new JLabel("Services / Applications");
		GridBagConstraints gbc_lbl_NetEQ_ServicesApplications = new GridBagConstraints();
		gbc_lbl_NetEQ_ServicesApplications.anchor = GridBagConstraints.NORTHEAST;
		gbc_lbl_NetEQ_ServicesApplications.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_ServicesApplications.gridx = 0;
		gbc_lbl_NetEQ_ServicesApplications.gridy = 6;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_ServicesApplications, gbc_lbl_NetEQ_ServicesApplications);

		//code for textArea_NetEQ_Services
		GridBagConstraints gbc_textArea_NetEQ_Services = new GridBagConstraints();
		gbc_textArea_NetEQ_Services.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_NetEQ_Services.fill = GridBagConstraints.BOTH;
		gbc_textArea_NetEQ_Services.gridx = 1;
		gbc_textArea_NetEQ_Services.gridy = 6;
		panel_NetEQ_dataEntry.add(textArea_NetEQ_Services, gbc_textArea_NetEQ_Services);

		JLabel lbl_NetEQ_Notes = new JLabel("Notes:");
		GridBagConstraints gbc_lbl_NetEQ_Notes = new GridBagConstraints();
		gbc_lbl_NetEQ_Notes.anchor = GridBagConstraints.NORTHEAST;
		gbc_lbl_NetEQ_Notes.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_NetEQ_Notes.gridx = 2;
		gbc_lbl_NetEQ_Notes.gridy = 6;
		panel_NetEQ_dataEntry.add(lbl_NetEQ_Notes, gbc_lbl_NetEQ_Notes);

		// code for textArea_NetEQ_notes
		GridBagConstraints gbc_textArea_NetEQ_notes = new GridBagConstraints();
		gbc_textArea_NetEQ_notes.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_NetEQ_notes.fill = GridBagConstraints.BOTH;
		gbc_textArea_NetEQ_notes.gridx = 3;
		gbc_textArea_NetEQ_notes.gridy = 6;
		panel_NetEQ_dataEntry.add(textArea_NetEQ_notes, gbc_textArea_NetEQ_notes);

		JButton btn_NetEQ_Add = new JButton("Add");
		btn_NetEQ_Add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("ADD", "NETEQ");
			}
		});
		GridBagConstraints gbc_btn_NetEQ_Add = new GridBagConstraints();
		gbc_btn_NetEQ_Add.insets = new Insets(0, 0, 5, 5);
		gbc_btn_NetEQ_Add.gridx = 1;
		gbc_btn_NetEQ_Add.gridy = 7;
		panel_NetEQ_dataEntry.add(btn_NetEQ_Add, gbc_btn_NetEQ_Add);

		JButton btn_NetEQ_Update = new JButton("Update");
		btn_NetEQ_Update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("Update", "NETEQ");
			}
		});
		GridBagConstraints gbc_btn_NetEQ_Update = new GridBagConstraints();
		gbc_btn_NetEQ_Update.anchor = GridBagConstraints.EAST;
		gbc_btn_NetEQ_Update.insets = new Insets(0, 0, 5, 5);
		gbc_btn_NetEQ_Update.gridx = 2;
		gbc_btn_NetEQ_Update.gridy = 7;
		panel_NetEQ_dataEntry.add(btn_NetEQ_Update, gbc_btn_NetEQ_Update);

		JButton btn_NetEQ_Delete = new JButton("Delete");
		btn_NetEQ_Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("Delete", "NETEQ");
			}
		});
		GridBagConstraints gbc_btn_NetEQ_Delete = new GridBagConstraints();
		gbc_btn_NetEQ_Delete.insets = new Insets(0, 0, 5, 5);
		gbc_btn_NetEQ_Delete.gridx = 3;
		gbc_btn_NetEQ_Delete.gridy = 7;
		panel_NetEQ_dataEntry.add(btn_NetEQ_Delete, gbc_btn_NetEQ_Delete);
		netEQTab.setLayout(gl_netEQTab);

		/////////////////////////////////////////
		//Start Code for PServer_PSU Tab       //
		/////////////////////////////////////////
		JPanel pServer_PSUTab = new JPanel();
		tabbedPane.addTab("Physical Server PSU", null, pServer_PSUTab, null);
		jTable_pServer_PSU = createTable("pServer_PSU");
		jTable_pServer_PSU.setBackground(Color.WHITE);
		jTable_pServer_PSU.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		jTable_pServer_PSU.setSurrendersFocusOnKeystroke(true);
		jTable_pServer_PSU.setFillsViewportHeight(true);
		jTable_pServer_PSU.setColumnSelectionAllowed(true);
		jTable_pServer_PSU.setCellSelectionEnabled(true);
		JScrollPane scrollPane_pServer_PSU = new JScrollPane(jTable_pServer_PSU);

		JPanel panel_pServer_PSU_dataEntry = new JPanel();
		GroupLayout gl_pServer_PSUTab = new GroupLayout(pServer_PSUTab);
		gl_pServer_PSUTab.setHorizontalGroup(
				gl_pServer_PSUTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pServer_PSUTab.createSequentialGroup()
						.addGroup(gl_pServer_PSUTab.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pServer_PSUTab.createSequentialGroup()
										.addContainerGap()
										.addComponent(scrollPane_pServer_PSU, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
										.addGroup(gl_pServer_PSUTab.createSequentialGroup()
												.addGap(12)
												.addComponent(panel_pServer_PSU_dataEntry, GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)))
												.addGap(12))
				);
		gl_pServer_PSUTab.setVerticalGroup(
				gl_pServer_PSUTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pServer_PSUTab.createSequentialGroup()
						.addGap(12)
						.addComponent(scrollPane_pServer_PSU, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(panel_pServer_PSU_dataEntry, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addGap(12))
				);
		GridBagLayout gbl_panel_pServer_PSU_dataEntry = new GridBagLayout();
		gbl_panel_pServer_PSU_dataEntry.columnWidths = new int[] {100, 100, 0, 100, 100};
		gbl_panel_pServer_PSU_dataEntry.rowHeights = new int[] {14, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0};
		gbl_panel_pServer_PSU_dataEntry.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE, 1.0};
		gbl_panel_pServer_PSU_dataEntry.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_pServer_PSU_dataEntry.setLayout(gbl_panel_pServer_PSU_dataEntry);

		JLabel lbl_pServer_PSU_PsuNum = new JLabel("PSU Number");
		GridBagConstraints gbc_lbl_pServer_PSU_PsuNum = new GridBagConstraints();
		gbc_lbl_pServer_PSU_PsuNum.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_PSU_PsuNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_PSU_PsuNum.gridx = 0;
		gbc_lbl_pServer_PSU_PsuNum.gridy = 0;
		panel_pServer_PSU_dataEntry.add(lbl_pServer_PSU_PsuNum, gbc_lbl_pServer_PSU_PsuNum);

		//code for comboBox_pServer_PSU_psuNum
		comboBox_pServer_PSU_psuNum.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBox_pServer_PSU_psuNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_pServer_PSU_psuNum.getSelectedItem().toString() != null)
					populateFields("PSERVERPSU");
			}
		});
		GridBagConstraints gbc_comboBox_pServer_PSU_psuNum = new GridBagConstraints();
		gbc_comboBox_pServer_PSU_psuNum.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_pServer_PSU_psuNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_pServer_PSU_psuNum.gridx = 1;
		gbc_comboBox_pServer_PSU_psuNum.gridy = 0;
		panel_pServer_PSU_dataEntry.add(comboBox_pServer_PSU_psuNum, gbc_comboBox_pServer_PSU_psuNum);

		JLabel lbl_pServer_PSU_pServerName = new JLabel("Physical  Server Name");
		GridBagConstraints gbc_lbl_pServer_PSU_pServerName = new GridBagConstraints();
		gbc_lbl_pServer_PSU_pServerName.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_PSU_pServerName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_PSU_pServerName.gridx = 2;
		gbc_lbl_pServer_PSU_pServerName.gridy = 0;
		panel_pServer_PSU_dataEntry.add(lbl_pServer_PSU_pServerName, gbc_lbl_pServer_PSU_pServerName);

		//code for comboBox_pServer_PSU_pServerName
		populateCombobox(comboBox_pServer_PSU_pServerName, "pservers", "pServer_Name", false);
		comboBox_pServer_PSU_pServerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_pServer_PSU_pServerName.getSelectedItem().toString() != null)
					populateFields("PSERVERPSU");
			}
		});
		GridBagConstraints gbc_comboBox_pServer_PSU_pServerName = new GridBagConstraints();
		gbc_comboBox_pServer_PSU_pServerName.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_pServer_PSU_pServerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_pServer_PSU_pServerName.gridx = 3;
		gbc_comboBox_pServer_PSU_pServerName.gridy = 0;
		panel_pServer_PSU_dataEntry.add(comboBox_pServer_PSU_pServerName, gbc_comboBox_pServer_PSU_pServerName);

		JLabel lbl_pServer_PSU_UpsName = new JLabel("UPS Name");
		GridBagConstraints gbc_lbl_pServer_PSU_UpsName = new GridBagConstraints();
		gbc_lbl_pServer_PSU_UpsName.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_PSU_UpsName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_PSU_UpsName.gridx = 0;
		gbc_lbl_pServer_PSU_UpsName.gridy = 1;
		panel_pServer_PSU_dataEntry.add(lbl_pServer_PSU_UpsName, gbc_lbl_pServer_PSU_UpsName);

		//code for comboBox_pServer_PSU_UpsName
		comboBox_pServer_PSU_UpsName.addItem("");		
		populateCombobox(comboBox_pServer_PSU_UpsName, "ups", "upsName", false);
		GridBagConstraints gbc_comboBox_pServer_PSU_UpsName = new GridBagConstraints();
		gbc_comboBox_pServer_PSU_UpsName.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_pServer_PSU_UpsName.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_pServer_PSU_UpsName.gridx = 1;
		gbc_comboBox_pServer_PSU_UpsName.gridy = 1;
		panel_pServer_PSU_dataEntry.add(comboBox_pServer_PSU_UpsName, gbc_comboBox_pServer_PSU_UpsName);

		JLabel lbl_pServer_PSU_PduName = new JLabel("PDU Name");
		GridBagConstraints gbc_lbl_pServer_PSU_PduName = new GridBagConstraints();
		gbc_lbl_pServer_PSU_PduName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_PSU_PduName.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_PSU_PduName.gridx = 2;
		gbc_lbl_pServer_PSU_PduName.gridy = 1;
		panel_pServer_PSU_dataEntry.add(lbl_pServer_PSU_PduName, gbc_lbl_pServer_PSU_PduName);

		//code for comboBox_pServer_PSU_PDUName
		comboBox_pServer_PSU_PDUName.addItem("");
		populateCombobox(comboBox_pServer_PSU_PDUName, "pdu", "pdu_Name", false);
		GridBagConstraints gbc_comboBox_pServer_PSU_PDUName = new GridBagConstraints();
		gbc_comboBox_pServer_PSU_PDUName.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_pServer_PSU_PDUName.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_pServer_PSU_PDUName.gridx = 3;
		gbc_comboBox_pServer_PSU_PDUName.gridy = 1;
		panel_pServer_PSU_dataEntry.add(comboBox_pServer_PSU_PDUName, gbc_comboBox_pServer_PSU_PDUName);

		JLabel lblenterUpsName = new JLabel("(Enter UPS Name OR PDU Name)");
		GridBagConstraints gbc_lblenterUpsName = new GridBagConstraints();
		gbc_lblenterUpsName.insets = new Insets(0, 0, 5, 5);
		gbc_lblenterUpsName.gridx = 1;
		gbc_lblenterUpsName.gridy = 2;
		panel_pServer_PSU_dataEntry.add(lblenterUpsName, gbc_lblenterUpsName);

		JLabel lbl_pServer_PSU_outletNum = new JLabel("Outlet Number");
		GridBagConstraints gbc_lbl_pServer_PSU_outletNum = new GridBagConstraints();
		gbc_lbl_pServer_PSU_outletNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_pServer_PSU_outletNum.anchor = GridBagConstraints.EAST;
		gbc_lbl_pServer_PSU_outletNum.gridx = 2;
		gbc_lbl_pServer_PSU_outletNum.gridy = 2;
		panel_pServer_PSU_dataEntry.add(lbl_pServer_PSU_outletNum, gbc_lbl_pServer_PSU_outletNum);

		// code for comboBox_pServer_PSU_outletNum
		comboBox_pServer_PSU_outletNum.setModel(new DefaultComboBoxModel<String>(new String[] {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"}));
		GridBagConstraints gbc_comboBox_pServer_PSU_outletNum = new GridBagConstraints();
		gbc_comboBox_pServer_PSU_outletNum.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_pServer_PSU_outletNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_pServer_PSU_outletNum.gridx = 3;
		gbc_comboBox_pServer_PSU_outletNum.gridy = 2;
		panel_pServer_PSU_dataEntry.add(comboBox_pServer_PSU_outletNum, gbc_comboBox_pServer_PSU_outletNum);

		JButton btn_Server_PSU_ADD = new JButton("Add");
		btn_Server_PSU_ADD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("ADD", "PSERVERPSU");
			}
		});
		GridBagConstraints gbc_btn_Server_PSU_ADD = new GridBagConstraints();
		gbc_btn_Server_PSU_ADD.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_btn_Server_PSU_ADD.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Server_PSU_ADD.gridx = 1;
		gbc_btn_Server_PSU_ADD.gridy = 3;
		panel_pServer_PSU_dataEntry.add(btn_Server_PSU_ADD, gbc_btn_Server_PSU_ADD);

		JButton btn_pServer_PSU_Update = new JButton("Update");
		btn_pServer_PSU_Update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("UPDATE", "PSERVERPSU");
			}
		});
		GridBagConstraints gbc_btn_pServer_PSU_Update = new GridBagConstraints();
		gbc_btn_pServer_PSU_Update.insets = new Insets(0, 0, 5, 5);
		gbc_btn_pServer_PSU_Update.gridx = 2;
		gbc_btn_pServer_PSU_Update.gridy = 3;
		panel_pServer_PSU_dataEntry.add(btn_pServer_PSU_Update, gbc_btn_pServer_PSU_Update);

		JButton button_pServer_PSU_delete = new JButton("Delete");
		button_pServer_PSU_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("DELETE", "PSERVERPSU");
			}
		});
		GridBagConstraints gbc_pServer_PSU_delete = new GridBagConstraints();
		gbc_pServer_PSU_delete.insets = new Insets(0, 0, 5, 0);
		gbc_pServer_PSU_delete.gridx = 3;
		gbc_pServer_PSU_delete.gridy = 3;
		panel_pServer_PSU_dataEntry.add(button_pServer_PSU_delete, gbc_pServer_PSU_delete);
		pServer_PSUTab.setLayout(gl_pServer_PSUTab);
		pServer_PSUTab.setLayout(gl_pServer_PSUTab);

		/////////////////////////////////////////
		//Start Code for AWS_EC2 Tab           //
		/////////////////////////////////////////
		JPanel AWS_EC2Tab = new JPanel();
		tabbedPane.addTab("AWS EC2", null, AWS_EC2Tab, null);
		jTable_AWS_EC2 = createTable("AWS_EC2");
		jTable_AWS_EC2.setBackground(Color.WHITE);
		jTable_AWS_EC2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		jTable_AWS_EC2.setSurrendersFocusOnKeystroke(true);
		jTable_AWS_EC2.setFillsViewportHeight(true);
		jTable_AWS_EC2.setColumnSelectionAllowed(true);
		jTable_AWS_EC2.setCellSelectionEnabled(true);
		JScrollPane scrollPane_AWS_EC2 = new JScrollPane(jTable_AWS_EC2);

		JPanel panel_AWSEC2_dataEntry = new JPanel();
		GroupLayout gl_AWS_EC2Tab = new GroupLayout(AWS_EC2Tab);
		gl_AWS_EC2Tab.setHorizontalGroup(
				gl_AWS_EC2Tab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AWS_EC2Tab.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_AWS_EC2Tab.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_AWS_EC2, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
								.addComponent(panel_AWSEC2_dataEntry, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
								.addContainerGap())
				);
		gl_AWS_EC2Tab.setVerticalGroup(
				gl_AWS_EC2Tab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AWS_EC2Tab.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane_AWS_EC2, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(panel_AWSEC2_dataEntry, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addContainerGap())
				);
		GridBagLayout gbl_panel_AWSEC2_dataEntry = new GridBagLayout();
		gbl_panel_AWSEC2_dataEntry.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_AWSEC2_dataEntry.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_AWSEC2_dataEntry.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_AWSEC2_dataEntry.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_AWSEC2_dataEntry.setLayout(gbl_panel_AWSEC2_dataEntry);

		JLabel lbl_AWSEC2_Instance = new JLabel("Instance");
		GridBagConstraints gbc_lbl_AWSEC2_Instance = new GridBagConstraints();
		gbc_lbl_AWSEC2_Instance.anchor = GridBagConstraints.EAST;
		gbc_lbl_AWSEC2_Instance.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_AWSEC2_Instance.gridx = 0;
		gbc_lbl_AWSEC2_Instance.gridy = 0;
		panel_AWSEC2_dataEntry.add(lbl_AWSEC2_Instance, gbc_lbl_AWSEC2_Instance);

		// for comboBox_AWSEC2_Instance
		populateCombobox(comboBox_AWSEC2_Instance, "dbo.AWS_EC2", "Instance", false);
		comboBox_AWSEC2_Instance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_AWSEC2_Instance.getSelectedItem() != null)
					populateFields("AWSEC2");
			}
		});
		comboBox_AWSEC2_Instance.setEditable(true);
		GridBagConstraints gbc_comboBox_AWSEC2_Instance = new GridBagConstraints();
		gbc_comboBox_AWSEC2_Instance.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_AWSEC2_Instance.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_AWSEC2_Instance.gridx = 1;
		gbc_comboBox_AWSEC2_Instance.gridy = 0;
		panel_AWSEC2_dataEntry.add(comboBox_AWSEC2_Instance, gbc_comboBox_AWSEC2_Instance);

		JLabel lbl_AWSEC2_IPAdd = new JLabel("IP Address");
		GridBagConstraints gbc_lbl_AWSEC2_IPAdd = new GridBagConstraints();
		gbc_lbl_AWSEC2_IPAdd.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_AWSEC2_IPAdd.anchor = GridBagConstraints.EAST;
		gbc_lbl_AWSEC2_IPAdd.gridx = 2;
		gbc_lbl_AWSEC2_IPAdd.gridy = 0;
		panel_AWSEC2_dataEntry.add(lbl_AWSEC2_IPAdd, gbc_lbl_AWSEC2_IPAdd);

		textField_AWSEC2_IPAdd = new JTextField();
		GridBagConstraints gbc_textField_AWSEC2_IPAdd = new GridBagConstraints();
		gbc_textField_AWSEC2_IPAdd.insets = new Insets(0, 0, 5, 0);
		gbc_textField_AWSEC2_IPAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_AWSEC2_IPAdd.gridx = 3;
		gbc_textField_AWSEC2_IPAdd.gridy = 0;
		panel_AWSEC2_dataEntry.add(textField_AWSEC2_IPAdd, gbc_textField_AWSEC2_IPAdd);
		textField_AWSEC2_IPAdd.setColumns(10);

		JLabel lbl_AWSEC2_Hostname = new JLabel("HostName");
		GridBagConstraints gbc_lbl_AWSEC2_Hostname = new GridBagConstraints();
		gbc_lbl_AWSEC2_Hostname.anchor = GridBagConstraints.EAST;
		gbc_lbl_AWSEC2_Hostname.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_AWSEC2_Hostname.gridx = 0;
		gbc_lbl_AWSEC2_Hostname.gridy = 1;
		panel_AWSEC2_dataEntry.add(lbl_AWSEC2_Hostname, gbc_lbl_AWSEC2_Hostname);

		textField_AWSEC2_Hostname = new JTextField();
		GridBagConstraints gbc_textField_AWSEC2_Hostname = new GridBagConstraints();
		gbc_textField_AWSEC2_Hostname.insets = new Insets(0, 0, 5, 5);
		gbc_textField_AWSEC2_Hostname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_AWSEC2_Hostname.gridx = 1;
		gbc_textField_AWSEC2_Hostname.gridy = 1;
		panel_AWSEC2_dataEntry.add(textField_AWSEC2_Hostname, gbc_textField_AWSEC2_Hostname);
		textField_AWSEC2_Hostname.setColumns(10);

		JLabel lbl_AWSEC2_URL = new JLabel("EC2 URL");
		GridBagConstraints gbc_lbl_AWSEC2_URL = new GridBagConstraints();
		gbc_lbl_AWSEC2_URL.anchor = GridBagConstraints.EAST;
		gbc_lbl_AWSEC2_URL.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_AWSEC2_URL.gridx = 2;
		gbc_lbl_AWSEC2_URL.gridy = 1;
		panel_AWSEC2_dataEntry.add(lbl_AWSEC2_URL, gbc_lbl_AWSEC2_URL);

		textField_AWSEC2_URL = new JTextField();
		GridBagConstraints gbc_textField_AWSEC2_URL = new GridBagConstraints();
		gbc_textField_AWSEC2_URL.insets = new Insets(0, 0, 5, 0);
		gbc_textField_AWSEC2_URL.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_AWSEC2_URL.gridx = 3;
		gbc_textField_AWSEC2_URL.gridy = 1;
		panel_AWSEC2_dataEntry.add(textField_AWSEC2_URL, gbc_textField_AWSEC2_URL);
		textField_AWSEC2_URL.setColumns(10);

		JLabel lbl_AWSEC2_OS = new JLabel("OS");
		GridBagConstraints gbc_lbl_AWSEC2_OS = new GridBagConstraints();
		gbc_lbl_AWSEC2_OS.anchor = GridBagConstraints.EAST;
		gbc_lbl_AWSEC2_OS.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_AWSEC2_OS.gridx = 0;
		gbc_lbl_AWSEC2_OS.gridy = 2;
		panel_AWSEC2_dataEntry.add(lbl_AWSEC2_OS, gbc_lbl_AWSEC2_OS);

		textField_AWSEC2_OS = new JTextField();
		GridBagConstraints gbc_textField_AWSEC2_OS = new GridBagConstraints();
		gbc_textField_AWSEC2_OS.insets = new Insets(0, 0, 5, 5);
		gbc_textField_AWSEC2_OS.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_AWSEC2_OS.gridx = 1;
		gbc_textField_AWSEC2_OS.gridy = 2;
		panel_AWSEC2_dataEntry.add(textField_AWSEC2_OS, gbc_textField_AWSEC2_OS);
		textField_AWSEC2_OS.setColumns(10);

		JLabel lbl_AWSEC2_ServicesApplications = new JLabel("Services / Applications");
		GridBagConstraints gbc_lbl_AWSEC2_ServicesApplications = new GridBagConstraints();
		gbc_lbl_AWSEC2_ServicesApplications.anchor = GridBagConstraints.EAST;
		gbc_lbl_AWSEC2_ServicesApplications.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_AWSEC2_ServicesApplications.gridx = 2;
		gbc_lbl_AWSEC2_ServicesApplications.gridy = 2;
		panel_AWSEC2_dataEntry.add(lbl_AWSEC2_ServicesApplications, gbc_lbl_AWSEC2_ServicesApplications);

		textField_AWSEC2_Services = new JTextField();
		GridBagConstraints gbc_textField_AWSEC2_Services = new GridBagConstraints();
		gbc_textField_AWSEC2_Services.insets = new Insets(0, 0, 5, 0);
		gbc_textField_AWSEC2_Services.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_AWSEC2_Services.gridx = 3;
		gbc_textField_AWSEC2_Services.gridy = 2;
		panel_AWSEC2_dataEntry.add(textField_AWSEC2_Services, gbc_textField_AWSEC2_Services);
		textField_AWSEC2_Services.setColumns(10);

		JButton btn_AWSEC2_Add = new JButton("Add");
		btn_AWSEC2_Add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("ADD", "AWS");
			}
		});
		GridBagConstraints gbc_btn_AWSEC2_Add = new GridBagConstraints();
		gbc_btn_AWSEC2_Add.insets = new Insets(0, 0, 0, 5);
		gbc_btn_AWSEC2_Add.gridx = 1;
		gbc_btn_AWSEC2_Add.gridy = 3;
		panel_AWSEC2_dataEntry.add(btn_AWSEC2_Add, gbc_btn_AWSEC2_Add);

		JButton btn_AWSEC2_Update = new JButton("Update");
		btn_AWSEC2_Update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("UPDATE", "AWS");
			}
		});
		GridBagConstraints gbc_btn_AWSEC2_Update = new GridBagConstraints();
		gbc_btn_AWSEC2_Update.insets = new Insets(0, 0, 0, 5);
		gbc_btn_AWSEC2_Update.gridx = 2;
		gbc_btn_AWSEC2_Update.gridy = 3;
		panel_AWSEC2_dataEntry.add(btn_AWSEC2_Update, gbc_btn_AWSEC2_Update);

		JButton btn_AWSEC2_Delete = new JButton("Delete");
		btn_AWSEC2_Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("DELETE", "AWS");
			}
		});
		GridBagConstraints gbc_btn_AWSEC2_Delete = new GridBagConstraints();
		gbc_btn_AWSEC2_Delete.gridx = 3;
		gbc_btn_AWSEC2_Delete.gridy = 3;
		panel_AWSEC2_dataEntry.add(btn_AWSEC2_Delete, gbc_btn_AWSEC2_Delete);
		AWS_EC2Tab.setLayout(gl_AWS_EC2Tab);	

		/////////////////////////////////////////
		//Start Code for UPS Tab           //
		/////////////////////////////////////////
		JPanel UPSTab = new JPanel();
		tabbedPane.addTab("UPS", null, UPSTab, null);
		jTable_UPS = createTable("UPS");
		jTable_UPS.setBackground(Color.WHITE);
		jTable_UPS.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		jTable_UPS.setSurrendersFocusOnKeystroke(true);
		jTable_UPS.setFillsViewportHeight(true);
		jTable_UPS.setColumnSelectionAllowed(true);
		jTable_UPS.setCellSelectionEnabled(true);
		JScrollPane scrollPane_UPS = new JScrollPane(jTable_UPS);

		JPanel panel_UPS_dataEntry = new JPanel();
		GroupLayout gl_UPSTab = new GroupLayout(UPSTab);
		gl_UPSTab.setHorizontalGroup(
				gl_UPSTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_UPSTab.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_UPSTab.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_UPS, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
								.addComponent(panel_UPS_dataEntry, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
								.addContainerGap())
				);
		gl_UPSTab.setVerticalGroup(
				gl_UPSTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_UPSTab.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane_UPS, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(panel_UPS_dataEntry, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addContainerGap())
				);
		GridBagLayout gbl_panel_UPS_dataEntry = new GridBagLayout();
		gbl_panel_UPS_dataEntry.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_UPS_dataEntry.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_UPS_dataEntry.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_UPS_dataEntry.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_UPS_dataEntry.setLayout(gbl_panel_UPS_dataEntry);

		JLabel lbl_UPS_Name = new JLabel("Name");
		GridBagConstraints gbc_lbl_UPS_Name = new GridBagConstraints();
		gbc_lbl_UPS_Name.anchor = GridBagConstraints.EAST;
		gbc_lbl_UPS_Name.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_UPS_Name.gridx = 0;
		gbc_lbl_UPS_Name.gridy = 0;
		panel_UPS_dataEntry.add(lbl_UPS_Name, gbc_lbl_UPS_Name);

		//for comboBox_UPS_Name
		populateCombobox(comboBox_UPS_Name, "dbo.UPS", "upsName", false);
		comboBox_UPS_Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_UPS_Name.getSelectedItem() != null)
					populateFields("UPS");
			}
		});
		comboBox_UPS_Name.setEditable(true);
		GridBagConstraints gbc_comboBox_UPS_Name = new GridBagConstraints();
		gbc_comboBox_UPS_Name.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_UPS_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_UPS_Name.gridx = 1;
		gbc_comboBox_UPS_Name.gridy = 0;
		panel_UPS_dataEntry.add(comboBox_UPS_Name, gbc_comboBox_UPS_Name);

		JLabel lbl_UPS_IPAdd = new JLabel("IP Address");
		GridBagConstraints gbc_lbl_UPS_IPAdd = new GridBagConstraints();
		gbc_lbl_UPS_IPAdd.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_UPS_IPAdd.anchor = GridBagConstraints.EAST;
		gbc_lbl_UPS_IPAdd.gridx = 2;
		gbc_lbl_UPS_IPAdd.gridy = 0;
		panel_UPS_dataEntry.add(lbl_UPS_IPAdd, gbc_lbl_UPS_IPAdd);

		textField_UPS_IPAdd = new JTextField();
		GridBagConstraints gbc_textField_UPS_IPAdd = new GridBagConstraints();
		gbc_textField_UPS_IPAdd.insets = new Insets(0, 0, 5, 0);
		gbc_textField_UPS_IPAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_UPS_IPAdd.gridx = 3;
		gbc_textField_UPS_IPAdd.gridy = 0;
		panel_UPS_dataEntry.add(textField_UPS_IPAdd, gbc_textField_UPS_IPAdd);
		textField_UPS_IPAdd.setColumns(10);

		JLabel lbl_UPS_RackNum = new JLabel("Rack Number");
		GridBagConstraints gbc_lbl_UPS_RackNum = new GridBagConstraints();
		gbc_lbl_UPS_RackNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_UPS_RackNum.gridx = 0;
		gbc_lbl_UPS_RackNum.gridy = 1;
		panel_UPS_dataEntry.add(lbl_UPS_RackNum, gbc_lbl_UPS_RackNum);

		comboBox_UPS_rackNum.addItem("");
		populateCombobox(comboBox_UPS_rackNum, "dbo.Racks", "RackNum", false);
		GridBagConstraints gbc_comboBox_UPS_rackNum = new GridBagConstraints();
		gbc_comboBox_UPS_rackNum.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_UPS_rackNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_UPS_rackNum.gridx = 1;
		gbc_comboBox_UPS_rackNum.gridy = 1;
		panel_UPS_dataEntry.add(comboBox_UPS_rackNum, gbc_comboBox_UPS_rackNum);


		JLabel lbl_UPS_macAdd = new JLabel("MAC Address");
		GridBagConstraints gbc_lbl_UPS_macAdd = new GridBagConstraints();
		gbc_lbl_UPS_macAdd.anchor = GridBagConstraints.EAST;
		gbc_lbl_UPS_macAdd.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_UPS_macAdd.gridx = 2;
		gbc_lbl_UPS_macAdd.gridy = 1;
		panel_UPS_dataEntry.add(lbl_UPS_macAdd, gbc_lbl_UPS_macAdd);

		textField_UPS_maccAdd = new JTextField();
		GridBagConstraints gbc_textField_UPS_maccAdd = new GridBagConstraints();
		gbc_textField_UPS_maccAdd.insets = new Insets(0, 0, 5, 0);
		gbc_textField_UPS_maccAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_UPS_maccAdd.gridx = 3;
		gbc_textField_UPS_maccAdd.gridy = 1;
		panel_UPS_dataEntry.add(textField_UPS_maccAdd, gbc_textField_UPS_maccAdd);
		textField_UPS_maccAdd.setColumns(10);

		JLabel lbl_UPS_model = new JLabel("Model");
		GridBagConstraints gbc_lbl_UPS_model = new GridBagConstraints();
		gbc_lbl_UPS_model.anchor = GridBagConstraints.EAST;
		gbc_lbl_UPS_model.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_UPS_model.gridx = 0;
		gbc_lbl_UPS_model.gridy = 2;
		panel_UPS_dataEntry.add(lbl_UPS_model, gbc_lbl_UPS_model);

		textField_UPS_model = new JTextField();
		GridBagConstraints gbc_textField_UPS_model = new GridBagConstraints();
		gbc_textField_UPS_model.insets = new Insets(0, 0, 5, 5);
		gbc_textField_UPS_model.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_UPS_model.gridx = 1;
		gbc_textField_UPS_model.gridy = 2;
		panel_UPS_dataEntry.add(textField_UPS_model, gbc_textField_UPS_model);
		textField_UPS_model.setColumns(10);

		JLabel lbl_UPS_prodURL = new JLabel("Product URL");
		GridBagConstraints gbc_lbl_UPS_prodURL = new GridBagConstraints();
		gbc_lbl_UPS_prodURL.anchor = GridBagConstraints.EAST;
		gbc_lbl_UPS_prodURL.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_UPS_prodURL.gridx = 2;
		gbc_lbl_UPS_prodURL.gridy = 2;
		panel_UPS_dataEntry.add(lbl_UPS_prodURL, gbc_lbl_UPS_prodURL);

		textField_UPS_URL = new JTextField();
		GridBagConstraints gbc_textField_UPS_URL = new GridBagConstraints();
		gbc_textField_UPS_URL.insets = new Insets(0, 0, 5, 0);
		gbc_textField_UPS_URL.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_UPS_URL.gridx = 3;
		gbc_textField_UPS_URL.gridy = 2;
		panel_UPS_dataEntry.add(textField_UPS_URL, gbc_textField_UPS_URL);
		textField_UPS_URL.setColumns(10);

		JLabel lbl_UPS_serialNum = new JLabel("Serial Number");
		GridBagConstraints gbc_lbl_UPS_serialNum = new GridBagConstraints();
		gbc_lbl_UPS_serialNum.anchor = GridBagConstraints.EAST;
		gbc_lbl_UPS_serialNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_UPS_serialNum.gridx = 0;
		gbc_lbl_UPS_serialNum.gridy = 3;
		panel_UPS_dataEntry.add(lbl_UPS_serialNum, gbc_lbl_UPS_serialNum);

		textField_UPS_serialNum = new JTextField();
		GridBagConstraints gbc_textField_UPS_serialNum = new GridBagConstraints();
		gbc_textField_UPS_serialNum.insets = new Insets(0, 0, 5, 5);
		gbc_textField_UPS_serialNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_UPS_serialNum.gridx = 1;
		gbc_textField_UPS_serialNum.gridy = 3;
		panel_UPS_dataEntry.add(textField_UPS_serialNum, gbc_textField_UPS_serialNum);
		textField_UPS_serialNum.setColumns(10);

		JLabel lbl_UPS_powCapacity = new JLabel("Power Capacity");
		GridBagConstraints gbc_lbl_UPS_powCapacity = new GridBagConstraints();
		gbc_lbl_UPS_powCapacity.anchor = GridBagConstraints.EAST;
		gbc_lbl_UPS_powCapacity.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_UPS_powCapacity.gridx = 2;
		gbc_lbl_UPS_powCapacity.gridy = 3;
		panel_UPS_dataEntry.add(lbl_UPS_powCapacity, gbc_lbl_UPS_powCapacity);

		textField_UPS_powCapacity = new JTextField();
		GridBagConstraints gbc_textField_UPS_powCapacity = new GridBagConstraints();
		gbc_textField_UPS_powCapacity.insets = new Insets(0, 0, 5, 0);
		gbc_textField_UPS_powCapacity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_UPS_powCapacity.gridx = 3;
		gbc_textField_UPS_powCapacity.gridy = 3;
		panel_UPS_dataEntry.add(textField_UPS_powCapacity, gbc_textField_UPS_powCapacity);
		textField_UPS_powCapacity.setColumns(10);

		JButton btn_UPS_Add = new JButton("Add");
		btn_UPS_Add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("ADD", "UPS");
			}
		});
		JLabel lblCircuitNumber = new JLabel("Circuit Number");
		GridBagConstraints gbc_lblCircuitNumber = new GridBagConstraints();
		gbc_lblCircuitNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblCircuitNumber.gridx = 0;
		gbc_lblCircuitNumber.gridy = 4;
		panel_UPS_dataEntry.add(lblCircuitNumber, gbc_lblCircuitNumber);
		GridBagConstraints gbc_btn_UPS_Add = new GridBagConstraints();
		gbc_btn_UPS_Add.insets = new Insets(0, 0, 0, 5);
		gbc_btn_UPS_Add.gridx = 1;
		gbc_btn_UPS_Add.gridy = 5;
		panel_UPS_dataEntry.add(btn_UPS_Add, gbc_btn_UPS_Add);

		textField_UPS_circuitNum = new JTextField();
		GridBagConstraints gbc_textField_UPS_circuitNum = new GridBagConstraints();
		gbc_textField_UPS_circuitNum.insets = new Insets(0, 0, 5, 0);
		gbc_textField_UPS_circuitNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_UPS_circuitNum.gridx = 1;
		gbc_textField_UPS_circuitNum.gridy = 4;
		panel_UPS_dataEntry.add(textField_UPS_circuitNum, gbc_textField_UPS_circuitNum);
		textField_UPS_powCapacity.setColumns(10);

		JButton btn_UPS_Update = new JButton("Update");
		btn_UPS_Update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("UPDATE", "UPS");
			}
		});
		GridBagConstraints gbc_btn_UPS_Update = new GridBagConstraints();
		gbc_btn_UPS_Update.insets = new Insets(0, 0, 0, 5);
		gbc_btn_UPS_Update.gridx = 2;
		gbc_btn_UPS_Update.gridy = 5;
		panel_UPS_dataEntry.add(btn_UPS_Update, gbc_btn_UPS_Update);

		JButton btn_UPS_Delete = new JButton("Delete");
		btn_UPS_Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAction("DELETE", "UPS");
			}
		});
		GridBagConstraints gbc_btn_UPS_Delete = new GridBagConstraints();
		gbc_btn_UPS_Delete.gridx = 3;
		gbc_btn_UPS_Delete.gridy = 5;
		panel_UPS_dataEntry.add(btn_UPS_Delete, gbc_btn_UPS_Delete);
		UPSTab.setLayout(gl_UPSTab);



	}
	/** 
	 * This method will display the results of the selected report from the reports menu in a self name tab
	 * @param tableName
	 * @param reportName
	 */
	public void showReport(String reportName, String qry){
		JFrame frame = new JFrame();
		JDialog reports = new JDialog(frame, reportName);

		jTable_Reports = createReportTable(qry);
		jTable_Reports.setBackground(Color.WHITE);
		jTable_Reports.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		jTable_Reports.setSurrendersFocusOnKeystroke(true);
		jTable_Reports.setFillsViewportHeight(true);
		jTable_Reports.setColumnSelectionAllowed(false);
		jTable_Reports.setCellSelectionEnabled(false);
		jTable_Reports.setAutoscrolls(true);
		JScrollPane scrollPane_REPORT = new JScrollPane(jTable_Reports);

		reports.getContentPane().add(scrollPane_REPORT);
		reports.setSize(800, 400);
		reports.setAlwaysOnTop(true);
		reports.setVisible(true);

	}
	/**
	 * This Method creates creates the table to hold the results of the selected reports
	 * @param tableName
	 * @param qry
	 * @return
	 */
	public JTable createReportTable(String qry){
		JTable table = new JTable();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = estConn().prepareStatement(qry);
			rs = stmt.executeQuery();
			table = new JTable(buildTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			closeConn(rs, stmt);
		}
		return table;


	}

	/**
	 * This Method is called on each tab of the app, is used to populate the jTable to display Data from the DB
	 * @param tableName
	 * @return
	 */
	public JTable createTable(String tableName){
		JTable table = new JTable();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		String qry = "select * from dbo." + tableName;

		try {
			stmt = estConn().prepareStatement(qry);
			rs = stmt.executeQuery();
			table = new JTable(buildTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			closeConn(rs, stmt);
		}
		return table;

	}

	public static DefaultTableModel buildTableModel(ResultSet rs)
			throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);

	}
	/**
	 * This method establishes a connection to the databse. And will be called before any statement is executed.
	 * @return
	 */
	public static Connection estConn(){
		Connection conn = null;
		String url = "jdbc:jtds:sqlserver://helpdeskvm.corpnet.com:1433;instance=SQLEXPRESS; DatabaseName=ITInfraDB";
		String driver = "net.sourceforge.jtds.jdbc.Driver";
		String userName = "admin_serversDB";
		String password = "admin";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection to serversDB has Failed", "Connection Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
		return conn;

	}
	/**
	 * This method will close the statement, result set, and the connection after each interaction with the database
	 * @param conn
	 * @param rs
	 * @param stmt
	 */
	public static void closeConn (ResultSet rs, Statement stmt){
		try{
			stmt.close();
			rs.close();
		}catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * This Method will be called on each tab in the main window and will populate the JcomboBox that corresponds to the primary key column in the table being displayed 
	 * on that tab
	 * @param comboBox
	 * @param tableName
	 * @param columnName
	 * @param refresh
	 */
	public void populateCombobox(JComboBox<String> comboBox, String tableName, String columnName, Boolean refresh) {
		String query = "SELECT DISTINCT " + columnName +  " FROM " + tableName + " ORDER BY " + columnName + " ASC" ;
		Connection conn = estConn();
		ResultSet rs = null;
		Statement stmt = null;
		if(refresh){
			try{

				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				comboBox.removeAllItems();
				while (rs.next()){
					String str = rs.getString(columnName);
					comboBox.addItem(str);
				}
			}catch (SQLException ex){
				ex.printStackTrace();
			}finally{
				closeConn(rs, stmt);
			}
		}else{
			try{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()){
					String str = rs.getString(columnName);
					comboBox.addItem(str);
				}
			}catch (SQLException ex){
				ex.printStackTrace();
			}finally{
				closeConn(rs, stmt);
			}
		}
	}
	/**
	 * Method will refresh secondary comboboxes based on a selection made in the source comboBox
	 * @param comboBox
	 * @param source
	 * @param tableName
	 * @param columnName
	 * @param refresh
	 */
	public void populateSecondaryComboBox (JComboBox<String> comboBox, JComboBox<String> source, String tableName, String columnName, Boolean refresh){
		String query = "SELECT DISTINCT " + columnName +  " FROM " + tableName;
		String query2 = "SELECT DISTINCT " + columnName +  " FROM " + tableName + " WHERE vServer_Name = '" + source.getSelectedItem().toString() + "'";
		Connection conn = estConn();
		ResultSet rs = null;
		ResultSet rs2 = null;
		Statement stmt = null;
		if(refresh){
			try{
				comboBox.removeAllItems();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()){
					String str = rs.getString(columnName);
					comboBox.addItem(str);
				}
				rs2 = stmt.executeQuery(query2);
				while(rs2.next())
					comboBox.setSelectedItem(rs2.getObject(columnName));
			}catch (SQLException ex){
				ex.printStackTrace();
			}finally{
				closeConn(rs, stmt);
				try {
					rs2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}else{
			try{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()){
					String str = rs.getString(columnName);
					comboBox.addItem(str);
				}
				rs2 = stmt.executeQuery(query2);
				while(rs2.next())
					comboBox.setSelectedItem(rs2.getObject(columnName));
			}catch (SQLException ex){
				ex.printStackTrace();
			}finally{
				closeConn(rs, stmt);
				try {
					rs2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}
	}
	/**
	 * This method will populate all the field on a tab when the selection of the primary key from the appropriate comboBox
	 * @param tableName
	 */
	public void populateFields(String tableName){
		Connection conn = estConn();
		ResultSet rs = null;
		Statement stmt = null;
		String query = "";

		switch(tableName.toUpperCase()){
		case "PSERVERS" :
			query = "SELECT * FROM dbo.pServers WHERE pServer_Name = '" + comboBox_pServer_Name.getSelectedItem().toString() + "'" ;
			try
			{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next())
				{
					comboBox_pServer_RackNum.setSelectedItem(rs.getString("RackNum"));
					comboBox_pServer_StatusOn.setSelectedItem(rs.getString("Status_ON"));
					textField_pServer_Model.setText(rs.getString("Model"));	
					textField_pServer_SerialNumber.setText(rs.getString("Serial_Num"));	
					textField_pServer_Rat.setText(rs.getString("RAT"));	
					textField_pServer_ProductionDate.setText(rs.getString("ProductionDate"));
					textField_pServer_OperatingSystem.setText(rs.getString("OS"));
					textField_pServer_IPAdd01.setText(rs.getString("IP_Address_01"));
					textField_pServer_IPAdd02.setText(rs.getString("IP_Address_02"));
					textField_pServer_IPAdd03.setText(rs.getString("IP_Address_03"));
					textField_pServer_IPAdd04.setText(rs.getString("IP_Address_04"));
					textField_pServer_DracIpAdd.setText(rs.getString("DRAC_IP_Address"));	
					textArea_pServer_services.setText(rs.getString("Services_Applications"));
					textArea_pServer_Notes.setText(rs.getString("Notes"));
					comboBox_pServer_RackPos.setSelectedItem(rs.getString("RackPosition"));
					comboBox_pServers_Usize.setSelectedItem(rs.getString("uSize"));
				}	
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			break;
		case "VSERVERS":
			query = "SELECT * FROM dbo.vServer WHERE vServer_Name = '" + comboBox_vServers_vServer_Name.getSelectedItem().toString() + "'" ;
			try
			{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next())
				{
					comboBox_vServer_pServer_Name.setSelectedItem(rs.getString("pServer_Name"));
					comboBox_vServer_StatusON.setSelectedItem(rs.getString("Status_ON"));	
					comboBox_vServer_AssignedMem.setSelectedItem(rs.getString("assignedMemory"));
					comboBox_vServer_VHDsize.setSelectedItem(rs.getString("VHD_Size"));
					textField_vServer_IPAdd01.setText(rs.getString("IP_Address_01"));
					textField_vServer_IPAdd02.setText(rs.getString("IP_Address_02"));
					textField_vServer_IPAdd03.setText(rs.getString("IP_Address_03"));
					textField_vServer_IPAdd04.setText(rs.getString("IP_Address_04"));
					textField_vServer_backupDestination.setText(rs.getString("BackupDestination"));
					textField_vServer_moreBackUp.setText(rs.getString("moreBackups"));
					textField_vServer_OS.setText(rs.getString("OS"));
					textField_vServer_Services.setText(rs.getString("Services_Applications"));
					textField_vServer_pointOfContact.setText(rs.getString("point_of_contact"));
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
			break;
		case "NETEQ":
			query = "SELECT * FROM dbo.Network_Equipment WHERE Net_EQ_Serial_Num = '" + comboBox_NetEQ_SerialNum.getSelectedItem().toString() + "'";
			try
			{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next())
				{
					comboBox_NetEQ_hostName.setSelectedItem(rs.getString("hostname"));
					comboBox_NetEQ_RackNum.setSelectedItem(rs.getString("RackNum"));
					textField_NetEQ_RAT.setText(rs.getString("RAT"));
					textField_NetEQ_Manufacturer.setText(rs.getString("Manufacturer"));
					textField_NetEQ_Model.setText(rs.getString("Model"));
					comboBox_NetEQ_Type.setSelectedItem(rs.getString("Type"));
					comboBox_NetEQ_StatusOn.setSelectedItem(rs.getString("Status_ON"));
					textField_NetEQ_IPAdd01.setText(rs.getString("IP_Address_01"));
					textField_NetEQ_IPAdd02.setText(rs.getString("IP_Address_02"));
					textField_NetEQ_IPAdd03.setText(rs.getString("IP_Address_03"));
					textField_NetEQ_IPAdd04.setText(rs.getString("IP_Address_04"));
					textArea_NetEQ_Services.setText(rs.getString("Services_Applications"));
					textArea_NetEQ_notes.setText(rs.getString("Notes"));					
				}

			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			break;
		case "PSERVERPSU":
			query = "SELECT * FROM dbo.pServer_PSU WHERE PSU_Num = '" + comboBox_pServer_PSU_psuNum.getSelectedItem().toString() + 
			"' AND Pserver_Name = '" +	comboBox_pServer_PSU_pServerName.getSelectedItem().toString() + "'";
			try
			{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next())
				{
					comboBox_pServer_PSU_UpsName.setSelectedItem(rs.getObject("upsName"));
					comboBox_pServer_PSU_outletNum.setSelectedItem(rs.getObject("outletNum"));
					comboBox_pServer_PSU_PDUName.setSelectedItem(rs.getObject("PDU_Name"));
				}

			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			break;
		case "AWSEC2":
			query = "SELECT * FROM dbo.AWS_EC2 WHERE Instance = '" + comboBox_AWSEC2_Instance.getSelectedItem().toString() + "'";
			try
			{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next())
				{
					textField_AWSEC2_Hostname.setText(rs.getString("Hostname"));
					textField_AWSEC2_OS.setText(rs.getString("OS"));
					textField_AWSEC2_IPAdd.setText(rs.getString("IP_Address"));
					textField_AWSEC2_URL.setText(rs.getString("ec2_url"));
					textField_AWSEC2_Services.setText(rs.getString("Services_Applications"));

				}

			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			break;
		case "UPS":
			query = "SELECT * FROM dbo.UPS WHERE upsName = '" + comboBox_UPS_Name.getSelectedItem().toString() + "'";
			try
			{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next())
				{
					comboBox_UPS_rackNum.setSelectedItem(rs.getObject("rackNum"));
					textField_UPS_model.setText(rs.getString("model"));
					textField_UPS_serialNum.setText(rs.getString("serialNum"));
					textField_UPS_IPAdd.setText(rs.getString("IP_Address_01"));
					textField_UPS_maccAdd.setText(rs.getString("mac_Address"));
					textField_UPS_URL.setText(rs.getString("productURL"));
					textField_UPS_powCapacity.setText(rs.getString("powerCapacity_W"));
					textField_UPS_circuitNum.setText(rs.getString("circuitNum"));				
				}

			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			break;
		}


	}
	/**
	 * @param values the values to set
	 */
	public void collectValues(String tabName) {
		switch (tabName.toUpperCase()){
		case "PSERVERS":
			values[0] = comboBox_pServer_Name.getSelectedItem().toString();
			values[1] = comboBox_pServer_RackNum.getSelectedItem().toString();
			values[2] = comboBox_pServer_StatusOn.getSelectedItem().toString();
			values[3] = textField_pServer_Model.getText();
			values[4] = textField_pServer_SerialNumber.getText();
			values[5] = textField_pServer_Rat.getText();
			values[6] = textField_pServer_ProductionDate.getText();
			values[7] = textField_pServer_OperatingSystem.getText();
			values[8] = textField_pServer_IPAdd01.getText();
			values[9] = textField_pServer_IPAdd02.getText();
			values[10] = textField_pServer_IPAdd03.getText();
			values[11] = textField_pServer_IPAdd04.getText();
			values[12] = textField_pServer_DracIpAdd.getText();
			values[13] = textArea_pServer_services.getText();
			values[14] = textArea_pServer_Notes.getText();
			values[15] = comboBox_pServer_RackPos.getSelectedItem().toString();
			values[16] = comboBox_pServers_Usize.getSelectedItem().toString();
			break;
		case "VSERVERS":
			values[0] = comboBox_vServers_vServer_Name.getSelectedItem().toString();
			values[1] = comboBox_vServer_pServer_Name.getSelectedItem().toString();
			values[2] = comboBox_vServer_StatusON.getSelectedItem().toString();
			values[3] = comboBox_vServer_AssignedMem.getSelectedItem().toString();	
			values[4] = comboBox_vServer_VHDsize.getSelectedItem().toString();
			values[5] = textField_vServer_IPAdd01.getText();
			values[6] = textField_vServer_IPAdd02.getText();
			values[7] = textField_vServer_IPAdd03.getText();
			values[8] = textField_vServer_IPAdd04.getText();
			values[9] = textField_vServer_backupDestination.getText();
			values[10] = textField_vServer_moreBackUp.getText();
			values[11] = textField_vServer_OS.getText();
			values[12] = textField_vServer_Services.getText();
			values[13] = textField_vServer_pointOfContact.getText();			
			break;
		case "NETEQ":
			values[0] = comboBox_NetEQ_SerialNum.getSelectedItem().toString();
			values[1] = comboBox_NetEQ_hostName.getSelectedItem().toString();
			values[2] = comboBox_NetEQ_RackNum.getSelectedItem().toString();
			values[3] = textField_NetEQ_RAT.getText();
			values[4] = textField_NetEQ_Manufacturer.getText();
			values[5] = textField_NetEQ_Model.getText();
			values[6] = comboBox_NetEQ_Type.getSelectedItem().toString();
			values[7] = comboBox_NetEQ_StatusOn.getSelectedItem().toString();
			values[8] = textField_NetEQ_IPAdd01.getText();
			values[9] = textField_NetEQ_IPAdd02.getText();
			values[10] = textField_NetEQ_IPAdd03.getText();
			values[11] = textField_NetEQ_IPAdd04.getText();
			values[12] = textArea_NetEQ_Services.getText();
			values[13] = textArea_NetEQ_notes.getText();
			break;
		case "PSERVERPSU":
			values[0] = comboBox_pServer_PSU_psuNum.getSelectedItem().toString();
			values[1] = comboBox_pServer_PSU_pServerName.getSelectedItem().toString();
			values[2] = comboBox_pServer_PSU_UpsName.getSelectedItem().toString();
			values[3] = comboBox_pServer_PSU_outletNum.getSelectedItem().toString();
			values[4] = comboBox_pServer_PSU_PDUName.getSelectedItem().toString();				
			break;
		case "AWS":
			values[0] = comboBox_AWSEC2_Instance.getSelectedItem().toString();
			values[1] = textField_AWSEC2_Hostname.getText();
			values[2] = textField_AWSEC2_OS.getText();
			values[3] = textField_AWSEC2_IPAdd.getText();
			values[4] = textField_AWSEC2_URL.getText();
			values[5] = textField_AWSEC2_Services.getText();	
			break;
		case "UPS":
			values[0] = comboBox_UPS_Name.getSelectedItem().toString();
			values[1] = comboBox_UPS_rackNum.getSelectedItem().toString();
			values[2] = textField_UPS_model.getText();
			values[3] = textField_UPS_serialNum.getText();
			values[4] = textField_UPS_IPAdd.getText();
			values[5] = textField_UPS_maccAdd.getText();
			values[6] = textField_UPS_URL.getText();
			values[7] = textField_UPS_powCapacity.getText();
			values[8] = textField_UPS_circuitNum.getText();
		}
	}

	public void btnAction(String action, String tabName){

		switch (tabName.toUpperCase()){
		case "PSERVERS":
			if(comboBox_pServer_StatusOn.getSelectedItem() == null || comboBox_pServer_StatusOn.getSelectedItem().toString().equals("") ){
				JOptionPane.showMessageDialog(null, "You must Select a Status \n T if server is On F if it is off", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else if(comboBox_pServer_RackNum.getSelectedItem() == null || comboBox_pServer_RackNum.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(null, "You must Select a Rack Number", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else if(comboBox_pServer_RackPos.getSelectedItem() == null || comboBox_pServer_RackPos.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(null, "You must Select a Rack Position)", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else if(comboBox_pServers_Usize.getSelectedItem() == null || comboBox_pServers_Usize.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(null, "You must Select the Servers U Size", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else{
				collectValues(tabName.toUpperCase());
				switch( action.toUpperCase()){
				case "ADD":	
					addRecord("PSERVERS", values);
					refreshTable("dbo.pServers", jTable_pServers);
					populateCombobox(comboBox_pServer_Name, "dbo.pServers", "pServer_Name", true);
					break;
				case "UPDATE":															
					updateRecord("PSERVERS", values);
					refreshTable("dbo.pServers", jTable_pServers);
					populateCombobox(comboBox_pServer_Name, "dbo.pServers", "pServer_Name", true);
					break;
				case "DELETE":															
					deleteRecord("PSERVERS", values);
					refreshTable("dbo.pServers", jTable_pServers);
					populateCombobox(comboBox_pServer_Name, "dbo.pServers", "pServer_Name", true);
					break;
				}
			}
			break;
		case "VSERVERS":
			if(comboBox_vServer_StatusON.getSelectedItem() == null || comboBox_vServer_StatusON.getSelectedItem().toString().equals("") ){
				JOptionPane.showMessageDialog(null, "You must Select a Status \n T if server is On F if it is off", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else if(comboBox_vServer_AssignedMem.getSelectedItem() == null || comboBox_vServer_AssignedMem.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(null, "You must Enter / Select an Amount os Assigned RAM\n (EX: Enter 4 to signify 4GB)", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else if(comboBox_vServer_VHDsize.getSelectedItem() == null || comboBox_vServer_VHDsize.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(null, "You must Enter / Select a VHD Size\n (EX: Enter 60 to Signify 60GB)", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else if(comboBox_vServer_pServer_Name.getSelectedItem() == null || comboBox_vServer_pServer_Name.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(null, "You must Enter / Select a Physical Host Name", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else{
				collectValues(tabName.toUpperCase());
				switch( action.toUpperCase()){
				case "ADD":														
					addRecord("VSERVERS", values);
					refreshTable("dbo.vServer", jTable_vServers);
					populateCombobox(comboBox_vServers_vServer_Name, "dbo.vServer", "vServer_Name", true);
					populateCombobox(comboBox_vServer_VHDsize, "dbo.vServer", "VHD_Size", true);
					populateCombobox(comboBox_vServer_AssignedMem, "dbo.vServer", "assignedMemory", true);					
					break;
				case "UPDATE":														
					updateRecord("VSERVERS", values);
					refreshTable("dbo.vServer", jTable_vServers);
					//populateCombobox(comboBox_vServers_vServer_Name, "dbo.vServer", "vServer_Name", true);
					populateCombobox(comboBox_vServer_VHDsize, "dbo.vServer", "VHD_Size", true);
					populateCombobox(comboBox_vServer_AssignedMem, "dbo.vServer", "assignedMemory", true);					
					break;
				case "DELETE":															
					deleteRecord("VSERVERS", values);
					refreshTable("dbo.vServer", jTable_vServers);
					//populateCombobox(comboBox_vServers_vServer_Name, "dbo.vServer", "vServer_Name", true);
					populateCombobox(comboBox_vServer_VHDsize, "dbo.vServer", "VHD_Size", true);
					populateCombobox(comboBox_vServer_AssignedMem, "dbo.vServer", "assignedMemory", true);
					break;
				}
			}
			break;
		case "NETEQ":
			if(comboBox_NetEQ_Type.getSelectedItem() == null || comboBox_NetEQ_Type.getSelectedItem().toString().equals("") ){
				JOptionPane.showMessageDialog(null, "You must Enter / Select a Type", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else if(comboBox_NetEQ_StatusOn.getSelectedItem() == null || comboBox_NetEQ_StatusOn.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(null, "You must Select a Status \n T if server is On F if it is off", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else if(comboBox_NetEQ_RackNum.getSelectedItem() == null || comboBox_NetEQ_RackNum.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(null, "You must Select a Rack Number", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else if(comboBox_NetEQ_hostName.getSelectedItem() == null || comboBox_NetEQ_hostName.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(null, "You must Enter / Select a Hostname", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else{
				collectValues(tabName.toUpperCase());
				switch( action.toUpperCase()){
				case "ADD":	

					addRecord("NETEQ", values);
					refreshTable("dbo.Network_Equipment", jTable_netEQ);
					populateCombobox(comboBox_NetEQ_SerialNum, "dbo.Network_Equipment", "Net_EQ_Serial_Num", true);
					populateCombobox(comboBox_NetEQ_hostName, "dbo.Network_Equipment", "hostName", true);
					populateCombobox(comboBox_NetEQ_Type, "dbo.Network_Equipment", "Type", true);
					break;
				case "UPDATE":															
					updateRecord("NETEQ", values);
					refreshTable("dbo.Network_Equipment", jTable_netEQ);
					populateCombobox(comboBox_NetEQ_SerialNum, "dbo.Network_Equipment", "Net_EQ_Serial_Num", true);
					populateCombobox(comboBox_NetEQ_hostName, "dbo.Network_Equipment", "hostName", true);
					populateCombobox(comboBox_NetEQ_Type, "dbo.Network_Equipment", "Type", true);
					break;
				case "DELETE":
					deleteRecord("NETEQ", values);
					refreshTable("dbo.Network_Equipment", jTable_netEQ);
					populateCombobox(comboBox_NetEQ_SerialNum, "dbo.Network_Equipment", "Net_EQ_Serial_Num", true);
					populateCombobox(comboBox_NetEQ_hostName, "dbo.Network_Equipment", "hostName", true);
					populateCombobox(comboBox_NetEQ_Type, "dbo.Network_Equipment", "Type", true);
					break;
				}
				break;
			}
		case "PSERVERPSU":
			collectValues(tabName.toUpperCase());
			switch( action.toUpperCase()){
			case "ADD":															
				addRecord("PSERVERPSU", values);
				refreshTable("dbo.pServer_PSU", jTable_pServer_PSU);
				populateCombobox(comboBox_pServer_Name, "dbo.pServers", "pServer_Name", true);
				break;
			case "UPDATE":														
				updateRecord("PSERVERPSU", values);
				refreshTable("dbo.pServer_PSU", jTable_pServer_PSU);
				populateCombobox(comboBox_pServer_Name, "dbo.pServers", "pServer_Name", true);
				break;
			case "DELETE":
				deleteRecord("PSERVERPSU", values);
				refreshTable("dbo.pServer_PSU", jTable_pServer_PSU);
				populateCombobox(comboBox_pServer_Name, "dbo.pServers", "pServer_Name", true);
				break;
			}
			break;
		case "AWS":
			collectValues(tabName.toUpperCase());
			switch( action.toUpperCase()){
			case "ADD":														
				addRecord("AWS", values);
				refreshTable("dbo.AWS_EC2", jTable_AWS_EC2);
				populateCombobox(comboBox_AWSEC2_Instance, "dbo.AWS_EC2", "Instance", true);
				break;
			case "UPDATE":														
				updateRecord("AWS", values);
				refreshTable("dbo.AWS_EC2", jTable_AWS_EC2);
				populateCombobox(comboBox_AWSEC2_Instance, "dbo.AWS_EC2", "Instance", true);
				break;
			case "DELETE":
				deleteRecord("AWS", values);
				refreshTable("dbo.AWS_EC2", jTable_AWS_EC2);
				populateCombobox(comboBox_AWSEC2_Instance, "dbo.AWS_EC2", "Instance", true);
				break;
			}
			break;	
		case "UPS":
			if(comboBox_UPS_rackNum.getSelectedItem() == null || comboBox_UPS_rackNum.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(null, "You must Select a Rack Number", "Error", JOptionPane.ERROR_MESSAGE);
				break;								
			}else{
				collectValues(tabName.toUpperCase());
				switch( action.toUpperCase()){
				case "ADD":														
					addRecord("UPS", values);
					refreshTable("dbo.UPS", jTable_UPS);
					populateCombobox(comboBox_UPS_Name, "dbo.UPS", "upsName", true);
					break;
				case "UPDATE":														
					updateRecord("UPS", values);
					refreshTable("dbo.UPS", jTable_UPS);
					populateCombobox(comboBox_UPS_Name, "dbo.UPS", "upsName", true);				
					break;
				case "DELETE":
					deleteRecord("UPS", values);
					refreshTable("dbo.UPS", jTable_UPS);
					populateCombobox(comboBox_UPS_Name, "dbo.UPS", "upsName", true);					
					break;
				}
			}
			break;
			}
	}

	/**
	 * This method will be called by the action listeners for the add button displayed on each tab. 
	 * @param tableName
	 * @param values
	 */
	public void addRecord(String tableName, String[] values){
		Statement addSTMT = null;
		/**
		 * Switch adds record to appropriate table in DB based on the param tableName)
		 */
		switch(tableName.toUpperCase()){

		case "PSERVERS":
			try {
				addSTMT = estConn().createStatement();
				addSTMT.executeUpdate("INSERT INTO dbo.pServers " + 
						"VALUES ( '" + values[0]  + "', '" + values[1]  + "', '" + values[2]  + "', '" + values[3]  + "', '" + values[4]  + "', '" + 
						values[5]  + "', '" + values[6]  + "', '" + values[7] +  "', '" + values[8]  + "', '" + values[9]  + "', '" + values[10]  + 
						"', '" + values[11]  + "', '" + values[12]  + "', '" + values[13]  + "', '" + values[14]  + "', '" + values[15] +  "', '" + 
						values[16]  + "')" );
				JOptionPane.showMessageDialog(null, "Record added to Physical Servers table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					addSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		case "VSERVERS":
			try {
				addSTMT = estConn().createStatement();
				addSTMT.executeUpdate("INSERT INTO dbo.vServer " + 
						"VALUES ( '" + values[0]  + "', '" + values[1]  + "', '" + values[2]  + "', '" + values[3]  + "', '" + values[4]  + "', '" + values[5]  + 
						"', '" + values[6]  + "', '" +  values[7] +  "', '" + values[8]  + "', '" + values[9]  + "', '" + values[10]  + "', '" + values[11]  + 
						"', '" + values[12]  + "', '" + values[13]  + "')" );
				JOptionPane.showMessageDialog(null, "Record added to Virtual Servers table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					addSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		case "NETEQ":
			try {
				addSTMT = estConn().createStatement();
				addSTMT.executeUpdate("INSERT INTO dbo.Network_Equipment " + 
						"VALUES ( '" + values[0]  + "', '" + values[1]  + "', '" + values[2]  + "', '" + values[3]  + "', '" + values[4]  + "', '" + values[5]  + "', '" + values[6]  + "', '" +
						values[7] +  "', '" + values[8]  + "', '" + values[9]  + "', '" + values[10]  + "', '" + values[11]  + "', '" + values[12]  + "', '" + values[13]  + "')" );
				JOptionPane.showMessageDialog(null, "Record added to Network Equipment table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					addSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		case "PSERVERPSU":
			try {
				addSTMT = estConn().createStatement();
				addSTMT.executeUpdate("INSERT INTO dbo.pServer_PSU " + 
						"VALUES ("  + values[0]  + ", '" + values[1]  + "', '" + values[2]  + "', " + values[3]+ ", '" + values[4]  + "'); ");
				JOptionPane.showMessageDialog(null, "Record added to Physical Server PSU table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					estConn().close();
					addSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		case "AWS":
			try {
				addSTMT = estConn().createStatement();
				addSTMT.executeUpdate("INSERT INTO dbo.AWS_EC2 " + 
						"VALUES ( '" + values[0]  + "', '" + values[1]  + "', '" + values[2]  + "', '" + values[3]  + "', '" + values[4]  + "', '" + values[5]  + "')" );
				JOptionPane.showMessageDialog(null, "Record added to AWS EC2 table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					addSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		default:
			JOptionPane.showMessageDialog(null, "Program bug detected, table name has no match");
			break;
		case "UPS":
			try {
				addSTMT = estConn().createStatement();
				addSTMT.executeUpdate("INSERT INTO dbo.UPS " + 
						"VALUES ( '" + values[0] + "', '" + values[1]  + "', '" + values[2]  + "', '" + values[3]  + "', '" + values[4]  + "', '" + 
						values[5]  + "', '" + values[6]  + "', '" + values[7] +  "', '" + values[8]  + "')" );
				JOptionPane.showMessageDialog(null, "Record added to UPS table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					addSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}

	}
	/**
	 * This method will be called from each tab and used to delete records from the appropriate DB table
	 * @param tableName
	 * @param values
	 */
	public void deleteRecord(String tableName, String[] values){
		Statement delSTMT = null;
		/**
		 * Switch deletes record from appropriate table in DB @param tableName)
		 */
		switch(tableName.toUpperCase()){
		case ("PSERVERS"):
			try {
				delSTMT = estConn().createStatement();
				delSTMT.executeUpdate("DELETE FROM dbo.pServers " + 
						"WHERE pServer_Name = '" + values[0] + "'");
				JOptionPane.showMessageDialog(null, "Record deleted from Physical Servers table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					delSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		case ("VSERVERS"):
			try {
				delSTMT = estConn().createStatement();
				delSTMT.executeUpdate("DELETE FROM dbo.vServer " + 
						"WHERE vServer_Name = '" + values[0] + "'");
				JOptionPane.showMessageDialog(null, "Record deleted from Virtual Servers table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					delSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		case ("NETEQ"):
			try {
				delSTMT = estConn().createStatement();
				delSTMT.executeUpdate("DELETE FROM dbo.Network_equipment " + 
						"WHERE Net_EQ_Serial_Num = '" + values[0] + "'");
				JOptionPane.showMessageDialog(null, "Record deleted from Network Equipment table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					delSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		case ("PSERVERPSU"):
			try {
				delSTMT = estConn().createStatement();
				delSTMT.executeUpdate("DELETE FROM dbo.pServer_PSU " + 
						"WHERE PSU_Num = '" + values[0] + "' AND Pserver_Name = '" + values[1] + "'");
				JOptionPane.showMessageDialog(null, "Record deleted from Physical Servers PSU table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					delSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		case ("AWS"):
			try {
				delSTMT = estConn().createStatement();
				delSTMT.executeUpdate("DELETE FROM dbo.AWS_EC2 " + 
						"WHERE instance = '" + values[0] + "'");
				JOptionPane.showMessageDialog(null, "Record deleted from AWS EC2 table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					delSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		case ("UPS"):
			try {
				delSTMT = estConn().createStatement();
				delSTMT.executeUpdate("DELETE FROM dbo.UPS " + 
						"WHERE upsName = '" + values[0] + "'");
				JOptionPane.showMessageDialog(null, "Record deleted from UPS table");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					delSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		}

	}
	/**
	 * This method will be called from tab and used to update records in the appropriate DB table
	 * @param tableName
	 * @param values
	 */
	public void updateRecord(String tableName, String[] values){
		Statement upSTMT = null;
		/**
		 * Switch deletes record from appropriate table in DB @param tableName)
		 */
		switch(tableName.toUpperCase()){
		case ("PSERVERS"):
			try {
				upSTMT = estConn().createStatement();
				upSTMT.executeUpdate("UPDATE dbo.pServers " + 
						"SET RackNum = '" + values[1] + "', Status_ON = '" + values[2] + "', Model = '" + values[3] + "', Serial_Num = '" + values[4] + "', RAT = '" + 
						values[5] + "', ProductionDate = '" + values[6] + "', OS = '" + values[7] + "', IP_Address_01 = '" + values[8] + "', IP_Address_02 = '" +
						values[9] + "', IP_Address_03 = '" + values[10] + "', IP_Address_04 = '" + values[11] + "', DRAC_IP_Address = '" + values[12] + 
						"', Services_Applications = '" + values[13] + "', Notes = '" + values[14] + "', RackPosition = '" + values[15] + "', uSize = '" + values[16] +
						"' WHERE pServer_Name = '" + values[0] + "';");
				JOptionPane.showMessageDialog(null, "Record Updated!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					upSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		case ("VSERVERS"):
			try {
				upSTMT = estConn().createStatement();
				upSTMT.executeUpdate("UPDATE dbo.vServer " + 
						"SET Status_ON = '" + values[2] + "', assignedMemory = '" + values[3] + "', VHD_Size = '" + values[4] + "', IP_Address_01 = '" + values[5] +
						"', IP_Address_02 = '" + values[6] + "', IP_Address_03 = '" + values[7] + "', IP_Address_04 = '" + values[8] + "', BackupDestination = '" +
						values[9] + "', moreBackups = '" + values[10] + "', OS = '" + values[11] + "', Services_Applications = '" + values[12] + 
						"', point_of_contact = '" + values[13] + "'" +
						"WHERE vServer_Name = '" + values[0] + "' AND pServer_Name = '" + values[1] + "';");
				JOptionPane.showMessageDialog(null, "Record Updated!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					upSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		case ("NETEQ"):
			try {
				upSTMT = estConn().createStatement();
				upSTMT.executeUpdate("UPDATE dbo.Network_Equipment " + 
						"SET hostname = '" + values[1] + "', RackNum = '" + values[2] + "', RAT = '" + values[3] + "', Manufacturer = '" + values[4] +
						"', Model = '" + values[5] + "', Type = '" + values[6] + "', Status_ON = '" + values[7] + "', IP_Address_01 = '" + values[8] +
						"', IP_Address_02 = '" + values[9] + "', IP_Address_03 = '" + values[10] + "', IP_Address_04 = '" + values[11] +
						"', Services_Applications = '" + values[12] + "', Notes = '" + values[13] + "'" +
						"WHERE Net_EQ_Serial_Num = '" + values[0] + "';");
				JOptionPane.showMessageDialog(null, "Record Updated!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					upSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		case ("PSERVERPSU"):
			try {
				upSTMT = estConn().createStatement();
				upSTMT.executeUpdate("UPDATE dbo.pServer_PSU " + 
						"SET upsname = '" + values[2] + "', outletNum = '" + values[3] + "', PDU_Name = '" + values[3] + "' " +
						"WHERE PSU_Num = '" + values[0] + "' AND Pserver_Name = '" + values[1] + "';");
				JOptionPane.showMessageDialog(null, "Record Updated!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					upSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		case ("AWS"):
			try {
				upSTMT = estConn().createStatement();
				upSTMT.executeUpdate("UPDATE dbo.AWS_EC2 " + 
						"SET hostname = '" + values[1] + "', OS = '" + values[2] + "', IP_Address = '" + values[3] +
						"', ec2_url = '" + values[4]+ "', instance = '" + values[5] + "' " +
						"WHERE instance = '" + values[0] + "';");
				JOptionPane.showMessageDialog(null, "Record Updated!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					upSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		case ("UPS"):
			try {
				upSTMT = estConn().createStatement();
				upSTMT.executeUpdate("UPDATE dbo.UPS " + 
						"SET rackNum = '" + values[1] + "', model = '" + values[2] + "', serialNum = '" + values[3] + "', IP_Address_01 = '" + values[4] +
						"', mac_Address = '" + values[5] + "', productURL = '" + values[6] + "', powerCapacity_W = '" + values[7] + "', circuitNum = '" + values[8] +
						"'" + " WHERE upsName = '" + values[0] + "';");
				JOptionPane.showMessageDialog(null, "Record Updated!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					upSTMT.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		break;
		}

	}
	/**
	 * This method will be called by the action listener for each add/delete/update button in the app. Is used to
	 * refresh the data displayed in the JTable
	 * @param tableName
	 * @param table
	 */
	public void refreshTable(String tableName, JTable table){

		ResultSet rs = null;
		PreparedStatement stmt = null;

		String qry = "select * from " + tableName;

		try {
			stmt = estConn().prepareStatement(qry);
			rs = stmt.executeQuery();
			table.setModel(buildTableModel(rs)); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			closeConn(rs, stmt);
		}		
	}
}
