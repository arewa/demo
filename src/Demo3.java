import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Demo3 {
	
	private static Set<String> skus = new HashSet<String>();
	
	public static void main(String[] args) throws Exception {
		File imagesDir = new File("images");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document target = builder.parse(new File("ImagesEmpty.xml"));

		for (File sourceFile : imagesDir.listFiles()) {
			Document source = builder.parse(sourceFile);
			NodeList offerNodes = source.getDocumentElement().getChildNodes();
			for (int i = 0; i < offerNodes.getLength(); i++) {
				Node offer = offerNodes.item(i);
				String sku = offer.getAttributes().getNamedItem("sku").getTextContent();
				if (!skus.contains(sku)) {
					Node offerCopy = target.importNode(offer.cloneNode(true), true);
					target.getDocumentElement().appendChild(offerCopy);
					skus.add(sku);
				}  else {
					System.out.println("Duplicate SKU detected: " + sku);
				}
			}
		}

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.transform(new DOMSource(target), new StreamResult(new File("Images.xml")));
	}
}
