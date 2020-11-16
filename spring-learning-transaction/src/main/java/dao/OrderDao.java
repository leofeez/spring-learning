package dao;

import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class OrderDao {

    private static final ThreadLocal<SimpleDateFormat> FORMATTER_CACHE = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmssSSS"));

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createOrder() {
        String orderCode = FORMATTER_CACHE.get().format(new Date());
        String sql = "INSERT INTO `order`( `order_code`, `create_time`) VALUES (?, ?);";
        jdbcTemplate.update(sql, orderCode, new Date());
    }

    public boolean updateOrder(Integer orderId) {
        Order order = getOrder(orderId);
        Integer oldVersion = order.getVersion();
        Integer newVersion = oldVersion + 1;

        Order order2 = getOrder(orderId);
        System.out.println("order2 = " + order2.toString());
        String sql = "update `order` set `version` = ? where `order_id` = ? and `version` = ?;";
        int update = jdbcTemplate.update(sql, newVersion, orderId, oldVersion);
        System.out.println("update = " + update);
        return update > 0;
    }

    /**
     * 根据 CAS 即乐观锁更新
     *
     * @param orderId
     * @return
     */
    public boolean updateOrderWithCASLock(Integer orderId) {
        Order order = getOrderWithXLock(orderId);
        Integer oldVersion = order.getVersion();
        Integer newVersion = oldVersion + 1;
        String sql = "update `order` set `version` = ? where `order_id` = ?;";
        int update = jdbcTemplate.update(sql, newVersion, orderId);
        System.out.println("update = " + update);
        return update > 0;
    }

    public Order getOrder(Integer orderId) {
       String sql = "select * from `order` where `order_id` = ?;";
        BeanPropertyRowMapper<Order> rowMapper = BeanPropertyRowMapper.newInstance(Order.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, orderId);
    }

    public Order getOrderWithXLock(Integer orderId) {
        String sql = "select * from `order` where `order_id` = ? for update;";
        BeanPropertyRowMapper<Order> rowMapper = BeanPropertyRowMapper.newInstance(Order.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, orderId);
    }

    public Integer count() {
        String sql = "SELECT COUNT(1) FROM `order`;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


}
