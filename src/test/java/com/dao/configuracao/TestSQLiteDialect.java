package com.dao.configuracao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TestSQLiteDialect {

    @Test
    void testSupportsIdentityColumns() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue(dialeto.supportsIdentityColumns());
    }

    @Test
    void testHasDataTypeInIdentityColumn() {
        SQLiteDialect dialeto = new SQLiteDialect();
        assertFalse(dialeto.hasDataTypeInIdentityColumn());
    }

    @Test
    void testGetIdentityColumnString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertEquals("integer", dialeto.getIdentityColumnString());
    }

    @Test
    void testGetIdentitySelectString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertEquals("select last_insert_rowid()", dialeto.getIdentitySelectString());
    }

    @Test
    void testSupportsTemporaryTables() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue(dialeto.supportsTemporaryTables());
    }

    @Test
    void testGetCreateTemporaryTableString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertEquals("create temporary table if not exists" ,dialeto.getCreateTemporaryTableString());
    }

    @Test
    void testDropTemporaryTableAfterUse() {
        SQLiteDialect dialeto = new SQLiteDialect();
        assertFalse(dialeto.dropTemporaryTableAfterUse());
    }

    @Test
    void testSupportsCurrentTimestampSelection() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue(dialeto.supportsCurrentTimestampSelection());
    }

    @Test
    void testIsCurrentTimestampSelectStringCallable() {
        SQLiteDialect dialeto = new SQLiteDialect();
        
        assertFalse(dialeto.isCurrentTimestampSelectStringCallable());
    }

    @Test
    void testGetCurrentTimestampSelectString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertEquals("select current_timestamp", dialeto.getCurrentTimestampSelectString());
    }

    @Test
    void testSupportsUnionAll() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue(dialeto.supportsUnionAll());
    }

    @Test
    void testHasAlterTable() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertFalse(dialeto.hasAlterTable());
    }

    @Test
    void testDropConstraints() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertFalse(dialeto.dropConstraints());
    }

    @Test
    void testGetAddColumnString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertEquals("add column", dialeto.getAddColumnString());
    }

    @Test
    void testGetForUpdateString() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertEquals("", dialeto.getForUpdateString());
    }

    @Test
    void testSupportsOuterJoinForUpdate() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertFalse(dialeto.supportsOuterJoinForUpdate());
    }

    @Test
    void testSupportsIfExistsBeforeTableName() {
        SQLiteDialect dialeto = new SQLiteDialect();

        assertTrue(dialeto.supportsIfExistsBeforeTableName());
    }

    @Test
    void testSpportsCascadeDelete() {
        SQLiteDialect dialeto = new SQLiteDialect();
        assertFalse(dialeto.supportsCascadeDelete());
    }
}
