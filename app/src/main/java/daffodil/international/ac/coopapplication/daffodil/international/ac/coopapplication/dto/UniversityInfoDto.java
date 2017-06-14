package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto;

import java.io.Serializable;

/**
 * Created by Pranto on 08-Jun-17.
 */

public class UniversityInfoDto implements Serializable {
    public static final long serialVersionUID = 20170608L;

    private long m_Id;
    private final String mUniversityName;
    private final String mUniversityAddress;
    private final String mUniversityWebURL;
    private int          muniversityIsApproved;
    private final int mContactId;


    public UniversityInfoDto(long id, String universityName, String universityAddress,
                             String universityWebURL, int contactId, int universityIsApproved) {
        this.m_Id = id;
        mUniversityName = universityName;
        mUniversityAddress = universityAddress;
        mUniversityWebURL = universityWebURL;
        mContactId = contactId;
        muniversityIsApproved = universityIsApproved;
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

    public int getMuniversityIsApproved() {
        return muniversityIsApproved;
    }

    public void setMuniversityIsApproved(int muniversityIsApproved) {
        this.muniversityIsApproved = muniversityIsApproved;
    }

    public int getContactId() {
        return mContactId;
    }

    public void setId(long id) {
        this.m_Id = id;
    }

    @Override
    public String toString() {
        return getUniversityName();
    }
}
