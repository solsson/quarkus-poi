/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.quarkiverse.poi.it;

import java.io.ByteArrayInputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.MultipartForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Path("/poi")
@ApplicationScoped
public class PoiResource {

    private static final Logger LOGGER = Logger.getLogger(PoiResource.class);

    // add some rest methods here

    @GET
    public String hello() {
        return "Hello poi";
    }

    @Inject
    PoiService poiService;

    @GET
    @Path("/export")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportExcel() {
        ByteArrayInputStream reportExcel = poiService.exportExcel();
        return Response
                .ok()
                .header("Content-Disposition", "attachment; filename=report.xlsx")
                .entity(reportExcel)
                .build();

    }

    @POST
    @Path("/read")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response readExcel(@MultipartForm FormData formData) {
        FileUpload fileUpload = formData.attachments.get(0);
        LOGGER.info(fileUpload.fileName());
        LOGGER.info(fileUpload.uploadedFile());
        LOGGER.info(fileUpload.uploadedFile().toFile().isFile());
        //
        poiService.readExcel(fileUpload.uploadedFile());
        return Response
                .ok()
                .entity("ok")
                .build();
    }

}
