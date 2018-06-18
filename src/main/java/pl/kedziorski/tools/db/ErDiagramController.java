package pl.kedziorski.tools.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class ErDiagramController {

    @Autowired
    private ErDiagramService service;


    @RequestMapping("er")
    public ResponseEntity<ByteArrayResource> er() throws Exception {


        ByteArrayResource resource = service.generateErDiagram();

        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header("Content-disposition", "attachment; filename=er-diagram.png")
                .body(resource);
    }

}
