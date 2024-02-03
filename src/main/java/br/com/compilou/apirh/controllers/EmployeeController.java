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
@RequestMapping(value = "/api/rh/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository emp;

    @Autowired
    private DepedentsRepository dep;

    @GetMapping(value = "/formulario")
    public String form() {
        return "./templates/employee/formEmployee.html";
    }

    @PostMapping(value = "/register")
    public String form(@Valid Employee employee, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Check the fields!");
            return "redirect:/register";
        }
        emp.save(employee);
        attributes.addFlashAttribute("message", "Employee registered successfully!");
        return "redirect:/register";
    }

    @GetMapping("/employee")
    public ModelAndView employeeList() {
        ModelAndView mv = new ModelAndView("./templates/employee/employeeList.html");
        Iterable<Employee> employees = emp.findAll();
        mv.addObject("employee", employees);
        return mv;
    }

    @GetMapping(value = "/detailsemp/{id}")
    public ModelAndView depedents(@PathVariable("id") long id) {
        Employee employee = emp.findById(id);

        ModelAndView mv = new ModelAndView("./templates/employee/detailsEmployee.html");
        mv.addObject("employee", employee);

        Iterable<Depedents> depedents = dep.findByEmployee(employee);
        mv.addObject("depedents", depedents);

        return mv;
    }

    @PostMapping(value = "detailspost/{id}")
    public String detailsFuncPost(@PathVariable("id") long id, Depedents depedents, BindingResult result,
                                RedirectAttributes attributes) {

      if(result.hasErrors()){
          attributes.addFlashAttribute("message", "Check the fields!");
          return "redirect:/detailspost/{id}";
      }

      Employee employee = emp.findById(id);
      depedents.setEmployee(employee);
      dep.save(depedents);
      attributes.addFlashAttribute("message", "Dependent added successfully!");
      return "redirect:/detailspost/{id}";
    }

    @GetMapping("/deleteEmp")
    public String deleteEmployee(long id) {
        Employee employee = emp.findById(id);
        emp.delete(employee);
        return "redirect:/employee";
    }

    @GetMapping(value = "/editemployeeget")
    public ModelAndView editEmployee(long id) {
        Employee employee = emp.findById(id);
        ModelAndView mv = new ModelAndView("./templates/employee/updateEmployee.html");
        mv.addObject("employee", employee);
        return mv;
    }

    // update funcionário
    @PostMapping(value = "/editemployeepost")
    public String updateEmployee(@Valid Employee employee, BindingResult result, RedirectAttributes attributes) {
        emp.save(employee);
        attributes.addFlashAttribute("successs", "Employee changed successfully!");

        Long idLong = employee.getId();
        String id = "" + idLong;
        return "redirect:/detailsemp/" + id;
    }

    @GetMapping(value = "/deleteDep")
    public String deleteDepedents(String cpf) {
        Depedents depedents = dep.findByCpf(cpf);

        Employee employee = depedents.getEmployee();
        String code = "" + employee.getId();

        dep.delete(depedents);
        return "redirect:/redirect:/detailsemp/" + code;
    }
}