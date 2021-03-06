package net.sf.jsqlparser.statement.create.table;

import java.util.List;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;
import net.sf.jsqlparser.statement.select.PlainSelect;

/**
 * A "CREATE TABLE" statement
 */
public class CreateTable implements Statement {

    private Table table;
    private List<String> tableOptionsStrings;
    private List<ColumnDefinition> columnDefinitions;
    private List<Index> indexes;

    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    /**
     * The name of the table to be created
     *
     * @return The {@link Table} identifier for the table being created.
     */
    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * A list of {@link ColumnDefinition}s of this table.
     *
     * @return The column definitions.
     */
    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    public void setColumnDefinitions(List<ColumnDefinition> list) {
        columnDefinitions = list;
    }

    /**
     * A list of options (as simple strings) of this table definition, as ("TYPE", "=", "MYISAM") 
     *
     * @return The table options as simple strings
     */
    public List<String> getTableOptionsStrings() {
        return tableOptionsStrings;
    }

    public void setTableOptionsStrings(List<String> list) {
        tableOptionsStrings = list;
    }

    /**
     * A list of {@link Index}es (for example "PRIMARY KEY") of this table.<br>
     * Indexes created with column definitions (as in mycol INT PRIMARY KEY) are not inserted into this list.  
     *
     * @return A list of indexes
     */
    public List<Index> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Index> list) {
        indexes = list;
    }

    public String toString() {
        String sql = "";

        sql = "CREATE TABLE " + table + " (";

        sql += PlainSelect.getStringList(columnDefinitions, true, false);
        if (indexes != null && indexes.size() != 0) {
            sql += ", ";
            sql += PlainSelect.getStringList(indexes);
        }
        sql += ") ";
        sql += PlainSelect.getStringList(tableOptionsStrings, false, false);

        return sql;
    }
}