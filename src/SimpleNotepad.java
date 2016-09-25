
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

public class SimpleNotepad extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextArea textArea;
	private MenuBar bar;
	private Menu file, help;
	private MenuItem newFile, openFile, saveFile, exit, about;
	private Scanner scan;
	
	public SimpleNotepad (String s)
	{
		super(s);
		textArea = new TextArea("", 0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		bar = new MenuBar();
		setMenuBar(bar);
		file = new Menu("File");
		bar.add(file);
		file.addActionListener(this);
		
		newFile = new MenuItem("New...");
		file.add(newFile);
		newFile.addActionListener(this);
		newFile.setShortcut(new MenuShortcut(KeyEvent.VK_N, false));
		
		openFile = new MenuItem("Open...");
		file.add(openFile);
		openFile.addActionListener(this);
		openFile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false));
		
		saveFile = new MenuItem("Save...");
		file.add(saveFile);
		saveFile.addActionListener(this);
		saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
		//file.addActionListener(this);
		
		exit = new MenuItem("Exit");
		file.add(exit);
		exit.addActionListener(this);
		exit.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
		
		help = new Menu("Help");
		bar.add(help);
		help.addActionListener(this);
		about = new MenuItem("About");
		help.add(about);
		about.addActionListener(this);
		
		setSize(400,300);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE); // set the default close operation (exit when it gets closed)
		this.textArea.setFont(new Font("Times New Roman", Font.BOLD, 12)); // set a default font for the TextArea
		this.getContentPane().setLayout(new BorderLayout()); // the BorderLayout bit makes it fill it automatically
		this.getContentPane().add(textArea);
		setResizable(false);
	}
	
	public void actionPerformed (ActionEvent e) {
		// about option
		if (e.getSource() == this.about)
		{
			new AboutNotepad().setVisible(true);
		}
		
		else if (e.getSource() == this.newFile){
			this.textArea.setText(" "); //clear the TextArea
		}
		// exit option
		else if (e.getSource() == this.exit)
			this.dispose(); // dispose all resources and close the application
		
		// open option
		else if (e.getSource() == this.openFile) {
			JFileChooser open = new JFileChooser(); // open up a file chooser
			int option = open.showOpenDialog(this); // get the option that the user selected (approve/cancel)
			if (option == JFileChooser.APPROVE_OPTION) {
				this.textArea.setText(""); // clear the TextArea
				try {
					scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					while (scan.hasNext())
						this.textArea.append(scan.nextLine() + "\n"); // append the line to the TextArea
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
 
		// save option
		else if (e.getSource() == this.saveFile) {
			JFileChooser save = new JFileChooser(); // open a file chooser
			int option = save.showSaveDialog(this); // similar to the open file, only this time we call
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					// create a buffered writer to write to a file
					BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					out.write(this.textArea.getText()); // write the contents of the TextArea to the file
					out.close(); // close the file stream
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
	}

	
	public static void main(String[] args) {
		SimpleNotepad app = new SimpleNotepad("Simple Notepad");
        app.setVisible(true);

	}
}


class AboutNotepad extends Frame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button bclose;
	private Label ltext1, ltext2, ltext3, ltext4;
	
	public AboutNotepad() 
	{
		super("About");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		bclose = new Button("Close");
		bclose.setFont(new Font("Arial",Font.PLAIN,18));
		bclose.setForeground(Color.lightGray);
		bclose.addActionListener(this);
		ltext1 = new Label("Notepad version 1.0");
		ltext1.setFont(new Font("Arial",Font.PLAIN,12));
		ltext2 = new Label("Author: HoangPham");
		ltext2.setFont(new Font("Arial", Font.PLAIN,12));
		ltext3 = new Label("Email: hoang@gmail.com");
		ltext3.setFont(new Font("Arial", Font.PLAIN,12));
		ltext4 = new Label("Tel: 01215718175");
		ltext4.setFont(new Font("Arial", Font.PLAIN,12));
		add(ltext1);
		add(ltext2);
		add(ltext3);
		add(ltext4);
		add(bclose);
		setSize(300,150);
		setResizable(false);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == bclose)
		{
			this.setVisible(false);
		}
	}
}