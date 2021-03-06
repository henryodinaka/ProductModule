package com.inits.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.inits.productservice.constants.ResponseCode;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Data
@Slf4j
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {

  private String code;
  private String msg;
  private Object data;
  @JsonIgnore
  private int httpCode;

  public Response(String code, String msg, Object data) {
    this.code = ("200".equals(code) ? "00" : code);
    this.msg = msg;
    this.data = data;
  }
  public static ResponseEntity<Response> setUpResponse(Integer httpCode, String statusCode, String statusMessage){
    Response responseMessage = new Response(statusCode,statusMessage, null);
    log.info(" Status 1 {} build {}",statusCode,responseMessage.getCode());
    return ResponseEntity.status(httpCode).body(responseMessage);
  }
  public static ResponseEntity<Response> setUpResponse(Integer httpCode, String statusCode, String statusMessage, Object obj){
    Response responseMessage = new Response(statusCode,statusMessage, obj);

    log.info(" Status 2 {} msg {}",statusCode,responseMessage.getMsg());
    return ResponseEntity.status(httpCode).body(responseMessage);
  }
  public static ResponseEntity<Response> setUpResponse(ResponseCode responseCode, String replace, Object obj){
    Response responseMessage = new Response(responseCode.getStatusCode(), responseCode.getValue().replace("{}",replace), obj);
    log.info(" Status 3 {} build {} {}",responseCode.getStatusCode(),responseMessage.getCode(),responseCode);
    return ResponseEntity.status(responseCode.getCode()).body(responseMessage);
  }
public static Response setUpResponse1(ResponseCode responseCode, String replace, Object obj){
    Response response = new Response(responseCode.getStatusCode(), responseCode.getValue().replace("{}", replace), obj);
    response.setHttpCode(responseCode.getCode());
    return response;
  }
  public static Response setUpResponse1(ResponseCode responseCode, String replace){
    Response response = new Response(responseCode.getStatusCode(), responseCode.getValue().replace("{}", replace),null);
    response.setHttpCode(responseCode.getCode());
    return response;
  }
  public static ResponseEntity<Response> setUpResponse(ResponseCode responseCode, String replace){
    Response response = new Response(responseCode.getStatusCode(), responseCode.getValue().replace("{}", replace), null);
    log.info(" Status 4 {} build {}",responseCode.getStatusCode(),response.getCode());
    return ResponseEntity.status(responseCode.getCode()).body(response);
  }

}