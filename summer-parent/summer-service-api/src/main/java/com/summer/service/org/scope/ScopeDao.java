package com.summer.service.org.scope;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScopeDao extends JpaRepository<Scope, Long>, ScopeDaoMixin {

    List<ScopeBase> findAllByOrderByIdAsc();

    Optional<ScopeBase> findOneById(Long id);
}
