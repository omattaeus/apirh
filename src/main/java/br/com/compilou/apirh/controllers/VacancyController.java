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
public class VacancyController {

    @Autowired
    private VacancyRepository vac;

    @Autowired
    private CandidateRepository cand;

    @GetMapping(value = "/vacancyRegister")
    public String form(){
        return "vacancy/formVacancy.html";
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

    @RequestMapping("/vagas")
    public ModelAndView vacancyList(){
        ModelAndView mv = new ModelAndView("vacancy/vacancyList.html");
        Iterable<Vacancy> vacancies = vac.findAll();
        mv.addObject("vacancy", vacancies);
        return mv;
    }

    @GetMapping(value = "/{code}")
    public ModelAndView detailsVacancy(@PathVariable("code") Long code){
        Vacancy vacancy = vac.findByCode(code);
        ModelAndView mv = new ModelAndView("vacancy/detailsVacancy.html");
        mv.addObject("vacancy", vacancy);

        Iterable<Candidate> candidates = cand.findByVacancy(vacancy);
        mv.addObject("candidates", candidates);

        return mv;
    }

    @PostMapping(value = "/{code}")
    public String datailsVacancyPost(@PathVariable("code") Long code, @Valid Candidate candidate,
                                     BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            attributes.addFlashAttribute("message", "Check the fields");
            return "redirect:/{code}";
        }

        if(cand.findByRg(candidate.getRg()) != null){
            attributes.addFlashAttribute("error_message", "Duplicate RG");
            return "redirect:/{code}";
        }

        Vacancy vacancy = vac.findByCode(code);
        candidate.setVacancy(vacancy);
        cand.save(candidate);
        attributes.addFlashAttribute("message", "Candidate successfully added!");
        return "redirect:/{code}";
    }

    @RequestMapping("/deleteCandidate")
    public String deleteCandidate(String rg){
        Candidate candidate = cand.findByRg(rg);
        Vacancy vacancy = candidate.getVacancy();
        String code = "" + vacancy.getCode();

        cand.delete(candidate);

        return "redirect:/" + code;
    }

    @GetMapping(value = "/editVacancy")
    public ModelAndView editVacancy(Long code){
        Vacancy vacancy = vac.findByCode(code);
        ModelAndView mv = new ModelAndView("vacancy/update-vacancy");
        mv.addObject("vacancy", vacancy);
        return mv;
    }

    @PostMapping(value = "/editVacancy")
    public String updateVacancy(@Valid Vacancy vacancy, BindingResult result, RedirectAttributes attributes){
        vac.save(vacancy);
        attributes.addFlashAttribute("success", "Vacancy changed successfully!");

        Long codeLong = vacancy.getCode();
        String code = "" + codeLong;
        return "redirect:/" + code;
    }
}
