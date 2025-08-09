package org.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "智慧物业管理系统 API文档", version = "1.0", description = "毕业设计后端接口文档")
)
public class SwaggerConfig {
    // springdoc-openapi 自动配置，无需额外代码即可访问Swagger UI
}
