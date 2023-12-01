package br.pess.afmo.svggrph;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class testeHTML {
/**
     * 
     */
    public testeHTML() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     *
     * @throws ParserConfigurationException
     * @throws FileNotFoundException
     * @throws TransformerException
     */
    public static void main(String[] args) throws ParserConfigurationException, FileNotFoundException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        Element e = doc.createElement("html");
        doc.appendChild(e);
        e.appendChild(doc.createElement("head"));

        Node b = e.appendChild(doc.createElement("body"));
        Node p = b.appendChild(doc.createElement("p"));
        p.appendChild(doc.createTextNode("teste"));
        doc.getDocumentElement().normalize();

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(new File("testehtml.html")));
        transformer.transform(source, result);
    }
}
