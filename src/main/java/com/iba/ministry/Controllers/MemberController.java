package com.iba.ministry.Controllers;


import com.iba.ministry.Entities.Member;
import com.iba.ministry.Repositories.MemberRepository;
import com.iba.ministry.Repositories.MinistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MinistryRepository ministryRepository;

    // Inefficient, fix later (To-Do). Look for a better way to do that
    @GetMapping()
    public List<Member> list() {
        List<Member> members = memberRepository.findAll();
        List<Member> new_members = new LinkedList<>();
        for (Member mem: members){
            if (mem.isActive()){
                Member e = new Member(mem.getId(),mem.getId_number(), mem.getMinistries(),
                        mem.getName(), mem.getFirst_name(), mem.getLast_name(),
                        mem.getSex(), mem.getAge(), mem.getPhone(), mem.getBirth_day(),
                        mem.getHome_address(), mem.getEmail(), mem.isActive());
                new_members.add(e);
            }
        }
        return new_members;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Member> getById(@PathVariable(value = "id") Long id) {
        Member member= memberRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Invalid member id %s", id)));
        return ResponseEntity.ok().body(member);
    }

    // Create new member
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Member> create(@Validated @RequestBody Member input) {
        memberRepository.save(input);
        return ResponseEntity.ok().body(input);
    }

    // Another less efficient way of creating entity Member

/*    public ResponseEntity<Member> create(@Validated @RequestParam(value = "id_code") String id_code,
                                       @RequestParam(value = "name") String name,
                                       @RequestParam(value ="last_name") String last_name,
                                       @RequestParam(value ="sex") Character sex,
                                       @RequestParam(value ="age") int age,
                                       @RequestParam(value ="phone") String phone,
                                       @RequestParam(value ="birthday") Date birthday,
                                       @RequestParam(value ="home_address") String home_address,
                                       @RequestParam(value ="email") String email ) {
        Member member = new Member(id_code,name, last_name, sex, age, phone, birthday, home_address, email);
        memberRepository.save(member);
        return ResponseEntity.ok().body(member);
    }*/

    // Modify member
    // To-do make an exception message
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<Member> update(@PathVariable(value = "id") Long id, @RequestBody Member input) {
        return memberRepository.findById(id).map(member -> {
            member.setId_number(input.getId_number());
            member.setName(input.getName());
            member.setLast_name(input.getLast_name());
            member.setSex(input.getSex());
            member.setAge(input.getAge());
            member.setPhone(input.getPhone());
            member.setBirth_day(input.getBirth_day());
            member.setHome_address(input.getHome_address());
            member.setEmail(input.getEmail());
            memberRepository.save(member);
            return ResponseEntity.ok().body(member);
        }).orElseThrow();
    }


    // Logic deletion
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Member> delete(@PathVariable(value = "id") Long id) {
        return memberRepository.findById(id).map(member -> {
            member.setActive(false);
            memberRepository.save(member);
            return ResponseEntity.ok().body(member);
        }).orElseThrow();
    }

}
