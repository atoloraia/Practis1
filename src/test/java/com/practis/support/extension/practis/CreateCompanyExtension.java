package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static java.lang.String.format;

import com.practis.rest.dto.admin.RestCompanyResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateCompanyExtension
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private final List<RestCompanyResponse> companyToToRemove = new ArrayList<>();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var annotation =
                context.getTestMethod().orElseThrow().getAnnotation(CompanyExtension.class);
        IntStream.range(0, annotation.count())
                .forEach(
                        idx -> {
                            final var input = getNewCompanyInput();
                            input.setSubdomain(String.format(input.getSubdomain(), timestamp()));
                            input.setName(String.format(input.getName(), timestamp()));

                            final var company = practisApi().createCompany(input);
                            companyToToRemove.add(company);
                        });
    }

    @Override
    public void afterEach(final ExtensionContext extensionContext) throws Exception {
        companyToToRemove.forEach(company -> practisApi().deactivateCompany(company.getName()));
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getParameterizedType()
                .getTypeName()
                .equals(format("java.util.List<%s>", RestCompanyResponse.class.getName()));
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return companyToToRemove;
    }
}
