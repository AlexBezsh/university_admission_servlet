package com.bezshtanko.university_admission_servlet.dto;

public class PageInfoDTO {

    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Criteria criteria;

    public static class Criteria {

        public enum SortDirection {
            DESC,
            ASC;
        }

        private String column;
        private SortDirection direction;

        public Criteria() {
        }

        public Criteria(String column) {
            this(column, SortDirection.DESC);
        }

        public Criteria(String column, SortDirection direction) {
            this.column = column;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return column + ' ' + direction;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public SortDirection getDirection() {
            return direction;
        }

        public void setDirection(SortDirection direction) {
            this.direction = direction;
        }
    }

    public PageInfoDTO() {
    }

    private PageInfoDTO(PageInfoDTOBuilder builder) {
        this.page = builder.page;
        this.size = builder.size;
        this.totalPages = builder.totalPages;
        this.criteria = builder.criteria;
    }

    public static class PageInfoDTOBuilder {
        private Integer page;
        private Integer size;
        private Integer totalPages;
        private Criteria criteria;

        public PageInfoDTO build() {
            return new PageInfoDTO(this);
        }

        public PageInfoDTOBuilder setPage(Integer page) {
            this.page = page;
            return this;
        }

        public PageInfoDTOBuilder setSize(Integer size) {
            this.size = size;
            return this;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        public PageInfoDTOBuilder setCriteria(Criteria criteria) {
            this.criteria = criteria;
            return this;
        }
    }

    public static PageInfoDTOBuilder builder() {
        return new PageInfoDTOBuilder();
    }

    @Override
    public String toString() {
        return "PageInfoDTO{" +
                "page=" + page +
                ", size=" + size +
                ", totalPages=" + totalPages +
                ", criteria=" + criteria +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
}
