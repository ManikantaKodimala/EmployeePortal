//package com.ee.employeesportal.services;
//
//import com.ee.employeesportal.advices.EmployeeException;
//import com.ee.employeesportal.model.Employee;
//import com.ee.employeesportal.repositories.JpaEmployeeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.List;
//
//@Component("UserDetailsService")
//@RequiredArgsConstructor
//public class EmployeeDetailsService implements UserDetailsService {
//    private final JpaEmployeeRepository employeeRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        final Employee byEmail = employeeRepository.findByEverestEmailId(username);
//        if (byEmail == null) {
//            throw new EmployeeException("employee not found");
//        }
//        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(byEmail.getDesignation()));
//        return new User(byEmail.getEverestEmailId(), byEmail.getPassword(), authorities);
//    }
//}
