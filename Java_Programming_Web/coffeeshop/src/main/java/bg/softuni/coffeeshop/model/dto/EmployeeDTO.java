package bg.softuni.coffeeshop.model.dto;

public class EmployeeDTO {
    private String username;
    private Integer countOfOrders;

    public EmployeeDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCountOfOrders() {
        return countOfOrders;
    }

    public void setCountOfOrders(Integer countOfOrders) {
        this.countOfOrders = countOfOrders;
    }
}
