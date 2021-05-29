package view;

import java.awt.BorderLayout;
//import java.awt.Component;
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
import java.util.HashMap;
////import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JScrollBar;
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
//import input.ExcelReader;
//import input.WordReader;
import model.Document;
//import model.FakeTTSFacade;
//import model.TTSFacade;
import model.TextToSpeechApi;
import model.TextToSpeechFactory;

public class Test {
	final static JFileChooser fileChooser = new JFileChooser();
	private static final String DocumentToSpeech = null;
	private static Document currDoc;
	//private static CommandsFactory commFactory;
	//private static OpenDocument openDoc;
	//private static DocumentToSpeech playDoc;
	private static String encryType;
	static String absolutePath = "";
	private static JFrame frame;
	private static JPanel panel;
	private static JTextField fileName;
	private static String folderPath = "";
	static JTextArea ta;
	private static PlayAPartOfLines playPartDoc;
	//private static SoundSettings soundSet;
	static ArrayList<Integer> soundList = new ArrayList<Integer>();
	static JLabel labSounds;
	final static int PITCH = 0;
	final static int SPEECH = 1;
	final static int VOLUME = 2;
	static boolean flag[] = {false, false, false};
	private static ArrayList<String> playAll = new ArrayList<String>();
	////
	
	
	static int k=0;
	static ArrayList<ActionListener> replay_key =new ArrayList<ActionListener>();
	static ArrayList<ActionEvent> replay_value =new ArrayList<ActionEvent>();

	private static int errorDialog(String msg, JFrame saveframe) {
		JFrame erframe = new JFrame("ERROR");
		erframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		erframe.setSize(600, 300);
		
		JPanel erpanel = new JPanel();
		JLabel error_msg = new JLabel(msg);
		erpanel.add(error_msg);
		
		JButton buttonOK = new JButton("OK");
		erpanel.add(buttonOK);
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
			
		//	while (currDoc.getDocumentReader() == null ){
		//		System.out.println("Error: can't read this file\nTry again");
		//		System.out.println(currDoc.getDocumentReader());
		//		OpenActionPerformed(arg0,ta);
		//	  }
			
			 
			CommandsFactory commFactory = new CommandsFactory(currDoc);
			OpenDocument	openDoc = (OpenDocument) commFactory.createCommand("openDocument");
			openDoc.actionPerformed(arg0);
			
			
			
			////
			if(k==1) {
				
				replayRecording(arg0,openDoc);
			}
			///
		}
		else if (retVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("if epistrofh " + retVal);
		}
		return absolutePath;

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
				
				 CommandsFactory	 commFactory = new CommandsFactory(currDoc); 
				 
