package com.synergykit.sdk.builders;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.SynergyKIT;
import com.synergykit.sdk.builders.uri.Collection;
import com.synergykit.sdk.builders.uri.Filter;
import com.synergykit.sdk.builders.uri.InLineCount;
import com.synergykit.sdk.builders.uri.OrderBy;
import com.synergykit.sdk.builders.uri.RecordId;
import com.synergykit.sdk.builders.uri.Resource;
import com.synergykit.sdk.builders.uri.Select;
import com.synergykit.sdk.builders.uri.Skip;
import com.synergykit.sdk.builders.uri.Top;

import com.synergykit.sdk.resources.SynergyKITUri;

public class UriBuilder {
	/* Constants */
	private static final String BASE_SYNERGYKIT_URL = "https://%s.api.synergykit.com/v2";		
	
	/* Attributes */
	private Resource resource = new Resource();
	private Collection collection = new Collection();
	private RecordId recordId = new RecordId();
	private Filter filter = new Filter();
	private OrderBy orderBy = new OrderBy();
	private Top top = new Top();
	private Skip skip = new Skip();
	private Select select = new Select();
	private InLineCount inLineCount = new InLineCount();

	
	/* Resource setter */
	public UriBuilder setResource(String resource){
		this.resource.setResource(resource);
		return this;
	}
	
	/* Resource URL setter */
	public UriBuilder setCollection(String collection){
		this.collection.setCollection(collection);
		return this;
	}
	
	/* Resource Id setter */
	public UriBuilder setRecordId(String recordId){
		this.recordId.setRecordId(recordId);
		return this;
	}
	
	/* Filter setter */
	public UriBuilder setFilter(String attribute, String operator, String parametr){
		this.filter.setFilter(attribute, operator, parametr);;
		return this;
	}
	
	/* Filter setter */
	public UriBuilder setFilter(String attribute, String operator, int parametr){
		this.filter.setFilter(attribute, operator, parametr);
		return this;
	}
	
	/* Filter setter */
	public UriBuilder setFilter(String filter){
		this.filter.setFilter(filter);
		return this;
	}
	
	/* Select setter */
	public UriBuilder setSelect(String attribute){
		this.select.setSelect(attribute);
		return this;
	}
	
	/* Order by desc setter */
	public UriBuilder setOrderByDesc(String parameter){
		this.orderBy.setOrderByDesc(parameter);
		return this;
	}

	/* Order by asc setter */
	public UriBuilder setOrderByAsc(String parameter){
		this.orderBy.setOrderByAsc(parameter);
		return this;
	}
	
	/* Top setter */
	public UriBuilder setTop(int top){
		this.top.setTop(top);
		return this;
	}
	
	/* Top setter */
	public UriBuilder setSkip(int skip){
		this.skip.setSkip(skip);
		return this;
	}
	
	public UriBuilder setInLineCountEnabled(boolean enabled){
		this.inLineCount.setEnabled(enabled);
		return this;
	}
	
	/* Build */
	public SynergyKITUri build(){
		String uri = new String(BASE_SYNERGYKIT_URL);
		boolean hasFilters = false;
		
		
		uri += "/" + resource.getResource();  //set resource	
		
		if(collection.getCollection()!=null)
			uri += "/" + collection.getCollection();  //set collection
		
		
		if(recordId.getRecordId()!=null)
			uri += "/" + recordId.getRecordId();	//set resource id 
		
		
		if(select.getSelect()!=null){
			
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri += select.getSelect();
		}
		
		//set filter
		if(filter.getFilter()!=null){
			
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri+=filter.getFilter();
		}
		
		//set order by
		if(orderBy.getOrderBy()!=null){
			
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri+=orderBy.getOrderBy();
		}
			
		
		//set top
		if(top.getTop() != null){
			
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri+=top.getTop();
		}
		
		//set skip
		if(skip.getSkip()!=null){
			
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri+=skip.getSkip();
		}
		
		//set inline count
		if(inLineCount.isEnabled()){
		
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri+=inLineCount.getInLineCount();
		}
		
		
		
		//set tenant
		uri = String.format(uri, SynergyKIT.getTenant());
		
		
		return new SynergyKITUri(uri);	
	}
}
