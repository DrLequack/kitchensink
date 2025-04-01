package com.cplane.kitchensink.repository;

import com.cplane.kitchensink.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, Long> {
}
