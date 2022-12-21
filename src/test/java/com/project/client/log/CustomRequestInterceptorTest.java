package com.project.client.log;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CustomRequestInterceptor.class})
@ExtendWith(SpringExtension.class)
class CustomRequestInterceptorTest {
    @Autowired
    private CustomRequestInterceptor customRequestInterceptor;

    /**
     * Method under test: {@link CustomRequestInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertTrue(customRequestInterceptor.preHandle(request, new Response(), "Handler"));
    }


    /**
     * Method under test: {@link CustomRequestInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle2() {
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        doNothing().when(defaultMultipartHttpServletRequest).setAttribute(any(), any());
        when(defaultMultipartHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("Str"));
        assertTrue(customRequestInterceptor.preHandle(defaultMultipartHttpServletRequest, new Response(), "Handler"));
        verify(defaultMultipartHttpServletRequest).getRequestURL();
        verify(defaultMultipartHttpServletRequest).setAttribute(any(), any());
    }

}

