package editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class LineNr extends JPanel {
// for this simple experiment, we keep the pane + scrollpane as members.
	JTextPane pane;
	JScrollPane scrollPane;

	public LineNr() {
		super();

		setMinimumSize(new Dimension(10, 10));
		setPreferredSize(new Dimension(30, 30));
		setMaximumSize(new Dimension(30, 30));
	
		pane = new JTextPane() 
		{
			public void paint(Graphics g) {
				super.paint(g);
				LineNr.this.repaint();
			}
		};

		scrollPane = new JScrollPane(pane);

	}

	public void paint(Graphics g) {
		super.paint(g);


		int start = pane.viewToModel(scrollPane.getViewport().getViewPosition()); // starting pos in document
		int end = pane.viewToModel(new Point(scrollPane.getViewport().getViewPosition().x + pane.getWidth(),
				scrollPane.getViewport().getViewPosition().y + pane.getHeight()));

		Document doc = pane.getDocument();
		int startline = doc.getDefaultRootElement().getElementIndex(start);
		int endline = doc.getDefaultRootElement().getElementIndex(end)+1;

		int fontHeight = g.getFontMetrics(pane.getFont()).getHeight();

		for (int line = startline, y = 0; line <= endline; line++, y += fontHeight) {
			g.drawString(Integer.toString(line), 0, y);
		}

	}
	
	private int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }


}