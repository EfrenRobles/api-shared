package api.shared.application;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageService {

    private Short page;
    private Byte limit;
    private String sortBy;
    private String sortDir;

    public Short getPage() {
        return page;
    }

    public void setPage(Short page) {
        this.page = page;
    }

    public Byte getLimit() {
        return limit;
    }

    public void setLimit(Byte limit) {
        this.limit = limit;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    public Pageable getPageable() {

        Sort sort = Sort.by(sortBy);

        if (sortDir.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Direction.DESC, sortBy);
        }

        return PageRequest.of(page, limit, sort);
    }
}
