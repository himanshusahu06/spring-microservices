package org.hsahu.springboot.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "all details about the user")
public class User {

    @ApiModelProperty(notes = "system generated integer identifier to uniquely identify users")
    private  Integer id;

    @NotEmpty
    @ApiModelProperty(notes = "user name should have at least 5 characters")
    @Size(min = 5, message = "user name should have at least 5 characters")
    private String name;

    @NotEmpty
    @ApiModelProperty(notes = "user email must be in example@domain.com format")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "user email must be in example@domain.com format")
    private String email;

    @Past(message = "birth date must be in past")
    @ApiModelProperty(notes = "birth date must be in past")
    private Date birthday;
}
