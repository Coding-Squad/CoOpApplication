package daffodil.international.ac.coopapplication;

import java.io.Serializable;

/**
 * Created by Pranto on 10-Jun-17.
 */

class ContactInfoDto implements Serializable {
    public static final long serialVersionUID = 20170610L;


    private long m_Id;
    private final String mPersonName;
    private final String mPersonEmail;
    private final String mPersonPhone;

    public ContactInfoDto(long id, String personName, String personEmail, String personPhone) {
        this.m_Id = id;
        mPersonName = personName;
        mPersonEmail = personEmail;
        mPersonPhone = personPhone;
    }

    public long getId() {
        return m_Id;
    }

    public String getPersonName() {
        return mPersonName;
    }

    public String getPersonEmail() {
        return mPersonEmail;
    }

    public String getPersonPhone() {
        return mPersonPhone;
    }

    public void setId(long id) {
        this.m_Id = id;
    }

    @Override
    public String toString() {
        return "ContactInfoDto{" +
                "id=" + m_Id +
                ", mPersonName='" + mPersonName + '\'' +
                ", mPersonEmail='" + mPersonEmail + '\'' +
                ", mPersonPhone='" + mPersonPhone + '\'' +
                '}';
    }
}
