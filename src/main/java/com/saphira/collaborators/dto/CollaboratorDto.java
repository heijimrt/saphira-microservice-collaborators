package com.saphira.collaborators.dto;

import com.saphira.collaborators.models.Collaborator;

import java.util.List;
import java.util.stream.Collectors;

public class CollaboratorDto {
    private String name;
    private String email;

    public CollaboratorDto(Collaborator collaborator) {
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

    public static List<CollaboratorDto> convert(List<Collaborator> collaborators) {
        return collaborators.stream().map(CollaboratorDto::new).collect(Collectors.toList());
    }
}
