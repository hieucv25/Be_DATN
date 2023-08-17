package com.example.demo.repository;

import com.example.demo.model.NhanVien;
import com.example.demo.model.NhanVienMap;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class NhanVienDao {
    RestTemplate rest=new RestTemplate();

    String url="https://poly-nhanvien-default-rtdb.firebaseio.com/nhanvien.json";
    private String getUrl(String key){
        return url.replace(".json","/"+key+".json");
    }
    public NhanVienMap findAll(){
        return rest.getForObject(url,NhanVienMap.class);
    }
    public NhanVien findByKey(String key){
        return rest.getForObject(getUrl(key), NhanVien.class);
    }
    public String create(NhanVien data){
        HttpEntity<NhanVien> entity=new HttpEntity<>(data);
        JsonNode resp=rest.postForObject(url,entity,JsonNode.class);
        return resp.get("name").asText();
    }
    public NhanVien update(String key,NhanVien data){
        HttpEntity<NhanVien> entity=new HttpEntity<>(data);
        rest.put(getUrl(key),entity);
        return data;
    }
    public void delete(String key){
        rest.delete(getUrl(key));
    }
}
