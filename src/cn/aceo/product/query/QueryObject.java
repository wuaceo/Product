package cn.aceo.product.query;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryObject {
    //参数列表
    private List<Object> params=new ArrayList();
    //占位符
    private List<Object> conditions=new ArrayList<>();
    //查询语句
    public String getQuery(){

        StringBuilder sb=new StringBuilder(100);

        this.customized();

        //重要
        if(conditions.size()==0){
            return "";
        }
        String sqlString= StringUtils.join(conditions," AND ");
        return sb.append(" WHERE ").append(sqlString).toString();
    }
    //向子类暴露
    protected void customized() {
    }
    protected void addQuery(String conditions,Object params){
        this.conditions.add(conditions);
        this.params.add(params);
    }
    //获取参数
    public List<Object> getParam(){
        return params;
    }

}
