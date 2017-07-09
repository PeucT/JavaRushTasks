package com.javarush.task.task33.task3309;



import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, ParserConfigurationException, IOException, SAXException, TransformerException {

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        StringWriter wr = new StringWriter();

        marshaller.marshal(obj, doc);
        NodeList nodes = doc.getElementsByTagName(tagName);

        //Element element = doc.getDocumentElement();
        Comment jcomment = null;

        createCdata(doc);

        for (int i = 0; i < nodes.getLength(); i++){
            jcomment = doc.createComment(comment);
            /*nodes.item(i).getNodeType();*/
            nodes.item(i).getParentNode().insertBefore(jcomment ,nodes.item(i));
        }
        /*for (int i = 0; i < nodes.getLength(); i++){
            *//*if (nodes.item(i).getNodeType() == Node.TEXT_NODE) {*//*
                if (nodes.item(i).getTextContent().matches(".*[<>&'\"].*")){
                    nodes.item(i).getParentNode().replaceChild(doc.createCDATASection(nodes.item(i).getTextContent()),nodes.item(i));
                }
            *//*}*//*
            //System.out.println(nodes.item(i).getTextContent());
        }*/

        /*String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<first>\n" +
                "    <second>name1</second>\n" +
                "    <second>name2 with second word</second>\n" +
                "</first>";

        Marshaller marshaller1 = JAXBContext.newInstance(String.class).createMarshaller();
        marshaller1.marshal(xmlData, doc);*/


        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(doc), new StreamResult(wr));

        //marshaller.marshal(obj, wr);
        return wr.toString();
    }

    private static void createCdata(Document doc){
        Node topNode = doc.getDocumentElement();
        Node currentNode = null;
        for (currentNode = topNode.getFirstChild(); currentNode != null; currentNode = currentNode.getNextSibling()){
            if (currentNode.getTextContent().matches(".*[<>&'\"].*")) {
                CDATASection cdata = doc.createCDATASection(currentNode.getTextContent());
                currentNode.replaceChild(cdata,currentNode.getFirstChild());
                /*Node parentNode = currentNode.getParentNode();
                parentNode.removeChild(currentNode);
                parentNode.appendChild(cdata);*/
                //currentNode.getParentNode().replaceChild(doc.createCDATASection(currentNode.getTextContent()), currentNode);
            }
        }
    }

    public static void main(String[] args) throws JAXBException, ParserConfigurationException, IOException, SAXException, TransformerException {
        System.out.println(toXmlWithComment(new TestClass(), "needCDATA", "it's a comment - <needCDATA>"));
    }

    @XmlType
    @XmlRootElement(name = "first")
    private static class TestClass {
        public String[] needCDATA = new String[]{"some text1","some text 2", "need CDATA because of < and >", ""};
    }
}
