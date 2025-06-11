package org.scoula.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    final String LOCATION = "c:/upload";
    final long MAX_FILE_SIZE = 1024 * 1024 * 10L;
    final long MAX_REQUEST_SIZE =  1024 * 1024 * 20L;
    final int FILE_SIZE_THRESHOLD = 1024 * 1024 * 5;;

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        MultipartConfigElement multipartConfig =
                new MultipartConfigElement(
                        LOCATION,   // 업로드 처리 디렉토리 경로
                        MAX_FILE_SIZE,	// 업로드 가능한 파일 하나의 최대 크기
                        MAX_REQUEST_SIZE,	// 업로드 가능한 전체 최대 크기(여러 파일 업로드 하는 경우)
                        FILE_SIZE_THRESHOLD		// 메모리 파일의 최대 크기(이보다 작으면 실제 메모리에서만 작업)
                );
        registration.setMultipartConfig(multipartConfig);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() { //프론트컨트롤러 언제 적용할지
        return new String[]{"/"};
    }

    //http의 body로 전송되는 post방식의 데이터는
    //프론트컨트롤러가 받기 전에 미리 utf-8로 인코딩을 먼저 한 후
    //받게 설정함.
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[] {characterEncodingFilter};
    }
}
