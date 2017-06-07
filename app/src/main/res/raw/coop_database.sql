CREATE schema co_op;

CREATE TABLE users (
	userId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    password VARCHAR(32),
    email VARCHAR(32),
    acountStatus BOOLEAN DEFAULT TRUE,
    secretQuestion VARCHAR(60),
    userRole INTEGER DEFAULT "1"
);

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
    jobCategoryListId LONG,
    jobStatusId LONG,
    priorWishlistId	LONG
);

INSERT INTO studentInformation VALUES();

CREATE TABLE companyInformation (    
    companyInformationId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    companyName VARCHAR(50),
    companyWebsiteUrl VARCHAR(50),
    companyAddress VARCHAR(75),
    companyBusinessTypeID LONG,
    contactId    LONG,
}

INSERT INTO  companyInformation VALUES();


CREATE TABLE contactInformation (    
    contactId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    userId LONG,
    phone VARCHAR(15),
    email VARCHAR(25),
    personName   VARCHAR(50)
);

INSERT INTO  contactInformation VALUES();


CREATE TABLE universityInformation (
    universityInformationID LONG PRIMARY KEY NOT NULL DEFAULT "0",
    universityName VARCHAR(35),
    universityAddress VARCHAR(35),
    universityWebURL VARCHAR(35),
    contactId    LONG
);

INSERT INTO universityInformation VALUES();


CREATE TABLE businessType(
    businessTypeId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    businessTypeName VARCHAR(25)
);

INSERT INTO businessType VALUES();

CREATE TABLE uploadFiles (
    fileId LONG PRIMARY KEY NOT NULL DEFAULT "0",
    filyType INTEGER,
    filesize VARCHAR(10),
    fileDirectory VARCHAR(50),
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





















