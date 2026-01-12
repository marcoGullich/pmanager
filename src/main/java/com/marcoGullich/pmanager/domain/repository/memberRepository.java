package com.marcoGullich.pmanager.domain.repository;

import com.marcoGullich.pmanager.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface memberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByIdAndDeleted(String id, boolean deleted);

    Optional<Member> findByEmailAndDeleted(String email, boolean deleted);
}
