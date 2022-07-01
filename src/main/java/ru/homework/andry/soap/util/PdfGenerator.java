package ru.homework.andry.soap.util;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
@Service
public class PdfGenerator {

    private static final String EXTENSION = ".jrxml";

    public byte[] generate(String pdfTemplateName, Map<String, Object> parameters) {
        log.info("Start generate pdf form by template name: {}", pdfTemplateName);
        JasperReport jasperReport = compileJasperReport(pdfTemplateName);
        JasperPrint jasperPrint;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        } catch (JRException e) {
            throw new RuntimeException(e.getMessage());
        }
        return exportTemplateToByteArray(jasperPrint);
    }


    private JasperReport compileJasperReport(String templateName) {
        log.info("Старт компилции шаблона, по имени: " + templateName);
        JasperReport jasperReport = null;
        try (InputStream reportStream = new ClassPathResource(templateName + EXTENSION).getInputStream()) {
            log.debug("Pdf template file InputStream: " + reportStream.getClass());
            jasperReport = JasperCompileManager.compileReport(reportStream);
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }

        return jasperReport;
    }


    private byte[] exportTemplateToByteArray(JasperPrint jasperPrint) {
        byte[] byteArray = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
        log.info("Ответ в байтах готовой PDF");
        return byteArray;
    }
}
