import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RandomController {
    @GetMapping("/random/{min}/{max}")
    public String getRandomNumber(@PathParam("min") int min,
                                  @PathParam("max") int max,
                                  Model model) {
        model.addAttribute()
    }
}