// Created by Pankti
package finalProject;
//Code for the model class that represents an item in the drop down list is as simple as below:

public class Category {
	
	// this will have the field of tables that we need to display in the drop down
    private int id;
    private String name;
 
    public Category(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
}