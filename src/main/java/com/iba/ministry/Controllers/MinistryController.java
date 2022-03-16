package com.iba.ministry.Controllers;

import com.iba.ministry.Entities.Member;
import com.iba.ministry.Entities.Ministry;
import com.iba.ministry.Repositories.MemberRepository;
import com.iba.ministry.Repositories.MinistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ministries")
public class MinistryController {

    @Autowired
    private MinistryRepository ministryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping()
    public List<Ministry> list() {
        return ministryRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Ministry> getById(@PathVariable(value = "id") Long id) {
        Ministry ministry = ministryRepository.findById(id).orElse(null);
        return ResponseEntity.ok().body(ministry);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Ministry> save(@Validated @RequestBody Ministry input) {
        ministryRepository.save(input);
        return ResponseEntity.ok().body(input);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<Ministry> update(@PathVariable(value = "id") Long id, @RequestBody Ministry input) {
        return ministryRepository.findById(id).map(ministry -> {
            ministry.setName(input.getName());
            ministry.setDescription(input.getDescription());
            ministryRepository.save(ministry);
            return ResponseEntity.ok().body(ministry);
        }).orElseThrow();
    }

    // Assign new member to Ministry
    // To-do, assign only active members
    @PutMapping("/{id}/members/{memberID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Ministry assignMemberToMinistry(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "memberID") Long memberID
    ) {
        Ministry ministry = ministryRepository.findById(id).orElse(null);
        Member member = memberRepository.findById(memberID).orElse(null);
        assert ministry != null;
        assert member != null;
        ministry.setMember(member);
        return ministryRepository.save(ministry);
    }

    // Remove member from Ministry
    @DeleteMapping("/{id}/members/{memberID}")
    public Ministry removeMember(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "memberID") Long memberID
    ){
        Ministry ministry = ministryRepository.findById(id).orElse(null);
        Member member = memberRepository.findById(memberID).orElse(null);
        assert ministry != null;
        assert member != null;
        ministry.removeMember(member);
        return ministryRepository.save(ministry);
    }
}
