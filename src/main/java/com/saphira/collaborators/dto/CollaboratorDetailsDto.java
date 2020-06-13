package com.saphira.collaborators.dto;

import com.saphira.collaborators.models.Collaborator;

public class CollaboratorDetailsDto {
    private String name;
    private String email;

    public CollaboratorDetailsDto(Collaborator collaborator) {
        this.name = collaborator.getName();
        this.email = collaborator.getEmail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
