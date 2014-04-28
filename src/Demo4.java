import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Demo4 {
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document cats = builder.parse(new File("Categories.xml"));
		
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();
		XPathExpression expression = xpath.compile("/enfinity/category/name");
		XPathExpression expression1 = xpath.compile("/enfinity/category/position");
		
		NodeList b13NodeList = (NodeList) expression.evaluate(cats,
				XPathConstants.NODESET);
		NodeList b13NodeList1 = (NodeList) expression1.evaluate(cats,
				XPathConstants.NODESET);
		for (int i = 0; i < b13NodeList.getLength(); i++) {
			Node b13Node = b13NodeList.item(i);
			Node b13Node1 = b13NodeList1.item(i);
			String name = b13Node.getTextContent();
			if (!name.contains("_")) {
				b13Node1.setTextContent((name.length()-1) + ".0");
			}
			System.out.println("Name: " + b13Node.getTextContent() + "; pos " + b13Node1.getTextContent());
		}
		
		TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.transform(new DOMSource(cats), new StreamResult(new File("CategoriesNew.xml")));
	}
}
