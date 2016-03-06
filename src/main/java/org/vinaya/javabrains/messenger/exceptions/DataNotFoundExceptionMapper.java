package org.vinaya.javabrains.messenger.exceptions;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.vinaya.javabrains.messenger.model.ErrorMessage;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

    @Override
    public Response toResponse(DataNotFoundException exception) {
        ErrorMessage errMessage = new ErrorMessage(exception.getMessage(),404,"http://www.google.com");
        return Response.status(Status.NOT_FOUND).entity(errMessage).build();
    }

}
