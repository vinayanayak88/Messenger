package org.vinaya.javabrains.messenger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.vinaya.javabrains.messenger.model.ErrorMessage;

//@Provider
public class GenenricExceptionMapper  implements ExceptionMapper<Throwable>{

    @Override
    public Response toResponse(Throwable exception) {
        ErrorMessage errMessage = new ErrorMessage(exception.getMessage(),500,"http://www.google.com");
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errMessage).build();
    }

}
