package com.kaseihaku.bpm.app.one.workflow.feature.v2.n1_base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kaseihaku.boot.starter.jackson.JacksonUtil;
import com.kaseihaku.boot.starter.orika.OrikaMapperFactorySingleton;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class BaseV2PojoTest {

    @Test
    public void orikaMap2BeanTest(){

        // language=json
        String json = "{\n" +
                      "    \"bean\": {\n" +
                      "      \"instantList\": [\n" +
                      "        1715184000000\n" +
                      "      ],\n" +
                      "      \"decimal\": 3\n" +
                      "    },\n" +
                      "    \"beanArySet\": [\n" +
                      "      [\n" +
                      "        {\n" +
                      "          \"instantList\": [\n" +
                      "            1716393600000,\n" +
                      "            1715151011000\n" +
                      "          ],\n" +
                      "          \"decimal\": 23\n" +
                      "        }\n" +
                      "      ]\n" +
                      "    ],\n" +
                      "    \"beanSet\": [\n" +
                      "      {\n" +
                      "        \"instantList\": [\n" +
                      "          1715529600000,\n" +
                      "          1714665600000\n" +
                      "        ],\n" +
                      "        \"decimal\": 1.43\n" +
                      "      }\n" +
                      "    ],\n" +
                      "    \"birthday\": 1715788800000,\n" +
                      "    \"distanceOfM78Nebula\": 3,\n" +
                      "    \"file\": \"2024-05/08/186732312858193920-aa.jpeg\",\n" +
                      "    \"hardDiskVolume\": 5565,\n" +
                      "    \"phone\": \"23423\",\n" +
                      "    \"season\": \"summer\",\n" +
                      "    \"stockAmount\": 345.767,\n" +
                      "    \"strAry\": [\n" +
                      "      \"ii8u\",\n" +
                      "      \"ds\"\n" +
                      "    ],\n" +
                      "    \"tokenExpireTime\": 1715184000000,\n" +
                      "    \"weight\": 3,\n" +
                      "    \"yesOrNo\": true\n" +
                      "}";

        LinkedHashMap<String, Object> map = JacksonUtil.readValue(json,
            new TypeReference<LinkedHashMap<String, Object>>() {
        });

        BaseV2Pojo map1 = OrikaMapperFactorySingleton.getInstance().getMapperFacade().map(map, BaseV2Pojo.class);
        System.out.println(222);


    }


}
