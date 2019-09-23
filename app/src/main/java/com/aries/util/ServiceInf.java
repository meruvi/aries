package com.aries.util;

public class ServiceInf {

    private String url="";
    private String nameService;
//    private String json;
//
    public ServiceInf(String nameService,String url){
        this.nameService=nameService;
        this.url=url;
    }
//

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getNameService() {
        return nameService;
    }
    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

//    public String getJson() {
//        return json;
//    }
//    public void setJson(String json) {
//        this.json = json;
//    }
//


}
