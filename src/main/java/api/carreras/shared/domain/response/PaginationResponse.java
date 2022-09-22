package api.carreras.shared.domain.response;

import java.util.List;

public class PaginationResponse {

    private List<?> content;

    private Short page;

    private Byte limit;

    private Short totalItems;

    private Short totalPages;

    private Boolean last;

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

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

    public Short getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Short totalItems) {
        this.totalItems = totalItems;
    }

    public Short getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Short totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }
}
