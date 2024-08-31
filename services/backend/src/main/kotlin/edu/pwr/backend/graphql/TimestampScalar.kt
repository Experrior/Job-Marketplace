package edu.pwr.backend.graphql

import graphql.schema.GraphQLScalarType
import graphql.schema.Coercing
import graphql.language.Value

import graphql.GraphQLContext
import graphql.execution.CoercedVariables
import graphql.language.StringValue
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import java.sql.Timestamp
import java.util.Locale

val TimestampScalar: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("Timestamp")
    .description(
        """
        |Represents my value class as a String value.
        |""".trimMargin()
    )
    .coercing(Timestamp)
    .build()

object Timestamp : Coercing<Timestamp, String> {
    override fun parseValue(input: Any, graphQLContext: GraphQLContext, locale: Locale): Timestamp {
        return try {
            Timestamp.valueOf(input as String)
        } catch (e: Exception) {
            throw CoercingParseValueException("Invalid timestamp format: $input")
        }
    }
    override fun parseLiteral(input: Value<*>, variables: CoercedVariables, graphQLContext: GraphQLContext, locale: Locale): Timestamp {
        when (input) {
            is StringValue -> {
                return try {
                    Timestamp.valueOf(input.value)
                } catch (e: Exception) {
                    throw CoercingParseLiteralException("Invalid timestamp format: ${input.value}")
                }
            }
            else -> {
                throw CoercingParseLiteralException("Invalid timestamp literal: $input")
            }
        }
    }

    override fun serialize(dataFetcherResult: Any, graphQLContext: GraphQLContext, locale: Locale): String {
        return try {
            (dataFetcherResult as Timestamp).toString()
        } catch (e: ClassCastException) {
            throw CoercingSerializeException("Data fetcher result must be a edu.pwr.backend.graphql.Timestamp: $dataFetcherResult")
        }
    }
}
