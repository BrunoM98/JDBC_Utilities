package bm.employee.controller;

import bm.employee.entity.Employee;
import bm.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class IndexController {

//    atributo con el cual mandamos informacion a consola
    private static final Logger logger =
            LoggerFactory.getLogger(IndexController.class);

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
//    Objeto de modelmap es el que se comparte con la vista en el index.jsp
    public String initIndex(ModelMap modelMap){
        List<Employee> employeeList = employeeService.employeeList();
//        por cada objeto de tipo empleado se imprime la informacion en consola
        employeeList.forEach((employee -> logger.info(employee.toString())));
//        compartimos el modelpmap con la vista de index
        modelMap.put("employee", employeeList);
        return "index"; // index.jsp
    }
}
