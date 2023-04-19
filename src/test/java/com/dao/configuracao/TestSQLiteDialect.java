package com.dao.configuracao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TestSQLiteDialect {

    @Test
    public void testSupportsIdentityColumns() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue(dialeto.supportsIdentityColumns());
    }

    @Test
    public void testHasDataTypeInIdentityColumn() {
        SQLiteDialect dialeto = new SQLiteDialect();
        assertFalse(dialeto.hasDataTypeInIdentityColumn());
    }

    @Test
    public void testGetIdentityColumnString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue("integer".equals(dialeto.getIdentityColumnString()));
    }

    @Test
    public void testGetIdentitySelectString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue("select last_insert_rowid()".equals(dialeto.getIdentitySelectString()));
    }

    @Test
    public void testSupportsTemporaryTables() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue(dialeto.supportsTemporaryTables());
    }

    @Test
    public void testGetCreateTemporaryTableString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue("create temporary table if not exists".equals(dialeto.getCreateTemporaryTableString()));
    }

    @Test
    public void testDropTemporaryTableAfterUse() {
        SQLiteDialect dialeto = new SQLiteDialect();
        assertFalse(dialeto.dropTemporaryTableAfterUse());
    }

    @Test
    public void testSupportsCurrentTimestampSelection() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue(dialeto.supportsCurrentTimestampSelection());
    }

    @Test
    public void testIsCurrentTimestampSelectStringCallable() {
        SQLiteDialect dialeto = new SQLiteDialect();
        assertFalse(dialeto.isCurrentTimestampSelectStringCallable());
    }

    @Test
    public void testGetCurrentTimestampSelectString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue("select current_timestamp".equals(dialeto.getCurrentTimestampSelectString()));
    }

    @Test
    public void testSupportsUnionAll() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue(dialeto.supportsUnionAll());
    }

    @Test
    public void testHasAlterTable() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertFalse(dialeto.hasAlterTable());
    }

    @Test
    public void testDropConstraints() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertFalse(dialeto.dropConstraints());
    }

    @Test
    public void testGetAddColumnString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue("add column".equals(dialeto.getAddColumnString()));
    }

    @Test
    public void testGetForUpdateString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue("".equals(dialeto.getForUpdateString()));
    }

    @Test
    public void testSupportsOuterJoinForUpdate() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertFalse(dialeto.supportsOuterJoinForUpdate());
    }

    @Test
    public void testSupportsIfExistsBeforeTableName() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue(dialeto.supportsIfExistsBeforeTableName());
    }

    @Test
    public void testSpportsCascadeDelete() {
        SQLiteDialect dialeto = new SQLiteDialect();
        assertFalse(dialeto.supportsCascadeDelete());
    }
}
