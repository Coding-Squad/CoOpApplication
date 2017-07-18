package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto;

import java.io.Serializable;

/**
 * Created by Pranto on 05-Jul-17.
 */

public class CompanyInfoDto implements Serializable {
    public static final long serialVersionUID = 20170705L;

    private long m_Id;
    private final String mCompanyName;
    private final String mCompanyAddress;
    private final String mCompanyWebURL;
    private final int mContactId;

    public CompanyInfoDto(long id, String companyName, String companyAddress, String companyWebURL, int contactId) {
        this.m_Id = id;
        mCompanyName = companyName;
        mCompanyAddress = companyAddress;
        mCompanyWebURL = companyWebURL;
        mContactId = contactId;
    }

    public long getId() {
        return m_Id;
    }

    public void setId(long id) {
        this.m_Id = id;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public String getCompanyAddress() {
        return mCompanyAddress;
    }

    public String getCompanyWebURL() {
        return mCompanyWebURL;
    }

    public int getContactId() {
        return mContactId;
    }

    @Override
    public String toString() {
        return "CompanyInfoDto{" +
                "m_Id=" + m_Id +
                ", mCompanyName='" + mCompanyName + '\'' +
                ", mCompanyAddress='" + mCompanyAddress + '\'' +
                ", mCompanyWebURL='" + mCompanyWebURL + '\'' +
                ", mContactId=" + mContactId +
                '}';
    }
}
