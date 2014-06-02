import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import static java.nio.file.StandardCopyOption.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
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

public class Demo5 {
	
	public static String STYLE = "48522528";

	private static final String IMAGES_XML = "Images.xml";
	private static final String PRICE_LISTS_XML = "PriceLists.xml";
	private static final String CONTENT_XML = "Content.xml";
	private static final String ITEM_MASTER_XML = "ItemMaster.xml";
	public static String FOLDER = "light_feeds/";
	public static String RESULT_FOLDER = "result/";

	private static Set<String> skus = new HashSet<String>();

	public static void main(String[] args) throws Exception {
		processItemMaster();
		processContent();
		processImages();
		processPriceLists();
	}

	private static void processItemMaster() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document target = builder.parse(new File(FOLDER
				+ "EnfinityTemplate.xml"));

		Document source = builder.parse(new File(FOLDER + ITEM_MASTER_XML));
		NodeList offerNodes = source.getDocumentElement().getChildNodes();
		for (int i = 0; i < offerNodes.getLength(); i++) {
			Node offer = offerNodes.item(i);

			String sku = "";

			if (offer.getAttributes() != null) {
				sku = offer.getAttributes().getNamedItem("sku")
						.getTextContent();

				if (sku.equals("25-" + STYLE)) {
					Node offerCopy = target.importNode(offer.cloneNode(true),
							true);
					target.getDocumentElement().appendChild(offerCopy);
				}
			}

			for (int i1 = 0; i1 < offer.getChildNodes().getLength(); i1++) {
				Node n = offer.getChildNodes().item(i1);
				if (n.getNodeName().equals("custom-attributes")) {
					for (int i2 = 0; i2 < n.getChildNodes().getLength(); i2++) {
						Node n1 = n.getChildNodes().item(i2);
						if (n1 != null) {
							if (n1.getAttributes() != null) {
								Node n2 = n1.getAttributes().getNamedItem(
										"name");
								if (n2 != null
										&& n2.getTextContent().equals(
												"StyleCode")) {
									String styleCode = n1.getTextContent();
									if (styleCode.equals(STYLE)) {
										Node offerCopy = target.importNode(
												offer.cloneNode(true), true);
										target.getDocumentElement()
												.appendChild(offerCopy);
										if (null != sku && "".equals("")) {
											skus.add(sku);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.transform(new DOMSource(target), new StreamResult(new File(FOLDER
				+ RESULT_FOLDER + ITEM_MASTER_XML)));
	}

	private static void processContent() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document target = builder.parse(new File(FOLDER
				+ "EnfinityTemplate.xml"));

		Document source = builder.parse(new File(FOLDER + CONTENT_XML));
		NodeList offerNodes = source.getDocumentElement().getChildNodes();
		for (int i = 0; i < offerNodes.getLength(); i++) {
			Node offer = offerNodes.item(i);

			if (offer.getAttributes() != null) {
				String sku = offer.getAttributes().getNamedItem("sku")
						.getTextContent();

				if (sku.equals("25-" + STYLE)) {
					
					skus.add(sku);
					
					Node offerCopy = target.importNode(offer.cloneNode(true),
							true);
					target.getDocumentElement().appendChild(offerCopy);
					break;
				}
			}
		}

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.transform(new DOMSource(target), new StreamResult(new File(FOLDER
				+ RESULT_FOLDER + CONTENT_XML)));
	}

	private static void processPriceLists() throws Exception {

		Files.copy(Paths.get(FOLDER + PRICE_LISTS_XML),
				Paths.get(FOLDER + RESULT_FOLDER + PRICE_LISTS_XML),
				REPLACE_EXISTING);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();

		Document source = builder.parse(new File(FOLDER + RESULT_FOLDER
				+ PRICE_LISTS_XML));
		NodeList priceListNodes = source.getDocumentElement().getChildNodes();
		for (int i = 0; i < priceListNodes.getLength(); i++) {
			Node priceList = priceListNodes.item(i);

			for (int i1 = 0; i1 < priceList.getChildNodes().getLength(); i1++) {
				Node n = priceList.getChildNodes().item(i1);
				if (n.getNodeName().equals("product-price-list-entry")) {
					if (n.getAttributes() != null) {
						Node n2 = n.getAttributes().getNamedItem("sku");
						if (n2 != null) {
							String sku = n2.getTextContent();
							if (!skus.contains(sku)) {
								priceList.removeChild(n);
							}
						}
					}
				}
			}
		}
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.transform(new DOMSource(source), new StreamResult(new File(FOLDER
				+ RESULT_FOLDER + PRICE_LISTS_XML)));
	}
	
	private static void processImages() throws Exception {

		Files.copy(Paths.get(FOLDER + IMAGES_XML),
				Paths.get(FOLDER + RESULT_FOLDER + IMAGES_XML),
				REPLACE_EXISTING);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();

		Document source = builder.parse(new File(FOLDER + RESULT_FOLDER + IMAGES_XML));
		NodeList offerNodes = source.getDocumentElement().getChildNodes();
		for (int i = 0; i < offerNodes.getLength(); i++) {
			Node offer = offerNodes.item(i);

			if (offer.getAttributes() != null) {
				String sku = offer.getAttributes().getNamedItem("sku")
						.getTextContent();
				
				sku = sku.substring(0, 11);
				
				if (!skus.contains(sku)) {
					source.getDocumentElement().removeChild(offer);
				}
			}
		}
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.transform(new DOMSource(source), new StreamResult(new File(FOLDER
				+ RESULT_FOLDER + IMAGES_XML)));
	}
}
