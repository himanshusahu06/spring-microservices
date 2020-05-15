package org.hsahu.springboot.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private  Integer id;

    @NotEmpty
    @Size(min = 5, message = "user name should have at least 5 characters")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "user email must be in example@domain.com format")
    private String email;

    @Past(message = "birth date must be in past")
    private Date birthday;
}
