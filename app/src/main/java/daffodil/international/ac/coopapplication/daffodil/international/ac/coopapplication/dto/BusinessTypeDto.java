package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto;

import java.io.Serializable;

/**
 * Created by Pranto on 12-Jun-17.
 */

public class BusinessTypeDto implements Serializable {
    public static final long serialVersionUID = 20170612L;

    private long m_Id;
    private final String mBusinessTypeName;

    public BusinessTypeDto(long id, String businessTypeName) {
        this.m_Id = id;
        mBusinessTypeName = businessTypeName;
    }

    public long getId() {
        return m_Id;
    }

    public void setId(long id) {
        this.m_Id = id;
    }

    public String getBusinessTypeName() {
        return mBusinessTypeName;
    }

    @Override
    public String toString() {
        return getBusinessTypeName();
    }
}