//import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
//import graphql.schema.Coercing
//import graphql.schema.GraphQLScalarType
//
//import java.util.*
//import kotlin.reflect.KType
//
//class CustomSchemaGeneratorHooks : SchemaGeneratorHooks {
//
//    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier as? KClass<*>) {
//        UUID::class -> graphqlUUIDType
//        else -> null
//    }
//}
//
//val graphqlUUIDType = GraphQLScalarType.newScalar()
//    .name("UUID")
//    .description("A type representing a formatted java.util.UUID")
//    .coercing(UUIDCoercing)
//    .build()
//
//object UUIDCoercing : Coercing<UUID, String> {
//    override fun parseValue(input: Any, graphQLContext: GraphQLContext, locale: Locale): UUID = runCatching {
//        UUID.fromString(serialize(input, graphQLContext, locale))
//    }.getOrElse {
//        throw CoercingParseValueException("Expected valid UUID but was $input")
//    }
//
//    override fun parseLiteral(input: Value<*>, variables: CoercedVariables, graphQLContext: GraphQLContext, locale: Locale): UUID {
//        val uuidString = (input as? StringValue)?.value
//        return runCatching {
//            UUID.fromString(uuidString)
//        }.getOrElse {
//            throw CoercingParseLiteralException("Expected valid UUID literal but was $uuidString")
//        }
//    }
//
//    override fun serialize(dataFetcherResult: Any, graphQLContext: GraphQLContext, locale: Locale): String = runCatching {
//        dataFetcherResult.toString()
//    }.getOrElse {
//        throw CoercingSerializeException("Data fetcher result $dataFetcherResult cannot be serialized to a String")
//    }
//}