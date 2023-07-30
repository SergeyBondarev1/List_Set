package Controller;

import Service.EmployeeAlreadyAddedException;
import com.example.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.sun.java.util.jar.pack.Attribute.Layout.makeKey;

public class EmployeeService {
    private static final int SIZE = 10;
    public final Map <String, Employee> employee = new HashMap<>();
    public void addEmployee(String firstName, String lastName){
        if (employee.size()==SIZE){
            throw new Exception.EmployeeAlreadyAddedException();
        }
        var key = makeKey (firstName, lastName);
        if (employee.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        employee.put(key,new Employee(firstName,lastName));
    }
    public Employee findEmployee(String firstName,String lastName){
        var emp = employee.get((makeKey(firstName,lastName));
        if (emp == null ){
            throw new Exception.EmployeeNotFoundException();
        }
        return emp;
    }
    public boolean removeEmployee(String firstName,String lastName){
        Employee removed = employee.remove(makeKey(firstName,lastName));
        if (removed==null){
            throw new Exception.EmployeeNotFoundException();
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

