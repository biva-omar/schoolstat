package com.school.schoolstat.services.impelements;

import com.school.schoolstat.dao.AccountRepository;
import com.school.schoolstat.dao.RoleRepository;
import com.school.schoolstat.dao.UserRepository;
import com.school.schoolstat.models.dto.requests.UserRequestDto;
import com.school.schoolstat.models.entities.Account;
import com.school.schoolstat.models.entities.Role;
import com.school.schoolstat.models.entities.User;
import com.school.schoolstat.models.enums.AccounStatus;
import com.school.schoolstat.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User retrieveUser(Long id) {
        return null;
    }

    @Override
    public List<User> retrieveUser() {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }

    public User save(UserRequestDto userDto, String username, String password) {
        if (userDto.getEmail() != null || userDto.getPhone() != null) {
            if (userDto.getEmail() != null) {
                if (accountRepository.findByEmailOrPhone(userDto.getEmail()) != null) {
                    return null;
                }
            }
            if (userDto.getPhone() != null) {
                if (accountRepository.findByEmailOrPhone(userDto.getPhone()) != null) {
                    return null;
                }
            }

            Account account = userDto.getAccountToUserRequest();
            User user = userDto.getUserTouserRequest();

            if((account.getPhone() != null && !account.getPhone().isEmpty()) && (account.getEmail() != null && !account.getEmail().isEmpty()) ) {
                account.setStatus(AccounStatus.AWAITING_EMAIL_PHONE_OTP_CODE);
                account.setPhoneOtp(getRandomNumberString());
                account.setEmailOtp(getRandomNumberString());
                //send email and phone otp
            }else {
                if(account.getPhone() != null && !account.getPhone().isEmpty()) {
                    account.setStatus(AccounStatus.AWAITING_PHONE_OTP_CODE);
                    account.setEmailOtp(getRandomNumberString());
                    //send phone
                }else {
                    if(account.getEmail() != null && !account.getEmail().isEmpty()) {
                        account.setEmailOtp(getRandomNumberString());
                        account.setStatus(AccounStatus.AWAITING_EMAIL_OTP_CODE);
                        //send email
                    }else {
                        return null;
                    }
                }
            }

            account.setId(UUID.randomUUID().toString());
            account.setPassword(encoder.encode(password));
            //account.setDisable(false);
            //account.setPhone(username);

            Role role = roleRepository.findByName(userDto.getRoleName());
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(role);
            account.setRoles(roles);

            user.setId(UUID.randomUUID().toString());

            account = accountRepository.save(account);
            //user = userRepository.insert(user);
            user.setAccount(account);
            userRepository.save(user);

            return user;
        }

        return null;
    }

    @Override
    public User findUserByAccount(String accId) {
        Optional<Account> optAcc = accountRepository.findById(accId);
        Account acc = (optAcc.isPresent()) ? optAcc.get() : null;
        if (acc == null) {
            return null;
        }
        return userRepository.findByAccount(acc);
    }

    // Fonction permettant de générer aléatoirement une chaine de caractères
    public static String getRandomNumberString() {
        // It will generate random Number from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

}
