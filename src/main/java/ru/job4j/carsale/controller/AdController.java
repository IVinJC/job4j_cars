package ru.job4j.carsale.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.carsale.model.Ad;
import ru.job4j.carsale.model.Author;
import ru.job4j.carsale.model.Body;
import ru.job4j.carsale.model.Mark;
import ru.job4j.carsale.service.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;
    private final MarkService markService;
    private final ModelService modelService;
    private final BodyService bodyService;
    private final AuthorService authorService;

    @GetMapping({"/index", "/"})
    public String all(Model model) {
        List<Ad> all = adService.findAll();
        model.addAttribute("ads", all);
        return "index";
    }

    @PostMapping("/createad")
    public String createAd(@ModelAttribute("ad") Ad ad,
                           @RequestParam("file") MultipartFile file) throws IOException {
        ad.setCreated(LocalDateTime.now());
        ad.setPhoto(file.getBytes());
        Mark mark = markService.findById(ad.getMark().getId());
        ru.job4j.carsale.model.Model modelById = modelService.findById(ad.getModel().getId());
        Body body = bodyService.findById(ad.getBody().getId());
        Author author = authorService.create(ad.getAuthor());
        ad.setMark(mark);
        ad.setModel(modelById);
        ad.setBody(body);
        ad.setAuthor(author);
        adService.create(ad);
        return "redirect:/index";
    }

    @GetMapping("/createad")
    public String createAdForm(Model model, @ModelAttribute("ad") Ad ad) {
        model.addAttribute("marks", markService.findAll());
        model.addAttribute("models", modelService.findAll());
        model.addAttribute("bodies", bodyService.findAll());
        return "createad";
    }

    @GetMapping("/description/{id}")
    public String description(Model model, @PathVariable("id") int id) {
        model.addAttribute("ad", adService.findById(id));
        return "description";
    }

    @GetMapping("/photoCar/{adId}")
    public ResponseEntity<Resource> download(@PathVariable("adId") Integer adId) {
        Ad ad = adService.findById(adId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(ad.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(ad.getPhoto()));
    }

    @GetMapping("/delete/{adId}")
    public ResponseEntity<Resource> download(@PathVariable("adId") int adId) {
        Ad ad = adService.findById(adId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(ad.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(ad.getPhoto()));
    }

    @PostMapping("/check_done/{id}")
    public String checkDone(@PathVariable("id") int id) {
        Ad adId = adService.findById(id);
        adId.setStatus(true);
        adService.update(id, adId);
        return "redirect:/index";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        adService.delete(id);
        return "redirect:/index";
    }
}
