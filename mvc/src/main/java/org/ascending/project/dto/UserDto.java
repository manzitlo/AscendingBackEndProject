package org.ascending.project.dto;

import lombok.Getter;
import lombok.Setter;
import org.ascending.project.model.User;


@Getter
@Setter
public class UserDto {

    private long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    public static UserDto convertToUserDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
}
