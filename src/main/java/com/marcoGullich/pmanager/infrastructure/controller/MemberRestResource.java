package com.marcoGullich.pmanager.infrastructure.controller;

import com.marcoGullich.pmanager.domain.applicationservice.MemberService;
import com.marcoGullich.pmanager.domain.entity.Member;
import com.marcoGullich.pmanager.infrastructure.dto.MemberDto;
import com.marcoGullich.pmanager.infrastructure.dto.SalveMemberDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.marcoGullich.pmanager.infrastructure.controller.RestConstants.PATH_MEMBERS;

@RestController
@RequestMapping(PATH_MEMBERS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class MemberRestResource {

    @Autowired
    private MemberService service;

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody @Valid SalveMemberDto dto) {
        Member member = service.createMember(dto);

        return ResponseEntity
                .created(URI.create(PATH_MEMBERS + "/" + member.getId()))
                .body(MemberDto.create(member));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> loadMemberById(@PathVariable("id") String memberId) {
        Member member = service.loadMemberById(memberId);
        return ResponseEntity.ok(MemberDto.create(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable("id") String memberId) {
        service.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable("id") String memberId,
                                                  @Valid @RequestBody SalveMemberDto salveMemberDto) {

        Member member = service.updateMember(memberId, salveMemberDto);
        return ResponseEntity.ok(MemberDto.create(member));
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> findMembers(
            @RequestParam(value = "email", required = false) String email
    ) {
        List<Member> members = service.findMembers(email);
        return ResponseEntity.ok(
                members.stream().map(MemberDto::create).toList()
        );
    }

}
