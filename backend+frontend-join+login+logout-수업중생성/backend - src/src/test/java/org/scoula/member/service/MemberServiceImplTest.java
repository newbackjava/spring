package org.scoula.member.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.member.mapper.MemberMapper;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j2
class MemberServiceImplTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberMapper mapper;

    @Test
    void join() {

        MemberVO member = MemberVO.builder()
                .username("test2")
                .password("1234")
                .email("test2@test.com")
                .build();

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        int result0 = mapper.insert(member);
        Assertions.assertEquals(1, result0);
        log.info("insert>> " + result0);

        AuthVO auth = new AuthVO();
        auth.setUsername(member.getUsername());
        auth.setAuth("ROLE_MEMBER");
        int result = mapper.insertAuth(auth);
        log.info("insertAuth>> " + result);
        Assertions.assertEquals(0, result);
    }
}