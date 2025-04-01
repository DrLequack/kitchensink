package com.cplane.kitchensink.service;

import com.cplane.kitchensink.exception.DuplicateEmailException;
import com.cplane.kitchensink.exception.UserNotFoundException;
import com.cplane.kitchensink.model.Member;
import com.cplane.kitchensink.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public List<Member> getAllMembers() {
        return memberRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public Member saveMember(Member member) {
        member.setId(sequenceGeneratorService.generateSequence(Member.SEQUENCE_NAME));
        try {
            return memberRepository.save(member);
        } catch (DuplicateKeyException e){
            throw new DuplicateEmailException("Email: " + member.getEmail() + " is already present");
        }

    }

}
