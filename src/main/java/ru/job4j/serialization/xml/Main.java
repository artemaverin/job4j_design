package ru.job4j.serialization.xml;

import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallerImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class Main {
    public static void main(String[] args) throws JAXBException {
        final Factory factory = new Factory(false,
                451,
                "G.S.L",
                new Product("Composite products"),
                new String[] {"drying", "painting", "moulding"});

        JAXBContext context = JAXBContext.newInstance(Factory.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String result = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(factory, writer);
            result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(result)) {
            Factory res = (Factory) unmarshaller.unmarshal(reader);
            System.out.println(res);
        }
    }


}
