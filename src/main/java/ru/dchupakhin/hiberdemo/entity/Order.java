package ru.dchupakhin.hiberdemo.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@NamedNativeQuery(name = "findAllByClientIdsCount",
        query = "select client_id, id, product_id from" +
                " (select *, row_number() over (partition by client_id) as i from test.orders where client_id in :ids) as o " +
                "where i <= :rowcount ;",
        resultClass = Order.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long clientId;

    private long productId;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
