package driver;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import entities.Employee;
import repositories.EmployeeRepository;

/**
 * @author dpatp
 *
 */
@SpringBootApplication
@ClientCacheApplication(name = "SimpleGemfireExample", logLevel = "fatal")
@EnableEntityDefinedRegions(basePackageClasses = Employee.class, clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireRepositories(basePackageClasses = EmployeeRepository.class)
public class Driver {

	private static final Logger LOG = LoggerFactory.getLogger(Driver.class);

	public static void main(String[] args) {
		SpringApplication.run(Driver.class, args);
	}

	@Bean
	ApplicationRunner run(EmployeeRepository employeeRepository) {
		LOG.info("Running Gemfire Demo...");

		employeeRepository.save(new Employee("Employee1", false, 0));
		employeeRepository.save(new Employee("Employee2", true, 1));
		employeeRepository.save(new Employee("Employee3", true, 8));
		employeeRepository.save(new Employee("Employee4", false, 7));
		employeeRepository.save(new Employee("Employee5", true, 2));

		LOG.info("All Employees------------------------------------------------");
		for (Employee persistedEmployee : employeeRepository.findAll()) {
			LOG.info("Employee found ('{}')", persistedEmployee);
		}
		LOG.info("-------------------------------------------------------------");

		LOG.info("Fulltime Employees-------------------------------------------");
		for (Employee persistedEmployee : employeeRepository.findByFulltimeTrue()) {
			LOG.info("Employee found ('{}')", persistedEmployee);
		}
		LOG.info("-------------------------------------------------------------");

		LOG.info("NOT Fulltime Employees---------------------------------------");
		for (Employee persistedEmployee : employeeRepository.findByFulltimeFalse()) {
			LOG.info("Employee found ('{}')", persistedEmployee);
		}
		LOG.info("-------------------------------------------------------------");

		LOG.info("Rating Greater than 5 Employees---------------------------------------");
		for (Employee persistedEmployee : employeeRepository.findByRatingGreaterThan(5)) {
			LOG.info("Employee found ('{}')", persistedEmployee);
		}
		LOG.info("-------------------------------------------------------------");

		LOG.info("Employee1 found ('{}')", employeeRepository.findById("Employee1"));
		LOG.info("Employee2 found ('{}')", employeeRepository.findById("Employee2"));

		return null;
	}

}
