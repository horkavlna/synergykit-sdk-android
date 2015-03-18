package com.synergykit.sdk.builders;

import com.synergykit.sdk.builders.uri.Collection;
import com.synergykit.sdk.builders.uri.Filter;
import com.synergykit.sdk.builders.uri.InLineCount;
import com.synergykit.sdk.builders.uri.OrderBy;
import com.synergykit.sdk.builders.uri.RecordId;
import com.synergykit.sdk.builders.uri.Resource;
import com.synergykit.sdk.builders.uri.Select;
import com.synergykit.sdk.builders.uri.Skip;
import com.synergykit.sdk.builders.uri.Top;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 18. 3. 2015.
 */
public class ODataBuilder {

    /* Attributes */
    private Filter filter = new Filter();
    private OrderBy orderBy = new OrderBy();
    private Top top = new Top();
    private Skip skip = new Skip();
    private Select select = new Select();
    private InLineCount inLineCount = new InLineCount();

    /* New instance */
    public static ODataBuilder newInstance(){
        return new ODataBuilder();
    }

    /* Filter setter */
    public ODataBuilder setFilter(String attribute, String operator, String parametr){
        this.filter.setFilter(attribute, operator, parametr);;
        return this;
    }

    /* Filter setter */
    public ODataBuilder setFilter(String attribute, String operator, int parametr){
        this.filter.setFilter(attribute, operator, parametr);
        return this;
    }

    /* Filter setter */
    public ODataBuilder setFilter(String filter){
        this.filter.setFilter(filter);
        return this;
    }

    /* Select setter */
    public ODataBuilder setSelect(String attribute){
        this.select.setSelect(attribute);
        return this;
    }

    /* Order by desc setter */
    public ODataBuilder setOrderByDesc(String parameter){
        this.orderBy.setOrderByDesc(parameter);
        return this;
    }

    /* Order by asc setter */
    public ODataBuilder setOrderByAsc(String parameter){
        this.orderBy.setOrderByAsc(parameter);
        return this;
    }

    /* Top setter */
    public ODataBuilder setTop(int top){
        this.top.setTop(top);
        return this;
    }

    /* Top setter */
    public ODataBuilder setSkip(int skip){
        this.skip.setSkip(skip);
        return this;
    }

    public ODataBuilder setInLineCountEnabled(boolean enabled){
        this.inLineCount.setEnabled(enabled);
        return this;
    }

    /* Build */
    public String build(){
        String oData = new String();
        boolean hasFilters = false;


        if(select.getSelect()!=null){

            if(hasFilters==false){
                oData+="?";
                hasFilters=true;
            }

            oData += select.getSelect();
        }

        //set filter
        if(filter.getFilter()!=null){

            if(hasFilters==false){
                oData+="?";
                hasFilters=true;
            }

            oData+=filter.getFilter();
        }

        //set order by
        if(orderBy.getOrderBy()!=null){

            if(hasFilters==false){
                oData+="?";
                hasFilters=true;
            }

            oData+=orderBy.getOrderBy();
        }


        //set top
        if(top.getTop() != null){

            if(hasFilters==false){
                oData+="?";
                hasFilters=true;
            }

            oData+=top.getTop();
        }

        //set skip
        if(skip.getSkip()!=null){

            if(hasFilters==false){
                oData+="?";
                hasFilters=true;
            }

            oData+=skip.getSkip();
        }

        //set inline count
        if(inLineCount.isEnabled()){

            if(hasFilters==false){
                oData+="?";
            }

            oData+=inLineCount.getInLineCount();
        }

        return oData;
    }
}
