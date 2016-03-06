/**
 * 
 */
package org.vinaya.javabrains.messenger.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * Date of Creation: Date: Feb 19, 2016<br>
 * File Name : FileUploadResource.java<br>
 * 
 * @author <a href=mailto:vinaya.nayak@transerainc.com>Vinaya Nayak</a>
 * @version $Revision: 1.5 $
 */
@Path("/upload")
public class FileUploadResource {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(@FormDataParam("file") InputStream stream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

        // save the file object to the local disk
        saveToDisk(stream, fileDetail);
        return "File Uploaded Successfully";
    }

    private void saveToDisk(InputStream stream, FormDataContentDisposition fileDetail) throws IOException {
        String uploadLocation = "D://Upload" + fileDetail.getFileName();

        try {
            OutputStream out = new FileOutputStream(new File(uploadLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = stream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
