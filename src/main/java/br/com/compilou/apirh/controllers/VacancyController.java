package br.com.compilou.apirh.controllers;

import br.com.compilou.apirh.models.Candidate;
import br.com.compilou.apirh.models.Vacancy;
import br.com.compilou.apirh.repositories.CandidateRepository;
import br.com.compilou.apirh.repositories.VacancyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping(value = "/api/rh/vacancy")
public class VacancyController {

    @Autowired
    private VacancyRepository vac;

    @Autowired
    private CandidateRepository cand;

    @GetMapping(value = "/vacancyRegister")
    public String form(){
        return "./templates/vacancy/formVacancy.html";
    }

    @PostMapping(value = "/vacancyRegister")
    public String form(@Valid Vacancy vacancy, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("message", "Check the fields...");
            return "redirect:/vacancyRegister";
        }

        vac.save(vacancy);
        attributes.addFlashAttribute("message", "Vacancy registered successfully!");
        return "redirect:/vacancyRegister";
    }

    @GetMapping("/vacancy")
    public ModelAndView vacancyList(){
        ModelAndView mv = new ModelAndView("./templates/vacancy/vacancyList.html");
        Iterable<Vacancy> vacancies = vac.findAll();
        mv.addObject("vacancy", vacancies);
        return mv;
    }

    @GetMapping(value = "/vacancy/{code}")
    public ModelAndView detailsVacancy(@PathVariable("code") Long code){
        Vacancy vacancy = vac.findByCode(code);
        ModelAndView mv = new ModelAndView("./templates/vacancy/detailsVacancy.html");
        mv.addObject("vacancy", vacancy);

        Iterable<Candidate> candidates = cand.findByVacancy(vacancy);
        mv.addObject("candidates", candidates);

        return mv;
    }

    @DeleteMapping(value = "/deletevancy")
    public String deleteVacancy(Long code){
        Vacancy vacancy = vac.findByCode(code);
        vac.delete(vacancy);
        return "redirect:/vacancy";
    }

    @PostMapping(value = "/vacancypost/{code}")
    public String detailsVacancyPost(@PathVariable("code") Long code, @Valid Candidate candidate,
                                     BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("message", "Check the fields!");
            return "redirect:/vacancy/{code}";
        }

        if(cand.findByRg(candidate.getRg()) != null){
            attributes.addFlashAttribute("message-error", "Duplicate RG");
        }

        Vacancy vacancy = vac.findByCode(code);
        candidate.setVacancy(vacancy);
        cand.save(candidate);
        attributes.addFlashAttribute("message", "Candidate successfully added!");
        return "redirect:/vacancy/{code}";
    }

    @DeleteMapping("deletecanddel")
    public String deleteCandidate(String rg){
        Candidate candidate = cand.findByRg(rg);
        Vacancy vacancy = candidate.getVacancy();
        String code = "" + vacancy.getId();

        cand.delete(candidate);

        return "redirect:/vacancy/" + code;
    }

    @GetMapping("/editvacancy")
    public ModelAndView editVacancy(Long code){
        Vacancy vacancy = vac.findByCode(code);
        ModelAndView mv = new ModelAndView("./templates/vacancy/updateVacancy.html");
        mv.addObject("vacancy", vacancy);
        return mv;
    }

    @PostMapping(value = "/editvacancypost")
    public String updateVacancy(@Valid Vacancy vacancy, BindingResult result, RedirectAttributes attributes){
        vac.save(vacancy);
        attributes.addFlashAttribute("success", "Vacancy changed successfully!");

        Long codeLong = vacancy.getId();
        String code = "" + codeLong;
        return "redirect:/vacancy/" + code;
    }
}
