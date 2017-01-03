import java.awt.BorderLayout;
import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.InputSource;

public class XMLTreeView {

	private SAXTreeBuilder saxTree = null;

	public XMLTreeView(JFrame frame, String file) {
		frame.getContentPane().setLayout(new BorderLayout());
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(file);
		//              DefaultMutableTreeNode top = new DefaultMutableTreeNode("XML Document"); 

		saxTree = new SAXTreeBuilder(top);

		try {
			SAXParser saxParser = new SAXParser();
			saxParser.setContentHandler(saxTree);
			saxParser.parse(new InputSource(new FileInputStream(file)));
		} catch (Exception ex) {
			top.add(new DefaultMutableTreeNode(ex.getMessage()));
		}
		JTree tree = new JTree(saxTree.getTree());
		JScrollPane scrollPane = new JScrollPane(tree);

		frame.getContentPane().add("Center", scrollPane);
		frame.setVisible(true);

	}
}
