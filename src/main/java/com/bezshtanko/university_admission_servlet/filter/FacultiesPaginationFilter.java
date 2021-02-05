package com.bezshtanko.university_admission_servlet.filter;

import com.bezshtanko.university_admission_servlet.dto.PageInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FacultiesPaginationFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(FacultiesPaginationFilter.class);

    public static final String FACULTIES_PAGE_INFO_ATTRIBUTE_NAME = "facultiesPageInfo";
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 3;
    private static final PageInfoDTO.Criteria DEFAULT_CRITERIA = new PageInfoDTO.Criteria("state_funded_places");

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Request received");
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession(false);

        PageInfoDTO pageInfo = (PageInfoDTO) session.getAttribute(FACULTIES_PAGE_INFO_ATTRIBUTE_NAME);
        if (pageInfo == null) {
            pageInfo = PageInfoDTO.builder()
                    .setPage(DEFAULT_PAGE)
                    .setSize(DEFAULT_SIZE)
                    .setCriteria(DEFAULT_CRITERIA).build();
        } else {
            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                pageInfo.setPage(Integer.parseInt(pageParam));
            } else {
                pageInfo.setPage(DEFAULT_PAGE);
            }

            String sizeParam = request.getParameter("size");
            if (sizeParam != null) {
                pageInfo.setSize(Integer.parseInt(sizeParam));
            }

            String sortParam = request.getParameter("sort");
            if (sortParam != null) {
                pageInfo.setCriteria(createCriteriaFromParam(sortParam));
            }
        }
        log.info("Page information was processed. Result: {}", pageInfo);
        session.setAttribute(FACULTIES_PAGE_INFO_ATTRIBUTE_NAME, pageInfo);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private PageInfoDTO.Criteria createCriteriaFromParam(String param) {
        if (param.matches("([a-zA-Z_]+)")) {
            return new PageInfoDTO.Criteria(param);
        }
        if (param.matches("([a-zA-Z_]+),desc")) {
            return new PageInfoDTO.Criteria(param.substring(0, param.indexOf(",")));
        }
        if (param.matches("([a-zA-Z_]+),asc")) {
            return new PageInfoDTO.Criteria(param.substring(0, param.indexOf(",")), PageInfoDTO.Criteria.SortDirection.ASC);
        }
        throw new IllegalArgumentException();
    }

}
