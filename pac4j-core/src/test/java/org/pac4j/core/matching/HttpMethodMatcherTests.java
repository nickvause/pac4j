package org.pac4j.core.matching;

import org.junit.Test;
import org.pac4j.core.context.HttpConstants;
import org.pac4j.core.context.MockWebContext;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.exception.TechnicalException;
import org.pac4j.core.util.TestsConstants;
import org.pac4j.core.util.TestsHelper;

import static org.junit.Assert.*;
import static org.pac4j.core.context.HttpConstants.*;

/**
 * Tests {@link HttpMethodMatcher}.
 *
 * @author Jerome Leleu
 * @since 1.9.3
 */
public final class HttpMethodMatcherTests implements TestsConstants {

    @Test
    public void testNullMethods() throws HttpAction {
        final HttpMethodMatcher matcher = new HttpMethodMatcher();
        TestsHelper.expectException(() -> matcher.matches(MockWebContext.create()), TechnicalException.class, "methods cannot be null");
    }

    @Test
    public void testBadMethod() throws HttpAction {
        final HttpMethodMatcher matcher = new HttpMethodMatcher(HTTP_METHOD.GET);
        final MockWebContext context = MockWebContext.create().setRequestMethod(HttpConstants.HTTP_METHOD.POST);
        assertFalse(matcher.matches(context));
    }

    @Test
    public void testGoodMethod() throws HttpAction {
        final HttpMethodMatcher matcher = new HttpMethodMatcher(HTTP_METHOD.POST);
        final MockWebContext context = MockWebContext.create().setRequestMethod(HttpConstants.HTTP_METHOD.POST);
        assertTrue(matcher.matches(context));
    }

    @Test
    public void testBadMethod2() throws HttpAction {
        final HttpMethodMatcher matcher = new HttpMethodMatcher(HTTP_METHOD.GET, HTTP_METHOD.PUT);
        final MockWebContext context = MockWebContext.create().setRequestMethod(HttpConstants.HTTP_METHOD.POST);
        assertFalse(matcher.matches(context));
    }

    @Test
    public void testGoodMethod2() throws HttpAction {
        final HttpMethodMatcher matcher = new HttpMethodMatcher(HTTP_METHOD.DELETE, HTTP_METHOD.POST);
        final MockWebContext context = MockWebContext.create().setRequestMethod(HttpConstants.HTTP_METHOD.POST);
        assertTrue(matcher.matches(context));
    }
}
