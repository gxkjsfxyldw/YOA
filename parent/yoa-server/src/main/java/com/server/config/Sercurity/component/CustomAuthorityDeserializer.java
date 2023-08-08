package com.server.config.Sercurity.component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * 自定义Authority解析器
 * 用于将 JSON 数据中的权限信息反序列化为 Spring Security 中的 GrantedAuthority 列表
 */
public class CustomAuthorityDeserializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        //首先从 JsonParser 中获取 ObjectMapper 对象，然后使用该对象解析 JSON 数据
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        //从 JSON 解析器中读取 JSON 数据，生成一个 JsonNode 对象，该对象表示整个 JSON 数据
        JsonNode jsonNode = mapper.readTree(p);
        //用于存储反序列化后的权限列表
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        //用于存储反序列化后的权限列表
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()){
            //对于每个元素，从中获取名为 "authority" 的属性（节点）
            // 然后将这个属性的值（权限名）转换为字符串
            // 并使用这个字符串创建一个新的 SimpleGrantedAuthority 对象
            JsonNode next = elements.next();
            JsonNode authority = next.get("authority");
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.asText()));
        }
        return grantedAuthorities;
    }
}
