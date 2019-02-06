package com.magical.oms.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaginationHelper<E> {
    private static Logger logger = LoggerFactory.getLogger(PaginationHelper.class);
    public Page<E> fetchPage(
            final JdbcTemplate jt,
            final String sqlCountRows,
            final String sqlFetchRows,
            final Object args[],
            final int pageNo,
            final int pageSize,
            final RowMapper<E> rowMapper) {

        // determine how many rows are available
        final int rowCount = jt.queryForObject(sqlCountRows, Integer.class);

        // calculate the number of pages
        int pageCount = rowCount / pageSize;
        if (rowCount > pageSize * pageCount) {
            pageCount++;
        }
        logger.info("rowCount="+rowCount+" pageCount="+pageCount+" pageSize="+pageSize);
        // create the page object
        final Page<E> page = new Page<E>();
        page.setPageNumber(pageNo);
        page.setPagesAvailable(pageCount);

        // fetch a single page of results
        final int startRow = (pageNo - 1) * pageSize;

        logger.info("startRow="+startRow);
        jt.query(
                sqlFetchRows,
                args,
                (ResultSet rs) -> {
                        final List pageItems = page.getPageItems();
                        int currentRow = 0;

                        while (rs.next() && currentRow <= startRow + pageSize) {
                            logger.info("test pagination ok!");
                            if (currentRow >= startRow) {
                                pageItems.add(rowMapper.mapRow(rs, currentRow));
                            }
                            currentRow++;
                        }
                        return page;
                    }
                );
        return page;
    }

}
