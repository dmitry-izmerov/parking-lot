package ru.demi.parkinglot.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import springfox.documentation.builders.ApiDescriptionBuilder;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Operation;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Adds login/logout paths for swagger.
 */
public class AuthOperations extends ApiListingScanner {

    private static final Ordering<Operation> ordering = Ordering.from(Comparator.comparing(Operation::getUniqueId));
    private static final String AUTHENTICATION_GROUP_NAME = "Authentication";

    @Autowired
    private TypeResolver typeResolver;

    @Autowired
    public AuthOperations(
        ApiDescriptionReader apiDescriptionReader,
        ApiModelReader apiModelReader,
        DocumentationPluginsManager pluginsManager
    ) {
        super(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context) {
        final Multimap<String, ApiListing> def = super.scan(context);
        final List<ApiDescription> apis = new LinkedList<>();

        final List<Operation> loginOperations = new ArrayList<>();
        loginOperations.add(
            new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .uniqueId("login")
                .parameters(
                    Arrays.asList(
                        new ParameterBuilder()
                            .name("username")
                            .required(true)
                            .description("username parameter")
                            .parameterType("query")
                            .type(typeResolver.resolve(String.class))
                            .modelRef(new ModelRef("string"))
                            .build(),
                        new ParameterBuilder()
                            .name("password")
                            .required(true)
                            .description("password parameter")
                            .parameterType("query")
                            .type(typeResolver.resolve(String.class))
                            .modelRef(new ModelRef("string"))
                            .build()
                    )
                )
                .summary("Log in")
                .responseMessages(getDefaultResponseMessages())
                .build()
        );

        final List<Operation> logoutOperations = new ArrayList<>();
        logoutOperations.add(
            new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .uniqueId("logout")
                .summary("Log out")
                .responseMessages(getDefaultResponseMessages())
                .build()
        );

        apis.add(
            new ApiDescriptionBuilder(ordering)
                .groupName(AUTHENTICATION_GROUP_NAME)
                .path("/login")
                .description("login operation")
                .operations(loginOperations)
                .hidden(false)
                .build()
        );
        apis.add(
            new ApiDescriptionBuilder(ordering)
                .groupName(AUTHENTICATION_GROUP_NAME)
                .path("/logout")
                .description("logout operation")
                .operations(logoutOperations)
                .hidden(false)
                .build()
        );

        def.put(AUTHENTICATION_GROUP_NAME, new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering())
            .apis(apis)
            .description(AUTHENTICATION_GROUP_NAME)
            .build()
        );

        return def;
    }

    private static Set<ResponseMessage> getDefaultResponseMessages() {
        return Sets.newHashSet(Arrays.asList(new ResponseMessageBuilder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .build()
        ));
    }
}
