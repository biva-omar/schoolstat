package com.school.schoolstat.models.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.school.schoolstat.models.entities.Account;
import com.school.schoolstat.models.entities.User;
import com.school.schoolstat.models.enums.AccounStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserRequestDto implements Serializable {
    @NotEmpty
    @Size(min = 2, max = 27, message = "{firstname.size}")
    private String firstname;

    @Size(min = 2, max = 27, message = "{lastname.size}")
    private String lastname;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{6,12}$", message = "{phone.pattern}")
    private String phone;

    private String gender;

    private String pseudo;

    private String avatar;

    private String country;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date bornAt;

    private String city;

    private String idCartRecto;

    private String idCartVerso;

    private String profession;

    private String roleName;

    public User getUserTouserRequest() {
        User user = new User();
        user.setBornAt(bornAt);
        user.setCity(city);
        user.setCountry(country);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setGender(gender);
        user.setIdCartRecto(idCartRecto);
        user.setIdCartVerso(idCartVerso);
        user.setProfession(profession);

        return user;
    }

    public Account getAccountToUserRequest() {
        Account acc = new Account();
        acc.setAvatar(avatar);
        acc.setEmail(email);
        acc.setPhone(phone);
        acc.setDisable(false);
        acc.setPseudo(pseudo);
        acc.setStatus(AccounStatus.AWAITING_OTP_CODE);

        return acc;
    }
}
