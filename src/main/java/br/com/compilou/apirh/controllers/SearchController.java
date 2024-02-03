package br.com.compilou.apirh.controllers;


import br.com.compilou.apirh.repositories.CandidateRepository;
import br.com.compilou.apirh.repositories.DepedentsRepository;
import br.com.compilou.apirh.repositories.EmployeeRepository;
import br.com.compilou.apirh.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/api/rh/search")
public class SearchController {

    @Autowired
    private EmployeeRepository emp;
    @Autowired
    private VacancyRepository vac;
    @Autowired
    private DepedentsRepository dep;
    @Autowired
    private CandidateRepository cand;

    @GetMapping(value = "/index")
    public ModelAndView openIndex() {
        ModelAndView mv = new ModelAndView("../resources/templates/index.html");
        return mv;
    }

    @PostMapping(value = "/search")
    public ModelAndView searchIndex(@RequestParam("search") String search, @RequestParam("name") String name) {
        ModelAndView mv = new ModelAndView("../resources/templates/index.html");
        String message = "Search results for " + search;

        if(name.equals("employeeName")){
            mv.addObject("employee", emp.findByNames(search));
        } else if(name.equals("depedentsName")){
            mv.addObject("depedents", dep.findByDependentsNameContaining(search));
        } else if(name.equals("candidateName")){
            mv.addObject("candidate", cand.findByNameCandidateContaining(search));
        } else if(name.equals("titleVacancy")){
            mv.addObject("vacancy", vac.findByVacancyName(search));
        } else{
            mv.addObject("employee", emp.findByNames(search));
            mv.addObject("depedents", dep.findByDependentsNameContaining(search));
            mv.addObject("candidate", cand.findByNameCandidateContaining(search));
            mv.addObject("vacancy", vac.findByVacancyName(search));
        }

        mv.addObject("message", message);

        return mv;
    }
}
