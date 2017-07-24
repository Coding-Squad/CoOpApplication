package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto;

import java.io.Serializable;

/**
 * Created by Pranto on 24-Jul-17.
 */

public class UploadFileDto implements Serializable {
    public static final long serialVersionUID = 20170724L;


    long m_Id;
    byte[] mUploadImage;

    public long getId() {
        return m_Id;
    }

    public void setId(long id) {
        this.m_Id = id;
    }

    public byte[] getUploadImage() {
        return mUploadImage;
    }

    public void setUploadImage(byte[] uploadImage) {
        mUploadImage = uploadImage;
    }
}
