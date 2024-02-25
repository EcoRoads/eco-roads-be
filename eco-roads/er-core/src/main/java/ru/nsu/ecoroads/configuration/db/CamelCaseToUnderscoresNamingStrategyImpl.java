package ru.nsu.ecoroads.configuration.db;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CamelCaseToUnderscoresNamingStrategyImpl extends CamelCaseToUnderscoresNamingStrategy {

    private static final String ENTITY_POSTFIX = "Entity";

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {

        Identifier identifier = super.toPhysicalTableName(
                Identifier.toIdentifier(name.getText().replace(ENTITY_POSTFIX, "")), jdbcEnvironment
        );
        return Identifier.toIdentifier(identifier.getText());
    }

}