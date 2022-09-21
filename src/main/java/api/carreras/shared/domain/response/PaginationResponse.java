package api.carreras.shared.domain.response;

import java.util.List;

public class PaginationResponse<E> {

    private List<E> content;

    private Short page;

    private Byte limit;

    private Short totalItems;

    private Short totalPages;

    private Boolean last;
    
    public PaginationResponse(List<E> data, Short page, Byte limit, Short totalItems, Short totalPages, Boolean last) {
        super();
        this.content = data;
        this.page = page;
        this.limit = limit;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.last = last;
    }

    public static PaginationResponse<?> build(
        List<?> data,
        Short page,
        Byte limit,
        Short totalItems,
        Short totalPages,
        Boolean last
    ) {

        return new PaginationResponse<>(data, page, limit, totalItems, totalPages, last);
    }

    public List<E> getContent() {
        return content;
    }

    public Short getPage() {
        return page;
    }

    public Byte getLimit() {
        return limit;
    }

    public Short getTotalItems() {
        return totalItems;
    }

    public Short getTotalPages() {
        return totalPages;
    }

    public Boolean getLast() {
        return last;
    }   
}
