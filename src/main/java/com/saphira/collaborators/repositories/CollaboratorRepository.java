package com.saphira.collaborators.repositories;

import com.saphira.collaborators.models.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> { }
