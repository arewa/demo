import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.w3c.dom.*;

public class Demo1 {
	
	private static Map<String,String> colorMap = new HashMap<String,String>();
	
	static {
		colorMap.put("012", "Lavender");
		colorMap.put("020", "DarkLavender");
		colorMap.put("078", "Khaki");
		colorMap.put("100", "White");
		colorMap.put("116", "Wheat");
		colorMap.put("202", "Burnt");
		colorMap.put("210", "ButtercupYellow");
		colorMap.put("216", "Burgundy");
		colorMap.put("262", "Beige");
		colorMap.put("310", "HotPink");
		colorMap.put("343", "Ivory");
		colorMap.put("357", "Grey");
		colorMap.put("378", "Green");
		colorMap.put("383", "Indigo");
		colorMap.put("421", "Blue");
		colorMap.put("424", "DarkBlue");
		colorMap.put("440", "Turquoise");
		colorMap.put("453", "LightBlue");
		colorMap.put("456", "Brown");
		colorMap.put("463", "Cerise");
		colorMap.put("468", "Chocolate");
		colorMap.put("469", "Black");
		colorMap.put("481", "Charcoal");
		colorMap.put("492", "Buff");
		colorMap.put("494", "Chlorine");
		colorMap.put("539", "Purple");
		colorMap.put("638", "Red");
		colorMap.put("669", "Pink");
		colorMap.put("683", "Plum");
		colorMap.put("717", "Gold");
		colorMap.put("726", "Yellow");
		colorMap.put("810", "Peach");
		colorMap.put("833", "Orange");
	}

	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document document = dbf.newDocumentBuilder().parse(
				new File("ItemMaster.xml"));

		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();
		XPathExpression expression = xpath.compile("/enfinity/offer/custom-attributes/custom-attribute[@name='ColorDescription']");
		XPathExpression expression1 = xpath.compile("/enfinity/offer/custom-attributes/custom-attribute[@name='ColorCode']");

		NodeList b13NodeList = (NodeList) expression.evaluate(document,
				XPathConstants.NODESET);
		NodeList b13NodeList1 = (NodeList) expression1.evaluate(document,
				XPathConstants.NODESET);
		for (int i = 1; i < b13NodeList.getLength(); i++) {
			Node b13Node = b13NodeList.item(i);
			Node b13Node1 = b13NodeList1.item(i);
			//String colorDescription = b13Node.getTextContent().trim();
			String colorCode = b13Node1.getTextContent().trim();
			b13Node.setTextContent(colorMap.get(colorCode));
			//System.out.println(b13Node1.getTextContent() + " " +b13Node.getTextContent());
		}
		
		TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.transform(new DOMSource(document), new StreamResult(new File("ItemMaster1.xml")));
	}

}
