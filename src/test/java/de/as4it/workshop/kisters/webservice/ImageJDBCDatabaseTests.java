package de.as4it.workshop.kisters.webservice;

import de.as4it.workshop.kisters.webservice.domain.Image;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ActiveProfiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("init")
public class ImageJDBCDatabaseTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Logger log = LoggerFactory.getLogger(ImageJDBCDatabaseTests.class);

    @Test
    public void testDatabaseConnection() {
        try {
            assertThat(jdbcTemplate.getDataSource().getConnection().isValid(1000)).isTrue();
        } catch (SQLException e) {
            assert (false);
        }
    }

    @Test
    public void testImageDataInitilizerCountImages() {
        ResultSetExtractor<Integer> rse = new ResultSetExtractor() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
                return 0;
            }
        };
        Integer list = jdbcTemplate.query("SELECT count(*) from image", rse);
        assertThat(list).isGreaterThan(0);
    }

    @Test
    public void testImageDataInitilizerAccessImages() {
        RowMapper<Image> rse = new RowMapper<Image>() {
            @Override
            public Image mapRow(ResultSet resultSet, int i) throws SQLException {
                    Image image = new Image();
                    image.setName(resultSet.getString("name"));
                    return image;
            }
        };
        List<Image> list = jdbcTemplate.query("SELECT * from image", rse);
        assertThat(list.size()).isGreaterThan(0);
        log.info("Listsize {}", list.size());
        list.stream().forEach(image -> log.info("Imagename: {}", image.getName()));
    }

    @Test
    public void testImageDataInitilizerWithNamesParameter() {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", "Image");
        List list = namedParameterJdbcTemplate.queryForList("SELECT * from image where name = :name", namedParameters);
        assertThat(list).isNotEmpty();
    }
}
