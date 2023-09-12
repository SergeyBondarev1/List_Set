package Controller;

import Service.EmployeeAlreadyAddedException;
import com.example.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.sun.java.util.jar.pack.Attribute.Layout.makeKey;
import static org.springframework.util.StringUtils.capitalize;

public class EmployeeService {
    private static final int SIZE = 10;
    public final Map <String, Employee> employee = new HashMap<>();
    public void addEmployee(String firstName, String lastName,double salary, int departmentId) throws EmployeeAlreadyAddedException {
        if (employee.size()==SIZE){
            throw new EmployeeStorageIsFullException();
        }
        var key = makeKey (firstName, lastName);
        if (employee.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        employee.put(key,new Employee(capitalize(firstName),capitalize(lastName), salary, departmentId));
    }
    public Employee findEmployee(String firstName,String lastName) throws EmployeeNotFoundException {
        var emp = employee.get((makeKey(firstName,lastName));
        if (emp == null ){
            throw new EmployeeNotFoundException();
        }
        return emp;
    }
    public boolean removeEmployee(String firstName,String lastName) throws EmployeeNotFoundException {
        Employee removed = employee.remove(makeKey(firstName,lastName));
        if (removed==null){
            throw new EmployeeNotFoundException();
        }
        return true;
    }
    public Collection<Employee> getAll(){
        return employee.values();
    }
private String makeKey ( String firstName, String lastName){
        return (firstName + "_" + lastName).toLowerCase();
}
}

