package cn.aceo.product.template;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetHandler<T> {
     //************
     T handdler(ResultSet rs) throws SQLException;
     //****************
}
