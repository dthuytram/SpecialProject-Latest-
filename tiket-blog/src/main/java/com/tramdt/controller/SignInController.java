package com.tramdt.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Date;
import com.google.api.services.people.v1.model.Person;
import com.tramdt.config.sercurity.JwtTokenUtil;
import com.tramdt.dto.CustomerDto;
import com.tramdt.dto.EmployeeDto;
import com.tramdt.dto.JwtResponse;
import com.tramdt.dto.TokenDto;
import com.tramdt.model.*;
import com.tramdt.security.userprinciple.AccountPrinciple;
import com.tramdt.service.ICustomerService;
import com.tramdt.service.sercurity.impl.AccountServiceImpl;
import com.tramdt.service.sercurity.impl.RoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@CrossOrigin("*")
@Slf4j
public class SignInController {
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ICustomerService customerService;

    @PostMapping(value = "api/signUp")
    public ResponseEntity<?> register(@Valid @RequestBody SignForm signForm) {
        CustomerDto customerDto = new CustomerDto();
        EmployeeDto employeeDto = new EmployeeDto();
        System.out.println("sign up");
        if (accountService.existAccountByPhone(signForm.getPhone())) {
            return new ResponseEntity<>("duplicate phone", HttpStatus.BAD_REQUEST);
        }
        if (accountService.existAccountByIdCard(signForm.getIdCard())) {
            return new ResponseEntity<>("duplicate idCard", HttpStatus.BAD_REQUEST);
        }
        if (accountService.existAccountByEmail(signForm.getEmail())) {
            return new ResponseEntity<>("duplicate email", HttpStatus.BAD_REQUEST);
        }
        Account acc = new Account();
        BeanUtils.copyProperties(signForm, acc);
        acc.setPassword(passwordEncoder.encode(signForm.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(3L, RoleName.ROLE_CUSTOMER));
        if(acc.getEmail().equals("admin@gmail.com")){
            roleSet.add(new Role(2L, RoleName.ROLE_EMPLOYEE));
            roleSet.add(new Role(1L, RoleName.ROLE_ADMIN));
        }
        if(acc.getEmail().equals("employee@gmail.com")){
            roleSet.add(new Role(2L, RoleName.ROLE_EMPLOYEE));
        }
        acc.setRoles(roleSet);

        customerDto.setNameCustomer(acc.getFullName());
        customerDto.setPhoneCustomer(acc.getPhone());
        customerDto.setBirthdayCustomer(acc.getBirthday());
        customerDto.setGenderCustomer(acc.getGender());
        customerDto.setEmailCustomer(acc.getEmail());
        customerDto.setIdCardCustomer(acc.getIdCard());
        customerDto.setAddressCustomer(acc.getAddress());
        customerDto.setCustomerType(2L);
        customerDto.setCountries(acc.getCountry().getId());
        customerService.save(customerDto);
        accountService.save(acc);
        return new ResponseEntity<>(acc, HttpStatus.CREATED);
    }

    @PostMapping("api/signIn")
    public ResponseEntity<?> signIn(@RequestBody User user) {
        System.out.println("=====WELCOME SIGNIN =======");
        Authentication authentication;
        if (!accountService.existAccountByEmail(user.getEmail())) {
            System.out.println("=====NOT EXIST EMAIL =======");
            return new ResponseEntity<>("emailError", HttpStatus.BAD_REQUEST);
        }
        if (!accountService.existAccountByPassword(user.getPassword())) {
            System.out.println("=====NOT EXIST PASS =======");
            return new ResponseEntity<>("passwordError", HttpStatus.BAD_REQUEST);
        }

        UsernamePasswordAuthenticationToken a = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        System.out.println("User: " + a);

        authentication = authenticationManager.authenticate(a);
        System.out.println("authentication: " + authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        System.out.println("token: "+ token);
        System.out.println("account: "+ accountPrinciple);
        System.out.println("accountPrinciple.getAuthorities(): "+ accountPrinciple.getAuthorities());
        return new ResponseEntity<>(new JwtReponse(token, accountPrinciple.getEmail(), accountPrinciple.getAuthorities()), HttpStatus.OK);

    }

    @PostMapping("api/forgetPassword")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody PasswordForgettedForm passwordForgettedForm){
        SimpleMailMessage message = new SimpleMailMessage();
        if(accountService.existAccountByEmail(passwordForgettedForm.getEmail())){
            Account account = accountService.findAccountByEmail(passwordForgettedForm.getEmail());
            account.setPassword(passwordEncoder.encode(passwordForgettedForm.getPassword()));
            account.setConfirmPassword(passwordForgettedForm.getConfirmPassword());
            accountService.save(account);
            message.setTo(passwordForgettedForm.getEmail());
            message.setSubject("Thay đổi mật khẩu");
            message.setText("Mật khẩu của bạn đã ở website Flight đã được thay đôi thành "+ passwordForgettedForm.getPassword());
            javaMailSender.send(message);
            return new ResponseEntity<>("ok",HttpStatus.OK);
        }else  return new ResponseEntity<>("Email không tồn taị",HttpStatus.BAD_REQUEST);


    }

    @PostMapping("api/changePassword")
    public ResponseEntity<?> forgetPassword(@RequestBody ChangingPassword changingPassword){
        if(accountService.existAccountByEmail(changingPassword.getEmail())&&
        accountService.existAccountByPassword(changingPassword.getPassword())){
            Account account = accountService.findAccountByEmail(changingPassword.getEmail());
            account.setPassword(passwordEncoder.encode(changingPassword.getNewPassword()));
            account.setConfirmPassword(changingPassword.getConfirmPassword());
            accountService.save(account);
            return new ResponseEntity<>("ok",HttpStatus.OK);
        }else  return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);

    }

