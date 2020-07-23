package ru.dchupakhin.hiberdemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dchupakhin.hiberdemo.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "select client_id, id, product_id from " +
            "(select *, row_number() over (partition by client_id) as i from test.orders where client_id in :ids) as o " +
            "where i <= :rowcount ;",
            nativeQuery = true)
    Optional<List<Order>> findAllByClientIdIn(@Param("ids") Iterable<Long> ids, @Param("rowcount") int count);

}
