import java.io.*;
import java.nio.charset.Charset;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import org.w3c.dom.*;

public class Demo {

    public static void main(String[] args) throws Exception {
    	
    	InputStream    fis;
    	BufferedReader br;
    	String         line;
    	
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document = dbf.newDocumentBuilder().parse(new File("Content.xml"));
        
        fis = new FileInputStream("uuu.log");
    	br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
    	while ((line = br.readLine()) != null) {
    		
    		String[] tt = line.split(" ");
    		
	        XPathFactory xpf = XPathFactory.newInstance();
	        XPath xpath = xpf.newXPath();
	        XPathExpression expression = xpath.compile("/enfinity/offer[@sku='" + tt[1].trim() + "']");
	
	        NodeList b13NodeList = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
	        for (int i = 1; i < b13NodeList.getLength(); i ++) {
	        	Node b13Node = b13NodeList.item(i);
	        	b13Node.getParentNode().removeChild(b13Node);
	        }
        
        // Deal with the line
    	}

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.transform(new DOMSource(document), new StreamResult(new File("tmp.xml")));
    }

}
