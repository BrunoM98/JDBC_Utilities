package bm.employee.controller;

import bm.employee.entity.Employee;
import bm.employee.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {

    //    atributo con el cual mandamos informacion a consola
    private static final Logger logger =
            LoggerFactory.getLogger(IndexController.class);

    @Autowired
    EmployeeService employeeService;

    //GET se utiliza para consultar información
    @RequestMapping(value = "/", method = RequestMethod.GET)
    //Objeto de modelmap es el que se comparte con la vista en el index.jsp
    public String initIndex(ModelMap modelMap) {
        List<Employee> employeeList = employeeService.employeeList();
    //por cada objeto de tipo empleado se imprime la informacion en consola
        employeeList.forEach((employee -> logger.info(employee.toString())));
    //compartimos el modelpmap con la vista de index
        modelMap.put("employees", employeeList);
        return "index"; // index.jsp
    }

    @RequestMapping(value = "/ADD", method = RequestMethod.GET)
    public String showADD() {
        return "add"; // add.jsp
    }

    // employeForm nombre con el que el objeto se guarda en el Model
    // maneja el envío del formulario de alta de empleados.
    // recibe los datos mediante POST, los mapea a un objeto Employee
    // y ejecuta la lógica necesaria para persistir la información.
    @RequestMapping(value = "/ADD", method = RequestMethod.POST)
    public String add(@ModelAttribute("employeeForm") Employee employee,
                      HttpServletRequest request) {
    //muestra el empleado a agregar
        logger.info("Add Employee" + employee);
    //guardamos los datos del objeto empleado
        employeeService.saveEmployee(employee);
        // Redirige a la URL principal para forzar una nueva petición GET
        // y recargar la lista de empleados actualizada.
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showEdit(@RequestParam int idEmployee, ModelMap modelMap){
        Employee employee = employeeService.employeeIDSearch(idEmployee);
        logger.info("Edit Employee" + employee);
        modelMap.put("employee" , employee);
        return "edit"; //muestra la pagina edit.jsp

    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("employeeForm")  Employee employee){
        logger.info("Update Employee" + employee);
        employeeService.saveEmployee(employee);
        return "redirect:/"; // redirigimos al controlador a la ruta de inicio
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@ModelAttribute("employeeForm")Employee employee){
        logger.info("Delete Employee", employee);
        employeeService.deleteEmployee(employee);
        return "redirect:/"; // redirigimos al controlador a la ruta de inicio despues de eliminar al empleado
    }
}