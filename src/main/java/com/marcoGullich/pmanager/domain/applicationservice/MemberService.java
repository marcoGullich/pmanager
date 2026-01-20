package com.marcoGullich.pmanager.domain.applicationservice;

import com.marcoGullich.pmanager.domain.entity.Member;
import com.marcoGullich.pmanager.domain.exception.DuplicateMemberException;
import com.marcoGullich.pmanager.domain.exception.MemberNotFoundException;
import com.marcoGullich.pmanager.domain.repository.MemberRepository;
import com.marcoGullich.pmanager.infrastructure.dto.SalveMemberDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repository;

    @Transactional
    public Member createMember(SalveMemberDto dto) {

        if (existsMemberWithEmail(dto.getEmail(), null)) {
            throw new DuplicateMemberException(dto.getEmail());
        }

        Member member = new Member();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setSecret(UUID.randomUUID().toString());
        member.setDeleted(Boolean.FALSE);

        repository.save(member);
        return member;
    }

    public Member loadMemberById(String memberId) {
        return repository
                .findByIdAndDeleted(memberId, false)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Transactional
    public void deleteMember(String memberId) {
        Member member = loadMemberById(memberId);
        member.setDeleted(true);

    }

    @Transactional
    public Member updateMember(String memberId, SalveMemberDto salveMemberDto) {

        if (existsMemberWithEmail(salveMemberDto.getEmail(), memberId)) {
            throw new DuplicateMemberException(salveMemberDto.getEmail());
        }

        Member member = loadMemberById(memberId);
        member.setName(salveMemberDto.getName());
        member.setEmail(salveMemberDto.getEmail());

        return member;
    }

    private boolean existsMemberWithEmail(String email, String idToExclude) {
        return repository
                .findByEmailAndDeleted(email, false)
                .filter(m -> !Objects.equals(m.getId(), idToExclude))
                .isPresent();
    }

    public List<Member> findMembers(String email) {
        List<Member> members;

        if (Objects.isNull(email)) {
            members = repository.findAllNotDeleted2();
        } else {

            // members = repository.findByEmailAndDeleted(email, false)
            //         .map(m -> List.of(m))
            //         .orElse(List.of()); -> Exemplo do método, abaixo método resumido
            members = repository.findByEmailAndDeleted(email, false)
                    .map(List::of)
                    .orElse(List.of());
        }
        return members;
    }

}
