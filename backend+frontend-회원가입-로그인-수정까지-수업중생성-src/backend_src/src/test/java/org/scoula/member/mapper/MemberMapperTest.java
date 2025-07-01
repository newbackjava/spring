package org.scoula.member.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j2
class MemberMapperTest {

    @Autowired
    MemberMapper mapper;

    @Test
    void insert() {
        //member테이블에 insert

        MemberVO member = MemberVO.builder()
                .username("test1")
                .password("$2a$10$LyIEnOZaHXqqiF2RzsISluXnCLOVFY6P/CJ5EMHagov09a5TtiA1G")
                .email("test1@test.com")
                .build();
        int result = mapper.insert(member);
        Assertions.assertEquals(0, result);
    }

    @Test
    void insertAuth() {
        //member_auth테이블에 insert

        AuthVO authVO = new AuthVO();
        authVO.setUsername("test1");
        authVO.setAuth("ROLE_MEMBER");

        int result = mapper.insertAuth(authVO);
        Assertions.assertEquals(0, result);
    }
}