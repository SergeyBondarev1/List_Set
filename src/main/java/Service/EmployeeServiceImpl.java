package Service;

import com.example.model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList;

    public EmployeeServiceImpl(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public Employee add(String firstName, String lastName) throws EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) throws EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        }
        throw new EmployeeAlreadyAddedException();
    }

    @Override
    public Employee find(String firstName, String lastName) throws EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);

        if (employeeList.contains(employee)) {
            return employee;
        }


        throw new EmployeeAlreadyAddedException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employeeList);
    }
}