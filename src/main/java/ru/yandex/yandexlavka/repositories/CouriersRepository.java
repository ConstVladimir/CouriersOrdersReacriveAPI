package ru.yandex.yandexlavka.repositories;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcOperations;
import ru.yandex.yandexlavka.model.courier.CreateCourierDto;

import java.sql.Types;
import java.util.Arrays;

@Repository
public class CouriersRepository {
    private JdbcOperations jdbcOperations;

    PreparedStatementCreatorFactory pscfCreateCourier = new PreparedStatementCreatorFactory(
            "INSERT INTO couriers VALUES (DEFAULT, ?, ?, ?);",
            Types.VARCHAR, Types.OTHER, Types.OTHER);

    CouriersRepository(JdbcOperations jdbcOperations){this.jdbcOperations=jdbcOperations;}

    public void createCourier (CreateCourierDto courierDto){
        String str = "{{\"09:00\",\"12:00\"},{\"13:00\",\"18:00\"}}";
        PreparedStatementCreator psc = pscfCreateCourier.newPreparedStatementCreator(
                Arrays.asList(
                        courierDto.getCourier_type(),
                        "{1,2,3}",
                        str
                ));

        System.out.println(psc.toString());
        jdbcOperations.update(psc);
    }
}
