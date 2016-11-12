/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.jboss.logging.Logger;

/**
 *
 * @author nyinyizin
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadToServer extends HttpServlet{
    
    private final static Logger LOGGER=Logger.getLogger(UploadToServer.class.getCanonicalName());
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String podId= req.getParameter("podId");
        String note=req.getParameter("note");
        String time=req.getParameter("time");
        Part imagePart=req.getPart("image");
        byte[] imageByte=imageToByte(imagePart);
        
        // [TODO] Save to entity
        // [TODO] Call CRON job to handle it
        // [TODO] Error Handling Part
        // [TODO] Test with APK
    }
    
    private byte[] imageToByte(Part imagePart) throws IOException{
        InputStream input=imagePart.getInputStream();
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        byte[] buffer=new byte[2048];
        for (int length = 0; (length = input.read(buffer)) > 0;) output.write(buffer, 0, length);
        return output.toByteArray();
    }
}
