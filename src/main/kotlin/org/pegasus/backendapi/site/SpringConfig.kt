package org.pegasus.backendapi.site

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring6.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode

fun siteInitializer(
    applicationContext: GenericApplicationContext
): ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean<SpringTemplateEngine> {
        val templateResolver = SpringResourceTemplateResolver().apply {
            setApplicationContext(applicationContext)
            prefix = "classpath:templates/"
            suffix = ".html"
            templateMode = TemplateMode.HTML
            isCacheable = true
        }

        SpringTemplateEngine().apply {
            setTemplateResolver(templateResolver)
            enableSpringELCompiler = true
        }
    }

    bean<ThymeleafViewResolver> {
        val templateEngine = ref<SpringTemplateEngine>()

        ThymeleafViewResolver().apply {
            setTemplateEngine(templateEngine)
            order = 1
            viewNames = arrayOf(".html", ".xhtml")
        }
    }
}
