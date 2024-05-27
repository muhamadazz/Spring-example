package com.azz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface memberRepository
        extends JpaRepository<Member, Integer > {

}
