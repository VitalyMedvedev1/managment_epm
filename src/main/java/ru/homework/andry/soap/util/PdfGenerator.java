package ru.homework.andry.soap.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfGenerator {

    private static final String EXTENSION = ".jrxml";
    private final DataSource dataSource;


    public Resource generate(String pdfTemplateName, Map<String, Object> parameters) {
        log.info("Start generate pdf form by template name: {}", pdfTemplateName);
        JasperReport jasperReport = compileJasperReport(pdfTemplateName);
        JasperPrint jasperPrint;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
        } catch (JRException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return exportTemplateToByteArray(jasperPrint);
    }


    private JasperReport compileJasperReport(String templateName) {
        log.info("Start compile report from template name: {}", templateName);
        JasperReport jasperReport;
        try (InputStream reportStream = new ClassPathResource(templateName + EXTENSION).getInputStream()) {
            log.debug("Pdf template file InputStream: " + reportStream.getClass());
            jasperReport = JasperCompileManager.compileReport(reportStream);
        } catch (JRException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return jasperReport;
    }


    private Resource exportTemplateToByteArray(JasperPrint jasperPrint) {
        log.info("Start generate resource");
        Resource resource;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
            resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());
        } catch (JRException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        log.debug("Successful generate resource pdf");
        return resource;
    }
}
