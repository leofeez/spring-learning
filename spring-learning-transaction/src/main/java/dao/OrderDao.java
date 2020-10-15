package dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Repository
public class OrderDao {

    ThreadLocal<SimpleDateFormat> FORMATTER_CACHE = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmssSSS"));

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
}
