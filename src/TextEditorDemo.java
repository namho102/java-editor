import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class TextEditorDemo extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private RSyntaxTextArea textArea;

	private static void printLines(String name, InputStream ins)
			throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			System.out.println(name + " " + line);
		}
	}

	private static void runProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);
		printLines(command + " stdout:", pro.getInputStream());
		printLines(command + " stderr:", pro.getErrorStream());
		pro.waitFor();
		System.out.println(command + " exitValue() " + pro.exitValue());
	}

	public TextEditorDemo() {

		JFrame f = new JFrame("Text Area Examples");

		JPanel cp = new JPanel(new BorderLayout());
		JPanel cp2 = new JPanel(new BorderLayout());

		f.getContentPane().add(cp, BorderLayout.PAGE_START);
		f.getContentPane().add(cp2, BorderLayout.PAGE_END);

		textArea = new RSyntaxTextArea(20, 60);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		RTextScrollPane sp = new RTextScrollPane(textArea);
		cp.add(sp);

		JButton b = new JButton("Run");
		b.addActionListener(this);
		cp2.add(b);

		f.pack();
		f.setVisible(true);

		// setContentPane(cp);
		// // setTitle("Text Editor Demo");
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		// pack();
		// setLocationRelativeTo(null);

	}

	public static void main(String[] args) {

		// ClassLoader cl = ClassLoader.getSystemClassLoader();
		//
		// URL[] urls = ((URLClassLoader) cl).getURLs();
		//
		// for (URL url : urls) {
		// System.out.println(url.getFile());
		// }

		// Start all Swing applications on the EDT.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TextEditorDemo(); // .setVisible(true);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
//		System.out.println(this.textArea.getText());
		// save text to temp.java
		System.out.println("clicked");
		
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter("/home/foo/workspace/java-editor/Main.java"));
			out.write(this.textArea.getText()); // write the contents of the TextArea to the file
			out.close(); // close the file stream
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			runProcess("javac Main.java");
			runProcess("java Main");
		} catch (Exception err) {
			err.printStackTrace();
		}

	}

}