package net.javademo.project.recruitment.request;

public class RegisterEmployerDtoRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String login;
    private String password;
    private String email;
    private String company;
    private String address;

    public RegisterEmployerDtoRequest(String firstName, String lastName, String middleName, String login, String password, String email, String company, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.company = company;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
