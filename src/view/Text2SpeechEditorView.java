package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ItemSelectable;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;

import commands.CommandsFactory;
import commands.DocumentToSpeech;
import commands.EditDocument;
import commands.OpenDocument;
import commands.PlayAPartOfLines;
import commands.ReplayManager;
import commands.SaveDocument;
import commands.SoundSettings;
import model.Document;

public class Text2SpeechEditorView {
	final static JFileChooser fileChooser = new JFileChooser();
	private static Document currDoc;
	private static PlayAPartOfLines playPartDoc;
	private static String encryType;
	static String absolutePath = "";
	private static String folderPath = "";
	final static int PITCH = 0;
	final static int SPEECH = 1;
	final static int VOLUME = 2;
	static boolean flag[] = {false, false, false};
	static ArrayList<Integer> soundList = new ArrayList<Integer>();
	static int k=0;
	static ArrayList<ActionListener> replay_key =new ArrayList<ActionListener>();
	static ArrayList<ActionEvent> replay_value =new ArrayList<ActionEvent>();
	
	private static JFrame myNewFrame(String frame, int x, int y, int inMain, int onClose ) {
		JFrame newFrame = new JFrame(frame);
		if (inMain == 1) {
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			newFrame.setLocation(d.width / 2 - newFrame.getSize().width / 2, d.height / 2 - newFrame.getSize().height / 2);
		}
		
		newFrame.setDefaultCloseOperation(onClose);
		newFrame.setSize(x,y);
		return newFrame;
	}
	
	private static JLabel myNewLabel(String label, JPanel panel, GridBagConstraints gbc) {
		JLabel newLabel = new JLabel(label);
		panel.add(newLabel, gbc);
		return newLabel;
	}
	
	private static JPanel myNewPanel(int flag, JFrame frame, int x, int y) {
		JPanel newPanel = new JPanel(new GridBagLayout());
		newPanel.setPreferredSize(new Dimension(x, y));
		return newPanel;
	}
	
	private static JButton myNewButton(String button, JPanel panel, GridBagConstraints gbc) {
		JButton newButton = new JButton(button);
		panel.add(newButton, gbc);
		return newButton;
		
	}
	
	private static JMenuBar myNewMenuBar() {
		JMenuBar newMenuBar = new JMenuBar();
		return newMenuBar;
	}
	
	private static JMenu myNewMenu(String menu, JMenuBar menuBar) {
		JMenu newMenu = new JMenu(menu);
		menuBar.add(newMenu);
		return newMenu;
	}
	
	private static JTextField myNewTextField(String textField, boolean editable, int x, int y, JPanel panel) {
		JTextField field = new JTextField(null);
		field.setMinimumSize(new Dimension(x, y));
		field.setPreferredSize(new Dimension(x, y));
		field.setEditable(editable);
		return field;
	}
	
	private static JMenuItem myNewMenuItem(String item, JMenu menu) {
		JMenuItem newItem = new JMenuItem(item);
		menu.add(newItem);
		return newItem;
	}
	
	private static JTextArea myNewTextArea(int x, int y, boolean editable, boolean wrapStyle, boolean lineWrap) {
		JTextArea newTa = new JTextArea(x, y);
		newTa.setEditable(editable);
		newTa.setWrapStyleWord(wrapStyle);
		newTa.setLineWrap(lineWrap);
		return newTa;
	}
	
