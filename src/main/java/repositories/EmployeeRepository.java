package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, String> {
	 
    Employee findByName(String name);
    
    Iterable<Employee> findByFulltimeTrue();
    
    Iterable<Employee> findByFulltimeFalse();
    
    Iterable<Employee> findByRatingGreaterThan(int rating);
    
}
