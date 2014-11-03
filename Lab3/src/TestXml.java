import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestXml {
    public static void main(String[] args) {



        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //org.w3c.dom
        Document dom ;

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse("/media/byte/Local Disk/Study/CompModel/Lab3/SimpleFx.iml");

            Element element = dom.getDocumentElement();

            NodeList list = dom.getElementsByTagName("JAVA_MODULE");
            if (list != null && list.getLength() > 0){
                for (int i = 0; i < list.getLength(); i++) {
                    Element el = (Element) list.item(i);

                    System.out.println(el);
                }
            }




        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
