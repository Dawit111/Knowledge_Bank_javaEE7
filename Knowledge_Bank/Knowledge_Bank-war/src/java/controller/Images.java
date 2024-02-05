/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import businessLogic.File_sessionLocal;
import entity.FileStore;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author user
 */
@ManagedBean
@ApplicationScoped
public class Images {

  
    @Inject
    FileStore filestore;
    File fileContenet;
    @EJB
    File_sessionLocal file_sessionLocal;


   

    public StreamedContent getFiles() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            //  rendering the view. Return a stub StreamedContent so that it will generate right URL.            
            return new DefaultStreamedContent();
        } else {
            // browser is requesting the image. Return a real StreamedContent with the image bytes.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            filestore = new FileStore();
            filestore.setFileId(Integer.parseInt(id));
            filestore = file_sessionLocal.findbyFId(filestore.getFileId());
            if (filestore != null && filestore.getFiles().length > 0) {
                return new DefaultStreamedContent(new ByteArrayInputStream(filestore.getFiles()));
            }
        }
        return null;
    }

}
