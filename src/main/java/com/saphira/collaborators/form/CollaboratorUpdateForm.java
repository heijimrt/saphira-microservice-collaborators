package com.saphira.collaborators.form;

import com.saphira.collaborators.models.Collaborator;
import com.saphira.collaborators.repositories.CollaboratorRepository;

public class CollaboratorUpdateForm {

    private String name;
    private String email;

    public CollaboratorUpdateForm(String name, String email) {
        this.name = name;
        this.email = email;
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

    public Collaborator update(
        Long id,
        CollaboratorRepository collaboratorRepository
    ) {
        Collaborator collaborator = collaboratorRepository.getOne(id);
        collaborator.setName(this.name);
        collaborator.setEmail(this.email);
        return collaborator;
    }
}
