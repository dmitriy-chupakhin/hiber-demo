package ru.dchupakhin.hiberdemo.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dchupakhin.hiberdemo.entity.Order;
import ru.dchupakhin.hiberdemo.repository.OrderRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private EntityManager manager;

    @PostMapping("/")
    public List<Order> getOrderList(@RequestParam List<Long> ids, @RequestParam int rowcount) {
        // способ 1, используя NamedNativeQuery
//        TypedQuery<Order> query = manager.createNamedQuery("findAllByClientIdsCount", Order.class);
//        query.setParameter("ids", ids)
//        .setParameter("rowcount", rowcount);
//        return query.getResultList();

        // способ 2, используя репозиторий
        return repository.findAllByClientIdIn(ids, rowcount).orElse(new ArrayList<>());

        // способ 3, используя Criteria
//        List<Order> resultList = new ArrayList<>();
//        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
//        CriteriaQuery<Order> criteria = criteriaBuilder.createQuery(Order.class);
//        Root<Order> root = criteria.from(Order.class);
//        for (Long id : ids) {
//            List<Order> list = manager.createQuery(criteria
//                    .select(root)
//                    .where(root.get("clientId").in(id))).setMaxResults(rowcount).getResultList();
//            resultList.addAll(list);
//        }
//        return resultList;
    }
}
