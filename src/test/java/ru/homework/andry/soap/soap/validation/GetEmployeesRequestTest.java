package ru.homework.andry.soap.soap.validation;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class GetEmployeesRequestTest {

    public static final String REQUEST_XML = "src/test/resources/xml/createEmployeesRequest.xml";
    private final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    private final Schema schema = factory.newSchema(new File("build/resources/main/xsd/employees.xsd"));
    private final Validator validator = schema.newValidator();

    public GetEmployeesRequestTest() throws SAXException {
    }

    @Test
    void firstNameWithIncorrectSymbols() {
        try {
            validator.validate(new StreamSource(new File(REQUEST_XML)));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
