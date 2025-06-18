package org.scoula.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.fail;

@Log4j2
public class JDBCTests {
    @BeforeAll
    public static void setup() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("JDBC 드라이버 연결이 된다.")
    public void testConnection() {
        String url = "jdbc:mysql://localhost:3307/scoula_db";
        try (Connection con = DriverManager.getConnection(url, "scoula", "12345")) {
            log.info(con);
        } catch (Exception e) {
            fail(e.getMessage());
            // System.out.println(e.getMessage());
            // 연결이 실패하면 예외 메시지를 출력하고 테스트 실패 처리
        }
    }

}
