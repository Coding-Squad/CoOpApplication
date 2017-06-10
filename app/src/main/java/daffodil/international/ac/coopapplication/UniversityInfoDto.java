package daffodil.international.ac.coopapplication;

import java.io.Serializable;

/**
 * Created by Pranto on 08-Jun-17.
 */

class UniversityInfoDto implements Serializable {
    public static final long serialVersionUID = 20170608L;

    private long m_Id;
    private final String mUniversityName;
    private final String mUniversityAddress;
    private final String mUniversityWebURL;
    private final int mContactId;

    public UniversityInfoDto(long id, String universityName, String universityAddress,
                             String universityWebURL, int contactId) {
        this.m_Id = id;
        mUniversityName = universityName;
        mUniversityAddress = universityAddress;
        mUniversityWebURL = universityWebURL;
        mContactId = contactId;
    }

    public long getid() {
        return m_Id;
    }

    public String getUniversityName() {
        return mUniversityName;
    }

    public String getUniversityAddress() {
        return mUniversityAddress;
    }

    public String getUniversityWebURL() {
        return mUniversityWebURL;
    }

    public int getContactId() {
        return mContactId;
    }

    public void setId(long id) {
        this.m_Id = id;
    }

    @Override
    public String toString() {
        return "UniversityInfoDto{" +
                "m_Id=" + m_Id +
                ", mUniversityName='" + mUniversityName + '\'' +
                ", mUniversityAddress='" + mUniversityAddress + '\'' +
                ", mUniversityWebURL='" + mUniversityWebURL + '\'' +
                ", mContactId=" + mContactId +
                '}';
    }
}
