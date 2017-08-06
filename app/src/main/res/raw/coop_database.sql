CREATE schema co_op;

CREATE TABLE users (
	userId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    password VARCHAR(32),
    email VARCHAR(32),
    acountStatus BOOLEAN DEFAULT TRUE,
    secretQuestion VARCHAR(60),
    userRole INTEGER DEFAULT "1"
);
//  >> UserInformation.Columns.USER_ROLE_ID, 1
//  >> UserInformation.Columns.USER_ROLE_ID, 2
// Student >> UserInformation.Columns.USER_ROLE_ID, 3
// Admin >> UserInformation.Columns.USER_ROLE_ID, 4

INSERT INTO users VALUES();

CREATE TABLE userType (
    typeId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    typeName VARCHAR(15)
);

INSERT INTO userType VALUES();

CREATE TABLE studentInformation (
    studentInformationId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    userId LONG,
    firstName VARCHAR(35),
    lastName VARCHAR(35),
    phone/mobile VARCHAR(35),
    address VARCHAR(75),
    gender INTEGER,
    bloodGroup VARCHAR(10),
    dateOfBirth DATE,
    universityName VARCHAR(35);
    studentId LONG,

);

INSERT INTO studentInformation VALUES();

CREATE TABLE companyInformation (    
    companyInformationId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    companyName VARCHAR(50),
    companyWebsiteUrl VARCHAR(50),
    companyAddress VARCHAR(75),
    companyBusinessTypeID LONG,
    contactId    LONG,
    userId LONG
}

INSERT INTO  companyInformation VALUES();


CREATE TABLE contactInformation (    
    contactId LONG PRIMARY KEY NOT NULL DEFAULT "0",
 //   userId LONG,
    personName   VARCHAR(50),
    email VARCHAR(25),
    phone VARCHAR(15)

);

INSERT INTO  contactInformation VALUES();


CREATE TABLE universityInformation (
    universityInformationID LONG PRIMARY KEY NOT NULL DEFAULT "0",
    universityName VARCHAR(35),
    universityAddress VARCHAR(35),
    universityWebURL VARCHAR(35),
    contactId    LONG,
    userId LONG,
    approvedByUserId Long,
    createDate DATE,
    modifiedDate DATE

);

INSERT INTO universityInformation VALUES();

+ BusinessType.Columns.CREATE_DATE + " DATE, "
            + BusinessType.Columns.MODIFIED_DATE + " DATE, "
            + BusinessType.Columns.USER_ID + " INTEGER, "
            + BusinessType.Columns.BUSINESS_TYPE_IMAGE + " BLOB, "
            + BusinessType.Columns.BUSINESS_TYPE_NAME + " TEXT);";

CREATE TABLE businessType(
    businessTypeId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    CREATE_DATE DATE,
    MODIFIED_DATE DATE,
    USER_ID INTEGER,
    BUSINESS_TYPE_IMAGE BLOB,
    businessTypeName VARCHAR(25)
);

INSERT INTO businessType VALUES();

CREATE TABLE uploadFiles (
    fileId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    filyType INTEGER,
    filesize VARCHAR(10),
    file VARCHAR(50),
    userId  LONG
);

INSERT INTO uploadFiles VALUES();


    CREATE TABLE feedback (
        feedbackId LONG PRIMARY KEY NOT NULL DEFAULT "0",
        submissionByUserId LONG,
        ratedUserId LONG,
        review VARCHAR(75),
        rating VARCHAR
    );

INSERT INTO feedback VALUES();


CREATE TABLE employees(
    employeeId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    companyId LONG,
    employeeUserId LONG,
    jobId    LONG
);

INSERT INTO employees VALUES();


CREATE TABLE job (
    jobId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    jobTitle VARCHAR(15),
    salary INTEGER,
    timeSchedule TIME,
    timeLimit INTEGER,
    jobCategoryId LONG,
    jobType INTEGER,
    companyId    LONG
);


INSERT INTO job VALUES();


CREATE TABLE jobCategory (
    jobCategoryId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    categoryName VARCHAR(35)
);

INSERT INTO jobCategory VALUES();


CREATE TABLE jobStatus (
    jobStatusId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    statusName VARCHAR(15)
);

INSERT INTO jobStatus VALUES();


CREATE TABLE jobCategoryList (
    jobCategoryListId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    categoryId LONG,
    userId LONG
);


INSERT INTO jobCategoryList VALUES();





















