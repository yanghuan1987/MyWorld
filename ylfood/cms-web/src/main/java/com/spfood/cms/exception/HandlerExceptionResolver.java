package com.spfood.cms.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.alibaba.dubbo.rpc.RpcException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spfood.cms.utils.JsonResult;
import com.spfood.common.mto.MessageTransferObject;
import com.spfood.kernel.exception.BizException;
import com.spfood.kernel.exception.DomainValidationException;

/**
 * exception utils
 * @author fengjunchao 2017-01-23
 *
 */
@Component
public class HandlerExceptionResolver extends DefaultHandlerExceptionResolver {
	private static final Logger log = LoggerFactory.getLogger(HandlerExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {

        try {
            if (ex instanceof BizException) {
                return handlerBizException(ex, response);
            } else if (ex instanceof DomainValidationException) {
                return handlerDomainValidationException(ex, response);
            } else if(ex instanceof RpcException){
                return handlerException(ex, response);
            } else if(ex instanceof UnauthorizedException){
            	return null;
            } else if(ex instanceof UnauthenticatedException){
            	return null;
            } else {
                return handlerException(ex, response);
            }
        } catch (Exception handlerException) {
            log.error("Handling of [" + ex.getClass().getName() + "] resulted in Exception",
                    handlerException);
        }

        return null;
    }

    protected ModelAndView handlerBizException(Exception ex, HttpServletResponse response)
            throws IOException {
    	ObjectMapper mapper = new ObjectMapper();
        String retStr = mapper.writeValueAsString(new JsonResult(MessageTransferObject.ERROR,ex.getMessage(),null));
        log.error("the exception is :",ex);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(retStr);

        return new ModelAndView();
    }

    protected ModelAndView handlerDomainValidationException(Exception ex, HttpServletResponse response)
            throws IOException {
    	ObjectMapper mapper = new ObjectMapper();
        String retStr = mapper.writeValueAsString(new JsonResult(MessageTransferObject.ERROR,ex.getMessage(),null));
        log.error("the exception is :",ex);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(retStr);

        return new ModelAndView();
    }

	protected ModelAndView handlerException(Exception ex, HttpServletResponse response)
            throws IOException {
		ObjectMapper mapper = new ObjectMapper();
        String retStr = mapper.writeValueAsString(new JsonResult(MessageTransferObject.ERROR,ex.getMessage(),null));
        log.error("the exception is :",ex);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(retStr);
        return new ModelAndView();
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
