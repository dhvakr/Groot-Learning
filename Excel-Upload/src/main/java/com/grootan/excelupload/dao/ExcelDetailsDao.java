package com.grootan.excelupload.dao;

import com.grootan.excelupload.domain.EntityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ExcelDetailsDao {
    protected NamedParameterJdbcTemplate jdbcTemplate;

    private static final String COllEGE_DETAILS = "";

    private static final String INSERT = "INSERT INTO excel_upload.college_details(collegeID, college_name, college_type) " +
            "values (:CollegeID,:CollegeName,:CollegeType)";

    public List<EntityClass> findAll() {
        return jdbcTemplate.query(COllEGE_DETAILS, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(EntityClass.class));
    }

    public void save(EntityClass candidate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();

        params.put("CollegeID", candidate.getCollegeID());
        params.put("CollegeName", candidate.getCollegeName());
        params.put("CollegeType", candidate.getCollegeType());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValues(params);
        jdbcTemplate.update(INSERT, sqlParameterSource, keyHolder, new String[]{"id"});
    }

    @Autowired
    public void setDataSource(@Qualifier("dataSource") DataSource ds) {
        jdbcTemplate = new NamedParameterJdbcTemplate(ds);
    }
}
