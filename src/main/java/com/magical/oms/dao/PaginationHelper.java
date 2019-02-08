package com.magical.oms.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

/**
 * Вспомогательный класс для пагинации
 *
 * @param <E>
 */
public class PaginationHelper<E> {
    public Page<E> fetchPage(
            final JdbcTemplate jt,
            final String sqlCountRows,
            final String sqlFetchRows,
            final Object args[],
            final int pageNo,
            final int pageSize,
            final RowMapper<E> rowMapper) {

        // получение количества записей
        final int rowCount = jt.queryForObject(sqlCountRows, Integer.class);

        // вычисление количества страниц пагинации
        int pageCount = rowCount / pageSize;
        if (rowCount > pageSize * pageCount) {
            pageCount++;
        }
        // создание страницы пагинации
        final Page<E> page = new Page<E>();
        page.setPageNumber(pageNo);
        page.setPagesAvailable(pageCount);

        // получение первой записи для страницы пагинации
        final int startRow = (pageNo - 1) * pageSize;
        jt.query(
                sqlFetchRows,
                args,
                (ResultSet rs) -> {
                    final List pageItems = page.getPageItems();
                    int currentRow = 0;
                    while (rs.next() && currentRow <= startRow + pageSize) {
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
