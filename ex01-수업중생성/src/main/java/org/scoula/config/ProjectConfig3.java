package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //컨텍스트 파일임을 설정
@ComponentScan(basePackages = "org.scoula.domain")
public class ProjectConfig3 {
}
