package org.scoula.member.mapper;

import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;

public interface MemberMapper {
    //회원검색
    //id중복체크
    //회원가입
    //권한(role)을 추가

    MemberVO get(String username);

    MemberVO findByUsername(String username); // id 중복 체크시 사용

    int insert(MemberVO member); // 회원 정보 추가

    int insertAuth(AuthVO auth); // 회원 권한 정보 추가

    int update(MemberVO member);

    int updatePassword(ChangePasswordDTO changePasswordDTO);
}
