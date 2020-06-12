package com.saphira.collaborators.controllers;

import com.saphira.collaborators.dto.CollaboratorDto;
import com.saphira.collaborators.form.CollaboratorForm;
import com.saphira.collaborators.models.Collaborator;
import com.saphira.collaborators.repositories.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/collaborators")
public class CollaboratorController {

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @GetMapping
    public ResponseEntity<List<CollaboratorDto>> index() {
        List<Collaborator> collaborators = collaboratorRepository.findAll();
        List<CollaboratorDto> collaboratorDtos = CollaboratorDto.convert(collaborators);
        return ResponseEntity.ok().body(collaboratorDtos);
    }

    @PostMapping
    public ResponseEntity<CollaboratorDto> create(
        @RequestBody() CollaboratorForm form,
        UriComponentsBuilder uriBuilder
    ) {
        Collaborator collaborator = form.convert();
        collaboratorRepository.save(collaborator);

        URI uri = uriBuilder
            .path("/api/collaborators/{id}")
            .buildAndExpand(collaborator.getId())
            .toUri();
        return ResponseEntity.created(uri).body(new CollaboratorDto(collaborator));
    }

    // public String show() {}

    // public String edit() {}

    // public String delete() {}
}
