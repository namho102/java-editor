package hello;

import java.awt.*;
import javax.swing.*;

public class LayoutDemo {

	public static void main(String[] args) {
		// Create and set up the window.
		JFrame frame = new JFrame("BorderLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel(new BorderLayout());
		JButton b = new JButton("hihi");

		p.add(b);
		// p.setPreferredSize(new Dimension(0, 100));

		JPanel p1 = new JPanel(new BorderLayout());

		JButton b1 = new JButton("dsgfdrgd dfgdf gdfgdfgdf dfg dfg dfg dfg dfg dfg dfg dfg dfg dfg dfgewrrrrrrrr");

		p1.add(b1);
		
		String text = "A JTextArea object represents a multiline area for displaying text. "
		        + "You can change the number of lines that can be displayed at a time, "
		        + "as well as the number of columns. You can wrap lines and words too. "
		        + "You can also put your JTextArea in a JScrollPane to make it scrollable.";
		
		JTextArea tf =  new JTextArea(text);
		tf.setLineWrap(true);
		JScrollPane scroll = new JScrollPane (tf,
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(500, 100));

		p.setBackground(Color.black);
		p1.setBackground(Color.blue);

		frame.getContentPane().add(p, BorderLayout.PAGE_START);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		frame.getContentPane().add(p1, BorderLayout.PAGE_END);
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
