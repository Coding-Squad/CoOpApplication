package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto;

import java.util.Date;

/**
 * Created by Programmer on 10-Jun-17.
 */

public class StudentInformationDto {
    private long        m_id;
    private long        m_userId;
    private String      m_firstName;
    private String      m_lastName;
    private String      m_mobile_number;
    private String      m_address;
    private int         m_gendar;
    private int         m_bloodGroup;
    private Date        m_date_of_Birth;
    private long        m_universityId;
    private long        m_studentId;
    private String      m_description;

    public StudentInformationDto() {

    }

    public StudentInformationDto(long id, long userId,
                                 String mobile_number,
                                 int gendar, int bloodGroup,
                                 long m_universityId) {
        this.m_id = id;
        this.m_userId = userId;
        this.m_mobile_number = mobile_number;
        this.m_gendar = gendar;
        this.m_bloodGroup = bloodGroup;
        this.m_universityId = m_universityId;
    }

    public long getM_id() {
        return m_id;
    }

    public void setM_id(long m_id) {
        this.m_id = m_id;
    }

    public long getM_userId() {
        return m_userId;
    }

    public void setM_userId(long m_userId) {
        this.m_userId = m_userId;
    }

    public String getM_firstName() {
        return m_firstName;
    }

    public void setM_firstName(String m_firstName) {
        this.m_firstName = m_firstName;
    }

    public String getM_lastName() {
        return m_lastName;
    }

    public void setM_lastName(String m_lastName) {
        this.m_lastName = m_lastName;
    }

    public String getM_mobile_number() {
        return m_mobile_number;
    }

    public void setM_mobile_number(String m_mobile_number) {
        this.m_mobile_number = m_mobile_number;
    }

    public String getM_address() {
        return m_address;
    }

    public void setM_address(String m_address) {
        this.m_address = m_address;
    }

    public int getM_gendar() {
        return m_gendar;
    }

    public void setM_gendar(int m_gendar) {
        this.m_gendar = m_gendar;
    }

    public int getM_bloodGroup() {
        return m_bloodGroup;
    }

    public void setM_bloodGroup(int m_bloodGroup) {
        this.m_bloodGroup = m_bloodGroup;
    }

    public Date getM_date_of_Birth() {
        return m_date_of_Birth;
    }

    public void setM_date_of_Birth(Date m_date_of_Birth) {
        this.m_date_of_Birth = m_date_of_Birth;
    }

    public long getm_universityId() {
        return m_universityId;
    }

    public void setm_universityId(long m_universityId) {
        this.m_universityId = m_universityId;
    }

    public long getM_studentId() {
        return m_studentId;
    }

    public void setM_studentId(long m_studentId) {
        this.m_studentId = m_studentId;
    }

    public String getM_description() {
        return m_description;
    }

    public void setM_description(String m_description) {
        this.m_description = m_description;
    }

    @Override
    public String toString() {
        return "StudentInformationDto{" +
                "m_id=" + m_id +
                ", m_userId=" + m_userId +
                ", m_firstName='" + m_firstName + '\'' +
                ", m_lastName='" + m_lastName + '\'' +
                ", m_mobile_number='" + m_mobile_number + '\'' +
                ", m_address='" + m_address + '\'' +
                ", m_gendar=" + m_gendar +
                ", m_bloodGroup=" + m_bloodGroup +
                ", m_date_of_Birth=" + m_date_of_Birth +
                ", m_universityId='" + m_universityId + '\'' +
                ", m_studentId=" + m_studentId +
                ", m_description='" + m_description + '\'' +
                '}';
    }
}
