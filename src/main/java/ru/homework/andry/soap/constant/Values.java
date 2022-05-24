package ru.homework.andry.soap.constant;

import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public final class Values {

    public static final int ERROR_CODE = 99;

    public static int QUEUE_SIZE_FOR_DELETE_EMP = 1;

    public static int CORE_POOL_SIZE = 1;
    public static int MAX_POOL_SIZE = 5;
    public static int QUEUE_CAPACITY = 3;
    public static String NAMESPACE_URI;
    public static String ROOT_WS_URL;
    public static String XSD_FILE_PATH;
    public static Range<Integer> ANALYTICS_SALARY_RANGE;
    public static Range<Integer> DEVELOPER_SALARY_RANGE;
    public static Range<Integer> MANAGER_SALARY_RANGE;
    public static String SALARY_ERROR_TEXT_MESSAGE = "Данная зарплата не подходит для позиции: {0}. ";
    public static String REQUIRED_FIELD_ERROR_TEXT_MESSAGE = "Для позиции: {0}, обязательные поля не заполнены!";
    public Values(@Value("${config.analytics.min.salary}") int analyticsMinSalary,
                  @Value("${config.analytics.max.salary}") int analyticsMaxSalary,
                  @Value("${config.developer.min.salary}") int developerMinSalary,
                  @Value("${config.developer.max.salary}") int developerMaxSalary,
                  @Value("${config.manager.min.salary}") int managerMinSalary,
                  @Value("${config.manager.mxn.salary}") int managerMaxSalary) {
        ANALYTICS_SALARY_RANGE = Range.between(analyticsMinSalary, analyticsMaxSalary);
        DEVELOPER_SALARY_RANGE = Range.between(developerMinSalary, developerMaxSalary);
        MANAGER_SALARY_RANGE = Range.between(managerMinSalary, managerMaxSalary);
    }

    @Value("${config.namespace.uri}")
    public void setNamespaceUri(String namespaceUri) {
        NAMESPACE_URI = namespaceUri;
    }

    @Value("${config.root.ws.url}")
    public void setRootWsUrl(String rootWsUrl) {
        ROOT_WS_URL = rootWsUrl;
    }

    @Value("${config.xsd.file.path}")
    public void setXsdFilePath(String xsdFilePath) {
        XSD_FILE_PATH = xsdFilePath;
    }

    @Value("${delete.emp.tread.core.poll.size}")
    public static void setCorePoolSize(int corePoolSize) {
        CORE_POOL_SIZE = corePoolSize;
    }

    @Value("${delete.emp.tread.max.poll.size}")
    public static void setMaxPoolSize(int maxPoolSize) {
        MAX_POOL_SIZE = maxPoolSize;
    }

    @Value("${delete.emp.tread.queue.capacity}")
    public static void setQueueCapacity(int queueCapacity) {
        QUEUE_CAPACITY = queueCapacity;
    }

    @Value("${delete.emp.queue.capacity}")
    public static void setQueueSizeForDeleteEmp(int queueSizeForDeleteEmp) {
        QUEUE_SIZE_FOR_DELETE_EMP = queueSizeForDeleteEmp;
    }
}
