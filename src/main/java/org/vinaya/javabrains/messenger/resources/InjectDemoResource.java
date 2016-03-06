package org.vinaya.javabrains.messenger.resources;


/**
 * Date of Creation: Date: Feb 01, 2016<br>
 * File Name : FileDownload.java<br>
 * 
 * @author <a href=mailto:vinaya.nayak@transerainc.com>Vinaya Nayak</a>
 * @version $Revision: 1.5 $
 */

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
    
    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
            @HeaderParam("customHeaderValue")String header){
        return "Matrix Param : " + matrixParam + " Custom Header Param : " + header ;
    }

}
