package com.project.securityservice.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.roles.dto.AdminDTO;


@Service
public class UserDetailServiceImpl implements UserDetailsService
{

    private final AdminClient adminClient;

    @Autowired
    public UserDetailServiceImpl(AdminClient adminClient) 
    {
        this.adminClient = adminClient;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        AdminDTO adminDTO = adminClient.fetchAdminByEmail(username);

        if (adminDTO == null) 
        {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        System.out.println("Fetched Admin Email: " + adminDTO.getAdminEmail());
        System.out.println("Fetched Roles: " + adminDTO.getRoles());

        return new User(
                adminDTO.getAdminEmail(), 
                adminDTO.getPassword(), 
                adminDTO.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList()));
    }
    
}
