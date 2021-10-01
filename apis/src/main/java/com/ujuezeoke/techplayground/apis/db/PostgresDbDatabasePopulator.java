package com.ujuezeoke.techplayground.apis.db;

import com.ujuezeoke.techplayground.apis.model.Member;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultProducer;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.postgresql.jdbc2.optional.PoolingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.function.Supplier;

@Component
public class PostgresDbDatabasePopulator implements DatabasePopulator {
    private final Jdbi jdbi;

    @Autowired
    public PostgresDbDatabasePopulator(@Value("${postgresdb.uri}") String connectionString) {
        jdbi = Jdbi.create(connectionString)
                .installPlugin(new PostgresPlugin())
                .installPlugin(new SqlObjectPlugin());
    }

    @Override
    public void saveBatch(Collection<Member> members) {
        jdbi.onDemand(Pp.class).saveBatch(members);
    }

    @PostConstruct
    @Override
    public void create() {
        jdbi.open()
                .createScript("CREATE TABLE IF NOT EXISTS members (" +
                        "name varchar," +
                        "age int," +
                        "trouble_maker boolean)")
                .execute();

    }
}
