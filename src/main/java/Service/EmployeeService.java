package Service;



import com.example.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String firstName, String lastName) throws EmployeeAlreadyAddedException;
    Employee remove(String firstName, String lastName) throws EmployeeAlreadyAddedException;
    Employee find(String firstName, String lastName) throws EmployeeAlreadyAddedException;

    Collection<Employee> findAll();
}