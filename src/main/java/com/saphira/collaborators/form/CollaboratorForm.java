package com.saphira.collaborators.form;

import com.saphira.collaborators.models.Collaborator;

public class CollaboratorForm {
    private String name;
    private String email;

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

    public Collaborator convert() {
        Collaborator collaborator = new Collaborator();
        collaborator.setEmail(this.getEmail());
        collaborator.setName(this.getName());
        return collaborator;
    }
}