				 SaveDocument saveDoc = (SaveDocument) commFactory.createCommand("saveDocument"); 
				 saveDoc.actionPerformed(arg0);
				 if(k==1) {
				
					 
					 replayRecording( arg0,saveDoc);
				 }
				
		    }
	        return folderPath;
        }
        else {
        	errorDialog("Empty Document. "+"\n" + "Can't Save it."+"\n"+" Open a document and try again", saveframe);
        	return null;
        }
	}
	
	
	private static String addContentsToPanel(JTextArea ta, JFrame openFrame, JComboBox<String> cb) {
		clearTextArea(ta);
		showContents(ta);
		String selectedType = (String) cb.getSelectedItem();
		System.out.println("You seleted the encryption type: " + selectedType);
		openFrame.dispose();
		return selectedType;
	}


	
	private static void saveContents(ActionEvent arg0, JFrame saveFrame) {
		 saveFrame.dispose();	 
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



	private static void cancelMode(JFrame openFrame, JTextArea ta) {
		clearTextArea(ta);
		absolutePath = "";
		encryType = "";
		openFrame.dispose();
	}

	private static void cancelSaveMode(JFrame saveframe) {
		folderPath = "";
		encryType = "";
		saveframe.dispose();
	}
	
	
	protected static String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		if (selected.length == 0) {
			return null;
		}
		else {
			encryType = (String) selected[0];
			return encryType;
		}
	}

	
	private static void selectedFileToOpenWindow(JTextArea ta) {
		frame = new JFrame("SELECT FILE AND ENCODE TYPE");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 300);

		panel = new JPanel(new GridBagLayout());
		panel.setPreferredSize(new Dimension(600, 300));

		// Select File
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		
		JLabel labelDecode = new JLabel("Select decode:");
		panel.add(labelDecode, gbc);

		String[] decodeChoices = { " ", "Atbash", "Rot13", "None" };
		final JComboBox<String> cb = new JComboBox<String>(decodeChoices);
		panel.add(cb, gbc);
	    ItemListener itemListener = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
	        ItemSelectable is = e.getItemSelectable();
	        encryType = selectedString(is);
	      }

	    };
	    cb.addItemListener(itemListener);
		 
		gbc.gridy++;

		// Select decode
		gbc.gridy++;
		fileName = new JTextField(null);
		fileName.setMinimumSize(new Dimension(300, 25));
		fileName.setPreferredSize(new Dimension(300, 25));
		fileName.setEditable(false);
		panel.add(fileName, gbc);

		JButton buttonSelFile = new JButton("Select File");
		panel.add(buttonSelFile, gbc);
		
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
		JButton buttonOK = new JButton("OK");
		panel.add(buttonOK, gbc);
		buttonOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ok pressed");
				encryType = addContentsToPanel(ta, frame, cb);

				System.out.println(encryType);

			}
		});

		JButton buttonCancel = new JButton("Cancel");
		panel.add(buttonCancel, gbc);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelMode(frame, ta);
			}
		});

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	private static void selectedFileToSaveWindow(JTextArea ta) {
		frame = new JFrame("SAVE FILE AND ENCODE TYPE");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 300);

		panel = new JPanel(new GridBagLayout());
		panel.setPreferredSize(new Dimension(600, 300));
		
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.fill = GridBagConstraints.NONE;
		
		JLabel labelEncode = new JLabel("Select encryption:"); 
		panel.add(labelEncode, gbc);
		
		String[] decodeChoices = { " ", "Atbash", "Rot13", "None" }; 
		final JComboBox<String> cb = new JComboBox<String>(decodeChoices);
		panel.add(cb, gbc);
		ItemListener itemListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				int state = ie.getStateChange();
				ItemSelectable is = ie.getItemSelectable();
				encryType = selectedString(is);
			}
		};
		cb.addItemListener(itemListener);
		
		gbc.gridy++;
		gbc.gridy++;
		JLabel setFileName = new JLabel("Choose file name and then choose a folder to save the file: "); 
		panel.add(setFileName, gbc);
		fileName = new JTextField(null);
		fileName.setMinimumSize(new Dimension(100, 25));
		fileName.setPreferredSize(new Dimension(100, 25));
		fileName.setEditable(true);
		panel.add(fileName, gbc);
		
		gbc.gridy++;
		gbc.gridy++;
		
		JTextField folderName = new JTextField(null);
		folderName.setMinimumSize(new Dimension(400, 25));
		folderName.setPreferredSize(new Dimension(400, 25));
		folderName.setEditable(false);
		panel.add(folderName, gbc);
		
		JButton buttonSelFolder = new JButton("Browse");
		panel.add(buttonSelFolder, gbc);
		buttonSelFolder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Select file pressed");
				  try { 
					  folderName.setText(saveActionPerformed(arg0, ta, folderName.getText()+fileName.getText(), frame)); 
				  }
				  catch (Exception e) { //path = ""; // TODO Auto-generated catch block
					  e.printStackTrace(); 
				  } 
			}
		});
		
		gbc.gridy++;
		JButton buttonOK = new JButton("OK");
		panel.add(buttonOK, gbc);
		buttonOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[save] ok pressed");
				saveContents(e, frame);
				System.out.println(encryType);

			}
		});

		JButton buttonCancel = new JButton("Cancel");
		panel.add(buttonCancel, gbc);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[save] cancel pressed");
				cancelSaveMode(frame);
			}
		});

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	private static void selectedFileToEditWindow(ActionEvent arg0, JTextArea ta) {
		JTextArea edit_area = new JTextArea(32,50);
		edit_area.setEditable(true);
		edit_area.setWrapStyleWord(true);
		edit_area.setLineWrap(true);
		
		JFrame eframe = new JFrame("EDIT CONTENT");
		eframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		eframe.setSize(600, 600);
		
		JPanel up_panel = new JPanel();
        up_panel.setPreferredSize(new Dimension(600,500));
		
		edit_area.setText(ta.getText());
		
		up_panel.add(edit_area);
		
		JPanel down_panel = new JPanel(new GridBagLayout());
        down_panel.setPreferredSize(new Dimension(600, 50));
        GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
        
        JButton ok_button = new JButton("OK");
        down_panel.add(ok_button, gbc);
		ok_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("[window edit] ok pressed");
				clearTextArea(ta);
				
				ta.setText(edit_area.getText());
				///
				CommandsFactory	 commFactory = new CommandsFactory(currDoc,edit_area.getText()); 
				 
				 EditDocument editDoc = (EditDocument) commFactory.createCommand("editDocument"); 
				 editDoc.actionPerformed(arg0);
				
				if(k==1) {
					
					replayRecording(arg0,editDoc);
				}
				//
				eframe.dispose();
			}
		});
		
		gbc.gridy++;
        JButton cancel_button = new JButton("CANCEL");
        down_panel.add(cancel_button,gbc);
		cancel_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("[window edit] cancel pressed");
				eframe.dispose();
			}
		});
		
		JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, up_panel, down_panel);
		eframe.add(sl);
		eframe.setVisible(true);
	}
	
	private static void TTSActionPerformed(ActionEvent arg0, JFrame ttsframe) {
		if (currDoc != null) {
			
			CommandsFactory	commFactory = new CommandsFactory(currDoc); //TODO if -else
			DocumentToSpeech playDoc = (DocumentToSpeech) commFactory.createCommand("DocumentToSpeech");
			
			playDoc.actionPerformed(arg0);
			
			if(k==1) {
				replayRecording( arg0,playDoc);
			}
			
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
				if(k==1) {
					
				
					replayRecording(arg0,playPartDoc);
				}
			}
			else {
				errorDialog("Empty Document. "+"\n" + "Can't play it."+"\n"+" Select text and try again", null);
			}
		}
		else {
			errorDialog("Empty Document. "+"\n" + "Can't play it."+"\n"+" Open a document and try again", null);
		}
	}
	
	private static void okSoundSett(ActionEvent e, JFrame setFrame) {
		setFrame.dispose();
		
	}
	
	private static void setUpdate(ChangeEvent e, int upd, JFrame soundFrame) {//, int pitch, int speech) {
		if (currDoc != null) {
			soundList.set(2, upd);
			ArrayList<Integer> copylist =new ArrayList<Integer>();
			
			for(Integer i: soundList) {
				copylist.add(i);
			}
			
			
			
			CommandsFactory	commFactory = new CommandsFactory(currDoc, copylist);
			SoundSettings soundSet = (SoundSettings) commFactory.createCommand("soundSettings");
			soundSet.actionPerformed(null);
			
			if(k==1) {
				replayRecording(null,soundSet);
			}
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
			SoundSettings soundSet = (SoundSettings) commFactory.createCommand("soundSettings");
			soundSet.actionPerformed(null);
			
			if(k==1) {
				
				replayRecording(null,soundSet);
			}
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
			SoundSettings soundSet = (SoundSettings) commFactory.createCommand("soundSettings");
			soundSet.actionPerformed(null);
			
			if(k==1) {
				replayRecording(null,soundSet);
			}
		}
		else {
			errorDialog("Empty Document. "+"\n" + "Can't play it."+"\n"+" Open a document and try again", soundFrame);
		}
	}
	
	private static void soundSettindsActionListener(ActionEvent arg0) {
		frame = new JFrame("SOUND SETTINGS");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 300);

		panel = new JPanel(new GridBagLayout());
		panel.setPreferredSize(new Dimension(600, 300));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		
		labSounds = new JLabel("Set volume");
		panel.add(labSounds,gbc);
		
		JSlider v_setSounds = new JSlider(0,150); //TODO set default value
		v_setSounds.addChangeListener(new ChangeListener() { //TODO
			
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("change volume");
				flag[PITCH] = true;
				ArrayList<Integer> pit = new ArrayList<Integer>();
				pit.add(v_setSounds.getValue());
				setUpdate(e, pit.get(pit.size()-1), frame);
			}
		});
		panel.add(v_setSounds,gbc);
		
		gbc.gridy++;
		gbc.gridy++;
		labSounds = new JLabel("Set speech rate"); //TODO set default value
		panel.add(labSounds,gbc);
		
		JSlider s_setSounds = new JSlider(0,500);
		s_setSounds.addChangeListener(new ChangeListener() { //TODO
			
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("change speech");
				flag[PITCH] = true;
				ArrayList<Integer> pit = new ArrayList<Integer>();
				pit.add(s_setSounds.getValue());
				speechUpdate(e, pit.get(pit.size()-1), frame);
			}
		});
		panel.add(s_setSounds,gbc);

		gbc.gridy++;
		gbc.gridy++;
		labSounds = new JLabel("Set pitch");
		panel.add(labSounds,gbc);
		
		JSlider setSounds = new JSlider(0,500);
		setSounds.addChangeListener(new ChangeListener() { //TODO
			
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("change pitch");
				flag[PITCH] = true;
				ArrayList<Integer> pit = new ArrayList<Integer>();
				pit.add(setSounds.getValue());
				pitchUpdate(e, pit.get(pit.size()-1), frame);
			}
		});
		panel.add(setSounds,gbc);
		
		gbc.gridy++;
		JButton buttonOK = new JButton("OK");
		panel.add(buttonOK, gbc);
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
		k=1;
		
		
	}
	private static void deActivateRecording() {
		k=0;
		//replay1.clear();
		replay_key.clear();
		replay_value.clear();
		
	}
	
	private static void replayRecording(ActionEvent arg0, ActionListener doc) {
	
		if(doc.toString().startsWith("commands.Open")) {
		Document copy = new Document(currDoc);
		OpenDocument op =new OpenDocument((OpenDocument) doc);
		CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
		ReplayManager replayDoc = (ReplayManager) commFactory.createCommand("replay");
		
		replayDoc.addcommand(op.getOpenDocument());
		replay_key.add(replayDoc);
		replay_value.add(arg0);
		}
		else if(doc.toString().startsWith("commands.SaveD")) {
			Document copy = new Document(currDoc);
		SaveDocument op =new SaveDocument((SaveDocument) doc);
		CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
		ReplayManager replayDoc = (ReplayManager) commFactory.createCommand("replay");
		
		replayDoc.addcommand(op.getSaveDocument());
		replay_key.add(replayDoc);
		replay_value.add(arg0);
		}else if(doc.toString().startsWith("commands.EditD")) {
			Document copy = new Document(currDoc);
			EditDocument op =new EditDocument((EditDocument) doc);
			CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
			ReplayManager replayDoc = (ReplayManager) commFactory.createCommand("replay");
			
			replayDoc.addcommand(op.getEditDocument());
			replay_key.add(replayDoc);
			replay_value.add(arg0);
			
			
		}else if(doc.toString().startsWith("commands.DocumentT")) {
			Document copy = new Document(currDoc);
			DocumentToSpeech op =new DocumentToSpeech((DocumentToSpeech) doc);
			CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
			ReplayManager replayDoc = (ReplayManager) commFactory.createCommand("replay");
			
			replayDoc.addcommand(op.getDocumentToSpeechDocument());
			replay_key.add(replayDoc);
			replay_value.add(arg0);
		}else if(doc.toString().startsWith("commands.PlayAP")) {
			Document copy = new Document(currDoc);
			PlayAPartOfLines op =new PlayAPartOfLines((PlayAPartOfLines) doc);
			CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
			ReplayManager replayDoc = (ReplayManager) commFactory.createCommand("replay");
			
			replayDoc.addcommand(op.getPlayAPartOfLinesDocument());
			replay_key.add(replayDoc);
			replay_value.add(arg0);	
			
			
			
		}else if(doc.toString().startsWith("commands.SoundS")) {
			Document copy = new Document(currDoc);
			
			SoundSettings op =new SoundSettings((SoundSettings) doc);
			System.out.println(op.getSettings_sound()+"edw eisaiiii");
			CommandsFactory commFactory = new CommandsFactory(copy.getDocument(),arg0);
			ActionListener replayDoc = (ReplayManager) commFactory.createCommand("replay");
			((ReplayManager) replayDoc).addcommand(op.getSoundSettingsDocument());
			
			replay_key.add(replayDoc);
			replay_value.add(arg0);	
			
		}
		
	}
	public static void rep(ActionEvent arg0,JTextArea ta) throws InterruptedException {
		
		
		for(int i=0; i<replay_key.size(); i++) {
			
			System.out.println(((ReplayManager)replay_key.get(i)).getList_actions().get(0).toString());
			
			if(((ReplayManager)replay_key.get(i)).getList_actions().get(0).toString().startsWith("commands.OpenD") || ((ReplayManager)replay_key.get(i)).getList_actions().get(0).toString().startsWith("commands.EditD") ) {
				
				clearTextArea(ta);				
				if(((ReplayManager)replay_key.get(i)).getList_actions().get(0).toString().startsWith("commands.OpenD")) {
					
					//((OpenDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).actionPerformed(null);
					((ReplayManager)replay_key.get(i)).actionPerformed(replay_value.get(i));
					
					if (((OpenDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).getDocument().getPath().endsWith(".docx")) {
					for (String s : ( ((OpenDocument)((ReplayManager)replay_key.get(i)).getList_actions().get(0)).getDocument().getContents() )){
						System.out.println(s);
						ta.append(s + "\n");
					}
				} else {
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
				} else {
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
		ta = new JTextArea(5, 25);
		ta.setEditable(true);
		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);
		
		soundList.add(100);//pitch
		soundList.add(150);//speech
		soundList.add(100);//volume
		
		frame = new JFrame("Text to Speech APP");
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(d.width / 2 - frame.getSize().width / 2, d.height / 2 - frame.getSize().height / 2);
		frame.setSize(600, 500);
		// Creating the MenuBar and adding components
		JMenuBar menu_bar = new JMenuBar();
		JMenu file_menu = new JMenu("File");
		menu_bar.add(file_menu);
		JMenuItem file_open = new JMenuItem("Open");
		file_menu.add(file_open);
		file_open.addActionListener(new ActionListener() { 

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Open pressed");
				selectedFileToOpenWindow(ta);
			}
		});

		JMenuItem file_save = new JMenuItem("Save");
		file_menu.add(file_save);
		file_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Save pressed");
				selectedFileToSaveWindow(ta);
			}
		});
		
		JMenuItem file_edit = new JMenuItem("Edit");
		file_menu.add(file_edit);
		file_edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("edit pressed"); 
				if(currDoc!=null) {
					selectedFileToEditWindow(arg0, ta);
				}
				else{
					System.out.println("ERROR DOCUMENT");
					//errorDialog("Empty Document. "+"\n" + "Can't play it."+"\n"+" Open a document and try again");
				}
				
			}
		});
		
		JMenu tts_menu = new JMenu("|Text To Speech|");
		menu_bar.add(tts_menu);
		JMenuItem tts_all = new JMenuItem("All Contents");
		tts_menu.add(tts_all);
		tts_all.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("all contents pressed");
				try {
					TTSActionPerformed(arg0, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JMenuItem tts_part = new JMenuItem("Part of Contents");
		tts_menu.add(tts_part);
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
		
		JMenuItem sound_set_menu = new JMenuItem("|Sound Settings|");
		menu_bar.add(sound_set_menu);
		sound_set_menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				soundSettindsActionListener(arg0);
			}
		});
		
		JMenu rec_set = new JMenu("|Record Settings|");
		menu_bar.add(rec_set);
		
		JMenuItem act_record = new JMenuItem("Activate Recording");
		rec_set.add(act_record);
		act_record.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Acrivate Recording pressed\n");
				activateRecording(arg0,null);
			}
		});
		
		JMenuItem deact_record = new JMenuItem("De-activate Recording");
		rec_set.add(deact_record);
		deact_record.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Deactivate Recording pressed\n");
				deActivateRecording();
			}
		});
		
		JMenuItem replay_record = new JMenuItem("Replay Recording");
		rec_set.add(replay_record);
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
		
		JScrollPane main_panel = new JScrollPane();

		main_panel.add(ta);
		frame.getContentPane().add(main_panel);
		frame.getContentPane().add(BorderLayout.NORTH, menu_bar); //NORTH
		frame.getContentPane().add(BorderLayout.CENTER, ta);
		
		frame.setVisible(true);
	}
}