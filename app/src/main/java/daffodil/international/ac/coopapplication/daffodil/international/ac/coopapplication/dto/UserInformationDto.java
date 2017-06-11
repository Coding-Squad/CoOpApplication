package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto;

/**
 * Created by Programmer on 10-Jun-17.
 */

public class UserInformationDto {

    private long m_id;
    private String m_password;
    private String m_email;
    private boolean m_accountStatus;
    private String m_secretQuestion;
    private int m_userRole;

    public UserInformationDto() {
        super();
    }

    public UserInformationDto(long id, String password, String email) {
        this.m_id = id;
        this.m_password = password;
        this.m_email = email;
    }

    public long getM_id() {
        return m_id;
    }

    public void setM_id(long m_id) {
        this.m_id = m_id;
    }

    public String getM_password() {
        return m_password;
    }

    public void setM_password(String m_password) {
        this.m_password = m_password;
    }

    public String getM_email() {
        return m_email;
    }

    public void setM_email(String m_email) {
        this.m_email = m_email;
    }

    public boolean isM_accountStatus() {
        return m_accountStatus;
    }

    public void setM_accountStatus(boolean m_accountStatus) {
        this.m_accountStatus = m_accountStatus;
    }

    public String getM_secretQuestion() {
        return m_secretQuestion;
    }

    public void setM_secretQuestion(String m_secretQuestion) {
        this.m_secretQuestion = m_secretQuestion;
    }

    public int getM_userRole() {
        return m_userRole;
    }

    public void setM_userRole(int m_userRole) {
        this.m_userRole = m_userRole;
    }

    @Override
    public String toString() {
        return "UserInformationDto{" +
                "m_id=" + m_id +
                ", m_password='" + m_password + '\'' +
                ", m_email='" + m_email + '\'' +
                ", m_accountStatus=" + m_accountStatus +
                ", m_secretQuestion='" + m_secretQuestion + '\'' +
                ", m_userRole=" + m_userRole +
                '}';
    }
}
