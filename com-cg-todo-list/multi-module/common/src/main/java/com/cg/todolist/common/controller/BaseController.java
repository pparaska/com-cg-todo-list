package com.cg.todolist.common.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;
import java.util.function.Function;


public class BaseController {

    protected <T> T withRequestAttributeContext(HttpServletRequest request, Callable<T> runnable)//Callable<T>--A task that returns a result and may throw an exception. 
    																
    {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        //RequestContextHolder--expose the current web request in the form of a thread-bound RequestAttributes object
        //setRequestAttributes--Bind the given RequestAttributes to the current thread, not exposing it as inheritable for child threads
        try {
            return runnable.call();//Computes a result, or throws an exception if unable to do so
            //  Returns: 
            //	computed result 
            //	Throws: 
            //	Exception - if unable to compute a result 
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

}
