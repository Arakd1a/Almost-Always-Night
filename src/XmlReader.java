
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlReader {

    private final String FILE_PATH = "src\\dialoguedata.xml";
    private File dialogueFile;
    private DocumentBuilder documentBuilder;
    private Document document;
    private String selectedDialogueText;
    private int selectedIndex;
    private ArrayList<String> allDialogueText;

    public XmlReader() {
        dialogueFile = new File(FILE_PATH);
        allDialogueText = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            try {
                document = documentBuilder.parse(dialogueFile);
            } catch (SAXException ex) {
                Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList nodeList = document.getElementsByTagName("Text");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                allDialogueText.add(node.getTextContent());         
            }
        }
    }
    
    public ArrayList<String> getAllDialogueText() {
        return allDialogueText;
    }

}
