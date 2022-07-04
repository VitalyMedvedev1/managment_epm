insert
into soap.employee_restrictions  (ID,
                     POSITION,
                     MAX_SALARY,
                     MIN_SALARY,
                     MAX_COUNT_TASKS)
values (NEXTVAL('SOAP.EMPLOYEE_RESTRICTIONS_S'),
        'DEVELOPER',
        50000,
        150000,
        3);
insert
into soap.employee_restrictions  (ID,
                     POSITION,
                     MAX_SALARY,
                     MIN_SALARY,
                     MAX_COUNT_TASKS)
values (NEXTVAL('SOAP.EMPLOYEE_RESTRICTIONS_S'),
        'MANAGER',
        150000,
        175000,
        1);
insert
into soap.employee_restrictions  (ID,
                     POSITION,
                     MAX_SALARY,
                     MIN_SALARY,
                     MAX_COUNT_TASKS)
values (NEXTVAL('SOAP.EMPLOYEE_RESTRICTIONS_S'),
        'ANALYTICS',
        10000,
        35000,
        2);