package myxmlparser;

import org.xml.sax.*;

import javax.xml.parsers.SAXParser;
import java.util.HashMap;

/**
 * Created by byte on 29.10.14.
 *
 */
public class MySaxParser extends SAXParser {
    private HashMap<String, Object> mapObject;
    private HashMap<String, String> mapString;

    public MySaxParser(){
        mapObject = new HashMap<String, Object>();
        mapString = new HashMap<String, String>();
    }

    @Override
    public Parser getParser() throws SAXException {
        return null;
    }

    @Override
    public XMLReader getXMLReader() throws SAXException {
        return null;
    }

    @Override
    public boolean isNamespaceAware() {
        return false;
    }

    @Override
    public boolean isValidating() {
        return false;
    }

    @Override
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (value.getClass().isInstance(String.class)) mapString.put(name, (String)value); else
            mapObject.put(name, value);
    }

    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (mapObject.get(name) != null) return mapObject.get(name);
        if (mapString.get(name) != null) return mapString.get(name);
        return null;
    }
}
