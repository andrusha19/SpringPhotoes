package ua.kiev.prog;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView ;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/")
public class MyController {

    private Map<Long, byte[]> photos = new HashMap<Long, byte[]>();

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo.isEmpty())
            throw new PhotoErrorException();

        try {
            long id = System.currentTimeMillis();
            photos.put(id, photo.getBytes());

            model.addAttribute("photo_id", id);
            return "result";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping(value="/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onView(@RequestParam("photo_id") long id) {
        return photoById(id);
    }
    
    @RequestMapping(value = "/viewall")
    public ModelAndView onViewall(ModelAndView mav) {
        mav.setViewName("list");
        mav.addObject("onlyList", "1");
        mav.addObject("viewList", photos.keySet());
        return mav;
    }
    
    @RequestMapping(value = "/viewlist")
    public ModelAndView onViewlist(ModelAndView mav) {
        mav.setViewName("list");
        mav.addObject("onlyList", "2");
        mav.addObject("viewList", photos.keySet());
        return mav;
    }

    @RequestMapping(value="/delete/{photo_id}")
    public String onDelete(@PathVariable("photo_id") long id) {
        if (photos.remove(id) == null)
            throw new PhotoNotFoundException();
        else
            return "index";
    }
    
    @RequestMapping(value="/deleteselected", method = RequestMethod.POST)
    public String onDeleteSelected(@RequestParam(value="photo_id", required=false) long[] id) {
        if(id == null)
            return "index";
        for(long photo_id : id){
            if (photos.remove(photo_id) == null)
                throw new PhotoNotFoundException();
        }
        return "index";
    }

    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = photos.get(id);
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
}