	private static int errorDialog(String msg, JFrame saveframe) {
		JFrame erframe = myNewFrame("ERROR", 600, 300, 0, JFrame.DISPOSE_ON_CLOSE);
		
		JPanel erpanel = myNewPanel(0, erframe, 600, 300);
		JLabel error_msg = myNewLabel(msg, erpanel, null);
		
		JButton buttonOK = myNewButton("OK", erpanel, null);
		buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (saveframe != null) {
					saveframe.dispose();
				}
				erframe.dispose();
			}
		});
		
		erframe.getContentPane().add(erpanel);
		erframe.setVisible(true);
		return 1;
	}
	
	private static void clearTextArea(JTextArea ta) {
		ta.selectAll();
		ta.replaceSelection(""); 
	}

	private static String OpenActionPerformed(ActionEvent arg0, JTextArea ta) throws IOException {
		int retVal = fileChooser.showOpenDialog(null);
		
		if (retVal == JFileChooser.APPROVE_OPTION) {
			File thisFile = fileChooser.getSelectedFile();
			absolutePath = thisFile.getAbsolutePath();
			currDoc = new Document(null , thisFile.getAbsolutePath(), encryType);
			
			CommandsFactory commFactory = new CommandsFactory(currDoc);
			OpenDocument	openDoc = (OpenDocument) commFactory.createCommand("openDocument");
			openDoc.actionPerformed(arg0);
			if (k == 1) {///
				replayRecording(arg0,openDoc);
			}///
		}
		else if (retVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("if epistrofh " + retVal);
		}
		return absolutePath;
	}

	private static String addContentsToPanel(JTextArea ta, JFrame openFrame, JComboBox<String> cb) {
		clearTextArea(ta);
		showContents(ta);
		String selectedType = (String) cb.getSelectedItem();
		System.out.println("You seleted the encryption type: " + selectedType);
		openFrame.dispose();
		return selectedType;
	}

	private static void showContents(JTextArea ta) {
		if (currDoc.getPath().endsWith(".docx")) {
			for (String s : currDoc.getContents()) {
				System.out.println(s);
				ta.append(s + "\n");
			}
		} else {
			for (String s : currDoc.getContents()) {
				System.out.println(s);
				ta.append(s);
			}
		}
	}

	private static String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		if (selected.length == 0) {
			return null;
		}
		else {
			encryType = (String) selected[0];
			return encryType;
		}
	}
	
	private static String checkPath(String str) {
		String s = null;
		if (str.indexOf('/') != -1) {
			s = "/";
		}
		else {
			s = "\\";
		}
		return s;
	}
	
	public static String saveActionPerformed(ActionEvent arg0, JTextArea ta, String fileName, JFrame saveframe) throws InvalidFormatException, IOException {
		// Invoke the showsSaveDialog function to show the save dialog
		String s = "";
		JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        int r = f.showSaveDialog(null);
        
        if (currDoc != null) {
	        if (r == JFileChooser.APPROVE_OPTION) {   
	        	folderPath = f.getSelectedFile().getAbsolutePath();
	        	s = checkPath(folderPath);
	        	folderPath = folderPath + s +fileName;
	        	if (folderPath.endsWith(".docx") == true) {
	        		currDoc.setFiletype_write("WordWriter");
	        	}
	        	else if (folderPath.endsWith(".xlsx") == true) {
	        		currDoc.setFiletype_write("ExcelWriter");
	        	}
				 currDoc.setPath_write(folderPath);
				 currDoc.setTypecode_write(encryType);
				 CommandsFactory  commFactory = new CommandsFactory(currDoc); 
				 SaveDocument     saveDoc = (SaveDocument) commFactory.createCommand("saveDocument"); 
				 saveDoc.actionPerformed(arg0);
				 if (k == 1) { ///
					 replayRecording( arg0,saveDoc);
				 } ///
		    }
	        return folderPath;
        }
        else {
        	errorDialog("Empty Document. "+"\n" + "Can't Save it."+"\n"+" Open a document and try again", saveframe);
        	return null;
        }
	}
	
	private static void saveContents(ActionEvent arg0, JFrame saveFrame) {
		 saveFrame.dispose();	 
	}
	
	private static void cancelMode(JFrame frame, String path, String encryption) {
		path = "";
		encryption = "";
		frame.dispose();
	}
	
	private static void setUpdate(ChangeEvent e, int upd, JFrame soundFrame) {//, int pitch, int speech) {
		if (currDoc != null) {
			soundList.set(2, upd);
			ArrayList<Integer> copylist =new ArrayList<Integer>();
			for(Integer i: soundList) {
				copylist.add(i);
			}
			CommandsFactory	commFactory = new CommandsFactory(currDoc, copylist);
			SoundSettings   soundSet = (SoundSettings) commFactory.createCommand("soundSettings");
			soundSet.actionPerformed(null);
			if (k == 1) { ///
				replayRecording(null, soundSet);
			} ///
		}
		else {
			errorDialog("Empty Document. "+"\n" + "Can't play it."+"\n"+" Open a document and try again", soundFrame);
		}
	}
	
	private static void speechUpdate(ChangeEvent e, int spe, JFrame soundFrame) {
		if (currDoc != null) {
			soundList.set(1, spe);
			ArrayList<Integer> copylist =new ArrayList<Integer>();
			for(Integer i: soundList) {
				copylist.add(i);
			}
			CommandsFactory	commFactory = new CommandsFactory(currDoc, copylist);
			SoundSettings   soundSet = (SoundSettings) commFactory.createCommand("soundSettings");
			soundSet.actionPerformed(null);
			if (k == 1) { ///
				replayRecording(null, soundSet);
			}///
		}
		else {
			errorDialog("Empty Document. "+"\n" + "Can't play it."+"\n"+" Open a document and try again", soundFrame);
		}
	}
	
	private static void pitchUpdate(ChangeEvent e, int pit, JFrame soundFrame) {
		if (currDoc != null) {
			soundList.set(0, pit);
			ArrayList<Integer> copylist =new ArrayList<Integer>();
			for(Integer i: soundList) {
				copylist.add(i);
			}
			CommandsFactory	commFactory = new CommandsFactory(currDoc, copylist);
			SoundSettings   soundSet = (SoundSettings) commFactory.createCommand("soundSettings");
			soundSet.actionPerformed(null);
			if (k==1) {	///
				replayRecording(null, soundSet);
			} ///
		}
		else {
			errorDialog("Empty Document. "+"\n" + "Can't play it."+"\n"+" Open a document and try again", soundFrame);
		}
	}
	
	private static void okSoundSett(ActionEvent e, JFrame setFrame) {
		setFrame.dispose();	
	}
	
	private static void selectedFileToOpenWindow(JTextArea ta) {
		JFrame openFrame = myNewFrame("SELECT FILE AND ENCODE TYPE", 600, 300, 0, JFrame.DISPOSE_ON_CLOSE);

		JPanel openPanel = myNewPanel(0, openFrame, 600, 300);

		// Select File
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		
		JLabel labelDecode = myNewLabel("Select decode:", openPanel, gbc);

		String[] decodeChoices = { " ", "Atbash", "Rot13", "None" };
		final JComboBox<String> cb = new JComboBox<String>(decodeChoices);
		openPanel.add(cb, gbc);
		ItemListener itemListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				ItemSelectable is = e.getItemSelectable();
				encryType = selectedString(is);
			}
		};
		cb.addItemListener(itemListener);
		 
		gbc.gridy++;
		gbc.gridy++;
		
		JTextField fileName = myNewTextField(null, false, 300, 25, openPanel);
		openPanel.add(fileName, gbc);

		JButton buttonSelFile = myNewButton("Select File", openPanel, gbc);
		buttonSelFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0){
				System.out.println("Select file pressed");
				try { 
					fileName.setText(fileName.getText() + OpenActionPerformed(arg0, ta)); 
				}
				catch (Exception e) { 
					e.printStackTrace(); 
				}
			}
		});

		gbc.gridy++;
		JButton buttonOK = myNewButton("OK", openPanel, gbc);
		buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ok pressed");
				encryType = addContentsToPanel(ta, openFrame, cb);
				System.out.println(encryType);
			}
		});

		JButton buttonCancel = myNewButton("Cancel", openPanel, gbc);
		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelMode(openFrame, absolutePath, encryType);
			}
		});

		openFrame.getContentPane().add(openPanel);
		openFrame.setVisible(true);
	}

	private static void selectedFileToSaveWindow(JTextArea ta) {
		JFrame frame = myNewFrame("SAVE FILE AND ENCODE TYPE", 600, 300, 0, JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = myNewPanel(0, frame, 600, 300);
		
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.fill = GridBagConstraints.NONE;
		
		JLabel labelEncode = myNewLabel("Select encryption:", panel, gbc); 
		
		String[] decodeChoices = { " ", "Atbash", "Rot13", "None"}; 
		final JComboBox<String> cb = new JComboBox<String>(decodeChoices);
		panel.add(cb, gbc);
		ItemListener itemListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent ie) {
				ItemSelectable is = ie.getItemSelectable();
				encryType = selectedString(is);
			}
		};
		cb.addItemListener(itemListener);
		
		gbc.gridy++;
		gbc.gridy++;
		JLabel setFileName = myNewLabel("Choose file name and then choose a folder to save the file: ", panel, gbc); 

		JTextField fileName = myNewTextField(null, true, 100, 25, panel);
		panel.add(fileName, gbc);
		
		gbc.gridy++;
		gbc.gridy++;
		
		JTextField folderName = myNewTextField(null, false, 400, 25, panel);
		panel.add(folderName, gbc);
		
		JButton buttonSelFolder = myNewButton("Browse", panel, gbc);
		buttonSelFolder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Select file pressed");
				try { 
					folderName.setText(saveActionPerformed(arg0, ta, folderName.getText()+fileName.getText(), frame)); 
				}
				catch (Exception e) { 
					e.printStackTrace(); 
				}
			}
		});
		
		gbc.gridy++;
		JButton buttonOK = myNewButton("OK", panel, gbc);
		buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[save] ok pressed");
				saveContents(e, frame);
				System.out.println(encryType);

			}
		});

		JButton buttonCancel = myNewButton("Cancel", panel, gbc);
		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[save] cancel pressed");
				cancelMode(frame, folderPath, encryType);
			}
		});

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	private static void selectedFileToEditWindow(ActionEvent arg0, JTextArea ta) {
		JTextArea edit_area = myNewTextArea(32,50, true, true, true);
		
		JFrame eframe = myNewFrame("EDIT CONTENT", 600, 600, 0, JFrame.DISPOSE_ON_CLOSE);
		
		JPanel up_panel = myNewPanel(0, eframe, 600, 500);
		up_panel.setPreferredSize(new Dimension(600,500));
		
		edit_area.setText(ta.getText());
		
		JScrollPane sp = new JScrollPane(edit_area);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setPreferredSize(new Dimension(2000, 600));
		
		JPanel down_panel = myNewPanel(0, eframe, 600, 50);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;

		JButton ok_button = myNewButton("OK", down_panel, gbc);
		ok_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("[window edit] ok pressed");
				clearTextArea(ta);
				ta.setText(edit_area.getText());
				CommandsFactory	commFactory = new CommandsFactory(currDoc,edit_area.getText()); 
				EditDocument    editDoc = (EditDocument) commFactory.createCommand("editDocument"); 
				editDoc.actionPerformed(arg0);
				if (k == 1) { ///
					replayRecording(arg0, editDoc);
				} ///
				eframe.dispose();
			}
		});
		
		gbc.gridy++;
		JButton cancel_button = myNewButton("CANCEL", down_panel, gbc);
		cancel_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("[window edit] cancel pressed");
				eframe.dispose();
			}
		});
		
		sp.setPreferredSize(new Dimension(600, 430));
		up_panel.add(sp);
		JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, sp, down_panel);
		sl.setOneTouchExpandable(true);
        sl.setDividerLocation(500);
		
		eframe.add(sl);
		eframe.setVisible(true);
	}
	
	private static void TTSActionPerformed(ActionEvent arg0, JFrame ttsframe, JTextArea ta) {
		if (currDoc != null) {
			CommandsFactory	 commFactory = new CommandsFactory(currDoc); //TODO if -else
			DocumentToSpeech playDoc = (DocumentToSpeech) commFactory.createCommand("DocumentToSpeech");
			playDoc.actionPerformed(arg0);
			if (k == 1) { ///
				replayRecording(arg0, playDoc);
			} ///
		} 
		else {
			errorDialog("Empty Document. "+"\n" + "Can't play it."+"\n"+" Open a document and try again", ttsframe);
		}
	}
	
	private static void TTSPartActionPerformed(ActionEvent arg0, JTextArea ta) {
		if (currDoc != null) {
			String part_s = (ta).getSelectedText(); 
			if (part_s != null) {
				CommandsFactory	commFactory = new CommandsFactory(currDoc, part_s);
				playPartDoc = (PlayAPartOfLines) commFactory.createCommand("playAPartOfLines");
				playPartDoc.actionPerformed(arg0);
				if (k == 1) { ///
					replayRecording(arg0, playPartDoc);
				} ///
			}
			else {
				errorDialog("Empty Document. "+"\n" + "Can't play it."+"\n"+" Select text and try again", null);
			}
		}
		else {
			errorDialog("Empty Document. "+"\n" + "Can't play it."+"\n"+" Open a document and try again", null);
		}
	}
	
	private static void soundSettindsActionListener(ActionEvent arg0) {
		JFrame frame = myNewFrame("SOUND SETTINGS", 600, 300, 0, JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = myNewPanel(0, frame, 600, 300);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		
		JLabel labSounds = myNewLabel("Set volume", panel, gbc);
		
		JSlider v_setSounds = new JSlider(0,150); 
		v_setSounds.addChangeListener(new ChangeListener() { 
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("change volume");
				ArrayList<Integer> pit = new ArrayList<Integer>();
				pit.add(v_setSounds.getValue());
				setUpdate(e, pit.get(pit.size()-1), frame);
			}
		});
		panel.add(v_setSounds,gbc);
		
		gbc.gridy++;
		gbc.gridy++;
		labSounds = myNewLabel("Set speech rate", panel, gbc); 
		
		JSlider s_setSounds = new JSlider(0,500);
		s_setSounds.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("change speech");
				ArrayList<Integer> pit = new ArrayList<Integer>();
				pit.add(s_setSounds.getValue());
				speechUpdate(e, pit.get(pit.size()-1), frame);
			}
		});
		panel.add(s_setSounds,gbc);

		gbc.gridy++;
		gbc.gridy++;
		labSounds = myNewLabel("Set pitch", panel, gbc);
		
		JSlider setSounds = new JSlider(0,500);
		setSounds.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("change pitch");
				ArrayList<Integer> pit = new ArrayList<Integer>();
				pit.add(setSounds.getValue());
				pitchUpdate(e, pit.get(pit.size()-1), frame);
			}
		});
		panel.add(setSounds,gbc);
		
		gbc.gridy++;
		JButton buttonOK = myNewButton("OK", panel, gbc);
		buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ok pressed");
				okSoundSett(e, frame);
			}
		});
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	private static void activateRecording(ActionEvent arg0, ActionListener doc) {
		k = 1;
	}
	
	private static void deActivateRecording() {
		k=0;
		//replay1.clear();
		replay_key.clear();
		replay_value.clear();
	}
	
	private static void replayRecording(ActionEvent arg0, ActionListener doc) {
		if (doc.toString().startsWith("commands.Open")) {
			Document copy = new Document(currDoc);
			OpenDocument op =new OpenDocument((OpenDocument) doc);
			CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
			ReplayManager replayDoc = (ReplayManager) commFactory.createCommand("replay");
			replayDoc.addcommand(op.getOpenDocument());
			replay_key.add(replayDoc);
			replay_value.add(arg0);
		} 
		else if (doc.toString().startsWith("commands.SaveD")) {
			Document copy = new Document(currDoc);
			SaveDocument op =new SaveDocument((SaveDocument) doc);
			CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
			ReplayManager replayDoc = (ReplayManager) commFactory.createCommand("replay");
			replayDoc.addcommand(op.getSaveDocument());
			replay_key.add(replayDoc);
			replay_value.add(arg0);
		}
		else if (doc.toString().startsWith("commands.EditD")) {
				Document copy = new Document(currDoc);
				EditDocument op =new EditDocument((EditDocument) doc);
				CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
				ReplayManager replayDoc = (ReplayManager) commFactory.createCommand("replay");
				replayDoc.addcommand(op.getEditDocument());
				replay_key.add(replayDoc);
				replay_value.add(arg0);
		}
		else if (doc.toString().startsWith("commands.DocumentT")) {
				Document copy = new Document(currDoc);
				DocumentToSpeech op =new DocumentToSpeech((DocumentToSpeech) doc);
				CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
				ReplayManager replayDoc = (ReplayManager) commFactory.createCommand("replay");
				replayDoc.addcommand(op.getDocumentToSpeechDocument());
				replay_key.add(replayDoc);
				replay_value.add(arg0);
		}
		else if (doc.toString().startsWith("commands.PlayAP")) {
				Document copy = new Document(currDoc);
				PlayAPartOfLines op =new PlayAPartOfLines((PlayAPartOfLines) doc);
				CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
				ReplayManager replayDoc = (ReplayManager) commFactory.createCommand("replay");
				replayDoc.addcommand(op.getPlayAPartOfLinesDocument());
				replay_key.add(replayDoc);
				replay_value.add(arg0);	
		}
		else if (doc.toString().startsWith("commands.SoundS")) {
				Document copy = new Document(currDoc);
				SoundSettings op =new SoundSettings((SoundSettings) doc);
				CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
				ActionListener replayDoc = (ReplayManager) commFactory.createCommand("replay");
				((ReplayManager) replayDoc).addcommand(op.getSoundSettingsDocument());
				replay_key.add(replayDoc);
				replay_value.add(arg0);	
			}
	}
	
	public static void rep(ActionEvent arg0,JTextArea ta) throws InterruptedException {
		for (int i=0; i<replay_key.size(); i++) {
			//System.out.println(((ReplayManager)replay_key.get(i)).getList_actions().get(0).toString());
			if (((ReplayManager)replay_key.get(i)).getList_actions().get(0).toString().startsWith("commands.OpenD") || ((ReplayManager)replay_key.get(i)).getList_actions().get(0).toString().startsWith("commands.EditD") ) {
				clearTextArea(ta);				
				if (((ReplayManager)replay_key.get(i)).getList_actions().get(0).toString().startsWith("commands.OpenD")) {
					//((OpenDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).actionPerformed(null);
					((ReplayManager)replay_key.get(i)).actionPerformed(replay_value.get(i));
					if (((OpenDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).getDocument().getPath().endsWith(".docx")) {
						for (String s : ( ((OpenDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).getDocument().getContents() )){
							System.out.println(s);
							ta.append(s + "\n");
						}
					} 
					else {
						for (String s :(((OpenDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).getDocument().getContents() )) {
							System.out.println(s);
							ta.append(s);
						}
					}	
				}
				else {
					//((EditDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).actionPerformed(null);
					((ReplayManager)replay_key.get(i)).actionPerformed(replay_value.get(i));
						if (((EditDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).getDocument().getPath().endsWith(".docx")) {
							for (String s : ( ((EditDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).getDocument().getContents() )){
								System.out.println(s);
								ta.append(s + "\n");
							}
						} 
						else {
							for (String s :(((EditDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).getDocument().getContents() )) {
								System.out.println(s);
								ta.append(s);
							}
						}
				}
			}
			else {
				((ReplayManager)replay_key.get(i)).actionPerformed(replay_value.get(i));
			}
		}
	}
	
	public static void main(String[] args) {
		JTextArea ta = myNewTextArea(5, 25, false, true, true);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(2000, 2000));

		soundList.add(100);//pitch
		soundList.add(150);//speech
		soundList.add(100);//volume
		
		JFrame frame = myNewFrame("Text to Speech APP", 600, 500, 1, JFrame.EXIT_ON_CLOSE);
		// Creating the MenuBar and adding components
		JMenuBar menu_bar = myNewMenuBar();
		
		JMenu file_menu = myNewMenu("File", menu_bar);

		JMenuItem file_open = myNewMenuItem("Open", file_menu);
		file_open.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Open pressed");
				selectedFileToOpenWindow(ta);
			}
		});
		
		JMenuItem file_save = myNewMenuItem("Save", file_menu);
		file_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Save pressed");
				selectedFileToSaveWindow(ta);
			}
		});
		
		JMenuItem file_edit = myNewMenuItem("Edit", file_menu);
		file_edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("edit pressed"); 
				ta.setEditable(true);
				if (currDoc != null) {
					selectedFileToEditWindow(arg0, ta);
				}
				else {
					System.out.println("ERROR: Empty Document\n");
					errorDialog("Empty Document. Can't edit", null);
				}
				ta.setEditable(false);
			}
		});

		JMenuItem close_file = myNewMenuItem("Close", file_menu);
		close_file.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearTextArea(ta);
			}
		});
		
		JMenu tts_menu = myNewMenu("|Text To Speech|", menu_bar);

		JMenuItem tts_all = myNewMenuItem("All Contents", tts_menu);
		tts_all.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("all contents pressed");
				try {
					TTSActionPerformed(arg0, null, ta);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JMenuItem tts_part = myNewMenuItem("Part of Contents", tts_menu);
		tts_part.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("part of contents pressed");
				try {
					TTSPartActionPerformed(arg0, ta);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JMenu sound_menu = myNewMenu("|Sound Settings|", menu_bar);
		JMenuItem sound_set_menu = myNewMenuItem("Sound Settings", sound_menu);
		sound_set_menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Setting Sounds select\n");
				soundSettindsActionListener(arg0);
			}
		});
		
		JMenu rec_set = myNewMenu("|Record Settings|", menu_bar);
		
		JMenuItem act_record = myNewMenuItem("Activate Recording", rec_set);
		act_record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Acrivate Recording pressed\n");
				activateRecording(arg0, null);
			}
		});
		
		JMenuItem deact_record = myNewMenuItem("De-activate Recording", rec_set);
		deact_record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Deactivate Recording pressed\n");
				deActivateRecording();
			}
		});
		
		JMenuItem replay_record = myNewMenuItem("Replay Recording", rec_set);
		replay_record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Replay Recording pressed\n");
				try {
					rep(arg0,ta);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JScrollPane main_panel = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		main_panel.setPreferredSize(new Dimension(600, 430));
		panel.add(main_panel);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(BorderLayout.NORTH, menu_bar);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
}