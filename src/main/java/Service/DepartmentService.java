package Service;

import Controller.EmployeeNotFoundException;
import com.example.model.Employee;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentService( EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
        public double  maxSalary (int deptId){

            return (double) employeeService.getAll()
                    .stream()
                    .filter(e->e.getDepartment()==deptId)
                    .map(Employee::getSalary)
                    .max( Comparator.comparingDouble(o-> o))
                            .orElseThrow(()-> new EmployeeNotFoundException())
        }
    public double  minSalary (int deptId){

        return (double) employeeService.getAll()
                .stream()
                .filter(e->e.getDepartment()==deptId)
                .map(Employee::getSalary)
                .min (Comparator.comparingDouble(o-> o))
                        .orElseThrow(() -> new EmployeeNotFoundException());
    }
    public List<Employee> findAllByDept(int deptId){
        return employeeService.getAll()
                .stream()
                .filter(e->e.getDepartment()==deptId)
                .collect(Collectors.toList());
    }
    public void groupByDept(){
        Map<Integer,List<Employee>> map = employeeService.getAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));


    }
}

