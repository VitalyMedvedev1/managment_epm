//package ru.homework.andry.soap.service;
//
//import io.dliga.micro.employee_web_service.Employee;
//import io.dliga.micro.employee_web_service.GetEmployeesResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import ru.homework.andry.soap.api.builder.EmployeeResponseBuilder;
//import ru.homework.andry.soap.api.service.EmployeeService;
//import ru.homework.andry.soap.element.employee.EmployeeElement;
//
//import java.util.List;
//
//@Service("SoapDecorator")
//@RequiredArgsConstructor
//@Slf4j
//public class EmployeeServiceSoapDecorator implements EmployeeServiceDecorator {
//
//    private final EmployeeService employeeService;
//    @SuppressWarnings("rawtypes")
//    private final List<EmployeeResponseBuilder> responseBuilders;
//
//    @Override
//    public GetEmployeesResponse findAll() {
//
//        List<Employee> employees = employeeService.findAll();
//
//        GetEmployeesResponse getEmployeesResponse = new GetEmployeesResponse();
//        String responseName = getEmployeesResponse.getClass().getSimpleName();
//        addResponseBody(null,
//                getEmployeesResponse,
//                getResponseBuilder(responseName));
//
//        return getEmployeesResponse;
//    }
//
//    @Override
//    public List<EmployeeElement> findAllByPosition(String position) {
//        return null;
//    }
//
//    @Override
//    public <T> T saveAll(List<Employee> entities) {
//        return null;
//    }
//
//    @SuppressWarnings("rawtypes")
//    public EmployeeResponseBuilder getResponseBuilder(String responseName) {
//        return responseBuilders.stream()
//                .filter(rb -> rb.getClass().getName().startsWith(responseName))
//                .findFirst().orElseThrow();
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    private <T> void addResponseBody(List<EmployeeElement> employeeElements,
//                                     T employeesResponse,
//                                     EmployeeResponseBuilder employeeResponseBuilder) {
//
//        employeeResponseBuilder.build(employeesResponse, employeeElements);
//    }
//}
