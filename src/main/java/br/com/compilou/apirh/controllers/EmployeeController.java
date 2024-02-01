package br.com.compilou.apirh.controllers;

import br.com.compilou.apirh.models.Depedents;
import br.com.compilou.apirh.models.Employee;
import br.com.compilou.apirh.repositories.DepedentsRepository;
import br.com.compilou.apirh.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping(value = "/api/rh/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository emp;
    @Autowired
    private DepedentsRepository dep;

    @GetMapping
    public String form(){
        return "employee/formEmployee.html";
    }

    @PostMapping(value = "/register")
    public String form(@Valid Employee employee, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("message", "Check the fields!");
            return "redirect:/register";
        }
        emp.save(employee);
        attributes.addFlashAttribute("message", "Employee registered successfully!");
        return "redirect:/register";
    }

    @RequestMapping("/employee")
    public ModelAndView employeeList() {
        ModelAndView mv = new ModelAndView("employee/employeeList.html");
        Iterable<Employee> employees = emp.findAll();
        mv.addObject("employee", employees);
        return mv;
    }

    @GetMapping(value = "/depedents/{id}")
    public ModelAndView depedents(@PathVariable("id") Long id) {
        Employee employee = emp.findById(id);
        ModelAndView mv = new ModelAndView("employee/depedents");
        mv.addObject("employee", employee);

        Iterable<Depedents> depedents = dep.findByEmployee(employee);
        mv.addObject("depedents", depedents);

        return mv;
    }

    @PostMapping(value = "/depedents/{id}")
    public String depedentsPost(@PathVariable("id") Long id, Depedents depedents, BindingResult result,
                                  RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("message", "Check the fields!");
            return "redirect:/depedents/{id}";
        }

        if(dep.findByCpf(depedents.getCpf()) != null) {
            attributes.addFlashAttribute("error-message", "Duplicate CPF");
            return "redirect:/depedents/{id}";
        }

        Employee employee = emp.findById(id);
        depedents.setEmployee(employee);
        dep.save(depedents);
        attributes.addFlashAttribute("message", "Dependent added successfully!");
        return "redirect:/depedents/{id}";

    }

    @RequestMapping("/deleteEmp")
    public String deleteEmployee(Long id) {
        Employee employee = emp.findById(id);
        emp.delete(employee);
        return "redirect:/employee";

    }

    @GetMapping(value = "/editEmployee")
    public ModelAndView editEmployee(long id) {
        Employee employee = emp.findById(id);
        ModelAndView mv = new ModelAndView("employee/update-employee");
        mv.addObject("employee", employee);
        return mv;
    }

    // update funcionário
    @PostMapping(value = "/editEmployee")
    public String updateEmployee(@Valid Employee employee,  BindingResult result, RedirectAttributes attributes){

        emp.save(employee);
        attributes.addFlashAttribute("successs", "Employee changed successfully!");

        Long idLong = employee.getId();
        String id = "" + idLong;
        return "redirect:/depedents/" + id;

    }

    @DeleteMapping(value = "/deleteDep")
    public String deleteDepedents(String cpf) {
        Depedents depedents = dep.findByCpf(cpf);

        Employee employee = depedents.getEmployee();
        String code = "" + employee.getId();

        dep.delete(depedents);
        return "redirect:/depedents/" + code;

    }
}
