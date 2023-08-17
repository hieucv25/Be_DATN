package com.example.demo.repository;


import com.example.demo.model.PhuKien;
import com.example.demo.model.PhuKienMap;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
@Repository
public class PhuKienDao {
    RestTemplate rest=new RestTemplate();

    String url="https://poly-phukien-default-rtdb.firebaseio.com/phukien.json";
    private String getUrl(String key){
        return url.replace(".json","/"+key+".json");
    }
    public PhuKienMap findAll(){
        return rest.getForObject(url,PhuKienMap.class);
    }
    public PhuKien findByKey(String key){
        return rest.getForObject(getUrl(key), PhuKien.class);
    }
    public String create(PhuKien data){
        HttpEntity<PhuKien> entity=new HttpEntity<>(data);
        JsonNode resp=rest.postForObject(url,entity,JsonNode.class);
        return resp.get("name").asText();
    }
    public PhuKien update(String key,PhuKien data){
        HttpEntity<PhuKien> entity=new HttpEntity<>(data);
        rest.put(getUrl(key),entity);
        return data;
    }
    public void delete(String key){
        rest.delete(getUrl(key));
    }
}
