package Controller;

import Service.EmployeeAlreadyAddedException;
import com.example.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest {
    EmployeeService employeeService = new EmployeeService();
    @Test
    void testAdd() {
            employeeService.addEmployee("Test", "testa", 10_000, 1) ;
    Collection <Employee> allEmployees = employeeService.getAll();
    assertEquals(1, allEmployees.size ());
    var employee = allEmployees. iterator().next();
    assertEquals("Test", employee.getFirstName()) ;
    assertEquals("Test2", employee .getLastName ()) ;
    assertEquals(10_000, employee.getSalary());
    assertEquals(1, employee.getDepartment ()) ;
    }
    @Test
    void testAddWhenStorageIsFull() {
        for (int i = 0; i < 10; i++) {
            employeeService.addEmployee("test" + i, "test_test " + 1, 0d,0);
        }
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService. addEmployee ("test", "test", 0, 0));
    }
    @Test
    void testAddWhenAlreadyExists() {
        employeeService.addEmployee("test","test",0,0);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee("test","test",0, 0));
    }

    @Test
    void testFind() {
        employeeService.addEmployee("test","testtest",10_000,1);
        var actual = employeeService.findEmployee("test","testtest");
        assertEquals("test",actual.getFirstName());
        assertEquals("testtest",actual.getLastName());
        assertEquals(10000,actual.getSalary());
        assertEquals(1,actual.getDepartment());
    }

    @Test
    void testFindWhenNotExists() {
        assertThrows(Exception.EmployeeNotFoundException.class,()->employeeService.findEmployee("test","testtest"));
    }

    @Test
    void testRemove() {
        employeeService.addEmployee("test","testtest",10,0);
        assertEquals(1,employeeService.getAll().size());
        assertTrue(employeeService.removeEmployee("test","testtest"));
        assertThrows(Exception.EmployeeNotFoundException.class,()-> employeeService.removeEmployee("exist","exist"));
    }
    @Test
    void testGetAll(){
        employeeService.addEmployee("test","testtest",100,1);
        employeeService.addEmployee("test1","testtest1",-100,1);
        employeeService.addEmployee("test2","testtest2",100,-1);
    }
}