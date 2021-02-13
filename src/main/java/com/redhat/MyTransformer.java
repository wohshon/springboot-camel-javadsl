/*
 * Copyright 2016 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package com.redhat;

import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.redhat.entities.Product;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * A sample transform
 */
@Component(value = "myTransformer")
@Slf4j
public class MyTransformer {


    public void convertJson(Exchange ex) {
        log.info("converting to json");
        //log.info("converting to Json {}",ex.getIn().getBody());
        Gson gson = new Gson();
        String json = gson.toJson(ex.getIn().getBody());
        log.info("json : {}",json);
        ex.getOut().setBody(json);
        
    }

    public void request(Exchange ex) {
        log.info("====================="+ex.getIn().getBody().toString());
        String input = ex.getIn().getBody().toString();
        //JsonObject jsonObject = JsonParser.parseString(input).getAsJsonObject();
        //log.info(" hello "+ jsonObject.get("hello"));
        Gson gson = new Gson();
        Product prod = gson.fromJson(input, Product.class);
        //Simulate transformation
        prod.setShortDesc("THIS IS A SHORT DESCRIPTION OF "+prod.getName());
        prod.setPrice(prod.getPrice() + 500);
        prod.setImg("img/random.png");
        log.info(prod.toString());

        ex.getOut().setBody(prod);
    }

    
}
