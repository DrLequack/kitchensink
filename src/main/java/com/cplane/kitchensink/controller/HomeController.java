package com.cplane.kitchensink.controller;

import com.cplane.kitchensink.model.Member;
import com.cplane.kitchensink.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private MemberService memberService;

    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute("newMember") Member newMember,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("members", memberService.getAllMembers());
            return "index";
        }

        if(memberService.isEmailPresent(newMember.getEmail())) {
            model.addAttribute("members", memberService.getAllMembers());
            model.addAttribute("messages", "Email already taken");
            return "index";
        }

        memberService.saveMember(newMember);
        model.addAttribute("messages", "Registered!");
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("newMember", new Member());
        return "index";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("newMember", new Member());
        return "index";
    }
}
