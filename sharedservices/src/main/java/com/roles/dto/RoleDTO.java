package com.roles.dto;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class RoleDTO implements Serializable
{
    
    private Long id;
    
    private String name;


    // NoArgConstructors
    public RoleDTO() {}


    // AllArgConstructor
    public RoleDTO(Long id, String name) 
    {
        this.id = id;
        this.name = name;
    }
    

    // Getters and Setters
    public Long getId() 
    {
         return id;
    }
    
    public void setId(Long id) 
    {
        this.id = id;
    }
    
    public String getName() 
    {
        return name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
    
    @Override
    public String toString() 
    {
        return "RoleDTO{" +"id=" + id +", name='" + name + '\'' +'}';
    }

}
