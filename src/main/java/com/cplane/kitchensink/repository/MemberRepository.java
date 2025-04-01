package com.cplane.kitchensink.repository;

import com.cplane.kitchensink.model.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, Long> {
    Boolean existsByEmail(@NotNull @NotEmpty @Email String email);
}