    @PatchMapping("/api/changePassword")
    public ResponseEntity <?> changePassword (@RequestBody PasswordForgettedForm passwordForgettedForm) {
        if (accountService.existAccountByEmail(passwordForgettedForm.getEmail())
                && (accountService.existAccountByPassword(passwordForgettedForm.getPassword()))) {
            Account account = accountService.findAccountByEmail(passwordForgettedForm.getEmail());
            account.setPassword(passwordEncoder.encode(passwordForgettedForm.getPassword()));
            accountService.save(account);
        }
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * Login by google
     */
    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody User user)  {
        Account accountGoogle ;
        Authentication authentication;
        System.out.println("PassWORD: " + user.getPassword() + "email: " + user.getEmail());
        UsernamePasswordAuthenticationToken a1 = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        authentication = authenticationManager.authenticate(a1);
        System.out.println("authentication: " + authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.createToken(authentication);

        System.out.println("token: " + token);
        if(accountService.existAccountByEmail(user.getEmail())) {
            System.out.println("DA TON TAI");
            AccountPrinciple accountPrinciple1 = (AccountPrinciple) authentication.getPrincipal();
            return new ResponseEntity<>(new JwtReponse(token, accountPrinciple1.getEmail(), accountPrinciple1.getAuthorities()), HttpStatus.OK);
        }
        else {
            accountGoogle = getProfileGoogle(user.getPassword(), token);
            accountService.save(accountGoogle);
            AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
            return new ResponseEntity<>(new JwtReponse(token, accountPrinciple.getEmail(), accountPrinciple.getAuthorities()), HttpStatus.OK);
        }


    }

    public Account getProfileGoogle(String pass, String token) {
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();

        GoogleCredential credential = new GoogleCredential.Builder().build().setAccessToken(token);
        PeopleService peopleService =
                new PeopleService(transport, jacksonFactory, credential);
        Person profile = null;
        try {
            profile = peopleService.people().get("people/me")
                    .setPersonFields("emailAddresses,birthdays,names,genders,photos,addresses,phoneNumbers")
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Account account = null;
        if (profile != null) {
            String email = profile.getEmailAddresses().get(0).getValue();
            Date date = profile.getBirthdays().get(0).getDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            String birthday = LocalDate.parse(date.getDay() + "/" + date.getMonth() + "/" + date.getYear(), formatter ).toString();
            String name = profile.getNames().get(0).getDisplayName();
            String gender = profile.getGenders().get(0).getValue();
            Boolean gender1 = checkGender1(gender);
            Set<Role> setA = new HashSet<>();
            Role role = new Role();
            role.setRole(RoleName.ROLE_CUSTOMER);
            setA.add(role);
            account = new Account(email, passwordEncoder.encode(pass), setA,
                    birthday, gender1);

        } else {
            Logger.getLogger("Not profile google");
        }

        return account;
    }

    public boolean checkGender1(String gender){
        if("male".equals(gender)){
            return true;
        }else {
            return false;
        }
    }

}
