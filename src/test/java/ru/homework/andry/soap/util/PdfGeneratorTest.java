package ru.homework.andry.soap.util;

import net.sf.jasperreports.engine.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import ru.homework.andry.soap.AbstractSpringContext;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

class PdfGeneratorTest extends AbstractSpringContext {

    private static final String EXTENSION = ".jrxml";
    private static final String template_DB_Name = "employee_form_template";

    @Autowired
    private DataSource dataSource;

    @Test
    void generate() throws SQLException {

        JasperReport jasperReport = null;
        try (InputStream reportStream = new ClassPathResource(template_DB_Name + EXTENSION).getInputStream()) {
            jasperReport = JasperCompileManager.compileReport(reportStream);
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> parameters = new HashMap<>();
        String uuid = "d36ee94c-f788-11ec-b939-0242ac120002";
        parameters.put("uuid", uuid);

        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
        } catch (JRException e) {
            System.out.println("\nERROR\n");
        }
        try {
            JasperExportManager.exportReportToPdfFile(jasperPrint, "employee_" + uuid + ".pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}