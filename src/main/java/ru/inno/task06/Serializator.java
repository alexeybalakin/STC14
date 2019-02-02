package ru.inno.task06;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;

/**
 * Серилизует и десериализует обьект в/из XML файл
 *
 * @author Alexey Balakin
 */
public class Serializator {
    /**
     * Сериализует объект в файл
     *
     * @param object объект для сериализации
     * @param file имя файла для сериализации
     */
    public void serialize(Object object, String file) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Class clazz = object.getClass();
            Element rootElement = doc.createElement(clazz.getName());
            doc.appendChild(rootElement);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Element element = doc.createElement(field.getName());
                element.appendChild(doc.createTextNode(field.get(object).toString()));
                rootElement.appendChild(element);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Десериализует объект из файла
     *
     * @param file имя файла для десериализации
     * @return десериализованный объект
     */
    public Object deSerialize(String file) {
        Object result = null;
        try {
            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            Class clazz = Class.forName(doc.getDocumentElement().getNodeName());
            result = clazz.newInstance();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                setFieldFromNode(result, nodeList.item(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Устанавливает в поле объекта значение, полученное из ноды
     *
     * @param object полю этоо обьекта будет установленно полученно из ноды значение
     * @param node нода в которой хранится значение одного из полей обьекта
     */
    private void setFieldFromNode(Object object, Node node) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(node.getNodeName());
        field.setAccessible(true);
        switch (field.getType().toString()) {
            case "byte":
                field.set(object, Byte.parseByte(node.getTextContent()));
                break;
            case "short":
                field.set(object, Short.parseShort(node.getTextContent()));
                break;
            case "int":
                field.set(object, Integer.parseInt(node.getTextContent()));
                break;
            case "long":
                field.set(object, Long.parseLong(node.getTextContent()));
                break;
            case "double":
                field.set(object, Double.parseDouble(node.getTextContent()));
                break;
            case "float":
                field.set(object, Float.parseFloat(node.getTextContent()));
                break;
            case "boolean":
                field.set(object, Boolean.parseBoolean(node.getTextContent()));
                break;
            default:
                field.set(object, node.getTextContent());
        }
    }
}
