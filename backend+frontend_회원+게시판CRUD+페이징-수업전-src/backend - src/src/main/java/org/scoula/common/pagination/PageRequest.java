package org.scoula.common.pagination;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 페이지 요청 정보를 담는 클래스입니다.
 * - page: 요청 페이지 번호 (1부터 시작)
 * - amount: 한 페이지에 보여줄 항목 수
 * - getOffset(): DB 쿼리 시 사용할 offset 계산
 */
@Data // Lombok 어노테이션: Getter, Setter, toString, equals, hashCode 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 갖는 생성자를 private으로 생성
public class PageRequest {

    private int page;     // 요청한 페이지 번호 (1부터 시작)
    private int amount;   // 한 페이지에 보여줄 항목 수

    /**
     * 기본 생성자
     * 기본값으로 페이지는 1, amount는 10으로 설정
     */
    public PageRequest() {
        page = 1;
        amount = 10;
    }

    /**
     * 정적 팩토리 메서드
     * - 외부에서 객체 생성을 간결하고 명시적으로 하기 위해 사용
     * - 생성자가 private이므로 반드시 이 메서드를 통해 생성
     *
     * @param page   요청 페이지 번호
     * @param amount 페이지 당 항목 수
     * @return PageRequest 객체
     */
    public static PageRequest of(int page, int amount) {
        return new PageRequest(page, amount);
    }

    /**
     * DB 쿼리에서 LIMIT 절과 함께 사용할 offset 계산
     * 예: LIMIT offset, amount
     *
     * @return offset 값 ((page - 1) * amount)
     */
    public int getOffset() {
        return (page - 1) * amount;
    }
}
