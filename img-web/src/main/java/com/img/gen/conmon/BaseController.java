package com.img.gen.conmon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * controller 公共基类
 * Created by zx.dev on 2016/1/6.
 */
public class BaseController {

    protected Logger log=LoggerFactory.getLogger(getClass());
    
    private JsonMapper jsonMapper = new JsonMapper(JsonInclude.Include.USE_DEFAULTS);
    private JsonMapper nonEmptyJsonMapper = JsonMapper.nonEmptyMapper();
    private JsonMapper nomDefaultJsonMapper = JsonMapper.nonDefaultMapper();


    //
    public JsonMapper getJsonMapper(){
        return jsonMapper;
    }

    public JsonMapper getNonEmptyJsonMapper() {
        return nonEmptyJsonMapper;
    }

    public JsonMapper getNomDefaultJsonMapper() {
        return nomDefaultJsonMapper;
    }
}
