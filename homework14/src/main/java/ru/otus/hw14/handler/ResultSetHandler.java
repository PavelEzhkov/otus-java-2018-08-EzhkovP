package ru.otus.hw14.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetHandler<T> {

    T handle(ResultSet rs) throws SQLException;

}