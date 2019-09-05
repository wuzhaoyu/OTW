package com.wzy;

import com.wzy.common.exception.BussinessException;
import com.wzy.common.exception.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 类功能说明:
 * 类修改者	创建日期2019/2/23
 * 修改说明
 * <p>Title: WholeExceptionHandle.java</p>
 *
 * @author Wzy
 * @version V1.0
 **/
@ControllerAdvice
public class WholeExceptionHandle {

    private static final Logger log = LoggerFactory.getLogger(WholeExceptionHandle.class);

    /**
     * 返回异常具体信息
     * @return
     */
   /* @ExceptionHandler(exception.class)
    public ModelAndView exception(exception e, HttpServletResponse response) {
        response.setStatus(500);
        log.error(e.getMessage(),e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mes",e.getMessage());
        modelAndView.setViewName("index");
        return modelAndView;
    }*/

   @ExceptionHandler(value = Exception.class)
   @ResponseBody
   public ResponseEntity<ErrorDto> exception(Exception exception, HttpServletResponse httpServletResponse){
      ErrorDto errorDto = new ErrorDto();
      if(exception instanceof BussinessException){
          BussinessException exception1 = (BussinessException) exception;
          errorDto.setErrorCode(exception1.getErrorCode());
      }else{
          //其他
          errorDto.setErrorCode(0);
      }
       errorDto.setTips(exception.getMessage());
       ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto, HttpStatus.valueOf(httpServletResponse.getStatus()));
       return responseEntity;
   }

}
