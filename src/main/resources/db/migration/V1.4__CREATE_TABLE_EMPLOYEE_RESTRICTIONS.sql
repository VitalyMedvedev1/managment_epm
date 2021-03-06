CREATE TABLE SOAP.EMPLOYEE_RESTRICTIONS
(
    ID        BIGINT NOT NULL,
    POSITION  VARCHAR(16),
    MAX_SALARY INTEGER,
    MIN_SALARY INTEGER,
    MAX_COUNT_TASKS INTEGER,
    PRIMARY KEY (ID)
);
CREATE UNIQUE INDEX EMPLOYEE_RESTRICTIONS_ID_INDX ON SOAP.EMPLOYEE_RESTRICTIONS (ID);