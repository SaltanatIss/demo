package com.docs.demo.config

import com.docs.demo.api.model.forms.CompanyForm
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.oas.models.media.Schema
import org.springdoc.core.SpringDocUtils
import org.springdoc.core.customizers.OpenApiCustomiser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal
import java.time.LocalDate
import javax.annotation.PostConstruct


@Configuration
class OpenApiConfiguration {

    @PostConstruct
    fun setupTypedValue() {
        //SpringDocUtils.getConfig().replaceWithClass(CompanyForm::class.java, CompanyFormOpenApiSchema::class.java)
    }

    @Bean
    fun viewDtoCustomizer(): OpenApiCustomiser {
        return OpenApiCustomiser { openApi: OpenAPI ->
            openApi
                    .components.schemas.values.stream()
                    .filter { schema: Schema<*> -> schema.name != null && schema.name.endsWith("Dto") }
                    .forEach { schema: Schema<*> ->
                        schema.properties.remove("parameters")
                        schema.additionalProperties(ObjectSchema().`$ref`(TYPED_VALUE_SCHEMA_NAME))
                    }
        }
    }

    @Bean
    fun apiInfoCustomizer(): OpenApiCustomiser {
        return OpenApiCustomiser { openApi: OpenAPI ->
            openApi.info = Info()
                    .title("Company API")
                    .description("Api to manage companies")
                    .version("v1")
        }
    }

    @io.swagger.v3.oas.annotations.media.Schema(name = TYPED_VALUE_SCHEMA_NAME,
            anyOf = [MultipartFile::class],
            nullable = true,
            example = "file")
    private class CompanyFormOpenApiSchema

    companion object {
        private const val TYPED_VALUE_SCHEMA_NAME = "CompanyForm"
    }
}