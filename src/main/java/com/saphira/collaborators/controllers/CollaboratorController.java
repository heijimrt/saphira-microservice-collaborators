package com.saphira.collaborators.controllers;

import com.saphira.collaborators.dto.CollaboratorDetailsDto;
import com.saphira.collaborators.dto.CollaboratorDto;
import com.saphira.collaborators.form.CollaboratorForm;
import com.saphira.collaborators.form.CollaboratorUpdateForm;
import com.saphira.collaborators.models.Collaborator;
import com.saphira.collaborators.repositories.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
        Optional<Collaborator> collaborator = collaboratorRepository.findById(id);
        if (collaborator.isPresent()) {
            return ResponseEntity.ok(new CollaboratorDetailsDto(collaborator.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<CollaboratorDto> edit(
        @PathVariable Long id,
        @RequestBody CollaboratorUpdateForm form
    ) {
        Optional<Collaborator> optional = collaboratorRepository.findById(id);
        if (optional.isPresent()) {
            Collaborator collaborator = form.update(id, collaboratorRepository);
            return ResponseEntity.ok(new CollaboratorDto(collaborator));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Collaborator> optional = collaboratorRepository.findById(id);
        if (optional.isPresent()) {
            collaboratorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
