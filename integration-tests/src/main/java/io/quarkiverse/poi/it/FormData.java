package io.quarkiverse.poi.it;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

/**
 * @see MediaType.MULTIPART_FORM_DATA
 *      form 表单接收
 */
public class FormData {

    @RestForm("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public List<FileUpload> attachments;

}
