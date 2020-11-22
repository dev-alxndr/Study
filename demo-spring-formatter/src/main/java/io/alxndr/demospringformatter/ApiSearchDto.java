package io.alxndr.demospringformatter;


public class ApiSearchDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ApiSearchDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
