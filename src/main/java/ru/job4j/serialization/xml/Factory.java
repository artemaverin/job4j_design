package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "factory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Factory {

    @XmlAttribute
    private boolean isSubsidized;
    @XmlAttribute
    private int personalQuantity;
    @XmlAttribute
    private String name;
    @XmlElement
    private Product product;
    @XmlElementWrapper(name = "units")
    @XmlElement(name = "unit")
    private String[] units;

    public Factory() { };


    public Factory(boolean isSubsidized, int personalQuantity, String name, Product product, String[] units) {
        this.isSubsidized = isSubsidized;
        this.personalQuantity = personalQuantity;
        this.name = name;
        this.product = product;
        this.units = units;
    }

    @Override
    public String toString() {
        return "Factory{"
                + "isSubsidized=" + isSubsidized
                + ", quantity=" + personalQuantity
                + ", name=" + name
                + ", product=" + product
                + ", units=" + Arrays.toString(units)
                + '}';
    }

    public boolean isSubsidized() {
        return isSubsidized;
    }

    public int getPersonalQuantity() {
        return personalQuantity;
    }

    public String getName() {
        return name;
    }

    public Product getProduct() {
        return product;
    }

    public String[] getUnits() {
        return units;
    }

    public static void main(String[] args) throws JAXBException {
        final Factory factory = new Factory(false,
                451,
                "G.S.L",
                new Product("Composite products"),
                new String[] {"drying", "painting", "moulding"});

        JAXBContext context = JAXBContext.newInstance(Factory.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(factory, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
