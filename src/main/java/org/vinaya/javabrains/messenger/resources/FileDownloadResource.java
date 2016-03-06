package org.vinaya.javabrains.messenger.resources;


/**
 * Date of Creation: Date: Feb 19, 2016<br>
 * File Name : FileDownload.java<br>
 * 
 * @author <a href=mailto:vinaya.nayak@transerainc.com>Vinaya Nayak</a>
 * @version $Revision: 1.5 $
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.vinaya.javabrains.messenger.model.Comment;

@Path("/download")
public class FileDownloadResource {

    @GET
    @Produces("text/plain")
    public Response downloadFile() {
        CSVPrinter csvp = null;
        FileWriter fileWriter = null;
        ResponseBuilder response = null;
       File fileName = new File("D:\\DemoFile.txt");

        List<Comment> commentList = new ArrayList<Comment>();

        Comment comment1 = new Comment("Hello", new Date(), "Vinaya");
        Comment comment2 = new Comment("Hello World", new Date(), "Vinaya1");
        Comment comment3 = new Comment("Hello Jersey", new Date(), "Vinaya2");
        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);
        try {
            fileWriter = new FileWriter(fileName);
            csvp = new CSVPrinter(fileWriter,CSVFormat.DEFAULT.withRecordSeparator("\n"));
            for(Comment comment : commentList){
                List<String> list = new ArrayList<String>();
                list.add(comment.getAuthor());
                list.add(comment.getMessage());
                list.add(String.valueOf(comment.getCreated()));
                csvp.printRecord(list);
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvp.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
        
        response = Response.ok((Object)fileName);
        response.header("Content-disposition",
                new StringBuilder().append("attachment;filename=").append("DemoFile.csv").toString());
        return response.build();

    }

}
