package entities;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region(value = "employee")
public class Employee implements Serializable {
	 
    private static final long serialVersionUID = 1L;
    
    @Id
    private final String name;
    
    private boolean fulltime;
    
    private int rating;

    @PersistenceConstructor
	public Employee(String name, boolean fulltime, int rating) {
		super();
		this.name = name;
		this.fulltime = fulltime;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}
	
	public boolean getFulltime() {
		return fulltime;
	}

	/**
	 * @param fulltime the fulltime to set
	 */
	public void setFulltime(boolean fulltime) {
		this.fulltime = fulltime;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
