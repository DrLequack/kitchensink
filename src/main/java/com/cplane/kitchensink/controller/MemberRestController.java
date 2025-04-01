package com.cplane.kitchensink.controller;

import com.cplane.kitchensink.model.Member;
import com.cplane.kitchensink.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
@AllArgsConstructor
public class MemberRestController {

    private MemberService memberService;

    @GetMapping("/members")
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("members/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.findMemberById(id);
    }

    @PostMapping("members")
    public Member addMember(@RequestBody Member newMember) {
        return memberService.saveMember(newMember);
    }


}
