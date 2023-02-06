package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import com.practis.rest.dto.company.RestCreateDraftUserRequest;
import com.practis.rest.dto.company.RestStagingResponse;
import com.practis.rest.dto.user.DraftUserRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateDraftExtension
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private final List<RestStagingResponse> draftsToRemove = new ArrayList<>();
    private final AtomicInteger integer = new AtomicInteger();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var annotation =
                context.getTestMethod().orElseThrow().getAnnotation(DraftExtension.class);
        IntStream.range(0, annotation.count())
                .forEach(
                        idx -> {
                            final var draftUserRequest =
                                    RestCreateDraftUserRequest.builder()
                                            .name(
                                                    format(
                                                            "test-%s-%s",
                                                            integer.addAndGet(1), timestamp()))
                                            .users(buildUsers(annotation.usersPerDraft(), 1))
                                            .build();
                            final var draftUser = practisApi().createDraftUser(draftUserRequest);
                            draftsToRemove.add(draftUser);
                        });
    }

    private List<DraftUserRequest> buildUsers(final int draftIdx, final int size) {
        return IntStream.range(0, size)
                .mapToObj(
                        idx ->
                                DraftUserRequest.builder()
                                        .email(
                                                format(
                                                        "test-%s-%s-%s@email.com",
                                                        draftIdx, idx, timestamp()))
                                        .firstName("User")
                                        .lastName("Test")
                                        .roleId(7)
                                        .build())
                .collect(toList());
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        draftsToRemove.forEach(draft -> practisApi().deleteDraftUser(draft.getName()));
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getParameterizedType()
                .getTypeName()
                .equals(format("java.util.List<%s>", RestStagingResponse.class.getName()));
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return draftsToRemove;
    }
}
