package com.example.homeownersspring.service.impl;


import com.example.homeownersspring.dto.UserDto;
import com.example.homeownersspring.model.Role;
import com.example.homeownersspring.model.User;
import com.example.homeownersspring.repo.UserRepository;
import com.example.homeownersspring.service.MailSender;
import com.example.homeownersspring.service.UserServiceDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserService implements UserDetailsService, UserServiceDao {

    private final UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findByUsername(username);
    }
    @Override
    public User register(UserDto user) {

        String password = generateRandomPassword(5);
        String email = user.getUsername();
        String firstName=user.getFirstname();

        User newUser = new User(email, firstName,
                user.getLastName(), user.getPatronymic(),
                user.getStreet(), user.getHouse(),
                user.getBuilding(), user.getApartment(),
                password, Collections.singleton(Role.USER));

        User registeredUser = userRepository.save(newUser);
        String message = String.format(
                "Здравствуйте, %s! \n" +
                        "Ваш пароль: %s.",
                firstName,
                password
        );
        mailSender.send(email, "Password", message);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }

    //генерации пароля
    public static String generateRandomPassword(int len)
    {
        // Диапазон ASCII – буквенно-цифровой (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }
}
