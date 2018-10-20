package com.sxw.wrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    private final String contentType;

    public TransRequestWrapper(HttpServletRequest request,String bodyStr, String contentType,String key,boolean isForm) {
        super(request);
        this.contentType = contentType;
        if(isForm) {
            bodyStr = key + "=" + bodyStr;
        }
        this.body = bodyStr.getBytes(StandardCharsets.UTF_8);
        log.info("ModifyHttpServletRequestWrapper缓存的字符流：{}", bodyStr);
    }

    @Override
    public long getContentLengthLong() {
        return this.body.length;
    }

    @Override
    public int getContentLength() {
        return this.body.length;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        if (null != name && name.equals("content-type")) {
            return new Enumeration<String>() {
                private boolean hasGetted = false;

                @Override
                public String nextElement() {
                    if (hasGetted) {
                        throw new NoSuchElementException();
                    } else {
                        hasGetted = true;
                        return contentType;
                    }
                }

                @Override
                public boolean hasMoreElements() {
                    return !hasGetted;
                }
            };
        }
        return super.getHeaders(name);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
}