package br.com.compilou.apirh.controllers;


import br.com.compilou.apirh.repositories.CandidateRepository;
import br.com.compilou.apirh.repositories.DepedentsRepository;
import br.com.compilou.apirh.repositories.EmployeeRepository;
import br.com.compilou.apirh.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
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
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @PostMapping(value = "/search")
    public ModelAndView searchIndex(@RequestParam("search") String search, @RequestParam("name") String name) {
        ModelAndView mv = new ModelAndView("index");
        String message = "Search results for " + search;

        switch (name) {
            case "employeeName" -> mv.addObject("employee", emp.findByNames(search));
            case "depedentsName" -> mv.addObject("depedents", dep.findByDependentsName(search));
            case "candidateName" -> mv.addObject("candidates", cand.findByNameCandidate(search));
            case "titleVacancy" -> mv.addObject("vacancies", vac.findByVacancyName(search));
            default -> {
                mv.addObject("employee", emp.findByNames(search));
                mv.addObject("depedents", dep.findByDependentsName(search));
                mv.addObject("candidates", cand.findByNameCandidate(search));
                mv.addObject("vacancies", vac.findByVacancyName(search));
            }
        }

        mv.addObject("message", message);

        return mv;
    }
}
