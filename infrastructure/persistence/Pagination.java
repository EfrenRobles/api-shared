package api.shared.infrastructure.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class Pagination<E> {
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<E> criteriaQuery;
    private Pageable pageable;

    public Pagination(
        EntityManager entityManager,
        CriteriaBuilder criteriaBuilder,
        CriteriaQuery<E> criteriaQuery,
        Pageable pageable
    ) {
        this.entityManager = entityManager;
        this.criteriaBuilder = criteriaBuilder;
        this.criteriaQuery = criteriaQuery;
        this.pageable = pageable;
    }

    public Page<E> getPagination() {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<E> countRoot = countQuery.from(criteriaQuery.getResultType());

        Long total = getTotal(countQuery, countRoot);
        setOrderBy(countRoot);

        return new PageImpl<E>(getList(), pageable, total);
    }

    private List<E> getList() {
        return entityManager
            .createQuery(criteriaQuery)
            .setFirstResult((int) pageable.getOffset())
            .setMaxResults(pageable.getPageSize())
            .getResultList();
    }

    private Long getTotal(CriteriaQuery<Long> countQuery, Root<E> countRoot) {
        countQuery.select(criteriaBuilder.count(countRoot));
        TypedQuery<Long> query = entityManager.createQuery(countQuery);
        countQuery.where(criteriaQuery.getRestriction());

        return query.getSingleResult();
    }

    private void setOrderBy(Root<E> countRoot) {
        String data[] = pageable.getSort().toString().split(":");
        Path<Object> param = countRoot.get(data[0].trim());
        Order order = criteriaBuilder.asc(param);

        if (data[1].trim().equalsIgnoreCase("DESC")) {
            order = criteriaBuilder.desc(param);
        }

        criteriaQuery.orderBy(order);
    }
}
