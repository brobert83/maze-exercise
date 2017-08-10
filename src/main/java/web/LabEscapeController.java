package web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabEscapeController {

    @RequestMapping(method = RequestMethod.POST)
    public String drawPathForEscape(
            @RequestParam String labyrinth,
            @RequestParam int startX,
            @RequestParam int startY
    ) {
        return labyrinth + "\n" + startX + "\n" + startY;
    }
}
