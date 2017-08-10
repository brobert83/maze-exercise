package web;

import escape.LabEscapeService;
import escape.exception.NoEscapeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabEscapeController {

    private LabEscapeService labEscapeService;

    public LabEscapeController(LabEscapeService labEscapeService) {
        this.labEscapeService = labEscapeService;
    }

    @RequestMapping(path = "/escapeRoute", method = RequestMethod.POST)
    public ResponseEntity<String> drawPathForEscape(
            @RequestParam String labyrinth,
            @RequestParam int startX,
            @RequestParam int startY) {

        try {
            String escapeRoute = labEscapeService.findEscapeRoute(labyrinth, startX, startY);
            return new ResponseEntity<>(escapeRoute, HttpStatus.OK);
        } catch (NoEscapeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
