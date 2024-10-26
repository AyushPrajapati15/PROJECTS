package com.jsp.medimart.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.medimart.Model.Member;

public interface MemberRepo extends JpaRepository<Member, Integer> {

	Optional<Member> findByEmailidAndPassword(String emailid, String password);

}
